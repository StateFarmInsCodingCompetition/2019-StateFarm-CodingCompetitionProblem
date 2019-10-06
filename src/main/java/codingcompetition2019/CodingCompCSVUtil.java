package codingcompetition2019;

import java.io.File;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;




/**
 * Utility class that parses CSV files filled with data about natural disasters, and parses
 * the data accordingly.
 * 
 * @author Angel Aguayo, Andrew Raftovich
 *
 */
public class CodingCompCSVUtil {
	private static final int ENTITY_COL=0;
	private static final int CODE_COL=1;
	private static final int YEAR_COL=2;
	private static final int NUM_INCIDENT_COL=3;
	
	
	/**
	 * Reads and parses a CSV file in order to collect natural disaster data about 
	 * a specified country
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
		
		return records;
	}
	
	
	/**
	 * Parses a CSV file and stores the data line by line, including the headers
	 * 
	 * @param fileName name of CSV file to parse
	 * @return 2D list containing CSV file as separate lines
	 * @throws IOException
	 */
	public List<List<String>> readCSVFileWithHeaders(String fileName) throws IOException {
		Scanner reader=null;
		
		//read in file
		File newFile=new File(fileName);
		try {
			reader=new Scanner(newFile);
		} catch (Exception e){
			throw new IOException("File can not be read");
		}
		
		List<List<String>> records=new ArrayList<>();
		
		//parse the file
		while(reader.hasNextLine()) {
			String newLine=reader.nextLine();
			
			//current line is a country record
			ArrayList<String> separatedLine=
						new ArrayList<String>(Arrays.asList(newLine.split(",")));
				
			records.add(separatedLine);	
		}
		
		reader.close();
		
		return records;
	}
	
	
	/**
	 * Parses a CSV file and stores the data line by line, excluding the headers
	 * 
	 * @param fileName String representing name of CSV file to parse
	 * @return 2D list of line by line representation of a CSV file
	 * @throws IOException
	 */
	public List<List<String>> readCSVFileWithoutHeaders(String fileName) throws IOException {
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
			ArrayList<String> separatedLine=
						new ArrayList<String>(Arrays.asList(newLine.split(",")));
				
			records.add(separatedLine);	
		}
		
		reader.close();
		
