package sample;

import controllers.ScenesController;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCodeCombination;
import javafx.stage.Stage;
import scenes.ScenesFactory;

public class Main extends Application {

    /**
     * link util : https://www.youtube.com/watch?v=9XJicRt_FaI
     *
     * @param args
     */

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        ScenesController.createController(primaryStage);
        Group rootMenu = new Group();//functioneaza ca un layer. root este ca un nod ce trebuie sa fie atribuit une scene noi. Fiecare scena are un root
        Group rootLoading = new Group();
        Group rootGame = new Group();
        Group rootWinner = new Group();


        primaryStage.setTitle("Backgammon");
        Image icon = new Image("/images/icon_BackGammon.ico");
        primaryStage.getIcons().add(icon);

        //primaryStage.setWidth(1280);
        //primaryStage.setHeight(980);
        //primaryStage.setResizable(false);//daca vreau sa nu poata faca resize

        //primaryStage.setX(50);
        //primaryStage.setY(50);
        //cele 2 spun unde sa plaseze imaginea

        // primaryStage.setFullScreen(true);
        primaryStage.setFullScreenExitHint("lmao apasa q mic");
        primaryStage.setFullScreenExitKeyCombination(KeyCodeCombination.valueOf("q"));



        ScenesFactory.setMenuScene(rootMenu, primaryStage);
        ScenesFactory.setGameScene(rootGame, primaryStage);
        ScenesFactory.setLoadingScene(rootLoading, primaryStage);
        ScenesFactory.setWinnerScene(rootWinner,primaryStage);

        ScenesController.setNewScene(ScenesFactory.getMenuScene().getScene());
        primaryStage.show();//arata ce avem pana acum in stage
    }
}
