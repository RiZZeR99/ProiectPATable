package scenes;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import controllers.ScenesController;

import static java.lang.System.exit;


public class MenuScene {
    private Scene menuScene;
    private Group root;

    MenuScene(Group root, Stage stage) {
        this.root = root;
        menuScene = new Scene(this.root, 1660, 1000, Color.SKYBLUE);
        Group buttonsGroup = new Group();
        Text welcome = new Text("Bun venit la jocul de table");
        welcome.setFont(Font.font("Comic Sans", 50));
        welcome.setFill(Color.ORANGERED);

        welcome.setX(this.menuScene.getWidth() / 2 - welcome.getLayoutBounds().getWidth() / 2);
        welcome.setY(100);

        Button buttonNetworkPlay = new Button("Joaca in retea");
        Button buttonExit = new Button("Exit");
        Button playOffline = new Button("Joaca singur");


        playOffline.setMinSize(80, 30);
        playOffline.setMaxSize(200, 30);
        playOffline.setLayoutX(this.menuScene.getWidth() / 2 - playOffline.getLayoutBounds().getWidth() / 2);
        playOffline.setLayoutY(400);

        buttonExit.setMinSize(80, 30);
        buttonExit.setMaxSize(80, 30);
        buttonExit.setLayoutX(this.menuScene.getWidth() / 2 - buttonExit.getLayoutBounds().getWidth() / 2);
        buttonExit.setLayoutY(600);

        buttonNetworkPlay.setMinSize(80, 30);
        buttonNetworkPlay.setMaxSize(200, 30);
        buttonNetworkPlay.setLayoutX(this.menuScene.getWidth() / 2 - buttonNetworkPlay.getLayoutBounds().getWidth() / 2);
        buttonNetworkPlay.setLayoutY(500);

        buttonNetworkPlay.setWrapText(true);
        buttonExit.setWrapText(true);
        playOffline.setWrapText(true);

        playOffline.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ScenesController.setNewScene(ScenesFactory.getGameScene().getScene());
            }
        });

        buttonExit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                exit(0);
            }
        });
        buttonNetworkPlay.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ScenesController.setNewScene(ScenesFactory.getLoadingScene().getScene());
            }
        });

        buttonsGroup.getChildren().addAll(buttonNetworkPlay, playOffline, buttonExit);
        root.getChildren().addAll(welcome, buttonsGroup);
    }

    public Scene getScene() {
        return menuScene;
    }
}
