package com.goit.java5.data.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Companies {
	int id;
	String company_name;
	String specialization;

	public Companies(String company_name, String specialization) {
		this.company_name = company_name;
		this.specialization = specialization;
	}
}
