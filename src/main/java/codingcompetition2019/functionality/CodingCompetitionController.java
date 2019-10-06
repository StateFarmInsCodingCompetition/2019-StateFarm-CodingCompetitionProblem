package codingcompetition2019.functionality;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import codingcompetition2019.DisasterDescription;

@Controller
@RequestMapping()
public class CodingCompetitionController {
	@Autowired
	DisasterService disasterService;

	
	
	/**
	 * 
	 * @return A simple string to tell if the application is operational
	 */
	@GetMapping("/")
	public String isUp() {
		return "This is an application for the 2019 State Farm Coding Competition!" + "";
	}

	/**
	 * 
	 * @param fileName A filename in the form foo.csv
	 * @return Json data of the parsed file
	 */
	@GetMapping("/getFile")
	@ResponseBody
	public List<List<String>> getFileData(@RequestParam("fileName") String fileName) {
		return disasterService.getFileAsString("src/main/resources/" + fileName);
	}

	
	/**
	 * 
	 * @param fileName A filename in the form foo.csv
	 * @param countryName An optional country name to filter data
	 * @param countryCode An optional country code to filter data
	 * @return filtered data in Json form
	 */
	@GetMapping("/getFileByCountry")
	@ResponseBody
	public List<List<String>> getFileData(@RequestParam(name = "fileName") String fileName,
			@RequestParam(name = "countryName", required = false) String countryName,
			@RequestParam(name = "countryCode", required = false) String countryCode) {
		if (countryName != null && !countryName.equals(""))
			return disasterService.getFileAsStringByCountry("src/main/resources/" + fileName, countryName);
		if (countryCode != null && !countryCode.equals(""))
			return disasterService.getFileAsStringByCountryCode("src/main/resources/" + fileName, countryCode);

		return disasterService.getFileAsString("src/main/resources/" + fileName);

	}

	
	/**
	 * 
	 * @param model An autowired parameter for visualization
	 * @param fileName A filename in the form foo.csv
	 * @param entity An optional entity name to filter data
	 * @param code An optional code name to filter data
	 * @return A web display table of parsed data
	 * @throws IOException
	 */
	@RequestMapping(value = "/visualizeData", method = RequestMethod.GET)
	public String showTableWithValues(Model model, @RequestParam(name = "fileName") String fileName,
	@RequestParam(name = "entity", required = false) String entity,
	@RequestParam(name = "code", required = false) String code) throws IOException{
		List<DisasterDescription> list;
		
		if (entity != null && !entity.equals("null"))
		{
			list = disasterService.asDisasterDescriptions(disasterService.getFileAsStringByCountry("src/main/resources/" + fileName, entity));			
		}else if (code != null && !code.equals("null"))
		{
			list = disasterService.asDisasterDescriptions(disasterService.getFileAsStringByCountryCode("src/main/resources/" + fileName, code));
		}else
		{
			list = disasterService.getFileAsDisaster("src/main/resources/" + fileName);
		}

		
		//List<DisasterDescription> list = disasterService.getFileAsDisaster("src/main/resources/" + fileName);

		model.addAttribute("list", list);

		return "visual";
	}
	

}
