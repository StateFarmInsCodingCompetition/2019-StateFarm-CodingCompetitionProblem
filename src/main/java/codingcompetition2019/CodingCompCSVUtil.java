package codingcompetition2019;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Utility class to parse through natural disaster data in CSV file format
 * @author --
 * @version 1.42
 */
public class CodingCompCSVUtil {
	
	/**
	 * Parses through file passed in and filters only for natural disasters occurring in the specified country
	 * @param fileName      path to the csv data file
	 * @param countryName   name of the country to be filtered by
	 * @return              a list of natural disasters found in the data file occurring in the specified country
	 * @throws IOException  if the file is not found or cannot be parsed
	 */
	public List<List<String>> readCSVFileByCountry(String fileName, String countryName) throws IOException {
		List<List<String>> disasters = new ArrayList<List<String>>();
		try {
			String line;
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			while ((line = br.readLine()) != null) {
				String[] lineArr = line.split(",");
				if(lineArr[0].contentEquals(countryName)) { //country is equal
					disasters.add(new ArrayList<String>()); //add new row
					for(String str : lineArr) {
						disasters.get(disasters.size()-1).add(str);
					}
				}
			}
			br.close();
		}
		catch (IOException e) {
			throw e;
		}
		return disasters;
	}
	/**
	 * Reads a CSV file with headers (reads in the first line)
	 * @param fileName      path to the csv data file
	 * @return              a 2D String arrayList representing the csv file including the first line (headers)
	 * @throws IOException  if the file is not found or cannot be parsed
	 */
	public List<List<String>> readCSVFileWithHeaders(String fileName) throws IOException {
		
		List<List<String>> disasters = new ArrayList<List<String>>();
		try {
			String line;
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			while ((line = br.readLine()) != null) {
				String[] lineArr = line.split(",");
				disasters.add(new ArrayList<String>()); //add new row
				for(String str : lineArr) {
					disasters.get(disasters.size()-1).add(str);
				}
				
			}
			br.close();
		}
		catch (IOException e) {
			throw e;
		}
		return disasters;
	}
	/**
	 * Reads a CSV file without the headers (ignores the first line)
	 * @param fileName     path to the csv file
	 * @return             a 2D String arrayList representing the csv file excluding the first line (headers)
	 * @throws IOException if the file is not found or cannot be parsed
	 */
	public List<List<String>> readCSVFileWithoutHeaders(String fileName) throws IOException {
		
		List<List<String>> disasters = new ArrayList<List<String>>();
		try {
			String line;
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			br.readLine(); //skip the header line

			while ((line = br.readLine()) != null) {
				String[] lineArr = line.split(",");
				disasters.add(new ArrayList<String>()); //add new row
				for(String str : lineArr) {
					disasters.get(disasters.size()-1).add(str);
				}
				
			}
			br.close();
		}
		catch (IOException e) {
			throw e;
		}
		return disasters;
	}
	/**
	 * Parses natural disaster data to find the year with the most natural disasters
	 * @param records 2D List representing the natural disaster data
	 * @return                a DisasterDescription object with both the year with the most disasters and how many there were
	 */
	public DisasterDescription getMostImpactfulYear(List<List<String>> records) {
		Map<Integer, Integer> map = new HashMap<Integer,Integer>();
		for(List<String> line : records) {
			if(line.get(0).equals("All natural disasters")) {
				continue; //don't need these
			}
			int year = Integer.parseInt(line.get(2));
			int numDisasters = Integer.parseInt(line.get(3));
			if(map.get(year)==null) {
				map.put(year, numDisasters);

			} else {
				map.put(year, map.get(year)+ numDisasters);
			}
		}
		int maxYear = 0;
		int maxDisasters = -1;
		for(Integer key : map.keySet()) {
			if(map.get(key) >= maxDisasters) {
				maxDisasters = map.get(key);
				maxYear = key;
			}
		}
		return new DisasterDescription(maxYear,"",maxDisasters);
	}
	/**
	 * Parses natural disaster data to find the year with the most natural disasters of a certain category/type
	 * @param category  the category or type of natural disaster
	 * @param records   the 2D list representing the natural disaster data
	 * @return          a DisasterDescription object with both the year with the most disasters given the category and how many there were
	 */
	public DisasterDescription getMostImpactfulYearByCategory(String category, List<List<String>> records) {
		for(int i = 0; i < records.size(); i ++) {
			if(!records.get(i).get(0).equals(category)) {
				records.remove(i);
				i--;
			}
		}
		return(getMostImpactfulYear(records));
	}
	
