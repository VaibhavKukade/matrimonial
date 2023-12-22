package com.app.matrimonial.service;


import com.app.matrimonial.BaseResponse.BaseResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public interface FileAttachmentService {

	BaseResponse fileAttachment(MultipartFile file) throws IOException;

	BaseResponse getFile(String fileId);

//    /BaseResponse uploadImages(MultipartFile file) throws IOException;


	byte[] downloadImage(String filename) throws IOException;
}
