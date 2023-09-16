import java.awt.Component;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import java.awt.Color;

public class TeethSelectionWindow extends Window {

	private JPanel contentPane;
	
	//panes
	private JTabbedPane TabsPane;
	
	//panel
	private JPanel PrimaryPanel;
	private JPanel PermanentPanel;
	
	//check boxes
	private JCheckBox chckbxA;
	private JCheckBox chckbxB;
	private JCheckBox chckbxC;
	private JCheckBox chckbxD;
	private JCheckBox chckbxE;
	private JCheckBox chckbxF;
	private JCheckBox chckbxG;
	private JCheckBox chckbxH;
	private JCheckBox chckbxI;
	private JCheckBox chckbxJ;
	private JCheckBox chckbxK;
	private JCheckBox chckbxL;
	private JCheckBox chckbxM;
	private JCheckBox chckbxN;
	private JCheckBox chckbxO;
	private JCheckBox chckbxP;
	private JCheckBox chckbxQ;
	private JCheckBox chckbxR;
	private JCheckBox chckBoxS;
	private JCheckBox chckBoxT;
	private JCheckBox chckbx1;
	private JCheckBox chckbx2;
	private JCheckBox chckbx3;
	private JCheckBox chckbx5;
	private JCheckBox chckbx4;
	private JCheckBox chckbx6;
	private JCheckBox chckbx7;
	private JCheckBox chckbx8;
	private JCheckBox chckbx9;
	private JCheckBox chckbx10;
	private JCheckBox chckbx11;
	private JCheckBox chckbx12;
	private JCheckBox chckbx13;
	private JCheckBox chckbx14;
	private JCheckBox chckbx15;
	private JCheckBox chckbx16;
	private JCheckBox chckbx17;
	private JCheckBox chckbx18;
	private JCheckBox chckbx19;
	private JCheckBox chckbx20;
	private JCheckBox chckbx21;
	private JCheckBox chckbx22;
	private JCheckBox chckbx23;
	private JCheckBox chckbx24;
	private JCheckBox chckbx25;
	private JCheckBox chckbx26;
	private JCheckBox chckbx27;
	private JCheckBox chckbx28;
	private JCheckBox chckbx29;
	private JCheckBox chckbx30;
	private JCheckBox chckbx31;
	private JCheckBox chckbx32;
	
	//buttons
	private JButton btnSave;
	private JButton btnCancel;
	private JButton btnReset;
	
	//data
	private char[] primaryTeeth;
	private char[] permanentTeeth;
	private String allTeeth;
	

	/**
	 * Create the frame.
	 */
	public TeethSelectionWindow(String selectTeeth) {
		
		allTeeth = selectTeeth;
		primaryTeeth = allTeeth.substring(0,20).toCharArray();
		permanentTeeth = allTeeth.substring(20,52).toCharArray();
		
		setGUI();
	}
	
	public void setGUI()
	{
		setBounds(100, 100, 296, 313);
		setPanes();
		setPanels();
		setCheckBoxes();
		setButtons();
	}
	
	private void setPanes()
	{
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 255, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		TabsPane = new JTabbedPane(JTabbedPane.TOP);
		TabsPane.setBounds(0, 11, 259, 191);
		contentPane.add(TabsPane);
	}
	
	private void setPanels()
	{
		PrimaryPanel = new JPanel();
		TabsPane.addTab("Primary", null, PrimaryPanel, null);
		PrimaryPanel.setLayout(null);
		
		PermanentPanel = new JPanel();
		TabsPane.addTab("Permanent", null, PermanentPanel, null);
		PermanentPanel.setLayout(null);
	}
	
