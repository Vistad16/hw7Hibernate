package com.goit.java5.data.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Customers {
	private int id;
	private String name;
	private String country;

	public Customers(String name, String country){
		this.name = name;
		this.country = country;
	}
}
