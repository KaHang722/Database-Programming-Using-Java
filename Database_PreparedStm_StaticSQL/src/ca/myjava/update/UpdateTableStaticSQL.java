package ca.myjava.update;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class UpdateTableStaticSQL {

	public static void main(String[] args) {
		try {
			//connection to the database
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@calvin.humber.ca:1521:grok",
					"n01526495", "oracle");
			System.out.println("Database connected.");

			//static SQL for delete
			Statement statement = connection.createStatement();
			String query = "DELETE FROM country Where name = 'UK'";
			statement.executeUpdate(query);

			
			System.out.println("Delete Successful");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}

	}

}
