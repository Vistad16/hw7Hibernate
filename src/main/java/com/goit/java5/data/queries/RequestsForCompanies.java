package com.goit.java5.data.queries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.goit.java5.connection.Storage;
import com.goit.java5.data.entity.Companies;


public class RequestsForCompanies {
	Storage storage = Storage.getInstance();


	private final PreparedStatement insertCompanies;
	private final PreparedStatement selectCompanies;
	private final PreparedStatement deleteCompaniesByIdSt;
	private final PreparedStatement updateCompaniesSt;
	private final PreparedStatement selectCompaniesAll;

	public RequestsForCompanies() throws SQLException {
		Connection connection = storage.getConnection();

		insertCompanies = connection.prepareStatement(
				"INSERT INTO companies (company_name, specialization) VALUES (?, ?)"
		);

		selectCompanies = connection.prepareStatement(
				"SELECT id, company_name, specialization FROM companies WHERE company_name = ?"
		);
		deleteCompaniesByIdSt = connection.prepareStatement(
				"DELETE FROM companies WHERE id = ?"
		);
		updateCompaniesSt = connection.prepareStatement(
				"UPDATE companies SET company_name = ?, specialization = ? WHERE id = ?"
		);
		selectCompaniesAll = connection.prepareStatement(
				"SELECT id, company_name, specialization FROM companies");
	}


	public boolean createCompanies(String company_name, String specialization) {
		try {
			insertCompanies.setString(1, company_name);
			insertCompanies.setString(2, specialization);
			return insertCompanies.executeUpdate() == 1;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return false;
	}

	public void createCompaniesFromList(List<Companies> companiesList) {
		try {
			for (Companies companies : companiesList) {
				insertCompanies.setString(1, companies.getCompany_name());
				insertCompanies.setString(2, companies.getSpecialization());
				insertCompanies.addBatch();
			}
			insertCompanies.executeBatch();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public Companies selectCompaniesByItCompanies(String company_name) {
		try {
			selectCompanies.setString(1, company_name);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		Companies companies = new Companies();

		try (ResultSet resultSet = selectCompanies.executeQuery()) {
			resultSet.next();
			companies.setId(resultSet.getInt("id"));
			companies.setCompany_name(resultSet.getString("company_name"));
			companies.setSpecialization(resultSet.getString("specialization"));

			return companies;
		} catch (SQLException ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public List<Companies> selectAllCompanies() {
		try (ResultSet resultSet = selectCompaniesAll.executeQuery()) {
			List<Companies> result = new ArrayList<>();
			while (resultSet.next()) {
				Companies companies = new Companies();

				companies.setId(resultSet.getInt("id"));
				companies.setCompany_name(resultSet.getString("company_name"));
				companies.setSpecialization(resultSet.getString("specialization"));

				result.add(companies);
			}

			return result;
		} catch (SQLException ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public void deleteCompaniesById(int id) throws SQLException {
		deleteCompaniesByIdSt.setInt(1, id);
		deleteCompaniesByIdSt.executeUpdate();
	}

	public void updateCompanies(int id, Companies companies) {
		try {
			updateCompaniesSt.setString(1, companies.getCompany_name());
			updateCompaniesSt.setString(2, companies.getSpecialization());
			updateCompaniesSt.setInt(3, id);
			updateCompaniesSt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
