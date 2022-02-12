package com.appointment;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Appointment {

	private Connection connect()
	{
				Connection con = null;
				try
				{
					Class.forName("com.mysql.jdbc.Driver");
					con= (Connection) DriverManager.getConnection("jdbc:mysql://127.0.0.1:3307/healthcare","root", "");
					//For testing
					System.out.print("Successfully connected");
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				return con;
	}
	
	public String insertAppoinment(String apt_ID, String discription, String patient_id,String doctor_id, String hospital_id,String datetime,String charge) {
		String output = "";
		try
		{
			Connection con = connect();
				if (con == null)
				{
					return "Error while connecting to the database";
				}
				// create a prepared statement
				String query = "insert into appoinment(apt_ID,discription,patient_id,doctor_id,hospital_id,datetime,charge) values ( ?,  ?,  ?,  ?,  ?,?,?)";
				PreparedStatement preparedStmt = (PreparedStatement) con.prepareStatement(query);
				// binding values
				preparedStmt.setString(1, apt_ID);
				preparedStmt.setString(2, discription);
				preparedStmt.setString(3, patient_id);
				preparedStmt.setString(4, doctor_id);
				preparedStmt.setString(5, hospital_id);
				preparedStmt.setString(6, datetime);
				preparedStmt.setDouble(7, Double.parseDouble(charge));

				//execute the statement
				preparedStmt.execute();
				con.close();
				output = "Inserted successfully";
		}
		catch (Exception e)
		{
				output = "Error while inserting";
				System.err.println(e.getMessage());
		}
		return output;
	}
		public String readAppoinment(){
			String output = "";
			try
			{
				Connection con = connect();
				if (con == null)
				{
				return "Error while connecting to the database for reading.";
				}
				// Prepare the html table to be displayed
				output = "<table class=\"table\"><tr><th>appoinmentapt_ID</th>"
				+"<th>appointmendiscription</th><th>patient ID</th>"
				+"<th>Doctor ID</th><th>Hospital ID</th>"
				+"<th>Date</th><th>Charge</th>"
				+ "<th>Update</th><th>Remove</th></tr>";
				String query = "select * from appoinment";
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				// iterate through the rows in the result set
				while (rs.next())
				{
					String apt_ID = rs.getString("apt_ID");
					String discription = rs.getString("discription");
					String patient_id = rs.getString("patient_id");
					String doctor_id = rs.getString("doctor_id");
					String hospital_id = rs.getString("hospital_id");
					String datetime = rs.getString("datetime");
					double charge = rs.getDouble("charge");

					
					// Add into the html table
					output += "<tr><td>" + apt_ID + "</td>";
					output += "<td>" + discription + "</td>";
					
					output += "<td>" + patient_id + "</td>";
					output += "<td>" + doctor_id + "</td>";
					output += "<td>" + hospital_id + "</td>";
					output += "<td>" + datetime + "</td>";
					output += "<td>" + charge + "</td>";

					// buttons
					output += "<td><input name=\"btnUpdate\" "
					+ " type=\"button\" class=\"btn btn-primary\" value=\"Update\"></td>"
					+ "<td><form method=\"post\" action=\"appoinment.jsp\">"
					+ "<input name=\"btnRemove\" "
					+ " type=\"submit\" class=\"btn btn-danger\" value=\"Remove\">"
					+ "<input name=\"appoinmentapt_ID\" type=\"hidden\" "
					+ " value=\"" + apt_ID + "\">" + "</form></td></tr>";
				}
			con.close();
			// Complete the html table
			output += "</table>";
			}
			catch (Exception e)
			{
			output = "Error while reading the appoinment.";
			System.err.println(e.getMessage());
			}
			return output;
		}
		
		public String updateappoinment(String apt_ID, String discription, String patient_id,String doctor_id, String hospital_id,String datetime,String charge) {
			String output = "";
			try {
				Connection con = connect();
				if (con == null) {
					return "Error while connecting to the database for updating.";
				}
				// create a prepared statement
				String query = "update appoinment SET discription=?,patient_id=?,doctor_id=?,hospital_id=?,datetime=?,charge=? WHERE apt_ID=?";
				PreparedStatement preparedStmt = (PreparedStatement) con.prepareStatement(query);
				// binding values
				preparedStmt.setString(7, apt_ID);
				preparedStmt.setString(1, discription);
				preparedStmt.setString(2, patient_id);
				preparedStmt.setString(3, doctor_id);
				preparedStmt.setString(4, hospital_id);
				preparedStmt.setDouble(6, Double.parseDouble(charge));
				preparedStmt.setString(5, datetime);


				// execute the statement
				preparedStmt.execute();
				con.close();
				output = "Updated successfully";
			} catch (Exception e) {
				output = "Error while updating the appoinment.";
				System.err.println(e.getMessage());
			}
			return output;
		}
		
		public String deleteappoinment(String apt_ID){
			String output = "";
			try{
				Connection con = connect();
				if (con == null)
				{
				return "Error while connecting to the database for deleting.";
				}
				// create a prepared statement
				String query = "delete from appoinment where apt_ID=?";
				PreparedStatement preparedStmt = (PreparedStatement) con.prepareStatement(query);
				// binding values
				preparedStmt.setString(1, apt_ID);
				// execute the statement
				preparedStmt.execute();
				con.close();
				output = "Deleted successfully";
			}
			catch (Exception e){
				output = "Error while deleting the appoinment.";
				System.err.println(e.getMessage());
			}
			return output;
		}
		
	}