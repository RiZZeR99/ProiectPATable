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
    private static final double length = 60;//dimension of a dice
    private static final Rectangle square1 = new Rectangle(length, length);
    private static final Rectangle square2 = new Rectangle(length, length);
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

    private static boolean collision(double ax, double ay, double bx, double by, double cx, double cy, double dx, double dy) {
        return ((ax > dx) || (bx < cx) || (ay > dy) || (by < cy));
    }

    static public void rollDices(double left, double up, double lengthAllowedGenerate) {
        value1 = (int) (Math.random() * 6) + 1;
        value2 = (int) (Math.random() * 6) + 1;

        ((Text) dice1.getChildren().get(1)).setText(String.valueOf(value1));
        ((Text) dice2.getChildren().get(1)).setText(String.valueOf(value2));

        dice1.setLayoutX(left + Math.random() * lengthAllowedGenerate);
        dice1.setLayoutY(up + Math.random() * 2 * lengthAllowedGenerate);
        do {
            dice2.setLayoutX(left + Math.random() * lengthAllowedGenerate);
            dice2.setLayoutY(up * 3 + Math.random() * lengthAllowedGenerate);

        }
        while (!collision(dice1.getLayoutX(), dice1.getLayoutY(),
                dice1.getLayoutX() + length, dice1.getLayoutY() + length,
                dice2.getLayoutX(), dice2.getLayoutY(),
                dice2.getLayoutX() + length, dice2.getLayoutY() + length));

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
