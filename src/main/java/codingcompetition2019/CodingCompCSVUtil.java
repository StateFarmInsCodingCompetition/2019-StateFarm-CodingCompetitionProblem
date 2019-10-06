package codingcompetition2019;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class CodingCompCSVUtil {
	
	public CodingCompCSVUtil()
	{
	}
	
	public List<List<String>> readCSVFileByCountry(String fileName, String countryName) throws IOException {
		CodingCompetitionCSVParser parser = new CodingCompetitionCSVParser(fileName);
		return parser.parseAsStringByCountry(false,countryName);
	}
	
	public List<List<String>> readCSVFileByCountryCode(String fileName, String countryCode) throws IOException {
		CodingCompetitionCSVParser parser = new CodingCompetitionCSVParser(fileName);
		return parser.parseAsStringByCountryCode(false,countryCode);
	}
	
	public List<DisasterDescription> readCSVFileAsDisaster(String fileName) throws IOException {
		CodingCompetitionCSVParser parser = new CodingCompetitionCSVParser(fileName);
		return parser.parseAsDisaster(false);
	}
	
	public List<List<String>> readCSVFileWithHeaders(String fileName) throws IOException {
		CodingCompetitionCSVParser parser = new CodingCompetitionCSVParser(fileName);
		return parser.parseAsString(true);
	}
	
	public List<List<String>> readCSVFileWithoutHeaders(String fileName) throws IOException {
		CodingCompetitionCSVParser parser = new CodingCompetitionCSVParser(fileName);
		return parser.parseAsString(false);
	}
	
	public DisasterDescription getMostImpactfulYear(List<List<String>> records) {
		HashMap<String,Integer> impactMap = new HashMap<String,Integer>();
		String mostImp = "";
		for(List<String> line : records)
		{
			String year = line.get(2);
			if(impactMap.containsKey(year))
			{
				impactMap.put(year, impactMap.get(year)+Integer.parseInt(line.get(3)));
			}else
			{
				impactMap.put(year, Integer.parseInt(line.get(3)));
			}
			if(mostImp.equals("") || impactMap.get(year) > impactMap.get(mostImp))
			{
				mostImp = year;
			}
		}
		DisasterDescription dd = new DisasterDescription();
		dd.setYear(mostImp);
		return dd;
	}

	public DisasterDescription getMostImpactfulYearByCategory(String category, List<List<String>> records) {
		HashMap<String,Integer> impactMap = new HashMap<String,Integer>();
		String mostImp = "";
		for(List<String> line : records)
		{
			if(!line.get(0).equals(category))
				continue;
			String year = line.get(2);
			if(impactMap.containsKey(year))
			{
				impactMap.put(year, impactMap.get(year)+Integer.parseInt(line.get(3)));
			}else
			{
				impactMap.put(year, Integer.parseInt(line.get(3)));
			}
			if(mostImp.equals("") || impactMap.get(year) > impactMap.get(mostImp))
			{
				mostImp = year;
			}
		}
		DisasterDescription dd = new DisasterDescription();
		dd.setYear(mostImp);
		return dd;
	}

	public DisasterDescription getMostImpactfulDisasterByYear(String year, List<List<String>> records) {
		HashMap<String,Integer> impactMap = new HashMap<String,Integer>();
		String mostImp = "";
		for(List<String> line : records)
		{
			if(!line.get(2).equals(year) || line.get(0).equals("All natural disasters"))
				continue;
			String disaster = line.get(0);
			if(impactMap.containsKey(disaster))
			{
				impactMap.put(disaster, impactMap.get(disaster)+Integer.parseInt(line.get(3)));
			}else
			{
				impactMap.put(disaster, Integer.parseInt(line.get(3)));
			}
			if(mostImp.equals("") || impactMap.get(disaster) > impactMap.get(mostImp))
			{
				mostImp = disaster;
			}
		}
		DisasterDescription dd = new DisasterDescription();
		dd.setEntity(mostImp);
		dd.setNumQuakes((impactMap.get(mostImp)));
		return dd;
	}

	public DisasterDescription getTotalReportedIncidentsByCategory(String category, List<List<String>> records) {
		HashMap<String,Integer> impactMap = new HashMap<String,Integer>();
		String mostImp = "";
		for(List<String> line : records)
		{
			if(!line.get(0).equals(category) || line.get(0).equals("All natural disasters"))
				continue;
			String disaster = line.get(0);
			if(impactMap.containsKey(disaster))
			{
				impactMap.put(disaster, impactMap.get(disaster)+Integer.parseInt(line.get(3)));
			}else
			{
				impactMap.put(disaster, Integer.parseInt(line.get(3)));
			}
			if(mostImp.equals("") || impactMap.get(disaster) > impactMap.get(mostImp))
			{
				mostImp = disaster;
			}
		}
		DisasterDescription dd = new DisasterDescription();
		dd.setEntity(mostImp);
		dd.setNumQuakes((impactMap.get(mostImp)));
		return dd;
	}
	
	/**
	 * This method will return the count if the number of incident falls within the provided range.
	 * To simplify the problem, we assume:
	 * 	+ A value of -1 is provided if the max range is NOT applicable.
	 *  + A min value can be provided, without providing a max value (which then has to be -1 like indicated above).
	 *  + If a max value is provided, then a max value is also needed.
	 */
	public int countImpactfulYearsWithReportedIncidentsWithinRange(List<List<String>> records, int min, int max) {
		if(min == -1)
			min = Integer.MIN_VALUE;
		
		if(max == -1)
			max = Integer.MAX_VALUE;
		
		int validYears = 0;
		
		for(List<String> line : records)
		{
			if(Integer.parseInt(line.get(3)) >= min && Integer.parseInt(line.get(3)) <= max)
			{
				validYears++;
			}
		}
		return validYears;
	}
	
	public boolean firstRecordsHaveMoreReportedIndicents(List<List<String>> records1, List<List<String>> records2) {
		int records1Incidents = 0;
		int records2Incidents = 0;
		
		
		
		for(List<String> line : records1)
		{
			records1Incidents+=Integer.parseInt(line.get(3));
		}
		for(List<String> line : records2)
		{
			records2Incidents+=Integer.parseInt(line.get(3));
		}
		
		
		
		return records1Incidents > records2Incidents;
	}
}
