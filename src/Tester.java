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
		createMobileElementJson(readFile("/Users/vigneswarans/Visual Code/tcoe-project-seaton-mobile/page-objects/mobile/profile.screen.js"));
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
		List<String> listData = readFile("/Users/vigneswarans/Visual Code/tcoe-project-seaton-mobile/page-objects/mobile/profile.screen.js");
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

	@SuppressWarnings({ "unchecked" })
	public static JSONObject createMobileElementJson(List<String> incomingData) {
		JSONObject Jobj = new JSONObject();
		try {
			ArrayList <String> finalData = new ArrayList<String>();
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
							if (str.split(":")[0].trim().equalsIgnoreCase(elementData)) {
								androidElementValue = str.trim().split(elementData)[1];
							androidElementValue = androidElementValue.replaceAll(":", "").trim();
							
							int position = androidElementValue.lastIndexOf(",");
							if(position!=-1)
							androidElementValue = androidElementValue.substring(0,position);
							break;
							}
							//System.out.println("*********androidElementValue" +androidElementValue);*/
						}
						// Hanlde single quote
						/*if (androidElementValue.contains("\'"))
							androidElementValue = androidElementValue.split("\'")[1].trim();

						// Handle double quotes
						else if (androidElementValue.contains("\""))
							androidElementValue = androidElementValue.split("\"")[1].trim();
						

						androidElementValue = Pattern.compile("\"").matcher(androidElementValue).replaceAll("'");
						// elementValue= elementValue.replace('’', '\'');

						androidElementValue = Pattern.compile("[\u2018\u2019\u201a\u201b\u275b\u275c]")
								.matcher(androidElementValue).replaceAll("'");
								*/
	
					}
					if (incomingData.get(i).contains("IOS_SELECTORS")) {

						elementData = incomingData.get(i).split("IOS_SELECTORS.")[1];
						elementData = elementData.replaceAll(";", "").trim();
						elementData = elementData.replaceAll("\\)", "").trim();
						for (String str : iOSSelectors) {
							if (str.split(":")[0].trim().equalsIgnoreCase(elementData)) {
								iosElementValue = str.trim().split(elementData)[1];
								iosElementValue = iosElementValue.replaceAll(":", "").trim();
								int position = iosElementValue.lastIndexOf(",");
								if(position!=-1)
									iosElementValue = iosElementValue.substring(0,position);
									break;
							}
						// Hanlde single quote
						/*if (iosElementValue.contains("\'"))
							iosElementValue = iosElementValue.split("\'")[1].trim();
						
						// Handle double quotes
						else if (iosElementValue.contains("\""))
							iosElementValue = iosElementValue.split("\"")[1].trim();
						

						iosElementValue = Pattern.compile("\"").matcher(iosElementValue).replaceAll("'");
						// elementValue= elementValue.replace('’', '\'');

						iosElementValue = Pattern.compile("[\u2018\u2019\u201a\u201b\u275b\u275c]")
								.matcher(iosElementValue).replaceAll("'");*/
					}
					}
					if(incomingData.get(i).contains("return")) {
						/*JSONObject elementJson = new JSONObject();
						//System.out.println("elementName :" + elementName);
						//System.out.println("android : "+androidElementValue);
						//System.out.println("ios : "+iosElementValue);
						
						elementJson.put("android:", androidElementValue);
						elementJson.put("ios:", iosElementValue);
						
						Jobj.put(elementName,elementJson);
						*/
						
						finalData.add(elementName+":");
						finalData.add("{");
						
						if(androidElementValue=="")
							finalData.add("android : "+"''"+",");
						else
						finalData.add("android : "+androidElementValue+",");
						if(iosElementValue=="")
							finalData.add("ios : "+"''");
						else
						finalData.add("ios : "+iosElementValue);
						finalData.add("},");
						androidElementValue="";
						iosElementValue="";
						isGetMethod=false;
					}						
				}

			}
			for(String str:finalData)
			System.out.println(str);
		} catch (Exception e) {

			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		return Jobj;
	}
}
