package codingcompetition2019;

import java.util.List;

public class DisasterDescription {
	private String year;
	private String disaster;
	private int inc;
	private List<List<String>> records;
	
	
	public DisasterDescription(List<List<String>> records) {
		this.records = records;
		String year = "";
		int inc = 0;
		for(int i = 0; i < records.size();i++) {
			//System.out.println(records.get(i).get(0)+ " "+ i);
				String[] ar = records.get(i).get(0).split(",");
				if(ar.length  == 4) {
				if(Integer.parseInt(ar[3]) > inc){
					inc = Integer.parseInt(ar[3]);
					year = ar[2];
				}
				}
		}
		this.inc = inc;
		this.year = year;
	}

	public String getYear() {
		return year;
	}

	public void setYearByCategory(String category) {
		String year = "";
		int inc = 0;
		for(int i = 0; i < records.size();i++) {
			//System.out.println(records.get(i).get(0)+ " "+ i);
				String[] ar = records.get(i).get(0).split(",");
				if(ar.length  == 4) {
				if(Integer.parseInt(ar[3]) > inc && ar[0].equals(category)){
					inc = Integer.parseInt(ar[3]);
					year = ar[2];
				}
				}
		}
		this.year = year;
	}

	public void setDisasterByYear(String year) {
		String disaster = "";
		int inc = 0;
		for(int i = 0; i < records.size();i++) {
			//System.out.println(records.get(i).get(0)+ " "+ i);
				String[] ar = records.get(i).get(0).split(",");
				if(!ar[0].equals("All natural disasters")) {
				if(Integer.parseInt(ar[3]) > inc && ar[2].equals(year)){
					inc = Integer.parseInt(ar[3]);
					disaster = ar[0];
				}
				}
				
		}
		this.inc = inc;	
		this.disaster = disaster;
	}

	public String getCategory() {
		return disaster;
	}

	public int getReportedIncidentsNum() {
		return inc;
	}

	public void setIncByCategory(String category) {
		int inc = 0;
		for(int i = 0; i < records.size();i++) {
				String[] ar = records.get(i).get(0).split(",");
				if(!ar[0].equals("All natural disasters")) {
				if(Integer.parseInt(ar[3]) > inc && ar[0].equals(category)){
					inc = Integer.parseInt(ar[3]);
				}
				}
				
		}
		this.inc = inc;	
	}

	public void addUpIncedentsByCategory(String category) {
		int inc = 0;
		for(int i = 0; i < records.size();i++) {
				String[] ar = records.get(i).get(0).split(",");
				if(!ar[0].equals("All natural disasters")) {
				if(ar[0].equals(category)){
					inc += Integer.parseInt(ar[3]);
				}
				}
				
		}
		this.inc = inc;			
	}
	
}
