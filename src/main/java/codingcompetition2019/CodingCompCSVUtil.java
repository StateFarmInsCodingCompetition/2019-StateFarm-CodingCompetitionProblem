package codingcompetition2019;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CodingCompCSVUtil {
	public List<List<String>> readCSVFileByCountry(String fileName, String countryName) throws IOException {
		String tempStr = "";
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		ArrayList<ArrayList<String>> row = new ArrayList<ArrayList<String>>();
		ArrayList<ArrayList<String>> selected = new ArrayList<ArrayList<String>>();
		
		while((tempStr = br.readLine()) != null) {
			String [] tempCol = tempStr.split(",");
			ArrayList<String> col = new ArrayList<String>(Arrays.asList(tempCol));
			row.add(col);
		}
		
		for (int i = 0; i < row.size(); i++) {
			if (row.get(i).get(0).contains(countryName)) {
				selected.add(row.get(i));
			}
		}
		List<List<String>> result = (List)selected;
		return result;
	}
	
	public List<List<String>> readCSVFileWithHeaders(String fileName) throws IOException {
		String tempStr = "";
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		ArrayList<ArrayList<String>> row = new ArrayList<ArrayList<String>>();
		ArrayList<ArrayList<String>> selected = new ArrayList<ArrayList<String>>();
		
		while((tempStr = br.readLine()) != null) {
			String [] tempCol = tempStr.split(",");
			ArrayList<String> col = new ArrayList<String>(Arrays.asList(tempCol));
			row.add(col);
		}
		
		List<List<String>> result = (List)row;
		return result;
	}
	
	public List<List<String>> readCSVFileWithoutHeaders(String fileName) throws IOException {
		ArrayList<ArrayList<String>> row = new ArrayList<ArrayList<String>>((ArrayList)readCSVFileWithHeaders("src/main/resources/natural-disasters-by-type.csv"));
		row.remove(0);
		List<List<String>> result = (List)row;
		return result;
	}
	
	public DisasterDescription getMostImpactfulYear(List<List<String>> records) {
		DisasterDescription highest = new DisasterDescription(records);
		return highest;
	}

	public DisasterDescription getMostImpactfulYearByCategory(String category, List<List<String>> records) {
		DisasterDescription high = new DisasterDescription(category, records);
		return high;
	}

	public DisasterDescription getMostImpactfulDisasterByYear(String year, List<List<String>> records) {
		DisasterDescription byYear = new DisasterDescription(0, year, records);
		return byYear;
	}

	public DisasterDescription getTotalReportedIncidentsByCategory(String category, List<List<String>> records) {
		DisasterDescription byCat = new DisasterDescription(0, 0, category, records);
		return byCat;
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
