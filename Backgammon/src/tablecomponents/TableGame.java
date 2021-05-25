package tablecomponents;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

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
    private final double lengthMiddleSeparator = 220;
    private List<Triangle> listOfAllTriangles = new ArrayList<>();

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

    private void initCheckers() {
        /*
       initialiasing predefined configuration for the table
       partea de jos merge de la dreapta la stanga cu numerotarea
       partea de sus merge clasic cu numerotarea triunghiurilor, sau mai bine zis a containerelor pt checkers
        */
        initCheckerDown();
        initCheckersUp();
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
    }

    public void drawTable() {
        tableArea = new Rectangle(this.lengthTable, this.heightTable);
        tableArea.setFill(Color.BLACK);
        tableArea.setX(scene.getWidth() / 2 - this.lengthTable / 2);
        tableArea.setY(scene.getHeight() / 2 - this.heightTable / 2);
        middleSeparator.setX(tableArea.getX() + this.lengthTable / 2 - lengthMiddleSeparator / 2);
        middleSeparator.setY(scene.getHeight() / 2 - this.heightTable / 2);
        leftSide = new BoardSide('s', this);
        rightSide = new BoardSide('r', this);
        lengthTriangle = 100;
        leftSide.drawHalfTable();
        rightSide.drawHalfTable();
        initCheckers();
        nodTabla.getChildren().addAll(tableArea, middleSeparator, leftSide.getHalfTable(), rightSide.getHalfTable());

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
