package br.com.depro.mugetsu.web.zk.tree;

import java.lang.ref.SoftReference;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import br.com.depro.mugetsu.web.zk.tree.schema.RootMenuDomain;

/**
 * Fabrica para inst�ncia do menu <br><br>
 * 
 * <strong>Hist�rico
 * <ul>
 * 		<li><strong>@author rsouza @version 1.0 - Data: 15/03/2012</li>
 * </ul>
 * 
 * @since 1.0
 * 
 */
public class MetaMenuFactory {

	private static SoftReference<RootMenuDomain> referenceRootMenuDomain = new SoftReference<RootMenuDomain>(null);
	private static String menuXMLRootPath = "/";

	/**
	 * Metodo para ler e obter menu principal da aplicacao
	 * 
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
