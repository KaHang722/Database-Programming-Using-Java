package ca.myjava.query;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class QueryTableStaticSQL {

	public static void main(String[] args) {
		
		try {
			// connection to the database
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@calvin.humber.ca:1521:grok",
					"n01530146", "oracle");
			System.out.println("Database connected.");

			//static SQL query
			Statement statement = connection.createStatement();
			String query = "Select * From Country where CountryID = 2 AND Name = 'US'";
			ResultSet resultSet = statement.executeQuery(query);

			//displaying the resultSet using a while loop
			String display = "";
			while (resultSet.next()) {
				display += "CountryID: " + resultSet.getInt(1) + "\n" + "Name: " 
						+ resultSet.getString(2) + "\n"
						+ "LifeExpectancy: " + resultSet.getFloat(3) + "\n";
			}
			System.out.println(display);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}

	}

}
