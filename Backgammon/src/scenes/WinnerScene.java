package scenes;

import controllers.GameController;
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


public class WinnerScene {
    Scene winnerTextScene;
    Group root;
    Text message;

    WinnerScene(Group root, Stage stage){
        this.root=root;
        VBox container = new VBox();
        message=new Text("default text");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        winnerTextScene=new Scene(this.root, screenSize.getWidth(), screenSize.getHeight(), Color.SKYBLUE);
        message.setFont(Font.font("Comic Sans", 35));
        message.setFill(Color.ORANGERED);
        Button menuBtn = new Button(Main.contentButtons.getString("menuBack"));
        menuBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Group newRoot = new Group();
                ScenesFactory.setGameScene(newRoot,stage);
                ScenesController.setNewScene(ScenesFactory.getMenuScene().getScene());
            }
        });
        container.getChildren().addAll(message,menuBtn);
        container.setAlignment(Pos.CENTER);
        container.setLayoutY(screenSize.getHeight()/3);
        container.setLayoutX(screenSize.getWidth()/2-200);
        container.setSpacing(150);
        root.getChildren().addAll(container);
    }
    public void setTextMessage(String message){
        this.message.setText(message);
    }
    public Scene getScene(){
        return winnerTextScene;
    }
}
