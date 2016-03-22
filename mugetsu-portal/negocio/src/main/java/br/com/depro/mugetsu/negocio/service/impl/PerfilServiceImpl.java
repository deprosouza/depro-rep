package br.com.depro.mugetsu.negocio.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.depro.fw.typezero.infrastructure.exception.ApplicationException;
import br.com.depro.fw.typezero.infrastructure.utils.PropConfig;
import br.com.depro.mugetsu.model.media.Media;
import br.com.depro.mugetsu.model.security.Login;
import br.com.depro.mugetsu.negocio.service.PerfilService;
import br.com.depro.mugetsu.negocio.service.media.MediaService;

/**
 *
 * @author rsouza
 * @version 1.0 - Versao Inicial - 18.08.2012
 */
@Service
public class PerfilServiceImpl implements PerfilService {

    private static final String PATH_INDECADORES = "dir.path.indicacao.user";
    @Autowired
    private MediaService mediaService;
    @Autowired
    private PropConfig propConfig;

    public List<Media> listraIndicacoesUsuario(Login login)
            throws ApplicationException {
        try {
            String path = this.propConfig.get(PATH_INDECADORES, login.getUsername());
            List<Media> medias = new ArrayList<Media>();
            File files[] = new File(path).listFiles();
            for (File file : files) {
                medias.add(this.mediaService.obterMediaPorNomeImagem(file.getName()));
            }
            return medias;
        } catch (ApplicationException nexp) {
            throw new ApplicationException();
        }
    }
}
