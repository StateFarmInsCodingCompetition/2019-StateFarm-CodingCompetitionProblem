package codingcompetition2019;

import java.util.Arrays;
import java.util.List;

/**
 * Stores information about a given disaster line item
 */
public class DisasterDescription {
	
	/** The category/country or disaster name */
	private String entity;
	
	/** The code associated with the country (if applicable) */
	private String code;
	
	/** The year of the line item */
	private String year;
	
	/** The number of incidents in that year */
	private int statistic;
	
	/**
	 * Creates a description object based on the values of a given CSV line
	 * @param data The values of a CSV line
	 */
	public DisasterDescription(String...data) {
		this(Arrays.asList(data));
	}

	/**
	 * Creates a description object based on the values of a given CSV line
	 * @param data The values of a CSV line
	 */
	public DisasterDescription(List<String> data) {
		if (data.size() < 4) {
			throw new IllegalArgumentException("Expected data to have at least 4 values");
		}
		
		entity = data.get(0);
		code = data.get(1);
		year = data.get(2);
		try {
			statistic = Integer.parseInt(data.get(3));
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("Invalid statistic number", e);
		}
	}
	
	/** Returns the country code (or '' if not applicable) */
	public String getCode() {
		return code;
	}
	
	/** Returns the category/country name */
	public String getCategory() {
		return entity;
	}
	
	/** Returns the country name or category name */
	public String getCountry() {
		return entity;
	}
	
	/** Returns the number of incidents reported */
	public int getReportedIncidentsNum() {
		return statistic;
	}
	
	/** Returns the year of the line item */
	public String getYear() {
		return year;
	}
	
	
	
}
