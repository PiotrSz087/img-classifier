package com.ps.imgclassifier.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ps.imgclassifier.model.Image;
import com.ps.imgclassifier.service.UploadService;

@Controller
public class UploadController {
	
	private UploadService uploadService;
	
	@Autowired
	public UploadController(UploadService uploadService) {
		this.uploadService = uploadService;
	}

    @GetMapping("/list")
    public String showUploadForm() {
    	return "image-upload";
    }
    
    @GetMapping("/images")
    public String showImagesGallery() {
    	return "image-gallery";
    }
    
    @PostMapping("save")
    public String saveToDb(@RequestParam("file") MultipartFile file) throws IOException {
    	Image img = new Image();
    	img.setFileName(file.getOriginalFilename());
    	img.setFileDescription("none at the moment");
    	img.setContent(file.getBytes());
    	
    	uploadService.save(img);
    	
    	return "redirect:/list";
    }
    
    @RequestMapping(value = "/imgFromDb", method = RequestMethod.GET)
    public void getImage(@RequestParam(value = "id") Long id, HttpServletResponse response) throws IOException {
    	Image img = uploadService.getOne(id);
    	response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
        response.getOutputStream().write(img.getContent());
        response.getOutputStream().close();
    }
}
