package codingcompetition2019;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class holds methods that interpret natural disaster records
 * @author Mudit Gupta and Ryan Thomas Lynch
 * @version 1.0
 */
public class CodingCompCSVUtil {

    /**
     * Interprets CSV file as a list of list of strings - only returns records for a specified country
     * @param  fileName    the file path relative to the location of program execution
     * @param  countryName the country we want records from
     * @return             a list of list of strings representing each entry in the CSV related to the country
     * @throws IOException thrown when fileName is invalid
     */
	public List<List<String>> readCSVFileByCountry(String fileName, String countryName) throws IOException {
		File file = new File(fileName);
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		String line;
		List<List<String>> interpretedFile = new ArrayList<List<String>>();
		while ((line = br.readLine()) != null) {
	        List<String> interpretedLine = Arrays.asList(line.split("\\s*,\\s*"));
	        if (interpretedLine.get(0).equals(countryName)) {
	        	interpretedFile.add(interpretedLine);
	        }
		}
		return interpretedFile;
	}

    /**
     * Interprets CSV file as a list of list of strings including column headers
     * @param  fileName    the file path relative to the location of program execution
     * @return             a list of list of strings representing each entry in the CSV
     * @throws IOException thrown when fileName is invalid
     */
	public List<List<String>> readCSVFileWithHeaders(String fileName) throws IOException {
		File file = new File(fileName);
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		String line;
		List<List<String>> interpretedFile = new ArrayList();
		while ((line = br.readLine()) != null) {
	        List<String> interpretedLine = Arrays.asList(line.split("\\s*,\\s*"));
	        interpretedFile.add(interpretedLine);
		}
		return interpretedFile;
	}

    /**
     * Interprets CSV file as a list of list of strings excluding column headers
     * @param  fileName    the file path relative to the location of program execution
     * @return             a list of list of strings representing each disaster entry in the CSV
     * @throws IOException thrown when fileName is invalid
     */
	public List<List<String>> readCSVFileWithoutHeaders(String fileName) throws IOException {
		File file = new File(fileName);
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		String line = br.readLine();//skips the first line
		List<List<String>> interpretedFile = new ArrayList();
		while((line = br.readLine()) != null){
	        List<String> interpretedLine = Arrays.asList(line.split("\\s*,\\s*"));
	        interpretedFile.add(interpretedLine);
		}
		return interpretedFile;
	}

    /**
     * Finds the most impactful year for a given set of natural disaster records
     * @param  records a list of lists of strings representing natural disaster data interpreted from a CSV file
     * @return         a DisasterDescription for the disasters in the most impactful year
     */
	public DisasterDescription getMostImpactfulYear(List<List<String>> records) {
		if (records.get(0).get(0).equals("All natural disasters")) {
			return getMostImpactfulYearByCategory("All natural disasters", records);
		}
		int highestNumDisasters = Integer.parseInt(records.get(0).get(3));
		int yearOf_highestNumDisasters = Integer.parseInt(records.get(0).get(2));
		for (int i = 1; i < records.size(); i++) {
            int numDisasters = Integer.parseInt(records.get(i).get(3));
			if(numDisasters > highestNumDisasters) {
				highestNumDisasters = numDisasters;
                yearOf_highestNumDisasters = Integer.parseInt(records.get(i).get(2));
			}
		}
		return new DisasterDescription(highestNumDisasters, yearOf_highestNumDisasters);
	}

    /**
     * Finds the most impactful year for a given set of natural disasters of a certain category
     * @param  category the desired category
     * @param  records  a list of lists of strings representing natural disaster data interpreted from a CSV file
     * @return          a DisasterDescription for the disasters of a certain category in the most impactful year
     */
	public DisasterDescription getMostImpactfulYearByCategory(String category, List<List<String>> records) {
		boolean foundCategory = false;
		int i = 0;
		while (!foundCategory && i < records.size()) {
			if(records.get(i++).get(0).equals(category)) {
				foundCategory = true;
			}
		}
		List<String> currentEntry;
		List<String> highestEntry = records.get(i++);
		int highestNumDistasters = Integer.parseInt(highestEntry.get(3));
		while((currentEntry = records.get(i++)).get(0).equals(category)) {
			int numDisasters = Integer.parseInt(currentEntry.get(3));
			if (numDisasters > highestNumDistasters) {
				highestEntry = currentEntry;
				highestNumDistasters = numDisasters;
			}
		}
		return new DisasterDescription(category, highestNumDistasters, Integer.parseInt(highestEntry.get(2)));
	}

