package com.goit.java5.servlet.command.companies;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import com.goit.java5.data.entity.Companies;
import com.goit.java5.data.queries.RequestsForCompanies;
import com.goit.java5.servlet.command.Command;

public class UpdateCommand implements Command {
	@Override
	public void process(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {
		String id = req.getParameter("id");
		String updateCompany_name = req.getParameter("updateCompany_name");
		String updateSpecialization = req.getParameter("updateSpecialization");

		Companies companies = new Companies();
		companies.setId(Integer.parseInt(id));
		companies.setCompany_name(updateCompany_name);
		companies.setSpecialization(updateSpecialization);

		new RequestsForCompanies().updateCompanies(companies);

		resp.sendRedirect("/hw7Hibernate/companies");
	}
}
