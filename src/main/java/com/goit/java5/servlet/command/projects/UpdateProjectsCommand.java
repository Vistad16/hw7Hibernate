package com.goit.java5.servlet.command.projects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

import com.goit.java5.data.entity.Projects;
import com.goit.java5.data.queries.RequestsForProjects;
import com.goit.java5.servlet.command.Command;

public class UpdateProjectsCommand implements Command {
	@Override
	public void process(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {
		Projects projects = new Projects();
		projects.setId(Integer.parseInt(req.getParameter("updateId")));
		projects.setProjects_name(req.getParameter("updateProjects_name"));
		projects.setCost(Long.parseLong(req.getParameter("updateCost")));
		projects.setCreation_Date(LocalDate.parse(req.getParameter("updateCreation_Date")));
		projects.setDescription(req.getParameter("updateDescription"));

		new RequestsForProjects().updateProjects(projects);

		resp.sendRedirect("/hw7Hibernate/projects");
	}
}
