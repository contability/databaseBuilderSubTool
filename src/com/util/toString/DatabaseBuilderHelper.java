package com.util.toString;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DatabaseBuilderHelper {
	public static void main(String[] args) {
		
		File filePath = new File("");
		String path = filePath.getAbsolutePath();			//현재 jar가 들어있는 파일 경로 읽어 옴
		
		System.out.println(path);

		boolean allChk = chkCommand(args, path);
		
		if (allChk) {
			List<String> shpFileList = shpFileList(path);

			List<String> dbfFileList = allFileList(path); // dbf파일이라 생각하고 돌림
			String buildStr = toString(args[0], args[1], path, shpFileList, dbfFileList); // String으로 변환
			
			exportFile(path, buildStr);
		}
		
	} // main method end
	
	
	
	// 파일 생성 메소드
	protected static void exportFile(String path, String buildStr){
		
		File makeDirectory = new File(path + "\\..\\export");
		
		if(!makeDirectory.exists()){	// 경로에 폴더가 없다면
			makeDirectory.mkdir();		// 만들어준다.
		}
		
		File buildFile = new File(path+"\\Build.bat");
		
		byte[] bytes = buildStr.getBytes();
		
		try {
//			FileOutputStream fos = new FileOutputStream(buildFile);
//			
//			fos.write(bytes);
//			fos.close();
			
//			batch 파일 생성하는 부분인데 위에처럼 해도 되고 밑에 처럼 해도 된다
			
			FileOutputStream fos = new FileOutputStream(buildFile);
			OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF8");
			BufferedWriter bw = new BufferedWriter(osw);
			
			
			bw.write(buildStr);
			bw.close();
			
			
		} catch (IOException e) {
			System.out.println("파일 생성 실패");
			System.out.println();
			e.printStackTrace();
		}
	}
	
	
	//파일 목록 불러오기(.shp만)
	protected static List<String> shpFileList(String path){
		
		File directoryPath = new File(path + "\\..\\shp");				// 디렉토리 지정
		
		List<String> chkFileList = new ArrayList<>();
		
		if(directoryPath.exists()){
			String shape = ".shp";

			String fileList[] = directoryPath.list(); // 디렉토리 안의 파일 이름들을 문자열 형태로
														// return

			if (fileList.length > 0) {
				for (int i = 0; i < fileList.length; i++) {

					if (fileList[i].endsWith(shape)) { // .shp으로 끝난다면
						String fileName = fileList[i]; // 그 파일을
						chkFileList.add(fileName); // 리스트에 넣는다

					} else if (fileList[i].endsWith(shape.toUpperCase())) { // .SHP으로
																			// 끝난다면

						String fileName = fileList[i]; // 그 파일을
						chkFileList.add(fileName); // 리스트에 넣는다
					}
				}
			}

		}
		
		return chkFileList;
	}	// shpFileList end
	
	//파일 목록 불러오기(.dbf)			로직은 파일 전체 목록 불러오기로 만들었음(연습)
	protected static List<String> allFileList(String path){
		
		File directoryPath = new File(path + "\\..\\dbf");				// 디렉토리 지정
		List<String> chkFileList = new ArrayList<>();
		
		if(directoryPath.exists()){
			File[] fileList = directoryPath.listFiles();		// 디렉토리 안의 파일들을 파일 배열로 return (위와는 다름)
			
			
			if(fileList.length > 0){
				for(int i = 0; i < fileList.length; i++){
					String fileName = fileList[i].getName();
					chkFileList.add(fileName);
					//System.out.println(fileName);				// 파일 이름만 나옴
					//System.out.println(fileList[i]);			// .getName(); 안하면 경로까지 다 나옴.		근데 Type이 File이라 String으로 못받음
				}
			}
		}
		
		return chkFileList;
	}	// allFileList end
	
	
	//문자열로 만들기
	protected static String toString(String projectName, String division, String path, List<String> shpFileList, List<String> dbfFileList){
		
		HashMap<String, String> filterStr = projectsInfo.filterStr(projectName);
		
		String shpBuildStr = "";
		String shpExportStr = "";
		String dbfBuildStr = "";
		String dbfExportStr = "";
		
		for(String list : shpFileList){
			
			String filteredList = "";
			
			if(list.contains(".shp")){
				filteredList = list.replace(".shp", "");
			}else if(list.contains(".SHP")){
				filteredList = list.replace(".SHP", "");
			}
			
			if(division.equals("dev")){
				shpExportStr += filterStr.get("shapeExportStr") + filterStr.get("DevData") + " " + filteredList + " " + path + "\\..\\export\\" + "\r\n";
				shpBuildStr += filterStr.get("databaseBuildStr") + filterStr.get("DevData")+ " " + path + "\\..\\shp\\" + list + filterStr.get("projection") + "\r\n";
			}else{
				if(filterStr.get("OperData") != null && filterStr.get("OperData") != ""){
					shpExportStr += filterStr.get("shapeExportStr") + filterStr.get("OperData") + " " + filteredList + " " + path + "\\..\\export\\" + "\r\n";
					shpBuildStr += filterStr.get("databaseBuildStr") + filterStr.get("OperData")+ " " + path + "\\..\\shp\\" + list + filterStr.get("projection") + "\r\n";
				}else{
					shpExportStr = projectName + " 운영서버 정보 없음 \r\n\r\n";
				}
			}
		}
		
		for(String list : dbfFileList){
			String filteredList = "";
			
			if(list.contains(".dbf")){
				filteredList = list.replace(".dbf", "");
			}else{
				filteredList = list.replace(".DBF", "");
			}
			
			if(division.equals("dev")){
				dbfExportStr += filterStr.get("dbfExportStr") + filterStr.get("DevDbf") + filteredList + " " + path + "\\..\\export" + "\r\n";
				dbfBuildStr += filterStr.get("dbfBuilderStr") + filterStr.get("DevDbf")+ path + "\\..\\dbf\\" +  list + "\r\n";
			}else{
				if(filterStr.get("OperDbf") != null && filterStr.get("OperDbf") != ""){
					dbfExportStr += filterStr.get("dbfExportStr") + filterStr.get("OperDbf") + filteredList + " " + path + "\\..\\export" + "\r\n";
					dbfBuildStr +=  filterStr.get("dbfBuilderStr") + filterStr.get("OperDbf")+ path + "\\..\\dbf\\" +  list + "\r\n";
				}else{
					dbfExportStr = projectName + " 운영서버 정보 없음 \r\n\r\n";
				}
			}
		}
		
		String buildStr = ""; 
		
		if(shpFileList.size() > 0){
			buildStr += "::---------------------------------------------------------" + division + "_shapeExport \r\n\r\n";
			buildStr += shpExportStr;
			buildStr += "\r\n\r\n\r\n";
			buildStr += "::---------------------------------------------------------" + division + "_databaseBuilder \r\n\r\n";
			buildStr += shpBuildStr;
			buildStr += "\r\n\r\n\r\n";
		}
		
		if(dbfFileList.size() > 0){
			buildStr += "::---------------------------------------------------------" + division + "_dbfExport \r\n\r\n";
			buildStr += dbfExportStr;
			buildStr += "\r\n\r\n\r\n";
			buildStr += "::---------------------------------------------------------" + division + "_dbfBuilder \r\n\r\n";
			buildStr += dbfBuildStr;
			buildStr += "\r\n\r\n\r\n";
		}
		
		return buildStr;
	}	// toString end
	
	
	// 명령어 제대로 안넣으면 안내 띄우고 exception 내서 멈춰버릴거임
	protected static boolean chkCommand(String[] args, String path){	// chkCommand
		
		projectsInfo pif = new projectsInfo();
		String[] projects = pif.projects;
		boolean allChk = false;
		boolean projectsChk = false;
		boolean folderChk = true;
		boolean divisionChk = false;
		
		if(args != null && args.length > 0){	// 뭔가 파라미터가 들어왔다면
			
			for (int i = 0; i < projects.length; i++) {		// 첫 명령어로 지자체 명이 제대로 들어갔는지 확인
				if (args[0].equals(projects[i])) {
					projectsChk = true;
				}
			}
			
			//현재 jar파일이 있는 디렉토리 기준으로 바로 위 부모 디렉토리에 shp, dbf 디렉토리가 있는지?
			File shpDirectory = new File(path + "\\..\\shp");
			File dbfDirectory = new File(path + "\\..\\dbf");
			
			if(!shpDirectory.exists() && !dbfDirectory.exists()){		// shp 폴더와 dbf 폴더가 생성 되어 있는지 확인(둘 중 하나라도 그 안에 파일 넣어둬야 함)
				folderChk = false;
			}
		} 
		
		
		if (args.length > 1) { // 두 번째 파라미터로 뭐라도 들어왔고
			
			if(args[1].equals("dev") || args[1].equals("oper")){	// dev나 oper라면
				divisionChk = true;
			}
		}
		
		//지자체 명이나 서버 구분, 디렉토리 제대로 생성 안되어 있다면 안내문과 함께 exception
		if (!projectsChk || !folderChk || !divisionChk) {
			projectsInfo.exceptionMsg(projectsChk, folderChk, divisionChk);
		}else{
			allChk = true;
		}
		
		return allChk;
	}	// chkCommand end
	
	
	
}	// class end
