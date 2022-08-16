package com.goit.java5.data.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Skills {
	private int id;
	private String programming_language;
	private String skill_level;

	public Skills(String programming_language, String skill_level){
		this.programming_language = programming_language;
		this.skill_level = skill_level;
	}
}
