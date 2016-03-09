package br.com.depro.ffsniffer.web.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.UUID;

import org.zkoss.image.AImage;

import br.com.depro.fw.typezero.infrastructure.exception.ApplicationException;
import br.com.depro.fw.typezero.infrastructure.spring.TypezeroSpringUtils;
import br.com.depro.fw.typezero.infrastructure.utils.PropConfig;

/**
 * @author rsouza
 * @version 1.0 - Versao Inicial - 11.03.2015
 */
public class UploadUtils {

    private static PropConfig propConfig;

    static {
        propConfig = (PropConfig) TypezeroSpringUtils.getBean(PropConfig.class);
    }

    /**
     *
     * @param imagem
     * @param evento
     * @return
     * @throws ApplicationException
     */
    public static String uploadFromFile(AImage imagem, TipoEvento evento) throws ApplicationException {
        String nomeArquivo = "" + UUID.randomUUID() + new Date().getTime() + ".jpg";
        String path = getFullPathOutput(nomeArquivo, evento);
        File file = new File(path);
        criarArquivo(imagem.getStreamData(), file);
        return nomeArquivo;
    }

    /**
     *
     * @param url
     * @param evento
     * @return
     * @throws ApplicationException
     * @throws IOException
     */
    public static String uploadFromURL(String enderecoImagem, TipoEvento evento) throws ApplicationException {
        String nomeArquivo = "" + UUID.randomUUID() + new Date().getTime() + ".jpg";
        String path = getFullPathOutput(nomeArquivo, evento);;
        File file = new File(path);
        try {
            URL url = new URL(enderecoImagem);
            URLConnection conn;
            conn = url.openConnection();
            criarArquivo(conn.getInputStream(), file);
            return nomeArquivo;
        } catch (IOException ioexp) {
            throw new ApplicationException(ioexp);
        }
    }

    /**
     * @param inputStream
     * @param file
     * @throws ApplicationException
     * @throws FileNotFoundException
     * @throws IOException
     */
    private static void criarArquivo(InputStream inputStream, File file) throws ApplicationException {
        BufferedInputStream in = new BufferedInputStream(inputStream);
        BufferedOutputStream out = null;
        OutputStream fout;
        try {
            fout = new FileOutputStream(file);
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
            throw new ApplicationException(fnfexp);
        } catch (IOException ioexp) {
            throw new ApplicationException(ioexp);
        }
    }

    /**
     *
     * @param nomeArquivo
     * @return
     */
    public static String getFullPathOutput(String nomeArquivo, TipoEvento evento, String... params) {
        String path = null;
        switch (evento) {
            case EXIBICAO:
                path = propConfig.get(evento.getKey());
                break;
            case AVATAR:
                path = propConfig.get(evento.getKey(), params);
                break;
        }
            
        return path + nomeArquivo;
    }
}