	private void setCheckBoxes()
	{
		chckbxA = new JCheckBox("A");
		chckbxA.setBounds(6, 7, 40, 23);
		PrimaryPanel.add(chckbxA);
		
		chckbxB = new JCheckBox("B");
		chckbxB.setBounds(6, 33, 40, 23);
		PrimaryPanel.add(chckbxB);
		
		chckbxC = new JCheckBox("C");
		chckbxC.setBounds(6, 59, 40, 23);
		PrimaryPanel.add(chckbxC);
		
		chckbxD = new JCheckBox("D");
		chckbxD.setBounds(6, 85, 40, 23);
		PrimaryPanel.add(chckbxD);
		
		chckbxE = new JCheckBox("E");
		chckbxE.setBounds(48, 7, 40, 23);
		PrimaryPanel.add(chckbxE);
		
		chckbxF = new JCheckBox("F");
		chckbxF.setBounds(48, 33, 40, 23);
		PrimaryPanel.add(chckbxF);
		
		chckbxG = new JCheckBox("G");
		chckbxG.setBounds(48, 59, 40, 23);
		PrimaryPanel.add(chckbxG);
		
		chckbxH = new JCheckBox("H");
		chckbxH.setBounds(48, 85, 40, 23);
		PrimaryPanel.add(chckbxH);
		
		chckbxI = new JCheckBox("I");
		chckbxI.setBounds(90, 7, 40, 23);
		PrimaryPanel.add(chckbxI);
		
		chckbxJ = new JCheckBox("J");
		chckbxJ.setBounds(90, 33, 40, 23);
		PrimaryPanel.add(chckbxJ);
		
		chckbxK = new JCheckBox("K");
		chckbxK.setBounds(90, 59, 40, 23);
		PrimaryPanel.add(chckbxK);
		
		chckbxL = new JCheckBox("L");
		chckbxL.setBounds(90, 85, 40, 23);
		PrimaryPanel.add(chckbxL);
		
		chckbxM = new JCheckBox("M");
		chckbxM.setBounds(132, 7, 40, 23);
		PrimaryPanel.add(chckbxM);
		
		chckbxN = new JCheckBox("N");
		chckbxN.setBounds(132, 33, 40, 23);
		PrimaryPanel.add(chckbxN);
		
		chckbxO = new JCheckBox("O");
		chckbxO.setBounds(132, 59, 40, 23);
		PrimaryPanel.add(chckbxO);
		
		chckbxP = new JCheckBox("P");
		chckbxP.setBounds(132, 85, 40, 23);
		PrimaryPanel.add(chckbxP);
		
		chckbxQ = new JCheckBox("Q");
		chckbxQ.setBounds(174, 7, 40, 23);
		PrimaryPanel.add(chckbxQ);
		
		chckbxR = new JCheckBox("R");
		chckbxR.setBounds(174, 33, 40, 23);
		PrimaryPanel.add(chckbxR);
		
		chckBoxS = new JCheckBox("S");
		chckBoxS.setBounds(174, 59, 40, 23);
		PrimaryPanel.add(chckBoxS);
		
		chckBoxT = new JCheckBox("T");
		chckBoxT.setBounds(174, 85, 40, 23);
		PrimaryPanel.add(chckBoxT);
		
		chckbx1 = new JCheckBox("1");
		chckbx1.setBounds(6, 7, 37, 23);
		PermanentPanel.add(chckbx1);
		
		chckbx2 = new JCheckBox("2");
		chckbx2.setBounds(6, 33, 37, 23);
		PermanentPanel.add(chckbx2);
		
		chckbx3 = new JCheckBox("3");
		chckbx3.setBounds(6, 59, 37, 23);
		PermanentPanel.add(chckbx3);
		
		chckbx4 = new JCheckBox("4");
		chckbx4.setBounds(6, 85, 37, 23);
		PermanentPanel.add(chckbx4);
		
		chckbx5 = new JCheckBox("5");
		chckbx5.setBounds(6, 111, 37, 23);
		PermanentPanel.add(chckbx5);
		
		chckbx6 = new JCheckBox("6");
		chckbx6.setBounds(6, 137, 37, 23);
		PermanentPanel.add(chckbx6);
		
		chckbx7 = new JCheckBox("7");
		chckbx7.setBounds(45, 7, 37, 23);
		PermanentPanel.add(chckbx7);
		
		chckbx8 = new JCheckBox("8");
		chckbx8.setBounds(45, 33, 37, 23);
		PermanentPanel.add(chckbx8);
		
		chckbx9 = new JCheckBox("9");
		chckbx9.setBounds(45, 59, 37, 23);
		PermanentPanel.add(chckbx9);
		
		chckbx10 = new JCheckBox("10");
		chckbx10.setBounds(45, 85, 43, 23);
		PermanentPanel.add(chckbx10);
		
		chckbx11 = new JCheckBox("11");
		chckbx11.setBounds(45, 111, 43, 23);
		PermanentPanel.add(chckbx11);
		
		chckbx12 = new JCheckBox("12");
		chckbx12.setBounds(45, 137, 43, 23);
		PermanentPanel.add(chckbx12);
		
		chckbx13 = new JCheckBox("13");
		chckbx13.setBounds(84, 7, 43, 23);
		PermanentPanel.add(chckbx13);
		
		chckbx14 = new JCheckBox("14");
		chckbx14.setBounds(84, 33, 43, 23);
		PermanentPanel.add(chckbx14);
		
		chckbx15 = new JCheckBox("15");
		chckbx15.setBounds(84, 59, 43, 23);
		PermanentPanel.add(chckbx15);
		
		chckbx16 = new JCheckBox("16");
		chckbx16.setBounds(84, 85, 43, 23);
		PermanentPanel.add(chckbx16);
		
		chckbx17 = new JCheckBox("17");
		chckbx17.setBounds(84, 111, 43, 23);
		PermanentPanel.add(chckbx17);
		
		chckbx18 = new JCheckBox("18");
		chckbx18.setBounds(84, 137, 43, 23);
		PermanentPanel.add(chckbx18);
		
		chckbx19 = new JCheckBox("19");
		chckbx19.setBounds(125, 7, 43, 23);
		PermanentPanel.add(chckbx19);
		
		chckbx20 = new JCheckBox("20");
		chckbx20.setBounds(125, 33, 43, 23);
		PermanentPanel.add(chckbx20);
		
		chckbx21 = new JCheckBox("21");
		chckbx21.setBounds(125, 59, 43, 23);
		PermanentPanel.add(chckbx21);
		
		chckbx22 = new JCheckBox("22");
		chckbx22.setBounds(125, 85, 43, 23);
		PermanentPanel.add(chckbx22);
		
		chckbx23 = new JCheckBox("23");
		chckbx23.setBounds(125, 111, 43, 23);
		PermanentPanel.add(chckbx23);
		
		chckbx24 = new JCheckBox("24");
		chckbx24.setBounds(125, 137, 43, 23);
		PermanentPanel.add(chckbx24);
		
		chckbx25 = new JCheckBox("25");
		chckbx25.setBounds(166, 7, 43, 23);
		PermanentPanel.add(chckbx25);
		
		chckbx26 = new JCheckBox("26");
		chckbx26.setBounds(166, 33, 43, 23);
		PermanentPanel.add(chckbx26);
		
		chckbx27 = new JCheckBox("27");
		chckbx27.setBounds(166, 59, 43, 23);
		PermanentPanel.add(chckbx27);
		
		chckbx28 = new JCheckBox("28");
		chckbx28.setBounds(166, 85, 43, 23);
		PermanentPanel.add(chckbx28);
		
		chckbx29 = new JCheckBox("29");
		chckbx29.setBounds(166, 111, 43, 23);
		PermanentPanel.add(chckbx29);
		
		chckbx30 = new JCheckBox("30");
		chckbx30.setBounds(166, 137, 43, 23);
		PermanentPanel.add(chckbx30);
		
		chckbx31 = new JCheckBox("31");
		chckbx31.setBounds(211, 7, 43, 23);
		PermanentPanel.add(chckbx31);
		
		chckbx32 = new JCheckBox("32");
		chckbx32.setBounds(211, 33, 43, 23);
		PermanentPanel.add(chckbx32);
		
		loadTeeth();
	}
	
