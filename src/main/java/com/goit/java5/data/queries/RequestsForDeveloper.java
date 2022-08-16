package com.goit.java5.data.queries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.goit.java5.connection.Storage;
import com.goit.java5.data.entity.Developer;
import com.goit.java5.data.entity.Skills;

public class RequestsForDeveloper {
	Storage storage = Storage.getInstance();

	private final PreparedStatement insertDevelopers;
	private final PreparedStatement selectDevelopers;
	private final PreparedStatement insertDevelopersSkills;
	private final PreparedStatement insertDeveloperToProjects;
	private final PreparedStatement updateDevelopers;
	private final PreparedStatement deleteByIdSt;
	private final PreparedStatement getAllDevelopersSt;


	public RequestsForDeveloper() throws SQLException {

		Connection connection = storage.getConnection();

		insertDevelopers = connection.prepareStatement(
				"INSERT INTO developer (company_id, name, age, sex, salary) VALUES (?, ?, ?, ?, ?)"
		);

		selectDevelopers = connection.prepareStatement(
				"SELECT company_id, name, age, sex, salary FROM developer WHERE name = ?"
		);

		insertDevelopersSkills = connection.prepareStatement(
				"INSERT INTO developers_skills (developers_id, skill_id) VALUES (?, ?)");

		insertDeveloperToProjects = connection.prepareStatement(
				"INSERT INTO developer_project (developer_id, project_id) VALUES (?, ?)");

		updateDevelopers = connection.prepareStatement(
				"UPDATE developer SET company_id = ?, name = ?, age = ?, sex = ?, salary = ? WHERE id = ?"
		);

		deleteByIdSt = connection.prepareStatement(
				"DELETE FROM developer WHERE id = ?"
		);

		getAllDevelopersSt = connection.prepareStatement(
				"SELECT id, company_id, name, age, sex, salary FROM developer");
	}

	public List<Developer> getAllDevelopers() {
		try (ResultSet resultSet = getAllDevelopersSt.executeQuery()) {
			List<Developer> developerList = new ArrayList<>();
			while (resultSet.next()) {
				Developer developer = new Developer();
				developer.setId(resultSet.getInt("id"));
				developer.setCompany_id(resultSet.getInt("company_id"));
				developer.setName(resultSet.getString("name"));
				developer.setAge(resultSet.getInt("age"));
				developer.setSex(Developer.Sex.valueOf(resultSet.getString("sex")));
				developer.setSalary(resultSet.getInt("salary"));

				developerList.add(developer);
			}
			return developerList;
		} catch (SQLException ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public void updateDevelopersById(Developer developer) {
		Developer findDeveloper = selectDevelopersByName(developer.getName());

		try {
			if (developer.getCompany_id() == 0) {
				updateDevelopers.setInt(1, findDeveloper.getCompany_id());
			} else {
				updateDevelopers.setInt(1, developer.getCompany_id());
			}

			if (developer.getName() == null) {
				updateDevelopers.setString(2, findDeveloper.getName());
			} else {
				updateDevelopers.setString(2, developer.getName());
			}

			if (developer.getAge() == 0) {
				updateDevelopers.setInt(3, findDeveloper.getAge());
			} else {
				updateDevelopers.setInt(3, developer.getAge());
			}

			if (developer.getSex() == null) {
				updateDevelopers.setString(4, findDeveloper.getSex().toString());
			} else {
				updateDevelopers.setString(4, developer.getSex().toString());
			}

			if (developer.getSalary() == 0) {
				updateDevelopers.setInt(5, findDeveloper.getSalary());
			} else {
				updateDevelopers.setInt(5, developer.getSalary());
			}

			if (developer.getId() == 0) {
				updateDevelopers.setInt(6, findDeveloper.getId());
			} else {
				updateDevelopers.setInt(6, developer.getId());
			}

			updateDevelopers.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public boolean createDeveloper(Developer developer) {
		try {
			insertDevelopers.setInt(1, developer.getCompany_id());
			insertDevelopers.setString(2, developer.getName());
			insertDevelopers.setInt(3, developer.getAge());
			insertDevelopers.setString(4, developer.getSex().toString());
			insertDevelopers.setInt(5, developer.getSalary());
			return insertDevelopers.executeUpdate() == 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public void createDevelopersFromList(List<Developer> developer) {
		try {
			for (Developer dev : developer) {
				insertDevelopers.setInt(1, dev.getCompany_id());
				insertDevelopers.setString(2, dev.getName());
				insertDevelopers.setInt(3, dev.getAge());
				insertDevelopers.setString(4, dev.getSex().toString());
				insertDevelopers.setInt(5, dev.getSalary());
				insertDevelopers.addBatch();
			}
			insertDevelopers.executeBatch();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}


	public Developer selectDevelopersByName(String name) {
		try {
			selectDevelopers.setString(1, name);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		try (ResultSet resultSet = selectDevelopers.executeQuery()) {
			Developer developer = new Developer();
			while (resultSet.next()) {
				developer.setId(resultSet.getInt("id"));
				developer.setCompany_id(resultSet.getInt("company_id"));
				developer.setName(resultSet.getString("name"));
				developer.setAge(resultSet.getInt("age"));
				developer.setSex(Developer.Sex.valueOf(resultSet.getString("sex")));
				developer.setSalary(resultSet.getInt("salary"));
			}
			return developer;
		} catch (SQLException ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public void addDevelopersSkills(Developer developer, Skills skills) {
		try {
			insertDevelopersSkills.setInt(1, developer.getId());
			insertDevelopersSkills.setInt(2, skills.getId());
			insertDevelopersSkills.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public void addDeveloperToProjects(int[] developer, int[] projects) {
		try {
			for (int i = 0; i < developer.length; i++) {
				insertDeveloperToProjects.setInt(1, developer[i]);
				insertDeveloperToProjects.setInt(2, projects[i]);
				insertDeveloperToProjects.addBatch();
			}
			insertDeveloperToProjects.executeBatch();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public void deleteById(int id) throws SQLException {
		deleteByIdSt.setInt(1, id);
		deleteByIdSt.executeUpdate();
	}
}
