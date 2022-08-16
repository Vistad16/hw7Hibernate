package com.goit.java5.servlet.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;


public interface Command {
	void process(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException;
}
