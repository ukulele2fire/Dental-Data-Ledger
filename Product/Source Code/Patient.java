public class Patient implements Comparable
{
	//array constants of the valid types for each data category
	public static final String[] CATEGORIES = new String[] { "Last Name", "First Name", "D.O.B", "Sex","Procedure","Material","Bonding","Color" };
	public static final String[] SEX = new String[] {"-Please Select-", "Male", "Female", "Other"};
	public static final String[] PROCEDURES = new String[] { "-Please Select-", "Crown", "Filling", "Other"};
	public static final String[] MATERIALS = new String[] { "-Please Select-", "Flowable Composite", "Packable Composite", "Zirconia", "EMAX", "Other"};
	public static final String[] BONDINGS = new String[] { "-Please Select-", "Adhesive", "Cement" , "Other"};
	public static final String[] COLORS = new String[] { "-Please Select-", "B1", "A1", "A2", "A3", "A3.5", "BL", "Other"};
	
	
	//basic info
	private String myFirstName;
	private String myLastName;
	private String mySex;
	
	//DOB info
	private int myDay;
	private int myMonth;
	private int myYear;
	
	//procedure info
	private String myProcedure;
	private String myTeethToProcedure;
	private String myColor;
	private String myMaterial;
	private String myBonding;
	
	//other
	private String myNotes;
	
	public Patient()
	{
		//gives essentially NULL values to each data value
		myFirstName = "First Name";
		myLastName = "Last Name";
		mySex = "-Please Select-";
		myProcedure = "-Please Select-";
		myTeethToProcedure = "0000000000000000000000000000000000000000000000000000";
		myColor = "-Please Select-";
		myMaterial = "-Please Select-";
		myBonding = "-Please Select-";
		myDay = 1;
		myMonth = 1;
		myYear= 2001;
		myNotes = "";
	}
	
	public Patient(String firstName, String lastName, String sex, String procedure, String teethToProcedure, 
			String color, String material, String bonding, int day, int month, int year, String notes)
	{
		myFirstName = firstName;
		myLastName = lastName;
		mySex = sex;
		myProcedure = procedure;
		myTeethToProcedure = teethToProcedure;
		myColor = color;
		myMaterial = material;	
		myBonding = bonding;
		myDay = day;
		myMonth = month;
		myYear = year;
		myNotes = notes;
	}
	
	////////////////////    getter methods  //////////////////////////////////
	public String getFirstName() {return myFirstName;}
	
	public String getLastName() {return myLastName;}
	
	public String getSex()  {return mySex;}

	public String getProcedure() {return myProcedure;}

	public String getColor() {return myColor;}
	
	public String getMaterial() {return myMaterial;}

	public String getBonding() {return myBonding;}
	
	public String getTeethToProcedure() {return myTeethToProcedure;}
	
	public int getDay() {return myDay;}
	
	public int getMonth() {return myMonth;}
	
	public int getYear() {return myYear;}
	
	public String getNotes() {return myNotes;}
	
	///////////////////////////////////////////////////////////////////////////////
	
	//////////////////////////       setter methods   //////////////////////////////
	public void setFirstName(String firstName) {myFirstName = firstName;}
	
	public void setLastName(String lastName) {myLastName = lastName;}
	
	public void setSex(String sex) {mySex = sex;}
	
	public void setProcedure(String procedure) {myProcedure = procedure;}
	
	public void setColor(String color) {myColor = color;}
	
	public void setMaterial(String material) {myMaterial = material;}
	
	public void setBonding(String bonding) {myBonding = bonding;}
	
	public void setTeethToProcedure(String teethToProcedure) {myTeethToProcedure = teethToProcedure;}
	
	public void setDay(int day) {myDay = day;}
	
	public void setMonth(int month) {myMonth = month;}
	
	public void setYear(int year) {myYear = year;}

	public void setNotes(String notes) {myNotes = notes;}
	
	/////////////////////////////////////////////////////////////////////////////////
	
	//returns patient data in the form of a string (mostly for saving to text file)
	public String toString()
	{
		return myFirstName + "\n" + myLastName + "\n" + mySex + "\n" + myProcedure + "\n" + 
			   myColor + "\n" + myBonding + "\n" + myMaterial + "\n" + myTeethToProcedure + "\n"
			   + myDay + "\n" + myMonth + "\n" + myYear + "\n" + Database.toHexString(myNotes) + "\n";
	}
	
	//compares patients based on their full names alphabetically
	public int compareTo(Object other) 
	{
		if (this.myLastName.compareTo(((Patient)other).getLastName()) != 0)
			return this.myLastName.compareTo(((Patient)other).getLastName());
		else
			return this.myFirstName.compareTo(((Patient)other).getFirstName());
	}
	
	//ordered based on sorting priority for the main menu's sort features
	public String[] getPatientInfo()
	{
		return new String[]{myLastName, myFirstName, myMonth + "/" + myDay + "/" + myYear, mySex, myProcedure,
				   myMaterial, myBonding, myColor,  myTeethToProcedure,
				   ""+myDay, ""+myMonth, ""+myYear};
	}
	
}
