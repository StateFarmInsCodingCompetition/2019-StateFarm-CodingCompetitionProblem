package main.java.codingcompetition2019;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CodingCompCSVUtil {


	public List<List<String>> readCSVFileByCountry(String fileName, String countryName) throws IOException {

		List<List<String>> queriedData = new ArrayList<>();

		BufferedReader csvReader = new BufferedReader(new FileReader(fileName));

		String row;
		while ((row = csvReader.readLine()) != null) {
			String[] data = row.split(",");
			//Check entity column for Country
			if (countryName.equals(data[0])) {
				//Add to return list
				queriedData.add(new ArrayList<>(Arrays.asList(row)));
			}
		}
		csvReader.close();
		return queriedData;
	}
	
	public List<List<String>> readCSVFileWithHeaders(String fileName) throws IOException {
		List<List<String>> queriedData = new ArrayList<>();

		BufferedReader csvReader = new BufferedReader(new FileReader(fileName));

		String row;
		while ((row = csvReader.readLine()) != null) {
			queriedData.add(new ArrayList<>(Arrays.asList(row)));
		}
		csvReader.close();
		return queriedData;
	}
	
	public List<List<String>> readCSVFileWithoutHeaders(String fileName) throws IOException {
		List<List<String>> queriedData = new ArrayList<>();

		BufferedReader csvReader = new BufferedReader(new FileReader(fileName));

		String row;
		while ((row = csvReader.readLine()) != null) {
			queriedData.add(new ArrayList<>(Arrays.asList(row)));
		}
		csvReader.close();

		queriedData.remove(0);
		return queriedData;
	}
	
	public DisasterDescription getMostImpactfulYear(List<List<String>> records) {
		// Third column in all is Year
		//Fourth column in all is occurrance count

		int highestImpact = Integer.MIN_VALUE;

		DisasterDescription disasterDescription = null;

		for (List<String> currList: records) {
			for (String record: currList) {

				String[] data = record.split(",");
				int currImpact = Integer.parseInt(data[3]);

				if (!data[0].equals("All natural disasters") && currImpact > highestImpact) {
					highestImpact = currImpact;

					disasterDescription = new DisasterDescription(data[0], data[1], data[2], data[3]);
				}
			}
		}

		return disasterDescription;
	}

	public DisasterDescription getMostImpactfulYearByCategory(String category, List<List<String>> records) {

		int highestImpact = Integer.MIN_VALUE;
		DisasterDescription disasterDescription = null;

		for (List<String> currList: records) {
			for (String record: currList) {

				//Only consider a row if category matches

				String[] data = record.split(",");
				String currCategory = data[0];
				int currImpact = Integer.parseInt(data[3]);

				if (!data[0].equals("All natural disasters") && currCategory.equals(category) && currImpact > highestImpact) {
					highestImpact = currImpact;
					disasterDescription = new DisasterDescription(data[0], data[1], data[2], data[3]);
				}
			}
		}

		return disasterDescription;
	}

	public DisasterDescription getMostImpactfulDisasterByYear(String year, List<List<String>> records) {
		int highestImpact = Integer.MIN_VALUE;
		DisasterDescription disasterDescription = null;

		for (List<String> currList: records) {
			for (String record: currList) {

				//Only consider a row if year matches

				String[] data = record.split(",");
				String currYear = data[2];
				int currImpact = Integer.parseInt(data[3]);

				if (!data[0].equals("All natural disasters") && currYear.equals(year) && currImpact >= highestImpact) {
					System.out.println(data[0]);
					highestImpact = currImpact;
					disasterDescription = new DisasterDescription(data[0], data[1], data[2], data[3]);
				}
			}
		}

		return disasterDescription;
	}

	public DisasterDescription getTotalReportedIncidentsByCategory(String category, List<List<String>> records) {
		int totalImpact = 0;


		for (List<String> currList: records) {
			for (String record: currList) {

				//Only consider a row if category matches

				String[] data = record.split(",");
				String currCategory = data[0];

				if (!data[0].equals("All natural disasters") && currCategory.equals(category)) {
					totalImpact += Integer.parseInt(data[3]);;
				}
			}
		}

		return new DisasterDescription(null, null, null, Integer.toString(totalImpact));
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

		int numValidYears = 0;

		for (List<String> elem : records) {

			for (String str : elem) {

				String[] vals1 = str.split(",");
				int currImpact = Integer.parseInt(vals1[3]);

				// case one: if max isn't provided
				// case two: max IS provided
				if (max == -1) {
					if (currImpact >= min) {
						numValidYears++;
					}
				} else {
					if (currImpact >= min && currImpact <= max) {
						numValidYears++;
					}
				}
			}
		}

		return numValidYears;
	}

	public boolean firstRecordsHaveMoreReportedIndicents(List<List<String>> records1, List<List<String>> records2) {
		// TODO implement this method

		boolean isGreater = false;

		int numReports1 = 0;
		int numReports2 = 0;

		for (List<String> elem : records1) {

			for (String str : elem) {

				String[] vals1 = str.split(",");
				int currImpact1 = Integer.parseInt(vals1[3]);
				numReports1 += currImpact1;
			}
		}

		for (List<String> elem2 : records2) {
			for (String str2 : elem2) {

				String[] vals2 = str2.split(",");
				int currImpact2 = Integer.parseInt(vals2[3]);
				numReports2 += currImpact2;
			}
		}

		if (numReports1 > numReports2) {
			isGreater = true;
		}

		return isGreater;
	}

}
