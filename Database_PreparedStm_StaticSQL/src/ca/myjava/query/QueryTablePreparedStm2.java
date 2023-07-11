package ca.myjava.query;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryTablePreparedStm2 {

	public static void main(String[] args) {
		
		
		try {
			//connection to the database
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@calvin.humber.ca:1521:grok", "n01530146", "oracle");
			System.out.println("Database connected.");
			
			//prepare statement
			PreparedStatement pstmt = connection.prepareStatement("Select * From Country where CountryID = ? AND Name = ?");
			pstmt.setInt(1, 1);
			pstmt.setString(2, "canada");
			ResultSet resultSet = pstmt.executeQuery();

			//displaying the resultSet using a while loop
			String display = "";
			while (resultSet.next()) {
				display += 	"CountryID: " + resultSet.getInt(1) + "\n"
						  +	"Name: " + 	resultSet.getString(2) + "\n"
						  +	"LifeExpectancy: " + resultSet.getFloat(3) + "\n";
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