	private void setButtons()
	{
		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				save();
			}
		});
		btnSave.setBounds(10, 213, 74, 23);
		contentPane.add(btnSave);
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				quit();
			}
		});
		btnCancel.setBounds(94, 213, 74, 23);
		contentPane.add(btnCancel);
		
		btnReset = new JButton("Reset Selection");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				reset();	
			}
		});
		btnReset.setBounds(10, 247, 126, 23);
		contentPane.add(btnReset);
	}
	
	private void loadTeeth()
	{
		for (Component c : PrimaryPanel.getComponents()) {
		    if (c instanceof JCheckBox) { 
		       if (primaryTeeth[toAscii(((JCheckBox)c).getText())] == '1')
		    	   ((JCheckBox) c).setSelected(true);
		    }
		}
		for (Component c : PermanentPanel.getComponents()) {
		    if (c instanceof JCheckBox) { 
		       if (permanentTeeth[Integer.parseInt(((JCheckBox)c).getText())-1] == '1')
		    	   ((JCheckBox) c).setSelected(true);
		    }
		}
	}
	
	private void save()
	{
		for (Component c : PrimaryPanel.getComponents()) {
		    if (c instanceof JCheckBox) { 
		       primaryTeeth[toAscii(((JCheckBox)c).getText())] = ((JCheckBox) c).isSelected() ? '1' : '0';
		    }
		}
		for (Component c : PermanentPanel.getComponents()) {
		    if (c instanceof JCheckBox) { 
		       permanentTeeth[Integer.parseInt(((JCheckBox)c).getText())-1] = ((JCheckBox) c).isSelected() ? '1' : '0';
		    }
		}		
		allTeeth = new String(primaryTeeth) + new String(permanentTeeth);
		setVisible(false);
	}
	
	public void quit()
	{
		int dialogResult = JOptionPane.showConfirmDialog(getRootPane(),
				"       Are you sure you cancel?\n(Any modifications will be erased.)", "Cancel Confirmation",
				JOptionPane.ERROR_MESSAGE);
		if (dialogResult == 1) {
			return;
		}
		dispose();	
	}
	
	private void reset()
	{
		int dialogResult = JOptionPane.showConfirmDialog(getRootPane(),
				"       Are you sure you reset?\n        (All data will be erased.)", "Reset Confirmation",
				JOptionPane.ERROR_MESSAGE);
		if (dialogResult == 1) {
			return;
		}
		
		for (Component c : PrimaryPanel.getComponents()) {
		    if (c instanceof JCheckBox) { 
		    	((JCheckBox) c).setSelected(false);
		    }
		}
		for (Component c : PermanentPanel.getComponents()) {
		    if (c instanceof JCheckBox) { 
		    	((JCheckBox) c).setSelected(false);
		    }
		}
	}
	
	private int toAscii(String character)
	{
		return (int)(character.charAt(0))-65;
	}
	
	public String getSelectedTeeth()
	{
		return allTeeth;
	}
}
