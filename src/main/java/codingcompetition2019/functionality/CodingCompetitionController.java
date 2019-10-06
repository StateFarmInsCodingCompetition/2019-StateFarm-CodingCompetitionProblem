package codingcompetition2019.functionality;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CodingCompetitionController
{
	
	@GetMapping("/")
	public String isUp()
	{
		return "This is an application for the 2019 State Farm Coding Competition!";
	}
	
	
	
	

}
