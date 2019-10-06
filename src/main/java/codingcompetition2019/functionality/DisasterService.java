package codingcompetition2019.functionality;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import codingcompetition2019.CodingCompCSVUtil;
import codingcompetition2019.DisasterDescription;

@Service
public class DisasterService
{
	CodingCompCSVUtil util = new CodingCompCSVUtil();
	
	public DisasterService()
	{
	}
	
	
	public List<List<String>> getFileAsString(String fileName)
	{
		try {
			return util.readCSVFileWithoutHeaders(fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<DisasterDescription> getFileAsDisaster(String fileName)
	{
		try {
			return util.readCSVFileAsDisaster(fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	public List<List<String>> getFileAsStringByCountry(String fileName, String countryName)
	{
		try {
			return util.readCSVFileByCountry(fileName,countryName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<List<String>> getFileAsStringByCountryCode(String fileName, String countryCode)
	{
		try {
			return util.readCSVFileByCountryCode(fileName,countryCode);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	

}
