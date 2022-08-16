package com.goit.java5.servlet.command.developers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import com.goit.java5.data.queries.RequestsForDeveloper;
import com.goit.java5.servlet.IndexPageServlet;
import com.goit.java5.servlet.command.Command;
import org.thymeleaf.context.Context;

public class GetDevelopersCommand implements Command {
	@Override
	public void process(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {
		resp.setContentType("text/html");
		Context context = new Context(
				req.getLocale(),
				Map.of("requestsForDevelopers", new RequestsForDeveloper().getAllDevelopers())
		);

		IndexPageServlet.getEngine().process("developers", context, resp.getWriter());
		resp.getWriter().close();
	}
}
