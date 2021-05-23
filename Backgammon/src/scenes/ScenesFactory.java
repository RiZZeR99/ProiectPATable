package scenes;

import javafx.scene.Group;
import javafx.stage.Stage;

public class ScenesFactory {
    private static LoadingScene loadingScene;
    private static MenuScene menuScene;
    private static GameScene gameScene;

    public static LoadingScene getLoadingScene() {
        return loadingScene;
    }

    public static void setLoadingScene(Group root, Stage stage) {
        if (loadingScene == null) {
            loadingScene=new LoadingScene(root,stage);
        }
    }

    public static MenuScene getMenuScene() {
        return menuScene;
    }

    public static void setMenuScene(Group root,Stage stage) {
        if (menuScene == null) {
            menuScene=new MenuScene(root,stage);
        }
    }

    public static GameScene getGameScene() {
        return gameScene;
    }

    public static void setGameScene(Group root,Stage stage) {
        if (gameScene == null) {
            gameScene=new GameScene(root,stage);
        }
    }
}
