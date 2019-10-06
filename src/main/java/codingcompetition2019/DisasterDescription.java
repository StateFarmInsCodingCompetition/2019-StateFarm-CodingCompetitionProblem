package codingcompetition2019;

public class DisasterDescription {
	// TODO finish this class
    private String year;
    private String category;
    private int incidentNumber;
    
    public DisasterDescription()
    {
        this.year = "";
        this.category = "";
        this.incidentNumber = 0;
    }

    public String getYear()
    {
        return this.year;
    }
    
    public String getCategory()
    {
        return this.category;
    }
    
    public int getReportedIncidentsNum()
    {
        return this.incidentNumber;
    }
    
    public void setYear(String inYear)
    {
        this.year = inYear;
    }
    
    public void setCategory(String inCategory)
    {
        this.category = inCategory;
    }
    
    public void setReportedIncidentsNum(int inIncidentNum)
    {
        this.incidentNumber = inIncidentNum;
    }
}
