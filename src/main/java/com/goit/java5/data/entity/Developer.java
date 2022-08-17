package com.goit.java5.data.entity;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table (name = "developer")
public class Developer {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int id;

	@Column (name = "company_id")
	private int company_id;

	@Column (name = "name")
	private String name;

	@Column (name = "age")
	private int age;

	@Column (name = "sex")
	@Enumerated (EnumType.STRING)
	private Sex sex;

	@Column (name = "salary")
	private int salary;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "developers_skills",
			joinColumns = { @JoinColumn(name = "developers_id") },
			inverseJoinColumns = { @JoinColumn(name = "skill_id") }
	)
	private Set<Skills> skills;

	@ManyToMany (fetch = FetchType.EAGER)
	@JoinTable (
			name = "developer_project",
			joinColumns = { @JoinColumn(name = "developer_id") },
			inverseJoinColumns = { @JoinColumn (name = "project_id") }
	)
	private Set<Projects> projects;


	public Developer(int company_id, String name, int age, Sex sex, int salary) {
		this.company_id = company_id;
		this.name = name;
		this.age = age;
		this.sex = sex;
		this.salary = salary;
	}

	public enum Sex {
		MALE,
		FEMALE,
		UNKNOWN
	}
}
