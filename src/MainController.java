import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import javax.swing.text.JTextComponent;

import org.json.simple.JSONObject;

public class MainController implements ActionListener {
	File JSFile = null;
	String selectedFileName = "";
	JLabel selectedFile, uploadFile;
	final JComboBox<String> comboBox ;
	static List<String> androidSelectors;
	static List<String> iOSSelectors;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MainController();
	}

	public MainController() {

		MyFrame mainFrame = new MyFrame();
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		JLabel headerLabel, fname;

		JPanel headerPanel, bodyPanel, bodyPanel2;
		JTextField fnameTxt;

		// width will store the width of the screen
		int screenWidth = (int) size.getWidth();
		// height will store the height of the screen
		int screenHeight = (int) size.getHeight();

		Border border = BorderFactory.createLineBorder(Color.white);
		headerLabel = new JLabel();
		headerLabel.setText("JSON Creator for Automation Framework Migration");
		headerLabel.setHorizontalTextPosition(JLabel.LEFT);
		headerLabel.setForeground(Color.BLACK);
		headerLabel.setFont(new Font("MV Boli", Font.BOLD, 30));
		headerLabel.setIconTextGap(80);
		headerLabel.setBorder(border);
		headerLabel.setVerticalAlignment(JLabel.TOP);
		headerLabel.setHorizontalAlignment(JLabel.CENTER);

		headerPanel = new JPanel();
		headerPanel.setBackground(Color.white);
		headerPanel.setSize(3, 1);
		headerPanel.setBounds(20, 20, screenWidth - 40, 100);
		headerPanel.add(headerLabel);

		bodyPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		bodyPanel.setBackground(new Color(102, 153, 235));
		bodyPanel.setSize(screenWidth - 50, screenHeight / 6);
		bodyPanel.setBounds(70, 150, screenWidth - 150, screenHeight / 12);

		bodyPanel2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		bodyPanel2.setBackground(new Color(102, 153, 235));
		bodyPanel2.setSize(screenWidth, screenHeight);
		bodyPanel2.setBounds(70, 250, screenWidth - 150, screenHeight);

		selectedFile = new JLabel();
		selectedFile.setForeground(Color.white);
		selectedFile.setFont(new Font("MV Boli", Font.PLAIN, 18));

		uploadFile = new JLabel("Select a JS File to Upload :   ");
		uploadFile.setForeground(Color.white);
		uploadFile.setFont(new Font("MV Boli", Font.PLAIN, 18));
		
		JButton selectFileButton = new JButton("Upload");
		selectFileButton.addActionListener(this);
		selectFileButton.setOpaque(true);

		selectFileButton.setBorderPainted(false);
		selectFileButton.setBackground(new Color(153, 255, 153));
		selectFileButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JSFile = uploadAFile();
				selectedFile.setText("  Selected File : "+JSFile.getAbsolutePath());
			}
		});

		selectFileButton.addMouseListener(new MouseListener() {
			@Override
			public void mouseEntered(MouseEvent e) {
				selectFileButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});

		fname = new JLabel("File Name *: ");
		fname.setForeground(Color.white);
		fname.setFont(new Font("MV Boli", Font.PLAIN, 18));
		fnameTxt = new JTextField("Enter the name of the converted file");
		fnameTxt.setPreferredSize(new Dimension(300, 40));
		fnameTxt.setBounds(80, 100, 200, 30);
		fnameTxt.addFocusListener(new MyFocusListener());

		JButton convertFileButton = new JButton("START CONVERSION");
		convertFileButton.addActionListener(this);
		convertFileButton.setOpaque(true);

		convertFileButton.setBorderPainted(false);
		convertFileButton.setBackground(new Color(255, 102, 153));
		convertFileButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String newFileName = fnameTxt.getText();
				JSONObject obj=null;
				if(comboBox.getSelectedItem().toString().equalsIgnoreCase("web")) {
				if(JSFile!=null) {
				System.out.println("In file conversion..");
				 obj = createElementJson(readFile(JSFile.getAbsolutePath()));
				}
				else 
					JOptionPane.showMessageDialog(null, "No files are selected. Please try again.");
				}
				else if(comboBox.getSelectedItem().toString().equalsIgnoreCase("mobile")) {
					System.out.println("In Mobile file conversion..");
					createiOSandAndroidSelectorsList(JSFile.getAbsolutePath());
					 obj = createMobileElementJson(readFile(JSFile.getAbsolutePath()));
					}
				writeFile(newFileName, obj);
			}
		});
		
		JLabel deviceSelection = new JLabel("Select the device : ");
		deviceSelection.setVisible(true);
		deviceSelection.setForeground(Color.white);
		deviceSelection.setFont(new Font("MV Boli", Font.PLAIN, 18));
		String[] choices ={"Web","Mobile"};
		comboBox = new JComboBox<String>(choices);
		comboBox.setMaximumSize(comboBox.getPreferredSize());
		comboBox.setEditable(false);
		
		bodyPanel.add(uploadFile);
		bodyPanel.add(selectFileButton);
		bodyPanel.add(selectedFile);
		
		bodyPanel2.add(fname);
		bodyPanel2.add(fnameTxt);
		bodyPanel2.add(deviceSelection);
		bodyPanel2.add(comboBox);
		bodyPanel2.add(convertFileButton);

		mainFrame.add(headerPanel);
		mainFrame.add(bodyPanel);
		mainFrame.add(bodyPanel2);
		mainFrame.setLayout(null);
		mainFrame.pack();
		mainFrame.setVisible(true);

	}

	public File uploadAFile() {
		File selectedFile = null;

		try {
			JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

			int returnValue = jfc.showOpenDialog(null);
			jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Javascript files", "js");
			jfc.addChoosableFileFilter(filter);

			jfc.setMultiSelectionEnabled(false);

			if (returnValue == JFileChooser.APPROVE_OPTION) {
				selectedFile = jfc.getSelectedFile();
				System.out.println(selectedFile.getAbsolutePath());
			}
			if (selectedFile != null) {
				JOptionPane.showMessageDialog(null, "File Uploaded successfully for conversion.");
				selectedFileName = selectedFile.getName();
			} else
				JOptionPane.showMessageDialog(null, "Failed to upload the file. Please try again !!");
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Failed to upload the file.");
		}

		return selectedFile;
	}

	public static List<String> readFile(String filePath) {
		List<String> data = new LinkedList<String>();
		try {
			File myObj = new File(filePath);
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				data.add(myReader.nextLine());
				//System.out.println(data);
			}
			myReader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	@SuppressWarnings({ "unchecked" })
	public JSONObject createElementJson(List<String> incomingData) {
		JSONObject Jobj = new JSONObject();
		try {
			String elementName = "", elementValue = "";
			for (int i = 0; i < incomingData.size(); i++) {
				if (incomingData.get(i).contains("get "))
					elementName = incomingData.get(i).replace("get ", "").replace("()", "").replace("{", "").trim();

				if (incomingData.get(i).contains("$")) {

					String elementData = incomingData.get(i).replace("$$", "$");
					// Hanlde single quote
					if (incomingData.get(i).contains("$(\'"))
						elementValue = elementData.split("\\$")[1].split("\'")[1].trim();

					// Handle double quotes
					else if (incomingData.get(i).contains("$(\""))
						elementValue = elementData.split("\\$")[1].split("\"")[1].trim();

					// Hanlde single quote mac
					else if (incomingData.get(i).contains("$(`"))
						elementValue = elementData.split("\\$")[1].split("\\`")[1].trim();
					
					
					elementValue= Pattern.compile("\"").matcher(elementValue).replaceAll("'");
					//elementValue= elementValue.replace('’', '\'');
					
					elementValue = Pattern.compile("[\u2018\u2019\u201a\u201b\u275b\u275c]").matcher(elementValue).replaceAll("'");
					
					if (elementValue.length() != 0) {
						Jobj.put(elementName, elementValue);
						System.out.println(elementName);
						System.out.println(elementValue);
						elementName = "";
						elementValue = "";
					}
				}

			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "An error while doing parsing. Please contact the developer.");
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		return Jobj;
	}

	
	public static void createiOSandAndroidSelectorsList(String filePath) {
		List<String> listData = readFile(filePath);
		androidSelectors = new ArrayList<String>();
		iOSSelectors = new ArrayList<String>();
		for (int i = 0; i < listData.size(); i++) {
			if (listData.get(i).contains("ANDROID_SELECTORS")) {
				int j = 0;
				for (j = i; j < listData.size(); j++) {
					androidSelectors.add(listData.get(j));
					if (listData.get(j).contains("};"))
						break;
				}
				break;
			}
		}
		for (int i = 0; i < listData.size(); i++) {
			if (listData.get(i).contains("IOS_SELECTORS")) {
				int j = 0;
				for (j = i; j < listData.size(); j++) {
					iOSSelectors.add(listData.get(j));
					if (listData.get(j).contains("};"))
						break;
				}
				break;
			}
		}

	}
	
	@SuppressWarnings({ "unchecked"})
	public static JSONObject createMobileElementJson(List<String> incomingData) {
		JSONObject Jobj = new JSONObject();
		try {
			String elementName = "", androidElementValue = "", iosElementValue = "", elementData = "";
			boolean isGetMethod = false;
			for (int i = 0; i < incomingData.size(); i++) {
				if (incomingData.get(i).contains("get ")) {
					elementName = incomingData.get(i).replace("get ", "").replace("()", "").replace("{", "").trim();
					isGetMethod = true;
				}
				if (isGetMethod) {

					if (incomingData.get(i).contains("ANDROID_SELECTORS")) {

						elementData = incomingData.get(i).split("ANDROID_SELECTORS.")[1];
						elementData = elementData.replaceAll(";", "").trim();
						elementData = elementData.replaceAll("\\)", "").trim();
						for (String str : androidSelectors) {

							if (str.contains(elementData))
								androidElementValue = str.trim().split(elementData)[1];
							androidElementValue = androidElementValue.replaceAll(":", "").replace(",", "").trim();
							androidElementValue = androidElementValue.replaceAll(";", "").replace(",", "").trim();
						}
						// Hanlde single quote
						if (androidElementValue.contains("\'"))
							androidElementValue = androidElementValue.split("\'")[1].trim();

						// Handle double quotes
						else if (androidElementValue.contains("\""))
							androidElementValue = androidElementValue.split("\"")[1].trim();
						

						androidElementValue = Pattern.compile("\"").matcher(androidElementValue).replaceAll("'");
						// elementValue= elementValue.replace('’', '\'');

						androidElementValue = Pattern.compile("[\u2018\u2019\u201a\u201b\u275b\u275c]")
								.matcher(androidElementValue).replaceAll("'");
	
					}
					if (incomingData.get(i).contains("IOS_SELECTORS")) {

						elementData = incomingData.get(i).split("IOS_SELECTORS.")[1];
						elementData = elementData.replaceAll(";", "").trim();
						elementData = elementData.replaceAll("\\)", "").trim();
						
						for (String str : iOSSelectors) {
							if (str.contains(elementData))
								iosElementValue = str.trim().split(elementData)[1];
							iosElementValue = iosElementValue.replaceAll(":", "").replace(",", "").trim();
							
							
						}
						// Hanlde single quote
						if (iosElementValue.contains("\'"))
							iosElementValue = iosElementValue.split("\'")[1].trim();
						
						// Handle double quotes
						else if (iosElementValue.contains("\""))
							iosElementValue = iosElementValue.split("\"")[1].trim();
						

						iosElementValue = Pattern.compile("\"").matcher(iosElementValue).replaceAll("'");
						// elementValue= elementValue.replace('’', '\'');

						iosElementValue = Pattern.compile("[\u2018\u2019\u201a\u201b\u275b\u275c]")
								.matcher(iosElementValue).replaceAll("'");
						
					}
					
					if(incomingData.get(i).contains("return")) {
						JSONObject elementJson = new JSONObject();
						System.out.println("elementName :" + elementName);
						System.out.println("android : "+androidElementValue);
						System.out.println("ios : "+iosElementValue);
						
						elementJson.put("android:", androidElementValue);
						elementJson.put("ios:", iosElementValue);
						
						Jobj.put(elementName,elementJson);
						
						androidElementValue="";
						iosElementValue="";
						isGetMethod=false;
					}						
				}

			}
		} catch (Exception e) {

			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		return Jobj;
	}
	@SuppressWarnings("unchecked")
	public void writeFile(String fileName, JSONObject data) {
		try {
			JSONObject mainObj = new JSONObject();
			FileWriter myWriter =null;
			mainObj.put(fileName, data);
			if(comboBox.getSelectedItem().toString().equalsIgnoreCase("web"))
			 myWriter = new FileWriter(fileName+"_JSON_WebElements.json");
			else
			 myWriter = new FileWriter(fileName+"_JSON_MobileElements.json");
			myWriter.write(mainObj.toJSONString());
			myWriter.close();
			JOptionPane.showMessageDialog(null, "JSON Successfully Created.");
			System.out.println("JSON Successfully Created.");

		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "An error while doing conversion. Please contact the developer.");
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

	class MyFocusListener extends FocusAdapter {
		@Override
		public void focusGained(FocusEvent fEvt) {
			JTextComponent component = (JTextComponent) fEvt.getSource();
			component.setText("");
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
