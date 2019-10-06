package codingcompetition2019;



/**
 * Class that serves as a description for natural disasters, describing years,
 * type of natural disaster as well as number of incidents. 
 * 
 * @author Angel Aguayo, Andrew Raftovich
 *
 */
public class DisasterDescription {
	// TODO finish this class
	private int amtOfIncidents=0;
	private String year="";
	private String category="";
	
	/**
	 * Constructor for DisasterDescription
	 * @param year year of a given natural diaster
	 * @param amtOfIncidents number of incidents for a given natural disaster
	 * @param category type of natural disaster
	 */
	public DisasterDescription(String year, int amtOfIncidents, String category) {
		this.year=year;
		this.amtOfIncidents=amtOfIncidents;
		this.category=category;
	}
	
	

	/**
	 * Constructor for DisasterDescription
	 * @param amtOfIncidents number of incidents for a given natural disaster
	 * @param category type of natural disaster
	 */
	public DisasterDescription(int amtOfIncidents, String category) {
		this.amtOfIncidents=amtOfIncidents;
		this.category=category;
	}
	
	

	/**
	 * Constructor for DisasterDescription
	 * @param year year of a given natural diaster
	 * @param amtOfIncidents number of incidents for a given natural disaster
	 */
	public DisasterDescription(String year, int amtOfIncidents) {
		this.year=year;
		this.amtOfIncidents=amtOfIncidents;
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
