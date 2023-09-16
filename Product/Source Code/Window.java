import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Window extends JFrame {
	
	//default constructor for a window
	public Window()
	{
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icon.png")));
	}
}


