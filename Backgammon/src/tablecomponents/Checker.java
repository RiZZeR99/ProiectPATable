package tablecomponents;

import controllers.GameController;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Checker {
    private Circle shapeChecker;
    private double radius;
    private Triangle currentTriangle;
    private int colorValue;
    private boolean inJail=false;

    public Checker(double raza, int colorValue, double strokeWidth, Triangle triangle) {
        this.currentTriangle = triangle;
        this.radius = raza;
        this.colorValue = colorValue;
        shapeChecker = new Circle(this.radius);
        shapeChecker.setStroke(Color.RED);
        shapeChecker.setStrokeWidth(strokeWidth);

        if (colorValue == 0) {
            shapeChecker.setFill(Color.WHITE);
        } else
            shapeChecker.setFill(Color.BLACK);
        Checker checker = this;
        shapeChecker.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                System.out.println("Valori vechi:");
                System.out.println("Coordonate checker:" + getShapeChecker().getCenterX() + "   " + getShapeChecker().getCenterY());
                System.out.println("Layout checker: "+getShapeChecker().getLayoutX()+"   "+getShapeChecker().getLayoutY()+"\n");
                GameController.makeMoveChecker(checker, checker.currentTriangle);
            }
        });
    }

    public boolean isInJail() {
        return inJail;
    }

    public void setInJail(boolean inJail) {
        this.inJail = inJail;
    }

    public Triangle getCurrentTriangle() {
        return currentTriangle;
    }

    public void setCurrentTriangle(Triangle currentTriangle) {
        this.currentTriangle = currentTriangle;
    }

    public int getColorValue() {
        return colorValue;
    }

    public void setColorValue(int colorValue) {
        this.colorValue = colorValue;
    }


    public Circle getShapeChecker() {
        return shapeChecker;
    }
}
