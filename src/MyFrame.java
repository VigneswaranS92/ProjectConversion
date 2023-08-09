import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class MyFrame extends JFrame{

	MyFrame(){
		
		this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		this.setTitle( "Automation Migration Helper");
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLayout(new FlowLayout());
		
		//ImageIcon image = new ImageIcon(getClass().getClassLoader().getResource("Tataplay-Binge.png"));
		//this.setIconImage(image.getImage());
		
		this.getContentPane().setBackground(Color.black); // Change the background color
		
	}
	
}
