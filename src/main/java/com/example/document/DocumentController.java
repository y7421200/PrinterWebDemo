package com.example.document;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.response.Response;

@RestController
public class DocumentController {
	private DocumentRepository documentrepository;
	
	@Autowired
	public DocumentController(DocumentRepository documentrepository){
		this.documentrepository = documentrepository;
	}
	
	@PostMapping("/document/details")
	public Response UsersDocuments(HttpSession session){
		Response response = new Response();
		Document document = new Document();
		DocumentInfo documentinfo = new DocumentInfo();
        /*UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()  
        	    .getAuthentication()  
        	    .getPrincipal();  */
		String username = (String) session.getAttribute("username");
		List<Document> data = documentrepository.findByUsername(username);
		List<DocumentInfo> datainfo = new ArrayList<DocumentInfo>();
		for(int i = 0;i < data.size();i++){
			document = data.get(i);
			documentinfo.setId(document.getId());
			documentinfo.setFilename(document.getFilename());
			documentinfo.setFiletype(document.getFiletype());
			documentinfo.setStatus(document.getStatus());
			datainfo.add(documentinfo);
		}
		response.setData(datainfo);
		response.setSuccess("true");
		response.setMsg("query successful!");
		return response;
	}
	
	@PostMapping("/document/delete")
	public Response DeleteDocument(@RequestBody IdBean idbean){
		long id = Long.parseLong(idbean.getId());
		Response response = new Response();
		Document document = documentrepository.findOne(id);
		if(document==null){
			response.setSuccess("false");
			response.setMsg("delete failure!");
		}else{
			File file = new File(document.getUrl());
			file.delete();
			documentrepository.delete(id);
			response.setSuccess("true");
			response.setMsg("delete successful!");
		}
		return response;
	}
	
	@PostMapping("/status/paid")
	public Response PaidDocument(@RequestBody IdBean idbean){
		Response response = new Response();
		Document document = documentrepository.findOne(Long.parseLong(idbean.getId()));
		if(document==null){
			response.setSuccess("false");
			response.setMsg("paid failure!");
		}else{
			document.setStatus(0);
			documentrepository.saveAndFlush(document);
			response.setSuccess("true");
			response.setMsg("paid successful!");
		}
		return response;
	}
	@PostMapping("/document/info")
	public Document findDocuments(@RequestParam("id") long id){
		Document document = new Document();
		document = documentrepository.findOne(id);
		return document;
	}
}
