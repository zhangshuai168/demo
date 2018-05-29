package com.geor.cboms.archive.controller;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.geor.cboms.archive.util.ArchiveEncryptTool;
import com.geor.cboms.archive.util.DesJBean;
import com.geor.cboms.core.controller.BaseController;
import com.geor.cboms.core.data.UsrUsers;
import com.geor.utils.JsonUtils;

@RequestMapping("/archiveAms")
@Controller
public class ArchiveAmsController extends BaseController{

	@RequestMapping("/ams")
	public void ams(HttpServletResponse response,String fullOrgCode) throws Exception{
		String url = ArchiveEncryptTool.url+"?param="+this.getArchiveEncryptedParams(fullOrgCode);
		System.out.println("the url is : " + url);
		response.sendRedirect(url);
	}
	
	// 加密
	public String getArchiveEncryptedParams(String fullOrgCode) throws Exception{
		String username = fullOrgCode;
		Map<String,String> param = new HashMap<String,String>();
		param.put("username", username);
		
		String json = JsonUtils.getJsonString4JavaPOJO(param);
		// URL编码
		String encryJson = URLEncoder.encode(json, "UTF-8");
		// 加密
		encryJson = DesJBean.DES_Encode(encryJson,ArchiveEncryptTool.key);
		return encryJson;
	}
}
