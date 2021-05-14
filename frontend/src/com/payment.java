package com;

import java.sql.*;

public class payment {
	
	private Connection connect() 
	 { 
	 Connection con = null; 
	 try
	 { 
	 Class.forName("com.mysql.cj.jdbc.Driver"); 
	 
	 //Provide the correct details: DBServer/DBName, username, password 
	 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/paf", "root", ""); 
	 } 
	 catch (Exception e) 
	 { 
		 e.printStackTrace();
	 } 
	 return con; 
	 } 
	
	
	public String insertPayment(String paymentID, String amount, String date, String paymentType) 
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for inserting."; } 
	 // create a prepared statement
	 String query = " insert into payment (`paymentID`,`amount`,`paymentName`,`date`,`paymentType`)"
	 + " values (?, ?, ?, ?, ?)"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 // binding values
	 preparedStmt.setInt(1, 0); 
	 preparedStmt.setString(2, paymentID); 
	 preparedStmt.setString(3, amount); 
	 preparedStmt.setDouble(4, Double.parseDouble(date)); 
	 preparedStmt.setString(5, paymentType); 
	 
	// execute the statement
	 preparedStmt.execute(); 
	 con.close(); 
	 
	 	String newPayment = readPayment();
	 	output = "{\"status\":\"success\", \"data\": \"" + 
	 			 newPayment + "\"}"; 
	 } 
	 catch (Exception e) 
	 { 
		 output = "{\"status\":\"error\", \"data\": \"Error while inserting the payment.\"}"; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 } 
	
	public String readPayment() 
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for reading."; } 
	 // Prepare the html table to be displayed
	 output = "<table border='1'><tr><th>Payment ID</th><th>amount</th>" +
	 "<th>date</th>" + 
	 "<th>Payment Type</th>" +
	 "<th>Update</th><th>Remove</th></tr>"; 
	 
	 String query = "select * from payment"; 
	 Statement stmt = con.createStatement(); 
	 ResultSet rs = stmt.executeQuery(query); 
	 
	 // iterate through the rows in the result set
	 while (rs.next()) 
	 { 
	 String paymentID = Integer.toString(rs.getInt("paymentID")); 
	 String amount = rs.getString("amount"); 
	 String date = rs.getString("date"); 
	 String paymentType = Double.toString(rs.getDouble("paymentType")); 
	 String accountNumber = rs.getString("accountNumber"); 
	 
	// Add into the html table
	 output += "<tr><td><input id='hidPaymentIDUpdate' name='hidPaymentIDUpdate' type='hidden' value='" + paymentID
	 + "'>" + paymentID + "</td>"; 
	 output += "<td>" + amount + "</td>"; 
	 output += "<td>" + date + "</td>"; 
	 output += "<td>" + paymentType + "</td>"; 
	 
	 
	// buttons
	 output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"
			+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-paymentid='"
			+ paymentID + "'>" + "</td></tr>"; 
	 } 
	 con.close(); 
	 // Complete the html table
	 output += "</table>"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "Error while reading the payment."; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 } 
	
	
	public String updatePayment(String paymentID, String amount, String date, String paymentType, String accountNumber)
	{ 
		 String output = ""; 
		 try
		 { 
			 Connection con = connect(); 
		 if (con == null) 
		 {return "Error while connecting to the database for updating."; } 
		 // create a prepared statement
		 String query = "UPDATE payment SET paymentID=?,amount=?,date=?,paymentType=? WHERE accountNumber=?"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 // binding values
		 preparedStmt.setString(1, paymentID); 
		 preparedStmt.setString(2, amount); 
		 preparedStmt.setDouble(3, Double.parseDouble(date)); 
		 preparedStmt.setString(4, paymentType); 
		 preparedStmt.setInt(5, Integer.parseInt(accountNumber)); 
		 // execute the statement
		 preparedStmt.execute(); 
		 con.close(); 
		 
		 String newPayment = readPayment();
		 output = "{\"status\":\"success\", \"data\": \"" + 
				 newPayment + "\"}";  
		 
		 } 
		 catch (Exception e) 
		 { 
			 output = "{\"status\":\"error\", \"data\": \"Error while updating the payment.\"}"; 
		 System.err.println(e.getMessage()); 
		 } 
		 return output; 
		 } 
	
	
	public String deletePayment(String paymentID) 
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {
		 return "Error while connecting to the database for deleting."; 
	 } 
	 // create a prepared statement
	 String query = "delete from payment where paymentID=?"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 // binding values
	 preparedStmt.setInt(1, Integer.parseInt(paymentID)); 
	 // execute the statement
	 preparedStmt.execute(); 
	 con.close();
	 
	 String newPayment = readPayment();
	 output = "{\"status\":\"success\", \"data\": \"" + 
			 newPayment + "\"}"; 
	 } 
	 catch (Exception e) 
	 { 
		 output = "{\"status\":\"error\", \"data\": \"Error while deleting the payment.\"}"; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 } 


}