    /**
     * Finds the most impactful disaster for a given set of natural disasters in a certain year
     * @param  year    the desired year
     * @param  records a list of lists of strings representing natural disaster data interpreted from a CSV file
     * @return         a DisasterDescription for the most impactful disaster category in a given year
     */
	public DisasterDescription getMostImpactfulDisasterByYear(String year, List<List<String>> records) {
		String mostImpactfulDisasterCategory = "";
		int mostImpactsByDisaster = -1;
		for (List<String> line : records) {
			if (line.get(2).equals(year)&&!(line.get(0).equals("All natural disasters"))) {
				if (Integer.parseInt(line.get(3)) > mostImpactsByDisaster) {
					mostImpactfulDisasterCategory = line.get(0);
					mostImpactsByDisaster = Integer.parseInt(line.get(3));
				}
			}
		}
		return new DisasterDescription(mostImpactfulDisasterCategory, mostImpactsByDisaster, Integer.parseInt(year));
	}

    /**
     * counts the number of incidents of a certain category within a set of disaster records
     * @param  category the desired category to measure
     * @param  records  a list of lists of strings representing natural disaster data interpreted from a CSV file
     * @return          a DisasterDescription for the number of incidents of a certain disaster category
     */
	public DisasterDescription getTotalReportedIncidentsByCategory(String category, List<List<String>> records) {
		int totalReportedIncidents = 0;
		for (List<String> line : records) {
			if (line.get(0).equals(category)) {
				totalReportedIncidents += Integer.parseInt(line.get(3));
			}
		}
		return new DisasterDescription(category,totalReportedIncidents);
	}

	/**
	 * This method will return the count if the number of incident falls within the provided range.
	 * To simplify the problem, we assume:
	 * 	+ A value of -1 is provided if the max range is NOT applicable.
	 *  + A min value can be provided, without providing a max value (which then has to be -1 like indicated above).
	 *  + If a max value is provided, then a MIN value is also needed.
	 * @param records a list of lists of strings representing natural disaster data interpreted from a CSV file
	 * @param min     the minimum number of incidents to count a year (see above for specifications)
	 * @param max     the maximum number of incidents to count a year (see above for specifications)
	 * @return        the number of incidents that fall in the given range
	 */
	public int countImpactfulYearsWithReportedIncidentsWithinRange(List<List<String>> records, int min, int max) {
		int count = 0;
		if (min==-1) {
			for (List<String> line : records) {
				count++;
			}
		}
		else {
			for (List<String> line : records) {
				int numIncidents = Integer.parseInt(line.get(3));
				if (min <= numIncidents && ((max==-1)?true:numIncidents<=max)) {
					count++;
				}
			}
		}
		return count;
	}


	/**
	 * counts the number of incidents in a set of natural disaster records
	 * @param records a list of lists of strings representing natural disaster data interpreted from a CSV file
	 * @return        the number of incidents in the records
	 */
	private int countIncidents(List<List<String>> records) {
		int count = 0;
		for (List<String> entry : records) {
			int numDisasters = Integer.parseInt(entry.get(3));
			count += numDisasters;
		}
		return count;
	}

    /**
     * Returns true if the first parameter has strictly more total reported incidents than the second.
     * @param  records1 The first list to check. Returns true if it has strictly more incidents.
     * @param  records2 The second list to check. Returns true if it has strictly fewer incidents.
     * @return          True if the first list is strictly larger than the second in terms of total reported incidents.
     */
	public boolean firstRecordsHaveMoreReportedIndicents(List<List<String>> records1, List<List<String>> records2) {
		return countIncidents(records1) > countIncidents(records2);
	}
}
