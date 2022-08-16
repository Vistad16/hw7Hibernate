package com.goit.java5.servlet.command.companies;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import com.goit.java5.data.queries.RequestsForCompanies;
import com.goit.java5.servlet.command.Command;

public class DeleteCommands implements Command {
	@Override
	public void process(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {
		String id = req.getParameter("id");

		new RequestsForCompanies().deleteCompaniesById(Integer.parseInt(id));

		resp.sendRedirect("/hw7Hibernate/companies");
	}
}
