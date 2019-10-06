package codingcompetition2019.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class UiMain extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("2019 StateFarm Coding Competition | Andrew Zeng and Luke Kim");
        primaryStage.setResizable(false);

        primaryStage.setScene(new Scene(new DataSummarizer(), 1280, 720));

        primaryStage.show();
    }
}
