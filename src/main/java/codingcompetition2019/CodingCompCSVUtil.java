package codingcompetition2019;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class CodingCompCSVUtil {
	/**
	 * Parses a CSV file and returns the data for a specific country name
	 * @param fileName The path to the CSV file to read
	 * @param countryName The name of the country
	 * @return The data for a specific country
	 * @throws IOException An exception that occurs while opening the file
	 */
	public List<List<String>> readCSVFileByCountry(String fileName, String countryName) throws IOException {
		List<List<String>> data = this.readCSVFileWithHeaders(fileName);
		List<List<String>> filtered = new LinkedList<List<String>>();
		data.remove(0);
		for (List<String> item : data) {
			DisasterDescription desc = new DisasterDescription(item);
			if (desc.getCountry().equals(countryName)) {
				filtered.add(item);
			}
		}
		return filtered;
	}
	
	/**
	 * Parses a CSV file and returns the data including the header row
	 * @param fileName The path to the CSV file to read
	 * @return The data including the header row
	 * @throws IOException An exception that occurs while opening the file
	 */
	public List<List<String>> readCSVFileWithHeaders(String fileName) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(fileName));
		List<List<String>> data = new LinkedList<List<String>>();
		String row = null;
		while ((row = reader.readLine()) != null) {
			String[] parts = row.split("\\,");
			data.add(Arrays.asList(parts));
		}
		reader.close();
		return data;
	}
	
	/**
	 * Parses a CSV file and returns the data without the header (first) row
	 * @param fileName The path to the CSV file to read
	 * @return The data including the header row
	 * @throws IOException An exception that occurs while opening the file
	 */
	public List<List<String>> readCSVFileWithoutHeaders(String fileName) throws IOException {
		List<List<String>> data = this.readCSVFileWithHeaders(fileName);
		data.remove(0);
		return data;
	}
	
	/**
	 * Determines the year with the most disasters
	 * @param records A list of records read from a file
	 * @return A DisasterDescription object containing the most impactful year and the number of incidents in that year
	 */
	public DisasterDescription getMostImpactfulYear(List<List<String>> records) {
		return getMostImpactfulYearByCategory(null, records);
	}

	/**
	 * Determines the year with the most disasters in a given category
	 * @param category The specific category to examine
	 * @param records A list of records read from a file
	 * @return A DisasterDescription object containing the most impactful year, the number of incidents in that year
	 * and the category analyzed
	 */
	public DisasterDescription getMostImpactfulYearByCategory(String category, List<List<String>> records) {
		Map<String, Integer> yearImpact = new HashMap<String, Integer>();
		for (List<String> line : records) {
			String cat = line.get(0);
			if (category != null && !cat.equals(category)) {
				continue;
			}
			
			String year = line.get(2);
			int numDisasters = Integer.parseInt(line.get(3));
			
			// Increment year's impact by one
			yearImpact.put(year, yearImpact.getOrDefault(year, 0) + numDisasters);
		}

		String maxYear = null;
		int maxImpact = 0;
		
		for (String year : yearImpact.keySet()) {
			int impact = yearImpact.get(year);
			if (maxYear == null || impact > maxImpact) {
				maxYear = year;
				maxImpact = impact;
			}
		}
		
		return new DisasterDescription("defaultEntity", "defaultCode", maxYear, String.valueOf(maxImpact));
	}

	/**
	 * Determines the most impractful disaster for a given year
	 * @param year The year to analyze
	 * @param records A list of records read from a file
	 * @return A DisasterDescription object containing the category, the year analyzed, and the number of incidents
	 */
	public DisasterDescription getMostImpactfulDisasterByYear(String year, List<List<String>> records) {
		HashMap<String, Integer> counts = new HashMap<String, Integer>();
		String maxDisaster = null;
		int maxDisasterCount = -1;
		for (List<String> item : records) {
			DisasterDescription desc = new DisasterDescription(item);
			if (!desc.getYear().equals(year)) continue;
			if (desc.getCategory().equals("All natural disasters")) continue;
			int newCount = counts.getOrDefault(desc.getCategory(), 0) + desc.getReportedIncidentsNum();
			counts.put(desc.getCategory(), newCount);
			if (newCount > maxDisasterCount) {
				maxDisasterCount = newCount;
				maxDisaster = desc.getCategory();
			}
		}
		return new DisasterDescription(maxDisaster, "", year, maxDisasterCount + "");	
	}

	/**
	 * Determines the total number of incidents reported for a specific category
	 * @param category The category to analyze
	 * @param records A list of records read from a file
	 * @return A DisasterDescription object containing the category analyzed, and the number of incidents reported
	 */
	public DisasterDescription getTotalReportedIncidentsByCategory(String category, List<List<String>> records) {
		int incidents = 0;
		for (List<String> item : records) {
			DisasterDescription desc = new DisasterDescription(item);
			if (!desc.getCategory().equals(category)) continue;
			incidents += desc.getReportedIncidentsNum();
		}
		return new DisasterDescription(category, "", "", incidents + "");	
	}
	
	/*
	 * This method will return the count if the number of incident falls within the provided range.
	 * To simplify the problem, we assume:
	 * 	+ A value of -1 is provided if the max range is NOT applicable.
	 *  + A min value can be provided, without providing a max value (which then has to be -1 like indicated above).
	 *  + If a max value is provided, then a max value is also needed.
	 */
	
	/**
	 * Determines the number of impactful years within the given range
	 * @param records A list of records read from a file
	 * @param min The minimum number of incidents to count as impactful
	 * @param max The maximum number of incidents to count as impactful (or -1 if there is no maximum)
	 * @return The number of years whose natural disaster count is between min and max (or just greater than or equal to min if max is -1)
	 */
	public int countImpactfulYearsWithReportedIncidentsWithinRange(List<List<String>> records, int min, int max) {
		if (max == -1) {
			max = Integer.MAX_VALUE;
		}
		HashMap<String, Integer> counts = new HashMap<String, Integer>();
		for (List<String> item : records) {
			DisasterDescription desc = new DisasterDescription(item);
			int newCount = counts.getOrDefault(desc.getYear(), 0) + desc.getReportedIncidentsNum();
			counts.put(desc.getYear(), newCount);
		}
		int impactfulYears = 0;
		for (String year : counts.keySet()) {
			int incidents = counts.get(year);
			if (incidents >= min && incidents <= max) {
				impactfulYears++;
			}
		}
		return impactfulYears;	
		
	}
	
	/**
	 * Compares the two passed in records
	 * @param records1 A list of records read from a file
	 * @param records2 Another list of records read from a file
	 * @return Whether or not the first list of records has more overall incidents
	 */
	public boolean firstRecordsHaveMoreReportedIndicents(List<List<String>> records1, List<List<String>> records2) {
		return countIncidents(records1) > countIncidents(records2);
	}
	
	/**
	 * Determines the total number of incidents in a list of records
	 * @param records A list of records read from a file
	 * @return The total number of incidents that have occurred
	 */
	private int countIncidents(List<List<String>> records) {
		int numIncidents = 0;
		for (List<String> line : records) {
			numIncidents += Integer.parseInt(line.get(3));
		}
		return numIncidents;
	}
}
