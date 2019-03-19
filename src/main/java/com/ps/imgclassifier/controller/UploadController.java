package com.ps.imgclassifier.controller;

import java.io.IOException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.ps.imgclassifier.model.Image;
import com.ps.imgclassifier.service.UploadService;

@Controller
public class UploadController {

	private UploadService uploadService;

	@Autowired
	public UploadController(UploadService uploadService) {
		this.uploadService = uploadService;
	}

	@GetMapping("/upload")
	public String showUploadForm() {
		return "image-upload";
	}

	@PostMapping("save")
	public String saveToDb(@RequestParam("tensor") String tensor, @RequestParam("file") MultipartFile file)
			throws IOException {
		if (file.getBytes().length > 0) {
			Image img = new Image();
			img.setFileName(file.getOriginalFilename());
			img.setFileType(file.getContentType());
			img.setContent(file.getBytes());
			img.setResultRecognition(tensor);

			uploadService.save(img);

			return "redirect:/list";
		}

		return "redirect:/upload";
	}

	@GetMapping("/list")
	public String showImageGallery(Model theModel) {
		theModel.addAttribute("images", uploadService.findAll().stream()
				.map(img -> new Image(img.getId(), img.getFileName(), img.getResultRecognition(), MvcUriComponentsBuilder
						.fromMethodName(UploadController.class, "getImage", img.getId().toString()).build().toString()))
				.collect(Collectors.toList()));

		return "image-gallery";
	}

	@RequestMapping(value = "/imgFromDb", method = RequestMethod.GET)
	public ResponseEntity<byte[]> getImage(@RequestParam(value = "id") Long id) throws IOException {

		Image img = uploadService.getOne(id);

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + img.getFileName() + "\"")
				.contentType(MediaType.valueOf(img.getFileType())).body(img.getContent());
	}
}
