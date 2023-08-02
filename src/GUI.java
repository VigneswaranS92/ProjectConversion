

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.text.JTextComponent;

import org.json.simple.JSONObject;


public class GUI implements ActionListener {

	public static void main(String[] args) {
		new GUI();

	}

	public GUI() {

		MyFrame mainFrame = new MyFrame();

		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		JLabel headerLabel, fname, lname, email, rmn, language, genre, boxType, addrLine1, addrLine2, spacer, spacer2,
				spacer3;
		JTextField fnameTxt, lnameTxt, emailTxt, rmnTxt, languageTxt, genreTxt, boxTypeTxt, addrLine1Txt, addrLine2Txt;
		JPanel headerPanel, fieldPanel, bodyPanel, radioBtnPanel;
		JLabel HDLabel, paymentLabel;
		ButtonGroup isHD, paymentType;
		// width will store the width of the screen
		int screenWidth = (int) size.getWidth();
		// height will store the height of the screen
		int screenHeight = (int) size.getHeight();

		ImageIcon headerImage = new ImageIcon("./resources/Tataplay-Binge.png");

		Border border = BorderFactory.createLineBorder(Color.white);
		headerLabel = new JLabel();
		headerLabel.setText("Automation Data Entry for TATAPLAY-BINGE");
		headerLabel.setHorizontalTextPosition(JLabel.LEFT);
		headerLabel.setForeground(Color.red);
		headerLabel.setFont(new Font("MV Boli", Font.BOLD, 30));
		headerLabel.setIconTextGap(80);
		headerLabel.setBorder(border);
		headerLabel.setVerticalAlignment(JLabel.TOP);
		headerLabel.setHorizontalAlignment(JLabel.CENTER);
		headerLabel.setIcon(headerImage);

		headerPanel = new JPanel();
		headerPanel.setBackground(Color.white);
		headerPanel.setSize(1000, 100);
		headerPanel.setBounds(20, 20, screenWidth - 40, 100);
		headerPanel.add(headerLabel);

		bodyPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		bodyPanel.setBackground(new Color(60, 11, 94));
		bodyPanel.setSize(screenWidth - 50, screenHeight);
		bodyPanel.setBounds(70, 150, screenWidth - 150, screenHeight);

		fieldPanel = new JPanel();
		fieldPanel.setLayout(new GridLayout(12, 3));
		fieldPanel.setBackground(new Color(60, 11, 94));
		fieldPanel.setSize((screenWidth / 2) - 250, screenHeight - 10);
		fieldPanel.setBounds(70, 150, (screenWidth / 2) - 150, screenHeight - 200);

		radioBtnPanel = new JPanel();
		radioBtnPanel.setLayout(new GridLayout(2, 3));
		// radioBtnPanel.setPreferredSize(new Dimension(100,100));
		radioBtnPanel.setBackground(new Color(60, 11, 94));

		// Test Data fields
		fname = new JLabel("First Name : ");
		fname.setForeground(Color.white);
		fname.setFont(new Font("MV Boli", Font.PLAIN, 18));
		fnameTxt = new JTextField("Enter the First Name");
		fnameTxt.setPreferredSize(new Dimension(300, 50));
		fnameTxt.setBounds(80, 100, 200, 30);
		fnameTxt.addFocusListener(new MyFocusListener());

		fieldPanel.add(fname);
		fieldPanel.add(fnameTxt);

		lname = new JLabel();
		lname.setFont(new Font("MV Boli", Font.PLAIN, 18));
		lname.setText("Last Name : ");
		lname.setForeground(Color.white);
		fieldPanel.add(lname);
		lnameTxt = new JTextField("Enter the Last Name");
		lnameTxt.addFocusListener(new MyFocusListener());
		lnameTxt.setPreferredSize(new Dimension(30, 50));
		lnameTxt.setBounds(80, 200, 200, 30);
		fieldPanel.add(lnameTxt);

		email = new JLabel();
		email.setFont(new Font("MV Boli", Font.PLAIN, 18));
		email.setForeground(Color.white);
		email.setText("Email address : ");
		fieldPanel.add(email);
		emailTxt = new JTextField("Enter the email address");
		emailTxt.addFocusListener(new MyFocusListener());
		emailTxt.setPreferredSize(new Dimension(300, 50));
		fieldPanel.add(emailTxt);

		rmn = new JLabel();
		rmn.setFont(new Font("MV Boli", Font.PLAIN, 18));
		rmn.setText("RMN : ");
		rmn.setForeground(Color.white);
		rmnTxt = new JTextField("Enter the RMN Number");
		rmnTxt.setPreferredSize(new Dimension(300, 50));
		rmnTxt.addFocusListener(new MyFocusListener());
		fieldPanel.add(rmn);
		fieldPanel.add(rmnTxt);

		language = new JLabel();
		language.setFont(new Font("MV Boli", Font.PLAIN, 18));
		language.setForeground(Color.white);
		language.setText("Language : ");
		languageTxt = new JTextField("Enter the languages.");
		languageTxt.setPreferredSize(new Dimension(300, 50));
		languageTxt.addFocusListener(new MyFocusListener());
		fieldPanel.add(language);
		fieldPanel.add(languageTxt);

		genre = new JLabel();
		genre.setFont(new Font("MV Boli", Font.PLAIN, 18));
		genre.setForeground(Color.white);
		genre.setText("Genre : ");
		genreTxt = new JTextField("Enter the Genre");
		genreTxt.setPreferredSize(new Dimension(300, 50));
		genreTxt.addFocusListener(new MyFocusListener());
		fieldPanel.add(genre);
		fieldPanel.add(genreTxt);

		boxType = new JLabel();
		boxType.setForeground(Color.white);
		boxType.setFont(new Font("MV Boli", Font.PLAIN, 18));
		boxType.setText("Box Type : ");
		boxTypeTxt = new JTextField("Enter the box type");
		boxTypeTxt.setPreferredSize(new Dimension(300, 50));
		boxTypeTxt.addFocusListener(new MyFocusListener());
		fieldPanel.add(boxType);
		fieldPanel.add(boxTypeTxt);

		addrLine1 = new JLabel();
		addrLine1.setForeground(Color.white);
		addrLine1.setFont(new Font("MV Boli", Font.PLAIN, 18));
		addrLine1.setText("Address Line 1 : ");
		addrLine1Txt = new JTextField("Enter the Address 1");
		addrLine1Txt.setPreferredSize(new Dimension(300, 50));
		addrLine1Txt.addFocusListener(new MyFocusListener());
		fieldPanel.add(addrLine1);
		fieldPanel.add(addrLine1Txt);

		addrLine2 = new JLabel();
		addrLine2.setForeground(Color.white);
		addrLine2.setFont(new Font("MV Boli", Font.PLAIN, 18));
		addrLine2.setText("Address Line 2 : ");
		addrLine2Txt = new JTextField("Enter the Address 2");
		addrLine2Txt.setPreferredSize(new Dimension(300, 50));
		addrLine2Txt.addFocusListener(new MyFocusListener());
		fieldPanel.add(addrLine2);
		fieldPanel.add(addrLine2Txt);

		// Radio buttons
		HDLabel = new JLabel();
		HDLabel.setForeground(Color.white);
		HDLabel.setText("HD Required *");
		HDLabel.setFont(new Font("MV Boli", Font.PLAIN, 18));

		isHD = new ButtonGroup();
		JRadioButton r1 = new JRadioButton("Yes");
		JRadioButton r2 = new JRadioButton("No");
		r1.setForeground(Color.white);
		r2.setForeground(Color.white);
		r1.setFont(new Font("MV Boli", Font.PLAIN, 18));
		r2.setFont(new Font("MV Boli", Font.PLAIN, 18));
		isHD.add(r1);
		isHD.add(r2);
		r1.setActionCommand("Yes");
		r2.setActionCommand("No");
		radioBtnPanel.add(HDLabel);
		radioBtnPanel.add(r1);
		radioBtnPanel.add(r2);

		paymentLabel = new JLabel();
		paymentLabel.setFont(new Font("MV Boli", Font.PLAIN, 18));
		paymentLabel.setForeground(Color.white);
		paymentLabel.setText("Payment Type *");
		paymentType = new ButtonGroup();
		JRadioButton r3 = new JRadioButton("Online");
		JRadioButton r4 = new JRadioButton("COD");
		r3.setForeground(Color.white);
		r4.setForeground(Color.white);
		r3.setFont(new Font("MV Boli", Font.PLAIN, 18));
		r4.setFont(new Font("MV Boli", Font.PLAIN, 18));
		paymentType.add(r3);
		paymentType.add(r4);
		r3.setActionCommand("Online");
		r4.setActionCommand("COD");
		radioBtnPanel.add(paymentLabel);
		radioBtnPanel.add(r3);
		radioBtnPanel.add(r4);

		// Pack Selection
		JLabel packSelection = new JLabel("Select a Pack : ");
		packSelection.setVisible(true);
		packSelection.setForeground(Color.white);
		packSelection.setFont(new Font("MV Boli", Font.PLAIN, 18));
		String[] choices ={"Premium Sports English HD 6M WBP2", "Premium Sports English HD 6M WBP1" ,"Premium Sports English HD 12M WBP1" };
		final JComboBox<String> comboBox = new JComboBox<String>(choices);
		comboBox.setMaximumSize(comboBox.getPreferredSize());
		comboBox.setEditable(false);
		fieldPanel.add(packSelection);
		fieldPanel.add(comboBox);

		spacer = new JLabel();
		spacer.setVisible(false);
		spacer2 = new JLabel();
		spacer2.setVisible(false);
		spacer3 = new JLabel();
		spacer3.setVisible(false);

		fieldPanel.add(spacer);
		fieldPanel.add(spacer2);
		fieldPanel.add(spacer3);
		JButton button = new JButton("Submit and Generate Data");
		button.addActionListener(this);
		button.setOpaque(true);
		button.setBorderPainted(false);
		button.setBackground(Color.green);
		button.addActionListener(new ActionListener() {
	         @SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent e) {
	        	 
	        	 JSONObject jsonObject = new JSONObject();
	        	 jsonObject.put("fname",fnameTxt.getText());
	        	 jsonObject.put("lname",lnameTxt.getText());
	        	 jsonObject.put("email",emailTxt.getText());
	        	 jsonObject.put("rmn",rmnTxt.getText());
	        	 jsonObject.put("language",languageTxt.getText());
	        	 jsonObject.put("genre",genreTxt.getText());
	        	 jsonObject.put("boxType",boxTypeTxt.getText());
	        	 jsonObject.put("addrline1",addrLine1Txt.getText());
	        	 jsonObject.put("addrLine2",addrLine2Txt.getText());
	        	 jsonObject.put("isHD",isHD.getSelection().getActionCommand());//
	        	 jsonObject.put("paymentType",paymentType.getSelection().getActionCommand());//
	        	 jsonObject.put("pack",comboBox.getSelectedItem().toString()); //
	        	 
	        	 new generateTestData().generateJSON(jsonObject);
	          }	
	       });
		fieldPanel.add(button);

