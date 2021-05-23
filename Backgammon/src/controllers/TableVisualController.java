package controllers;

import javafx.scene.shape.Circle;
import tablecomponents.Checker;
import tablecomponents.Triangle;


public class TableVisualController {
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
            yCenterChecker += offset;
        }

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
        Triangle oldTriangle = checker.getCurrentTriangle();
        cadre = (oldTriangle.getIndex() - 1) / 6 + 1;
        newTriangle.getGroupCheckers().getChildren().add(checker.getShapeChecker());
        newTriangle.setNumberCheckers(newTriangle.getNumberCheckers() + 1);

        checker.getShapeChecker().setCenterY(yCenterChecker);
        checker.getShapeChecker().setCenterX(xCenterChecker);
        oldTriangle.setNumberCheckers(checker.getCurrentTriangle().getNumberCheckers() - 1);
        oldTriangle.getGroupCheckers().getChildren().remove(checker.getShapeChecker());
        checker.setCurrentTriangle(newTriangle);
        reconfigureCheckersPositioning(cadre, oldTriangle);
    }
}
