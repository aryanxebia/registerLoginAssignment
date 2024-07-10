package com.springboot.registerLogin.service;

import java.io.FileNotFoundException;

import org.springframework.web.multipart.MultipartFile;

public interface UploadStudentCsvService {

	String uplaodStudentInformaiton(MultipartFile file) throws FileNotFoundException;

}
