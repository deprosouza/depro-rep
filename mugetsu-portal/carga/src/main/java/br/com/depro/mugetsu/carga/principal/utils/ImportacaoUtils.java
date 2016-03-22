package br.com.depro.mugetsu.carga.principal.utils;

import java.text.Normalizer;

import org.apache.commons.lang.StringUtils;

import br.com.depro.mugetsu.model.media.Media;
import br.com.depro.mugetsu.model.media.nome.MediaNome;

/**
 * 
 * @author rsouza
 * @version 1.0 - Versao Inicial - 11/02/2013
 */
public class ImportacaoUtils {

    /**
     * Remove Tag htmls do texto
     * 
     * @param htmlString
     * @return
     */
    public static String removerHtml(String htmlString) {
    	String noHTMLString = htmlString;
    	if (htmlString != null) {
    		noHTMLString = htmlString.replaceAll("\\<.*?\\>", "");
	        noHTMLString = noHTMLString.replaceAll("\n", " ");
    	}
    	return noHTMLString;
    }

    /**
     * Transaforma Todas as Primeiras letras da palavra para maiusculo
     * 
     * @param s
     * @return
     */
    public static String toProperCase(String s) {
        String[] parts = s.split(" ");
        String camelCaseString = "";
        for (String part : parts) {
            if (StringUtils.isNotBlank(part)) {
                camelCaseString = camelCaseString + part.substring(0, 1).toUpperCase() + part.substring(1).toLowerCase() + " ";
            }
        }
        return camelCaseString.trim();
    }
    
    public static String normalizer(String string) {
    	return Normalizer.normalize(string, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
    }
    
    /**
     * Mescla dados de duas medias
     * @param mo
     * @param ma
     * @return Entidade {@link Media} mesclada
     */
    public static Media mergeMedia(Media mo, Media ma) {
    	
    	for (MediaNome titulo : ma.getNomes()) {
    		if (!mo.getNomes().contains(titulo)) {
    			mo.getNomes().add(titulo);
    		}
    	}

//    	for (BroadcastBase broadcast : ma.getBroadcasts()) {
//    		if (!mo.getBroadcasts().contains(broadcast)) {
//    			mo.getBroadcasts().add(broadcast);
//    		}
//    	}
    	
    	if (StringUtils.isNotBlank(ma.getSinopse()) && !mo.getSinopse().equals(ma.getSinopse())) {
    		mo.setSinopse(ma.getSinopse());
    	}
    	
    	mo.getGeneros().clear();
    	mo.getGeneros().addAll(ma.getGeneros());
    	
    	mo.getTemas().clear();
    	mo.getTemas().addAll(ma.getTemas());
    	
    	mo.setNomePrincipal(mo.getNomePrincipal());
    	switch (mo.getFormatoMedia()) {
    		case ANIME:
	    		if (mo.getQuantidedaEpisodios() == 0 || ma.getQuantidedaEpisodios() > 0) {
	    			mo.setEpisodios(ma.getEpisodios());
	    		}
	    		mo.setQuantidadeVolumes(0);
	    		mo.setQuantidadeTemporadas(0);
	    		break;
    		case MANGA:
    			if (mo.getQuantidadeVolumes() == 0 || ma.getQuantidadeVolumes() > 0) {
	    			mo.setQuantidadeVolumes(ma.getQuantidadeVolumes());
	    		}
    			mo.setQuantidadeTemporadas(0);
    			mo.setQuantidedaEpisodios(0);
    			break;
    		case FILME:
    			mo.setQuantidadeTemporadas(0);
    			mo.setQuantidedaEpisodios(0);
    			mo.setQuantidadeVolumes(0);
    			break;
    	}
    	
    	return mo;
    }
    
}
