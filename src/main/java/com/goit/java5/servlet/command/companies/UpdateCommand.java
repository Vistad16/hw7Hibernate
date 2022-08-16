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

		new RequestsForCompanies().updateCompanies(Integer.parseInt(id), new Companies(updateCompany_name, updateSpecialization));

		resp.sendRedirect("/hw7Hibernate/companies");
	}
}
