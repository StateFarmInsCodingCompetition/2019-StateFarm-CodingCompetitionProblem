package codingcompetition2019.functionality;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import codingcompetition2019.CodingCompCSVUtil;
import codingcompetition2019.CodingCompetitionCSVParser;
import codingcompetition2019.DisasterDescription;

@Service
public class DisasterService
{
	CodingCompCSVUtil util = new CodingCompCSVUtil();
	
	public DisasterService()
	{
	}
	
	/**
	 * 
	 * @param fileName a valid filename
	 * @return parsed file data as a nested String list
	 */
	public List<List<String>> getFileAsString(String fileName)
	{
		try {
			return util.readCSVFileWithoutHeaders(fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * @param fileName a valid fileName
	 * @return parsed data as a list of DisasterDescription objects
	 */
	public List<DisasterDescription> getFileAsDisaster(String fileName)
	{
		try {
			return util.readCSVFileAsDisaster(fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * 
	 * @param fileName a valid fileName
	 * @param countryName a valid countryName
	 * @return parsed data with respect to countryName
	 */
	public List<List<String>> getFileAsStringByCountry(String fileName, String countryName)
	{
		try {
			return util.readCSVFileByCountry(fileName,countryName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * @param fileName a valid fileName
	 * @param countryCode a valid countryCode
	 * @return parsed data with respect to countryCode
	 */
	public List<List<String>> getFileAsStringByCountryCode(String fileName, String countryCode)
	{
		try {
			return util.readCSVFileByCountryCode(fileName,countryCode);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * This method is meant to speed up visualization efforts to assist with the competition timeframe
	 * 
	 * 
	 * @param data parsed data in String format
	 * @return parsed data converted to DisasterDescription objects
	 * @throws IOException
	 */
	public List<DisasterDescription> asDisasterDescriptions(List<List<String>> data) throws IOException
	{
		CodingCompetitionCSVParser parser = new CodingCompetitionCSVParser(null);
		return parser.parseAsDisaster(data);
	}

}
