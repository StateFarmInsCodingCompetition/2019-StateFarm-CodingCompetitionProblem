package codingcompetition2019;

import java.io.*;
import java.util.*;

public class CodingCompCSVUtil {
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
	
	public List<List<String>> readCSVFileWithHeaders(String fileName) throws IOException {
		File file = new File(fileName);
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		String line;
		List<List<String>> interpretedFile = new ArrayList();
		while((line = br.readLine()) != null){
	        List<String> interpretedLine = Arrays.asList(line.split("\\s*,\\s*"));
	        	interpretedFile.add(interpretedLine);
        	}
		}
		return interpretedFile;
	}
	
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
		}
		return interpretedFile;
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
