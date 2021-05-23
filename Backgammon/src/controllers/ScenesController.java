package controllers;

import javafx.scene.Scene;
import javafx.stage.Stage;

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

    public static void setNewScene(Scene scene){
        controller.stage.setScene(scene);
    }

}
