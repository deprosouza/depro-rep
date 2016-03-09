package br.com.depro.ffsniffer.carga.linkshell.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.depro.ffsniffer.carga.linkshell.model.QueryCombinationEnum;
import br.com.depro.ffsniffer.core.linkshell.service.LinkshellService;
import br.com.depro.ffsniffer.core.linkshell.service.PlayerService;
import br.com.depro.ffsniffer.model.Linkshell;
import br.com.depro.ffsniffer.model.Player;
import br.com.depro.ffsniffer.model.ServerEnum;
import br.com.depro.fw.typezero.infrastructure.exception.ApplicationException;
import br.com.depro.fw.typezero.infrastructure.utils.ExtracaoUtils;
import br.com.depro.fw.typezero.infrastructure.utils.PropConfig;

/**
 * @author rsouza
 * @version 1.0 - Versao Inicial - 25.02.2015
 */
@Service
public class ImportarLServiceImpl implements ImportarLSService {

	private static final String URL_BASE_LS = "http://na.finalfantasyxiv.com/lodestone/linkshell/";
	private static final String PARAM_QUERY_STRING = "q=";
	private static final String PARAM_SERVER = "worldname=";
	private static final String PARAM_QUANTIDADE_PLAYERS = "character_count=";
	private static final String PARAM_ORDEM = "order=";
	private static final String PARAM_PAGINA = "page=";
	
	@Autowired
	private PropConfig propConfig;
	@Autowired
	private LinkshellService linkshellService;
	@Autowired
	private PlayerService playerService;
	
	public void importarLinkshells(boolean isFromArquivo, boolean onlyNewOnes) throws ApplicationException {
		
	    Map<Linkshell, List<Player>> mapLS = extrarirConteudoHtml(isFromArquivo, onlyNewOnes);
		
		
		for (Map.Entry<Linkshell, List<Player>> entryLS : mapLS.entrySet()) {
			Linkshell ls = linkshellService.buscarPorIdLodestone(entryLS.getKey().getIdLodestone());
			if (ls != null) {
				ls.setNome(entryLS.getKey().getNome());
				for (Player playerUnsaved : entryLS.getValue()) {
					if (!ls.getMembros().contains(playerUnsaved)) {
						playerService.salvar(playerUnsaved);
						ls.getMembros().add(playerUnsaved);
						playerUnsaved.setLinkshells(new HashSet<Linkshell>(Arrays.asList(ls)));
					}
				}
				linkshellService.atualizar(ls);
			} else {
				ls = entryLS.getKey();
				ls.setMembros(new HashSet<Player>());
				
				for (Player playerUnsaved : entryLS.getValue()) {
					Player player = playerService.buscarPorIdLodestone(playerUnsaved.getIdLodestone());
					if (player == null) {
					    player = playerService.salvar(playerUnsaved);
					}
					ls.getMembros().add(player);
				}
				linkshellService.salvar(ls);
			}
		}
	}
	
	/**
	 * Obtem conteudo HTML das paginas de consulta de linkshell
	 * @param importacao
	 * @return 
	 * @throws ApplicationException 
	 */
	private Map<Linkshell, List<Player>> extrarirConteudoHtml(boolean isFromArquivo, boolean onlyNewOnes) throws ApplicationException {
	    int qtdePaginas = 20;
		Map<Linkshell, List<Player>> mapLS  = new HashMap<Linkshell, List<Player>>();
		
		for (ServerEnum server : ServerEnum.values()) {
		    List<String> linhas = new ArrayList<String>();
    		for (QueryCombinationEnum q1 : QueryCombinationEnum.values()) {
    			List<String> request = new ArrayList<String>();
    			ExtracaoUtils importacao = new ExtracaoUtils(propConfig, "path.dir.ffsnifffer.request.data", server.name() +  "_LS_" + q1.getExtenso());
    			if (!isFromArquivo || !importacao.exists()) {
    				for (QueryCombinationEnum q2 : QueryCombinationEnum.values()) {
    					for (int i = 1 ; i <= qtdePaginas ; i++) {
    						try {
    							request.addAll(importacao.doRequest(gerarURLConsulta(q1, q2, i, server.name())));
    							if (q1.isSolo() || q2.isSolo()) {
    								break;
    							}
    						} catch (ApplicationException e) {
    						}	
    					}
    					if (q1.isSolo()) {
    						break;
    					}
    				}
    				importacao.criarArquivo(request);
    				linhas.addAll(request);
    			} else if (!onlyNewOnes) {
    			    linhas.addAll(importacao.obterLinhas());
    			}
    		}
    		
    		Set<Linkshell> links = extrairLinksLS(linhas, server);
            mapLS.putAll(extrairPlayers(links, isFromArquivo, server));
		}
		return mapLS;
	}
	
