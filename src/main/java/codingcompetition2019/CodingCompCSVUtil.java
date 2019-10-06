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
	 * Reads a CSV file with headers - it reads in the first line
	 * @param fileName
	 * @return
	 * @throws IOException
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

	public DisasterDescription getMostImpactfulYearByCategory(String category, List<List<String>> records) {
		for(int i = 0; i < records.size(); i ++) {
			if(!records.get(i).get(0).equals(category)) {
				records.remove(i);
				i--;
			}
		}
		return(getMostImpactfulYear(records));
	}

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
		return new DisasterDescription(0, category, amountOfIncidents);
	
	}

	public DisasterDescription getTotalReportedIncidentsByCategory(String category, List<List<String>> records) {
		for(int i = 0; i < records.size(); i ++) {
			if(!records.get(i).get(0).contentEquals(category)) {
				records.remove(i--);
			}
		}
		int count = 0;
		for(int i = 0; i < records.size(); i ++) { 
			count += Integer.parseInt(records.get(i).get(3));
		}
		return(new DisasterDescription(0, "", count));
	}
	
	/**
	 * This method will return the count if the number of incident falls within the provided range.
	 * To simplify the problem, we assume:
	 * 	+ A value of -1 is provided if the max range is NOT applicable.
	 *  + A min value can be provided, without providing a max value (which then has to be -1 like indicated above).
	 *  + If a max value is provided, then a max value is also needed.
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
	
	public boolean firstRecordsHaveMoreReportedIndicents(List<List<String>> records1, List<List<String>> records2) {
		return records1.size() > records2.size();
	}
	
	public static void main(String[] args) throws IOException {
		CodingCompCSVUtil util = new CodingCompCSVUtil();
		String naturalDisasterByTypeFile = "src/main/resources/natural-disasters-by-type.csv";
		
		String significantEarthquakeFileNameName = "src/main/resources/significant-earthquakes.csv";
		
		String significantVolcanicEruptionsFileName = "src/main/resources/significant-volcanic-eruptions.csv";
		
		List<List<String>>records = util.readCSVFileWithoutHeaders(naturalDisasterByTypeFile);
		
		List<List<String>> earthquakeRecords = util.readCSVFileByCountry(significantEarthquakeFileNameName, "United States");
		System.out.println(util.getMostImpactfulYear(earthquakeRecords).getYear());
		System.out.println(util.readCSVFileWithHeaders(naturalDisasterByTypeFile).size());
		System.out.println(util.getMostImpactfulYearByCategory("Earthquake", records).getYear());
		DisasterDescription dd = util.getMostImpactfulDisasterByYear("2005", records);
		System.out.println(dd.getCategory());
		System.out.println(dd.getReportedIncidentsNum());
	}
}
