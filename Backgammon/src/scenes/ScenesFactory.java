package scenes;

import javafx.scene.Group;
import javafx.stage.Stage;

public class ScenesFactory {
    private static LoadingScene loadingScene;
    private static MenuScene menuScene;
    private static GameScene gameScene;
    private static WinnerScene winnerScene;
    private static LanguageScene languageScene;

    public static LanguageScene getLanguageScene(){
        return languageScene;
    }

    public static void setLanguageScene(Group root,Stage stage){
        if(languageScene==null){
            languageScene=new LanguageScene(root,stage);
        }
    }

    public static WinnerScene getWinnerScene() {
        return winnerScene;
    }

    public static void setWinnerScene(Group root, Stage stage) {
        if (winnerScene == null) {
            winnerScene = new WinnerScene(root, stage);
        }
    }

    public static LoadingScene getLoadingScene() {
        return loadingScene;
    }

    public static void setLoadingScene(Group root, Stage stage) {
        if (loadingScene == null) {
            loadingScene = new LoadingScene(root, stage);
        }
    }

    public static MenuScene getMenuScene() {
        return menuScene;
    }

    public static void setMenuScene(Group root, Stage stage) {
        if (menuScene == null) {
            menuScene = new MenuScene(root, stage);
        }
    }

    public static GameScene getGameScene() {
        return gameScene;
    }

    public static void setGameScene(Group root, Stage stage) {
        if (gameScene == null) {
            gameScene = new GameScene(root, stage);
        } else if (gameScene.getEnableReset()) {
            gameScene = new GameScene(root, stage);
        }
    }
}
