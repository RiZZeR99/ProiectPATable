package tablecomponents;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;


public class BoardSide {
    TableGame tableGame;//tabla mare
    Rectangle tableArea;//partea alba a tablei
    Group whiteBoardArea;//grup pentru a pune toate elementele unei jumatati
    char side;
    List<Triangle> listTrianglesDownSide = new ArrayList<>();
    List<Triangle> listTrianglesUpSide = new ArrayList<>();
    Group upTrianglesImages = new Group();
    Group downTrianglesImages = new Group();

    public BoardSide(char side, TableGame tableGame) {
        tableArea = new Rectangle(tableGame.lengthTable / 2 - 200, tableGame.heightTable - 50);
        whiteBoardArea = new Group();
        this.tableGame = tableGame;
        this.side = side;
        if (side == 's')//daca e in stanga
        {
            tableArea.setX(tableGame.tableArea.getX() + 50);
        } else {//daca ii in dreapta
            tableArea.setX(tableGame.tableArea.getX() + tableGame.tableArea.getWidth() / 2 + 150);
        }
        tableArea.setY(tableGame.tableArea.getY() + 25);
    }

    public char getSide(){
        return side;
    }

    public void drawHalfTable() {
        tableArea.setFill(Color.WHITE);
        whiteBoardArea.getChildren().add(tableArea);
        double startXDraw = tableArea.getX()+tableArea.getWidth();
        double offset = tableArea.getWidth() / 6;
        for (int i = 1; i <= 6; i++) {
            //desenez triunghiurile de la stanga la dreapta

            Triangle downTriangle = new Triangle(startXDraw, tableArea.getY() + tableGame.heightTable - 50,//dreapta
                    startXDraw - offset, tableArea.getY() + tableGame.heightTable - 50,//stanga
                    startXDraw - offset / 2, tableArea.getY() + 500,//mijloc
                    side == 's' ? i + 6 : i);
            Triangle upTriangle = new Triangle(startXDraw, tableArea.getY(), startXDraw - offset, tableArea.getY(),
                    startXDraw - offset / 2, tableArea.getY() + 300,
                    side == 's' ? 19-i:25-i );
            startXDraw -= offset;
            listTrianglesDownSide.add(downTriangle);
            listTrianglesUpSide.add(upTriangle);
            upTrianglesImages.getChildren().add(upTriangle.getGroupCheckers());
            upTriangle.getGroupCheckers().getChildren().get(0).toBack();
            downTrianglesImages.getChildren().add(downTriangle.getGroupCheckers());
            downTriangle.getGroupCheckers().getChildren().get(0).toBack();
        }

        whiteBoardArea.getChildren().addAll(downTrianglesImages, upTrianglesImages);
    }

    public List<Triangle> getListTrianglesDownSide() {
        return listTrianglesDownSide;
    }

    public List<Triangle> getListTrianglesUpSide() {
        return listTrianglesUpSide;
    }

    public Group getUpTrianglesImages() {
        return upTrianglesImages;
    }

    public Group getDownTrianglesImages() {
        return downTrianglesImages;
    }

    public Group getHalfTable() {
        return whiteBoardArea;
    }
}
