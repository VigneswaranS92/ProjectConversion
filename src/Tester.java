import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.json.simple.JSONObject;

public class Tester {

	Map<String,String> androidSelectors;
	Map<String,String> iosSelectors;
	public static void main(String[] args) {
		JSONObject jobj= createElementJson(readFile("/Users/vigneswarans/Desktop/audio.screen.js"));

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
			String elementName="",elementValue="",elementData="";
		for(int i=0;i<incomingData.size();i++)
		{
			if(incomingData.get(i).contains("get ")) 
				elementName= incomingData.get(i).replace("get ", "")
						.replace("()", "").replace("{","").trim();
			System.out.println(elementName);
			
			if(incomingData.get(i).contains("ANDROID.SELECTORS")) {
				elementData = incomingData.get(i).replace(";", "");
				elementData = elementData.split("ANDROID.SELECTORS")[0];
				elementData = incomingData.get(i).replace(".", "");
				System.out.println(elementData);
			}
				
			if(incomingData.get(i).contains("IOS.SELECTORS")){
				elementData = incomingData.get(i).replace(";", "");
				elementData = elementData.split("IOS.SELECTORS")[1];
				elementData = incomingData.get(i).replace(".", "");
				System.out.println(elementData);
			}
			
			 
			/* if(elementValue.length()!=0) {
				 System.out.println(elementName);
					System.out.println(elementValue);
					
					elementName="";
					elementValue="";
			}*/
			}
		}catch(Exception e) {
			
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		  }
		return Jobj;
	}
}
