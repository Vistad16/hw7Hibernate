package com.goit.java5.servlet.command.developers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import com.goit.java5.data.queries.RequestsForDeveloper;
import com.goit.java5.servlet.command.Command;

public class DeleteDevelopersCommand implements Command {

	@Override
	public void process(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {
		String id = req.getParameter("deleteDevelopers");

		new RequestsForDeveloper().deleteDeveloperById(Integer.parseInt(id));

		resp.sendRedirect("/hw7Hibernate/developers");
	}
}
