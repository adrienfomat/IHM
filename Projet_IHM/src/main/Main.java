package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.TextMergedView;

public class Main extends Application {

    private static final String MERGE_TITLE = "TEXT_MERGE";
    private static final int MERGE_WIDTH = 800;
    private static final int MERGE_HEIGHT = 600;

    @Override
    public void start(Stage primaryStage) {
        Scene MainSceneView = new Scene(new TextMergedView()); // Création de la vue
        new control.TextMergedController((TextMergedView) MainSceneView.getRoot()); // Création du contrôleur a passer à la vue
        primaryStage.setTitle(MERGE_TITLE);
        primaryStage.setScene(MainSceneView);
        primaryStage.setWidth(MERGE_WIDTH);
        primaryStage.setHeight(MERGE_HEIGHT);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}