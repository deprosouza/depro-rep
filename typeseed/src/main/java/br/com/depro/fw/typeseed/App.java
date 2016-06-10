package br.com.depro.fw.typeseed;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import br.com.depro.fw.typezero.infrastructure.exception.ApplicationException;
import br.com.depro.fw.typezero.infrastructure.spring.TypezeroSpringUtils;
import br.com.depro.fw.typezero.infrastructure.utils.ExtracaoUtils;
import br.com.depro.fw.typezero.infrastructure.utils.PropConfig;

public class App {

	public static void main(String[] args) throws ApplicationException {
		PropConfig propConfig = (PropConfig) TypezeroSpringUtils.getBean(PropConfig.class);
		
		String pathProject = "/home/rsouza/Desktop/trunk/";
		String packageBase = "br.com.esb.cadastro";
		
		
		
		String pathBase = packageBase.replaceAll("\\.", "/");
		File baseDir = new File(pathProject);
		if (baseDir.isDirectory()) {

			Map<String, String> mapEntities = new HashMap<String, String>();
			File modelBaseDir = new File(baseDir, propConfig.get("src.path", "model"));

			findEntities(propConfig, mapEntities, modelBaseDir);
			
			String corePatheBase = pathBase + "/core";
			File coreBaseDir = new File(baseDir, propConfig.get("src.path", "core") + corePatheBase);
			if (!coreBaseDir.exists()) {
				coreBaseDir.mkdirs();
			}
			
			for (Map.Entry<String, String> entry : mapEntities.entrySet()) {
				Matcher matcher = Pattern.compile(".*\\/src\\/main\\/java\\/.*\\/model\\/?((.*)\\/(.*)\\.java|(.*)\\.java)").matcher(entry.getKey());
				if (matcher.find()) {
					String complementoPackage = "";
					String entityName = "";
					
					if (StringUtils.isNotBlank(matcher.group(2))) {
						complementoPackage = matcher.group(2);
						entityName = matcher.group(3);
					} else {
						entityName = matcher.group(4);
					}
					
					File classDir = null;
					String corePackageDAO = convertToPackage(corePatheBase, "dao", complementoPackage);
					if (StringUtils.isNotBlank(complementoPackage)) {
						classDir = new File(coreBaseDir, complementoPackage);
						classDir.mkdirs();
					} else {
						classDir = coreBaseDir;
					}

					
					URL url = App.class.getResource("/TemplateDAO");
					ExtracaoUtils extracaoUtils = new ExtracaoUtils(propConfig);
					List<String> linhasTemplate = new ArrayList<String>();
					for (String linha : extracaoUtils.obterLinhas(url.getFile())) {
//						if (linha.replaceAll("\\$\\{entity\\.name\\}", entityName)) {
//							
//						}
					}
					File dao = new File(classDir, entityName + ".java");
				}
			}
			
		}
		
	}
	
	private static String convertToPath(String... strings) {
		String string = StringUtils.join(strings, File.separator).replaceAll("\\.", File.separator);
		if (string.endsWith(File.separator)) {
			string = string.substring(0, string.length() - 1);
		}
		return string;
	}
	
	private static String convertToPackage(String... strings) {
		String string = StringUtils.join(strings, ".").replaceAll(File.separator, ".");
		if (string.endsWith(".")) {
			string = string.substring(0, string.length() - 1);
		}
		return string;
	}

	private static void findEntities(PropConfig propConfig, Map<String, String> mapEntities, File modelBaseDir) throws ApplicationException {
		for (File file : modelBaseDir.listFiles()) {
			if (file.isDirectory()) {
				findEntities(propConfig, mapEntities, file);
			} else {
				ExtracaoUtils extracaoUtils = new ExtracaoUtils(propConfig);
				boolean isValidEntity = false;
				for (String linha : extracaoUtils.obterLinhas(file.getAbsolutePath())) {
					if (linha.indexOf("@Entity") >= 0) {
						isValidEntity = true;
					} else if (linha.indexOf("@DiscriminatorValue") >= 0) {
						isValidEntity = false;
					}
				}
				
				if (isValidEntity) {
					mapEntities.put(file.getAbsolutePath(), "");
				}
			}
		}
	}
}
