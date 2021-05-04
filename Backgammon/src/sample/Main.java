package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

        //primaryStage.setFullScreen(true);
        //primaryStage.setFullScreenExitHint("lmao apasa q mic");
        //primaryStage.setFullScreenExitKeyCombination(KeyCodeCombination.valueOf("q"));

        Parent ROOT = FXMLLoader.load(getClass().getResource("sample.fxml"));

//        primaryStage.setScene(currentScene(root);
        primaryStage.setScene(new Scene(ROOT));
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
        Line line = new Line();
        line.setStartX(100);
        line.setStartY(100);
        line.setEndX(1000);
        line.setEndY(100);
        line.setStrokeWidth(10);//grosimea
        line.setStroke(Color.LIMEGREEN);
        line.setOpacity(0.25);
        line.setRotate(90);

        //adaugare dreptunghi si eventual alte forme geometrice
        Rectangle rect = new Rectangle();
        rect.setX(333);
        rect.setY(500);
        rect.setHeight(200);
        rect.setWidth(600);
        rect.setArcWidth(30);
        rect.setFill(null);//sa nu am nici o culoare in forma
        rect.setStrokeWidth(5);
        rect.setStroke(Color.BLUE);

        //triunghi folosim clasa Polygon
        //generalizare de a crea o forma
        Polygon triunghi = new Polygon();
        triunghi.getPoints().setAll(200.0,300.0
                                    ,500.0,400.0
                                    ,800.0,800.0);
        triunghi.setFill(null);
        triunghi.setStrokeWidth(3);
        triunghi.setStroke(Color.BLACK);

        //cerc
        Circle circle = new Circle( );
        circle.setCenterX(640);
        circle.setCenterY(490);
        circle.setRadius(150);
        circle.setFill(null);
        circle.setStroke(Color.YELLOW);
        circle.setStrokeWidth(15);

        //adaugare de imagini
        Image vsAI= new Image("images/vs_AI.jpg");
        ImageView vsAIView = new ImageView(vsAI);//view pentru a putea afisa/vedea imaginea pe ecran
        vsAIView.setX(0);
        vsAIView.setY(0);
        //redimensionare imagine
        vsAIView.setFitHeight(100);
        vsAIView.setFitWidth(200);

        //adaugare elemente la root
        //elementele is de fapt noduri frunza
        root.getChildren().add(welcome);
        root.getChildren().add(line);
        root.getChildren().add(rect);
        root.getChildren().add(triunghi);
        root.getChildren().add(circle);
        root.getChildren().add(vsAIView);
        return scene;
    }
}