	/**
	 * Cria a entidade {@link Linkshell} apartir do HTML informado 
	 * @param linhas
	 * @param server TODO
	 * @return
	 */
	private Set<Linkshell> extrairLinksLS(List<String> linhas, ServerEnum server) {
		Set<Linkshell> links = new HashSet<Linkshell>();
		for (String linha : linhas) {
			Matcher matcher = Pattern.compile("(.*.)/lodestone/linkshell/(.*.)/\">(.*.)</a>(.*.)").matcher(linha);
			if (matcher.matches()) {
				Linkshell linkshell = new Linkshell(matcher.group(2), server, matcher.group(3).replaceAll("&#39;", "'")
						.replaceAll("&amp;", "&"));
				links.add(linkshell);
			}
		}
		return links;
	}

	/**
	 * Extrai dados dos players
	 * @param links
	 * @param server TODO
	 * @param importacao
	 * @return 
	 */
	private Map<Linkshell, List<Player>> extrairPlayers(Set<Linkshell> links, boolean isFromArquivo, ServerEnum server) {
		Map<Linkshell, List<Player>> mapLS = new HashMap<Linkshell, List<Player>>();
		for (Linkshell linkshell : links) {
			try {
				List<String> conteudo = new ArrayList<String>();
				List<Player> players = new ArrayList<Player>();
				ExtracaoUtils importacao = new ExtracaoUtils(propConfig, "path.dir.ffsnifffer.request.data", server.name() + "_PlayersFromLS_" + linkshell.getNome());
				if (!isFromArquivo || !importacao.exists()) {
					for (int i = 1 ; i <= 3 ; i++){
						conteudo.addAll(importacao.doRequest(URL_BASE_LS + linkshell.getIdLodestone() + "/?" + PARAM_PAGINA + i));
					}
					importacao.criarArquivo(conteudo);
				} else {
					conteudo = importacao.obterLinhas();
				}
				boolean isInsideGrid = false;
				for (String linha : conteudo) {
					if (linha.contains("table_black_border_bottom") || isInsideGrid) {
						isInsideGrid = true;
						Matcher matcher = Pattern.compile("(.*.)/lodestone/character/(.*.)/\">(.*.)</a><(.*.)").matcher(linha);
						if (matcher.matches()) {
							if (!matcher.group(3).startsWith("<")) {
								players.add(new Player(server, matcher.group(2), matcher.group(3).replaceAll("&#39;", "'")));
							}
						} else {
							if (linha.contains("</table>")) {
								isInsideGrid = false;
							}
						}
					}
				}
				if (mapLS.containsKey(linkshell)) {
					mapLS.get(linkshell).addAll(players);
				} else {
					mapLS.put(linkshell, players);
				}
			} catch (ApplicationException e) {
			}
		}
		return mapLS;
	}

	/**
	 * Gera url para consulta de linkshell
	 * @param q1
	 * @param q2
	 * @param pagina
	 * @param server
	 * @return
	 */
	private String gerarURLConsulta(QueryCombinationEnum q1, QueryCombinationEnum q2, int pagina, String server) {
		String url = URL_BASE_LS + "?" + PARAM_SERVER + server + "&" + PARAM_ORDEM + "1&" + PARAM_PAGINA + pagina + "&";
		if (q1.isSolo()) {
			url += PARAM_QUERY_STRING + q1.getExtenso();
		} else {
			url += PARAM_QUERY_STRING + q1.getExtenso() + q2.getExtenso();
		}
		return url;
	}
}
