package tablecomponents;

import controllers.GameController;
import controllers.TableVisualController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TableGame extends Node {
    private final double radius = 25;
    private final int strokeWidth = 5;
    public final int heightTable;
    public final int lengthTable;
    public Rectangle tableArea;
    public Rectangle middleSeparator;
    private Scene scene;
    private Group nodTabla;
    private BoardSide leftSide;
    private BoardSide rightSide;
    private double lengthTriangle;
    private final double lengthMiddleSeparator = 120;
    private List<Triangle> listOfAllTriangles = new ArrayList<>();
    private VBox containerWhite;
    private VBox containerBlack;

    //0 pt alb 1 pt negru
    private void initCheckerDown() {
        double xValue = rightSide.getListTrianglesDownSide().get(0).getXRight();
        double yValue = rightSide.tableArea.getY() + rightSide.tableArea.getHeight() - radius;


        //rightdown ----checked
        rightSide.getListTrianglesDownSide().get(0).colorCheckerType = 1;//pt negru
        rightSide.getListTrianglesDownSide().get(0).numberCheckers = 2;
        for (int i = 0; i < 2; i++) {
            Checker checker = new Checker(radius, 1, strokeWidth, rightSide.getListTrianglesDownSide().get(0));
            checker.getShapeChecker().setCenterX(xValue - lengthTriangle / 2);
            checker.getShapeChecker().setCenterY(yValue);
            yValue -= 50;
            rightSide.getListTrianglesDownSide().get(0).getGroupCheckers().getChildren().add(checker.getShapeChecker());
            rightSide.getListTrianglesDownSide().get(0).getListOfCheckers().add(checker);
        }

        xValue = rightSide.getListTrianglesDownSide().get(5).getXRight();
        yValue = rightSide.tableArea.getY() + rightSide.tableArea.getHeight() - 25;
        rightSide.getListTrianglesDownSide().get(5).colorCheckerType = 0;//pt alb
        rightSide.getListTrianglesDownSide().get(5).numberCheckers = 5;
        for (int i = 0; i < 5; i++) {
            Checker checker = new Checker(radius, 0, strokeWidth, rightSide.getListTrianglesDownSide().get(5));
            checker.getShapeChecker().setCenterX(xValue - lengthTriangle / 2);
            checker.getShapeChecker().setCenterY(yValue);
            yValue -= 50;
            rightSide.getListTrianglesDownSide().get(5).getGroupCheckers().getChildren().add(checker.getShapeChecker());
            rightSide.getListTrianglesDownSide().get(5).getListOfCheckers().add(checker);
        }


        xValue = leftSide.getListTrianglesDownSide().get(1).getXRight();
        yValue = leftSide.tableArea.getY() + leftSide.tableArea.getHeight() - 25;
        //leftdown ----checked
        leftSide.getListTrianglesDownSide().get(1).numberCheckers = 3;
        leftSide.getListTrianglesDownSide().get(1).colorCheckerType = 0;
        for (int i = 0; i < 3; i++) {
            Checker checker = new Checker(radius, 0, strokeWidth, leftSide.getListTrianglesDownSide().get(1));
            checker.getShapeChecker().setCenterX(xValue - lengthTriangle / 2);
            checker.getShapeChecker().setCenterY(yValue);
            yValue -= 50;
            leftSide.getListTrianglesDownSide().get(1).getGroupCheckers().getChildren().add(checker.getShapeChecker());
            leftSide.getListTrianglesDownSide().get(1).getListOfCheckers().add(checker);
        }
        xValue = leftSide.getListTrianglesDownSide().get(5).getXRight();
        yValue = leftSide.tableArea.getY() + leftSide.tableArea.getHeight() - 25;
        leftSide.getListTrianglesDownSide().get(5).numberCheckers = 5;
        leftSide.getListTrianglesDownSide().get(5).colorCheckerType = 1;
        for (int i = 0; i < 5; i++) {
            Checker checker = new Checker(radius, 1, strokeWidth, leftSide.getListTrianglesDownSide().get(5));
            checker.getShapeChecker().setCenterX(xValue - lengthTriangle / 2);
            checker.getShapeChecker().setCenterY(yValue);
            yValue -= 50;
            leftSide.getListTrianglesDownSide().get(5).getGroupCheckers().getChildren().add(checker.getShapeChecker());
            leftSide.getListTrianglesDownSide().get(5).getListOfCheckers().add(checker);
        }

    }

    private void initCheckersUp() {
        double xValue = rightSide.getListTrianglesUpSide().get(0).getXRight();
        double yValue = rightSide.tableArea.getY() + 25;


        //rightup ----checked
        rightSide.getListTrianglesUpSide().get(0).colorCheckerType = 0;//pt alb
        rightSide.getListTrianglesUpSide().get(0).numberCheckers = 2;
        for (int i = 0; i < 2; i++) {
            Checker checker = new Checker(radius, 0, strokeWidth, rightSide.getListTrianglesUpSide().get(0));
            checker.getShapeChecker().setCenterX(xValue - lengthTriangle / 2);
            checker.getShapeChecker().setCenterY(yValue);
            yValue += 50;
            rightSide.getListTrianglesUpSide().get(0).getGroupCheckers().getChildren().add(checker.getShapeChecker());
            rightSide.getListTrianglesUpSide().get(0).getListOfCheckers().add(checker);
        }

        xValue = rightSide.getListTrianglesUpSide().get(5).getXRight();
        yValue = rightSide.tableArea.getY() + 25;

        rightSide.getListTrianglesUpSide().get(5).colorCheckerType = 1;
        rightSide.getListTrianglesUpSide().get(5).numberCheckers = 5;
        for (int i = 0; i < 5; i++) {
            Checker checker = new Checker(radius, 1, strokeWidth, rightSide.getListTrianglesUpSide().get(5));
            checker.getShapeChecker().setCenterX(xValue - lengthTriangle / 2);
            checker.getShapeChecker().setCenterY(yValue);
            yValue += 50;
            rightSide.getListTrianglesUpSide().get(5).getGroupCheckers().getChildren().add(checker.getShapeChecker());
            rightSide.getListTrianglesUpSide().get(5).getListOfCheckers().add(checker);
        }


        xValue = leftSide.getListTrianglesDownSide().get(1).getXRight();
        yValue = leftSide.tableArea.getY() + 25;
        //leftup ----checked
        leftSide.getListTrianglesUpSide().get(1).numberCheckers = 3;
        leftSide.getListTrianglesUpSide().get(1).colorCheckerType = 1;
        for (int i = 0; i < 3; i++) {
            Checker checker = new Checker(radius, 1, strokeWidth, leftSide.getListTrianglesUpSide().get(1));
            checker.getShapeChecker().setCenterX(xValue - lengthTriangle / 2);
            checker.getShapeChecker().setCenterY(yValue);
            yValue += 50;
            leftSide.getListTrianglesUpSide().get(1).getGroupCheckers().getChildren().add(checker.getShapeChecker());
            leftSide.getListTrianglesUpSide().get(1).getListOfCheckers().add(checker);
        }
        xValue = leftSide.getListTrianglesUpSide().get(5).getXRight();
        yValue = leftSide.tableArea.getY() + 25;
        leftSide.getListTrianglesUpSide().get(5).numberCheckers = 5;
        leftSide.getListTrianglesUpSide().get(5).colorCheckerType = 0;
        for (int i = 0; i < 5; i++) {
            Checker checker = new Checker(radius, 0, strokeWidth, leftSide.getListTrianglesUpSide().get(5));
            checker.getShapeChecker().setCenterX(xValue - lengthTriangle / 2);
            checker.getShapeChecker().setCenterY(yValue);
            yValue += 50;
            leftSide.getListTrianglesUpSide().get(5).getGroupCheckers().getChildren().add(checker.getShapeChecker());
            leftSide.getListTrianglesUpSide().get(5).getListOfCheckers().add(checker);
        }
    }

    private void initHousesFull() {
        double xValue = rightSide.getListTrianglesDownSide().get(0).getXRight();
        double yValue = rightSide.tableArea.getY() + rightSide.tableArea.getHeight() - radius;

        for (Triangle triangle : rightSide.getListTrianglesDownSide()
        ) {
            triangle.setColorCheckerType(0);
            int counter = triangle.getIndex() <= 3 ? 3 : 2;
            triangle.setNumberCheckers(counter);
            for (int i = 0; i < counter; i++) {
                Checker checker = new Checker(radius, 0, strokeWidth, triangle);
                //checker.getShapeChecker().setCenterX(xValue - lengthTriangle / 2);
                //checker.getShapeChecker().setCenterY(yValue);
                // yValue += 50;
                triangle.getGroupCheckers().getChildren().add(checker.getShapeChecker());
                triangle.getListOfCheckers().add(checker);
            }
            TableVisualController.reconfigureCheckersPositioning(1, triangle);
//            xValue -= lengthTriangle;
//            yValue = rightSide.tableArea.getY() + rightSide.tableArea.getHeight() - radius;
        }
        xValue = rightSide.getListTrianglesUpSide().get(0).getXRight();
        yValue = rightSide.tableArea.getY() + 25;

        for (Triangle triangle : rightSide.getListTrianglesUpSide()
        ) {
            triangle.setColorCheckerType(1);
            int counter = triangle.getIndex() <= 21 ? 2 : 3;
            triangle.setNumberCheckers(counter);
            for (int i = 0; i < counter; i++) {
                Checker checker = new Checker(radius, 1, strokeWidth, triangle);
                //checker.getShapeChecker().setCenterX(xValue - lengthTriangle / 2);
                //checker.getShapeChecker().setCenterY(yValue);
                //yValue -= 50;
                triangle.getGroupCheckers().getChildren().add(checker.getShapeChecker());
                triangle.getListOfCheckers().add(checker);
            }
            TableVisualController.reconfigureCheckersPositioning(3, triangle);
//            xValue -= lengthTriangle;
//            yValue = rightSide.tableArea.getY() + rightSide.tableArea.getHeight() - radius;
        }

    }

    private void initCheckers() {
        /*
       initialiasing predefined configuration for the table
       partea de jos merge de la dreapta la stanga cu numerotarea
       partea de sus merge clasic cu numerotarea triunghiurilor, sau mai bine zis a containerelor pt checkers
        */
        initCheckerDown();
        initCheckersUp();
//        initHousesFull();
        Collections.reverse(leftSide.getListTrianglesUpSide());
        Collections.reverse(rightSide.getListTrianglesUpSide());
        listOfAllTriangles.add(null);
        listOfAllTriangles.addAll(rightSide.getListTrianglesDownSide());
        listOfAllTriangles.addAll(leftSide.getListTrianglesDownSide());
        listOfAllTriangles.addAll(leftSide.getListTrianglesUpSide());
        listOfAllTriangles.addAll(rightSide.getListTrianglesUpSide());
    }

    public TableGame(int lengthTable, int heightTable, Scene scene) {
        this.heightTable = heightTable;
        this.lengthTable = lengthTable;
        this.scene = scene;
        nodTabla = new Group();
        middleSeparator = new Rectangle(lengthMiddleSeparator, heightTable);
        middleSeparator.setFill(Color.GREY);
        containerBlack = new VBox();
        containerWhite = new VBox();
    }

    public void drawTable() {
        tableArea = new Rectangle(this.lengthTable, this.heightTable);
        tableArea.setFill(Color.BLACK);
        tableArea.setX(scene.getWidth() / 2 - this.lengthTable / 2);
        tableArea.setY(scene.getHeight() / 2 - this.heightTable / 2);
        middleSeparator.setX(tableArea.getX() + this.lengthTable / 2 - lengthMiddleSeparator / 2 - 100);
        middleSeparator.setY(scene.getHeight() / 2 - this.heightTable / 2);
        leftSide = new BoardSide('s', this);
        rightSide = new BoardSide('r', this);
        Button putWhite = new Button("Scoate alba");
        Button putBlack = new Button("Scoate neagra");

        lengthTriangle = leftSide.tableArea.getWidth() / 6;
        leftSide.drawHalfTable();
        rightSide.drawHalfTable();
        Rectangle test = new Rectangle(100, 100);
        test.setFill(Color.RED);
        Rectangle test2 = new Rectangle(100, 100);
        test2.setFill(Color.YELLOW);
        containerWhite.setLayoutY(tableArea.getY() + 30);
        containerWhite.setLayoutX(rightSide.tableArea.getX() + rightSide.tableArea.getWidth() + 15);
        containerBlack.setLayoutY(tableArea.getY() + 30);
        containerBlack.setLayoutX(rightSide.tableArea.getX() + rightSide.tableArea.getWidth() + 95);
        putWhite.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                GameController.bearOff(0);
            }
        });
        putBlack.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                GameController.bearOff(1);
            }
        });
        containerWhite.getChildren().add(putWhite);
        containerBlack.getChildren().add(putBlack);
        initCheckers();
        nodTabla.getChildren().addAll(tableArea, middleSeparator, leftSide.getHalfTable(), rightSide.getHalfTable(), containerWhite, containerBlack);

    }

    public VBox getContainerBlack() {
        return containerBlack;
    }

    public VBox getContainerWhite() {
        return containerWhite;
    }

    public Group getNode() {
        return nodTabla;
    }

    public List<Triangle> getListOfAllTriangles() {
        return listOfAllTriangles;
    }

    public BoardSide getLeftSide() {
        return leftSide;
    }

    public BoardSide getRightSide() {
        return rightSide;
    }
}
