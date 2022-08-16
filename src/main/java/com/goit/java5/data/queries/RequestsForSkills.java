package com.goit.java5.data.queries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.goit.java5.connection.Storage;
import com.goit.java5.data.entity.Skills;

public class RequestsForSkills {
	Storage storage = Storage.getInstance();

	private final PreparedStatement insertSkillsSt;
	private final PreparedStatement selectSkillsSt;
	private final PreparedStatement deleteByIdSt;
	private final PreparedStatement updateSkillsSt;
	private final PreparedStatement getAllSkillsSt;

	public RequestsForSkills() throws SQLException {
		Connection connection = storage.getConnection();

		insertSkillsSt = connection.prepareStatement(
				"INSERT INTO skills (programming_language, skill_level) VALUES (?, ?)"
		);

		selectSkillsSt = connection.prepareStatement(
				"SELECT id, programming_language, skill_level FROM skills WHERE programming_language LIKE ? AND skill_level LIKE ?"
		);
		deleteByIdSt = connection.prepareStatement(
				"DELETE FROM skills WHERE id = ?"
		);
		updateSkillsSt = connection.prepareStatement(
				"UPDATE skills SET programming_language = ?, skill_level = ? WHERE id = ?"
		);
		getAllSkillsSt = connection.prepareStatement(
				"SELECT id, programming_language, skill_level FROM skills"
		);
	}

	public List<Skills> getAllSkills() {
		try (ResultSet resultSet = getAllSkillsSt.executeQuery()) {
			List<Skills> skillsList = new ArrayList<>();
			while (resultSet.next()) {
				Skills skills = new Skills();
				skills.setId(resultSet.getInt("id"));
				skills.setProgramming_language(resultSet.getString("programming_language"));
				skills.setSkill_level(resultSet.getString("skill_level"));

				skillsList.add(skills);
			}

			return skillsList;

		} catch (SQLException ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public boolean createSkills(Skills skills) {
		try {
			insertSkillsSt.setString(1, skills.getProgramming_language());
			insertSkillsSt.setString(2, skills.getSkill_level());
			return insertSkillsSt.executeUpdate() == 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public void createSkillsFromList(List<Skills> skillsList) {
		try {
			for (Skills skills : skillsList) {
				insertSkillsSt.setString(1, skills.getProgramming_language());
				insertSkillsSt.setString(2, skills.getSkill_level());
				insertSkillsSt.addBatch();
			}
			insertSkillsSt.executeBatch();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public Skills selectSkillsByPosition(String programming_language, String skill_level) {
		try {
			selectSkillsSt.setString(1, programming_language);
			selectSkillsSt.setString(2, skill_level);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try (ResultSet resultSet = selectSkillsSt.executeQuery()) {
			Skills skills = new Skills();
			while (resultSet.next()) {
				skills.setId(resultSet.getInt("id"));
				skills.setProgramming_language(resultSet.getString("programming_language"));
				skills.setSkill_level(resultSet.getString("skill_level"));
			}
			return skills;
		} catch (SQLException ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public void deleteSkillsById(int id) throws SQLException {
		deleteByIdSt.setInt(1, id);
		deleteByIdSt.executeUpdate();
	}

	public void updateSkills(int id, Skills skills) {
		try {
			updateSkillsSt.setString(1, String.valueOf(skills.getProgramming_language()));
			updateSkillsSt.setString(2, String.valueOf(skills.getSkill_level()));
			updateSkillsSt.setInt(3, id);
			updateSkillsSt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
