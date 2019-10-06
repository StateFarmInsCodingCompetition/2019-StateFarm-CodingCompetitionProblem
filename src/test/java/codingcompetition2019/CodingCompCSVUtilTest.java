package codingcompetition2019;

import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

public class CodingCompCSVUtilTest extends TestCase {
	private CodingCompCSVUtil util;
	
	private List<List<String>> records;
	
	private String naturalDisasterByTypeFile = "src/main/resources/natural-disasters-by-type.csv";
	
	private String significantEarthquakeFileNameName = "src/main/resources/significant-earthquakes.csv";
	
	private String significantVolcanicEruptionsFileName = "src/main/resources/significant-volcanic-eruptions.csv";

	@Before
	public void setUp() throws Exception {
		util = new CodingCompCSVUtil();
		records = util.readCSVFileWithoutHeaders(naturalDisasterByTypeFile);
	}	
	
	@Test
	public void testReadCSVFileWithHeadersByCountry() throws IOException {
		List<List<String>> tempRecords = util.readCSVFileByCountry(significantEarthquakeFileNameName, "United States");
		assertEquals(945, tempRecords.size());
		
		tempRecords = util.readCSVFileByCountry(significantVolcanicEruptionsFileName, "United States");
		assertEquals(343, tempRecords.size());
	}
	
	@Test
	public void testGetTheMostImpactfulYearInUSAByEarthquake() throws IOException {
		List<List<String>> earthquakeRecords = util.readCSVFileByCountry(significantEarthquakeFileNameName, "United States");
		assertEquals("2011", util.getMostImpactfulYear(earthquakeRecords).getYear());
	}
	
	@Test
	public void testReadCSVFileWithHeaders() throws IOException {
		assertEquals(829, util.readCSVFileWithHeaders(naturalDisasterByTypeFile).size());
	}
	
	@Test
	public void testReadCSVFileWithoutHeaders() throws IOException {
		assertEquals(828, util.readCSVFileWithoutHeaders(naturalDisasterByTypeFile).size());
	}
	
	@Test
	public void testGetMostImpactfulYear() {
		assertEquals("2005", util.getMostImpactfulYear(records).getYear());
	}
	
	@Test
	public void testGetMostImpactfulYearByCategory() {
		assertEquals("1990", util.getMostImpactfulYearByCategory("Earthquake", records).getYear());
	}
	
	@Test
	public void testGetMostImpactfulDisasterByYear() {
		DisasterDescription dd = util.getMostImpactfulDisasterByYear("2005", records);
		
		assertEquals("Flood", dd.getCategory());
		assertEquals(193, dd.getReportedIncidentsNum());
	}
	
	@Test
	public void testGetTotalReportedIncidentsByCategoryy() {
		assertEquals(1372, util.getTotalReportedIncidentsByCategory("Earthquake", records).getReportedIncidentsNum());
	}
	
	@Test
	public void testCountImpactfulYearsWithReportedIncidentsWithinRange() throws IOException {
		List<List<String>> tempRecords = util.readCSVFileByCountry(significantVolcanicEruptionsFileName, "United States");

		assertEquals(40, util.countImpactfulYearsWithReportedIncidentsWithinRange(tempRecords, 1, -1));
		assertEquals(4, util.countImpactfulYearsWithReportedIncidentsWithinRange(tempRecords, 2, 4));
	}
	
	@Test
	public void testFirstRecordsHaveMoreReportedIndicents() throws IOException {
		List<List<String>> tempRecords1 = util.readCSVFileByCountry(significantEarthquakeFileNameName, "United States");
		List<List<String>> tempRecords2 = util.readCSVFileByCountry(significantVolcanicEruptionsFileName, "United States");

		assertTrue(util.firstRecordsHaveMoreReportedIndicents(tempRecords1, tempRecords2));
		assertFalse(util.firstRecordsHaveMoreReportedIndicents(tempRecords2, tempRecords1));
	}
}
