package codingcompetition2019;

import java.io.IOException;

public class runner {

	public static void main(String[] args)
	{
		String target = "src/main/resources/test.csv";
		CodingCompetitionCSVParser parser = new CodingCompetitionCSVParser(target);
		try {
			System.out.println(parser.parseAsString(false).toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		

	}

}
