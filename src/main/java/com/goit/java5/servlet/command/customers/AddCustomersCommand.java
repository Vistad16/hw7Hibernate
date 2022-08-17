package com.goit.java5.servlet.command.customers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;

import com.goit.java5.data.entity.Customers;
import com.goit.java5.data.queries.RequestsForCustomers;
import com.goit.java5.data.queries.RequestsForProjects;
import com.goit.java5.servlet.command.Command;

public class AddCustomersCommand implements Command {
	@Override
	public void process(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {

		Customers customers = new Customers();
		customers.setName(req.getParameter("name"));
		customers.setCountry(req.getParameter("country"));
		customers.setProjects(Collections.singleton(new RequestsForProjects().getProjectsById(Integer.parseInt(req.getParameter("projects")))));

		new RequestsForCustomers().createCustomers(customers);

		resp.sendRedirect("/hw7Hibernate/customers");
	}
}
