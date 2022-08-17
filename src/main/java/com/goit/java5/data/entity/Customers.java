package com.goit.java5.data.entity;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table (name = "customers")
public class Customers {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int id;

	@Column (name = "name")
	private String name;

	@Column (name = "country")
	private String country;

	@ManyToMany (fetch = FetchType.EAGER)
	@JoinTable (
			name = "customers_projects",
			joinColumns = { @JoinColumn (name = "customer_id") },
			inverseJoinColumns = { @JoinColumn(name = "projects_id") }
	)
	private Set<Projects> projects;

	public Customers(String name, String country) {
		this.name = name;
		this.country = country;
	}
}
