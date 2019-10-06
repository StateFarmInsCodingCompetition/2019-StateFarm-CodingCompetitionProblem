package codingcompetition2019;

public class DisasterDescription {
	// TODO finish this class
	private int amtOfIncidents;
	private String year="";
	private String category;
	
	public DisasterDescription(String year, int amtOfIncidents, String category) {
		this.year=year;
		this.amtOfIncidents=amtOfIncidents;
		this.category=category;
	}
	
	public DisasterDescription(int amtOfIncidents, String category) {
		this.amtOfIncidents=amtOfIncidents;
		this.category=category;
	}
	
	//getYear
	String getYear() {
		return year;
	}
	
	
	//getAmtIncidents
	int getReportedIncidentsNum() {
		return amtOfIncidents;
	}
	
	//getCAtegory
	String getCategory() {
		return category;
	}
}
