package scenes;

import controllers.GameController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.Main;
import tablecomponents.Dices;
import tablecomponents.TableGame;

import java.awt.*;


public class GameScene {
    private TableGame tableGame;
    private Scene sceneGame;
    private Group root;
    public TableGame getTableGame() {
        return tableGame;
    }
    private boolean enableReset=false;

    public Group getRoot() {
        return root;
    }

    GameScene(Group root, Stage stage) {
        this.root = root;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        sceneGame = new Scene(this.root, screenSize.getWidth(), screenSize.getHeight(), Color.SKYBLUE);
        tableGame = new TableGame((int) screenSize.getWidth() - 60, (int) screenSize.getHeight() - 100, this.sceneGame);
        VBox jail = new VBox();
        Text statusGame = new Text("Status Joc aici");
        Button rollDices = new Button(Main.contentButtons.getString("dices"));


        statusGame.setFont(Font.font("Comic Sans", this.sceneGame.getHeight()/20));
        statusGame.setFill(Color.BLACK);
        statusGame.setX(this.sceneGame.getWidth() / 12);
        statusGame.setY(statusGame.getFont().getSize()-10);

        jail.setSpacing(5);
        jail.setLayoutX(screenSize.getWidth() / 2 - 200);
        jail.setLayoutY(screenSize.getHeight() * 3 / 4);

        Dices.initDices();

        tableGame.drawTable();

        rollDices.setMinSize(60, 40);
        rollDices.setLayoutY(screenSize.getHeight() * 3 / 4);
        rollDices.setLayoutX(screenSize.getWidth() / 2 -150);
        rollDices.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (!GameController.getDicesThrown()) {
                    Dices.rollDices(tableGame.middleSeparator.getX()-25,tableGame.middleSeparator.getY(),tableGame.middleSeparator.getWidth()+50);
                    root.getChildren().remove(Dices.getDices());
                    root.getChildren().add(Dices.getDices());
                    GameController.setDicesThrown(true);
                }
            }
        });
        root.getChildren().add(tableGame.getNode());
        Dices.getDices().toFront();
        rollDices.toFront();
        root.getChildren().addAll(rollDices, jail, statusGame);
    }

    public Scene getScene() {
        GameController.startGame(this);
        return sceneGame;
    }
    public void setEnableReset(){
        enableReset=true;
    }
    public boolean getEnableReset(){
        return enableReset;
    }
}
