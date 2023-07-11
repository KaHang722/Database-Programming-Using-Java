package ca.myjava.update;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateTablePreparedStm {

	public static void main(String[] args) {
		
		try {
			//connection to the database
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@calvin.humber.ca:1521:grok", "n01526495", "oracle");
			System.out.println("Database connected.");
			
			//prepareStatement for insert
			PreparedStatement pstmt = connection.prepareStatement("Insert into country(countryID, name, LifeExpectancy) values(?,?,?)");
			pstmt.setInt(1, 7);
			pstmt.setString(2, "Germany");
			pstmt.setFloat(3, (float)82.1);
			pstmt.executeUpdate();

			System.out.println("Insert completed");
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
	}

}
