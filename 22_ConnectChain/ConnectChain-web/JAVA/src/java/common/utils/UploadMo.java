package common.utils;

import java.io.File;
import java.util.Enumeration;
import java.util.Iterator;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import common.collection.ABox;
import common.collection.ABoxList;

@Component
public class UploadMo {
	// service 클래스 내부에서 쓸 함수.
	public static ABoxList<ABox> upload(MultipartHttpServletRequest multipartRequest) throws Exception {

		String userSeq = "";
		String newFileName = "";
		String imagePath = "";

		ABox map = new ABox();		
		ABoxList<ABox> mapList = new ABoxList<ABox>();

		multipartRequest.setCharacterEncoding("utf-8");
		Enumeration<?> enu = multipartRequest.getParameterNames();
		while (enu.hasMoreElements()) {
			String name = (String) enu.nextElement();
			String value = multipartRequest.getParameter(name);
			map.set(name, value);
		}
		userSeq = map.get("userSeq").toString();

		Iterator<String> fileNames = multipartRequest.getFileNames();
		while (fileNames.hasNext()) {
			String fileName = fileNames.next();
			MultipartFile mFile = multipartRequest.getFile(fileName);
			String mFileName = mFile.getName();

			/*
			if (mFile.getSize() > 1048576) {
				map.set("check", "fail");
				mapList.add(map);
				return mapList;
			}
			*/
			String originalFileName = mFile.getOriginalFilename();
			String originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);

			switch (originalFileExtension) {
			case "jpg":
				newFileName = mFileName + "_" + userSeq + "_" + System.currentTimeMillis() + "." + originalFileExtension;
				break;

			case "jpeg":
				newFileName = mFileName + "_" + userSeq + "_" + System.currentTimeMillis() + "." + originalFileExtension;
				break;

			case "png":
				newFileName = mFileName + "_" + userSeq + "_" + System.currentTimeMillis() + "." + originalFileExtension;
				break;

			default:
				map.set("check", "fail");
				mapList.add(map);
				return mapList;
			}
			
			map.set("originalFileName", mFileName);
			
			switch (mFileName) {
			case "goods":
				imagePath = "/usr/local/apache-tomcat-8.5.63/webapps/examples/goods/"+userSeq+"/";
				map.set("goodsFileName", newFileName);
				map.set("goodsImagePath", imagePath+newFileName);
				break;

			case "profile":
				imagePath = "/usr/local/apache-tomcat-8.5.63/webapps/examples/profile/"+userSeq+"/";
				map.set("profileFileName", newFileName);
				break;
				
			default:
				map.set("check", "fail");
				mapList.add(map);
				return mapList;
			}
			
			try {
				File file = new File(imagePath + newFileName);
				if (mFile.getSize() != 0) { // File Null Check
					if (!file.exists()) { // 경로에 파일이 없으면
						if (file.getParentFile().mkdirs()) { // 그 경로에 해당하는 디렉터리를 만든 후
							file.createNewFile(); // 파일을 생성
						}
					}
					mFile.transferTo(new File(imagePath + newFileName)); // 임시로 저장된 multipartFile을 실제 파일로 전송
				}
				map.set("check", "ok");
				mapList.add(map);

			} catch (Exception e) {
				map.set("check", "fail");
				mapList.add(map);
			}
		}
		return mapList;

	}
}