package com.goit.java5.servlet.command.projects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

import com.goit.java5.data.entity.Projects;
import com.goit.java5.data.queries.RequestsForProjects;
import com.goit.java5.servlet.command.Command;

public class AddProjectsCommand implements Command {
	@Override
	public void process(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {
		Projects projects = new Projects();
		projects.setProjects_name(req.getParameter("projects_name"));
		projects.setCost(Integer.parseInt(req.getParameter("cost")));
		projects.setCreation_Date(LocalDate.parse(req.getParameter("creation_Date")));
		projects.setDescription(req.getParameter("description"));

		new RequestsForProjects().createProjects(projects);

		resp.sendRedirect("/hw7Hibernate/projects");
	}
}
