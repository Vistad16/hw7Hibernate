package com.goit.java5.data.queries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.goit.java5.connection.Storage;
import com.goit.java5.data.entity.Customers;


public class RequestsForCustomers {
	Storage storage = Storage.getInstance();

	private final PreparedStatement insertCustomersSt;
	private final PreparedStatement selectCustomersSt;
	private final PreparedStatement deleteByIdSt;
	private final PreparedStatement updateCustomersSt;
	private final PreparedStatement getAllCustomersSt;

	public RequestsForCustomers() throws SQLException {
		Connection connection = storage.getConnection();

		insertCustomersSt = connection.prepareStatement(
				"INSERT INTO customers (name, country) VALUES (?, ?)"
		);

		selectCustomersSt = connection.prepareStatement(
				"SELECT id, name, country FROM customers WHERE name LIKE ? AND country LIKE ?"
		);
		deleteByIdSt = connection.prepareStatement(
				"DELETE FROM customers WHERE id = ?"
		);
		updateCustomersSt = connection.prepareStatement(
				"UPDATE customers SET name = ?, country = ? WHERE id = ?"
		);
		getAllCustomersSt = connection.prepareStatement(
				"SELECT id, name, country FROM customers"
		);
	}

	public List<Customers> getAllCustomers() {
		try (ResultSet resultSet = getAllCustomersSt.executeQuery()) {
			List<Customers> customersList = new ArrayList<>();
			while (resultSet.next()) {
				Customers customers = new Customers();
				customers.setId(resultSet.getInt("id"));
				customers.setName(resultSet.getString("name"));
				customers.setCountry(resultSet.getString("country"));

				customersList.add(customers);
			}

			return customersList;

		} catch (SQLException ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public boolean createCustomers(Customers customers) {
		try {
			insertCustomersSt.setString(1, customers.getName());
			insertCustomersSt.setString(2, customers.getCountry());
			return insertCustomersSt.executeUpdate() == 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public void createCustomersFromList(List<Customers> customersList) {
		try {
			for (Customers customers : customersList) {
				insertCustomersSt.setString(1, customers.getName());
				insertCustomersSt.setString(2, customers.getCountry());
				insertCustomersSt.addBatch();
			}
			insertCustomersSt.executeBatch();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public Customers selectCustomersByPosition(String name, String country) {
		try {
			selectCustomersSt.setString(1, name);
			selectCustomersSt.setString(2, country);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try (ResultSet resultSet = selectCustomersSt.executeQuery()) {
			Customers customers = new Customers();
			while (resultSet.next()) {
				customers.setId(resultSet.getInt("id"));
				customers.setName(resultSet.getString("name"));
				customers.setCountry(resultSet.getString("country"));
			}
			return customers;
		} catch (SQLException ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public void deleteCustomersById(int id) throws SQLException {
		deleteByIdSt.setInt(1, id);
		deleteByIdSt.executeUpdate();
	}

	public void updateSkills(int id, Customers customers) {
		try {
			updateCustomersSt.setString(1, String.valueOf(customers.getName()));
			updateCustomersSt.setString(2, String.valueOf(customers.getCountry()));
			updateCustomersSt.setInt(3, id);
			updateCustomersSt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
