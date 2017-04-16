package com.example.authentification;

import javax.servlet.http.HttpSession;

//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.response.Response;

@RestController
public class UserInfoController {
	//private boolean haslogedin;
	@PostMapping("/user/info")
	public Response getUserInfo(HttpSession session){
		Response response = new Response();
		response.setSuccess("false");
		//haslogedin = SecurityContextHolder.getContext().getAuthentication().isAuthenticated();
		if(session.getAttribute("username")!=null){
			response.setSuccess("true");
		}else{
			response.setSuccess("false");
		}
		return response;
	}
}
