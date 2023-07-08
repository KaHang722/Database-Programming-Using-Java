

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import javax.swing.*;

public class Application extends JFrame{
	private static final int FRAME_WIDTH = 800;
	private static final int FRAME_HEIGHT = 600;
	
	private JLabel dateLabel;
	private JLabel cityLabel;
	private JLabel noPfizer;
	private JLabel noModerna;
	private JLabel noAstraZeneca;
	
	private JTextField txtDate;
	private JTextField txtCity;
	private JTextField txtNoPfizer;
	private JTextField txtNoModerna;
	private JTextField txtNoAstraZeneca;
	
	private JButton btnSave;
	private JButton btnClear;
	private JButton btnFind;
	
	private JTextArea txtAreaDisplay;
	
	private JPanel inputPanel;
	private JPanel btnPanel;
	private JPanel findDisplayPanel;
	private JPanel mainPanel;
	private JPanel displayItemPanel;
	private JPanel middlePanel;
	
	private String city;
	private String date;
	private String pfizer;
	private String moderna;
	private String astraZeneca;
	private int countPfizerC = 0;
	private int countModernaC = 0;
	private int countAstraZenecaC = 0;
	private int countPfizerD = 0;
	private int countModernaD = 0;
	private int countAstraZenecaD = 0;
	private int countPfizer = 0;
	private int countModerna = 0;
	private int countAstraZeneca = 0;
	private int countPfizerCD = 0;
	private int countModernaCD = 0;
	private int countAstraZenecaCD = 0;
	
	ArrayList<String> data;
	
	public Application() {
		setSize(FRAME_WIDTH,FRAME_HEIGHT);
		
		CreateInputPanel();
		
		CreateBtnPanel();
		
		CreateFindDisplayPanel();
		
		CreateDisplayItemPanel();
		
		CreateLayout();
		
	}
	
	public void CreateInputPanel() {
		
		//Create labels and textfields
		dateLabel = new JLabel("Date of vaccination: (Please enter in MM/DD/YYYY format)");
		txtDate = new JTextField("");
		cityLabel = new JLabel("Name of city: ");
		txtCity = new JTextField();
		noPfizer = new JLabel("Number of Pfizer vaccine doses administrated: ");
		txtNoPfizer = new JTextField();
		noModerna = new JLabel("Number of Moderna vaccine doses administrated: ");
		txtNoModerna = new JTextField();
		noAstraZeneca = new JLabel("Number of AstraZeneca vaccine doses administrated: ");
		txtNoAstraZeneca = new JTextField();
		
		//Create a panel and add all labels and textfield into it
		inputPanel = new JPanel();
		inputPanel.setLayout(new GridLayout(5,2));  //set gridLayout
		inputPanel.add(dateLabel);
		inputPanel.add(txtDate);
		inputPanel.add(cityLabel);
		inputPanel.add(txtCity);
		inputPanel.add(noPfizer);
		inputPanel.add(txtNoPfizer);
		inputPanel.add(noModerna);
		inputPanel.add(txtNoModerna);
		inputPanel.add(noAstraZeneca);
		inputPanel.add(txtNoAstraZeneca);
		
	}
	
