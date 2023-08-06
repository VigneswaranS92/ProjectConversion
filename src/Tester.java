import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Tester {
	static List<String> androidSelectors;
	static List<String> iOSSelectors;

	public static void main(String[] args) {
		// JSONObject jobj=
		// createElementJson(readFile("/Users/vigneswarans/Desktop/audio.screen.js"));
		createiOSandAndroidSelectorsList();
		createMobileElementJson(readFile("/Users/vigneswarans/Desktop/audio.screen.js"));
	}

	public static List<String> readFile(String filePath) {
		List<String> data = new LinkedList<String>();
		try {
			File myObj = new File(filePath);
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				data.add(myReader.nextLine());
			}
			myReader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	public static void createiOSandAndroidSelectorsList() {
		List<String> listData = readFile("/Users/vigneswarans/Desktop/audio.screen.js");
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
		/*for(String str:androidSelectors)
			System.out.println(str);
		for(String str:iOSSelectors)
			System.out.println(str);*/

	}

	@SuppressWarnings({ "unchecked" })
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
						System.out.println(elementData);
						for (String str : androidSelectors) {

							if (str.contains(elementData))
								androidElementValue = str.trim().split(elementData)[1];
							androidElementValue = androidElementValue.replaceAll(":", "").trim();
							int position = androidElementValue.lastIndexOf(",");
							System.out.println(position);
							if(position!=-1)
							androidElementValue = androidElementValue.substring(0,position+1);
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
						//System.out.println("elementName :" + elementName);
						//System.out.println("android : "+androidElementValue);
						//System.out.println("ios : "+iosElementValue);
						
						elementJson.put("android:", androidElementValue);
						elementJson.put("ios:", iosElementValue);
						
						Jobj.put(elementName,elementJson);
						
						androidElementValue="";
						iosElementValue="";
						isGetMethod=false;
					}						
				}

			}
			Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();
			String jsonOutput = gson.toJson(Jobj);
			System.out.println(jsonOutput);
		} catch (Exception e) {

			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		return Jobj;
	}
}
