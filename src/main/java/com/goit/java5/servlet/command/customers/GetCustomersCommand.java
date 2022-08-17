package com.goit.java5.servlet.command.customers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import com.goit.java5.data.entity.Developer;
import com.goit.java5.data.queries.RequestsForCustomers;
import com.goit.java5.data.queries.RequestsForDeveloper;
import com.goit.java5.data.queries.RequestsForProjects;
import com.goit.java5.data.queries.RequestsForSkills;
import com.goit.java5.servlet.IndexPageServlet;
import com.goit.java5.servlet.command.Command;
import org.thymeleaf.context.Context;

public class GetCustomersCommand implements Command {
	@Override
	public void process(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {
		resp.setContentType("text/html");

		Context context = new Context(
				req.getLocale(),
				Map.of("requestsForCustomers", new RequestsForCustomers().selectAllCustomers(),
						"requestsForProjects", new RequestsForProjects().listAllProjects()
				)
		);

		IndexPageServlet.getEngine().process("customers", context, resp.getWriter());

		resp.getWriter().close();
	}
}
