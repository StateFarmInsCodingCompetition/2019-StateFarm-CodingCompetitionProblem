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
		    DisasterDescription dd = new DisasterDescription(dataArray[0],dataArray[1],dataArray[2],dataArray[3]);
		    parsedEntrys.add(dd);
		}
		br.close();
        
        
		return parsedEntrys;
	}
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
	
	
	

}
