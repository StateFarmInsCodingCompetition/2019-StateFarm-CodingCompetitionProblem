package codingcompetition2019;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

/**
 * A class holding methods to analyze csv files of disasters
 * @author Sidhartha Chaganti, schaganti@gatech.edu
 * @version 1.0.0
 */
public class CodingCompCSVUtil {

        /**
         * Uses a HashSet to find the unique categories in a given records List of List of Strings and iterates through them to find the average number of reported incidents per year by category
         * @param records The List of List of Strings containing the disaster entries
         * @return a List of DisasterDescription instances, each containing the average number of disaster incidents per year for their category
         */
        public List<DisasterDescription> averageNumberOfReportedIncidentsPerYearByCategory(List<List<String>> records) {
            List<DisasterDescription> finalList = new ArrayList<DisasterDescription>();
            Set<String> categorySet = new HashSet<String>();
            for (List<String> entry : records) {
                categorySet.add(entry.get(0));
            }
            for (String category : categorySet) {
                int totalForCategory = 0;
                int numEntries = 0;
                for (List<String> entry : records) {
                    if (entry.get(0).equals(category)) {
                        totalForCategory += Integer.parseInt(entry.get(3));
                    }
                    numEntries++;
                }
                double avgIncidents = (double) (totalForCategory) / numEntries;
                DisasterDescription temp = new DisasterDescription(category, avgIncidents);
                finalList.add(temp);
            }
            return finalList;
        }

        /**
         * Reads a specified CSV file and returns only the entries pertaining to a specified country
         * @param fileName The csv file to be read
         * @param countryName The name of the country with the disasters we are searching for
         * @return A List of List of Strings containing the entries of the disasters from a particular country
         */
	public List<List<String>> readCSVFileByCountry(String fileName, String countryName) throws IOException {
                List<List<String>> finalList = new ArrayList<List<String>>();
                BufferedReader csvReader = new BufferedReader(new FileReader(fileName));
                String row = csvReader.readLine();
                while ( (row = csvReader.readLine()) != null ) {
                    String[] data = row.split(",");
                    if (data[0].equals(countryName)) {
                        List<String> temp = new ArrayList<String>();
                        for (String d : data) {
                            temp.add(d);
                        }
                        finalList.add(temp);
                    }
                }

                csvReader.close();
		return finalList;
	}
	
        /**
         * Reads a CSV file and returns all entries 
         * @param fileName The csv file to be read
         * @return A List of List of Strings of all entries from the csv file
         */
	public List<List<String>> readCSVFileWithHeaders(String fileName) throws IOException {
                List<List<String>> finalList = new ArrayList<List<String>>();
                BufferedReader csvReader = new BufferedReader(new FileReader(fileName));
                String row;
                while ( (row = csvReader.readLine()) != null ) {
                    String[] data = row.split(",");
                    List<String> temp = new ArrayList<String>();
                    for (String d : data) {
                        temp.add(d);
                    }
                    finalList.add(temp);
                }

                csvReader.close();
		return finalList;
	}
	
        /**
         * Reads a CSV file and returns all entries except for the header entry
         * @param fileName The csv file to be read
         * @return A List of List of Strings of all entries from the csv file except the header entry
         */
	public List<List<String>> readCSVFileWithoutHeaders(String fileName) throws IOException {
                List<List<String>> finalList = new ArrayList<List<String>>();
                BufferedReader csvReader = new BufferedReader(new FileReader(fileName));
                String row = csvReader.readLine();
                while ( (row = csvReader.readLine()) != null ) {
                    String[] data = row.split(",");
                    List<String> temp = new ArrayList<String>();
                    for (String d : data) {
                        temp.add(d);
                    }
                    finalList.add(temp);
                }

                csvReader.close();
		return finalList;
	}
	
        /**
         * Finds and returns the DisasterDescriptionInstance of the record entry with the most impactful year (the year with the most incidents)
         * @param records The List of List of Strings containing the disaster entries
         * @return a DisasterDescription instance of the year with the most disaster incidents
         */
	public DisasterDescription getMostImpactfulYear(List<List<String>> records) {
            // let first entry be the most impactful
            List<String> first = records.get(0);
            DisasterDescription mostImpactfulDisasterDescription = new DisasterDescription(first.get(0), first.get(2), Integer.parseInt(first.get(3)));
            for (List<String> entry : records) {
                int numIncidents = Integer.parseInt(entry.get(3));
                if (numIncidents > mostImpactfulDisasterDescription.getReportedIncidentsNum()) {
                    // we need to make a new object
                    mostImpactfulDisasterDescription = new DisasterDescription(entry.get(0), entry.get(2), Integer.parseInt(entry.get(3)));
                }
            }
		return mostImpactfulDisasterDescription;
	}

