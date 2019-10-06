package codingcompetition2019;

public class DisasterDescription {


	
	public String entity;
	public String code;
	public String year;
	public int numQuakes;
	
	
	public DisasterDescription() 
	{
	}
	
	
	
	
	
	
	public DisasterDescription(String entity, String code, String year, int numQuakes) {
		super();
		this.entity = entity;
		this.code = code;
		this.year = year;
		this.numQuakes = numQuakes;
	}






	public String getYear()
	{
		return year;
	}
	
	
	public int getReportedIncidentsNum()
	{
		return numQuakes;
	}
	
	
	
	public String getCategory()
	{
		return entity;
	}












	public void setEntity(String entity) {
		this.entity = entity;
	}






	public String getCode() {
		return code;
	}






	public void setCode(String code) {
		this.code = code;
	}






	public int getNumQuakes() {
		return numQuakes;
	}






	public void setNumQuakes(int numQuakes) {
		this.numQuakes = numQuakes;
	}






	public void setYear(String year) {
		this.year = year;
	}
	
	
	
}
