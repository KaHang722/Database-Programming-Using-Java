package ca.myjava.unknown;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import java.sql.ResultSetMetaData;

public class AnySQL {

	public static void main(String[] args) {
		//Create scanner object for user to insert the query
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please input an SQL query");
		String query = scanner.nextLine();
		
		try {
			// connection to the database
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@calvin.humber.ca:1521:grok",
					"n01526495", "oracle");
			System.out.println("Database connected.");

			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			//create metaData which includes column names
			ResultSetMetaData rsmd = resultSet.getMetaData();
			int columnCount = rsmd.getColumnCount();

			// for loop to loop through the column names in the metaData 
			//and get all info from resultSet
			while (resultSet.next())
				for (int i = 1; i <= columnCount; i++) {
					System.out.print(rsmd.getColumnName(i) + ": ");
					System.out.println(resultSet.getString(i));

				}
		
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			
		} catch (SQLException ex) {
			ex.printStackTrace();
			
		}
		

	}

}
