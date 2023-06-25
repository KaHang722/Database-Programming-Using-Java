package assignment1;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

public class Management extends JFrame{
	private static final int FRAME_WIDTH = 600;
	private static final int FRAME_HEIGHT = 300;
	
	// Variables declaration
	private String name;
	private String gender;
	private int semester;
	private ArrayList <String> courses;
	
	private ActionListener listener;
	
	private JPanel mainPanel;
	private JPanel centerPanel;
	
	private JPanel studentPanel;
	private JPanel genderPanel;
	private JPanel semesterPanel;
	private JPanel coursesPanel;
	private JPanel radioButtonPanel;
	
	private JLabel displayLabel;
	private JLabel studentNameLabel;
	private JLabel genderLabel;
	private JLabel semesterLabel;
	private JLabel coursesLabel;
	
	private JTextField studentNameField;
	
	private JRadioButton maleButton;
	private JRadioButton femaleButton;
	
	private JSpinner semesterSpinner;
	
	private JCheckBox javaCheckBox;
	private JCheckBox databaseCheckBox;
	private JCheckBox webProgrammingCheckBox;
	
	private JButton saveButton;
	
	public Management() {
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		
		class semesterValueListener implements ActionListener{
			public void actionPerformed (ActionEvent ae) {
				displaySemesterValue();
				}
			}

		listener = new semesterValueListener();
		
		//TextField
		createTextField();
				
		//radio buttons
		createRadioButton();
		
		//Spinner
		createSpinner();
		
		//checkboxes
		createCheckBox();
		
		//design layout
		createLayout();
		
		setDefaultCloseOperation(3);
		setTitle("SMS Management");
		setVisible(true);
	}
	
	//create student Panel and add student name label and student name field in it
	public void createTextField() {
		studentNameLabel = new JLabel("Student Name");
		studentNameField = new JTextField(10);

		studentPanel = new JPanel();
		studentPanel.setLayout(new GridLayout(1,3,20,10));
		studentPanel.add(studentNameLabel);
		studentPanel.add(studentNameField);
		studentPanel.add(new JLabel(""));
	}
	
	//create gender panel and add gender label and radio buttons in it
	public void createRadioButton() {
		maleButton = new JRadioButton("Male");
		femaleButton = new JRadioButton("Female");
		
		ButtonGroup group = new ButtonGroup();
		group.add(maleButton);
		group.add(femaleButton);
		
		radioButtonPanel = new JPanel();
		radioButtonPanel.setLayout(new GridLayout(1,2));
		radioButtonPanel.add(maleButton);
		radioButtonPanel.add(femaleButton);
		
		genderLabel = new JLabel("Gender");
		
		genderPanel = new JPanel();
		genderPanel.setLayout(new GridLayout(1,3,20,10));
		genderPanel.add(genderLabel);
		genderPanel.add(radioButtonPanel);
		genderPanel.add(new JLabel(""));
	}
	
	//create semester panel and add semester label and spinner in it 
	public void createSpinner() {
		SpinnerModel model = new SpinnerNumberModel();
		semesterSpinner = new JSpinner(model);
		
		semesterLabel = new JLabel("Semester");
		semesterPanel = new JPanel();
		
		BoxLayout box1layout = new BoxLayout(semesterPanel, BoxLayout.X_AXIS);
		semesterPanel.setLayout(box1layout);

		semesterPanel.add(semesterLabel);
		semesterPanel.add(Box.createRigidArea(new Dimension(95, 0)));
        semesterPanel.add(semesterSpinner);
		semesterPanel.add(Box.createRigidArea(new Dimension(220, 0)));
	}
	
	//create courses panel and add courses label, check boxes and save button in it
	public void createCheckBox() {
		javaCheckBox = new JCheckBox ("Java");
		databaseCheckBox = new JCheckBox ("Database");
		webProgrammingCheckBox = new JCheckBox ("Web Programming");
		
		saveButton = new JButton ("Save");
		saveButton.addActionListener(listener);
		
		coursesLabel = new JLabel("Courses");
		
		coursesPanel = new JPanel();
		coursesPanel.setLayout(new GridLayout(3,3,15,10));
		coursesPanel.add(coursesLabel);
		coursesPanel.add(javaCheckBox);
		coursesPanel.add(new JLabel(""));
		coursesPanel.add(new JLabel(""));
		coursesPanel.add(databaseCheckBox);
		coursesPanel.add(new JLabel(""));
		coursesPanel.add(new JLabel(""));
		coursesPanel.add(webProgrammingCheckBox);
		coursesPanel.add(saveButton);
	}
	
	//create the layout for the management frame and add the above panels and center panel in it
	public void createLayout() {
		displayLabel = new JLabel("");
		
		centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(3,1));
		centerPanel.add(studentPanel);
		centerPanel.add(genderPanel);
		centerPanel.add(semesterPanel);
		
		mainPanel= new JPanel();
		mainPanel.add(displayLabel, BorderLayout.NORTH);
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		mainPanel.add(coursesPanel, BorderLayout.SOUTH);
		
		add(mainPanel, BorderLayout.CENTER);		
	}
	
	//create a method to display semester value when the save button is enable
	public void displaySemesterValue() {
		name=null;
		gender=null;
		semester=0;
		courses = new ArrayList<String>();
		
		if (saveButton.isEnabled()) {
			name = studentNameField.getText();
			semester = (int)semesterSpinner.getValue();
			
			if (maleButton.isSelected()) {
				gender = maleButton.getText();
			}
			if (femaleButton.isSelected()) {
				gender = femaleButton.getText();
			}			
			if (javaCheckBox.isSelected()) {
				courses.add(javaCheckBox.getText());
			}
			if (databaseCheckBox.isSelected()) {
				courses.add(databaseCheckBox.getText());
			}
			if (webProgrammingCheckBox.isSelected()) {
				courses.add(webProgrammingCheckBox.getText());
			}
			displayLabel.setText("Student Name: " + name + ", Gender: "+ gender+", Semester: "+semester +", "+ toString());
		}
	}
	
	//To String method to display the courses arraylist
	@Override
	public String toString() {
		return "courses: " + courses;
	}
}
