import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

import org.json.simple.JSONObject;

public class Tester {

	public static void main(String[] args) {
		JSONObject jobj= createElementJson(readFile("/Users/vigneswarans/Desktop/five-things.page.js"));

	}

	public static List<String> readFile(String filePath) {
		List <String> data = new LinkedList<String>();
		try {
			File myObj = new File(filePath);
		      Scanner myReader = new Scanner(myObj);
		      while (myReader.hasNextLine()) {
		    	  data.add(myReader.nextLine());
		      }
		        myReader.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return data;
	}
	
	public static JSONObject createElementJson(List<String> incomingData) {
		JSONObject Jobj=null;
		try {
			String elementName="",elementValue="";
		for(int i=0;i<incomingData.size();i++)
		{
			if(incomingData.get(i).contains("get ")) 
				elementName= incomingData.get(i).replace("get ", "")
						.replace("()", "").replace("{","").trim();
			
			if(incomingData.get(i).contains("$")){
				String elementData = incomingData.get(i).replace("$$", "$");
				
				
				if(incomingData.get(i).contains("$(\'")) 
				elementValue=elementData.split("\\$")[1].split("\'")[1];
				
				else if(incomingData.get(i).contains("$(\"")) 
				elementValue=elementData.split("\\$")[1].split("\"")[1];
		
				else if(incomingData.get(i).contains("$(`")) 
				elementValue=elementData.split("\\$")[1].split("\\`")[1];
				
			 
			 if(elementValue.length()!=0) {
				 System.out.println(elementName);
					System.out.println(elementValue);
					
					elementName="";
					elementValue="";
					}
			}
		}
		}catch(Exception e) {
			
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		      }
		return Jobj;
	}
}
