package controllers;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import scenes.ScenesFactory;

public class ScenesController {
    private Stage stage;
    static ScenesController controller;

    private ScenesController(Stage stage) {
        this.stage = stage;
    }

    public static ScenesController createController(Stage stage) {
        if (controller == null)
            controller = new ScenesController(stage);
        return controller;
    }
    public static void createScenes(Stage primaryStage){
        Group rootMenu = new Group();
        Group rootLoading = new Group();
        Group rootGame = new Group();
        Group rootWinner = new Group();

        ScenesFactory.setMenuScene(rootMenu, primaryStage);
        ScenesFactory.setGameScene(rootGame, primaryStage);
        ScenesFactory.setLoadingScene(rootLoading, primaryStage);
        ScenesFactory.setWinnerScene(rootWinner, primaryStage);
    }
    public static void setNewScene(Scene scene){
        controller.stage.setScene(scene);
    }

}
