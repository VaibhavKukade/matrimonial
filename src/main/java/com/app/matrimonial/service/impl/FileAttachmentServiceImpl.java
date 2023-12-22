package com.app.matrimonial.service.impl;


import com.app.matrimonial.BaseResponse.BaseResponse;
import com.app.matrimonial.BaseResponse.Errors;
import com.app.matrimonial.BaseResponse.Response;
import com.app.matrimonial.model.FileAttachment;
import com.app.matrimonial.repository.FileAttachmentRepository;
import com.app.matrimonial.service.FileAttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

@Service
public class FileAttachmentServiceImpl implements FileAttachmentService {



	@Autowired
	private FileAttachmentRepository fileAttachmentRepository;


	@Override
	public BaseResponse fileAttachment(MultipartFile file) throws IOException {
		FileAttachment fileAttachment = FileAttachment.builder().data(file.getBytes())
				. fileName(file.getOriginalFilename())
				.fileType(file.getContentType())
				.fileSize(file.getSize()).
				build();

		FileAttachment FileAttachment1 = fileAttachmentRepository.save(fileAttachment);
		Map<String,Integer> responseMap =new HashMap<>();
		responseMap.put("fileId",FileAttachment1.getFileId());
		return BaseResponse.builder()
				.data(responseMap)
				.response(Response.builder().type("SUCCESS")
						.responseCode("200")
						.responseMessage("File upload").build())
				.errors(Errors.builder().build())
				.logID(null)
				.serviceName("FileAttachmentService --- FileAttachment")
				.build();
	}


	@Override
	public BaseResponse getFile(String fileName) {
		 Optional<FileAttachment> files = this.fileAttachmentRepository.findByFileName(fileName);

	        return BaseResponse.builder()
	                .data(files)
	                .response(Response.builder().type("SUCCESS")
	                        .responseCode("200")
	                        .responseMessage(" FileAttachment  file").build())
	                .errors(Errors.builder().build())
	                .logID(null)
	                .serviceName("FileAttachmentService --- GetFile")
	                .build();
	}

//	@Override
//	public BaseResponse uploadImages(MultipartFile file) throws IOException {
//
//		Path dirLocation;
//		dirLocation = Paths.get("Images")
//				.toAbsolutePath()
//				.normalize();
//		String originalFilename = file.getOriginalFilename();
//
//		List<String> inValidExtension = Arrays.asList("exe", "rar", "zip", "dll");
//
//		String fileExtension = com.google.common.io.Files.getFileExtension(file.getOriginalFilename());
//
//		if (inValidExtension.contains(fileExtension)) {
//			throw new CustomExceptionHandler("Invalid File Type extension selected,Kindly change your request file type");
//		}
//
//		String randomId = UUID.randomUUID().toString().concat(".").concat(fileExtension);
//		Path resolvePath = dirLocation.resolve(randomId);
//
//		file.transferTo(resolvePath);
//
//
//		FileAttachment fileAttachment = FileAttachment.builder()
//				.fileName(file.getOriginalFilename())
//				.fileType(file.getContentType())
//				.fileSize(file.getSize())
//				.fileLink(resolvePath.toString())
//				.randomId(randomId)
//				.build();
//		fileAttachmentRepository.save(fileAttachment);
//		return BaseResponse.builder()
//				.data(fileAttachment)
//				.response(Response.builder().type("SUCCESS")
//						.responseCode("200")
//						.responseMessage("Image uploaded successfully").build())
//				.errors(Errors.builder().build())
//				.logID(null)
//				.serviceName("FileAttachmentService --- FileAttachment")
//				.build();
//	}
//
//
//	@PostConstruct
//	public void init() {
//		try {
//			Path dirLocation = Paths.get("Images")
//					.toAbsolutePath()
//					.normalize();
//
//			Files.createDirectories(dirLocation);
//
//		} catch (Exception ex) {
//			System.out.println("Error to Create directory ");
//		}
//
//
//	}

	public  byte[] downloadImage(String fileName) throws IOException {
		Optional<FileAttachment> fileAttachment = fileAttachmentRepository.findByFileName(fileName);

		String filePath=fileAttachment.get().getFileLink();
		byte[] image = Files.readAllBytes(new File(filePath).toPath());
		return  image;
	}
}
