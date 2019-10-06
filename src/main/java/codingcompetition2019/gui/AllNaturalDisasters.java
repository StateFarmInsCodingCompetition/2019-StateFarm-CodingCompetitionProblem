package codingcompetition2019.gui;

import codingcompetition2019.CodingCompCSVUtil;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class AllNaturalDisasters extends JFrame {

    private String naturalDisasterByTypeFile = "src/main/resources/natural-disasters-by-type.csv";
    private CodingCompCSVUtil util;
    private List<List<String>> records;
    private ArrayList<String> disasters;
    private ArrayList<String> years;

    private JLabel lblImpactedYear;
    private JLabel lblTotalIncidents;
    private JLabel lblDisasterName;
    private JLabel lblReportedIncidentsByYear;

    public AllNaturalDisasters(){
        super("All Natural Disasters");
        util = new CodingCompCSVUtil();
        try{
            records = util.readCSVFileWithoutHeaders(naturalDisasterByTypeFile);
            getDisasterCategoriesAndYear();
        }
        catch (Exception ex) {

        }

        createGUIComponents();

        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    private void getDisasterCategoriesAndYear(){
        Set<String> categories = new HashSet<String>();
        Set<String> yearList = new TreeSet<String>();

        for(List<String> record : records){
            String category = record.get(0).trim();
            String year = record.get(2).trim();
            if (!categories.contains(category))
                categories.add(category);

            if (!yearList.contains(year))
                yearList.add(year);
        }
        disasters = new ArrayList<String>();
        years = new ArrayList<String>();

        disasters.addAll(categories);
        years.addAll(yearList);
    }

    private void createGUIComponents(){
        Container pane = this.getContentPane();
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));

        pane.add(Box.createVerticalGlue());
        pane.add(getSelectionMenuByCategory());
        pane.add(Box.createVerticalStrut(20));
        pane.add(getDisasterInfoPanelByCategory());
        pane.add(Box.createVerticalStrut(40));
        pane.add(getSelectionMenuByYear());
        pane.add(Box.createVerticalStrut(20));
        pane.add(getDisasterInfoPanelByYear());
    }

    private JPanel getDisasterInfoPanelByCategory(){
        JPanel disasterInfo = new JPanel();
        disasterInfo.setBorder(BorderFactory.createTitledBorder("Disaster Info By Category"));
        disasterInfo.setLayout(new GridLayout(2, 2));

        disasterInfo.add(new JLabel("Most impacted year: "));
        lblImpactedYear = new JLabel(util.getMostImpactfulYearByCategory(disasters.get(0), records).getYear() + "");
        disasterInfo.add(lblImpactedYear);
        disasterInfo.add(new JLabel("Total reported incidents: "));
        lblTotalIncidents = new JLabel(util.getTotalReportedIncidentsByCategory(disasters.get(0), records).getReportedIncidentsNum() + "");
        disasterInfo.add(lblTotalIncidents);


        return disasterInfo;
    }

    private JPanel getSelectionMenuByCategory(){
        JPanel selection = new JPanel();
        selection.setLayout(new GridLayout(1, 2));
        selection.setBorder(new EmptyBorder(10,10,10,10));
        selection.add(new JLabel("Select Disaster Type"));

        final JComboBox cbDisaster = new JComboBox(disasters.toArray());
        selection.add(cbDisaster);
        cbDisaster.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateCategoryInfo((String)cbDisaster.getSelectedItem());
            }
        });

        return selection;
    }

    private JPanel getSelectionMenuByYear(){
        JPanel selection = new JPanel();
        selection.setLayout(new GridLayout(1, 2));
        selection.setBorder(new EmptyBorder(10,10,10,10));
        selection.add(new JLabel("Select Year"));

        final JComboBox cbYear = new JComboBox(years.toArray());
        selection.add(cbYear);
        cbYear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateYearInfo((String)cbYear.getSelectedItem());
            }
        });

        return selection;
    }

    private JPanel getDisasterInfoPanelByYear(){
        JPanel disasterInfo = new JPanel();
        disasterInfo.setBorder(BorderFactory.createTitledBorder("Disaster Info By Year"));
        disasterInfo.setLayout(new GridLayout(2, 2));

        disasterInfo.add(new JLabel("Most impactful disaster: "));
        lblDisasterName = new JLabel(util.getMostImpactfulDisasterByYear(years.get(0), records).getYear() + "");
        disasterInfo.add(lblDisasterName);

        disasterInfo.add(new JLabel("Total reported incidents: "));
        lblReportedIncidentsByYear = new JLabel(util.getTotalReportedIncidentsByCategory(disasters.get(0), records).getReportedIncidentsNum() + "");
        disasterInfo.add(lblReportedIncidentsByYear);


        return disasterInfo;
    }

    private void updateCategoryInfo(String category){
        lblTotalIncidents.setText(util.getTotalReportedIncidentsByCategory(category, records).getReportedIncidentsNum() + "");
        lblImpactedYear.setText(util.getMostImpactfulYearByCategory(category, records).getYear() + "");
    }

    private void updateYearInfo(String year){
        lblDisasterName.setText(util.getMostImpactfulDisasterByYear(year, records).getCategory() + "");
        lblReportedIncidentsByYear.setText(util.getMostImpactfulDisasterByYear(year, records).getReportedIncidentsNum() + "");
    }


}
