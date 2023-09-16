import java.text.DateFormatSymbols;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JTabbedPane;
import javax.swing.DropMode;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Canvas;

public class PatientInfoWindow extends Window {

	//panes
	private JPanel contentPane;
	private JTabbedPane tabbedPane;
	private JPanel infoPanel;
	private JScrollPane scrollPaneNotes;
	
	//buttons
	private JButton btnEdit;
	private JButton btnBack;
	private JButton btnPrevious;
	private JButton btnNext;
	
	//data
	private Patient myPatient;
	private ArrayList<Patient> myPatientList;
	private int myIndex;
	
	//labels && text areas
	private JLabel lblPatientName;
	private JLabel lblSexTitle;
	private JLabel lblDateTitle;
	private JLabel lblSex;
	private JLabel lblDOB;
	private JLabel lblProcedureTitle;
	private JLabel lblProcedure;
	private JLabel lblTeethServiceTitle;
	private JLabel lblMaterialTitle;
	private JLabel lblBondingTitle;
	private JLabel lblColorTitle;
	private JLabel lblColor;
	private JLabel lblBonding;
	private JLabel lblMaterial;
	private JLabel lblPhoto;
	private JTextArea textAreaTeethToService;
	private JTextArea textAreaNotes;
	private JTextField photoBackground;
	private JTextField nameBackground;
	

	/**
	 * Create the frame.
	 */
	public PatientInfoWindow(Patient pat, int index) {
		myPatientList = Database.provideList();
		myIndex = index;
		
		
		myPatient = pat;
		setGUI();
	}
	
	//initializes the GUI and related elements
	public void setGUI()
	{
		setTitle("Patient Information");

		setBounds(100, 100, 514, 432);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 255, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		setPanels();
		setButtons();
		setLabels();
		setPictures();
	}
	
	//sets up the panels/panes
	private void setPanels()
	{
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 58, 240, 290);
		contentPane.add(tabbedPane);
		
		infoPanel = new JPanel();
		infoPanel.setBackground(new Color(245, 245, 220));
		tabbedPane.addTab("Information", null, infoPanel, null);
		tabbedPane.setBackgroundAt(0, new Color(245, 245, 220));
		infoPanel.setLayout(null);
		
