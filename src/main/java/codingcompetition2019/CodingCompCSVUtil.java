package codingcompetition2019;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class CodingCompCSVUtil {
	final String ALL_CATEGORIES = "ALL";
	public List<List<String>> readCSVFileByCountry(String fileName, String countryName) throws IOException {
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
		List<List<String>> csvFile = new ArrayList<List<String>>();
		String delimiter = ",";
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			String line = "";
			while ((line = br.readLine()) != null) {
				String[] lineData = line.split(delimiter, -1);
				List<String> lineDataList = Arrays.asList(lineData);
				csvFile.add(lineDataList);
			}
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		return csvFile;
	}

	public List<List<String>> readCSVFileWithoutHeaders(String fileName) throws IOException {
        List<List<String>> csvFile = new ArrayList<List<String>>();
        String delimiter = ",";
        boolean first_line = false;
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line = "";
            while ((line = br.readLine()) != null) {
                if (!first_line) {
                    first_line = true;
                    continue;
                }
                String[] lineData = line.split(delimiter, -1);
                List<String> lineDataList = Arrays.asList(lineData);
                csvFile.add(lineDataList);
            }
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        }
        return csvFile;
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
		String mostImpactfulYear = null;
		int maxYearImpact = 0;

		for (List<String> record : records) {
			if (record.get(0).compareTo(category) > 0) {
				break;
			}

			if (!record.get(0).equals(category)) {
				continue;
			}

			int currImpact = Integer.parseInt(record.get(3));
			if (currImpact > maxYearImpact) {
				maxYearImpact = currImpact;
				mostImpactfulYear = record.get(2);
			}
		}
		return new DisasterDescription(mostImpactfulYear);
	}

	public DisasterDescription getMostImpactfulDisasterByYear(String year, List<List<String>> records) {
		// TODO implement this method
        String maxCategory = null;
        int maxDisaster = 0;
        for (List<String> record : records) {
            if (!record.get(0).equals("All natural disasters")) {
                if (record.get(2).equals(year)) {
                    String category = record.get(0);
                    int disaster = Integer.parseInt(record.get(record.size() - 1));
                    if (disaster > maxDisaster) {
                        maxDisaster = disaster;
                        maxCategory = category;
                    }
                }
            }
        }
        return new DisasterDescription(year, maxCategory, maxDisaster);
	}

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
			if (Integer.parseInt(record.get(3)) >= min && Integer.parseInt(record.get(3)) <= max) {
				numYearsInRange++;
			}
		}
		return numYearsInRange;
	}
	
	public boolean firstRecordsHaveMoreReportedIndicents(List<List<String>> records1, List<List<String>> records2) {
		return helperGetTotalReportedIncidents(ALL_CATEGORIES, records1).getReportedIncidentsNum() > helperGetTotalReportedIncidents(ALL_CATEGORIES, records2).getReportedIncidentsNum();
	}

	public DisasterDescription helperGetTotalReportedIncidents (String category, List<List<String>> records) {
		boolean allCategories = false;
		if (category.equals(ALL_CATEGORIES)) {
			allCategories = true;
		}
		DisasterDescription disaster = new DisasterDescription("N/A", category, 0);

		for (List<String> record : records) {
			if (!allCategories && record.get(0).compareTo(category) > 0) {
				break;
			}

			if (!allCategories && !record.get(0).equals(category)) {
				continue;
			}
			disaster.addReportedIncidents(Integer.parseInt(record.get(3)));
		}
		return disaster;
	}
}
