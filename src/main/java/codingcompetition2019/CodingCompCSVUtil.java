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
		List<List<String>> queriedData = readCSVFileWithHeaders(fileName);
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

				if (currImpact > highestImpact) {
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

		int numValidYearsInRange = 0;

		for (List<String> record : records) {
			for (String str : record) {

				String[] dataSplit = str.split(",");
				int currImpact = Integer.parseInt(dataSplit[3]);

				// case one: if max isn't provided
				// case two: max IS provided
				if (max == -1) {
					if (currImpact >= min) {
						numValidYearsInRange++;
					}
				} else {
					if (currImpact >= min && currImpact <= max) {
						numValidYearsInRange++;
					}
				}
			}
		}

		return numValidYearsInRange;
	}

	public boolean firstRecordsHaveMoreReportedIndicents(List<List<String>> records1, List<List<String>> records2) {
		// TODO implement this method

		boolean isGreater = false;
		int numReports1 = 0, numReports2 = 0;

		numReports1 += getTotalNumReports(records1);
		numReports2 += getTotalNumReports(records2);

		if (numReports1 > numReports2) {
			isGreater = true;
		}

		return isGreater;
	}

	public int getTotalNumReports(List<List<String>> records) {
		int total = 0;
		for (List<String> record : records) {
			for (String str : record) {
				String[] dataSplit = str.split(",");
				int currImpact = Integer.parseInt(dataSplit[3]);
				total += currImpact;
			}
		}
		return total;
	}


	/**
	 * Custom Methods
	 */


	// Given a location name, return whether or not the individual is a low risk, medium risk or high risk individual based on some parameters for any given year
	public String evaluateRisk(String countryName, String year) throws IOException {

		String risk_analysis;
		int totalIncidents = 0;

		String significantEarthquakeFileNameName = "src/main/resources/significant-earthquakes.csv";
		String significantVolcanicEruptionsFileName = "src/main/resources/significant-volcanic-eruptions.csv";

		List<List<String>> volcanic_data = readCSVFileByCountry(significantVolcanicEruptionsFileName, countryName);
		List<List<String>> earthquake_data = readCSVFileByCountry(significantEarthquakeFileNameName, countryName);



		//Our method will see the total number of incidents this location has had in the past 10 years. We will slot these into catagories of < 5, <10, and 11+

		totalIncidents += getTotalNumReportsInYearRange(volcanic_data, year);
		totalIncidents += getTotalNumReportsInYearRange(earthquake_data, year);




		if (totalIncidents >= 10) {
			risk_analysis = "HIGH RISK";
		} else if (totalIncidents < 10 && totalIncidents >= 5) {
			risk_analysis = "MEDIUM RISK";
		} else {
			risk_analysis = "LOW RISK";
		}



		return risk_analysis;

	}

	public int getTotalNumReportsInYearRange(List<List<String>> records, String year) {
		int total = 0;

		int high_year = Integer.parseInt(year);
		int low_year = high_year - 10;
		for (List<String> record : records) {
			for (String str : record) {
				String[] dataSplit = str.split(",");

				if (Integer.parseInt(dataSplit[2]) >= low_year && Integer.parseInt(dataSplit[2]) <= high_year) {
					total += Integer.parseInt(dataSplit[3]);
				}
			}
		}
		return total;
	}

	/* Custom method
		To display countries that had a disaster based on year of input.
		If there are countries that reported incidences during that year, they will get outputted.
	 */
	public void countriesOfIncidenceThatYear(int year, String filename) throws IOException {
		List<String> listOfCountries = new ArrayList<>();

		List<List<String>> data = readCSVFileWithoutHeaders(filename);
		for (List<String> record : data) {
			for (String str : record) {
				String[] dataSplit = str.split(",");
				if (year == Integer.parseInt(dataSplit[2])) {
					listOfCountries.add(dataSplit[0]);
				}
			}
		}

		for (String country : listOfCountries) {
			System.out.print(country + " ");
		}
	}

}

