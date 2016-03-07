package br.com.depro.fw.typezero.infrastructure.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;

import br.com.depro.fw.typezero.infrastructure.exception.ApplicationException;

/**
 * 
 * @author rsouza
 * @version 1.0 - Versao Inicial - 26.02.2015
 */
public class ExtracaoUtils {

    private ResumoExtracao resumo = new ResumoExtracao();
    private PropConfig propConfig;
    private String pathOutput;
    private String sufixoNomeArquivo;
    private String nomeArquivoGenerado;
    private boolean isRemoveOriginalFile = false;

    /**
     * @param propConfig
     * @param pathOutput
     * @param sufixoNomeArquivo
     */
    public ExtracaoUtils(PropConfig propConfig) {
        super();
        this.propConfig = propConfig;
        this.nomeArquivoGenerado = "";
    }

    /**
     * @param propConfig
     * @param pathOutput
     * @param sufixoNomeArquivo
     */
    public ExtracaoUtils(PropConfig propConfig, String pathOutput, String sufixoNomeArquivo) {
        super();
        this.propConfig = propConfig;
        this.pathOutput = pathOutput;
        this.sufixoNomeArquivo = sufixoNomeArquivo;
        this.nomeArquivoGenerado = "";
    }
    
    /**
     * 
     * @param propConfig
     * @param pathOutput
     * @param sufixoNomeArquivo
     * @param nomeArquivoGenerado
     */
    public ExtracaoUtils(PropConfig propConfig, String pathOutput, String sufixoNomeArquivo, String nomeArquivoGenerado) {
        super();
        this.propConfig = propConfig;
        this.pathOutput = pathOutput;
        this.sufixoNomeArquivo = sufixoNomeArquivo;
        this.nomeArquivoGenerado = nomeArquivoGenerado;
    }

    /**
     * @param propConfig
     * @param pathOutput
     * @param sufixoNomeArquivo
     */
    public ExtracaoUtils(PropConfig propConfig, String pathOutput, String sufixoNomeArquivo, boolean isRemoveOriginalFile) {
        super();
        this.propConfig = propConfig;
        this.pathOutput = pathOutput;
        this.sufixoNomeArquivo = sufixoNomeArquivo;
        this.isRemoveOriginalFile = isRemoveOriginalFile;
        this.nomeArquivoGenerado = "";
    }

    /**
     * Indica se o arquivo gerado ja existe.
     * 
     * @return true caso exista
     */
    public boolean exists() {
        return new File(getFullPathOutput()).exists();
    }

    /**
     * 
     * @param URL
     * @param id
     * @param isImagem TODO
     * @param string 
     * @throws ApplicationException
     */
    public void doRequest(String URL, Object id, boolean isImagem) throws ApplicationException {
        String nomeArquivo = "";
        if (isImagem) {
            this.nomeArquivoGenerado = nomeArquivo = "" + UUID.randomUUID() + new Date().getTime();
            this.nomeArquivoGenerado += ".jpg";
        } else {
            this.nomeArquivoGenerado = nomeArquivo = "" + id.toString() + "-" + sufixoNomeArquivo;
            this.nomeArquivoGenerado += ".txt";
        }
        String path = getFullPathOutput(nomeArquivo, isImagem);
        File file = new File(path);
        if (!file.exists() || isRemoveOriginalFile) {
            try {
                URL url = new URL(URL);
                URLConnection conn = url.openConnection();
                conn.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
                criarArquivo(conn.getInputStream(), file);
                this.resumo.addQuantidadeImportado();
            } catch (IOException ioexp) {
                throw new ApplicationException("INFRA0028", "Falha na requisicao para \"" + nomeArquivo + "\".");
            }
        } else {
            this.resumo.addQuantidadeExistente();
        }
    }

