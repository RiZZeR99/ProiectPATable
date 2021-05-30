package scenes;

import controllers.ScenesController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.Main;

import java.awt.*;


public class LanguageScene {
    private Group root;
    private Scene languageScene;

    public LanguageScene(Group root, Stage stage) {
        this.root = root;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        languageScene = new Scene(this.root, screenSize.getWidth(), screenSize.getHeight(), Color.SKYBLUE);
        Text info = new Text("Choose a language");
        VBox container = new VBox();
        Button roBtn = new Button("Română");
        Button enBtn = new Button("English");
        Button frBtn = new Button("Français");
        info.setFont(Font.font("Comic Sans", 50));
        info.setX(screenSize.getWidth()/2 - info.getLayoutBounds().getWidth() / 2);
        info.setY(100);

        roBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Main.changeLanguage("ro", "RO");
                ScenesController.createScenes(stage);
                ScenesController.setNewScene(ScenesFactory.getMenuScene().getScene());
            }
        });

        enBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Main.changeLanguage("en", "US");
                ScenesController.createScenes(stage);
                ScenesController.setNewScene(ScenesFactory.getMenuScene().getScene());
            }
        });

        frBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Main.changeLanguage("fr", "FR");
                ScenesController.createScenes(stage);
                ScenesController.setNewScene(ScenesFactory.getMenuScene().getScene());
            }
        });

        container.getChildren().addAll(roBtn, enBtn, frBtn);
        container.setAlignment(Pos.CENTER);
        container.setLayoutY(screenSize.getHeight() / 3);
        container.setLayoutX(screenSize.getWidth() / 2 - 50);
        container.setSpacing(50);
        root.getChildren().addAll(info,container);
    }

    public Scene getScene() {
        return languageScene;
    }
}