        /**
         * Finds and returns the DisasterDescriptionInstance of the record entry with the most impactful year (the year with the most incidents) and the specified category
         * @param category The category of disaster to filter records by
         * @param records The List of List of Strings containing the disaster entries
         * @return a DisasterDescription instance of the year with the most disaster incidents and the specified category
         */
	public DisasterDescription getMostImpactfulYearByCategory(String category, List<List<String>> records) {
            // need to find the first instance of the category we want
            DisasterDescription mostImpactfulDisasterDescription = new DisasterDescription(category, null, -1);
            for (List<String> entry : records) {
                int numIncidents = Integer.parseInt(entry.get(3));
                if (entry.get(0).equals(category) && numIncidents > mostImpactfulDisasterDescription.getReportedIncidentsNum()) {
                    // we need to make a new object
                    mostImpactfulDisasterDescription = new DisasterDescription(entry.get(0), entry.get(2), Integer.parseInt(entry.get(3)));
                }
            }
		return mostImpactfulDisasterDescription;
	}

        /**
         * Returns the DisasterDescription instance of the most impactful type of disaster based on a specified year
         * @param year The year to filter records by
         * @param records The List of List of Strings containing the entries of disasters
         * @return A DisasterDescription instance containing the category of the most impactful disaster during a specified year
         */
	public DisasterDescription getMostImpactfulDisasterByYear(String year, List<List<String>> records) {
            DisasterDescription mostImpactfulDisasterDescription = new DisasterDescription(null, year, -1);
            for (List<String> entry : records) {
                int numIncidents = Integer.parseInt(entry.get(3));
                if (entry.get(2).equals(year) && !(entry.get(0).equals("All natural disasters")) && numIncidents > mostImpactfulDisasterDescription.getReportedIncidentsNum()) {
                    // we need to make a new object
                    mostImpactfulDisasterDescription = new DisasterDescription(entry.get(0), entry.get(2), Integer.parseInt(entry.get(3)));
                }
            }
            return mostImpactfulDisasterDescription;
	}

        /**
         * Returns a DisasterDescription instance of the total reported incidents of a disaster category
         * @param category The category to filter records by to find the total number of reported incidents
         * @param records The List of List of Strings containing the disaster entries
         * @return a DisasterDescription containing the total number of incidents from a specified category
         */
	public DisasterDescription getTotalReportedIncidentsByCategory(String category, List<List<String>> records) {
            int totalForCategory = 0;
            for (List<String> entry : records) {
                if (entry.get(0).equals(category)) {
                    totalForCategory += Integer.parseInt(entry.get(3));
                }
            }
            return new DisasterDescription(category, null, totalForCategory);
	}
	
	/**
	 * This method will return the count if the number of incident falls within the provided range.
	 * To simplify the problem, we assume:
	 * 	+ A value of -1 is provided if the max range is NOT applicable.
	 *  + A min value can be provided, without providing a max value (which then has to be -1 like indicated above).
	 *  + If a max value is provided, then a max value is also needed.
         *  @param records The List of List of Strings containing the disaster entries
         *  @param min The minimum number of incidents to consider in the total (inclusive)
         *  @param max The maximum number of incidents to consider in the total (inclusive) (Can be -1, which indicates no max and retrieve all values above minimum)
         *  @return The count of impactful years within the specified range
	 */
	public int countImpactfulYearsWithReportedIncidentsWithinRange(List<List<String>> records, int min, int max) {
            int total = 0;
            boolean hasMax = (max == -1) ? false : true;

            for (List<String> entry : records) {
                int entryNum = Integer.parseInt(entry.get(3));
                if (hasMax) {
                    if (entryNum >= min && entryNum <= max) {
                        total += 1;
                    }
                } else {
                    if (entryNum >= min) {
                        total += 1;
                    }
                }
            }
            return total;
	}
	
        /**
         * Returns whether the first records List contains more reported incidents than the second records List
         * @param records1 The first List of List of Strings containing disaster entries
         * @param records2 The second List of List of Strings continaining disaster entries
         * @return A boolean of whether records1 contains more reported incidents than records2
         */
	public boolean firstRecordsHaveMoreReportedIndicents(List<List<String>> records1, List<List<String>> records2) {
            return countImpactfulYearsWithReportedIncidentsWithinRange(records1, 1, -1) > countImpactfulYearsWithReportedIncidentsWithinRange(records2, 1, -1);
	}
}