	/**
	 * Parses natural disaster data to find the most frequent type of natural disaster in a given year
	 * @param year     the year to be specified
	 * @param records  the 2D list representing the natural disaster data
	 * @return         a DisasterDescription object with both the category/type of the most frequent natural disaster in that year and how many times it occured in that year
	 */
	public DisasterDescription getMostImpactfulDisasterByYear(String year, List<List<String>> records) {
		for(int i = 0; i < records.size(); i ++) {
			if(!records.get(i).get(2).contentEquals(year)) {
				records.remove(i--);
			
			}
		}
		for(int i = 0; i < records.size(); i ++) {
			if(records.get(i).get(0).equalsIgnoreCase("All natural disasters")) {
				records.remove(i--);
			}
		}
		String category = "";
		int max = 0;
		int amountOfIncidents = 0;
		for(int i = 0; i < records.size(); i ++) {
			if(Integer.parseInt(records.get(i).get(3)) > max) {
				max = Integer.parseInt(records.get(i).get(3));
				category = records.get(i).get(0); 
				amountOfIncidents = Integer.parseInt(records.get(i).get(3));
			}
		}
		return new DisasterDescription(Integer.parseInt(year), category, amountOfIncidents);
	
	}
	
	/**
	 * Parses natural disaster data to find how many reported incidents of a given type of natural disaster there were within a data set
	 * @param category  the category/type of natural disaster to be counted/searched for
	 * @param records	the 2D list representing the natural disaster data
	 * @return			a DisasterDescription object containing the category and the amount that type of disaster occurred throughout the entire data set
	 */
	public DisasterDescription getTotalReportedIncidentsByCategory(String category, List<List<String>> records) {
		for(int i = 0; i < records.size(); i ++) {
			if(!records.get(i).get(0).equalsIgnoreCase(category)) {
				records.remove(i--);
			}
		}
		int count = 0;
		for(int i = 0; i < records.size(); i ++) { 
			count += Integer.parseInt(records.get(i).get(3));
		}
		return(new DisasterDescription(0, category, count));
	}
	
	/**
	 * Finds the number of years that had a certain amount (range) of natural disasters occurring in that year throughout a natural disaster data set
	 * @param records	the 2D list representing the natural disaster data
	 * @param min  		the lower bound of the disaster frequency range for a year to be counted
	 * @param max		the upper bound of the disaster frequency range for a year to be counted. -1 if no max is specified
	 * @return			the number of years with number of disasters within the specified range
	 */
	public int countImpactfulYearsWithReportedIncidentsWithinRange(List<List<String>> records, int min, int max) {
		int count = 0;
		if(max == -1) {
			max = Integer.MAX_VALUE;
		}
		for(int i = 0; i < records.size(); i ++) {
			int incidentAmountPerYear = Integer.parseInt(records.get(i).get(3));
			if(incidentAmountPerYear >= min && incidentAmountPerYear <= max) {
				count++;
			}
		}
		return count;
	}
	/**
	 * @param records1	the 2D list representing the first set of natural disaster data
	 * @param records2	the 2D list representing the second set of natural disaster data
	 * @return			true if the first set of data has more natural disasters than the second. False otherwise.
	 */
	public boolean firstRecordsHaveMoreReportedIndicents(List<List<String>> records1, List<List<String>> records2) {
		return records1.size() > records2.size();
	}
	
	
}
