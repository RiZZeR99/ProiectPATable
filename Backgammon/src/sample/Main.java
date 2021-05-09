package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {

    /**
     * link util : https://www.youtube.com/watch?v=9XJicRt_FaI
     * @param args
     */

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //Stage stage = new Stage();
        Group root = new Group();//functioneaza ca un layer. root este ca un nod ce trebuie sa fie atribuit une scene noi. Fiecare scena are un root
        //Scene currentScene = new Scene(root, Color.RED);

        primaryStage.setTitle("Backgammon");
        Image icon = new Image("/images/icon_BackGammon.ico");
        primaryStage.getIcons().add(icon);

        primaryStage.setWidth(1280);
        primaryStage.setHeight(980);
        //primaryStage.setResizable(false);//daca vreau sa nu poata faca resize

        //primaryStage.setX(50);
        //primaryStage.setY(50);
        //cele 2 spun unde sa plaseze imaginea

       // primaryStage.setFullScreen(true);
        primaryStage.setFullScreenExitHint("lmao apasa q mic");
        primaryStage.setFullScreenExitKeyCombination(KeyCodeCombination.valueOf("q"));



 //       Parent ROOT = FXMLLoader.load(getClass().getResource("sample.fxml"));

        primaryStage.setScene(currentScene(root));
        //primaryStage.setScene(new Scene(ROOT));
        primaryStage.show();//arata ce avem pana acum in stage
    }
    public Scene currentScene(Group root){
        Scene scene=new Scene(root,1280,980,Color.SKYBLUE);//stage se adjusteaza dupa scene
        //scene ii locul de desenat pentru continutul grafic si container pentru noduri

        //Adaugare de text
        Text welcome = new Text("Bun venit la jocul de table");
        welcome.setFont(Font.font("Comic Sans",50));
        welcome.setFill(Color.ORANGERED);

        welcome.setX(scene.getWidth()/2-welcome.getLayoutBounds().getWidth()/2);
        welcome.setY(100);
        //Adaugare linii punem de unde incepe si unde se termina


        Tabla tabla = new Tabla(600,1000, scene);
        tabla.deseneazaTabla();

        //adaugare elemente la root
        //elementele is de fapt noduri frunza
        root.getChildren().add(welcome);
        root.getChildren().add(tabla.getNod());
        return scene;
    }
}