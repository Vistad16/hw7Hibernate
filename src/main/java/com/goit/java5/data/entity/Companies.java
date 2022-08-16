package com.goit.java5.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table (name = "companies")
public class Companies {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	int id;

	@Column (name = "company_name")
	String company_name;

	@Column (name = "specialization")
	String specialization;

	public Companies(String company_name, String specialization) {
		this.company_name = company_name;
		this.specialization = specialization;
	}
}
