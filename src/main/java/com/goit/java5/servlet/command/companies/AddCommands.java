package com.goit.java5.servlet.command.companies;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import com.goit.java5.data.queries.RequestsForCompanies;
import com.goit.java5.servlet.command.Command;

public class AddCommands implements Command {
	@Override
	public void process(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {
		String company_name = req.getParameter("company_name");
		String specialization = req.getParameter("specialization");

		new RequestsForCompanies().createCompanies(company_name, specialization);

		resp.sendRedirect("/hw7Hibernate/companies");
	}
}
