package com.goit.java5.servlet.command.projects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import com.goit.java5.data.queries.RequestsForProjects;
import com.goit.java5.servlet.command.Command;

public class DeleteProjectsCommand implements Command {
	@Override
	public void process(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {
		String id = req.getParameter("deleteProject");
		new RequestsForProjects().deleteProjectsById(Integer.parseInt(id));

		resp.sendRedirect("/hw7Hibernate/projects");
	}
}
