package com.example.upload;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.document.Document;
import com.example.document.DocumentRepository;
import com.example.response.Response;

@Controller
public class UploadController {
	
	@Autowired
	private DocumentRepository documentrepository;
    @Value("${web.upload-path}")
    private String path;
	
    private static final Logger logger = LoggerFactory.getLogger(UploadController.class);

    @PostMapping("/action/uploadfile")
    @ResponseBody
    public Response upload(HttpSession session, @RequestParam("file") MultipartFile file, 
    		@RequestParam("amount") int amount, 
    			@RequestParam("type") String type) {
    	Response response = new Response();
    	response.setSuccess("false");
        if (file.isEmpty()) {
        	response.setMsg("文件为空");
        	response.setSuccess("false");
            return response;
        }
        // 获取文件名
        String fileName = file.getOriginalFilename();
        logger.info("上传的文件名为：" + fileName);
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        logger.info("上传的后缀名为：" + suffixName);
        // 文件上传后的路径
        String filePath = path + "/filestorage/";
        String fileNamer = UUID.randomUUID() + suffixName;
        File dest = new File(filePath + fileNamer);
        String username = (String) session.getAttribute("username");
        /*UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()  
        	    .getAuthentication()  
        	    .getPrincipal();  */

        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
            Document document = new Document();
            document.setFilename(fileName);
            document.setUsername(username);
            document.setFiletype(suffixName);
            document.setStatus(-1);
            document.setPrinttype(type);
            document.setAmount(amount);
            document.setUrl(fileNamer);
            documentrepository.save(document);
            response.setSuccess("true");
            response.setMsg("上传成功");
            return response;
        } catch (IllegalStateException e) {
            e.printStackTrace();
            response.setSuccess("false");
            response.setMsg("上传失败");
        } catch (IOException e) {
            e.printStackTrace();
            response.setSuccess("false");
            response.setMsg("上传失败");
        }
        return response;
        
    }
}
