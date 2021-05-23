package controllers;

import javafx.scene.paint.Color;
import scenes.GameScene;
import tablecomponents.Checker;
import tablecomponents.Triangle;

import java.util.HashSet;
import java.util.Set;

public class GameController {
    private static short turn = 1;
    static int valueDice1;
    static int valueDice2;
    private static GameScene gameScene;
    private static Checker checkerToMove;
    /*
    SCHIMBAT INTR_un map ceva pt a seta pt fiecare triunghi o valoarea ce va repreze ta costul unei mutari
    pe care o poate face un jucator
     */
    private static Set<Triangle> trianglesAvailable = new HashSet<>();
    private static boolean dicesThrown = false;
    private static int sumAllMoves;
    private static int countAvailableMoves;

    public static void setSumAllMoves(int newSum) {
        sumAllMoves = newSum;
    }

    public static void setCountAvailableMoves(int count) {
        countAvailableMoves = count;
    }

    public static void startGame(GameScene gameSceneCurrent) {
        System.out.println("sa inceapa jocul");
        gameScene = gameSceneCurrent;
    }

    public static void changeTurn() {
        turn = (short) (1 - turn);
    }

    public static String accessNewTriangle(int currentIndex, int newIndex) {
        if (newIndex < 25 && newIndex > 0) {
            if (gameScene.getTableGame().getListOfAllTriangles().get(newIndex).getColorCheckerType() == -1) {
                gameScene.getTableGame().getListOfAllTriangles().get(newIndex).getTriangleShape().setFill(Color.YELLOW);
                gameScene.getTableGame().getListOfAllTriangles().get(newIndex).setAvailableForChecker(true);
                trianglesAvailable.add(gameScene.getTableGame().getListOfAllTriangles().get(newIndex));
                return ("triunghi index " + newIndex + " valabil adica ii gol");
            } else if (gameScene.getTableGame().getListOfAllTriangles().get(newIndex).getColorCheckerType() == gameScene.getTableGame().getListOfAllTriangles().get(currentIndex).getColorCheckerType()) {
                gameScene.getTableGame().getListOfAllTriangles().get(newIndex).getTriangleShape().setFill(Color.YELLOW);
                gameScene.getTableGame().getListOfAllTriangles().get(newIndex).setAvailableForChecker(true);
                trianglesAvailable.add(gameScene.getTableGame().getListOfAllTriangles().get(newIndex));
                return ("triunghi valabil la index " + newIndex + "care are aceleasi checkers");
            } else if (gameScene.getTableGame().getListOfAllTriangles().get(newIndex).getNumberCheckers() == 1) {
                gameScene.getTableGame().getListOfAllTriangles().get(newIndex).getTriangleShape().setFill(Color.YELLOW);
                gameScene.getTableGame().getListOfAllTriangles().get(newIndex).setAvailableForChecker(true);
                trianglesAvailable.add(gameScene.getTableGame().getListOfAllTriangles().get(newIndex));
                return ("Tringhi valabil la index " + newIndex + " care are o piesa inamica");
            }
        }
        return "Nici un triunghi valabil pentru zarul cu valoare " + (newIndex - currentIndex);
    }

    public static void makeMoveChecker(Checker checker, Triangle triangle) {
        if (dicesThrown) {
            System.out.println("Index checker triunghi: " + checker.getCurrentTriangle().getIndex());
            System.out.println("Index triunghi dat: " + triangle.getIndex());
            System.out.println("Tringhiuri accesibile pentru mutare:");
            trianglesAvailable.clear();
            checkerToMove = checker;
            int colorChecker = checker.getColorValue();
            if (turn == colorChecker) {
                for (int i = 1; i < 24; i++) {
                    gameScene.getTableGame().getListOfAllTriangles().get(i).resetColor();
                }
                trianglesAvailable.clear();
                if (turn == 0) {
                    valueDice1 = -valueDice1;
                    valueDice2 = -valueDice2;
                }
                int index = checker.getCurrentTriangle().getIndex();
                int indexTriangle1 = index + valueDice1;
                int indexTriangle2 = index + valueDice2;
                int indexTriangle3 = index + valueDice1 + valueDice2;
                System.out.println("\n----------------------------------------------------------\n");
                System.out.println(accessNewTriangle(index, indexTriangle1));
                System.out.println(accessNewTriangle(index, indexTriangle2));
                System.out.println(accessNewTriangle(index, indexTriangle3));
            } else
                System.out.println("Nu este piesa ta");

        }
    }

    public static int getValueDice1() {
        return valueDice1;
    }

    public static void setValueDice1(int valueDice1) {
        GameController.valueDice1 = valueDice1;
    }

    public static int getValueDice2() {
        return valueDice2;
    }

    public static void setValueDice2(int valueDice2) {
        GameController.valueDice2 = valueDice2;
    }

    public static void putChecker(Triangle triangle) {
        if (trianglesAvailable.contains(triangle)) {
            checkerToMove.getCurrentTriangle().getIndex();
            TableVisualController.addCheckerToTriangle(checkerToMove, triangle);
            for (int i = 1; i < 24; i++) {
                gameScene.getTableGame().getListOfAllTriangles().get(i).resetColor();
            }
            trianglesAvailable.clear();
            checkerToMove = null;
            if (sumAllMoves == 0) {
                changeTurn();
            }
        }
    }

    public static void setDicesThrown(boolean dicesThrown) {
        GameController.dicesThrown = dicesThrown;
    }
}
