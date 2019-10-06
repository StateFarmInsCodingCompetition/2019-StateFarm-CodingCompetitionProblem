package codingcompetition2019;

import java.io.IOException;

public class runner {

	public static void main(String[] args) throws IOException
	{
		String target = "src/main/resources/natural-disasters-by-type.csv";
		CodingCompCSVUtil util = new CodingCompCSVUtil();
		CodingCompetitionCSVParser parser = new CodingCompetitionCSVParser(target);
		
		
		System.out.println(util.getMostImpactfulDisasterByYear("2005",(util.readCSVFileWithoutHeaders(target))).getCategory());
		

	}

}
