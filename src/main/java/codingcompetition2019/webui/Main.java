package codingcompetition2019.webui;

import codingcompetition2019.CodingCompCSVUtil;
import codingcompetition2019.DisasterDescription;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static spark.Spark.*;
public class Main {
    private static final String naturalDisasterByTypeFile = "src/main/resources/natural-disasters-by-type.csv";
    private static final String significantEarthquakeFile = "src/main/resources/significant-earthquakes.csv";
    private static final String significantVolanicEruptionsFile = "src/main/resources/significant-volcanic-eruptions.csv";

    public static String loadJson(String fileName) throws IOException {
        Gson gson = new Gson();
        CodingCompCSVUtil util = new CodingCompCSVUtil();
        List<List<String>> records = util.readCSVFileWithoutHeaders(fileName);
        List<DisasterDescription> dds = records.stream().map(DisasterDescription::new).collect(Collectors.toList());
        return gson.toJson(dds);
    }

    public static void main(String[] args) throws IOException {
        String typeJson = loadJson(naturalDisasterByTypeFile);
        String earthquakeJson = loadJson(significantEarthquakeFile);
        String volcanoJson  = loadJson(significantVolanicEruptionsFile);


        System.out.println("Running on: http://localhost:4567");

        staticFiles.location("html");
        //staticFiles.externalLocation("/home/hsheth/data/projects/2019-StateFarm-CodingCompetitionProblem-Private/src/main/resources/html");
        get("/api/disasters_by_type", (req, res) -> {
            res.type("application/json");
            return typeJson;
        });

        get("/api/significant_earthquakes", (req, res) -> {
            res.type("application/json");
            return earthquakeJson;
        });

        get("/api/significant_volcanic_eruptions", (req, res) -> {
            res.type("application/json");
            return volcanoJson;
        });
    }

}
