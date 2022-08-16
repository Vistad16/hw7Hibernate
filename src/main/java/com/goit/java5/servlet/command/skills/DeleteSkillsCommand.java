package com.goit.java5.servlet.command.skills;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import com.goit.java5.data.queries.RequestsForSkills;
import com.goit.java5.servlet.command.Command;

public class DeleteSkillsCommand implements Command {
	@Override
	public void process(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {
		String deleteSkill = req.getParameter("deleteSkill");

		new RequestsForSkills().deleteSkillsById(Integer.parseInt(deleteSkill));

		resp.sendRedirect("/hw7Hibernate/skills");
	}
}
