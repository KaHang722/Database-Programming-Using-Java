package assignment4;

/*************************************************************************************************
*  Course_Name – Assignment 4                                                                                                                                *

*  I declare that this assignment is my own work in accordance with Humber Academic Policy.        *

*  No part of this assignment has been copied manually or electronically from any other source       *

*  (including web sites) or distributed to other students/social media.                                                       *
                                                                                                                                                                             
*  Name: LONG TENG PAK 	Student ID: N01526495 	Date: 10 Nov 2022 	
*  Name: KA HANG CHAN	Student ID: N01530146	Date: 10 Nov 2022
*  Name: .Vaishali		Student ID:	N01495940	Date: 10 Nov 2022
*  
*  Self Evaluation
* 	1.	Is your database created correctly? YES
	2.	Can you create the UI? YES
	3.	Can you connect to the database? YES
	4.	Is the View button implemented correctly? YES
	5.	Is the Insert button implemented correctly? YES
	6.	Is the Update button implemented correctly? YES
	7.	Is the Clear button implemented correctly? YES
	          
*************************************************************************/
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class Application extends JFrame{
	private static final int FRAME_WIDTH = 650;
	private static final int FRAME_HEIGHT = 500;
	
	private JLabel lblID;
	private JLabel lblLastName;
	private JLabel lblFirstName;
	private JLabel lblMI;
	private JLabel lblAddress;
	private JLabel lblCity;
	private JLabel lblState;
	private JLabel lblTelephone;
	private JLabel lblEmail;
	private JLabel lblDatabase;
	
	private JTextField txtID;
	private JTextField txtLastName;
	private JTextField txtFirstName;
	private JTextField txtMI;
	private JTextField txtAddress;
	private JTextField txtCity;
	private JTextField txtState;
	private JTextField txtTelephone;
	private JTextField txtEmail;
	
	private JTextArea txtAreaDisplay;
	
	private JButton btnView;
	private JButton btnInsert;
	private JButton btnUpdate;
	private JButton btnClear;
	
	private JPanel inputPanel;
	private JPanel btnPanel;
	private JPanel databasePanel;
	private JPanel displayPanel;
	private JPanel mainPanel;
	private JPanel middlePanel;
	private JPanel bottomPanel;
	
	private JPanel iPanel1;
	private JPanel iPanel2;
	private JPanel iPanel3;
	private JPanel iPanel4;
	private JPanel iPanel5;
	private JPanel iPanel6;
	
	
	private Connection connection;
	private Statement statement;
	private PreparedStatement pstmt;
	private ResultSet resultset;
	
	private String query;
	
	private String id;
	private String lastName;
	private String firstName;
	private String mi;
	private String address;
	private String city;
	private String state;
	private String tel;
	private String email;
	
	public Application() {
		setSize(FRAME_WIDTH,FRAME_HEIGHT);
		
		CreateInputPanel();
		
		CreateBtnPanel();
		
		CreateBottomPanel();
		
		CreateLayout();
		
		//connect to the database
		try {
			OracleInfo oracleInfo = new OracleInfo();
			Class.forName(oracleInfo.DRIVER);
			connection = DriverManager.getConnection(oracleInfo.URL,oracleInfo.USERNAME,oracleInfo.PASSWORD);
			lblDatabase.setText("Database is now connected");
			
			statement = connection.createStatement();
			query = " CREATE TABLE Staff ("+
	                " id CHAR(9) NOT NULL,"+
	                " lastName VARCHAR(15),"+
	                "  firstName VARCHAR(15),"+
	                "  mi CHAR(1),"+
	                "  address VARCHAR(20),"+
	                "  city VARCHAR(20),"+
	                "  state CHAR(2),"+
	                "  telephone CHAR(10),"+
	                "  email VARCHAR(40),"+
	                "  PRIMARY KEY (id)) ";
			statement.executeUpdate(query);
			
			
		}catch(ClassNotFoundException ex){
			lblDatabase.setText("Database NOT connected");
			
		}catch(SQLException ex) {

		}
	}
	
	//input panel content
	public void CreateInputPanel() {
		
		iPanel1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		iPanel2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		iPanel3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		iPanel4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		iPanel5 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		iPanel6 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		
		lblID = new JLabel("ID ");
		txtID = new JTextField();
		txtID.setPreferredSize(new Dimension(200,20));
		txtID.setBackground(Color.YELLOW);
		iPanel1.add(lblID);
		iPanel1.add(txtID);
		
		lblLastName = new JLabel("Last Name ");
		txtLastName = new JTextField();
		txtLastName.setPreferredSize(new Dimension(150,20));
		lblFirstName = new JLabel("First Name ");
		txtFirstName = new JTextField();
		txtFirstName.setPreferredSize(new Dimension(150,20));
		lblMI = new JLabel("MI ");
		txtMI = new JTextField();
		txtMI.setPreferredSize(new Dimension(30,20));
		iPanel2.add(lblLastName);
		iPanel2.add(txtLastName);
		iPanel2.add(lblFirstName);
		iPanel2.add(txtFirstName);
		iPanel2.add(lblMI);
		iPanel2.add(txtMI);
		
		lblAddress = new JLabel("Address ");
		txtAddress = new JTextField();
		txtAddress.setPreferredSize(new Dimension(210,20));
		iPanel3.add(lblAddress);
		iPanel3.add(txtAddress);
		
		lblCity = new JLabel("City ");
		txtCity = new JTextField();
		txtCity.setPreferredSize(new Dimension(210,20));
		lblState = new JLabel("State ");
		txtState = new JTextField();
		txtState.setPreferredSize(new Dimension(30,20));
		iPanel4.add(lblCity);
		iPanel4.add(txtCity);
		iPanel4.add(lblState);
		iPanel4.add(txtState);
		
		
		lblTelephone= new JLabel("Telephone ");
		txtTelephone = new JTextField();
		txtTelephone.setPreferredSize(new Dimension(210,20));
		iPanel5.add(lblTelephone);
		iPanel5.add(txtTelephone);
		
		lblEmail = new JLabel("Email ");
		txtEmail = new JTextField();
		txtEmail.setPreferredSize(new Dimension(210,20));
		iPanel6.add(lblEmail);
		iPanel6.add(txtEmail);
		
		inputPanel = new JPanel();
		inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
		inputPanel.setPreferredSize(new Dimension(600,210));
		inputPanel.setBorder(new TitledBorder("Staff Information"));
		inputPanel.add(iPanel1);
		inputPanel.add(iPanel2);
		inputPanel.add(iPanel3);
		inputPanel.add(iPanel4);
		inputPanel.add(iPanel5);
		inputPanel.add(iPanel6);
	}
	
	//button panel content
	public void CreateBtnPanel() {
		btnView = new JButton("View");
		btnInsert = new JButton("Insert");
		btnUpdate = new JButton("Update");
		btnClear = new JButton("Clear");
		
		btnPanel = new JPanel();
		btnPanel.add(btnView);
		btnPanel.add(btnInsert);
		btnPanel.add(btnUpdate);
		btnPanel.add(btnClear);
		
		//ActionListener to btnView
		class BtnViewListener implements ActionListener {
			public void actionPerformed (ActionEvent ae) {
				BtnView();
			}
		}
		btnView.addActionListener(new BtnViewListener());
		
		//ActionListener to btnInsert
		class BtnInsertListener implements ActionListener {
			public void actionPerformed (ActionEvent ae) {
				BtnInsert();
			}
		}
		btnInsert.addActionListener(new BtnInsertListener());
		
		//ActionListener to btnUpdate
		class BtnUpdateListener implements ActionListener {
			public void actionPerformed (ActionEvent ae) {
				BtnUpdate();
			}
		}
		btnUpdate.addActionListener(new BtnUpdateListener());
		
		//ActionListener to btnClear
		class BtnClearListener implements ActionListener {
			public void actionPerformed (ActionEvent ae) {
				BtnClear();
			}
		}
		btnClear.addActionListener(new BtnClearListener());
		
	}
	
	//bottom Panel consist of database connection msg (lblDatabase)
	//and Display textArea
	public void CreateBottomPanel() {
		lblDatabase = new JLabel("");
		databasePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		databasePanel.add(lblDatabase);
		
		txtAreaDisplay = new JTextArea(10,50);
		txtAreaDisplay.setText("Click view button to display information here.");
		txtAreaDisplay.setEditable(false);
		displayPanel = new JPanel();
		displayPanel.add(txtAreaDisplay);
	}
	
	//Layout design
	public void CreateLayout() {
		mainPanel = new JPanel();
		mainPanel.add(inputPanel);
		
		middlePanel = new JPanel();
		middlePanel.add(btnPanel);
		
		bottomPanel = new JPanel(new BorderLayout());
		bottomPanel.add(databasePanel, BorderLayout.CENTER);
		bottomPanel.add(displayPanel, BorderLayout.SOUTH);
		
		
		add(mainPanel, BorderLayout.NORTH);
		add(middlePanel, BorderLayout.CENTER);
		add(bottomPanel, BorderLayout.SOUTH);
	}
	
	//View button actionPerformed
	public void BtnView() {
		try {
			//Check if there is existing data using input ID
			if(!UniqueIDValidation()) {
			
				String inputID = txtID.getText();
				int id = Integer.parseInt(inputID);
				String display = "";
			
				statement = connection.createStatement();
				query = "SELECT FIRSTNAME,LASTNAME, MI, ADDRESS, CITY, STATE, TELEPHONE, EMAIL FROM Staff WHERE id = ";
				resultset = statement.executeQuery(query + id);
		
				//getting the info from database and display it to textArea
				while(resultset.next()) {
					display += 	"First Name : "+ resultset.getString("FIRSTNAME")+"\n" +
								"Last Name : "+ resultset.getString("LASTNAME") + "\n" +
								"Mi : "+ resultset.getString("MI")+ "\n" +
								"Address : " + resultset.getString("ADDRESS")+ "\n" +
								"City : " + resultset.getString("CITY")+ "\n" +
								"State : " + resultset.getString("STATE")+ "\n" +
								"Telephone : " + resultset.getString("TELEPHONE")+ "\n" +
								"Email : " + resultset.getString("EMAIL");
				}
		
				txtAreaDisplay.setText(display);
			}else {
				//Error msg occur if there is no matching data
				JOptionPane.showMessageDialog(null,"No data available for this ID input");
			}
		}catch(SQLException ex) {
			System.out.println(ex.getMessage());
			
		}catch(NumberFormatException ex) {
			//for blank ID input
			JOptionPane.showMessageDialog(null,"Please input id field to view");

		}
	}
	
	//Insert button actionPerformed
	public void BtnInsert() {
		//Check if the input ID does NOT match with existing data (i.e. UNIQUE id)
		if(!UniqueIDValidation()) {
			JOptionPane.showMessageDialog(null,"input ID is NOT unique");
			
		}else if (InputValidation()) {  //validation of input data
			
			id = txtID.getText();
			lastName = txtLastName.getText();
			firstName = txtFirstName.getText();
			mi = txtMI.getText();
			address = txtAddress.getText();
			city = txtCity.getText();
			state = txtState.getText();
			tel = txtTelephone.getText();
			email = txtEmail.getText();
			
			//use prepareStatement to insert new data into the database
			try {
				statement = connection.createStatement();
				pstmt = connection.prepareStatement("INSERT INTO Staff (ID,LASTNAME,FIRSTNAME,MI,ADDRESS,CITY,STATE,TELEPHONE, EMAIL)"
                        + "VALUES(?,?,?,?,?,?,?,?,?)");
                pstmt.setString(1,id);
                pstmt.setString(2,lastName);
                pstmt.setString(3,firstName);
                pstmt.setString(4,mi);
                pstmt.setString(5,address);
                pstmt.setString(6,city);
                pstmt.setString(7,state);
                pstmt.setString(8,tel);
                pstmt.setString(9,email);
                pstmt.executeUpdate();
                
                JOptionPane.showMessageDialog(null,"Insert successful!");
                		
			}catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}
		}
	}
	
	//Update button actionPerformed
	public void BtnUpdate() {
		//validation of input data
		if (InputValidation()) {
			
			id = txtID.getText();
			int parsedID = Integer.parseInt(id);
			lastName = txtLastName.getText();
			firstName = txtFirstName.getText();
			mi = txtMI.getText();
			address = txtAddress.getText();
			city = txtCity.getText();
			state = txtState.getText();
			tel = txtTelephone.getText();
			email = txtEmail.getText();
			
			//use prepareStatement to update new data into the database
			try {
				statement = connection.createStatement();
				pstmt = connection.prepareStatement("UPDATE Staff SET LASTNAME = ?, FIRSTNAME = ?, MI = ?, ADDRESS = ?, CITY = ?, STATE = ?, TELEPHONE = ?, EMAIL = ? WHERE ID = ?");
               
                pstmt.setString(1,lastName);
                pstmt.setString(2,firstName);
                pstmt.setString(3,mi);
                pstmt.setString(4,address);
                pstmt.setString(5,city);
                pstmt.setString(6,state);
                pstmt.setString(7,tel);
                pstmt.setString(8,email);
                pstmt.setInt(9,parsedID);
                pstmt.executeUpdate();
                
                JOptionPane.showMessageDialog(null,"UPDATE successful!");
                		
			}catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}
		}
	}
	
	//Clear button actionPerformed
	public void BtnClear() {
		txtID.setText("");
		txtLastName.setText("");
		txtFirstName.setText("");
		txtMI.setText("");
		txtAddress.setText("");
		txtCity.setText("");
		txtState.setText("");
		txtTelephone.setText("");
		txtAreaDisplay.setText("");
		txtEmail.setText("");
	}
	
	//Input validation (mainly blank and length of input)
	public boolean InputValidation() {
		boolean result = true;
		
		id = txtID.getText();
		lastName = txtLastName.getText();
		firstName = txtFirstName.getText();
		mi = txtMI.getText();
		address = txtAddress.getText();
		city = txtCity.getText();
		state = txtState.getText();
		tel = txtTelephone.getText();
		email = txtEmail.getText();
		
		if(id.equals("")) {
            JOptionPane.showMessageDialog(null,"ID CANNOT BE NULL");
            result = false;
		}
		
		if(lastName.equals("")) {
            JOptionPane.showMessageDialog(null,"lastName CANNOT BE NULL");
            result = false;
		}
		
		if(firstName.equals("")) {
            JOptionPane.showMessageDialog(null,"firstName CANNOT BE NULL");
            result = false;
		}
		
		if(mi.equals("")) {
            JOptionPane.showMessageDialog(null,"MI CANNOT BE NULL");
            result = false;
		}else if (mi.length() != 1) {
			JOptionPane.showMessageDialog(null,"MI can only be in ONE character");
            result = false;
		}
		
		if(address.equals("")) {
            JOptionPane.showMessageDialog(null,"address CANNOT BE NULL");
            result = false;
		}
		
		if(city.equals("")) {
            JOptionPane.showMessageDialog(null,"city CANNOT BE NULL");
            result = false;
		}
		
		if(state.equals("")) {
            JOptionPane.showMessageDialog(null,"state CANNOT BE NULL");
            result = false;
		}else if (state.length() != 2) {
			JOptionPane.showMessageDialog(null,"state can only be in TWO characters");
            result = false;
		}
		
		if(tel.equals("")) {
            JOptionPane.showMessageDialog(null,"tel CANNOT BE NULL");
            result = false;
		}else if (tel.length() > 10) {
			JOptionPane.showMessageDialog(null,"tel cannot be greater than 10 digits");
            result = false;
		}
			
		if(email.equals("")) {
            JOptionPane.showMessageDialog(null,"email CANNOT BE NULL");
            result = false;
		}else if (!(email.contains("@"))) {
			JOptionPane.showMessageDialog(null,"email CANNOT be without @ presented");
            result = false;
		}
		
		return result;
	}
	
	//check if the ID is unique
	public boolean UniqueIDValidation() {
		boolean result = true;
		
		try {
			String inputID = txtID.getText();
			int testID = Integer.parseInt(inputID);
			
			statement = connection.createStatement();
			query = "SELECT ID FROM Staff";
			resultset = statement.executeQuery(query);
			
			//set up an arrayList to store all returned result
			ArrayList<Integer> results = new ArrayList<Integer>();
			
			//add returned result into the arrayList
			while(resultset.next()) {
				int resultID = resultset.getInt("ID");
				results.add(resultID);
			}
			
			//loop through the arrayList to see if there is match
			//if there is match, loop breaks and function will return false (NOT unique)
			for(int i=0; i<results.size(); i++) {
				if (results.get(i) == testID) {
					result = false;
					break;
				}
			}
		
		}catch(SQLException ex) {
			System.out.println(ex.getMessage());
			
		}
		
		return result;
	}
	
}