		mainFrame.add(headerPanel);
		bodyPanel.add(fieldPanel);
		bodyPanel.add(radioBtnPanel);
		mainFrame.add(bodyPanel);
		mainFrame.setLayout(null);

		mainFrame.pack();
		mainFrame.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}

class MyFocusListener extends FocusAdapter {
	@Override
	public void focusGained(FocusEvent fEvt) {
		JTextComponent component = (JTextComponent) fEvt.getSource();
		component.setText("");
	}
}

class generateTestData {
	public void generateJSON(JSONObject data){
		try {
		      File myObj = new File("testData.json");
		      if (myObj.createNewFile()) {
		        System.out.println("File created: " + myObj.getName());
		        writeFile( myObj, data);
		        executeShell();
		      } else {
		        System.out.println("File already exists.");
		        deleteFile(myObj);
		        writeFile( myObj, data);
		        executeShell();
		      }
		    } catch (IOException e) {
		    	JOptionPane.showMessageDialog(null,"An error while creating Test Data. Please contact admin.");
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }	
	}
	
	
	public void deleteFile(File fs) {
		    File myObj = fs;
		    if (myObj.delete()) { 
		      System.out.println("Deleted the file: " + myObj.getName());
		      JOptionPane.showMessageDialog(null,"Old Test Data is Deleted. Creating a new one.");
		    } else {
		    	JOptionPane.showMessageDialog(null,"Failed to delete the Test Data.");
		      System.out.println("Failed to delete the file.");
		    } 
		  }
	
