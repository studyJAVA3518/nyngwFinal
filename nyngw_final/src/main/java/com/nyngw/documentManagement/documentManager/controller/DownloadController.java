package com.nyngw.documentManagement.documentManager.controller;

import java.io.File;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

/**
 * jsp단에서 <a href="#"> # = "/documentManagement/documentManager/documentDownload" 이렇게 매핑해주면 이 컨트롤러로 넘어와서 
 * 메서드를 찾음. 
 * @author p25
 *
 */
@RequestMapping("/documentManagement/documentManager")
public class DownloadController implements ApplicationContextAware{
 
    private WebApplicationContext context = null;
     
    @RequestMapping("/documentDownload")
    public ModelAndView download(@RequestParam("fileName")String fileName){ // 가져올 파일이름을 넘겨받음.
         
    	//파일을 가져올 경로를 적어주고 + 가져올 파일 이름을 받아옴. 
        String fullPath = "D:/git/nyngw/nyngw_final/nyngw_final/src/main/webapp/WEB-INF/upload/document/" + fileName;
         
        File file = new File(fullPath);
         
        return new ModelAndView("download", "downloadFile", file);
    }
 
    @Override
    public void setApplicationContext(ApplicationContext arg0)
            throws BeansException {
        // TODO Auto-generated method stub
         
        this.context = (WebApplicationContext)arg0;
         
    }
     
}
