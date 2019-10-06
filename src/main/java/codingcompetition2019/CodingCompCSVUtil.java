package codingcompetition2019;

import java.io.File;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class CodingCompCSVUtil {
	
	
	/**
	 * 
	 * @param fileName String representing the name of a csv file to be read
	 * @param countryName String representing the name of a country 
	 *                    to parse csv file data for
	 * @return 2D List of Strings that serves as the data for a specific country.
	 * @throws IOException
	 */
	public List<List<String>> readCSVFileByCountry(String fileName, String countryName) throws IOException {
		Scanner reader=null;
		
		//read in file
		File newFile=new File(fileName);
		try {
			reader=new Scanner(newFile);
		} catch (Exception e){
			throw new IOException("File can not be read");
		}
		
		List<List<String>> records=new ArrayList<>();
		
		//skip the header
		reader.nextLine();
		
		//parse the file
		while(reader.hasNextLine()) {
			String newLine=reader.nextLine();
			
			//current line is a country record
			if(newLine.startsWith(countryName)) {
				ArrayList<String> separatedLine=
						new ArrayList<String>(Arrays.asList(newLine.split(",")));
				
				records.add(separatedLine);
			}
		}
		
		reader.close();
		
		System.out.println(records.size());
		return records;
	}
	
	public List<List<String>> readCSVFileWithHeaders(String fileName) throws IOException {
		// TODO implement this method
		return null;
	}
	
	public List<List<String>> readCSVFileWithoutHeaders(String fileName) throws IOException {
		// TODO implement this method
		return null;
	}
	
	public DisasterDescription getMostImpactfulYear(List<List<String>> records) {
		// TODO implement this method
		return null;
	}

	public DisasterDescription getMostImpactfulYearByCategory(String category, List<List<String>> records) {
		// TODO implement this method
		return null;
	}

	public DisasterDescription getMostImpactfulDisasterByYear(String year, List<List<String>> records) {
		// TODO implement this method
		return null;
	}

	public DisasterDescription getTotalReportedIncidentsByCategory(String category, List<List<String>> records) {
		// TODO implement this method
		return null;
	}
	
	/**
	 * This method will return the count if the number of incident falls within the provided range.
	 * To simplify the problem, we assume:
	 * 	+ A value of -1 is provided if the max range is NOT applicable.
	 *  + A min value can be provided, without providing a max value (which then has to be -1 like indicated above).
	 *  + If a max value is provided, then a max value is also needed.
	 */
	public int countImpactfulYearsWithReportedIncidentsWithinRange(List<List<String>> records, int min, int max) {
		// TODO implement this method
		return -1;
	}
	
	public boolean firstRecordsHaveMoreReportedIndicents(List<List<String>> records1, List<List<String>> records2) {
		// TODO implement this method
		return false;
	}
}
