package codingcompetition2019;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class CodingCompCSVUtil {
	public List<List<String>> readCSVFileByCountry(String fileName, String countryName) throws IOException {
		// TODO implement this method
		BufferedReader br = null;
		String line = "";
		List<List<String>> countryLines = new ArrayList<List<String>>();
		String delimiter = ",";
		try {
			br = new BufferedReader(new FileReader(fileName));
			while ((line = br.readLine()) != null) {
				String[] country = line.split(delimiter, -1);
				if (country[0].equals(countryName)) {
					List<String> countryCast = Arrays.asList(country);
					countryLines.add(countryCast);
				}
			}
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		return countryLines;
	}
	
	public List<List<String>> readCSVFileWithHeaders(String fileName) throws IOException {
		// TODO implement this method
		return null;
	}

	public List<List<String>> readCSVFileWithoutHeaders(String fileName) throws IOException {
		List<List<String>> records = new ArrayList<>();
		boolean firstLine = false;
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			String line;
			while ((line = br.readLine()) != null) {
				if (!firstLine) {
					firstLine = true;
					continue;
				}
				String[] values = line.split(",");
				records.add(Arrays.asList(values));
			}
		}
		System.out.println(records);
		return null;
	}
	
	public DisasterDescription getMostImpactfulYear(List<List<String>> records) {
		String mostImpactfulYear = null;
		int maxYearImpact = 0;
		for (List<String> record : records) {
			int currImpact = Integer.parseInt(record.get(3));
			if (currImpact > maxYearImpact) {
				maxYearImpact = currImpact;
				mostImpactfulYear = record.get(2);
			}
		}
		return new DisasterDescription(mostImpactfulYear);
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
