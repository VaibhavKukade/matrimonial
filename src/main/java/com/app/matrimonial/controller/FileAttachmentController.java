package com.app.matrimonial.controller;


import com.app.matrimonial.BaseResponse.BaseResponse;
import com.app.matrimonial.model.FileAttachment;
import com.app.matrimonial.repository.FileAttachmentRepository;
import com.app.matrimonial.service.FileAttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/fileAttachment")
public class FileAttachmentController {

	@Autowired
	private FileAttachmentService fileAttachmentService;

	@Autowired
	FileAttachmentRepository fileAttachmentRepository;



	@PostMapping("/file")
	ResponseEntity<BaseResponse> errorAttachmentFile(@RequestParam("file") MultipartFile file) throws IOException {
		BaseResponse baseResponse = fileAttachmentService.fileAttachment(file);
		return new ResponseEntity<>(baseResponse, HttpStatus.OK);
	}
	
	@GetMapping("/getFile")
	ResponseEntity<BaseResponse> getFile(@RequestParam("fileName") String fileName){
        return new ResponseEntity<>(fileAttachmentService.getFile(fileName), HttpStatus.OK);

	}

//	@PostMapping("/uploadImages")
//	public ResponseEntity<BaseResponse> uploadImage(@RequestParam("file") MultipartFile file) throws IOException{
//		BaseResponse baseResponse = fileAttachmentService.uploadImages(file);
//		return new ResponseEntity<>(baseResponse, HttpStatus.OK);
//	}
//
//	@GetMapping("/getImage")
//	ResponseEntity<?> getImage(@RequestParam("randomId") String randomId) throws IOException {
//		Optional<FileAttachment> fileAttachment = fileAttachmentRepository.findByRandomId(randomId);
//
//		String filePath=fileAttachment.get().getFileLink();
//		byte[] image = Files.readAllBytes(new File(filePath).toPath());
//
//		HttpHeaders httpHeaders = new HttpHeaders();
//
//
//		httpHeaders.set(HttpHeaders.CONTENT_DISPOSITION, "inline");
//		httpHeaders.add(HttpHeaders.CONTENT_TYPE, fileAttachment.get().getFileType());
//
//
//		return  ResponseEntity.status(HttpStatus.OK)
//				.headers(httpHeaders)
//				.body(image);
//
//	}
//
//
//	@GetMapping("/getImageFromDB")
//	ResponseEntity<?> getImageFormDB(@RequestParam("randomId") String randomId) throws IOException {
//		Optional<FileAttachment> fileAttachment = fileAttachmentRepository.findByRandomId(randomId);
//
//		String filePath=fileAttachment.get().getFileLink();
//		byte[] image = Files.readAllBytes(new File(filePath).toPath());
//
//		HttpHeaders httpHeaders = new HttpHeaders();
//
//
//		httpHeaders.set(HttpHeaders.CONTENT_DISPOSITION, "inline");
//		httpHeaders.add(HttpHeaders.CONTENT_TYPE, fileAttachment.get().getFileType());
//
//
//		return  ResponseEntity.status(HttpStatus.OK)
//				.headers(httpHeaders)
//				.body(image);
//
//	}
//

}
