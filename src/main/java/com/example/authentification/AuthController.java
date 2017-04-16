package com.example.authentification;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.response.Response;




@RestController
public class AuthController {
	
	@Autowired
	private UserRepository userrepository;
	//@RequestMapping(value="/user/login", method=RequestMethod.GET)
	/*public String showLoginPage(Model model){
		User user=new User();
		model.addAttribute("user",user);
		return "login";
	}*/
	@PostMapping("/user/login")
	public Response login(HttpSession session, @RequestBody User user){
		Response response = new Response();
		User repouser = userrepository.findOne(user.getUsername());
		if (repouser!=null){
			response.setSuccess("true");
			response.setMsg("登录成功");
			session.setAttribute("username", user.getUsername());
		}else{
			response.setMsg("登录错误");
			response.setSuccess("false");
		}
		return response;
	}
	
	/*@RequestMapping(value="/user/register", method=RequestMethod.GET)
	public String showRegisterPage(Model model){
		User user=new User();
		model.addAttribute("user",user);
		return "redirect:/user/register";
	}*/
	@PostMapping("/user/logout")
	public Response logout(HttpSession session){
		Response response = new Response();
		session.removeAttribute("username");
		response.setSuccess("true");
		response.setMsg("登出成功");
		return response;
	}
	@RequestMapping(value = "/user/register", method = RequestMethod.POST)
	public Response register(@RequestBody User user){
		User repouser = userrepository.findOne(user.getUsername());
		Response response = new Response();
		if (repouser!=null){
			response.setSuccess("false");
			response.setMsg("用户名已存在");
		}else{
			userrepository.save(user);
			response.setMsg("注册成功");
			response.setSuccess("true");
		}
		return response;
	}
}
