package codingcompetition2019;

import java.io.IOException;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

public class CodingCompCSVUtil {
	/**
	 * Produces a list containing a list of table entries found in the CSV file referred to by fileName with a country listing of countryName.
	 * If the CSV cannot be read, an IOException is thrown.
	 *
	 * @param  fileName    the URI linking to the CSV table containing data to process
	 * @param  countryName the name of the country to filter for
	 * @return             a list of lists containing strings parsed exactly based on CSV format
	 * @throws IOException if table cannot be found
	 */
	
	public List<List<String>> readCSVFileByCountry(String fileName, String countryName) throws IOException {
		String line = "";
        String cvsSplitBy = ",";
        List<List<String>> fullList = new ArrayList<List<String>>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

            while ((line = br.readLine()) != null) {

            	String[] country = line.split(cvsSplitBy);
            	
                if (countryName.equals(country[0])) {
                	fullList.add(Arrays.asList(country));
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fullList;
	}
	
	/**
	 * Produces a list containing a list of table entries found in the CSV file referred to by fileName, including headers.
	 * If the CSV cannot be read, an IOException is thrown.
	 *
	 * @param  fileName    the URI linking to the CSV table containing data to process
	 * @return             a list of lists containing strings parsed exactly based on CSV format
	 * @throws IOException if table cannot be found
	 */
	
	public List<List<String>> readCSVFileWithHeaders(String fileName) throws IOException {
		// TODO implement this method
        String line = "";
        String cvsSplitBy = ",";
        List<List<String>> fullList = new ArrayList<List<String>>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

            while ((line = br.readLine()) != null) {

                fullList.add(Arrays.asList(line.split(cvsSplitBy)));

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fullList;
	}
	
	/**
	 * Produces a list containing a list of table entries found in the CSV file referred to by fileName, excluding headers.
	 * If the CSV cannot be read, an IOException is thrown.
	 *
	 * @param  fileName    the URI linking to the CSV table containing data to process
	 * @return             a list of lists containing strings parsed exactly based on CSV format
	 * @throws IOException if table cannot be found
	 */
	
	public List<List<String>> readCSVFileWithoutHeaders(String fileName) throws IOException {
		// TODO implement this method
		String line = "";
        String cvsSplitBy = ",";
        List<List<String>> fullList = new ArrayList<List<String>>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
        	
        	br.readLine(); // Skips first line (contains header)
        	
            while ((line = br.readLine()) != null) {

                fullList.add(Arrays.asList(line.split(cvsSplitBy)));

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fullList;
	}
	
	/**
	 * Produces a DisasterDescription containing information regarding the most impactful year, as measured in the list records.
	 *
	 * @param  records     a list of lists containing all data points with category, code (if applicable), year, and occurrence rate
	 * @return             a DisasterDescription containing the most impactful year by calling setYear()
	 */
	
	public DisasterDescription getMostImpactfulYear(List<List<String>> records) {
		// TODO implement this method
		HashMap<String, Integer> yearMap = new HashMap<String, Integer>();
		String topYear = "";
		int topVal = 0;
		for (List<String> currentList: records) {
			String year = currentList.get(2);
			int incident;
			Optional<Integer> init = atoi(currentList, 3);
			if (!init.isPresent()) {
				continue;
			}
			incident = init.get();
			yearMap.put(year, yearMap.getOrDefault(year, 0) + incident);
			if (yearMap.get(year) > topVal) {
				topYear = year;
				topVal = yearMap.get(year);
			}
		}
		DisasterDescription curr = new DisasterDescription("", "", topYear, 0);
		return curr;
	}

	/**
	 * Produces a DisasterDescription containing information regarding the most impactful year from the correct category, as measured in the list records.
	 *
	 * @param  records     a list of lists containing all data points with category, code (if applicable), year, and occurrence rate
	 * @param  category    a String containing the category to match with the list
	 * @return             a DisasterDescription containing the most impactful year by calling setYear()
	 */
	
	public DisasterDescription getMostImpactfulYearByCategory(String category, List<List<String>> records) {
		// TODO implement this method
		HashMap<String, Integer> yearMap = new HashMap<String, Integer>();
		String topYear = "";
		int topVal = 0;
		int incident;
		for (List<String> currentList: records) {
			String year = currentList.get(2);
			Optional<Integer> init = atoi(currentList, 3);
			if (!init.isPresent()) {
				continue;
			}
			incident = init.get();
			if (category.equals(currentList.get(0))) {
				yearMap.put(year, yearMap.getOrDefault(year, 0) + incident);
				if (yearMap.get(year) > topVal) {
					topYear = year;
					topVal = yearMap.get(year);
				}
			}
		}
		DisasterDescription curr = new DisasterDescription("", "", topYear, 0);
		return curr;
		
	}

	/**
	 * Produces a DisasterDescription containing information regarding the most impactful disaster type with a specified year, as measured in the list records.
	 *
	 * @param  records     a list of lists containing all data points with category, code (if applicable), year, and occurrence rate
	 * @param  year        a String containing the year to match with the list
	 * @return             a DisasterDescription containing the most impactful year by calling setYear()
	 */
	
	public DisasterDescription getMostImpactfulDisasterByYear(String year, List<List<String>> records) {
		// TODO implement this method
		HashMap<String, Integer> disasterMap = new HashMap<String, Integer>();
		String topDisaster = "";
		int topVal = 0;
		int incident;
		for (List<String> currentList: records) {
			String currDisaster = currentList.get(0);
			Optional<Integer> init = atoi(currentList, 3);
			if (!init.isPresent()) {
				continue;
			}
			incident = init.get();
            if (year.equals(currentList.get(2)) && !currDisaster.equals("All natural disasters")) { //All natural disasters is the sum of all inputs, so excluding the value removes this exception
                disasterMap.put(currDisaster, disasterMap.getOrDefault(currDisaster, 0) + incident);
                if (disasterMap.get(currDisaster) > topVal) {
                    topDisaster = currDisaster;
                    topVal = disasterMap.get(currDisaster);
                }
            }
		}
		DisasterDescription curr = new DisasterDescription(topDisaster, "", year, topVal);
		return curr;
	}

	/**
	 * Produces a DisasterDescription containing information regarding the total number of reported incidents of a specified categry, as measured in the list records.
	 *
	 * @param  records     a list of lists containing all data points with category, code (if applicable), year, and occurrence rate
	 * @param  category    a String containing the category to match with the list
	 * @return             a DisasterDescription containing the most impactful year by calling setYear()
	 */
	
	public DisasterDescription getTotalReportedIncidentsByCategory(String category, List<List<String>> records) {
		// TODO implement this method
		int incident = 0;
		int currIncident;
		for (List<String> currentList: records) {
			Optional<Integer> init = atoi(currentList, 3);
			if (!init.isPresent()) {
				continue;
			}
			currIncident = init.get();
			if (category.equals(currentList.get(0))) {
				incident += currIncident;
			}
		}
		
		DisasterDescription curr = new DisasterDescription(category, "", "", incident);
		return curr;
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
		if (max == -1) {
			max = Integer.MAX_VALUE;
		}
		int total = 0;
		int incident;
		for (List<String> currentList: records) {
			Optional<Integer> init = atoi(currentList, 3);
			if (!init.isPresent()) {
				continue;
			}
			incident = init.get();
			if (incident >= min && incident <= max) {
				total += 1;
			}
		}
		return total;
	}
	
	/**
	 * Determines if the first record contains more incidents than the second record
	 *
	 * @param  records1    a list of lists containing all data points with category, code (if applicable), year, and occurrence rate for the first dataset
	 * @param  records2    a list of lists containing all data points with category, code (if applicable), year, and occurrence rate for the second dataset
	 * @return             a boolean describing whether the first record contains more reported incidents
	 */
	
	public boolean firstRecordsHaveMoreReportedIndicents(List<List<String>> records1, List<List<String>> records2) {
		// TODO implement this method
		return records1.size() > records2.size();
	}
	
	public Optional<Integer> atoi(List<String> curr, int index) {
		try {
			return Optional.of(Integer.parseInt(curr.get(index)));
		} catch (NumberFormatException e) {
			return Optional.empty();
		}
	}
}
