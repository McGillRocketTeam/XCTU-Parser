package xctu_parser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

// File arrays
static List<String> INPUT_FILE = new ArrayList<String>();
static List<String> OUTPUT_FILE = new ArrayList<String>();

// Output filename
static String OUTPUT_FILENAME = "output.csv";

// Patterns
static Pattern TIME_RSSI = Pattern.compile("(\\d{2}-\\d{2}-\\d{4} \\d{2}:\\d{2}:\\d{2}.\\d{3}).*([0-9A-F]{2})([0-9A-F]{4})");
static Pattern SENT = Pattern.compile(".*SENT,[0-9A-F]{10}");

	
	public static void main(String[] args) {
		
		/* Scan the .TXT file and load it into the buffered reader. */
		
		String filename = "";
		try {
			filename = args[0];
			String line;
			System.out.println("Opening data file...");
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			while ((line = reader.readLine()) != null) {
				INPUT_FILE.add(line);
			}
			reader.close();
			System.out.println("Closed data file.");
		} catch (Exception e) {
			System.err.format("Exception occurred trying to read '%s'.", filename);
		}
		
		/* Operate on the tokens. */
		
		boolean nextIsReceived = false;
		System.out.println("Building output array...");
		for (String line : INPUT_FILE) {
			// Create the matchers
			Matcher sent = SENT.matcher(line);
			Matcher timeRssi = TIME_RSSI.matcher(line);
			// If current token is wanted, extract information and add to the output file.
			if (nextIsReceived && timeRssi.find()) {
				System.out.println("Found match.");
				// Convert the RSSI hex value to decimal.
				Integer rssiValue = Integer.parseInt(timeRssi.group(3), 16);
				// Construct a output file line.
				OUTPUT_FILE.add(timeRssi.group(1).replaceAll(" ", "/") + "," + rssiValue.toString());
			}
			// Verify if the current token is the desired marker.
			nextIsReceived = sent.matches();
		}
		System.out.println("Output array built.");
		
		/* Append the resulting strings to a .CSV file */
		
		try {
			System.out.println("Opening output file...");
			// Create and open an file.
			PrintWriter writer = new PrintWriter(OUTPUT_FILENAME, "UTF-8");
			// Write collumn titles
			writer.println("Timestamp,RSSI");
			// Append the results to the end of the file.
			for (String line : OUTPUT_FILE) {
				System.out.println(line);
				writer.println(line);
			}
			// Close the file.
			writer.close();
			System.out.println("Output file closed.");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.err.println("Exception occurred trying to read the output file.");
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			System.err.println("Unsupported encoding.");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Processing complete.");

	}

}