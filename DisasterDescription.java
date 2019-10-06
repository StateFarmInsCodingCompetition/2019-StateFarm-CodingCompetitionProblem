package codingcompetition2019;

public class DisasterDescription {
	// TODO finish this class
	private String year;
	private String category;
	private int IncedentNum = 0;
	
	public DisasterDescription(String year, String cat, int num) {
		this.year = year;
		this.IncedentNum = num;
		this.category = cat;
	}
	
	public String getCategory() {
	
		return this.category;
		
	}
	public int getReportedIncidentsNum() {
		
		return this.IncedentNum;
	}
	
	public String getYear () {
		return this.year;
	}
}
