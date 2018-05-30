package com.ssj.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ssj.commons.utils.FastDFSUtils;
import com.ssj.commons.utils.JsonUtils;

/*
 * 将图片上传到fastDFS服务器
 */
@Controller
public class PictuareController {
	
	//注入图片服务器Ip
	@Value("${fastdfsserver}")
	private String imageServer;

	@RequestMapping("/pic/upload")
	@ResponseBody
	public String FileUpload(MultipartFile uploadFile) {
		try {		
	FastDFSUtils fastDFSUtils =	new FastDFSUtils("classpath:conf/fastdfs.conf");
	String uploadFileName =  uploadFile.getOriginalFilename();
	String extName = uploadFileName.substring(uploadFileName.lastIndexOf(".")+1);
	String url = fastDFSUtils.uploadFile(uploadFile.getBytes(), extName);
     url = imageServer + url;
     Map resultMap = new HashMap<>();
     resultMap.put("error", 0);
     resultMap.put("url", url);
     return JsonUtils.objectToJson(resultMap);
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			Map resultMap = new HashMap<>();
		     resultMap.put("error", 1);
		     resultMap.put("message", "图片上传失败");
		     return JsonUtils.objectToJson(resultMap);
		}
	}
}