    /**
     * 
     * @param URL
     * @throws ApplicationException
     */
    public List<String> doRequest(String URL) throws ApplicationException {
        try {
            URL url = new URL(URL);
            URLConnection conn = url.openConnection();
            conn.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
            List<String> linhas = new ArrayList<String>();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String linha = "";
            while ((linha = bufferedReader.readLine()) != null) {
                linhas.add(linha);
            }
            return linhas;
        } catch (IOException ioexp) {
            throw new ApplicationException("INFRA0028", "Falha na requisicao da URL - " + ioexp.getMessage());
        }
    }

    /**
     * Obtem linhas de um arquivo
     * @return Lista com linhas de um arquivo 
     * @throws ApplicationException Caso algum erro ocorra
     */
    public List<String> obterLinhas() throws ApplicationException {
        try {
            List<String> linhas = new ArrayList<String>();
            BufferedReader bf = new BufferedReader(new FileReader(new File(getFullPathOutput())));
            while (bf.ready()) {
                linhas.add(bf.readLine().trim());
            }
            bf.close();
            return linhas;
        } catch (FileNotFoundException fnfexp) {
            throw new ApplicationException("INFRA0026", "Arquivo com conteudo nao encontrado.");
        } catch (IOException ioexp) {
            throw new ApplicationException("INFRA0027", "Falha na escrita do arquivo do conteudo.");
        }
    }

    /**
     * @param inputStream
     * @param file
     * @throws ApplicationException 
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void criarArquivo(InputStream inputStream, File file) throws ApplicationException {
        BufferedInputStream in = new BufferedInputStream(inputStream);
        BufferedOutputStream out = null;
        OutputStream fout;
        try {
            fout = new FileOutputStream(file, !isRemoveOriginalFile);
            out = new BufferedOutputStream(fout);
            byte buffer[] = new byte[1024];
            int ch = in.read(buffer);
            while (ch != -1) {
                out.write(buffer, 0, ch);
                ch = in.read(buffer);
            }
            out.close();
            in.close();
        } catch (FileNotFoundException fnfexp) {
            throw new ApplicationException("INFRA0026", "Arquivo com conteudo nao encontrado.");
        } catch (IOException ioexp) {
            throw new ApplicationException("INFRA0027", "Falha na escrita do arquivo do conteudo.");
        }
    }

    /**
     * @param linhas
     * @throws ApplicationException
     */
    public void criarArquivo(List<String> linhas) throws ApplicationException {
        try {
            BufferedWriter fr = new BufferedWriter(new FileWriter(getFullPathOutput(), !isRemoveOriginalFile));
            for (String linha : linhas) {
                fr.write(linha);
                fr.newLine();
            }
            fr.close();
        } catch (IOException fnfexp) {
            throw new ApplicationException("INFRA0026", "Arquivo com conteudo nao encontrado.");
        }
    }

    /**
     * 
     * @param nomeArquivo
     * @param isImagem TODO
     * @return
     */
    public String getFullPathOutput(String nomeArquivo, boolean isImagem) {
        String path = propConfig.get(pathOutput) + nomeArquivo;
        if (isImagem) {
            path += ".jpg";
        } else {
            path += ".txt";
        }
        return path;
    }

    /**
     * @return
     */
    public File getFile() {
    	File file = new File(getFullPathOutput());
    	if (file.exists()) {
    		return file;
    	} else {
    		return null;
    	}
    }
    
    /**
     * 
     */
    public void removeFile() {
    	getFile().delete();
    }

    /**
     * 
     * @param nomeArquivo
     * @return
     */
    public String getFullPathOutput() {
        return propConfig.get(pathOutput) + (StringUtils.isNotBlank(nomeArquivoGenerado) ? nomeArquivoGenerado : sufixoNomeArquivo + ".txt");
    }

    /**
     * @return the resumo
     */
    public ResumoExtracao getResumo() {
        return resumo;
    }

    /**
     * @param resumo the resumo to set
     */
    public void setResumo(ResumoExtracao resumo) {
        this.resumo = resumo;
    }

    /**
     * @return the nomeArquivoGenerado
     */
    public String getNomeArquivoGerado() {
        return nomeArquivoGenerado;
    }
}
