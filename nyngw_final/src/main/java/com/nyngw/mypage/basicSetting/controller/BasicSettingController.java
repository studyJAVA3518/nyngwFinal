package com.nyngw.mypage.basicSetting.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;



@Controller
@RequestMapping("mypage/basicSetting")
public class BasicSettingController {
//	@Autowired
//	private BasicSettingServiceImpl basicSettingServiceImpl; 
	
	
	
	/**
	 * 자신의 서명을 등록하는 폼의 url을 반환하는 메서드
	 * @return 서명등록하는 페이지를 보여줄 url
	 */
	@RequestMapping("sign")
	public String signInsertForm(){
		System.out.println("사인 들어옴");
		
		return "mypage/basicSetting/sign";
	}
	
	/**
	 * 정보를 전부 입력한뒤 등록 버튼을 눌러 화면전환 url을 반환하는 메서드
	 * @return 등록 url 반환
	 */
//	@RequestMapping("fnUpload")
//	public String signInsert(){
//		
//		return null;
//	}
	
	/**
	 * 자신의 서명을 재등록 하기 위해 화면을 전환하는 메서드
	 * @return	서명 재등록하는 페이지를 보여줄 url
	 */
//	@RequestMapping("")
//	public String signUpdateFrom(){
//		
//		return null;
//	}
	
	/**
	 * 정보를 입력한뒤 수정 버튼을 통해 화면전환 하는 url을 반환하는 메서드
	 * @return	수정 url 반환
	 */
//	@RequestMapping("")
//	public String signUpdate(){
//		
//		return null;
//	}
	
	/**
	 * 파일업로드 등록버튼
	 * @return 파일 업로드
	 */
	static final Logger LOG = LoggerFactory.getLogger(BasicSettingController.class);

	/**
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonGenerationException
	 */
	@RequestMapping("dragUpload")
	public @ResponseBody String uploadMultipleFileHandler(@RequestParam("file") MultipartFile[] files, @RequestParam("fileNames") String[] fileNames, HttpServletRequest request, HttpServletResponse reponse) throws JsonGenerationException, JsonMappingException, IOException {
		System.out.println("마하반야바라2");
		String fileName = "";
		String uploadDir = "C:\\test";
		BufferedOutputStream outputStream = null;
		ObjectMapper objectMapper = new ObjectMapper();
		HashMap<String, Object> resultItemList = new HashMap<String, Object>();
		
		/**
		 * [참고] 
		 * Client에서 http header에 UserDefine 속성명을 추가해서 전달할때 받는 방법
		 * (예) xhr.setRequestHeader("X-File-Name", encodeURIComponent('요청관리3.jpg'));
		 * 
		 *  서버단에서는 다음과 같이 받아서 처리한다.
		 *  -------------------------------------------------------
		 *  String fileName = request.getHeader("X-File-Name");
		 *  fileName = URLDecoder.decode(fileName, "UTF-8");
		 *  -------------------------------------------------------
		 */
		
		int fileCount = files.length;
		
		resultItemList.put("count", fileCount);
		for (int inx = 0; inx < fileCount; inx++) {
			HashMap<String, String> resultItem = new HashMap<String, String>();
			MultipartFile file = files[inx];
			try {
				byte[] bytes = file.getBytes();

				File dir = new File(uploadDir);
				if (!dir.exists()) {
					dir.mkdirs();
				}

				int jnx = 1;
				/**
				 * 업로드된 첨부파일명을 읽어들일 때 'file.getOriginalFilename()' 대신(한글이 깨짐!) 
				 * fileNames[inx] 에 저장된 파일명(한글깨짐을 예방하기 위해 인코딩되어있음)을 사용한다.
				 */
				fileName = URLDecoder.decode(fileNames[inx], "UTF-8");
				File serverFile = new File(dir.getAbsolutePath() + File.separator + fileName);
				System.out.println("fileNames[" + inx + "] : " + fileNames[inx]);
				/**
				 *  dir.getAbsolutePath()      => C:\\appdev8\\workspace\\html\\web\\upload
				 *  File.separator             => \\ (사실은 역슬레쉬 한개)
				 *  file.getOriginalFilename() => 파일명
				 */
				while (serverFile.exists()) {
					serverFile = new File(dir.getAbsolutePath() + File.separator + FilenameUtils.getBaseName(fileName) + "[" + jnx + "]." + FilenameUtils.getExtension(fileName));
					jnx++;
				}

				outputStream = new BufferedOutputStream(new FileOutputStream(serverFile));
				outputStream.write(bytes);

				resultItem.put("result", "true");
				resultItem.put("filename", URLEncoder.encode(serverFile.getName(),"UTF-8")); //encoding해서 Client에 전달해야 한글이 않깨진다.
				resultItemList.put(inx + "", resultItem);

			} catch (Exception e) {
				resultItem.put("result", "false");
				resultItem.put("error", e.getMessage());
				resultItemList.put(inx + "", resultItem);
			} finally {
				try {
					if (outputStream != null) {
						outputStream.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return objectMapper.writeValueAsString(resultItemList);
	}
}
