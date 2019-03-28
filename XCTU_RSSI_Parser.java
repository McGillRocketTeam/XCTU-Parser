import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.File;

public class XCTU_RSSI_Parser {
	public static void main(String[] args) {
		String in_file = (args[0]);
		String path = Paths.get(in_file).getParent().toString();
		String out_file = (new StringBuilder()).append(path).append("/").append("decodedRSSIValues.txt").toString();
		String rawData = readFileAsString(in_file);
		
		StringBuilder sb = new StringBuilder();

		//Strip out non essential data
		rawData = simplifyString(rawData);
		
		String[] result = rawData.split("\n");
	     for (int x=0; x<result.length; x++) {
	    	 
	    	 if(result[x].length() == 24) {
	    		 String number = new String (result[x].substring(20,24));
	    		 Integer decimalRSSI = Integer.parseInt(hexToAscii(number), 16);
	    	
	    		 String newString = new String(result[x].substring(0,20) + "-" + decimalRSSI);
	    		 
	    		 sb.append(newString);
	    		 sb.append("\n");
	
	    	 }
	     }
	     
	     String s = sb.toString();
	     //System.out.println(s);
	     
	     try (PrintWriter out = new PrintWriter(new File(out_file))) {
	    	    out.write(s);
	    	} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
	}
		

	public static String readFileAsString(String filename) {
	
		String text = "";
		
		try { 
			text = new String(Files.readAllBytes(Paths.get(filename)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return text;
	}
	
	
	private static String hexToAscii(String hexStr) {
	    StringBuilder output = new StringBuilder("");
	     
	    for (int i = 0; i < hexStr.length(); i += 2) {
	        String str = hexStr.substring(i, i + 2);
	        output.append((char) Integer.parseInt(str, 16));
	    }
	    return output.toString();
	}
	
	
	public static String simplifyString(String rawData) {
		
			//Strip out the +++
			rawData = rawData.replaceAll("(\\d\\d-\\d\\d-\\d\\d\\d\\d \\d\\d:\\d\\d:\\d\\d.\\d\\d\\d,\\d\\d\\d\\d\\d,SENT,2B2B2B)+", "");
				
			//Strip out OK
			rawData = rawData.replaceAll("(\\d\\d-\\d\\d-\\d\\d\\d\\d \\d\\d:\\d\\d:\\d\\d.\\d\\d\\d,\\d\\d\\d\\d\\d,RECV,4F4B0D)+", "");
				
			//Strip out other versions of OK
			rawData = rawData.replaceAll("(\\d\\d-\\d\\d-\\d\\d\\d\\d \\d\\d:\\d\\d:\\d\\d.\\d\\d\\d,\\d\\d\\d\\d\\d,RECV,4F4B)+", "");
				
			rawData = rawData.replaceAll("(\\d\\d-\\d\\d-\\d\\d\\d\\d \\d\\d:\\d\\d:\\d\\d.\\d\\d\\d,\\d\\d\\d\\d\\d,RECV,4F)+", "");
				
			rawData = rawData.replaceAll("(\\d\\d-\\d\\d-\\d\\d\\d\\d \\d\\d:\\d\\d:\\d\\d.\\d\\d\\d,\\d\\d\\d\\d\\d,RECV,4B)+", "");
				
			//Strip out atdb
			rawData = rawData.replaceAll("(\\d\\d-\\d\\d-\\d\\d\\d\\d \\d\\d:\\d\\d:\\d\\d.\\d\\d\\d,\\d\\d\\d\\d\\d,SENT,415444420D)+", "");
				
			//Strip out 0D at the end of RSSI value
			rawData = rawData.replaceAll("(0D\\b)+", "");
				
			//Strip out the middle bits of information
			rawData = rawData.replaceAll(".\\d\\d\\d,\\d\\d\\d\\d\\d,RECV,", " ");
			
			//Create separation
			rawData = rawData.replaceAll(" ", ",");
				
			//make multiple new lines into one
			rawData = rawData.replaceAll("[\n\r]{2,}","\n");
				
			return rawData;
	}
}