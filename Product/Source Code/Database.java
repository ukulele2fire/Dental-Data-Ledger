import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Database 
{
	public static FileNameExtensionFilter filter = new FileNameExtensionFilter("Image File","png","jpg","jfif","jpeg","gif");
	private static PriorityQueue<Patient> myPatients  = new PriorityQueue<Patient>();
	private static String myFile = "patientData.txt";
	
	private Database()
	{
		//no variables to initialize
	}
	
	public static void createImageFolders()
	{
		File picDir = new File("Pictures/");
		File tempDir = new File("TempPics/");
		
		if (!picDir.exists())
			picDir.mkdirs();
		if (!tempDir.exists())
			tempDir.mkdirs();
	}
	
	//saves patient data to the text file
	public static void saveData()
	{
		//save patients
		Iterator<Patient> iter = myPatients.iterator();
		PrintWriter writer;
		try
		{
			writer = new PrintWriter(new File(myFile));
			while(iter.hasNext())
			{
				Patient pat = iter.next();
				System.out.println(pat);
				writer.print(pat.toString());
			}
			writer.close();
		}
		catch (Exception e)
		{
			System.out.println("Error: " + e.getMessage());
		}
		printList();
		
		
		//save photos
		File tempDir = new File("TempPics/");
		File saveDir = new File ("Pictures/");
		File[] tempPics = tempDir.listFiles();
		if (tempPics != null)
		{
			for (File pic: tempPics)
			{
				String oldPath = pic.getPath();
				String newPath = saveDir.getAbsolutePath() + "/" + pic.getName();
				System.out.println(oldPath + ", " + newPath);
				try {
					Files.copy(Paths.get(oldPath), Paths.get(newPath), StandardCopyOption.REPLACE_EXISTING);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			deleteTempPics();
		}
	}
	
	//loads patient data from the text file
	public static void loadData()
	{
		Scanner scanner;
		myPatients = new PriorityQueue<Patient>();
		try 
		{
			scanner = new Scanner(new File(myFile));
			while (scanner.hasNext())
			{
				String firstName = scanner.nextLine();
				String lastName = scanner.nextLine();
				String sex = scanner.nextLine();
				String procedure = scanner.nextLine();
				String color = scanner.nextLine();
				String bonding = scanner.nextLine();
				String material = scanner.nextLine();
				String teethToProcedure = (scanner.nextLine());
				int day = Integer.parseInt(scanner.nextLine());
				int month = Integer.parseInt(scanner.nextLine());
				int year = Integer.parseInt(scanner.nextLine());
				String notes = fromHexString(scanner.nextLine());
				
				Patient pat = new Patient(firstName,lastName,sex,procedure,
						teethToProcedure,color,material,bonding,day,month,year, notes);
				myPatients.add(pat);
			}
			scanner.close();
		}
		catch (Exception e)
		{
			System.out.println("Error: " + e.getMessage());
		}
	}
	
	//returns list of patients from the database in the form of an ArrayList
	public static ArrayList<Patient> provideList()
	{
		ArrayList<Patient> patientList = new ArrayList<Patient>();
		PriorityQueue<Patient> patientQueue = new PriorityQueue<Patient>(myPatients);
		while (!patientQueue.isEmpty())
			patientList.add(patientQueue.remove());
		return patientList;
	}
	
	//adds patient data to the database
	public static void addData(Patient pat)
	{
		myPatients.add(pat);
	}
	
	//delete patient from the database
	public static void deleteData(Patient pat)
	{
		myPatients.remove(pat);
		
		//removes the photo of patient whether it has been saved or not
		for (String extension: filter.getExtensions())
		{
			File picture1 = new File("Pictures/" + pat.getLastName() + pat.getFirstName() + pat.getMonth() + pat.getDay() + pat.getYear() + "." + extension);
			File picture2 = new File("TempPics/" + pat.getLastName() + pat.getFirstName() + pat.getMonth() + pat.getDay() + pat.getYear() + "." + extension);
			picture1.delete();
			picture2.delete();
		}
		
	}
	
	//edits patient data by removing original patient and add the new patient
	public static void editData(Patient original, Patient editted)
	{
		deleteData(original);
		addData(editted);
	}
	
	//delete pictures that have not been saved
	public static void deleteTempPics()
	{
		File dir = new File("TempPics/");
		File[] tempPics = dir.listFiles();
		if (tempPics != null)
		{
			for (File pic: tempPics)
			{
				pic.delete();
			}
		}
	}
	
	//resets contents of the database
	public static void reset()
	{
		myPatients = new PriorityQueue<Patient>();
	}
	
	//debug method to check contents of the priority queue database as the program is running
	public static void printList()
	{
		loadData();
		Iterator<Patient> iter = myPatients.iterator();
		while (iter.hasNext())
		{
			System.out.println(iter.next());
		}
	}
	
	//methods to convert between hex and string (utf-8)
	//source: https://stackoverflow.com/questions/923863/converting-a-string-to-hexadecimal-in-java
	
	public static String toHexString(String text) {
	    StringBuilder str = new StringBuilder();
	    byte[] ba = text.getBytes(StandardCharsets.UTF_8);
	    for(int i = 0; i < ba.length; i++)
	        str.append(String.format("%02x", ba[i]));
	    if (str.length() == 0)
	    	return "";
	    else
	    	return str.toString();
	  }

	  public static String fromHexString(String hex) {
		if (hex.length() == 0)
			return "";
	    StringBuilder str = new StringBuilder();
	    for (int i = 0; i < hex.length(); i+=2) {
	      str.append((char) Integer.parseInt(hex.substring(i, i + 2), 16));
	    }
	    return str.toString();
	  }
	  
	  public static BufferedImage loadPhoto(Patient pat)
		{			
			BufferedImage bufferedImage = null;
			
			try {
				bufferedImage = ImageIO.read(Database.class.getResourceAsStream("Default.png"));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			File picFile = findImageFile(pat);
			if (picFile != null)
			{
				try {
					bufferedImage = ImageIO.read(picFile);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return bufferedImage;
		}
	  
	  public static File findImageFile(Patient pat)
	  {
		  String header =  pat.getLastName() + pat.getFirstName() + 
					pat.getMonth() + pat.getDay() + pat.getYear();
		  for (String possibleFormat: filter.getExtensions())
			{
				File savedPic = new File("Pictures/" + header + "." + possibleFormat);
				File tempPic = new File("TempPics/" + header + "." + possibleFormat);
				
				if (savedPic.exists())
				{
					return savedPic;
				}
				else if (tempPic.exists())
				{
					return tempPic;
				}
				else
					System.out.println(possibleFormat + " does not exist");
			}
		  return null;
	  }
	
}
