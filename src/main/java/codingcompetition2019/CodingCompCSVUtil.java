package codingcompetition2019;

import java.io.*;
import java.util.*;

public class CodingCompCSVUtil {

    /**
     * [readCSVFileByCountry description]
     * @param  fileName    [description]
     * @param  countryName [description]
     * @return             [description]
     * @throws IOException [description]
     */
	public List<List<String>> readCSVFileByCountry(String fileName, String countryName) throws IOException {
		File file = new File(fileName);
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		String line;
		List<List<String>> interpretedFile = new ArrayList<List<String>>();
		while((line = br.readLine()) != null){
	        List<String> interpretedLine = Arrays.asList(line.split("\\s*,\\s*"));
	        if (interpretedLine.get(0).equals(countryName)) {
	        	interpretedFile.add(interpretedLine);
	        }
		}
		return interpretedFile;
	}

    /**
     * [readCSVFileWithHeaders description]
     * @param  fileName    [description]
     * @return             [description]
     * @throws IOException [description]
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
     * [readCSVFileWithoutHeaders description]
     * @param  fileName    [description]
     * @return             [description]
     * @throws IOException [description]
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
     * [getMostImpactfulYear description]
     * @param  records [description]
     * @return         [description]
     */
	public DisasterDescription getMostImpactfulYear(List<List<String>> records) {
		return getMostImpactfulYearByCategory("All natural disasters", records);
	}

    /**
     * [getMostImpactfulYearByCategory description]
     * @param  category [description]
     * @param  records  [description]
     * @return          [description]
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
		return new DisasterDescription(category, highestNumDistasters, highestEntry.get(2));
	}

    /**
     * [getMostImpactfulDisasterByYear description]
     * @param  year    [description]
     * @param  records [description]
     * @return         [description]
     */
	public DisasterDescription getMostImpactfulDisasterByYear(String year, List<List<String>> records) {
		String mostImpactfulDisaster = "";
		int mostImpactsByDisaster = -1;
		for (List<String> line : records) {
			if (line.get(2).equals(year)&&!(line.get(0).equals("All natural disasters"))) {
				if (Integer.parseInt(line.get(3)) > mostImpactsByDisaster) {
					mostImpactfulDisaster = line.get(0);
					mostImpactsByDisaster = Integer.parseInt(line.get(3));
				}
			}
		}
		return new DisasterDescription(mostImpactfulDisaster, mostImpactsByDisaster, year);
	}

    /**
     * [getTotalReportedIncidentsByCategory description]
     * @param  category [description]
     * @param  records  [description]
     * @return          [description]
     */
	public DisasterDescription getTotalReportedIncidentsByCategory(String category, List<List<String>> records) {
		// TODO implement this method
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
	 */
	public int countImpactfulYearsWithReportedIncidentsWithinRange(List<List<String>> records, int min, int max) {
		int count = 0;
		if (min==-1) {
			for (List<String> line : records) {
				count += Integer.parseInt(line.get(3));
			}
		}
		else {
			for (List<String> line : records) {
				int numIncidents = Integer.parseInt(line.get(3)); 
				if (min <= numIncidents && (max==-1)?true:numIncidents<=max) {
					count += Integer.parseInt(line.get(3));
				}
			}
		}
		return count;
	}

    /**
     * [firstRecordsHaveMoreReportedIndicents description]
     * @param  records1 [description]
     * @param  records2 [description]
     * @return          [description]
     */
	public boolean firstRecordsHaveMoreReportedIndicents(List<List<String>> records1, List<List<String>> records2) {
		// TODO implement this method
		return false;
	}
}
