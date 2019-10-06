package codingcompetition2019;

public class DisasterDescription {
    private String category;
    private String code;
    private String year;
    private int significant;
    
    public DisasterDescription(String category, String code, String year, int significant) {
        this.category = category;
        this.code = code;
        this.year = year;
        this.significant = significant;
    }
    
    public void setCategory(String category) {
        this.category = category;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
    
    public void setYear(String year) {
        this.year = year;
    }
    
    public void setSignificant(int significant) {
        this.significant = significant;
    }
    
    public String getCategory() {
        return category;
    }
    
    public String getCode() {
        return code;
    }
    
    public String getYear() {
        return year;
    }
    
    public int getReportedIncidentsNum() {
        return significant;
    }
    // TODO finish this class
}
