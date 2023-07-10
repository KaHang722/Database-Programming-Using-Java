

import javax.swing.*;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Objects;
import java.awt.Color;

public class CustomerManager extends JFrame implements Serializable {
	
	JLabel id_Label;
	JLabel id_Label2;
	JLabel name_Label;
	JLabel name_Label2;
	JLabel phone_Label;
	JLabel phone_Label2;
	JLabel email_Label;
	JLabel email_Label2;
	JLabel postal_code_Label;
	JLabel postal_code_Label2;
	JLabel displayTitle;
	
	JTextField id_Field;
	JTextField name_Field;
	JTextField phone_Field;
	JTextField email_Field;
	JTextField postal_code_Field;
	JTextField customerIDTextField;

	Boolean flag_Id;// flag to indicate a valid data for ID
	Boolean flag_Name;// flag to indicate a valid data for Name
	Boolean flag_Phone;// flag to indicate a valid data for Phone
	Boolean flag_Email;// flag to indicate a valid data for Email Address
	Boolean flag_Postal_Code;// flag to indicate a valid data for Postal Code
	
	// buttons
	JButton submit_Button; 
	JButton modify_Button;
	
	// text area
	JTextArea area_Button;

	public CustomerManager() {
		
		this.setTitle("Customer Management System");
		this.setLayout(null);
		
		//Setting labels, label is for the display and label2 is displayed when error occur (like blank/wrong input)
		id_Label = new JLabel("Customer ID      : ");
		id_Label.setBounds(20, 50, 150, 30);
		id_Label.setFont(new Font("Verdana", Font.BOLD, 13));
		this.add(id_Label);
		id_Label2 = new JLabel("");
		id_Label2.setBounds(20, 70, 300, 30);
		id_Label2.setFont(new Font("Verdana", Font.BOLD, 10));
		this.add(id_Label2);
		id_Field = new JTextField("");
		id_Field.setBounds(150, 50, 150, 30);
		this.add(id_Field);

		name_Label = new JLabel("Customer Name  : ");
		name_Label.setBounds(320, 50, 140, 30);
		name_Label.setFont(new Font("Verdana", Font.BOLD, 13));
		this.add(name_Label);
		name_Label2 = new JLabel("");
		name_Label2.setBounds(320, 70, 300, 30);
		name_Label2.setFont(new Font("Verdana", Font.BOLD, 10));
		this.add(name_Label2);
		name_Field = new JTextField("");
		name_Field.setBounds(460, 50, 150, 30);
		this.add(name_Field);

		phone_Label = new JLabel("Phone Number   : ");
		phone_Label.setBounds(20, 100, 150, 30);
		phone_Label.setFont(new Font("Verdana", Font.BOLD, 13));
		this.add(phone_Label);
		phone_Label2 = new JLabel("");
		phone_Label2.setBounds(20, 120, 300, 30);
		phone_Label2.setFont(new Font("Verdana", Font.BOLD, 10));
		this.add(phone_Label2);
		phone_Field = new JTextField("");
		phone_Field.setBounds(150, 100, 150, 30);
		this.add(phone_Field);

		email_Label = new JLabel("Email Address     : ");
		email_Label.setBounds(320, 100, 140, 30);
		email_Label.setFont(new Font("Verdana", Font.BOLD, 13));
		this.add(email_Label);
		email_Label2 = new JLabel("");
		email_Label2.setBounds(320, 120, 220, 30);
		email_Label2.setFont(new Font("Verdana", Font.BOLD, 10));
		this.add(email_Label2);
		email_Field = new JTextField("");
		email_Field.setBounds(460, 100, 150, 30);
		this.add(email_Field);

		postal_code_Label = new JLabel("Postal Code        : ");
		postal_code_Label.setBounds(20, 150, 150, 30);
		postal_code_Label.setFont(new Font("Verdana", Font.BOLD, 13));
		this.add(postal_code_Label);
		postal_code_Label2 = new JLabel("");
		postal_code_Label2.setBounds(20, 170, 250, 30);
		postal_code_Label2.setFont(new Font("Verdana", Font.BOLD, 10));
		this.add(postal_code_Label2);
		postal_code_Field = new JTextField("");
		postal_code_Field.setBounds(150, 150, 150, 30);
		this.add(postal_code_Field);

		flag_Id = false;
		flag_Name = false;
		flag_Phone = false;
		flag_Email = false;
		flag_Postal_Code = false;

		submit_Button = new JButton("SUBMIT");
		submit_Button.setBounds(460, 200, 100, 30);
		submit_Button.setFont(new Font("Verdana", Font.BOLD, 14));
		submit_Button.setBackground(Color.decode("#33E6FF"));
		submit_Button.setForeground(Color.WHITE);
		this.add(submit_Button);
		//ActionListener for submit button
		submit_Button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					File file = new File("src/customer.dat");// creating a file with name customer.dat
					// Setting flags value to TRUE to write data in file
					
					//Verification of input
					if (Objects.equals(id_Field.getText(), "")) {// check for blank ID field
						id_Label2.setText("Please enter Customer Id");
						flag_Id = false;
					} else {
						id_Label2.setText("");
						flag_Id = true;// setting the ID flag to true
					}
					if (Objects.equals(name_Field.getText(), "")) {// check for blank Name field
						name_Label2.setText("Please enter Customer Name");
						flag_Name = false;
					} else {
						name_Label2.setText("");
						flag_Name = true;// setting the name flag to true
					}
					if (Objects.equals(phone_Field.getText(), "")) {// check for blank phone number
						phone_Label2.setText("Please enter Customer Phone");
						flag_Phone = false;
					} else {
						phone_Label2.setText("");
						flag_Phone = true;// set the phone flag to true
					}
					if (Objects.equals(email_Field.getText(), "")) {// check for blank email field
						email_Label2.setText("Please enter Customer Email");
						flag_Email = false;
					} else {
						email_Label2.setText("");
						flag_Email = true;// check the price flag to true
					}
					if (Objects.equals(postal_code_Field.getText(), "")) {// check for blank postal code
						postal_code_Label2.setText("Please enter Customer Postal code");
						flag_Postal_Code = false;
					} else {
						postal_code_Label2.setText("");
						flag_Postal_Code = true;// check the price flag to true
					}
					
					int length_phone = phone_Field.getText().length();
					int length_postal_code = postal_code_Field.getText().length();
					if (!(length_phone == 10)) {// condition to verify the length of phone number to be 10
						JOptionPane.showMessageDialog(null, "PLEASE ENTER A CORRECT PHONE NUMBER with 10 numbers");
						flag_Phone = false;
					}
					if (!(email_Field.getText().contains("@"))) {// condition to verify that email contains @
						JOptionPane.showMessageDialog(null, "PLEASE ENTER A CORRECT EMAIL ADDRESS (with @ present)");
						flag_Email = false;

					}
					if (!(length_postal_code == 6)) {// condition to verify the length of postal code is 6
						JOptionPane.showMessageDialog(null, "PLEASE ENTER A CORRECT POSTAL CODE with 6 characters");
						flag_Postal_Code = false;
					}
				
					try (DataInputStream input2 = new DataInputStream(new FileInputStream(file));) {// creating new
																									// reader to fetch
																									// ID from
																									// customer.dat
						int user_input_id = Integer.parseInt(id_Field.getText());// storing old ID in a variable
						int store_file_id = 0;
						Boolean flag_unique_id_check = false;
						while(input2.available() > 0) {  //check if there is any data to be read in file
							store_file_id = input2.read();
							if (store_file_id == user_input_id) {
								flag_unique_id_check = true;// setting the flag to true to raise a dialog box below to
															// let user know that ID is not unique
							}
							input2.readUTF();// traversing through the entire file so that Customer id is read in while
												// condition. This reads the name
							input2.readLong();// traversing through the entire file so that Customer id is read in while
												// condition. This reads the phone
							input2.readUTF();// traversing through the entire file so that Customer id is read in while
												// condition. This reads the email
							input2.readUTF();// traversing through the entire file so that Customer id is read in while
												// condition. This reads the postal code
						}
						// Condition to check unique customer id is entered by user
						if (flag_unique_id_check) {
							JOptionPane.showMessageDialog(null, "PLEASE ENTER A UNIQUE CUSTOMER ID");
							flag_Id = false;// setting the flag ID to false so that the id input becomes invalid
						}
					} catch (Exception ex) {
						System.out.println("No existing data found");

					}
					if (flag_Id && flag_Name && flag_Phone && flag_Email && flag_Postal_Code) {// Writing the records in
																								// customer.dat file

						try (DataOutputStream output = new DataOutputStream(new FileOutputStream(file, true)); // creating
																												// a
																												// output
																												// stream
																												// to
																												// write
																												// the
																												// data
								DataInputStream input = new DataInputStream(new FileInputStream(file));)// creating a
																										// input stream
																										// to read form
																										// file to
																										// display on
																										// APP
						{
							//write the new data to the file
							output.write(Integer.parseInt(id_Field.getText()));
							output.writeUTF(name_Field.getText());
							output.writeLong(Long.parseLong(phone_Field.getText()));
							output.writeUTF(email_Field.getText());
							output.writeUTF(postal_code_Field.getText());

							//load and display data after saving data
							LoadAndDisplayData();
							
						} catch (IOException ex) {
							System.out.println(ex);
						}

					}

				} catch (Exception exc) {

					JOptionPane.showMessageDialog(null, "Error!!, Opening file to write data.");
					exc.printStackTrace();
				}
			}
		});
		displayTitle = new JLabel("CUSTOMER LIST :");// setting display title
		displayTitle.setBounds(20, 280, 300, 30);
		displayTitle.setFont(new Font("Verdana", Font.BOLD, 14));
		this.add(displayTitle);
		area_Button = new JTextArea("");
		area_Button.setColumns(100);
		area_Button.setRows(8);
		area_Button.setBounds(20, 305, 700, 250);
		
		//Use JScrollPane for the textArea as there may be lots of information to be displayed
		JScrollPane scroll = new JScrollPane (area_Button);
		scroll.setBounds(20, 305, 700, 250);
	    scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		this.add(scroll);
		
		//modify button and its actionListener
		modify_Button = new JButton("MODIFY");
		modify_Button.setBounds(460, 240, 100, 30);
		modify_Button.setFont(new Font("Verdana", Font.BOLD, 14));
		modify_Button.setBackground(Color.decode("#33E6FF"));
		modify_Button.setForeground(Color.WHITE);
		this.add(modify_Button);
		//Add actionListener to modify button
		modify_Button.addActionListener(new ActionListener() {
			@Override
			/* We read the old file with existing data (customer.dat)
			 * If the input customer id matches the existing data, the new data will be written into (customer_temp.dat)
			 * If the input customer id DOES NOT match the existing data, the existing data will be written into
			 * (customer_temp.dat) instead
			 * Hence the (customer_temp.dat) will consist of the most updated list of data
			 * We then rename (customer_temp.dat) back to (customer.dat) and delete the old customer.dat file
			 */
			public void actionPerformed(ActionEvent e) {
				//writing data from old products file to new products file
				try {// writing data from customer.dat file ==> customer_temp.dat file
					
					File customer_old_file = new File("src/customer.dat");
					File customer_temp_file = new File("src/customer_temp.dat");
					
					// new code

					
					String inputID = JOptionPane.showInputDialog("Enter the Customer ID to modify");// enter the ID
					while(inputID.equals("")) {
						JOptionPane.showMessageDialog(null, "Cannot accept blank entries!");
						inputID = JOptionPane.showInputDialog("Enter the Customer ID to modify");
					}
					int searchID = Integer.parseInt(inputID);
				
					String custnameNew = JOptionPane.showInputDialog("Enter the new customer name");
					while (custnameNew.equals("")) {
					      JOptionPane.showMessageDialog(null, "Cannot accept blank entries!");
					      custnameNew = JOptionPane.showInputDialog("Enter the new customer name");
					}
					
					String custphone = JOptionPane.showInputDialog("Enter the new phone number");
					int length_phone = custphone.length();					
					while(length_phone != 10) {// condition to verify the length of phone number to be 10
						JOptionPane.showMessageDialog(null, "PLEASE ENTER A CORRECT PHONE NUMBER with 10 numbers");
						custphone = JOptionPane.showInputDialog("Enter the new phone number");
						length_phone = custphone.length();
					}
					long custphoneNew = Long.parseLong(custphone);
					
					
					
					String emailcustNew = JOptionPane.showInputDialog("Enter the new Email address");
					while((!(emailcustNew.contains("@")))) {// condition to verify that email contains @
								JOptionPane.showMessageDialog(null, "PLEASE ENTER A CORRECT EMAIL ADDRESS (with @ present)");
								emailcustNew = JOptionPane.showInputDialog("Enter the new Email address");
					}
					
					String custpostalcode = JOptionPane.showInputDialog("Enter the new Postal code");
					int length_postal_code = custpostalcode.length();
					while ((!(length_postal_code == 6))) {// condition to verify the length of postal code is 6
								JOptionPane.showMessageDialog(null, "PLEASE ENTER A CORRECT POSTAL CODE with 6 characters");
								custpostalcode = JOptionPane.showInputDialog("Enter the new Postal code");
								length_postal_code = custpostalcode.length();
					}


					

					try (DataInputStream inputsearch = new DataInputStream(new FileInputStream(customer_old_file)); // creating
																													// reader
																													// for
																													// customer.dat
																													// file
							DataOutputStream outputTemp = new DataOutputStream(
									new FileOutputStream(customer_temp_file, true));// creating writer for
																					// customer_temp.dat file
					) {
												
						int store_file_id = 0;
						//check if any data to be read in the customer.dat file
						while (inputsearch.available() > 0) {
							store_file_id = inputsearch.read();     //read the next data and store it to store_file_id
							if (store_file_id == searchID) {
								outputTemp.write(store_file_id);// writing data in customer_temp.dat
								outputTemp.writeUTF(custnameNew);// writing data in customer_temp.dat
								outputTemp.writeLong(custphoneNew);// writing data in customer_temp.dat
								outputTemp.writeUTF(emailcustNew);// writing data in customer_temp.dat
								outputTemp.writeUTF(custpostalcode);// writing data in customer_temp.dat
								inputsearch.readUTF();// consuming the data from customer.dat file so that inputsearch
														// reader moves to name field
								inputsearch.readLong();// consuming the data from customer.dat file so that inputsearch
														// reader moves to phone field
								inputsearch.readUTF();// consuming the data from customer.dat file so that inputsearch
														// reader moves to email field
								inputsearch.readUTF();// consuming the data from customer.dat file so that inputsearch
														// reader moves to postal code field
							} else {
								outputTemp.write(store_file_id);// writing not modified data in customer_temp.dat
								outputTemp.writeUTF(inputsearch.readUTF());// writing not modified data in
																			// customer_temp.dat
								outputTemp.writeLong(inputsearch.readLong());// writing not modified data in
																				// customer_temp.dat
								outputTemp.writeUTF(inputsearch.readUTF());// writing not modified data in
																			// customer_temp.dat
								outputTemp.writeUTF(inputsearch.readUTF());// writing not modified data in
																				// customer_temp.dat
							}
						}
						inputsearch.close();
						outputTemp.close();
						
						//delete customer.dat which contains NOT updated data
						customer_old_file.delete();
						
						File customer_new_file_fresh = new File("src/customer.dat");// creating a new instance of
																				// customer.dat

						customer_temp_file.renameTo(customer_new_file_fresh); //renaming customer_temp.file to customer.dat
						//load and display updated data
						LoadAndDisplayData();
						
						 
					}catch (IOException ex) {
						System.out.println(ex);
						}
					
					
				} catch (Exception exc) {
					JOptionPane.showMessageDialog(null, "Error!!, Opening file to read data.");
					exc.printStackTrace();
				}
				
			}
		});
		
		//load and display data when the application is initiated
		LoadAndDisplayData();
	}
	public void LoadAndDisplayData() {
		File customer_new_file_fresh = new File("src/customer.dat");
		
		// code to read the updated data and display it to the textArea
		try (DataInputStream readfromnew = new DataInputStream(
				new FileInputStream(customer_new_file_fresh));) {
			// Display data
			// Index 1 is for ID
			// Index 2 is for NAME
			// Index 3 is for Phone Number
			// Index 4 is for EMail
			// Index 5 is for Postal COde
			int index = 1;
			String display = "";
			int ans = 0;
			while (readfromnew.available() > 0) {
				ans = readfromnew.read();
				if (index == 1) {
					display += "ID: " + ans + "";
					display += "\n";
					++index;
				}
				if (index == 2) {
					display += "Name: " + readfromnew.readUTF();
					display += "\n";
					++index;
				}
				if (index == 3) {
					display += "Phone No: " + readfromnew.readLong();
					display += "\n";
					++index;
				}
				if (index == 4) {
					display += "Email: " + readfromnew.readUTF();
					display += "\n";
					++index;
				}
				if (index == 5) {
					display += "Postal Code: " + readfromnew.readUTF();
					display += "\n";
					++index;
				}
					//add a blank line for read-friendly display
					display += "\n";
					
				//Resetting index to 1
				index = 1;
			}

			area_Button.setText(display);
			readfromnew.close();
			
		} catch(Exception ex) {
			
		}
	}
}
