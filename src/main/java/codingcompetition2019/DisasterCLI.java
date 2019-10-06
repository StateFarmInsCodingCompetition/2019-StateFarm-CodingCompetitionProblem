package codingcompetition2019;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
public class DisasterCLI {
    private static CodingCompCSVUtil util = new CodingCompCSVUtil();
    private static List<List<String>> records;
    public static void main(String[] args) {
        HashMap<Integer, String> directions = directions();
        System.out.println("Please enter the number of which method that you want to peruse.");
        System.out.println("0: getMostImpactfulYear");
        System.out.println("1: getMostImpactfulYearByCategory");
        System.out.println("2: getMostImpactfulDisasterByYear");
        System.out.println("3: getTotalReportedIncidentsByCategory");
        System.out.println("4: countImpactfulYearsWithReportedIncidentsWithinRange");
        Scanner scanner = new Scanner(System.in);
        String numMethod = scanner.next();
        int method = -1;
        try {
            method = Integer.parseInt(numMethod);
        } catch(Exception e) {

        }
        if (method == 0) {
            try {
                System.out.println(impactfulYear());
            } catch(IOException e) {
                e.printStackTrace();
            }
        } else if (method == 1) {
            System.out.println(directions.get(method));
            String category = scanner.next();
            try {
                System.out.println(categoryImpactfulYear(category));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (method == 2) {
            System.out.println(directions.get(method));
            String category = scanner.next();
            try {
                System.out.println(yearImpactfulDisaster(category));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (method == 3) {
            System.out.println(directions.get(method));
            String category = scanner.next();
            try {
                System.out.println(totalIncidents(category));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (method == 4){
            System.out.println(directions.get(method));
            String country = scanner.next();
            int min = scanner.nextInt();
            int max = scanner.nextInt();
            try {
                System.out.println(rangeIncidents(country, min, max));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Please rerun and enter proper input.");
        }


    }

    public static HashMap<Integer, String> directions() {
        HashMap<Integer, String> directions = new HashMap<Integer, String>();
        directions.put(1, "Please enter the category you want to look at.");
        directions.put(2, "Please enter the year you want to look at.");
        directions.put(3, "Please enter the category you want to look at.");
        directions.put(4, " First enter a country you want to look at. Then please enter two integer " +
                "values. One min and max value which represent your range. ");
        return directions;
    }

    public static String impactfulYear() throws IOException {
        records = util.readCSVFileWithoutHeaders("src/main/resources/natural-disasters-by-type.csv");
        return  util.getMostImpactfulYear(records).getYear();
    }

    public static String categoryImpactfulYear(String category) throws IOException {
        records = util.readCSVFileWithoutHeaders("src/main/resources/natural-disasters-by-type.csv");
        return  util.getMostImpactfulYearByCategory(category, records).getYear();
    }

    public static String yearImpactfulDisaster(String year) throws IOException {
        records = util.readCSVFileWithoutHeaders("src/main/resources/natural-disasters-by-type.csv");
        return  util.getMostImpactfulDisasterByYear(year, records).getCategory();
    }

    public static int totalIncidents(String category) throws IOException {
        records = util.readCSVFileWithoutHeaders("src/main/resources/natural-disasters-by-type.csv");
        return  util.getTotalReportedIncidentsByCategory(category, records).getReportedIncidentsNum();
    }
    public static int rangeIncidents(String country, int min, int max) throws IOException {
        List<List<String>> tempRecords = util.readCSVFileByCountry("src/main/resources/signifi" +
                        "cant-volcanic-eruptions.csv", country);
        return util.countImpactfulYearsWithReportedIncidentsWithinRange(tempRecords, min, max);
    }
}
