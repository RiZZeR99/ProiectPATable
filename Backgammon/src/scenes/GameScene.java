package scenes;

import controllers.GameController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import tablecomponents.Dices;
import tablecomponents.TableGame;


public class GameScene {
    private TableGame tableGame;
    private Scene sceneGame;
    private Group root;

    public TableGame getTableGame() {
        return tableGame;
    }

    public Scene getSceneGame() {
        return sceneGame;
    }

    public Group getRoot() {
        return root;
    }

    GameScene(Group root, Stage stage) {
        this.root = root;
        sceneGame = new Scene(this.root, 1660, 1000, Color.SKYBLUE);
        tableGame = new TableGame(1600, 900, this.sceneGame);
        Dices.initDices();
        tableGame.drawTable();
        Button rollDices = new Button("Roll dices!");
        rollDices.setMinSize(100,80);
        rollDices.setLayoutY(800);
        rollDices.setLayoutX(800);
        rollDices.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Dices.rollDices();
                root.getChildren().remove(Dices.getDices());
                root.getChildren().add(Dices.getDices());
            }
        });
        root.getChildren().add(tableGame.getNode());
        Dices.getDices().toFront();
        rollDices.toFront();
        root.getChildren().add(rollDices);
        /**
         * configuration for each player
         * to draw the checkers
         * alb ===>>>> 0
         * negru ===>>>> 1z
         */
    }

    public Scene getScene() {
        GameController.startGame(this);
        return sceneGame;
    }
}