	//Create button panel with two buttons "Save" and "Clear"
	public void CreateBtnPanel() {
		btnSave = new JButton("Save");
		btnClear = new JButton("Clear");
		
		btnPanel = new JPanel();
		btnPanel.add(btnSave);
		btnPanel.add(btnClear);
		
		//click event on btnSave
		class BtnSaveListener implements ActionListener{
			public void actionPerformed(ActionEvent ae) {
				String date = txtDate.getText();
				//check format of date input
				if (!date.matches("[0-1][0-9]/[0-3][0-9]/[1-2][0-9][0-9][0-9]")) {
					JOptionPane.showMessageDialog(null, "Please input a valid date");
					return;
				};
				//check NotNull on name of city
				String name = txtCity.getText();
				if (name == "") {
					JOptionPane.showMessageDialog(null, "Name of city should not be blank");
					return;
				}
				//check input should be a positive integer
				String pfizer = txtNoPfizer.getText();
				if (!(CheckInteger(pfizer) && Integer.parseInt(pfizer) >= 0)) {
					JOptionPane.showMessageDialog(null, "Please input a valid integer for number of Pfizer vaccine doses");
					return;
				}
				//check input should be a positive integer
				String moderna = txtNoModerna.getText();
				if (!(CheckInteger(moderna) && Integer.parseInt(moderna) >= 0)) {
					JOptionPane.showMessageDialog(null, "Please input a valid integer for number of Moderna vaccine doses");
					return;
				}
				//check input should be a positive integer
				String astraZeneca = txtNoAstraZeneca.getText();
				if (!(CheckInteger(astraZeneca) && Integer.parseInt(astraZeneca) >= 0)) {
					JOptionPane.showMessageDialog(null, "Please input a valid integer for number of AstraZeneca vaccine doses");
					return;
				}
				//Try to save the new record into a new file "src/records.txt"
				File file = new File("src/records.txt");
				try{
					/*
                    Use the Scanner to read all the existing data from the text file first
                    and save all the existing data into the data variable which is a ArrayList of String type
					 */
					Scanner reader = new Scanner(file);
					if (reader.hasNextLine()) {
						String s = reader.nextLine();
						String replace = s.replace("[","");
						String replace1 = replace.replace("]","");
						data = new ArrayList<>(Arrays.asList(replace1.split(",")));
					} else {
						// If there is no existing data, just create a new empty ArrayList of String Type
						data = new ArrayList<>();
					}
					reader.close();
					JOptionPane.showMessageDialog(null, "Record saved!");
					// Save the new records as a String with values separated by a hyphen character
					String value = name + "-" + date + "-" + pfizer + "-" + moderna + "-" + astraZeneca;
					data.add(value);
					
					PrintWriter output = new PrintWriter(file);
					output.println(data);
					output.close();
					
				} catch (Exception ex) {
					// Output the Exception Message
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
				
			}
		}
		//create an ActionListener object and register it to btnSave
		ActionListener listener = new BtnSaveListener();
		btnSave.addActionListener(listener);
		
		//click event for btnClear
		class btnClearListener implements ActionListener{
			public void actionPerformed(ActionEvent ae) {
				txtDate.setText("");
				txtCity.setText("");
				txtNoPfizer.setText("");
				txtNoModerna.setText("");
				txtNoAstraZeneca.setText("");
				txtAreaDisplay.setText("");
			}
		}
		//create an ActionListener object and register it to btnSave
		ActionListener listener1 = new btnClearListener();
		btnClear.addActionListener(listener1);
	}
	
	//check if the input is integer
	public boolean CheckInteger(String s) {
		try {
			//try parsing the input string. If success, no exception will be catched
			Integer.parseInt(s);
		} catch(Exception ex) {
			return false;
		}
		return true;
	}
	
	public void CreateFindDisplayPanel() {
		btnFind = new JButton("Find");
		
		class btnFindListener implements ActionListener{

			public void actionPerformed(ActionEvent e) {
				File file = new File("src/records.txt");
				try{
					/*
                    Use the Scanner to read all the existing data from the text file first
                    and save all the existing data into the data variable which is a ArrayList of String type
					*/
					Scanner reader = new Scanner(file);
					if (reader.hasNextLine()) {
						String s = reader.nextLine();
						String replace = s.replace("[","");
						String replace1 = replace.replace("]","");
						data = new ArrayList<>(Arrays.asList(replace1.split(",")));
						
						//clear all count to 0 at the beginning (avoid multiple counting)
						countPfizerC = 0;
						countModernaC = 0;
						countAstraZenecaC = 0;
						countPfizerD = 0;
						countModernaD = 0;
						countAstraZenecaD = 0;
						countPfizer = 0;
						countModerna = 0;
						countAstraZeneca = 0;
						countPfizerCD = 0;
						countModernaCD = 0;
						countAstraZenecaCD = 0;
						
						for (int i = 0; i < data.size(); i++) {
							//each info (name of city, date etc) is seperated with a hyphen, so we split to get respective info
							String[] s1 = data.get(i).split("-");
							city = s1[0].trim();
							date = s1[1];
							pfizer = s1[2];
							moderna = s1[3];
							astraZeneca = s1[4];
							//count sum of total count of corresponding vaccine
							countPfizer += Integer.parseInt(pfizer);
							countModerna += Integer.parseInt(moderna);
							countAstraZeneca += Integer.parseInt(astraZeneca);
							
							//Count of each vaccine doses count for a certain city ONLY
							if(city.equals(txtCity.getText())){
								
								countPfizerC += Integer.parseInt(pfizer);
								countModernaC += Integer.parseInt(moderna);
								countAstraZenecaC += Integer.parseInt(astraZeneca);
							}
							
							//Count of each vaccine doses count for a certain date ONLY
							if(date.equals(txtDate.getText())){
								
								countPfizerD += Integer.parseInt(pfizer);
								countModernaD += Integer.parseInt(moderna);
								countAstraZenecaD += Integer.parseInt(astraZeneca);
							}
							
							//Count of each vaccine for a certain city AND date
							if(city.equals(txtCity.getText()) && date.equals(txtDate.getText())) {
								
								countPfizerCD += Integer.parseInt(pfizer);
								countModernaCD += Integer.parseInt(moderna);
								countAstraZenecaCD += Integer.parseInt(astraZeneca);
							}

						}
						
						/*If input is city AND date, then btnFind will display vaccination records 
						of a given city AND date along with the total of each type of vaccine.*/
						if(!txtCity.getText().isBlank() && !txtDate.getText().isBlank() 
								&& txtNoPfizer.getText().isBlank() && txtNoModerna.getText().isBlank()
								&& txtNoAstraZeneca.getText().isBlank()) {
							txtAreaDisplay.setText("Date = " + txtDate.getText() + "\n"
													+ "City = " + txtCity.getText() + "\n"
													+ "Total count of Pfizer doses = " + countPfizerCD + "\n"
													+ "Total count of Moderna doses = " + countModernaCD + "\n"
										            + "Total count of AstraZeneca doses = " + countAstraZenecaCD + "\n");
						}
						
						/*If only input is city, then btnFind will display vaccination records 
							of a given city along with the total of each type of vaccine.*/
						if(!txtCity.getText().isBlank() && txtDate.getText().isBlank() 
								&& txtNoPfizer.getText().isBlank() && txtNoModerna.getText().isBlank()
								&& txtNoAstraZeneca.getText().isBlank()) {
							txtAreaDisplay.setText("City = " + txtCity.getText() + "\n"
													+ "Total count of Pfizer doses = " + countPfizerC + "\n"
													+ "Total count of Moderna doses = " + countModernaC + "\n"
										            + "Total count of AstraZeneca doses = " + countAstraZenecaC + "\n");
						}
						
						/*If only input is date, then btnFind will display vaccination records 
						of a given date along with the total of each type of vaccine.*/
						if(txtCity.getText().isBlank() && !txtDate.getText().isBlank() 
								&& txtNoPfizer.getText().isBlank() && txtNoModerna.getText().isBlank()
								&& txtNoAstraZeneca.getText().isBlank()) {
							txtAreaDisplay.setText("Date = " + txtDate.getText() + "\n"
													+ "Total count of Pfizer doses = " + countPfizerD + "\n"
													+ "Total count of Moderna doses = " + countModernaD + "\n"
										            + "Total count of AstraZeneca doses = " + countAstraZenecaD + "\n");
						}
						
						/*For no input, then btnFind will display vaccination records 
						with the total of each type of vaccine.*/
						if(txtCity.getText().isBlank() && txtDate.getText().isBlank() 
								&& txtNoPfizer.getText().isBlank() && txtNoModerna.getText().isBlank()
								&& txtNoAstraZeneca.getText().isBlank()) {
							txtAreaDisplay.setText("Total Number of doses administrated: " + "\n"
													+ "Total count of Pfizer doses = " + countPfizer + "\n"
													+ "Total count of Moderna doses = " + countModerna + "\n"
										            + "Total count of AstraZeneca doses = " + countAstraZeneca + "\n");
						}
						
					} else {
						JOptionPane.showMessageDialog(null, "No records found");
					}
					reader.close();
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		}
		ActionListener listener = new btnFindListener();
		btnFind.addActionListener(listener);
		
		findDisplayPanel = new JPanel();
		findDisplayPanel.add(btnFind);

	}
	
	//Creating text Area and add it to a panel
	public void CreateDisplayItemPanel() {
		txtAreaDisplay = new JTextArea(20,30);
		txtAreaDisplay.setEditable(false);
		
		displayItemPanel = new JPanel();
		displayItemPanel.add(txtAreaDisplay);
	}
	
	//Overall layout of the application
	public void CreateLayout() {
		
		mainPanel = new JPanel();
		mainPanel.add(inputPanel);
		middlePanel = new JPanel();
		middlePanel.add(btnPanel);
		middlePanel.add(findDisplayPanel);
		
		//main: labels and textboxes; middle: btnSave, btnClear and btnFind; displayItem: textAreaDisplay
		add(mainPanel, BorderLayout.NORTH);
		add(middlePanel, BorderLayout.CENTER);
		add(displayItemPanel, BorderLayout.SOUTH);

	}
	
	
}
