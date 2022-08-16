package com.goit.java5.servlet.command.customers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import com.goit.java5.data.queries.RequestsForCustomers;
import com.goit.java5.servlet.command.Command;

public class DeleteCustomersCommand implements Command {
	@Override
	public void process(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {
		String deleteCustomer = req.getParameter("deleteCustomer");

		new RequestsForCustomers().deleteCustomersById(Integer.parseInt(deleteCustomer));

		resp.sendRedirect("/hw7Hibernate/customers");
	}
}
