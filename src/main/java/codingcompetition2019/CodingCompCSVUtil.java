package codingcompetition2019;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.BufferedReader;


public class CodingCompCSVUtil 
{
	
	private BufferedReader csvReader;
	public List<List<String>> readCSVFileByCountry(String fileName, String countryName) throws IOException 
	{	
		// TODO implement this method
		if(fileName.equals(""))
		{
			throw new IOException();
		}
		
		List<List<String>> csvData = new ArrayList<List<String>>();
		csvReader = new BufferedReader(new FileReader(fileName));
		
		String rowString = "";
		while((rowString = csvReader.readLine()) != null)
		{
			String[] splitRowData = rowString.split(",");
			if(splitRowData[0].equals(countryName))
			{
				List<String> rowEntryData = new ArrayList<String>();
				for(int dataIndex = 0; dataIndex < splitRowData.length; dataIndex++)
				{
					rowEntryData.add(splitRowData[dataIndex]);
				}
				csvData.add(rowEntryData);
			}
		}
		
		return csvData;
	}
	
	public List<List<String>> readCSVFileWithHeaders(String fileName) throws IOException {
		// TODO implement this method
		return null;
	}
	
	public List<List<String>> readCSVFileWithoutHeaders(String fileName) throws IOException 
	{
		// TODO implement this method
		if(fileName.equals(""))
		{
			throw new IOException();
		}
		
		csvReader = new BufferedReader(new FileReader(fileName));
		List<List<String>> csvData = new ArrayList<List<String>>();
		csvReader.readLine();
		
		String rowString = "";
		while((rowString = csvReader.readLine()) != null)
		{
			String[] splitRowData = rowString.split(",");
			List<String> rowEntryData = new ArrayList<String>();
			for(int dataIndex = 0; dataIndex < splitRowData.length; dataIndex++)
			{
				rowEntryData.add(splitRowData[dataIndex]);
			}
			
			csvData.add(rowEntryData);
		}
		
		return csvData;
	}
	
	public DisasterDescription getMostImpactfulYear(List<List<String>> records) {
		// TODO implement this method
		return null;
	}

	public DisasterDescription getMostImpactfulYearByCategory(String category, List<List<String>> records) 
	{
		// TODO implement this method
		DisasterDescription description = new DisasterDescription();
		description.setCategory(category);
		int maxImpact = 0;
		int currentImpact = 0;
		String maxYear = "";
		
		for(List<String> record : records)
		{
			currentImpact = Integer.valueOf(record.get(3));
			if(record.get(0).equals(category))
			{
				if(currentImpact > maxImpact)
				{
					maxImpact = currentImpact;
					maxYear = record.get(2);
				}
			}
		}
		
		description.setYear(maxYear);
		return description;
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
