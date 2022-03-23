package com.util.toString;

import java.io.File;
import java.util.HashMap;

public class projectsInfo {
	
	String projects[] = {"gngis","scgis","dhgis","ssgis","sunchang","yygis","gcgis","yggis","gsgis","isgis", "khnp"};
	String jars[] = {"DatabaseBuilder.jar","DbfBuilder.jar","DbfExport.jar","ShapeExport.jar"};
	
	
	
	public static HashMap<String, String> filterStr(String projectName){
		String databaseBuildStr = "\"./jdk1.8.0_192" + File.separator + "bin" + File.separator  + "java\" -jar -Dfile.encoding=UTF-8 DatabaseBuilder.jar ";
		String shapeExportStr = "\"./jdk1.8.0_192" + File.separator + "bin" + File.separator  + "java\" -jar ShapeExport.jar ";
		String dbfBuilderStr = "\"./jdk1.8.0_192" + File.separator + "bin" + File.separator  + "java\" -jar DbfBuilder.jar ";
		String dbfExportStr = "\"./jdk1.8.0_192" + File.separator + "bin" + File.separator  + "java\" -jar DbfExport.jar ";
		
//		String databaseBuildStr = "java -jar -Dfile.encoding=UTF-8 DatabaseBuilder.jar ";
//		String shapeExportStr = "java -jar ShapeExport.jar ";
//		String dbfBuilderStr = "java -jar DbfBuilder.jar ";
//		String dbfExportStr = "java -jar DbfExport.jar ";
		
		HashMap<String, String> filterStr = new HashMap<>();
		
		filterStr.put("databaseBuildStr", databaseBuildStr);
		filterStr.put("shapeExportStr", shapeExportStr);
		filterStr.put("dbfBuilderStr", dbfBuilderStr);
		filterStr.put("dbfExportStr", dbfExportStr);
		
		switch (projectName) {
		case "gngis":
			// 강릉시
			filterStr.put("OperData", "106.3.1.55 8991 " + projectName);
			filterStr.put("OperDbf", "Kairos 106.3.1.55 5000 gngis gngis gngis ");
			filterStr.put("DevData", "192.168.0.21 8992 " + projectName);
			filterStr.put("DevDbf", "kairos 192.168.0.23 5000 gngis gngis gngis ");
			filterStr.put("projection", " EPSG:5187 C");
			break;

		case "scgis":
			// 속초시
			filterStr.put("OperData", "106.6.10.45 8991 " + projectName);
			filterStr.put("OperDbf", "kairos 106.6.10.45 5000 scgis scgis scgis ");
			filterStr.put("DevData", "192.168.0.21 8993 " + projectName);
			filterStr.put("DevDbf", "kairos 192.168.0.25 5000 scgis scgis scgis ");
			filterStr.put("projection", " EPSG:5187 C");
			break;

		case "dhgis":
			// 동해시
			filterStr.put("OperData", "106.4.1.42 8991 " + projectName);
			filterStr.put("OperDbf", "Kairos 106.4.1.42 5000 dhgis dhgis dhgis ");
			filterStr.put("DevData", "192.168.0.21 8994 " + projectName);
			filterStr.put("DevDbf", "kairos 192.168.0.24 5000 dhgis dhgis dhgis ");
			filterStr.put("projection", " EPSG:5187 C");
			break;
		case "ssgis":
			// 서산시
			filterStr.put("OperData", "localhost 8991 " + projectName);
			//filterStr.put("OperDbf", "oracle 108.5.2.5 1521 ");
			filterStr.put("DevData", "192.168.0.21 8997 " + projectName);
			filterStr.put("DevDbf", "oracle 192.168.0.22 1521 ORCL ssgis ssgis ");
			filterStr.put("projection", " EPSG:5186 C");
			
			break;
		case "sunchang":
			// 순창군
			filterStr.put("OperData", "localhost 8985 " + projectName);
			filterStr.put("OperDbf", "postgresql localhost 5432 sunchang postgres gds0425! ");
			filterStr.put("DevData", "192.168.0.21 8985 " + projectName);
			filterStr.put("DevDbf", "postgresql 192.168.0.22 5432 sunchang postgres gds0425! ");
			filterStr.put("projection", " EPSG:5186 C");
			break;
		case "yygis":
			// 양양군
			filterStr.put("OperData", "localhost 8995 " + projectName);
			filterStr.put("OperDbf", "postgresql localhost 5432 yygis postgres admin!23 ");
			filterStr.put("DevData", "192.168.0.21 8995 " + projectName);
			filterStr.put("DevDbf", "postgresql 192.168.0.22 5432 yygis postgres gds0425! ");
			filterStr.put("projection", " EPSG:5187 C");
			break;
		case "gcgis":
			// 과천시
			// filterStr.put("OperData", "" + projectName);
			// filterStr.put("OperDbf", "");
			// filterStr.put("DevData", "" + projectName);
			// filterStr.put("DevDbf", "");
			filterStr.put("projection", " EPSG:5186 C");
			break;
		case "yggis":
			// 양구군
			// filterStr.put("OperData", "" + projectName);
			// filterStr.put("OperDbf", "");
			filterStr.put("DevData", "localhost 8996 yggis " + projectName);
			// filterStr.put("DevDbf", "");
			filterStr.put("projection", " EPSG:5186 C");
			break;
		case "gsgis":
			// 고성군
			filterStr.put("OperData", "106.17.10.45 8985 " + projectName);
			filterStr.put("OperDbf", "postgresql 106.17.10.45 5432 gsgis postgres gds0425! ");
			filterStr.put("DevData", "192.168.0.21 8956 " + projectName);
			filterStr.put("DevDbf", "postgresql 192.168.0.22 5432 gsgis postgres gds0425! ");
			filterStr.put("projection", " EPSG:5187 C");
			break;
		case "isgis":
			//임실군
			filterStr.put("DevData", "192.168.0.202 8959 " + projectName);
			filterStr.put("DevDbf", "postgresql 192.168.0.22 5432 isgis postgres gds0425! ");
			filterStr.put("OperData", "109.11.2.61 9089 " + projectName);
			filterStr.put("OperDbf", "postgresql 109.11.2.66 5432 isgis isgis isgis");
			filterStr.put("projection", " EPSG:5186 C");
			break;
		case "khnp":
			// 한수원
			filterStr.put("DevData", "192.168.0.21 8971 " + projectName + "GIS");
			filterStr.put("DevDbf", "postgresql 192.168.0.22 5432 khnp postgres gds0425! ");
			filterStr.put("OperData", "10.145.61.106 8971 " + projectName + "GIS");
			filterStr.put("OperDbf", "postgresql 10.145.61.106 15463 khnp postgres gds0425! ");
			filterStr.put("projection", " EPSG:5186 C");
		default:
			break;
		}			
		return filterStr;
	}
	
