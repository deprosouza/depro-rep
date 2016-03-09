package br.com.depro.ffsniffer.web.zk.tree.schema;

/**
 * @author rsouza
 * @version 1.0 - Versao Inicial - 11.03.2015
 */
public interface IMenuDomain {

	String getRightName();
	String getId();
	String getLabel();
	Boolean isWithOnClickAction();
	String getZulNavigation();
	String getIconName();
}
