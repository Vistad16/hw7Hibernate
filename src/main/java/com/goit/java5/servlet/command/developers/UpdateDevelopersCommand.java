package com.goit.java5.servlet.command.developers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;

import com.goit.java5.data.entity.Developer;
import com.goit.java5.data.queries.RequestsForDeveloper;
import com.goit.java5.data.queries.RequestsForProjects;
import com.goit.java5.data.queries.RequestsForSkills;
import com.goit.java5.servlet.command.Command;

public class UpdateDevelopersCommand implements Command {
	@Override
	public void process(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {
		Developer developer = new Developer();
		developer.setId(Integer.parseInt(req.getParameter("updateId")));
		developer.setCompany_id(Integer.parseInt(req.getParameter("updateCompany_id")));
		developer.setName(req.getParameter("updateName"));
		developer.setAge(Integer.parseInt(req.getParameter("updateAge")));
		developer.setSex(Developer.Sex.valueOf(req.getParameter("updateSex")));
		developer.setSalary(Integer.parseInt(req.getParameter("updateSalary")));
		developer.setSkills(Collections.singleton(new RequestsForSkills().getSkillsById(Integer.parseInt(req.getParameter("updateSkills")))));
		developer.setProjects(Collections.singleton(new RequestsForProjects().getProjectsById(Integer.parseInt(req.getParameter("updateProjects")))));

		new RequestsForDeveloper().updateDeveloper(developer);

		resp.sendRedirect("/hw7Hibernate/developers");
	}
}
