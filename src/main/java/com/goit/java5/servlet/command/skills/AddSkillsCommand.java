package com.goit.java5.servlet.command.skills;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import com.goit.java5.data.entity.Skills;
import com.goit.java5.data.queries.RequestsForSkills;
import com.goit.java5.servlet.command.Command;

public class AddSkillsCommand implements Command {
	@Override
	public void process(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {
		Skills skills = new Skills();
		skills.setProgramming_language(req.getParameter("programming_language"));
		skills.setSkill_level(req.getParameter("skill_level"));

		new RequestsForSkills().createSkills(skills);

		resp.sendRedirect("/hw7Hibernate/skills");
	}
}
