package scenes;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LoadingScene {
    private Scene loadingScene;
    private Group root;


    LoadingScene(Group root, Stage stage) {
        this.root = root;
        loadingScene = new Scene(this.root, 1660, 1000, Color.SKYBLUE);

        Text textLoading = new Text("Va rugam sa asteptati pana cand un alt jucator se conecteaza");
        textLoading.setFont(Font.font("Comic Sans", 35));
        textLoading.setFill(Color.ORANGERED);

        textLoading.setX(this.loadingScene.getWidth() / 2 - textLoading.getLayoutBounds().getWidth() / 2);
        textLoading.setY(this.loadingScene.getHeight() / 2 - textLoading.getLayoutBounds().getHeight() / 2);

        root.getChildren().add(textLoading);

    }

    public Scene getScene() {
        /**
         * TODO activate thread to listen to communication i guess
         */

        return loadingScene;
    }
}
