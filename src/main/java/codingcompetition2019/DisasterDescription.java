package codingcompetition2019;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class DisasterDescription {
	int highest = 0;
	String highestYr = "";
	String category = "";
	
	public DisasterDescription(List<List<String>> records) {
		this.highest = 0;
		this.highestYr = "";
		for (int i = 0; i < records.size(); i++) {
			if ((Integer.parseInt(records.get(i).get(3))) > highest) {
				highest = Integer.parseInt(records.get(i).get(3));
				highestYr = records.get(i).get(2);
			}
		}
	}
	
	public DisasterDescription(String cat, List<List<String>> records) {
		this.highest = 0;
		this.highestYr = "";
		
		List<List<String>> newRecords = records;
		
		for (int i = 0; i < newRecords.size(); i++){
			if(newRecords.get(i).get(0).contains(cat)) {
				continue;
			}
			else {
				newRecords.remove(i);
				i--;
			}
		}
		
		for (int i = 0; i < newRecords.size(); i++) {
			if ((Integer.parseInt(newRecords.get(i).get(3))) > highest) {
				highest = Integer.parseInt(newRecords.get(i).get(3));
				highestYr = newRecords.get(i).get(2);
			}
		}
	}
	
	public DisasterDescription(int no, String year, List<List<String>> records) {
		this.highest = 0;
		this.highestYr = "";
		
		List<List<String>> newRecords = records;
		
		for (int i = 0; i < newRecords.size(); i++){
			if(newRecords.get(i).get(0).contains("All natural disasters")){
				newRecords.remove(i);
				i--;
			}
			
			else if(newRecords.get(i).get(2).contains(year)) {
				continue;
			}
			else {
				newRecords.remove(i);
				i--;
			}
		}
		
		for (int i = 0; i < newRecords.size(); i++) {
			if ((Integer.parseInt(newRecords.get(i).get(3))) > highest) {
				highest = Integer.parseInt(newRecords.get(i).get(3));
				category = newRecords.get(i).get(0);
			}
		}
	}
	
	public String getYear() {
		return highestYr;
	}
	
	public String getCategory() {
		return category;
	}
	
	public int getReportedIncidentsNum() {
		return highest;	
	}
}