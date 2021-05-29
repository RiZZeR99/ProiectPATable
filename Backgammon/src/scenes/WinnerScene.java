package scenes;

import controllers.GameController;
import controllers.ScenesController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import tablecomponents.Dices;

import java.awt.*;


public class WinnerScene {
    Scene winnerTextScene;
    Group root;
    Text message;

    WinnerScene(Group root, Stage stage){
        this.root=root;
        message=new Text();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        winnerTextScene=new Scene(this.root, screenSize.getWidth(), screenSize.getHeight(), Color.SKYBLUE);
        message.setFont(Font.font("Comic Sans", 35));
        message.setFill(Color.ORANGERED);
        message.setX(this.winnerTextScene.getWidth() / 2 - message.getLayoutBounds().getWidth() / 2);
        message.setY(this.winnerTextScene.getHeight() / 2 - message.getLayoutBounds().getHeight() / 2);
        Button menuBtn = new Button("Revenire meniu");
        menuBtn.setMinSize(60, 40);
        message.setLayoutY(screenSize.getWidth()/2);
        message.setLayoutX(screenSize.getWidth() / 2 +200);
        menuBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ScenesController.setNewScene(ScenesFactory.getMenuScene().getScene());
            }
        });

        root.getChildren().addAll(message,menuBtn);
    }
    public void setTextMessage(String message){
        this.message.setText(message);
    }
    public Scene getScene(){
        return winnerTextScene;
    }
}
