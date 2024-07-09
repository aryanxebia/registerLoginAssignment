package com.springboot.registerLogin.controller;

import java.io.FileNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.registerLogin.service.UploadStudentCsvService;

@RestController
@RequestMapping("/api/v1/")
public class UploadStudentCsvController {

	private UploadStudentCsvService uploadStudentCsvService;

	@Autowired
	public UploadStudentCsvController(UploadStudentCsvService uploadStudentCsvService) {

		this.uploadStudentCsvService = uploadStudentCsvService;
	}

	@PostMapping("upload-student-informtion")
	public ResponseEntity<String> uplaodStudentInformaiton(@RequestParam MultipartFile file)
			throws FileNotFoundException {
		String response = uploadStudentCsvService.uplaodStudentInformaiton(file);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

}
