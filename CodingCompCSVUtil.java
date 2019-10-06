package codingcompetition2019;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CodingCompCSVUtil {
	public List<List<String>> readCSVFileByCountry(String fileName, String countryName) throws IOException {
		List<List<String>> rows = new ArrayList<List<String>>();
		
		try {
		Scanner file = new Scanner (new File (fileName));
		
		if (file.nextLine ().split(",")[0].equals ("Entity")){
			file.nextLine ();
		}
		
		while (file.hasNext()) {
			
			
			String x  = file.nextLine();
			if (x.contains(countryName)) {
				rows.add(Arrays.asList(x.split(",")));
			}
			
			
			}

		}
		catch (FileNotFoundException e){
			System.out.print(e);
		}
		
		return rows;
	}
	
	public List<List<String>> readCSVFileWithHeaders(String fileName) throws IOException {
		List<List<String>> rows = new ArrayList<List<String>>();
		
		try {
		Scanner file = new Scanner (new File (fileName));


		while (file.hasNext()) {
			
			
			rows.add(Arrays.asList(file.nextLine ().split(",")));
			
			}

		}
		catch (FileNotFoundException e){
			System.out.print(e);
		}

		return rows;
	}

	
	
	public List<List<String>> readCSVFileWithoutHeaders(String fileName) throws IOException {
		List<List<String>> rows = new ArrayList<List<String>>();
		
		try {
		Scanner file = new Scanner (new File (fileName));
		

		file.nextLine ();

		while (file.hasNext()) {
			

			rows.add(Arrays.asList(file.nextLine ().split(",")));
			
			}

		}
		catch (FileNotFoundException e){
			System.out.print(e);
		}

		return rows;
	}
	
	public DisasterDescription getMostImpactfulYear(List<List<String>> records) {
		int max = 0;
		String year = "";
		String cat = "";
		
		for (List <String> x : records) {
			int number2 = Integer.valueOf(x.get(3));
			if(number2 > max) {
				max = number2;
				year = x.get(2);
				cat = x.get(0);
			}

		}


		return new DisasterDescription (year,cat,max);
	}

	public DisasterDescription getMostImpactfulYearByCategory(String category, List<List<String>> records) {
		int max = 0;
		String year = "";
		String cat = "";
		
		for (List <String> x : records) {
			if (x.contains (category)) {
				int number2 = Integer.valueOf(x.get(3));
				if(number2 > max) {
					max = number2;
					year = x.get(2);
					cat = x.get(0);
					}
			}

		}
		return new DisasterDescription (year,cat,max);
		
	}

	public DisasterDescription getMostImpactfulDisasterByYear(String year, List<List<String>> records) {
		int max = 0;
		String ourYear = "";
		String cat = "";
		
		for (List <String> x : records) {
			if (x.contains (year)&& !(x.contains("All natural disasters"))) {

				int number2 = Integer.valueOf(x.get(3));
				if(number2 > max) {
					max = number2;
					ourYear = x.get(2);
					cat = x.get(0);
					}
			}

		}
		return new DisasterDescription (ourYear,cat,max);
	}

	public DisasterDescription getTotalReportedIncidentsByCategory(String category, List<List<String>> records) {
		int total = 0;
		String year = "0000";
		String cat = category;
		
		for (List <String> x : records) {
			if (x.contains(category)) {
				total += Integer.valueOf(x.get(3));

			}
		}


		return new DisasterDescription (year,cat,total);		

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

		int count = 0;
		for (List <String> list : records) {
			if (max == -1 && Integer.valueOf (list.get(3))>=min){
				count++;
			}
				
			else if (Integer.valueOf (list.get(3))<= max && Integer.valueOf (list.get(3))>=min){
				count++;
			}
		}
		return count;
	}
	
	public boolean firstRecordsHaveMoreReportedIndicents(List<List<String>> records1, List<List<String>> records2) {
		int count1 = 0;
		int count2 = 0;
		for (List <String> list : records1) {
			count1+=Integer.valueOf (list.get(3));
		}
		for (List <String> list : records2) {
			count2+=Integer.valueOf (list.get(3));
		}
		return count1>count2;
	}
}
