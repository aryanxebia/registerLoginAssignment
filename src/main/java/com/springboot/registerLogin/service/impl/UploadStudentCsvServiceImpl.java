package com.springboot.registerLogin.service.impl;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;
import com.springboot.registerLogin.request.StudentCsvDto;
import com.springboot.registerLogin.service.UploadStudentCsvService;

@Service
public class UploadStudentCsvServiceImpl implements UploadStudentCsvService {

	@Override
	public String uplaodStudentInformaiton(MultipartFile file) throws FileNotFoundException {
		Map<String, String> mapping = new
                HashMap<String, String>();
		mapping.put("id", "id");
        mapping.put("name", "name");
        mapping.put("age", "age");
        
		
		HeaderColumnNameTranslateMappingStrategy<StudentCsvDto> strategy = new HeaderColumnNameTranslateMappingStrategy<StudentCsvDto>();
		strategy.setType(StudentCsvDto.class);
		strategy.setColumnMapping(mapping);
		CSVReader csvReader = null;
		csvReader = new CSVReader(new FileReader(file.getOriginalFilename()));
		CsvToBean csvToBean = new CsvToBean();
		List<StudentCsvDto> studentCsvDtoList = csvToBean.parse(strategy, csvReader);
		for(StudentCsvDto studentCsvDto : studentCsvDtoList) {
			System.out.println(" studentCsvDto :"+studentCsvDto);
		}
		return "";
	}

}
