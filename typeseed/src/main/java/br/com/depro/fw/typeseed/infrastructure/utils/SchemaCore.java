package br.com.depro.fw.typeseed.infrastructure.utils;

/**
 * @author rsouza
 * @version 2.0 - versao inicial - 08.06.2016
 */
public class SchemaCore {

	private String entityName;
	private String entityPackage;
	private String entityDaoPackage;
	private String entityServicePackage;
	private String suffixProject;
	private String packageBase;

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public String getEntityPackage() {
		return entityPackage;
	}

	public void setEntityPackage(String entityPackage) {
		this.entityPackage = entityPackage;
	}

	public String getEntityDaoPackage() {
		return entityDaoPackage;
	}

	public void setEntityDaoPackage(String entityDaoPackage) {
		this.entityDaoPackage = entityDaoPackage;
	}

	public String getEntityServicePackage() {
		return entityServicePackage;
	}

	public void setEntityServicePackage(String entityServicePackage) {
		this.entityServicePackage = entityServicePackage;
	}

	public String getSuffixProject() {
		return suffixProject;
	}

	public void setSuffixProject(String suffixProject) {
		this.suffixProject = suffixProject;
	}

	public String getPackageBase() {
		return packageBase;
	}

	public void setPackageBase(String packageBase) {
		this.packageBase = packageBase;
	}

}
