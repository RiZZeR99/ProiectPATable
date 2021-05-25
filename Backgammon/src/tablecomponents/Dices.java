package tablecomponents;

/*
 * X:720.0  Width:  220.0   Y:   50.0   Height:   900.0
 * the area where the dices are thrown
 */

import controllers.GameController;
import javafx.scene.Group;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Dices {
    private static final double length = 60;
    private static final Rectangle square1 = new Rectangle(length, length);
    private static final Rectangle square2 = new Rectangle(length, length);
    //private static final Rectangle containerDices = new Rectangle(720, 50, 220, 900);
    private static final StackPane dice1 = new StackPane();
    private static final StackPane dice2 = new StackPane();
    private static int value1;
    private static int value2;
    private static final Group dices = new Group();

    static public void initDices() {
        dices.getChildren().clear();
        dice1.getChildren().clear();
        dice2.getChildren().clear();

        Text valueDice1 = new Text(String.valueOf(0));
        Text valueDice2 = new Text(String.valueOf(0));
        valueDice1.setFont(Font.font("Comic Sans", 40));
        valueDice2.setFont(Font.font("Comic Sans", 40));
        valueDice1.setFill(Color.WHITE);
        valueDice2.setFill(Color.WHITE);

        dice1.getChildren().addAll(square1, valueDice1);
        dice2.getChildren().addAll(square2, valueDice2);

        square1.setFill(Color.RED);
        square2.setFill(Color.RED);
        dices.getChildren().addAll(dice1, dice2);
    }

    static public Group getDices() {
        return dices;
    }

    public static int getValue1() {
        return value1;
    }

    public static int getValue2() {
        return value2;
    }

    static public void rollDices() {
        value1 = (int) (Math.random() * 5) + 1;
        value2 = (int) (Math.random() * 5) + 1;

        ((Text) dice1.getChildren().get(1)).setText(String.valueOf(value1));
        ((Text) dice2.getChildren().get(1)).setText(String.valueOf(value2));

        dice1.setLayoutX(700 + Math.random() * 200);
        dice1.setLayoutY(150 + Math.random() * 300);
        dice2.setLayoutX(700 + Math.random() * 200);
        dice2.setLayoutY(450 + Math.random() * 200);

        GameController.setValueDice1(value1);
        GameController.setValueDice2(value2);

        GameController.setSumAllMoves(value1 + value2);
        if (value1 != value2)
            GameController.setCountAvailableMoves(2);
        else {
            GameController.setCountAvailableMoves(4);
            GameController.setDoubleDices(true);
        }
    }
}
