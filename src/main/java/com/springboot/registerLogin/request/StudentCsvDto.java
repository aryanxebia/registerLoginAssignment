package com.springboot.registerLogin.request;

import com.opencsv.bean.CsvBindByPosition;

public class StudentCsvDto {

	@CsvBindByPosition(position = 0)
	String id;
	@CsvBindByPosition(position = 1)
	String name;
	@CsvBindByPosition(position = 2)
	String age;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "StudentCsvDto [id=" + id + ", name=" + name + ", age=" + age + "]";
	}

}
