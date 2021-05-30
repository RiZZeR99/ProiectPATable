package scenes;

import java.awt.*;

import javafx.application.Platform;
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
import controllers.ScenesController;



public class MenuScene {
    private Scene menuScene;
    private Group root;

    MenuScene(Group root, Stage stage) {
        this.root = root;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        menuScene = new Scene(this.root, screenSize.getWidth(), screenSize.getHeight(), Color.SKYBLUE);
        VBox buttonsGroup = new VBox();
        Text welcome = new Text("Bun venit la jocul de table");
        welcome.setFont(Font.font("Comic Sans", 50));
        welcome.setFill(Color.ORANGERED);

        welcome.setX(this.menuScene.getWidth() / 2 - welcome.getLayoutBounds().getWidth() / 2);
        welcome.setY(100);

        Button buttonNetworkPlay = new Button("Joaca in retea");
        Button buttonExit = new Button("Exit");
        Button playOffline = new Button("Joaca singur");
        ;

        playOffline.setMinSize(80, 30);
        playOffline.setMaxSize(200, 30);

        buttonExit.setMinSize(80, 30);
        buttonExit.setMaxSize(80, 30);

        buttonNetworkPlay.setMinSize(80, 30);
        buttonNetworkPlay.setMaxSize(200, 30);

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
                Platform.exit();
                stage.close();
            }
        });
        buttonNetworkPlay.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ScenesController.setNewScene(ScenesFactory.getLoadingScene().getScene());
            }
        });
        buttonsGroup.getChildren().addAll(playOffline,buttonNetworkPlay, buttonExit);
        buttonsGroup.setAlignment(Pos.CENTER);
        buttonsGroup.setLayoutY(screenSize.getHeight()/3);
        buttonsGroup.setLayoutX(screenSize.getWidth()/2-50);
        buttonsGroup.setSpacing(50);
        root.getChildren().addAll(welcome, buttonsGroup);
    }

    public Scene getScene() {
        return menuScene;
    }
}
