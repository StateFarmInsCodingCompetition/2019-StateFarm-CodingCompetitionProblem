package codingcompetition2019;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CodingCompCSVUtil {
	public List<List<String>> readCSVFileByCountry(String fileName, String countryName) throws IOException {
		// TODO implement this method
		List<List<String>> records = readCSVFileWithHeaders(fileName);
		List<List<String>> countryRecords = new ArrayList<>();
		
		for(List<String> record : records) {
			if(record.get(0).equals(countryName))
				countryRecords.add(record);
		}
		return countryRecords;
	}
	
	public List<List<String>> readCSVFileWithHeaders(String fileName) throws IOException {
		
		List<List<String>> records = new ArrayList<>();
		
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		        String[] values = line.split(",");
		        records.add(Arrays.asList(values));
		    }
		} catch(Exception e) {
			e.printStackTrace();
		}
		return records;
	}
	
	public List<List<String>> readCSVFileWithoutHeaders(String fileName) throws IOException {
		// TODO implement this method
		List<List<String>> records = readCSVFileWithHeaders(fileName);
		records.remove(0);
		return records;
		
	}
	
	public DisasterDescription getMostImpactfulYear(List<List<String>> records) {
		// TODO implement this method
		int maxDisasters = 0;
		DisasterDescription disaster = new DisasterDescription();
		
		for(List<String> record : records) {
			int disasterCount = Integer.parseInt(record.get(3));
						
			if(disasterCount > maxDisasters) {
				maxDisasters = disasterCount;
				disaster.setYear(record.get(2));
				disaster.setCategory(record.get(0));
				disaster.setReportedIncidentsNum(disasterCount);
			}	
		}
		return disaster;
	}

	public DisasterDescription getMostImpactfulYearByCategory(String category, List<List<String>> records) {
		// TODO implement this method
		List<List<String>> catRecords = new ArrayList<>();
		
		for(List<String> record : records) {
			if(record.get(0).equals(category)){
				catRecords.add(record);
			}
		}
		return getMostImpactfulYear(catRecords);
	}
	
	public List<List<String>> filterListBySearch(int column, String columnSearch, List<List<String>> records) {
		List<List<String>> newRecords = new ArrayList<>();
		
		for(List<String> record : records) {
			if(record.get(column).equals(columnSearch)){
				newRecords.add(record);
			}
		}
		return newRecords;
	}

	public DisasterDescription getMostImpactfulDisasterByYear(String year, List<List<String>> records) {
		// TODO implement this method
		List<List<String>> catRecords = new ArrayList<>();
		
		for(List<String> record : records) {
			if(record.get(2).equals(year) && !(record.get(0).equals("All natural disasters"))){ //must ignore "All Natural Disasters" since it is the total
				catRecords.add(record);
			}
		}
		return getMostImpactfulYear(catRecords);
	}

	public DisasterDescription getTotalReportedIncidentsByCategory(String category, List<List<String>> records) {
		// TODO implement this method
		List<List<String>> newRecords = new ArrayList<>();
		int totalIncidentCount = 0;
		
		newRecords = filterListBySearch(0, category, records);

		for(List<String> record : newRecords) {
			int incidentCount = Integer.parseInt(record.get(3));
			
			totalIncidentCount += incidentCount;
		}
		DisasterDescription totalDisaster = new DisasterDescription(category, totalIncidentCount);
		return totalDisaster;
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
		
		int countYear = 0;

		for(List<String> record : records) {
			int disasterCount = Integer.parseInt(record.get(3));
			if(disasterCount >= min && ((max==-1)?true:disasterCount <= max)) {
				countYear++;
			}
		}
		return countYear;
	}
	
	public int getTotalIncidents(List<List<String>> records) {
		int total = 0;
		
		for(List<String> record : records) {
			int incidentCount = Integer.parseInt(record.get(3));
			total += incidentCount;
		}

		return total;
	}
	
	public boolean firstRecordsHaveMoreReportedIndicents(List<List<String>> records1, List<List<String>> records2) {
		// TODO implement this method
		
		if(getTotalIncidents(records1) >= getTotalIncidents(records2))
			return true;
		return false;
	}
}
