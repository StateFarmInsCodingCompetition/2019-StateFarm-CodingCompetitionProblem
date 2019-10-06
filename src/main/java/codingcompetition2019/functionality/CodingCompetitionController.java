package codingcompetition2019.functionality;

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

	@GetMapping("/")
	public String isUp() {
		return "This is an application for the 2019 State Farm Coding Competition!";
	}

	@GetMapping("/visual")
	@ResponseBody
	public String dataVisual(Model model) {
		return "visual";
	}

	@PostMapping("/visual")
	public String dataVisualInput() {
		return "visual";
	}

	@GetMapping("/getFile")
	@ResponseBody
	public List<List<String>> getFileData(@RequestParam("fileName") String fileName) {
		return disasterService.getFileAsString("src/main/resources/" + fileName);
	}

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

	@RequestMapping(value = "/showTableWithValues", method = RequestMethod.GET)
	public String showTableWithValues(Model model, @RequestParam(name = "fileName") String fileName) {
		// list with Persons
		List<DisasterDescription> list = disasterService.getFileAsDisaster("src/main/resources/" + fileName);

		model.addAttribute("list", list);

		return "visual";
	}

}
