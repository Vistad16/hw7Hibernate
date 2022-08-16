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
@Table (name = "skills")
public class Skills {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int id;

	@Column (name = "programming_language")
	private String programming_language;

	@Column (name = "skill_level")
	private String skill_level;

	public Skills(String programming_language, String skill_level) {
		this.programming_language = programming_language;
		this.skill_level = skill_level;
	}
}
