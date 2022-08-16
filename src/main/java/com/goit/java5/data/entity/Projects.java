package com.goit.java5.data.entity;

import java.time.LocalDate;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Projects {
	private int id;
	private String projects_name;
	private long cost;
	private LocalDate creation_Date;
	private String description;

	public Projects(String projects_name, int cost, LocalDate creation_Date, String description){
		this.projects_name = projects_name;
		this.cost = cost;
		this.creation_Date = creation_Date;
		this.description = description;
	}
}
