package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class Patient {

	public Connection connect() {
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

public String insertPatient(String NIC, String fname, String lname, String gender, String phone) {
				
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database";
			}
			// create a prepared statement
			String query = "insert into patient(NIC,fname,lname,gender) values ( ?,  ?,  ?,  ?)";
			PreparedStatement preparedStmt = (PreparedStatement) con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, NIC);
			preparedStmt.setString(2, fname);
			preparedStmt.setString(3, lname);
			preparedStmt.setString(4, gender);
			// execute the statement
			preparedStmt.execute();
			con.close();

            output = "Inserted successfully" ;

		
		} catch (Exception e) {
			output = "Error while inserting";
			System.err.println(e.getMessage());
		}
		return output;
	}


	public String readPatient() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table class=\"table\"><tr><th>Patient NIC</th>" + "<th>Patient fname</th><th>Patient lname</th>"
					+ "<th>Patient gender</th>" + "<th>Update</th><th>Remove</th></tr>";
			String query = "select * from patient";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String patientNIC = rs.getString("NIC");
				String patientfname = rs.getString("fname");
				String patientlname = rs.getString("lname");
				String patientgender = rs.getString("gender");
				// Add into the html table
				output += "<tr><td>" + patientNIC + "</td>";
				output += "<td>" + patientfname + "</td>";
				output += "<td>" + patientlname + "</td>";

				output += "<td>" + patientgender + "</td>";
				// buttons
				output += "<td><input name=\"btnUpdate\" "
						+ " type=\"button\" class=\"btn btn-primary\" value=\"Update\"></td>"
						+ "<td><form method=\"post\" action=\"patient.jsp\">" + "<input name=\"btnRemove\" "
						+ " type=\"submit\" class=\"btn btn-danger\" value=\"Remove\">"
						+ "<input name=\"patientNIC\" type=\"hidden\" " + " value=\"" + patientNIC + "\">"
						+ "</form></td></tr>";
			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the patient.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updatePatient(String NIC, String fname, String lname, String gender, String phone) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			String query = "update patient SET NIC=?,fname=?,lname=?,gender=? WHERE NIC=?";
			PreparedStatement preparedStmt = (PreparedStatement) con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, NIC);
			preparedStmt.setString(2, fname);
			preparedStmt.setString(3, lname);
			preparedStmt.setString(4, gender);
			preparedStmt.setString(5, NIC);
//			preparedStmt.setString(5, phone);

			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the patient.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deletePatient(String NIC) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from patient where NIC=?";
			PreparedStatement preparedStmt = (PreparedStatement) con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, NIC);
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the patient.";
			System.err.println(e.getMessage());
		}
		return output;
	}

}
