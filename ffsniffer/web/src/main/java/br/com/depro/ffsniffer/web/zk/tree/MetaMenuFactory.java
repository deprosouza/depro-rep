package br.com.depro.ffsniffer.web.zk.tree;

import java.lang.ref.SoftReference;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import br.com.depro.ffsniffer.web.zk.tree.schema.RootMenuDomain;

/**
 * @author rsouza
 * @version 1.0 - Versao Inicial - 11.03.2015
 */
public class MetaMenuFactory {

	private static SoftReference<RootMenuDomain> referenceRootMenuDomain = new SoftReference<RootMenuDomain>(null);
	private static String menuXMLRootPath = "/";

	/**
	 * Metodo para ler e obter menu principal da aplicacao
	 * @return {@link RootMenuDomain}
	 */
	public static RootMenuDomain getRootMenuDomain() {
		RootMenuDomain rootMenuDomain = referenceRootMenuDomain.get();
		if (rootMenuDomain == null) {
			try {
				Unmarshaller unmarshaller = JAXBContext.newInstance(RootMenuDomain.class).createUnmarshaller();
				rootMenuDomain = (RootMenuDomain) unmarshaller.unmarshal(MetaMenuFactory.class.getResource(menuXMLRootPath + "mainmenu.xml"));
				referenceRootMenuDomain = new SoftReference<RootMenuDomain>(rootMenuDomain);

			} catch (JAXBException e) {
				throw new RuntimeException(e);
			}
		}
		return rootMenuDomain;
	}
}
