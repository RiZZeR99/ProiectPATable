package controllers;

import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import scenes.GameScene;
import tablecomponents.Checker;
import tablecomponents.Triangle;


public class TableVisualController {
    public static void addJailChecker(GameScene gameScene, Checker checker, VBox jailGame) {
        VBox jail = jailGame;
        Triangle oldTriangle = checker.getCurrentTriangle();
        oldTriangle.setNumberCheckers(checker.getCurrentTriangle().getNumberCheckers() - 1);
        oldTriangle.getGroupCheckers().getChildren().remove(checker.getShapeChecker());
        reconfigureCheckersPositioning((oldTriangle.getIndex() - 1) / 6 + 1, oldTriangle);
        checker.setCurrentTriangle(null);
        jail.getChildren().add(checker.getShapeChecker());
    }

    public static void reconfigureCheckersPositioning(int cadre, Triangle triangle) {
        //reconfigure the checkers from a triangle, i mean theirs positions
        double xCenterCheckers = triangle.getXRight() - (Math.abs(triangle.getXRight() - triangle.getXLeft())) / 2;
        double yCenterChecker = triangle.getYLeft();
        double offset = 0;
        if (cadre == 1 || cadre == 2) {
            yCenterChecker -= 25;
            offset = -50;
        } else {
            offset = 50;
            yCenterChecker += 25;
        }
        for (int i = 1; i <= triangle.getNumberCheckers(); i++) {
            ((Circle) triangle.getGroupCheckers().getChildren().get(i)).setCenterX(xCenterCheckers);
            ((Circle) triangle.getGroupCheckers().getChildren().get(i)).setCenterY(yCenterChecker);
            // triangle.getGroupCheckers().getChildren().get(i).toFront(); ---this is so WRONG
            yCenterChecker += offset;
        }

    }

    public static void resetCheckerColor(Checker checker) {
        checker.getShapeChecker().setStroke(Color.RED);
    }

    public static void setColorSelectedChecker(Checker checker) {
        checker.getShapeChecker().setStroke(Color.ORANGE);
    }

    public static void addCheckerToTriangle(Checker checker, Triangle newTriangle) {
        newTriangle.resetColor();
        int cadre = (newTriangle.getIndex() - 1) / 6 + 1;
        double xCenterChecker = newTriangle.getXRight() - (Math.abs(newTriangle.getXRight() - newTriangle.getXLeft())) / 2;
        double yCenterChecker = 0;
        switch (cadre) {
            case 1:
            case 2: {
                yCenterChecker = newTriangle.getYRight() - 25 - (newTriangle.getNumberCheckers()) * (checker.getShapeChecker().getRadius() * 2);
                break;
            }
            case 3:
            case 4: {
                yCenterChecker = newTriangle.getYRight() + 25 + (newTriangle.getNumberCheckers()) * (checker.getShapeChecker().getRadius() * 2);
                break;
            }
        }
        if (!checker.isInJail()) {
            checker.getShapeChecker().toFront();
            Triangle oldTriangle = checker.getCurrentTriangle();
            cadre = (oldTriangle.getIndex() - 1) / 6 + 1;

            newTriangle.getGroupCheckers().getChildren().add(checker.getShapeChecker());
            newTriangle.setNumberCheckers(newTriangle.getNumberCheckers() + 1);
            newTriangle.getListOfCheckers().add(checker);
            newTriangle.setColorCheckerType(checker.getColorValue());

            checker.getShapeChecker().setCenterY(yCenterChecker);//mai am nevoie de astea??
            checker.getShapeChecker().setCenterX(xCenterChecker);

            oldTriangle.setNumberCheckers(checker.getCurrentTriangle().getNumberCheckers() - 1);
            oldTriangle.getGroupCheckers().getChildren().remove(checker.getShapeChecker());
            oldTriangle.getListOfCheckers().remove(checker);

            checker.setCurrentTriangle(newTriangle);
            reconfigureCheckersPositioning(cadre, oldTriangle);
        } else {
            checker.getShapeChecker().setLayoutX(0);
            checker.getShapeChecker().setLayoutY(0);
            //i guess the layout is affected when i put the checker in the jail (VBox)
            checker.setInJail(false);
            newTriangle.getListOfCheckers().add(checker);
            newTriangle.getGroupCheckers().getChildren().add(checker.getShapeChecker());
            newTriangle.setNumberCheckers(newTriangle.getNumberCheckers() + 1);
            newTriangle.setColorCheckerType(checker.getColorValue());

            checker.getShapeChecker().setCenterY(yCenterChecker);//mai am nevoie de astea??
            checker.getShapeChecker().setCenterX(xCenterChecker);

            checker.setCurrentTriangle(newTriangle);
            reconfigureCheckersPositioning(cadre,newTriangle);
        }
    }
}
