package sample;

import controllers.ScenesController;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import scenes.ScenesFactory;

import java.util.Locale;
import java.util.ResourceBundle;

public class Main extends Application {

    /**
     * link util : https://www.youtube.com/watch?v=9XJicRt_FaI
     *
     * @param args
     */

    public static void main(String[] args) {
        launch(args);
    }

    public static String buttonsTexts;
    public static String gameTexts;
    public static String screensTexts;
    public static Locale locale;
    public static ResourceBundle contentButtons;
    public static ResourceBundle contentGame;
    public static ResourceBundle contentScreens;

    public static void changeLanguage(String language, String country) {
        locale = new Locale(language, country);
        contentButtons = ResourceBundle.getBundle(buttonsTexts, locale);
        contentGame = ResourceBundle.getBundle(gameTexts, locale);
        contentScreens = ResourceBundle.getBundle(screensTexts, locale);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        buttonsTexts = "resources.btnText.ButtonsText";
        gameTexts = "resources.statusGame.StatusGame";
        screensTexts = "resources.screenMessages.ScreenMessages";
        locale = Locale.getDefault();
        contentButtons = ResourceBundle.getBundle(buttonsTexts, locale);
        contentGame = ResourceBundle.getBundle(gameTexts, locale);
        contentScreens = ResourceBundle.getBundle(screensTexts, locale);
        System.out.println(contentScreens.getString("welcome"));
        Group languageRoot = new Group();

        ScenesController.createController(primaryStage);

        primaryStage.setTitle("Backgammon");
        Image icon = new Image("/images/icon_BackGammon.ico");
        primaryStage.getIcons().add(icon);

        ScenesFactory.setLanguageScene(languageRoot, primaryStage);
        ScenesController.setNewScene(ScenesFactory.getLanguageScene().getScene());
        primaryStage.show();
    }
}
