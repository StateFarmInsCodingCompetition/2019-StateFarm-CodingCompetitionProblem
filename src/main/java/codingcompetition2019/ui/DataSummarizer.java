package codingcompetition2019.ui;

import codingcompetition2019.CodingCompCSVUtil;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DataSummarizer extends GridPane {

    private CodingCompCSVUtil util;
    private List<List<String>> records;

    private ComboBox csvSelector;
    private Button go;

    private ObservableList<String> options;

    // natural disasters
    private PieChart naturalDisastersBreakdown;

    private ObservableList<String> countries;
    private ComboBox countrySelector;

    private BarChart<String, Number> barChart;
    final CategoryAxis xAxis;
    final NumberAxis yAxis;

    public DataSummarizer() {
        util = new CodingCompCSVUtil();
        setPadding(new Insets(5, 5, 5, 5));

        options = FXCollections.observableArrayList(
                "natural-disasters-by-type.csv",
                "significant-earthquakes.csv",
                "significant-volcanic-eruptions.csv"
        );

        countries = FXCollections.observableArrayList();

        csvSelector = new ComboBox(options);
        csvSelector.setValue(options.get(0));

        add(csvSelector, 0, 0);

        go = new Button("Go");
        go.setOnAction(this::updateContent);

        add(go, 1, 0);


        naturalDisastersBreakdown = new PieChart();
        naturalDisastersBreakdown.setTitle("Disaster Breakdown");
        naturalDisastersBreakdown.setVisible(false);

        add(naturalDisastersBreakdown, 0, 1, 2, 2);

        countrySelector = new ComboBox();
        countrySelector.setVisible(false);

        add(countrySelector, 0, 1);

        xAxis = new CategoryAxis();
        yAxis = new NumberAxis();

        xAxis.setLabel("Country");
        yAxis.setLabel("Incidents");

        barChart = new BarChart<>(xAxis, yAxis);
        barChart.setVisible(false);

        add(barChart, 0, 2, 2, 2);


        updateContent(new ActionEvent());
    }

    public void updateContent(ActionEvent event) {
        String selectedCsv = (String) csvSelector.getValue();

        if (selectedCsv == null) {
            throw new RuntimeException("Must select valid csv value");
        }

        try {
            records = util.readCSVFileWithoutHeaders("src/main/resources/" + selectedCsv);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (selectedCsv.equalsIgnoreCase(options.get(0))) {
            countrySelector.setVisible(false);
            barChart.setVisible(false);
            naturalDisastersBreakdown.setVisible(true);
            ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

            util.getIncidentsByEntityMap(records).entrySet().stream()
                    .filter(e -> !e.getKey().equalsIgnoreCase(CodingCompCSVUtil.ALL_NATURAL_DISASTERS))
                    .forEach(e -> pieChartData.add(new PieChart.Data(e.getKey(), e.getValue())));

            naturalDisastersBreakdown.setData(pieChartData);
        } else if (selectedCsv.equalsIgnoreCase(options.get(1))) {
            naturalDisastersBreakdown.setVisible(false);
            barChart.setVisible(true);
            countrySelector.setVisible(true);

            countries = FXCollections.observableArrayList();

            Map<String, Integer> data = util.getIncidentsByEntityMap(records);

            data.keySet().forEach(k -> countries.add(k));

            countrySelector.setItems(countries);
            countrySelector.setValue(countries.get(0));

            barChart.setTitle("Earthquake Breakdown");

            countrySelector.valueProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue observable, String oldValue, String newValue) {
                    List<List<String>> sorted = new LinkedList<>(new LinkedList<>());

                    try {
                        sorted = util.readCSVFileByCountry("src/main/resources/" + selectedCsv, (String) countrySelector.getValue());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    Map<String, Integer> data = util.getIncidentsByYearMap(sorted);

                    System.out.println(data.entrySet().size());

                    XYChart.Series series = new XYChart.Series<>();
                    series.setName((String) countrySelector.getValue());

                    data.entrySet().stream()
                            .forEach(e -> {
                                series.getData().add(new XYChart.Data<>(e.getKey(), e.getValue()));

                            });
                    barChart.getData().add(series);
                }
            });
        } else if (selectedCsv.equalsIgnoreCase(options.get(2))) {
            naturalDisastersBreakdown.setVisible(false);
            barChart.setVisible(true);
            countrySelector.setVisible(true);

            countries = FXCollections.observableArrayList();

            Map<String, Integer> data = util.getIncidentsByEntityMap(records);

            data.keySet().forEach(k -> countries.add(k));

            countrySelector.setItems(countries);
            countrySelector.setValue(countries.get(0));

            barChart.setTitle("Volcanic Eruptions Breakdown");

            countrySelector.valueProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue observable, String oldValue, String newValue) {
                    List<List<String>> sorted = new LinkedList<>(new LinkedList<>());

                    try {
                        sorted = util.readCSVFileByCountry("src/main/resources/" + selectedCsv, (String) countrySelector.getValue());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    Map<String, Integer> data = util.getIncidentsByYearMap(sorted);

                    System.out.println(data.entrySet().size());

                    XYChart.Series series = new XYChart.Series<>();
                    series.setName((String) countrySelector.getValue());

                    data.entrySet().stream()
                            .forEach(e -> {
                                series.getData().add(new XYChart.Data<>(e.getKey(), e.getValue()));

                            });
                    barChart.getData().add(series);
                }
            });
        }
    }
}
