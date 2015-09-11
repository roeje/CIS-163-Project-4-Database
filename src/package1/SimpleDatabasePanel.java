package package1;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTextPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.JTextComponent;
import java.awt.Font;
import java.io.File;

/**********************************************************************
 * CIS 163
 * @author bensonb and Jesse Roe
 * A database panel to display the current database.
 *********************************************************************/
public class SimpleDatabasePanel extends JPanel 
								implements ActionListener{	

	private static final long serialVersionUID = 8114526294890424119L;

	/**SimpleDatabase for Panel**/
	private SimpleDatabase gvDatabase;
	
	/**The first name field**/
	private JTextField firstNameField;
	
	/**The last name of the student**/
	private JTextField lastNameField;
	
	/**The gNumber field to retrieve a students gpa**/
	private JTextField gNumberText;
	
	/**The gpa of the student**/
	private JTextField txtGPA;
	
	/**a text field to find person based on their name.**/
	private JTextField findPerson;
	
	/**a text field to remove a student based on their name.**/
	private JTextField txtRemove;
	
	/**The button to save serializable**/
	private JButton btnSave;
	
	/**the button to load from a serializable file**/
	private JButton btnLoad;
	
	/**The button toe display the text on the panel**/
	private JButton btnDisplay;
	
	/**the button to remove duplicates**/
	private JButton btnDuplicate;
	
	/**The button to reverse the list.**/
	private JButton btnReverse;
	
	/**The Button to sort the list of people**/
	private JButton btnSort;
	
	/**The button to insert some fellow Students**/
	/**Welcome to GV new peeps**/
	private JButton btnInsert;
	
	/**Button to find the person in the txtFind Field**/
	private JButton btnFind;
	
	/**Button to find the person you want to remove**/
	private JButton btnRemove;
	
	/**The display area of the GUI **/
	/**Will display the current status of the DataBase**/
	private JTextPane databaseDisplay;
	
	/**Enables the Quit menu option**/
	private JMenuItem menuItemQuit;
	
	/**Enables the Update button functionality**/
	private JButton btnUpdate;
	
	/**Undo button**/
	private JButton btnUndo;
	
	/******************************************************************
	 * Create the panel.
	 *****************************************************************/
	public SimpleDatabasePanel() {
		gvDatabase = new SimpleDatabase();
		
		setLayout(new BorderLayout(0, 0));
		
		JPanel northPanel = new JPanel();
		add(northPanel, BorderLayout.NORTH);
		
		JLabel lbFirstName = new JLabel("Last Name: ");
		northPanel.add(lbFirstName);
		
		lastNameField = new JTextField();
		lastNameField.setText("Benjamin");
		northPanel.add(lastNameField);
		lastNameField.setColumns(10);
		
		JLabel lblName = new JLabel("First Name:");
		northPanel.add(lblName);
		
		firstNameField = new JTextField();
		firstNameField.setText("Ben");
		northPanel.add(firstNameField);
		firstNameField.setColumns(10);
		
		JLabel lbGNumber = new JLabel("G#");
		northPanel.add(lbGNumber);
		
		gNumberText = new JTextField();
		gNumberText.setText("01081456");
		northPanel.add(gNumberText);
		gNumberText.setColumns(10);
		
		JLabel lbGpa = new JLabel("GPA: ");
		northPanel.add(lbGpa);
		
		txtGPA = new JTextField();
		txtGPA.setText("3.25");
		northPanel.add(txtGPA);
		txtGPA.setColumns(10);
		
		JPanel eastButtonPanel = new JPanel();
		add(eastButtonPanel, BorderLayout.EAST);
		eastButtonPanel.setLayout(new GridLayout(0, 1, 10, 10));
		
		btnSave = new JButton("Save");
		btnSave.addActionListener(this);
		eastButtonPanel.add(btnSave);
		
		btnLoad = new JButton("Load");
		btnLoad.addActionListener(this);
		eastButtonPanel.add(btnLoad);
		
		btnDisplay = new JButton("Display");
		btnDisplay.addActionListener(this);
		eastButtonPanel.add(btnDisplay);
		
		btnDuplicate = new JButton("duplicate");
		btnDuplicate.addActionListener(this);
		eastButtonPanel.add(btnDuplicate);
		
		btnReverse = new JButton("Reverse");
		btnReverse.addActionListener(this);
		eastButtonPanel.add(btnReverse);
		
		btnSort = new JButton("Sort");
		btnSort.addActionListener(this);
		eastButtonPanel.add(btnSort);
		
		btnInsert = new JButton("Insert");
		btnInsert.addActionListener(this);
		eastButtonPanel.add(btnInsert);
		
		btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(this);
		eastButtonPanel.add(btnUpdate);
		
		btnUndo = new JButton("Undo");
		btnUndo.addActionListener(this);
		eastButtonPanel.add(btnUndo);
		
		JPanel findPanel = new JPanel();
		eastButtonPanel.add(findPanel);
		findPanel.setLayout(new BorderLayout(0, 0));
		
		btnFind = new JButton("Find");
		btnFind.addActionListener(this);
		findPanel.add(btnFind, BorderLayout.SOUTH);
		
		findPerson = new JTextField();
		findPerson.setText("G#");
		findPanel.add(findPerson, BorderLayout.CENTER);
		findPerson.setColumns(10);
		
		JLabel lblNameOfPerson = new JLabel("GNumber:");
		findPanel.add(lblNameOfPerson, BorderLayout.NORTH);
		
		JPanel removePanel = new JPanel();
		eastButtonPanel.add(removePanel);
		removePanel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblName2 = new JLabel("GNumber:");
		removePanel.add(lblName2, BorderLayout.NORTH);
		
		txtRemove = new JTextField();
		txtRemove.setText("G#");
		removePanel.add(txtRemove, BorderLayout.CENTER);
		txtRemove.setColumns(10);
		
		btnRemove = new JButton("Remove");
		btnRemove.addActionListener(this);
		removePanel.add(btnRemove, BorderLayout.SOUTH);
		
		JPanel databasePanel = new JPanel();
		add(databasePanel, BorderLayout.CENTER);
		databasePanel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		databasePanel.add(scrollPane, BorderLayout.CENTER);
		
		databaseDisplay = new JTextPane();
		databaseDisplay.setFont(new Font("Times New Roman"
					, Font.PLAIN, 18));
		databaseDisplay.setEditable(true);
		databaseDisplay.setText("Text Area");
		scrollPane.setViewportView(databaseDisplay);
		
		JMenuBar menuBar = new JMenuBar();
		scrollPane.setColumnHeaderView(menuBar);
		
		JMenu menuDropDown = new JMenu("Menu");
		menuBar.add(menuDropDown);
		
		menuItemQuit = new JMenuItem("Quit");
		menuItemQuit.addActionListener(this);
		menuDropDown.add(menuItemQuit);
	}
	
	/******************************************************************
	 * Grabs the text from the text areas, and gets rid of any extra
	 * spaces within the String.
	 * @param pressed
	 * The text area you wish to parse.
	 * @return
	 * A string of the text area.
	 *****************************************************************/
	private String textGetter(JComponent pressed){
		String info = "";
		
		// type casting prevent any funny business
		if(pressed instanceof JTextField){
			
			// don't just trim the text. prevent spacing at 
			// the begining of the text.
			info += ((JTextComponent)pressed).
					getText().replaceAll("\\s","");
		}
		return info;
	}
	
	/******************************************************************
	 * Creates a student based on the text fields.
	 * @return
	 * The student who was created. 
	 *****************************************************************/
	private Student addStudent(){
		Double gpa = 1.0;
		try{
			gpa = Double.parseDouble(textGetter(txtGPA));
		}catch(NumberFormatException ex){
			JOptionPane.showMessageDialog(null, 
			"Check the student's GPA, it appears "
			+ "to have foreign characters.",null
					,JOptionPane.ERROR_MESSAGE);
			return null;
		}
		Student temp = null;
		try{
			temp = new Student(textGetter(lastNameField),
					textGetter(firstNameField), 
						textGetter(gNumberText),gpa);
		}catch(IllegalArgumentException ex){
			JOptionPane.showMessageDialog(null, 
			"Check the student's GPA, it appears "
			+ "to be above or below a reasonable number.", null
					,JOptionPane.ERROR_MESSAGE);
			return null;
		}
		return temp;
	}
	
	/******************************************************************
	 * Determines the path of the file you're trying to save.
	 *****************************************************************/
	private void saveFilePath(){
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setApproveButtonText("Save");
		fileChooser.setCurrentDirectory(new File
				("//home//bensonb//workspace//"));
		int returnValue = fileChooser.showOpenDialog(null);

		File save;
     
		if(returnValue == JFileChooser.APPROVE_OPTION){
			save = fileChooser.getSelectedFile();
			if(!save.getName().substring(save.getName().
					length()-4).equals(".ser")) {
				gvDatabase.saveDB(
						save.getAbsolutePath()+".ser");
			} else{
			gvDatabase.saveDB(save.getAbsolutePath());
		}
		}
	}
	
	/******************************************************************
	 * Gets the absolute file path of the file you're saving.
	 *****************************************************************/
	private void getFilePath(){
		
		JFileChooser fileChooser = new JFileChooser();
		FileNameExtensionFilter filter = new 
				FileNameExtensionFilter(
		        "Serializable Files", "ser");
		fileChooser.setCurrentDirectory(new File
				("//home//bensonb//workspace"));
		fileChooser.setFileFilter(filter);
		int returnValue = fileChooser.showOpenDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();

			try {
				gvDatabase.loadDB(selectedFile
						.getAbsolutePath());
			} catch (Exception ex) {
				JOptionPane.showInternalMessageDialog(null, 
						"File not found.", "IOException", 
						ERROR);
			}
		}
		return;
	}
	
	/******************************************************************
	 * Takes the existing string and saves the file based on that.
	 * @param text
	 * The text that's on the database panel.
	 *****************************************************************/
	private void append(String text){
		databaseDisplay.setText(databaseDisplay.getText()+text);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JComponent pressed = (JComponent)e.getSource();
		//Testing the text getter method
		if(pressed == menuItemQuit){
			System.exit(0);
		}
		if(pressed == btnRemove){
		boolean removed = gvDatabase.delete(textGetter(txtRemove));
			if(removed){
			}else{
				return;
			}
		}else if(pressed == btnFind){
			if(gvDatabase.find(textGetter(findPerson)) == null){
				append("No Students found with that GNumber\n");
				return;
			}
			try{
				append("found: "+(gvDatabase.
						find(textGetter(findPerson))
							.toString()+"\n"));
			}catch(IllegalArgumentException ex){
				JOptionPane.showMessageDialog(null,
					"Sorry the GNumber you tried to find is invalid.",
					"Whoops!",JOptionPane.ERROR_MESSAGE);
			}
		}else if(pressed == btnInsert){
			
			Student student = addStudent();
			if(student != null){
				gvDatabase.insert(student);
			}
			return;
		}else if(pressed == btnDisplay){
			
			append(gvDatabase.toString());
			
		}else if(pressed == btnDuplicate){
			
			gvDatabase.removeDuplicates();
			
		}else if(pressed == btnSave){
			
			saveFilePath();
			
		}else if(pressed == btnLoad){
			
			getFilePath();
			
		}else if(pressed == btnReverse){
			
			gvDatabase.reverseList();
			
		}else if(pressed == btnUpdate){
			gvDatabase.update(textGetter(gNumberText), 
					addStudent());
		}else if(pressed == btnUndo){
			gvDatabase.undo();
		}else if(pressed == btnSort){
			gvDatabase.Sort();
		}
	}
}
