package com.goit.java5.data.queries;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.goit.java5.connection.Storage;
import com.goit.java5.data.entity.Projects;

public class RequestsForProjects {
	Storage storage = Storage.getInstance();

	private final PreparedStatement insertProjectsSt;
	private final PreparedStatement selectProjectsSt;
	private final PreparedStatement deleteProjectByIdSt;
	private final PreparedStatement updateProjectSt;
	private final PreparedStatement getAllProjectsST;

	public RequestsForProjects() throws SQLException {
		Connection connection = storage.getConnection();
		insertProjectsSt = connection.prepareStatement(
				"INSERT INTO projects (projects_name, cost, creation_Date, description) VALUES (?, ?, ?, ?)"
		);

		selectProjectsSt = connection.prepareStatement("" +
				"SELECT id, projects_name, cost, creation_Date, description FROM projects WHERE projects_name = ?"
		);

		deleteProjectByIdSt = connection.prepareStatement(
				"DELETE FROM projects WHERE id = ?"
		);
		updateProjectSt = connection.prepareStatement(
				"UPDATE projects SET projects_name = ?, cost = ?, creation_Date = ?, description = ? WHERE id = ?"
		);
		getAllProjectsST = connection.prepareStatement(
				"SELECT id, projects_name, cost, creation_Date, description FROM projects");
	}

	public List<Projects> getAllProjects() {
		try(ResultSet resultSet = getAllProjectsST.executeQuery()) {
			List<Projects> projectsList = new ArrayList<>();
			while (resultSet.next()) {
				Projects projects = new Projects();
				projects.setId(resultSet.getInt("id"));
				projects.setProjects_name(resultSet.getString("projects_name"));
				projects.setCost(resultSet.getLong("cost"));
				projects.setCreation_Date(LocalDate.parse(resultSet.getString("creation_Date")));
				projects.setDescription(resultSet.getString("description"));

				projectsList.add(projects);
			}

			return projectsList;

		} catch (SQLException ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public boolean createProjects(String projects_name, long cost, LocalDate creation_Date, String description) {
		try {
			insertProjectsSt.setString(1, projects_name);
			insertProjectsSt.setLong(2, cost);
			insertProjectsSt.setDate(3, Date.valueOf(creation_Date));
			insertProjectsSt.setString(4, description);
			return insertProjectsSt.executeUpdate() == 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public void createProjectsFromList(List<Projects> listProjects) {
		try {
			for (Projects listProject : listProjects) {
				insertProjectsSt.setString(1, listProject.getProjects_name());
				insertProjectsSt.setLong(2, listProject.getCost());
				insertProjectsSt.setDate(3, Date.valueOf(listProject.getCreation_Date()));
				insertProjectsSt.setString(4, listProject.getDescription());
				insertProjectsSt.addBatch();
			}
			insertProjectsSt.executeBatch();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public Projects selectProjectsByProjectName(String projects_name) {
		try {
			selectProjectsSt.setString(1, projects_name);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		Projects projects = new Projects();
		try (ResultSet resultSet = selectProjectsSt.executeQuery()) {
			resultSet.next();
			projects.setId(resultSet.getInt("id"));
			projects.setProjects_name(resultSet.getString("project_name"));
			projects.setCost(resultSet.getInt("cost"));
			projects.setCreation_Date(LocalDate.parse(resultSet.getString("date_creation")));
			projects.setDescription(resultSet.getString("project_description"));
			return projects;
		} catch (SQLException ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public void deleteProjectsById(int id) throws SQLException {
		deleteProjectByIdSt.setInt(1, id);
		deleteProjectByIdSt.executeUpdate();
	}


	public void updateProjects(int id, Projects projects) {
		try {
			updateProjectSt.setString(1, projects.getProjects_name());
			updateProjectSt.setLong(2, projects.getCost());
			updateProjectSt.setDate(3, Date.valueOf(projects.getCreation_Date()));
			updateProjectSt.setString(4, projects.getDescription());
			updateProjectSt.setInt(5, id);
			updateProjectSt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
