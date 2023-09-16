import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JFileChooser;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.image.BufferedImage;

import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class AddEditPatientWindow extends Window {

	private JPanel contentPane;
	
	//text fields
	private JTextField textFieldFirstName;
	private JTextField textFieldLastName;
	
	//spinners
	private JSpinner spinnerMonth;
	private JSpinner spinnerDay;
	private JSpinner spinnerYear;
	
	//combo boxes
	private JComboBox<String> comboBoxProcedure;
	private JComboBox<String> comboBoxSex;
	private JComboBox<String> comboBoxMaterial;
	private JComboBox<String> comboBoxBonding;
	private JComboBox<String> comboBoxColor;
	
	//buttons
	private JButton btnSelectTeeth;
	private JButton btnSelectPhoto;
	private JButton btnConfirm;
	private JButton btnCancel;

	//labels
	private JLabel lblTitle;
	private JLabel lblName;
	private JLabel lblSex;
	private JLabel lblDOB;
	private JLabel lblProcedure;
	private JLabel lblServiceTeeth;
	private JLabel lblMaterial;
	private JLabel lblBonding;
	private JLabel lblColor;
	private JLabel lblPhoto;
	private JLabel lblNotesTitle;
	private JLabel lblPhotoImage;
	
	//extra windows
	private TeethSelectionWindow teethWindow;
	private JFileChooser photoChooser;
	
	//data
	private Patient myPatient;
	private boolean isAdding;
	private File fileToDelete;
	
	//backgrounds
	private JTextField infoBackground;
	private JTextArea textAreaNotes;
	private JTextArea titleBackground;
	private JTextField photoBackground;
	private JTextArea photoTitleBackground;
	private JTextArea notesBackground;
	private JScrollPane scrollPane;


	/**
	 * Create the frame.
	 */
	public AddEditPatientWindow(Patient pat) {
		
		myPatient = pat;
		
		//determine if client is adding or editing
		isAdding = false;
		if (myPatient.getSex().equals("-Please Select-"))
			isAdding = true;
		
		fileToDelete = null;
		
		//creates window to select teeth
		teethWindow = new TeethSelectionWindow(myPatient.getTeethToProcedure());
			
		//creates window to choose patient photo
		photoChooser = new JFileChooser();
		photoChooser.addChoosableFileFilter(Database.filter);
		photoChooser.setAcceptAllFileFilterUsed(false);
		
		File imageFile = Database.findImageFile(pat);
		
		if (imageFile != null)
		{
			File copyDir = new File("Copy/");
			copyDir.mkdirs();
			try {
				Files.copy(Paths.get(imageFile.getPath()), Paths.get("Copy/" + imageFile.getName()), StandardCopyOption.REPLACE_EXISTING);
				fileToDelete = new File("Copy/" + imageFile.getName());
				System.out.println(fileToDelete.getPath());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
			System.out.println("no pic");
		
		photoChooser.setSelectedFile(fileToDelete);
		
		
		setGUI();
	}
	
	//initializes the GUI elements and features
	public void setGUI()
	{
		setBounds(100, 100, 776, 434);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 255, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		setLabels();
		setTextFields();
		setComboBoxes();
		setButtons();
		setSpinners();
		
		infoBackground = new JTextField();
		infoBackground.setEditable(false);
		infoBackground.setEnabled(false);
		infoBackground.setBackground(new Color(245, 245, 220));
		infoBackground.setBounds(10, 56, 349, 295);
		contentPane.add(infoBackground);
		infoBackground.setColumns(10);
		
		titleBackground = new JTextArea();
		titleBackground.setEnabled(false);
		titleBackground.setEditable(false);
		titleBackground.setBackground(new Color(245, 245, 220));
		titleBackground.setBounds(10, 11, 741, 34);
		contentPane.add(titleBackground);
		
		lblNotesTitle = new JLabel("Notes:");
		lblNotesTitle.setForeground(new Color(0, 0, 0));
		lblNotesTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblNotesTitle.setBackground(new Color(245, 245, 220));
		lblNotesTitle.setBounds(367, 58, 209, 20);
		contentPane.add(lblNotesTitle);
		
		notesBackground = new JTextArea();
		notesBackground.setForeground(new Color(0, 0, 0));
		notesBackground.setEditable(false);
		notesBackground.setEnabled(false);
		notesBackground.setBackground(new Color(245, 245, 220));
		notesBackground.setBounds(369, 56, 209, 22);
		contentPane.add(notesBackground);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(369, 83, 209, 268);
		contentPane.add(scrollPane);
		
		textAreaNotes = new JTextArea(myPatient.getNotes());
		scrollPane.setViewportView(textAreaNotes);
		textAreaNotes.setLineWrap(true);
		textAreaNotes.setMargin(new Insets(10,10,10,10));
		
		photoTitleBackground = new JTextArea();
		photoTitleBackground.setForeground(Color.BLACK);
		photoTitleBackground.setEnabled(false);
		photoTitleBackground.setEditable(false);
		photoTitleBackground.setBackground(new Color(245, 245, 220));
		photoTitleBackground.setBounds(586, 56, 165, 22);
		contentPane.add(photoTitleBackground);
		
		lblPhotoImage = new JLabel("");
		lblPhotoImage.setHorizontalAlignment(SwingConstants.CENTER);
		lblPhotoImage.setBackground(new Color(245, 245, 220));
		lblPhotoImage.setBounds(594, 95, 150, 207);
		
		BufferedImage bufferedImage = Database.loadPhoto(myPatient);
		Image image = bufferedImage.getScaledInstance(140, lblPhotoImage.getHeight(), Image.SCALE_DEFAULT);
		lblPhotoImage.setIcon(new ImageIcon(image));
		
		contentPane.add(lblPhotoImage);
		
		photoBackground = new JTextField();
		photoBackground.setEnabled(false);
		photoBackground.setEditable(false);
		photoBackground.setColumns(10);
		photoBackground.setBackground(new Color(245, 245, 220));
		photoBackground.setBounds(586, 83, 165, 268);
		contentPane.add(photoBackground);
	}
	
	//sets up all the labels
	private void setLabels()
	{
		lblTitle = new JLabel();
		lblTitle.setForeground(new Color(0, 128, 0));
		lblTitle.setFont(new Font("Perpetua", Font.PLAIN, 27));
		if (isAdding)
		{
			lblTitle.setText("Add Patient");
			this.setTitle("Add Patient");
		}
		else
		{
			lblTitle.setText("Edit Patient");
			this.setTitle("Edit Patient");
		}
		
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(10, 11, 741, 46);
		contentPane.add(lblTitle);
		
		lblName = new JLabel("Name: ");
		lblName.setHorizontalAlignment(SwingConstants.LEFT);
		lblName.setBounds(25, 68, 97, 26);
		contentPane.add(lblName);
		
		lblSex = new JLabel("Sex:");
		lblSex.setHorizontalAlignment(SwingConstants.LEFT);
		lblSex.setBounds(25, 98, 41, 26);
		contentPane.add(lblSex);
		
		lblDOB = new JLabel("Date of Birth: ");
		lblDOB.setBounds(25, 138, 106, 14);
		contentPane.add(lblDOB);
		
		lblProcedure = new JLabel("Procedure: ");
		lblProcedure.setBounds(25, 177, 97, 14);
		contentPane.add(lblProcedure);
		
		lblServiceTeeth = new JLabel("Teeth to Service: ");
		lblServiceTeeth.setBounds(25, 214, 117, 14);
		contentPane.add(lblServiceTeeth);
		
		lblMaterial = new JLabel("Material: ");
		lblMaterial.setBounds(25, 252, 106, 14);
		contentPane.add(lblMaterial);
		
		lblBonding = new JLabel("Bonding: ");
		lblBonding.setBounds(25, 288, 106, 14);
		contentPane.add(lblBonding);
		
		lblColor = new JLabel("Color: ");
		lblColor.setBounds(25, 321, 46, 14);
		contentPane.add(lblColor);
		
		lblPhoto = new JLabel("Photo:");
		lblPhoto.setHorizontalAlignment(SwingConstants.CENTER);
		lblPhoto.setBounds(586, 61, 165, 14);
		contentPane.add(lblPhoto);
	}
	
	//sets up all the text fields
	private void setTextFields()
	{
		textFieldFirstName = new JTextField(myPatient.getFirstName());
		textFieldFirstName.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) 
			{
				if (textFieldFirstName.getText().equals("First Name"))
					textFieldFirstName.setText("");
			}
			@Override
			public void focusLost(FocusEvent e) 
			{
				if (textFieldFirstName.getText().equals(""))
					textFieldFirstName.setText(myPatient.getFirstName());
			}
		});
		textFieldFirstName.setBounds(151, 71, 86, 20);
		contentPane.add(textFieldFirstName);
		textFieldFirstName.setColumns(10);
		
		textFieldLastName = new JTextField(myPatient.getLastName());
		textFieldLastName.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) 
			{
				if (textFieldLastName.getText().equals("Last Name"))
					textFieldLastName.setText("");
			}
			@Override
			public void focusLost(FocusEvent e) 
			{
				if (textFieldLastName.getText().equals(""))
					textFieldLastName.setText(myPatient.getLastName());
			}
		});
		textFieldLastName.setBounds(247, 71, 101, 20);
		contentPane.add(textFieldLastName);
		textFieldLastName.setColumns(10);
	}
	
	//sets up all the combo boxes
	private void setComboBoxes()
	{
		comboBoxProcedure = new JComboBox(Patient.PROCEDURES);
		comboBoxProcedure.setSelectedItem(myPatient.getProcedure());
		comboBoxProcedure.setBounds(152, 173, 196, 22);
		contentPane.add(comboBoxProcedure);
		
		comboBoxSex = new JComboBox(Patient.SEX);
		comboBoxSex.setSelectedItem(myPatient.getSex());
		comboBoxSex.setBounds(152, 102, 196, 22);
		contentPane.add(comboBoxSex);

		comboBoxMaterial = new JComboBox(Patient.MATERIALS);
		comboBoxMaterial.setSelectedItem(myPatient.getMaterial());
		comboBoxMaterial.setBounds(152, 248, 196, 22);
		contentPane.add(comboBoxMaterial);

		comboBoxBonding = new JComboBox(Patient.BONDINGS);
		comboBoxBonding.setSelectedItem(myPatient.getBonding());
		comboBoxBonding.setBounds(152, 284, 196, 22);
		contentPane.add(comboBoxBonding);

		comboBoxColor = new JComboBox(Patient.COLORS);
		comboBoxColor.setSelectedItem(myPatient.getColor());
		comboBoxColor.setBounds(152, 317, 196, 22);
		contentPane.add(comboBoxColor);
	}
	
	//sets up all the buttons
	private void setButtons()
	{
		btnSelectTeeth = new JButton("Select Teeth");
		btnSelectTeeth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				teethWindow.setVisible(true);
			}
		});
		btnSelectTeeth.setBounds(152, 210, 196, 23);
		contentPane.add(btnSelectTeeth);
		
		btnSelectPhoto = new JButton("Select Photo");
		btnSelectPhoto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selected = photoChooser.showSaveDialog(null);
				if (selected == JFileChooser.APPROVE_OPTION)
				{
					File selectedPhoto = photoChooser.getSelectedFile();
		            try {
		    			Image image = (ImageIO.read(selectedPhoto)).getScaledInstance(lblPhotoImage.getWidth(), lblPhotoImage.getHeight(), Image.SCALE_DEFAULT);
		    			lblPhotoImage.setIcon(new ImageIcon(image));
		    		} catch (IOException exception) {
		    			// TODO Auto-generated catch block
		    			exception.printStackTrace();
		    		}
				}
	            else
	                System.out.println("the user cancelled the operation");
			}
		});
		btnSelectPhoto.setBounds(595, 320, 150, 23);
		contentPane.add(btnSelectPhoto);
		
		btnConfirm = new JButton("Confirm");
		btnConfirm.setBounds(247, 362, 89, 23);
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean addedEditted = addPatient();
				if (addedEditted) {
					MainMenu menu = new MainMenu();
					menu.setVisible(true);
					dispose();
				}
			}
		});
		contentPane.add(btnConfirm);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(439, 362, 89, 23);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quit();
			}
		});
		contentPane.add(btnCancel);
	}
	
	//sets up all the spinners
	private void setSpinners()
	{
		spinnerMonth = new JSpinner();
		spinnerMonth.setModel(new SpinnerNumberModel(1, 1, 12, 1));
		spinnerMonth.setValue(myPatient.getMonth());
		spinnerMonth.setBounds(151, 135, 41, 20);
		contentPane.add(spinnerMonth);
		
		spinnerDay = new JSpinner();
		spinnerDay.setModel(new SpinnerNumberModel(1, 1, 31, 1));
		spinnerDay.setValue(myPatient.getDay());
		spinnerDay.setBounds(213, 135, 41, 20);
		contentPane.add(spinnerDay);
		
		spinnerYear = new JSpinner();
		spinnerYear.setModel(new SpinnerNumberModel(2001, 1900, 3000, 1));
		spinnerYear.setValue(myPatient.getYear());
		spinnerYear.setBounds(272, 135, 76, 20);
		contentPane.add(spinnerYear);
	}
	
	//adds (or edits) patient to database using info from input fields
	private boolean addPatient() {
		
	
		//check if the teeth selection window is still open
		if (teethWindow.isVisible())
		{
			JOptionPane.showMessageDialog(getRootPane(), "       Error: Finish choosing the teeth to procedure", "Select Teeth", JOptionPane.ERROR_MESSAGE);
			return false;
		}
			
		//get all data from input fields
		String firstName = capitalize(textFieldFirstName.getText());
		String lastName = capitalize(textFieldLastName.getText());
		String procedure = comboBoxProcedure.getSelectedItem().toString();
		String sex = comboBoxSex.getSelectedItem().toString();
		String color = comboBoxColor.getSelectedItem().toString();
		String adhesive = comboBoxBonding.getSelectedItem().toString();
		String compositeMaterial = comboBoxMaterial.getSelectedItem().toString();
		String teethSelected = teethWindow.getSelectedTeeth();
		int day = (int) spinnerDay.getValue();
		int month = (int) spinnerMonth.getValue();
		int year = (int) spinnerYear.getValue();
		String notes = textAreaNotes.getText();
			
		//check if all required data has been added
		if (firstName.equals("First Name") || lastName.equals("Last Name") 
				|| comboBoxSex.getSelectedIndex() == 0 || comboBoxProcedure.getSelectedIndex() == 0 
				|| comboBoxColor.getSelectedIndex() == 0 || comboBoxBonding.getSelectedIndex() == 0 
				|| comboBoxMaterial.getSelectedIndex() == 0)
		{
			JOptionPane.showMessageDialog(getRootPane(), 
					"       Error: Empty or invalid fields", "Invalid fields", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		//create new patient using the data
		Patient pat = new Patient(firstName, lastName, sex, procedure, teethSelected, color, compositeMaterial,
					adhesive, day, month, year, notes);
			
		//adds patient if adding; otherwise edit patient
		if (isAdding)
			Database.addData(pat);
		else
			Database.editData(myPatient, pat);
			
		//add image for user
		if (photoChooser.getSelectedFile() != null)
			addImage(pat);
		
		if (fileToDelete != null)
		{
			fileToDelete.delete();
			File copyDir = new File("Copy/");
			copyDir.delete();
		}
			
		return true;
	}
	
	//adds image for patient given selected image file and patient name & DOB
	private void addImage(Patient pat)
	{
		String header = pat.getLastName() + pat.getFirstName() + pat.getMonth() + pat.getDay() + pat.getYear();
		String path = photoChooser.getSelectedFile().getPath();
		String fileType = path.substring(path.indexOf("."));
		String newPath = "TempPics/" + header + fileType;
		System.out.println(newPath);
		
		try {
			Files.copy(Paths.get(path), Paths.get(newPath), StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//confirms if user wants to go back to main menu
	public void quit() {
		int dialogResult = JOptionPane.showConfirmDialog(getRootPane(),
				"       Are you sure you cancel?\n(Any modifications will be erased.)", "Cancel Confirmation",
				JOptionPane.ERROR_MESSAGE);
		if (dialogResult == 1) {
			return;
		}
		MainMenu menu = new MainMenu();
		menu.setVisible(true);
		dispose();
	}
	
	//makes a string have first letter capitalize (proper proper noun convention)
	private String capitalize(String text)
	{
		String firstLetter = ("" + text.charAt(0)).toUpperCase();
		if (text.length() > 1)
		{
			return firstLetter + text.substring(1).toLowerCase();
		}
		else
			return firstLetter;
	}
}
