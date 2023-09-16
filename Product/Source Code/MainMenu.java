import java.awt.AWTEvent;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.AWTEventListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Component;
import javax.swing.JTextArea;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class MainMenu extends Window {
	
	private JPanel contentPane;
	
	//decorations
	private JLabel lblLogo;
	private JLabel toothImg1;
	private JLabel toothImg2;
	
	//table parts
	private JScrollPane scrollPanePatient;
	private DefaultTableModel modelTable;
	private JTable tablePatient;
	
	//buttons
	private JButton btnAdd;
	private JButton btnSave;
	private JButton btnQuit;
	private JButton btnReset;
	private JPopupMenu rightClickMenu;
	
	//sort parts
	private JComboBox<String> comboBoxSort;
	private JComboBox<String> comboBoxSortOrder;
	private JTextArea sortBackground;
	
	//search parts
	private JTextField searchLastName;
	private JComboBox searchColor;
	private JComboBox searchBonding;
	private JComboBox searchProcedure;
	private JComboBox searchSex;
	private JTextField searchFirstName;
	private JComboBox searchMaterial;
	private JTextArea searchBackground;
	
	//data
	private ArrayList<Patient> patientList;
	private boolean saved;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				Database.createImageFolders();
				Database.loadData();
				Database.printList();
				try {
					System.out.println(Database.provideList().size());
					MainMenu frame = new MainMenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainMenu() {
		saved = false;
		patientList = Database.provideList();
		setGUI();
	}
		
	//initializes all the GUI elements and features
	public void setGUI()
	{
		setBackground(SystemColor.activeCaption);
		setTitle("Restoration Information Manager");
		
		setBounds(100, 100, 1218, 578);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 255, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTitle = new JLabel("Restoration Information \r\nManager");
		lblTitle.setForeground(new Color(0, 128, 0));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Perpetua", Font.PLAIN, 36));
		lblTitle.setBounds(20, 11, 907, 70);
		contentPane.add(lblTitle);
		
		
		setTable();
		setPictures();
		setButtons();
		setSortFeatures();
		setSearchFeatures();
		setPopup();

	}
	
	//sets up the data table
	private void setTable()
	{
		modelTable = new DefaultTableModel(null, Patient.CATEGORIES);

		scrollPanePatient = new JScrollPane();
		scrollPanePatient.setBounds(20, 72, 907, 417);
		contentPane.add(scrollPanePatient);
		
		tablePatient = new JTable(modelTable);
		tablePatient.setSurrendersFocusOnKeystroke(true);
		tablePatient.getTableHeader().setReorderingAllowed(false);
		tablePatient.getTableHeader().setResizingAllowed(false);
		tablePatient.getTableHeader().setBackground(new Color(245, 245, 220));
		tablePatient.setToolTipText("Select Patient and Right Click to Modify/View");	
		
		scrollPanePatient.setViewportView(tablePatient);
		scrollPanePatient.getViewport().setBackground(new Color(245, 255, 245));
		
		if (patientList.size() > 0)
			loadTable();
	}
	
	//sets up all the picture elements
	private void setPictures()
	{
		lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("logo.png"))));
		lblLogo.setBounds(937, 23, 299, 113);
		contentPane.add(lblLogo);
		
		toothImg1 = new JLabel("");
		toothImg1.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		toothImg1.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("tooth.png"))));
		toothImg1.setBounds(20, 11, 178, 125);
		contentPane.add(toothImg1);
		
		toothImg2 = new JLabel("");
		toothImg2.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		toothImg2.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("tooth.png"))));
		toothImg2.setBounds(816, 11, 158, 125);
		contentPane.add(toothImg2);
	}
	
	//sets up all buttons
	private void setButtons()
	{
		btnAdd = new JButton("Add");
		btnAdd.setToolTipText("Add a Patient");
		btnAdd.setBackground(new Color(245, 245, 220));
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAdd.setBounds(24, 500, 82, 28);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddEditPatientWindow addWindow = new AddEditPatientWindow(new Patient());
				addWindow.setVisible(true);
				dispose();
			}
		});
		contentPane.add(btnAdd);

		btnReset = new JButton("RESET");
		btnReset.setToolTipText("Reset all data");
		btnReset.setBackground(new Color(245, 245, 220));
		btnReset.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnReset.setForeground(new Color(220, 20, 60));
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reset();
			}
		});
		btnReset.setBounds(742, 500, 82, 28);
		contentPane.add(btnReset);

		btnSave = new JButton("Save");
		btnSave.setToolTipText("Save changes");
		btnSave.setBackground(new Color(245, 245, 220));
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saved = true;
				Database.saveData();
				System.out.println("saved");
				Database.printList();
			}
		});
		btnSave.setBounds(116, 500, 82, 28);
		contentPane.add(btnSave);

		btnQuit = new JButton("Quit");
		btnQuit.setToolTipText("Quit program");
		btnQuit.setBackground(new Color(245, 245, 220));
		btnQuit.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quit();
			}
		});
		btnQuit.setBounds(847, 500, 82, 28);
		contentPane.add(btnQuit);
	}
	
	//sets up the sorting features
	private void setSortFeatures()
	{
		JLabel lblSort = new JLabel("Sort Options");
		lblSort.setHorizontalAlignment(SwingConstants.CENTER);
		lblSort.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblSort.setBounds(947, 147, 219, 21);
		contentPane.add(lblSort);

		comboBoxSort = new JComboBox(Patient.CATEGORIES);
		comboBoxSort.setBackground(new Color(245, 255, 245));
		comboBoxSort.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				sortTable();
			}
		});
		comboBoxSort.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBoxSort.setBounds(944, 179, 148, 22);
		contentPane.add(comboBoxSort);
		
		comboBoxSortOrder = new JComboBox(new String[]{"A->Z", "Z->A"});
		comboBoxSortOrder.setBackground(new Color(245, 255, 245));
		comboBoxSortOrder.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBoxSortOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sortTable();
			}
		});
		comboBoxSortOrder.setBounds(1102, 179, 64, 22);
		contentPane.add(comboBoxSortOrder);
		
		sortBackground = new JTextArea();
		sortBackground.setBackground(new Color(245, 245, 220));
		sortBackground.setBounds(937, 142, 245, 70);
		sortBackground.setEnabled(false);
		contentPane.add(sortBackground);
	}
	
	//sets up the search features
	private void setSearchFeatures()
	{
		JLabel lblSearch = new JLabel("Search Filters");
		lblSearch.setHorizontalAlignment(SwingConstants.CENTER);
		lblSearch.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblSearch.setBounds(947, 226, 219, 21);
		contentPane.add(lblSearch);
		
		searchLastName = new JTextField();
		searchLastName.setText("Enter here...");
		searchLastName.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) 
			{
				searchLastName.setText("");
			}
			@Override
			public void focusLost(FocusEvent e) 
			{
				if (searchLastName.getText().equals(""))
					searchLastName.setText("Enter here...");
			}
		});
		searchLastName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				searchTable();
			}
		});
		searchLastName.setBounds(1030, 255, 136, 21);
		contentPane.add(searchLastName);
		searchLastName.setColumns(10);
		
		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblLastName.setBounds(949, 258, 85, 14);
		contentPane.add(lblLastName);
		
		JLabel lblFirstName = new JLabel("First Name:");
		lblFirstName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblFirstName.setBounds(949, 294, 85, 14);
		contentPane.add(lblFirstName);
		
		searchFirstName = new JTextField();
		searchFirstName.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) 
			{
				searchFirstName.setText("");
			}
			@Override
			public void focusLost(FocusEvent e) 
			{
				if (searchFirstName.getText().equals(""))
					searchFirstName.setText("Enter here...");
			}
		});
		searchFirstName.setText("Enter here...");
		searchFirstName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				searchTable();
			}
		});
		searchFirstName.setColumns(10);
		searchFirstName.setBounds(1030, 291, 136, 21);
		contentPane.add(searchFirstName);
		
		JLabel lblSex = new JLabel("Sex:");
		lblSex.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSex.setBounds(949, 333, 56, 14);
		contentPane.add(lblSex);
		
		searchSex = new JComboBox(Patient.SEX);
		searchSex.setBackground(new Color(245, 255, 245));
		searchSex.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				searchTable();
			}
		});
		searchSex.setBounds(1030, 330, 136, 22);
		contentPane.add(searchSex);
		
		JLabel lblProcedure = new JLabel("Procedure:");
		lblProcedure.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblProcedure.setBounds(949, 367, 71, 14);
		contentPane.add(lblProcedure);
		
		searchProcedure = new JComboBox(Patient.PROCEDURES);
		searchProcedure.setBackground(new Color(245, 255, 245));
		searchProcedure.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				searchTable();
			}
		});
		searchProcedure.setBounds(1030, 364, 136, 22);
		contentPane.add(searchProcedure);
		
		JLabel lblMaterial = new JLabel("Material:");
		lblMaterial.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblMaterial.setBounds(947, 410, 102, 14);
		contentPane.add(lblMaterial);
		
		searchMaterial = new JComboBox(Patient.MATERIALS);
		searchMaterial.setBackground(new Color(245, 255, 245));
		searchMaterial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				searchTable();
			}
		});
		searchMaterial.setBounds(1030, 407, 136, 22);
		contentPane.add(searchMaterial);
		
		JLabel lblBonding = new JLabel("Bonding:");
		lblBonding.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblBonding.setBounds(949, 446, 56, 14);
		contentPane.add(lblBonding);
		
		searchBonding = new JComboBox(Patient.BONDINGS);
		searchBonding.setBackground(new Color(245, 255, 245));
		searchBonding.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				searchTable();
			}
		});
		searchBonding.setBounds(1030, 443, 136, 22);
		contentPane.add(searchBonding);
		
		JLabel lblColor = new JLabel("Color:");
		lblColor.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblColor.setBounds(947, 486, 89, 14);
		contentPane.add(lblColor);
		
		searchColor = new JComboBox(Patient.COLORS);
		searchColor.setBackground(new Color(245, 255, 245));
		searchColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				searchTable();
			}
		});
		searchColor.setBounds(1030, 483, 136, 22);
		contentPane.add(searchColor);
		
		searchBackground = new JTextArea();
		searchBackground.setBackground(new Color(245, 245, 220));
		searchBackground.setBounds(937, 224, 245, 292);
		searchBackground.setEnabled(false);
		contentPane.add(searchBackground);
	}
	
	//sets up the right click popup menu
	private void setPopup()
	{
		rightClickMenu = new JPopupMenu();
		
		JMenuItem editMenuItem = new JMenuItem("Edit");
		JMenuItem viewMenuItem = new JMenuItem("View");
		JMenuItem deleteMenuItem = new JMenuItem("Delete");
		deleteMenuItem.setForeground(Color.RED);
		
		rightClickMenu.add(editMenuItem);
		rightClickMenu.add(viewMenuItem);
		rightClickMenu.add(deleteMenuItem);
		
		
		viewMenuItem.addActionListener(new ActionListener() {
	          public void actionPerformed(ActionEvent e) {
	            PatientInfoWindow viewWindow = new PatientInfoWindow(patientList.get(tablePatient.getSelectedRow()),tablePatient.getSelectedRow());
	            viewWindow.setVisible(true);
	            tablePatient.clearSelection();
	            dispose();
	          }
	        });
		
		editMenuItem.addActionListener(new ActionListener() {
	          public void actionPerformed(ActionEvent e) {
	        	AddEditPatientWindow editWindow = new AddEditPatientWindow(patientList.get(tablePatient.getSelectedRow()));
	            editWindow.setVisible(true);
	            tablePatient.clearSelection();
	            dispose();
	          }
	        });
		
		deleteMenuItem.addActionListener(new ActionListener() {
	          public void actionPerformed(ActionEvent e) {
	            this.delete();
	          }

	          private void delete() {
	        	Database.deleteData(patientList.get(tablePatient.getSelectedRow()));
	      		modelTable.removeRow(tablePatient.getSelectedRow());
	      		tablePatient.clearSelection();
	      	}
	        });

		Toolkit.getDefaultToolkit().addAWTEventListener(new AWTEventListener() {
			public void eventDispatched(AWTEvent awte) {
				if (tablePatient.getSelectedRow() != -1)
					this.showPopup(awte);
			}

			private void showPopup(AWTEvent awte) {
				MouseEvent me = (MouseEvent) awte;
				if (me.isPopupTrigger())
					rightClickMenu.show(me.getComponent(), me.getX(), me.getY());

			}
		}, 16L);
	}
	
	//asks the user to save changes and then quit
	public void quit()
	{
		if (!saved)
		{
			int dialogResult = JOptionPane.showConfirmDialog(null,
					" Save unsaved changes?",
					"Quit Confirmation", JOptionPane.ERROR_MESSAGE);
			if (dialogResult == 0) {
				System.out.println("saved");
				Database.saveData();
				Database.printList();
				Database.deleteTempPics();
				dispose();
			}
			else if (dialogResult == 1)
			{
				Database.printList();
				Database.deleteTempPics();
				dispose();
			}
		}
		else
		{
			Database.printList();
			Database.deleteTempPics();
			dispose();
		}
	}

	//resets all the data and table upon asking the user
	private void reset() {
		int dialogResult = JOptionPane.showConfirmDialog(null,
				"  Are you sure you want to reset this \nprogram? (All data will be erased.)",
				"Reset Confirmation", JOptionPane.ERROR_MESSAGE);
		if (dialogResult == 0) {
			Database.reset();
			resetTable();
			patientList = new ArrayList<Patient>();
			System.out.println("reset");
			
		}
	}

	//load the table within a list of patients
	private void loadTable() {
		String[][] table = new String[patientList.size()][Patient.CATEGORIES.length];
		for (int i = 0; i < patientList.size(); i++) {
			Patient pat = patientList.get(i);
			String dateOfBirth = pat.getMonth() + "/" + pat.getDay() + "/" + pat.getYear();
			table[i] = new String[]{ pat.getLastName(), pat.getFirstName(), dateOfBirth, pat.getSex(), 
					pat.getProcedure(),pat.getMaterial(),pat.getBonding(),pat.getColor() };
			
			modelTable = new DefaultTableModel(table, Patient.CATEGORIES) {

				@Override
		  		public boolean isCellEditable(int row, int column) {
			  		//all cells false
			  		return false;
		 		}
			};
		}
		tablePatient.setModel(modelTable);
	}
	
	//reset the table (clear it)
	private void resetTable()
	{
		for (int i = 0; i < patientList.size(); i++)
		{
			modelTable.removeRow(0);
		}
	}
	
	//sort the table in order based on the select sort method
	private void sortTable()
	{
		resetTable();
		
		int sortMethod = comboBoxSort.getSelectedIndex();
		ArrayList<Patient> sortedList = new ArrayList<Patient>();
		for (Patient pat : patientList)
		{
			insert(sortedList,pat,sortMethod);
		}
		
		patientList = sortedList;
		
		loadTable();
	}
	
	//insert patient into list based on sort method given (which information to sort by)
	private void insert(ArrayList<Patient> list, Patient pat, int sortMethod)
	{
		int i = 0;
				
		//determines if data is put into alphabetical or reverse-alphabetical order
		int orderMultipler = (comboBoxSortOrder.getSelectedIndex() == 0) ? 1 : -1;
				
		String patInfo = pat.getPatientInfo()[sortMethod];
				
		if (sortMethod != 2) //sort by everything but date of birth
		{
			while (i < list.size() && (orderMultipler*(patInfo.compareTo((list.get(i).getPatientInfo())[sortMethod]))) > 0)
				i++;
		
			list.add(i,pat);
		}
		else  //sort by date of birth
		{
			patInfo = dateConvert(patInfo, pat);
					
			while (i < list.size() && (orderMultipler*patInfo.compareTo(dateConvert(list.get(i).getPatientInfo()[sortMethod],list.get(i))) > 0))
				i++;
	
			list.add(i,pat);
		}
	}
	
	//helper method for the sorting algorithm for date string conversion
	private String dateConvert(String patInfo, Patient pat)
	{
		if (pat.getMonth() < 10)
			return "0" + patInfo;
		return patInfo;
	}
	
	//have the table show all patients with information matching the search query
	private void searchTable()
	{
		resetTable();
		
		patientList = Database.provideList();
		
		ArrayList<Patient> wantedList = new ArrayList<Patient>();
		
		for (Patient pat : patientList)
		{
			if  (fitsFilter(pat))
		        	wantedList.add(pat);
		}
		
		patientList = wantedList;
		
		loadTable();
	}
	
	//checks if a patient fits all the filters
	private boolean fitsFilter(Patient pat)
	{
		String firstName = searchFirstName.getText();
		String lastName = searchLastName.getText();
		
		return ((firstName.equals("") || firstName.equals("Enter here...")|| ((pat.getFirstName().toLowerCase()).indexOf(firstName.toLowerCase()) == 0)) &&
				(lastName.equals("") || lastName.equals("Enter here...") || ((pat.getLastName().toLowerCase()).indexOf(lastName.toLowerCase()) == 0)) &&
				(searchSex.getSelectedIndex() == 0 || ((String)searchSex.getSelectedItem()).equals(pat.getSex())) &&
				(searchProcedure.getSelectedIndex() == 0 || ((String)searchProcedure.getSelectedItem()).equals(pat.getProcedure())) &&
				(searchMaterial.getSelectedIndex() == 0 || ((String)searchMaterial.getSelectedItem()).equals(pat.getMaterial())) &&
				(searchBonding.getSelectedIndex() == 0 || ((String)searchBonding.getSelectedItem()).equals(pat.getBonding())) &&
				(searchColor.getSelectedIndex() == 0 || ((String)searchColor.getSelectedItem()).equals(pat.getColor())));
	}
}
