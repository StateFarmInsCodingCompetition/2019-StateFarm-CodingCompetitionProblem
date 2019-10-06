package codingcompetition2019;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class CodingCompCSVUtil {
	
	/**
	 * Reads CSV File and Parses by Country
	 * @param fileName CSV path
	 * @param countryName Name of Country
	 * @return List of Data for Specific Country
	 * @throws IOException Opening File Exception
	 */
	public List<List<String>> readCSVFileByCountry(String fileName, String countryName) throws IOException {
		
		List<List<String>> records = readCSVFileWithHeaders(fileName);
		List<List<String>> countryRecords = new ArrayList<List<String>>();
		
		for(List<String> record : records) {
			if(record.get(0).equals(countryName))
				countryRecords.add(record);
		}
		return countryRecords;
	}
	
	/**
	 * Reads CSV File with the Headers
	 * @param fileName CSV path
	 * @return List of Data from the CSV File
	 * @throws IOException Opening File Exception
	 */
	public List<List<String>> readCSVFileWithHeaders(String fileName) throws IOException {
		
		List<List<String>> records = new ArrayList<List<String>>();
		
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
	
	/**
	 * Reads CSV File without the Headers
	 * @param fileName CSV path
	 * @return List of Data from the CSV File
	 * @throws IOException Opening File Exception
	 */
	public List<List<String>> readCSVFileWithoutHeaders(String fileName) throws IOException {
		// TODO implement this method
		List<List<String>> records = readCSVFileWithHeaders(fileName);
		records.remove(0);
		return records;
		
	}
	
	/**
	 * Reads a list or records and finds the record with the most disasters
	 * @param records List of Records
	 * @return DisasterDescription Object which is the category, year, and the number of incidents for the disaster
	 */
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

	/**
	 * Reads a list or records that match the category and finds the record year with the most disasters
	 * @param records List of Records
	 * @param category The type of disaster (earthquake, flood, ...)
	 * @return DisasterDescription Object which is the category, year, and the number of incidents for the disaster
	 */
	public DisasterDescription getMostImpactfulYearByCategory(String category, List<List<String>> records) {
		
		return getMostImpactfulYear(filterListBySearch(0,category, records));
	}
	
	/**
	 * Filters the Lists by searching the rows; Can search rows by any column.
	 * @param column Column number to search by
	 * @param columnSearch Search term that you will filter the results by
	 * @param records List of Records that you want filtered
	 * @return A list of filtered records
	 */
	public List<List<String>> filterListBySearch(int column, String columnSearch, List<List<String>> records) {
		List<List<String>> newRecords = new ArrayList<List<String>>();
		
		for(List<String> record : records) {
			if(record.get(column).equals(columnSearch)){
				newRecords.add(record);
			}
		}
		return newRecords;
	}

	/**
	 * Filters the List by a specific year and looks for the greatest number of disasters
	 * @param records List of Records
	 * @param year Specific year to search for
	 * @return DisasterDescription Object which is the category, year, and the number of incidents for the disaster
	 */
	public DisasterDescription getMostImpactfulDisasterByYear(String year, List<List<String>> records) {
		List<List<String>> catRecords = new ArrayList<List<String>>();
		
		for(List<String> record : records) {
			if(record.get(2).equals(year) && !(record.get(0).equals("All natural disasters"))){ //must ignore "All Natural Disasters" since it is the total
				catRecords.add(record);
			}
		}
		return getMostImpactfulYear(catRecords);
	}

	/**
	 * Filters the List by a specific category and gets the sum of all incidents in that category
	 * @param records List of Records
	 * @param category Specific category to search for
	 * @return DisasterDescription Object which is the category and the number of incidents total
	 */
	public DisasterDescription getTotalReportedIncidentsByCategory(String category, List<List<String>> records) {
		// TODO implement this method
		List<List<String>> newRecords = new ArrayList<List<String>>();
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
	
	/**
	 * Counts the number of Years that have the disaster amount between the min and the max
	 * @param records List of Records
	 * @param min Minimum number of incidents to look for in the list
	 * @param max Maximum number of incidents to look for in the list; if -1, there is no maximum value 
	 * @return the sum of the years with disasters between the min and the max values
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
	
	/**
	 * Counts the number of incidents total in the records
	 * @param records List of Records
	 * @return the sum of total incidents 
	 */
	public int getTotalIncidents(List<List<String>> records) {
		int total = 0;
		
		for(List<String> record : records) {
			int incidentCount = Integer.parseInt(record.get(3));
			total += incidentCount;
		}
		return total;
	}
	
	/**
	 * Compares 2 records and sees if the incident count of the first list of records is larger than the second
	 * @param records List of Records
	 * @return true if incident count of records1 is larger than 2, false if incident count of records1 is smaller than 2
	 */
	public boolean firstRecordsHaveMoreReportedIndicents(List<List<String>> records1, List<List<String>> records2) {
		
		if(getTotalIncidents(records1) > getTotalIncidents(records2))
			return true;
		return false;
	}
}
