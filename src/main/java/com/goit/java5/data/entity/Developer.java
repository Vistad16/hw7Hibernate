package com.goit.java5.data.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Developer {
	private int id;
	private int company_id;
	private String name;
	private int age;
	private Sex sex;
	private int salary;

	public Developer(int company_id, String name, int age, Sex sex, int salary){
		this.company_id = company_id;
		this.name = name;
		this.age = age;
		this.sex = sex;
		this.salary = salary;
	}

	public enum Sex{
		MALE,
		FEMALE,
		UNKNOWN
	}
}
