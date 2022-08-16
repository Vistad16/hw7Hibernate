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
@Table (name = "customers")
public class Customers {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int id;

	@Column (name = "name")
	private String name;

	@Column (name = "country")
	private String country;

	public Customers(String name, String country) {
		this.name = name;
		this.country = country;
	}
}
