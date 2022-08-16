package com.goit.java5.servlet.command.customers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import com.goit.java5.data.entity.Customers;
import com.goit.java5.data.queries.RequestsForCustomers;
import com.goit.java5.servlet.command.Command;

public class UpdateCustomersCommand implements Command {
	@Override
	public void process(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {
		Customers customers = new Customers();
		customers.setId(Integer.parseInt(req.getParameter("updateId")));
		customers.setName(req.getParameter("updateName"));
		customers.setCountry(req.getParameter("updateCountry"));

		new RequestsForCustomers().updateCustomers(customers);

		resp.sendRedirect("/hw7Hibernate/customers");
	}
}
