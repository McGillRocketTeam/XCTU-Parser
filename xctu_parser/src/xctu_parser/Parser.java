package xctu_parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Parser {

static List<String> INPUT_FILE = new ArrayList<String>();
static List<String> OUTPUT_FILE = new ArrayList<String>();

Pattern RSSI = Pattern.compile("[0-9A-F]{6}");
Pattern TIME_STAMP = Pattern.compile("\\d{2}-\\d{2}-\\d{4} \\d{2}:\\d{2}:\\d{2}.\\d{3}");
Pattern SENT = Pattern.compile("SENT,[0-9A-F]{10}");

	
	public static void main(String[] args) {
		
		// Scan the .TXT file and populate tokenizer.
		String filename = "";
		try {
			filename = args[1];
			String line;
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			while ((line = reader.readLine()) != null) {
				INPUT_FILE.add(line);
			}
			reader.close();
		} catch (Exception e) {
			System.err.format("Exception occurred trying to read '%s'.", filename);
		}
		
		// Operate on the tokens.
		for (String line : INPUT_FILE) {
			
		}
		
		// Append the resulting strings to a .CSV file

	}

}
