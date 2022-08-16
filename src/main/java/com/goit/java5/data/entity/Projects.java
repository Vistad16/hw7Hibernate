package com.goit.java5.data.entity;

import java.time.LocalDate;

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
@Table (name = "projects")
public class Projects {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int id;

	@Column (name = "projects_name")
	private String projects_name;

	@Column (name = "cost")
	private long cost;

	@Column (name = "creation_Date")
	private LocalDate creation_Date;

	@Column (name = "description")
	private String description;

	public Projects(String projects_name, int cost, LocalDate creation_Date, String description) {
		this.projects_name = projects_name;
		this.cost = cost;
		this.creation_Date = creation_Date;
		this.description = description;
	}
}
