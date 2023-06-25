package assignment1;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class Application extends JFrame{
	private static final int FRAME_WIDTH = 600;
	private static final int FRAME_HEIGHT = 300;
	
	public JLabel displayLabel;
	public JButton btnOK;
	
	public Application(){
		setSize(FRAME_WIDTH,FRAME_HEIGHT);
		
		//Create displayLabel (align centre and placed in the centre)
		displayLabel = new JLabel("SMS application", SwingConstants.CENTER);
		add(displayLabel, BorderLayout.CENTER);
		
		//Create MenuBar
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		//Add menu-creating functions into the menuBar 
		menuBar.add(createFileMenu());
		menuBar.add(createStudentMenu());
		menuBar.add(createHelpMenu());
		
		//Create OK button and add it to the south
		btnOK = new JButton("OK");
		add(btnOK, BorderLayout.SOUTH);
		
	}
	
		//create File menu with Mnemonic on 'F'
	public JMenu createFileMenu() {
		JMenu menuFile = new JMenu("File");
		menuFile.setMnemonic('F');
		return menuFile;
	}
	
		//create Student menu with Mnemonic on 'S' and add two items in it
	public JMenu createStudentMenu() {
		JMenu menuStudent = new JMenu("Student");
		menuStudent.setMnemonic('S');
		menuStudent.add(createStudentMenuItem("Manage"));
		menuStudent.add(createStudentMenuItem("Search"));
		return menuStudent;
	}
	
		//create Help Menu
	public JMenu createHelpMenu() {
		JMenu menuHelp = new JMenu("Help");
		return menuHelp;
	}
	
		//create Student MenuItems
	public JMenuItem createStudentMenuItem(String item) {
		JMenuItem menuItemManage = new JMenuItem(item);
		class MenuItemListener implements ActionListener{
			public void actionPerformed (ActionEvent event)
			{
				Management m = new Management();
			} 
		}
		ActionListener listener = new MenuItemListener();
		menuItemManage.addActionListener(listener);
		
		return (menuItemManage);
	}
}
