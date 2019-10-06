package codingcompetition2019;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CodingCompetitionCSVParser 
{
	
	
	private String fileName;
	
	public CodingCompetitionCSVParser(String fileName)
	{
		this.fileName = fileName;
	}
	
	
	/**
	 * 
	 * @param data parsed data in String format
	 * @return parsed data in DisasterDescription format
	 * @throws IOException
	 */
	public List<DisasterDescription> parseAsDisaster(List<List<String>> data) throws IOException
	{
		ArrayList<DisasterDescription> parsedEntrys = new ArrayList<DisasterDescription>();
		for(List<String> line : data)
		{
			DisasterDescription dd = new DisasterDescription(line.get(0),line.get(1),line.get(2),Integer.parseInt(line.get(3)));
			parsedEntrys.add(dd);
		}
			
			
        
		return parsedEntrys;
	}
	
	
	/**
	 * 
	 * @param readHead a boolean value representing if the file head should be included in the output
	 * @return parsed data in DisasterDescription form
	 * @throws IOException
	 */
	public List<DisasterDescription> parseAsDisaster(boolean readHead) throws IOException
	{
		boolean rh = !readHead;
		ArrayList<DisasterDescription> parsedEntrys = new ArrayList<DisasterDescription>();
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		String data = "";
		while ((data = br.readLine()) != null) {
			if(rh)
			{
				rh = false;
				continue;
			}
			String[] dataArray = data.split(",");
			for(String s : dataArray)
			{
				if(s == null)
				{
					s = "null";
				}
			}
			DisasterDescription dd = new DisasterDescription(dataArray[0],dataArray[1],dataArray[2],Integer.parseInt(dataArray[3]));
			parsedEntrys.add(dd);
		}
		br.close();
		
		
		return parsedEntrys;
	}
	
	/**
	 * 
	 * @param readHead a boolean value representing if the file head should be included in the output
	 * @return parsed data in String format
	 * @throws IOException
	 */
	public List<List<String>> parseAsString(boolean readHead) throws IOException
	{
		boolean rh = !readHead;
		List<List<String>> parsedEntrys = new ArrayList<List<String>>();
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		String data = "";
		while ((data = br.readLine()) != null) {
			if(rh)
			{
				rh = false;
				continue;
			}
			String[] dataArray = data.split(",");
			parsedEntrys.add(Arrays.asList(dataArray));
		}
		br.close();
		
		
		return parsedEntrys;
	}
	
	
	public List<List<String>> parseAsStringByCountry(boolean readHead, String targetCountry) throws IOException
	{
		boolean rh = !readHead;
		List<List<String>> parsedEntrys = new ArrayList<List<String>>();
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		String data = "";
		while ((data = br.readLine()) != null) {
			if(rh)
			{
				rh = false;
				continue;
			}
			String[] dataArray = data.split(",");
			if(dataArray[0].equals(targetCountry))
				parsedEntrys.add(Arrays.asList(dataArray));
		}
		br.close();
		
		
		return parsedEntrys;
	}
	
	
	public List<List<String>> parseAsStringByCountryCode(boolean readHead, String targetCode) throws IOException
	{
		boolean rh = !readHead;
		List<List<String>> parsedEntrys = new ArrayList<List<String>>();
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		String data = "";
		while ((data = br.readLine()) != null) {
			if(rh)
			{
				rh = false;
				continue;
			}
			String[] dataArray = data.split(",");
			if(dataArray[1].equals(targetCode))
				parsedEntrys.add(Arrays.asList(dataArray));
		}
		br.close();
		
		
		return parsedEntrys;
	}
	
	
	

}
