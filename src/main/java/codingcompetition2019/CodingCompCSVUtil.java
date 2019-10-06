package codingcompetition2019;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class CodingCompCSVUtil {
	private final String ALL_CATEGORIES = "ALL";
	private final String COMMA_DELIMITER = ",";
	private final String SKIP_HEADER = "SKIP_HEADER";
	private final String ALL_DISASTERS = "All natural disasters";
	private final int ENTITY_COLMN = 0;
	private final int YEAR_COLMN = 2;
	private final int INCIDENTS_COLMN = 3;

	/**
	 * Returns a list of all the records parsed by country
	 * @param fileName the csv filename
	 * @param countryName the country
	 * @return the list of all records
	 * @throws IOException if file is not found
	 */
	public List<List<String>> readCSVFileByCountry(String fileName, String countryName) throws IOException {
		return helperReadCSVFile(fileName, countryName);
	}

	/**
	 * Returns a list of all the records with the first header line
	 * @param fileName the csv filename
	 * @return the list of all the records
	 * @throws IOException if file is not found
	 */
	public List<List<String>> readCSVFileWithHeaders(String fileName) throws IOException {
		return helperReadCSVFile(fileName, ALL_CATEGORIES);
	}

	/**
	 * Returns a list of all the records without the first header line
	 * @param fileName the csv filename
	 * @return the list of all the records
	 * @throws IOException if file is not found
	 */
	public List<List<String>> readCSVFileWithoutHeaders(String fileName) throws IOException {
		return helperReadCSVFile(fileName, SKIP_HEADER);
	}

	/**
	 * Returns a disaster description containing the most impactful year
	 * @param records the records from the csv file
	 * @return the disaster description with the most impactful year
	 */
	public DisasterDescription getMostImpactfulYear(List<List<String>> records) {
		return helperGetMostImpactfulYear(ALL_CATEGORIES, records);
	}

	/**
	 * Returns a disaster description containing the most impactful year by category
	 * @param category the category of the record
	 * @param records the records from the csv file
	 * @return the disaster description with the most impactful year by category
	 */
	public DisasterDescription getMostImpactfulYearByCategory(String category, List<List<String>> records) {
		return helperGetMostImpactfulYear(category, records);
	}

	/**
	 * Returns the most impactful disaster for the specified year.
	 * @param year the specified year
	 * @param records the records from the csv file
	 * @return the disaster description with the most impactful year
	 */
	public DisasterDescription getMostImpactfulDisasterByYear(String year, List<List<String>> records) {
        String maxCategory = null;
        int maxDisaster = 0;
        for (List<String> record : records) {
            if (!getLineEntity(record).equals(ALL_DISASTERS)) {
                if (getLineYear(record).equals(year)) {
                    String category = getLineEntity(record);
                    int numDisasters = Integer.parseInt(getNumIncidents(record));
                    if (numDisasters > maxDisaster) {
                        maxDisaster = numDisasters;
                        maxCategory = category;
                    }
                }
            }
        }
        return new DisasterDescription(year, maxCategory, maxDisaster);
	}

	/**
	 * Returns the total number of incidents based on the records that match the category.
	 * @param category the category of records
	 * @param records the records from the csv file
	 * @return the total number of reported incidents
	 */
	public DisasterDescription getTotalReportedIncidentsByCategory(String category, List<List<String>> records) {
		return helperGetTotalReportedIncidents(category, records);
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
		int numYearsInRange = 0;
		for (List<String> record : records) {
			if (Integer.parseInt(getNumIncidents(record)) >= min && Integer.parseInt(getNumIncidents(record)) <= max) {
				numYearsInRange++;
			}
		}
		return numYearsInRange;
	}

	/**
	 * Determines whether the first record has more total incidents than the second record.
	 * @param records1 the first csv file records
	 * @param records2 the second csv file records
	 * @return true if the first csv file has more incidents or false otherwise
	 */
	public boolean firstRecordsHaveMoreReportedIndicents(List<List<String>> records1, List<List<String>> records2) {
		return helperGetTotalReportedIncidents(ALL_CATEGORIES, records1).getReportedIncidentsNum() > helperGetTotalReportedIncidents(ALL_CATEGORIES, records2).getReportedIncidentsNum();
	}

	/**
	 * Helper method to get the total number of incidents that match the category.
	 * @param category the category of incidents or all categories
	 * @param records the records from the csv file
	 * @return a disaster description object containing the total reported incidents
	 */
	public DisasterDescription helperGetTotalReportedIncidents (String category, List<List<String>> records) {
		boolean allCategories = false;
		if (category.equals(ALL_CATEGORIES)) {
			allCategories = true;
		}
		DisasterDescription disaster = new DisasterDescription("N/A", category, 0);

		for (List<String> record : records) {

			if (!allCategories && !getLineEntity(record).equals(category)) {
				continue;
			}
			disaster.addReportedIncidents(Integer.parseInt(getNumIncidents(record)));
		}
		return disaster;
	}

	/**
	 * Helper method to get the most impactful year (most number of incidents) by category.
	 * @param category the category of the entity in the record or all categories
	 * @param records the records from the csv file
	 * @return the disaster description containing the most impactful year
	 */
	public DisasterDescription helperGetMostImpactfulYear (String category, List<List<String>> records) {
		boolean allCategories = false;
		if (category.equals(ALL_CATEGORIES)) {
			allCategories = true;
		}

		String mostImpactfulYear = null;
		int maxYearImpact = 0;

		for (List<String> record : records) {
			if (!allCategories && !getLineEntity(record).equals(category)) {
				continue;
			}

			int currImpact = Integer.parseInt(getNumIncidents(record));
			if (currImpact > maxYearImpact) {
				maxYearImpact = currImpact;
				mostImpactfulYear = getLineYear(record);
			}
		}
		return new DisasterDescription(mostImpactfulYear);
	}

	/**
	 * Helper method to parse csv file based on the specified parameters. Extra parameter is ALL_CATEGORIES
	 * if entire csv file needs to be parsed, SKIP_HEADER if entire csv needs to be parsed without header,
	 * or the country name if a specific country needs to be parsed.
	 *
	 * @param fileName the csv file
	 * @param extraParam the extra parameter (all categories/skip header/country name)
	 * @return a list of all the entries in csv file matching the query
	 * @throws IOException if file is not found
	 */
	private List<List<String>> helperReadCSVFile(String fileName, String extraParam) throws IOException{
		boolean allCategories = false;
		boolean header = true;
		if (extraParam.equals(SKIP_HEADER)) {
			header = false;
			allCategories = true;
		} else if (extraParam.equals(ALL_CATEGORIES)) {
			allCategories = true;
		}
		List<List<String>> csvFile = new ArrayList<List<String>>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			String line = "";
			while ((line = br.readLine()) != null) {
				if (!header) {
					header = true;
					continue;
				}
				String[] lineData = line.split(COMMA_DELIMITER, -1);
				List<String> lineDataList = Arrays.asList(lineData);
				if (!allCategories && !getLineEntity(lineDataList).equals(extraParam)) {
					continue;
				}
				csvFile.add(lineDataList);
			}
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		return csvFile;
	}

	/**
	 * Helper method returns the year from the current line in csv file.
	 * @param lineData current line in csv file
	 * @return year of current line
	 */
	private String getLineYear(List<String> lineData) {
		return lineData.get(YEAR_COLMN);
	}

	/**
	 * Helper method returns the entity from the current line in csv file.
	 * @param lineData current line in csv file
	 * @return entity of current line
	 */
	private String getLineEntity(List<String> lineData) {
		return lineData.get(ENTITY_COLMN);
	}

	/**
	 * Helper method returns the number of incidents from the current line in csv file.
	 * @param lineData current line in csv file
	 * @return year of current line
	 */
	private String getNumIncidents(List<String> lineData) {
		return lineData.get(INCIDENTS_COLMN);
	}
}
