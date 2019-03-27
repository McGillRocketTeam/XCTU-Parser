package xctu_parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Parser {

static List<String> inputFile = new ArrayList<String>();
static List<String> outputFile = new ArrayList<String>();
	
	public static void main(String[] args) {
		
		// Scan the .TXT file and populate tokenizer.
		String filename = "";
		try {
			filename = args[1];
			String line;
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			while ((line = reader.readLine()) != null) {
				inputFile.add(line);
			}
			reader.close();
		} catch (Exception e) {
			System.err.format("Exception occurred trying to read '%s'.", filename);
		}
		
		// Operate on the tokens.
		for (String line : inputFile) {
			
		}
		
		// Append the resulting strings to a .CSV file

	}

}
