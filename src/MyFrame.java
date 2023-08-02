import java.awt.FlowLayout;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class MyFrame extends JFrame{

	MyFrame(){
		
		this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		this.setTitle( "Generic Automation Test Script Generator");
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLayout(new FlowLayout());
		
		//ImageIcon image = new ImageIcon(getClass().getClassLoader().getResource("Tataplay-Binge.png"));
		//this.setIconImage(image.getImage());
		
		//this.getContentPane().setBackground(new Color(60, 11, 94)); // Change the background color
		
	}
	
}