	public void writeFile( File fs, JSONObject data) {
		try {
		      FileWriter myWriter = new FileWriter("testData.json");
			  myWriter.write(data.toJSONString());
			  myWriter.close();
		      JOptionPane.showMessageDialog(null,"Test Data Successfully Created.");
		      System.out.println("Successfully wrote to the file.");
		     
		    } catch (IOException e) {
		    	JOptionPane.showMessageDialog(null,"An error while creating Test Data. Please contact admin.");
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
	}
	
	
	public void executeShell() throws IOException {

		/*String[] args = new String[]{"/bin/bash", "-c", 
				"mvn -f \"/Users/vigneswarans/eclipse-workspace/TATA PLAY/TataPlay-master/pom.xml\" clean compile exec:java test -DScenarioSheet=UM_Scenarios_Sheet -DResFolder=resources"
				, "with", "args"};
		
		String[] mvnCmd =new String[]{"mvn -f \"/Users/vigneswarans/eclipse-workspace/TATA PLAY/TataPlay-master/pom.xml\" clean compile exec:java test -DScenarioSheet=UM_Scenarios_Sheet -DResFolder=resources"
				};
		try {
			
			Process proc = new ProcessBuilder(mvnCmd).start();
			System.out.println(proc.getOutputStream());
			proc.waitFor();
		BufferedReader reader =  
	              new BufferedReader(new InputStreamReader(proc.getInputStream()));

	        String line = "";
	        while((line = reader.readLine()) != null) {
	            System.out.print(line + "\n");
	        }

	        
		}
		catch(Exception e) {
			e.printStackTrace();
		}*/
		
		
		/*try {
			//Process P =Runtime.getRuntime().exec("/usr/bin/open -a Terminal " +System.getProperty("user.dir"));
			Runtime r = Runtime.getRuntime(); 
	        r.exec("/usr/bin/open -a Terminal " +System.getProperty("user.dir"));
	        
	        r.exec("sh mvn.sh");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/

		//String[] mvnCmd =new String[]{"mvn -f \""+System.getProperty("user.dir")+"/pom.xml\" clean compile exec:java test -DScenarioSheet=UM_Scenarios_Sheet -DResFolder=resources"};
		
		String[] cmd = new String[] {System.getProperty("user.dir")+"/mvn.sh"};
				
		try {
			ProcessBuilder pb= new ProcessBuilder(cmd);
			pb.inheritIO();
			Process process =pb.start();
			JOptionPane.showMessageDialog(null,"Automation Script is running..");
			process.waitFor();
			BufferedReader reader =  
		              new BufferedReader(new InputStreamReader(process.getInputStream()));

		        String line = "";
		        while((line = reader.readLine()) != null) {
		            System.out.print(line + "\n");
		        }
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null,"An error while creating Test Data. Please contact admin.");
			e.printStackTrace();
		}
	}
	}