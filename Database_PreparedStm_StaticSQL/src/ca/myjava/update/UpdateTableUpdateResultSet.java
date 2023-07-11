package ca.myjava.update;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdateTableUpdateResultSet {

	public static void main(String[] args) {
		try {
			//connection to the database
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@calvin.humber.ca:1521:grok",
					"n01526495", "oracle");
			System.out.println("Database connected.");

			//Set the resultSet to be updatable with scrollable cursor
			Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			String query = "Select countryID, name, LifeExpectancy From Country";
			ResultSet resultSet = statement.executeQuery(query);

			// point the cursor to the first row
			// update the first row and its column "LifeExpectancy"
			// execute the update
			resultSet.absolute(1);
			resultSet.updateFloat("LifeExpectancy", (float)79.4);
			resultSet.updateRow();
			
			System.out.println("Update successful");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}


	}

}
