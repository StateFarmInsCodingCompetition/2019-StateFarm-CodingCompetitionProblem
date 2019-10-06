package codingcompetition2019;

import java.io.IOException;
import java.nio.file.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CodingCompCSVUtil {
    private Stream<List<String>> readCSVStream(String fileName) throws IOException {
        Path file = Paths.get(fileName);
        return Files.lines(file).map(line -> {
            String[] lineContents = line.split(",");
            return Arrays.asList(lineContents);
        });
    }

    public List<List<String>> readCSVFileByCountry(String fileName, String countryName) throws IOException {
        return readCSVStream(fileName).filter(
                line -> countryName.equals(line.get(0))
        ).collect(Collectors.toList());
    }

    public List<List<String>> readCSVFileWithHeaders(String fileName) throws IOException {
        return readCSVStream(fileName).collect(Collectors.toList());
    }

    public List<List<String>> readCSVFileWithoutHeaders(String fileName) throws IOException {
        return readCSVStream(fileName).skip(1).collect(Collectors.toList());
    }

    private DisasterDescription getMostImpactfulDisaster(Stream<DisasterDescription> ddStream) {
        return ddStream.max(
                Comparator.comparingInt(
                        DisasterDescription::getReportedIncidentsNum
                )
        ).orElseThrow(() -> new IllegalArgumentException("records must not be empty"));
    }

    public DisasterDescription getMostImpactfulYear(List<List<String>> records) {
        Stream<DisasterDescription> dds = records.stream()
                .map(DisasterDescription::new);
        return getMostImpactfulDisaster(dds);
    }

    public DisasterDescription getMostImpactfulYearByCategory(String category, List<List<String>> records) {
        Stream<DisasterDescription> ddsByCategory = records.stream()
                .map(DisasterDescription::new)
                .filter(dd -> dd.getCategory().equals(category));
        return getMostImpactfulDisaster(ddsByCategory);
    }

    public DisasterDescription getMostImpactfulDisasterByYear(String year, List<List<String>> records) {
        Stream<DisasterDescription> ddsByYear = records.stream()
                .map(DisasterDescription::new)
                .filter(dd -> !dd.getCategory().equals("All natural disasters"))
                .filter(dd -> dd.getYear().equals(year));
        return getMostImpactfulDisaster(ddsByYear);
    }

    public DisasterDescription getTotalReportedIncidentsByCategory(String category, List<List<String>> records) {
        int totalReportedIncidents = records.stream()
                .map(DisasterDescription::new)
                .filter(dd -> dd.getCategory().equals(category))
                .mapToInt(DisasterDescription::getReportedIncidentsNum)
                .sum();
        return new DisasterDescription(category, "", "", totalReportedIncidents);
    }

	/**
	 * This method will return the count if the number of incident falls within the provided range.
	 * To simplify the problem, we assume:
	 * 	+ A value of -1 is provided if the max range is NOT applicable.
	 *  + A min value can be provided, without providing a max value (which then has to be -1 like indicated above).
	 *  + If a max value is provided, then a max value is also needed.
	 */
	public int countImpactfulYearsWithReportedIncidentsWithinRange(List<List<String>> records, int min, int max) {
		return (int) records.stream()
				.map(DisasterDescription::new)
				.filter(disaster -> {
					int incidents = disaster.getReportedIncidentsNum();
					return (min <= incidents) && (max == -1 || incidents <= max);
                }).count();
	}

	private int countReportedIncidents(List<List<String>> records) {
	    return records.stream()
                .map(DisasterDescription::new)
                .mapToInt(DisasterDescription::getReportedIncidentsNum)
                .sum();
    }
	
	public boolean firstRecordsHaveMoreReportedIndicents(List<List<String>> records1, List<List<String>> records2) {
	    int incidents1 = countReportedIncidents(records1);
		int incidents2 = countReportedIncidents(records2);
		return incidents1 > incidents2;
	}
}
