package com.doctor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class Doctor {
	
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Properties connectionProps = new Properties();
		    connectionProps.put("user", "root");
		    connectionProps.put("password", "");
			con = (Connection) DriverManager.getConnection("jdbc:mysql://127.0.0.1:3307/healthcare", connectionProps);
			// For testing
			System.out.print("Successfully connected");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	public String insertDoctor(String DocID, String name, String gender, String specialist) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database";
			}
			// create a prepared statement
			String query = "insert into doctor(doc_ID,name,gender,specialist)"
					+ " values ( ?,  ?,  ?,  ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setString(1, DocID);
			preparedStmt.setString(2, name);
			preparedStmt.setString(3, gender);
			preparedStmt.setString(4, specialist);
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting";
			System.err.println(e.getMessage());
		}
		return output;

	}
	
	public String readItems() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the table to be displayed
			output = "<table class=\"table\"><tr><th>Doctor ID</th>"
					+ "<th>Doctor Name</th><th>Doctor Gender</th>" + "<th>Doctor Specialist</th>"
					+ "<th>Update</th><th>Remove</th></tr>";
			String query = "select * from Doctor";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String DoctorID = rs.getString("doc_ID");
				String DoctorName = rs.getString("name");
				String DoctorGender = rs.getString("gender");
				String DoctorSpecialist = rs.getString("specialist");
				
				// I Added to table
				
				output += "<tr><td>" + DoctorID + "</td>";
				output += "<td>" + DoctorName + "</td>";
				output += "<td>" + DoctorGender + "</td>";
				output += "<td>" + DoctorSpecialist + "</td>";
				
				//I added some buttons here
				
				output += "<td><input name=\"btnUpdate\" "
						+ " type=\"button\" class=\"btn btn-primary\" value=\"Update\"></td>"
						+ "<td><form method=\"post\" action=\"items.jsp\">" + "<input name=\"btnRemove\" "
						+ " type=\"submit\" class=\"btn btn-danger\" value=\"Remove\">"
						+ "<input name=\"itemID\" type=\"hidden\" " + " value=\"" + DoctorID + "\">"
						+ "</form></td></tr>";
			}
			con.close();
			// Complete the table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the items.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	public String updateDoctor(String ID, String name, String gender, String specialist) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			String query = "UPDATE doctor SET name=?,gender=?,specialist=? WHERE doc_ID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, name);
			preparedStmt.setString(2, gender);
			preparedStmt.setString(3, specialist);
			preparedStmt.setString(4, ID);

			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the item.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	public String deleteDoctor(String DoctorID) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from Doctor where doc_ID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, DoctorID);
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the item.";
			System.err.println(e.getMessage());
		}
		return output;
	}
}