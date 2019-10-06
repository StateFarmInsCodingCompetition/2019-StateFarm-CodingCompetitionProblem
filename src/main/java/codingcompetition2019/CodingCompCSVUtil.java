package codingcompetition2019;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.ArrayList;

public class CodingCompCSVUtil {
	public List<List<String>> readCSVFileByCountry(String fileName, String countryName) throws IOException {
		// TODO implement this method
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
	
	public List<List<String>> readCSVFileWithHeaders(String fileName) throws IOException {
		// TODO implement this method
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
	
	public List<List<String>> readCSVFileWithoutHeaders(String fileName) throws IOException {
		// TODO implement this method
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
	
        // entity, code, year, numIncidents
	public DisasterDescription getMostImpactfulYear(List<List<String>> records) {
            // TODO implement this method
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

	public DisasterDescription getMostImpactfulYearByCategory(String category, List<List<String>> records) {
            // TODO implement this method
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

        // entity, code, year, numIncidents
	public DisasterDescription getMostImpactfulDisasterByYear(String year, List<List<String>> records) {
		// TODO implement this method
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

	public DisasterDescription getTotalReportedIncidentsByCategory(String category, List<List<String>> records) {
		// TODO implement this method
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
	 */
        // entity, code, year, numIncidents
	public int countImpactfulYearsWithReportedIncidentsWithinRange(List<List<String>> records, int min, int max) {
		// TODO implement this method
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
	
	public boolean firstRecordsHaveMoreReportedIndicents(List<List<String>> records1, List<List<String>> records2) {
		// TODO implement this method
            return countImpactfulYearsWithReportedIncidentsWithinRange(records1, 1, -1) > countImpactfulYearsWithReportedIncidentsWithinRange(records2, 1, -1);
	}
}
