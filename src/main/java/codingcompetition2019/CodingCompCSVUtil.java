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
	
	public List<List<String>> readCSVFileWithoutHeaders(String fileName) throws IOException {
		List<List<String>> data = this.readCSVFileWithHeaders(fileName);
		data.remove(0);
		return data;
	}
	
	public DisasterDescription getMostImpactfulYear(List<List<String>> records) {
		return getMostImpactfulYearByCategory(null, records);
	}

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
		
		// TODO figure out if we want to change default values
		return new DisasterDescription("defaultEntity", "defaultCode", maxYear, String.valueOf(maxImpact));
	}

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

	public DisasterDescription getTotalReportedIncidentsByCategory(String category, List<List<String>> records) {
		int incidents = 0;
		for (List<String> item : records) {
			DisasterDescription desc = new DisasterDescription(item);
			if (!desc.getCategory().equals(category)) continue;
			incidents += desc.getReportedIncidentsNum();
		}
		return new DisasterDescription(category, "", "", incidents + "");	
	}
	
	/**
	 * This method will return the count if the number of incident falls within the provided range.
	 * To simplify the problem, we assume:
	 * 	+ A value of -1 is provided if the max range is NOT applicable.
	 *  + A min value can be provided, without providing a max value (which then has to be -1 like indicated above).
	 *  + If a max value is provided, then a max value is also needed.
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
	
	public boolean firstRecordsHaveMoreReportedIndicents(List<List<String>> records1, List<List<String>> records2) {
		return countIncidents(records1) > countIncidents(records2);
	}
	
	private int countIncidents(List<List<String>> records) {
		int numIncidents = 0;
		for (List<String> line : records) {
			numIncidents += Integer.parseInt(line.get(3));
		}
		return numIncidents;
	}
}