	public static void exceptionMsg(boolean projectsChk, boolean folderChk, boolean divisionChk){
		
		Exception e = new Exception();
		
		projectsInfo pif = new projectsInfo();
		String[] projects = pif.projects;
		
		int cnt = 1;
		
		System.out.println();
		System.out.println();
		System.out.println("[erorrrrrrrrrrr] 커맨드 잘못됐음 \n");
		System.out.println();
		
		if(!projectsChk){
			System.out.println("[erorrrrrrrrrrr]"+ cnt +". 프로젝트 이름 확인 \n");
			cnt++;
		}
		
		if(!divisionChk){
			System.out.println("[erorrrrrrrrrrr]"+ cnt +". dev or oper 구분 필요 \n");
			cnt++;
		}
		
		if(!folderChk){
			System.out.println("[erorrrrrrrrrrr]"+ cnt + ". shp, dbf 폴더 둘 다 없음 \n\n");
			cnt++;
		}
		System.out.println("[command guide] \n");
		System.out.println("java -jar bbbb.jar 'project 이름' 'server 구분'\n\n");
		System.out.println("---------------------------projectNameList");
		for (int i = 0; i < projects.length; i++) {
			System.out.println(projects[i]);
		}
		System.out.println("---------------------------/projectNameList \n\n");
		
		System.out.println("---------------------------Server");
		System.out.println("dev");
		System.out.println("oper");
		System.out.println("---------------------------/Server \n\n");
		
		e.printStackTrace();
		
	}
}