		return records;
	}
	
	
	/**
	 * Reads through a given list of records to find the most impactful year in terms
	 * of number of incidents, processing it as a DisasterDescription object.
	 * 
	 * @param records list of records to parse through
	 * @return
	 */
	public DisasterDescription getMostImpactfulYear(List<List<String>> records) {
		String maxYear="";
		int maxIncidents=0;
		int i=0;
		
		//only check the cumulative sum of incidents per year
		while(i<records.size()) {
			List<String> currentLine=records.get(i);
			int numIncidents=Integer.parseInt(currentLine.get(NUM_INCIDENT_COL));
			
			//if new max is found
			if(numIncidents>maxIncidents) {
				maxYear=currentLine.get(YEAR_COL);
				maxIncidents=Integer.parseInt(currentLine.get(NUM_INCIDENT_COL));
			}
			
			i++;
		}
		
		DisasterDescription disaster=new DisasterDescription(maxYear, 
				                                             maxIncidents);
		
		return disaster;
	}
	

	/**
	 * Gets the most impactful year based upon natural disaster category
	 * 
	 * @param category name of category to find most impactful year
	 * @param records contains all records of events and years
	 * @return disaster is a DisasterDescription object contains information 
	 *         about the found event
	 */
	public DisasterDescription getMostImpactfulYearByCategory(String category, List<List<String>> records) {
		String maxYear="";
		int maxIncidents=0;
		int i=0;
		
		//only check the cumulative sum of incidents per year
		while(i<records.size()) {
			if(records.get(i).get(0).startsWith(category)) {
				List<String> currentLine=records.get(i);
				int numIncidents=Integer.parseInt(currentLine.get(NUM_INCIDENT_COL));
				
				//new max is found
				if(numIncidents>maxIncidents) {
					maxYear=currentLine.get(YEAR_COL);
					maxIncidents=Integer.parseInt(currentLine.get(NUM_INCIDENT_COL));
				}
			}
			
			i++;
		}
		
		DisasterDescription disaster=new DisasterDescription(maxYear, 
				                                             maxIncidents, 
				                                             category);
		
		return disaster;
	}
	
	
	/**
	 * Gets the most impactful disaster based upon a given year
	 * 
	 * @param year
	 * @param records
	 * @return
	 */
	public DisasterDescription getMostImpactfulDisasterByYear(String year, List<List<String>> records) {
		// TODO implement this method
		String maxYear="";
		int maxIncidents=0;
		String maxCategory="";
		int i=0;
		
		//only check the cumulative sum of incidents per year
		while(i<records.size()) {
			List<String> currentLine=records.get(i);
			
			//skip cumulative sum lines
			if(currentLine.get(ENTITY_COL).startsWith("All natural disasters")) {
				i++;
				continue;
			}
			
			//found a year entry for a given natural disaster
			if(records.get(i).get(YEAR_COL).startsWith(year)) {
				int numIncidents=Integer.parseInt(currentLine.get(NUM_INCIDENT_COL));
				
				if(numIncidents>maxIncidents) {
					maxYear=currentLine.get(YEAR_COL);
					maxIncidents=Integer.parseInt(currentLine.get(NUM_INCIDENT_COL));
					maxCategory=currentLine.get(ENTITY_COL);
				}
			}
			
			i++;
		}
		
		DisasterDescription disaster=new DisasterDescription(maxYear, 
				                                             maxIncidents, 
				                                             maxCategory);
		
		return disaster;
	}

	public DisasterDescription getTotalReportedIncidentsByCategory(String category, List<List<String>> records) {
		int maxIncidents=0;
		int i=0;
		
		//only check the cumulative sum of incidents per year
		while(i<records.size()) {
			List<String> currentLine=records.get(i);
			
			//skip cumulative sum lines
			if(currentLine.get(ENTITY_COL).startsWith("All natural disasters")) {
				i++;
				continue;
			}
			
			if(records.get(i).get(ENTITY_COL).startsWith(category)) {
				int numIncidents=Integer.parseInt(currentLine.get(NUM_INCIDENT_COL));
				
				maxIncidents+=numIncidents;
				
			}
			
			i++;
		}
		
		DisasterDescription disaster=new DisasterDescription(maxIncidents, 
				                                             category);
		
		return disaster;
	}
	
	/**
	 * This method will return the count if the number of incident falls within the provided range.
	 * To simplify the problem, we assume:
	 * 	+ A value of -1 is provided if the max range is NOT applicable.
	 *  + A min value can be provided, without providing a max value (which then has to be -1 like indicated above).
	 *  + If a max value is provided, then a max value is also needed.
	 *  
	 *  @param records 2D list of records
	 *  @param min integer repretsenting minimum number of incidents for a year
	 *  @param max integer representing maximum number of incidnts for a year. Note that
	 *             this is not rquired, and if no, will be -1.
	 */
	public int countImpactfulYearsWithReportedIncidentsWithinRange(List<List<String>> records, int min, int max) {
		int numOfIncidents=0;
		
		for(int i=0; i<records.size(); i++) {
			List<String> currentLine=records.get(i);
			
			int incidents=Integer.parseInt(currentLine.get(NUM_INCIDENT_COL));
			
			//num of incidents falls within range, count year
			if(incidents>=min && max==-1) {
				numOfIncidents+=1;
			}
			else if(incidents>=min && incidents<=max) {
				numOfIncidents+=1;
			}
		}
		
		return numOfIncidents;
	}
	
	
	/**
	 * Determines if the first records has more reported incidents
	 * 
	 * @param records1 2D list of natural disaster records
	 * @param records2 2D list of natural diaster records
	 * @return boolean on if first records has more reported incidents
	 */
	public boolean firstRecordsHaveMoreReportedIndicents(List<List<String>> records1, List<List<String>> records2) {
		int records1Incidents=countNumberOfIncidents(records1);
		int records2Incidents=countNumberOfIncidents(records2);
		
		return records1Incidents>records2Incidents;
	}
	
	
	/**
	 * Count number of incidents for a list of records
	 * 
	 * @param records 2D list of records
	 * @return int representing the count of incidents per a list of records
	 */
	private int countNumberOfIncidents(List<List<String>> records) {
		int recordsIncidents=0;
		for(int i=0; i<records.size(); i++) {
			List<String> currentLine=records.get(i);
			int incidents=Integer.parseInt(currentLine.get(NUM_INCIDENT_COL));
			
			recordsIncidents+=incidents;
		}
		
		return recordsIncidents;
	}
}