		scrollPaneNotes = new JScrollPane();
		tabbedPane.addTab("Notes", null, scrollPaneNotes, null);
		tabbedPane.setBackgroundAt(1, new Color(245, 245, 220));
	}
	
	//sets up the labels (and also text areas)
	private void setLabels()
	{
		
		lblPatientName = new JLabel(myPatient.getLastName() + ", " + myPatient.getFirstName());
		lblPatientName.setBackground(new Color(245, 245, 220));
		lblPatientName.setForeground(new Color(0, 128, 0));
		lblPatientName.setFont(new Font("Corbel Light", Font.BOLD | Font.ITALIC, 35));
		lblPatientName.setHorizontalAlignment(SwingConstants.CENTER);
		lblPatientName.setBounds(10, 17, 478, 36);
		contentPane.add(lblPatientName);
		
		textAreaTeethToService = new JTextArea(convertTeethSelect());
		textAreaTeethToService.setWrapStyleWord(true);
		textAreaTeethToService.setLineWrap(true);
		textAreaTeethToService.setFocusable(false);
		textAreaTeethToService.setEditable(false);
		textAreaTeethToService.setBackground(new Color(245, 245, 220));
		textAreaTeethToService.setBounds(5, 98, 203, 83);
		infoPanel.add(textAreaTeethToService);
		
		lblSexTitle = new JLabel("Sex:");
		lblSexTitle.setFont(new Font("Dialog", Font.BOLD, 13));
		lblSexTitle.setBounds(5, 11, 46, 14);
		infoPanel.add(lblSexTitle);
		
		lblSex = new JLabel(myPatient.getSex());
		lblSex.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSex.setFont(new Font("Dialog", Font.PLAIN, 13));
		lblSex.setBounds(67, 7, 158, 23);
		infoPanel.add(lblSex);
		
		lblDateTitle = new JLabel("Date of Birth:");
		lblDateTitle.setFont(new Font("Dialog", Font.BOLD, 13));
		lblDateTitle.setBounds(5, 33, 100, 14);
		infoPanel.add(lblDateTitle);
		
		lblDOB = new JLabel(new DateFormatSymbols().getMonths()[myPatient.getMonth()-1] + " " + myPatient.getDay() + ", " + myPatient.getYear());
		lblDOB.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDOB.setFont(new Font("Dialog", Font.PLAIN, 13));
		lblDOB.setBounds(70, 29, 155, 23);
		infoPanel.add(lblDOB);
		
		lblProcedure = new JLabel(myPatient.getProcedure());
		lblProcedure.setHorizontalAlignment(SwingConstants.RIGHT);
		lblProcedure.setFont(new Font("Dialog", Font.PLAIN, 13));
		lblProcedure.setBounds(91, 53, 134, 23);
		infoPanel.add(lblProcedure);
		
		lblProcedureTitle = new JLabel("Procedure:");
		lblProcedureTitle.setFont(new Font("Dialog", Font.BOLD, 13));
		lblProcedureTitle.setBounds(5, 57, 77, 14);
		infoPanel.add(lblProcedureTitle);
		
		lblTeethServiceTitle = new JLabel("Teeth to Service:");
		lblTeethServiceTitle.setVerticalAlignment(SwingConstants.TOP);
		lblTeethServiceTitle.setFont(new Font("Dialog", Font.BOLD, 13));
		lblTeethServiceTitle.setBounds(5, 78, 108, 23);
		infoPanel.add(lblTeethServiceTitle);
		
		lblMaterialTitle = new JLabel(" Material:");
		lblMaterialTitle.setFont(new Font("Dialog", Font.BOLD, 13));
		lblMaterialTitle.setBounds(5, 192, 127, 14);
		infoPanel.add(lblMaterialTitle);
		
		lblBondingTitle = new JLabel("Bonding:");
		lblBondingTitle.setFont(new Font("Dialog", Font.BOLD, 13));
		lblBondingTitle.setBounds(5, 217, 127, 14);
		infoPanel.add(lblBondingTitle);
		
		lblColorTitle = new JLabel("Color:");
		lblColorTitle.setFont(new Font("Dialog", Font.BOLD, 13));
		lblColorTitle.setBounds(5, 242, 127, 14);
		infoPanel.add(lblColorTitle);
		
		lblColor = new JLabel(myPatient.getColor());
		lblColor.setHorizontalAlignment(SwingConstants.RIGHT);
		lblColor.setFont(new Font("Dialog", Font.PLAIN, 13));
		lblColor.setBounds(70, 242, 155, 14);
		infoPanel.add(lblColor);
		
		lblBonding = new JLabel(myPatient.getBonding());
		lblBonding.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBonding.setFont(new Font("Dialog", Font.PLAIN, 13));
		lblBonding.setBounds(67, 217, 158, 14);
		infoPanel.add(lblBonding);
		
		lblMaterial = new JLabel(myPatient.getMaterial());
		lblMaterial.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMaterial.setFont(new Font("Dialog", Font.PLAIN, 13));
		lblMaterial.setBounds(67, 192, 158, 14);
		infoPanel.add(lblMaterial);
		
		textAreaNotes = new JTextArea(myPatient.getNotes());
		textAreaNotes.setEditable(false);
		textAreaNotes.setLineWrap(true);
		textAreaNotes.setWrapStyleWord(true);
		textAreaNotes.setMargin(new Insets(10,10,10,10));
		scrollPaneNotes.setViewportView(textAreaNotes);
	}
	
	//sets up the pictures
	private void setPictures()
	{
		lblPhoto = new JLabel("");
		lblPhoto.setBackground(new Color(245, 245, 220));
		lblPhoto.setBounds(260, 58, 229, 290);
		contentPane.add(lblPhoto);
		loadPhoto();
		
		photoBackground = new JTextField();
		photoBackground.setBackground(new Color(245, 245, 220));
		photoBackground.setEnabled(false);
		photoBackground.setEditable(false);
		photoBackground.setBounds(259, 58, 229, 290);
		contentPane.add(photoBackground);
		photoBackground.setColumns(10);
		
		nameBackground = new JTextField();
		nameBackground.setEditable(false);
		nameBackground.setEnabled(false);
		nameBackground.setBackground(new Color(245, 245, 220));
		nameBackground.setBounds(11, 11, 478, 40);
		contentPane.add(nameBackground);
		nameBackground.setColumns(10);
	}
	
	//loads photo of the patient if it exists
	private void loadPhoto()
	{
		BufferedImage bufferedImage = Database.loadPhoto(myPatient);
		Image patientImage = bufferedImage.getScaledInstance(240, lblPhoto.getHeight(), Image.SCALE_DEFAULT);
		lblPhoto.setIcon(new ImageIcon(patientImage));
	}
	
	//sets up all the buttons
	private void setButtons()
	{
		btnNext = new JButton("->");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				Patient afterPat = myPatientList.get(myIndex+1);
				PatientInfoWindow afterPatWindow = new PatientInfoWindow(afterPat, myIndex+1);
				afterPatWindow.setVisible(true);
				dispose();
			}
		});
		btnNext.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNext.setBounds(426, 20, 51, 23);
		contentPane.add(btnNext);
		
		
		btnPrevious = new JButton("<-");
		btnPrevious.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				Patient beforePat = myPatientList.get(myIndex-1);
				PatientInfoWindow beforePatWindow = new PatientInfoWindow(beforePat, myIndex-1);
				beforePatWindow.setVisible(true);
				dispose();
			}
		});
		btnPrevious.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnPrevious.setBounds(20, 19, 51, 23);
		contentPane.add(btnPrevious);
		
		if (myIndex == 0)
			btnPrevious.setEnabled(false);
		else if (myIndex == myPatientList.size()-1)
			btnNext.setEnabled(false);
		
		btnEdit = new JButton("Edit");
		btnEdit.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				AddEditPatientWindow editWindow = new AddEditPatientWindow(myPatient);
				editWindow.setVisible(true);
				dispose();
			}
		});
		
		btnEdit.setBounds(134, 359, 89, 23);
		contentPane.add(btnEdit);
		
		btnBack = new JButton("Back");
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				quit();
			}
		});
		btnBack.setBounds(288, 359, 89, 23);
		contentPane.add(btnBack);
	}
	
	//quits the window
	public void quit()
	{
		MainMenu menu = new MainMenu();
		menu.setVisible(true);
		dispose();
	}
	
	//converts the array of selected teeth to user-friendly text
	private String convertTeethSelect()
	{
		String result = "";
		String teethSelect = myPatient.getTeethToProcedure();
		for (int i = 0; i < 20; i++)
		{
			if (teethSelect.charAt(i) == '1')
				result += (char)('A' + i) + ", ";
		}
		for (int j = 20; j < teethSelect.length(); j++)
		{
			if (teethSelect.charAt(j) == '1')
				result += j-19 + ", ";
		}
		
		if (result.length() != 0)
			result = result.substring(0,result.length()-2);
		else
			result = "None";
		
		return result;
	}
}
