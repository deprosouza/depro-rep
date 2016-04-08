package br.com.depro.fw.typezero.infrastructure.utils;

import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import br.com.depro.fw.typezero.infrastructure.model.EntidadeBase;

/**
 *
 * @author rsouza
 * @version 1.0 - Versao Incial - 11.09.2012
 */
public class Transform {

    /**
     * Cria corpo do email para ser enviado
     *
     * @param entidadeBase
     * @param template
     * @return String com conteudo do corpo do email
     */
    public static <E extends EntidadeBase> String renderToHtml(E entidadeBase, File template) {
        StringWriter stringWriterRetorno = new StringWriter();
        try {
            StringWriter stringWriter = new StringWriter();

            JAXBContext context = JAXBContext.newInstance(entidadeBase.getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(entidadeBase, stringWriter);

            StringReader stringReader = new StringReader(stringWriter.toString());
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer(new StreamSource(template));
            transformer.transform(new StreamSource(stringReader), new StreamResult(stringWriterRetorno));

        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return stringWriterRetorno.toString();
    }
}
