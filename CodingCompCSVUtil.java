package codingcompetition2019;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CodingCompCSVUtil {
	public List<List<String>> readCSVFileByCountry(String fileName, String countryName) throws IOException {
		Scanner sc = new Scanner(new File(fileName));
		List<List<String>> fin = new ArrayList<List<String>>();
		while(sc.hasNextLine()) {
			String line = sc.nextLine();
			String[] ar = line.split(",");
			if(ar[0].equals(countryName)) {
				ArrayList<String> temp = new ArrayList<String>();
				temp.add(line);
				fin.add(temp);
			}
		}
		sc.close();
		return fin;
	}
	
	public List<List<String>> readCSVFileWithHeaders(String fileName) throws IOException {
		Scanner sc = new Scanner(new File(fileName));
		List<List<String>> fin = new ArrayList<List<String>>();
		while (sc.hasNextLine()) {
			String line = sc.nextLine();
			String[]ar = line.split(",");
			ArrayList<String> temp = new ArrayList<String>();
			temp.add(line);
			fin.add(temp);
		}
		sc.close();
		return fin;
	}
	
	public List<List<String>> readCSVFileWithoutHeaders(String fileName) throws IOException {
		Scanner sc = new Scanner(new File(fileName));
		sc.nextLine();
		List<List<String>> fin = new ArrayList<List<String>>();
		while (sc.hasNextLine()) {
			String line = sc.nextLine();
			String[]ar = line.split(",");
			ArrayList<String> temp = new ArrayList<String>();
			temp.add(line);
			fin.add(temp);
		}
		sc.close();
		return fin;
	}
	
	public DisasterDescription getMostImpactfulYear(List<List<String>> records) {
		DisasterDescription dd = new DisasterDescription(records);
		return dd;
	}

	public DisasterDescription getMostImpactfulYearByCategory(String category, List<List<String>> records) {
		DisasterDescription dd = new DisasterDescription(records);
		dd.setYearByCategory(category);
		return dd;
	}

	public DisasterDescription getMostImpactfulDisasterByYear(String year, List<List<String>> records) {
		DisasterDescription dd = new DisasterDescription(records);
		dd.setDisasterByYear(year);
		return dd;
	}

	public DisasterDescription getTotalReportedIncidentsByCategory(String category, List<List<String>> records) {
		DisasterDescription dd = new DisasterDescription(records);
		dd.addUpIncedentsByCategory(category);
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
		int inc = 0;
		for(int i = 0; i < records.size();i++) {
				String[] ar = records.get(i).get(0).split(",");
				System.out.println(ar[0] + " "+ ar[3] +" " + records.size());
				if(max == -1) {
				if(Integer.parseInt(ar[3]) >= min){
					inc += 1;
				}
				}else {
					if(Integer.parseInt(ar[3]) >= min && Integer.parseInt(ar[3])<=max){
						inc += 1;
					}
				}
				
		}
		return inc;
	}
	
	public boolean firstRecordsHaveMoreReportedIndicents(List<List<String>> records1, List<List<String>> records2) {
		DisasterDescription dd1 = new DisasterDescription(records1);
		DisasterDescription dd2 = new DisasterDescription(records2);
		return dd1.getReportedIncidentsNum() > dd2.getReportedIncidentsNum();
	}
}
