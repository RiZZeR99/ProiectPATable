package tablecomponents;

import controllers.GameController;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;


public class Triangle {
    private double x1, y1, x2, y2, x3, y3;
    int index=-1, colorCheckerType = -1, numberCheckers = 0;
    private Polygon triangle;
    private Group groupCheckers;
    private boolean availableForChecker = false;

    public Triangle(double x1, double y1, double x2, double y2, double x3, double y3, int index) {
        //punctele am dat in urmatoarea ordine in BoardSide:
        /*
         * dreapta prima pereche
         * stanga a doua pereche
         * mijloc a treia pereche
         * */
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
        this.index = index;
        triangle = new Polygon(x1, y1, x2, y2, x3, y3);
        Triangle thisTriangle=this;
        triangle.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                System.out.println("sunt triunghiul cu index " + index);
                if(availableForChecker){
                    GameController.putChecker(thisTriangle);
                }
            }
        });
        this.groupCheckers = new Group();
        groupCheckers.getChildren().add(triangle);
        resetColor();
    }

    public int getIndex() {
        return index;
    }

    public int getColorCheckerType() {
        return colorCheckerType;
    }

    public int getNumberCheckers() {
        return numberCheckers;
    }

    public void setNumberCheckers(int newNumber){
        this.numberCheckers=newNumber;
        if(this.numberCheckers==0)
            this.colorCheckerType=-1;
    }

    public double getXLeft() {
        return x2;
    }

    public double getYLeft() {
        return y2;
    }

    public double getXMiddle() {
        return x1;
    }

    public double getYMiddle() {
        return y1;
    }

    public double getXRight() {
        return x1;
    }

    public double getYRight() {
        return y1;
    }

    public boolean isAvailableForChecker() {
        return availableForChecker;
    }

    public void setAvailableForChecker(boolean availableForChecker) {
        this.availableForChecker = availableForChecker;
    }

    public void resetColor() {
        this.triangle.setFill(index % 2 == 1 ? Color.TURQUOISE : Color.LIMEGREEN);
    }

    public Polygon getTriangleShape() {
        return this.triangle;
    }

    public Group getGroupCheckers() {
        return groupCheckers;
    }


}
