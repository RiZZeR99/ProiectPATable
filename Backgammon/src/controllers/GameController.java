package controllers;

import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import scenes.GameScene;
import tablecomponents.Checker;
import tablecomponents.Triangle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameController {
    private static short turn = 0;//white first
    static int valueDice1;
    static int valueDice2;
    private static GameScene gameScene;
    private static Checker checkerToMove;
    private static short playerBlocked = -1;
    private static boolean doubleDices = false;
    private static int backUpDiceDouble = -1;
    /*
    SCHIMBAT INTR_un map ceva pt a seta pt fiecare triunghi o valoarea ce va repreze ta costul unei mutari
    pe care o poate face un jucator
    DONE
     */
    private static final Map<Triangle, Integer> trianglesAvailable = new HashMap<>();
    private static boolean dicesThrown = false;
    private static int sumAllMoves = -1;
    private static int countAvailableMoves = -1;
    private static int whiteInJail = 0;
    private static int blackInJail = 0;
    private static List<Integer> indexesJailTriangle = new ArrayList<>();//the triangles that are vulnerable for an enemy checker


    public static void setCounterMoves(int count) {
        countAvailableMoves = count;
    }

    public static int checkAvailableMoves() {
        /*
        function used to see how many possibilities a player has
         */
        int counterPossibleSimpleMoves = 0;
        Checker testChecker;
        int index1, index2;
        for (Triangle triangle : gameScene.getTableGame().getListOfAllTriangles()) {
            if (triangle != null && triangle.getColorCheckerType() == turn) {
                if (turn == 1) {
                    index1 = triangle.getIndex() + valueDice1;
                    index2 = triangle.getIndex() + valueDice2;
                } else {
                    index1 = triangle.getIndex() - valueDice1;
                    index2 = triangle.getIndex() - valueDice2;
                }
                if (accessNewTriangle(triangle.getIndex(), index1, valueDice1)) {
                    counterPossibleSimpleMoves++;
                }
                if (accessNewTriangle(triangle.getIndex(), index2, valueDice2)) {
                    counterPossibleSimpleMoves++;
                }
            }
        }

        return counterPossibleSimpleMoves;
    }

    public static boolean isDoubleDices() {
        return doubleDices;
    }

    public static void setDoubleDices(boolean doubleDices) {
        GameController.doubleDices = doubleDices;
        backUpDiceDouble = valueDice1;
    }

    public static short getPlayerBlocked() {
        return playerBlocked;
    }

    public static void setPlayerBlocked(short playerBlocked) {
        GameController.playerBlocked = playerBlocked;
    }

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
        dicesThrown = false;
        System.out.println("Dati cu zarul. Jucatorul cu tura " + (1 - turn) + " a terminat");
        if (checkAvailableMoves() == 0) {
            System.out.println("Player " + turn + " nu are ce piese sa mute. Prin urmare pierde tura :(");
            turn = (short) (1 - turn);
        }
    }

    public static boolean accessNewTriangle(int currentIndex, int newIndex, int costMove) {
        if (newIndex < 25 && newIndex > 0) {
            if (gameScene.getTableGame().getListOfAllTriangles().get(newIndex).getColorCheckerType() == -1) {
                //return ("triunghi index " + newIndex + " valabil adica ii gol");
                return true;
            } else if (gameScene.getTableGame().getListOfAllTriangles().get(newIndex).getColorCheckerType() == gameScene.getTableGame().getListOfAllTriangles().get(currentIndex).getColorCheckerType()) {
                //return ("triunghi valabil la index " + newIndex + "care are aceleasi checkers");
                return true;
            } else if (gameScene.getTableGame().getListOfAllTriangles().get(newIndex).getNumberCheckers() == 1 && gameScene.getTableGame().getListOfAllTriangles().get(newIndex).getColorCheckerType() != turn) {
                //return ("Tringhi valabil la index " + newIndex + " care are o piesa inamica");
                indexesJailTriangle.add(newIndex);
                return true;
            }
        }
        //return "Nici un triunghi valabil pentru zarul cu valoare " + (newIndex - currentIndex);
        return false;
    }

    public static void makeMoveChecker(Checker checker, Triangle triangle) {
        if (dicesThrown) {
            //if the dices were thrown
            if (checker.getCurrentTriangle() != null) {
                System.out.println("Index checker triunghi: " + checker.getCurrentTriangle().getIndex());
                System.out.println("Index triunghi dat: " + triangle.getIndex());
                System.out.println("Tringhiuri accesibile pentru mutare:");
            }
            trianglesAvailable.clear();
            if (checkerToMove != null) {
                TableVisualController.resetCheckerColor(checkerToMove);
            }
            for (int i = 1; i < 24; i++) {
                gameScene.getTableGame().getListOfAllTriangles().get(i).resetColor();
            }
            trianglesAvailable.clear();
            checkerToMove = checker;
            TableVisualController.setColorSelectedChecker(checkerToMove);
            int colorChecker = checker.getColorValue();
            if (turn == colorChecker) {
                //if the player requesting to move a checker has its turn
                if (playerBlocked != turn) {
                    //if the player has not any checker in the jail

                    int index = checker.getCurrentTriangle().getIndex();
                    int indexTriangle1;
                    int indexTriangle2;
                    if (turn == 0) {
                        indexTriangle1 = index - valueDice1;
                        indexTriangle2 = index - valueDice2;
                    } else {
                        indexTriangle1 = index + valueDice1;
                        indexTriangle2 = index + valueDice2;

                    }
                    System.out.println("\n----------------------------------------------------------\n");
                    /*
                     * this works like this:
                     * first call is for the triangle accessible with the number of the first dice
                     * second call is for the triangle accessible with the number of the second dice
                     * the third triangle is accessible with the sum of both, so the cost is greater
                     * */
                    addAvailableTriangle(index, indexTriangle1, valueDice1);//first dice
                    addAvailableTriangle(index, indexTriangle2, valueDice2);//second dice
                } else {
                    System.out.println("jucatorule  " +
                            turn + "  esti blocat");
                    int index1 = 0, index2 = 0;
                    if (turn == 0 && checkerToMove.isInJail()) {
                        //white searches in the house of the black
                        index1 = 24 - valueDice1 + 1;
                        index2 = 24 - valueDice2 + 1;
                    } else if (turn == 1 && checkerToMove.isInJail()) {
                        index1 = valueDice1;
                        index2 = valueDice2;
                        //black searches in the house of the white
                    }
                    if (checkerToMove.isInJail()) {
                        addTrianglesForEscapeJail(index1, valueDice1);
                        addTrianglesForEscapeJail(index2, valueDice2);

                    }
                }
            } else
                System.out.println("Nu este piesa ta");

        }
    }

    private static void addTrianglesForEscapeJail(int index, int valueDice) {
        if (index > 0 && index < 25) {
            if (gameScene.getTableGame().getListOfAllTriangles().get(index).getColorCheckerType() == -1) {
                gameScene.getTableGame().getListOfAllTriangles().get(index).getTriangleShape().setFill(Color.YELLOW);
                gameScene.getTableGame().getListOfAllTriangles().get(index).setAvailableForChecker(true);
                trianglesAvailable.put(gameScene.getTableGame().getListOfAllTriangles().get(index), valueDice);
            } else if (gameScene.getTableGame().getListOfAllTriangles().get(index).getColorCheckerType() == turn) {
                gameScene.getTableGame().getListOfAllTriangles().get(index).getTriangleShape().setFill(Color.YELLOW);
                gameScene.getTableGame().getListOfAllTriangles().get(index).setAvailableForChecker(true);
                trianglesAvailable.put(gameScene.getTableGame().getListOfAllTriangles().get(index), valueDice);
            } else if (gameScene.getTableGame().getListOfAllTriangles().get(index).getNumberCheckers() == 1 && gameScene.getTableGame().getListOfAllTriangles().get(index).getColorCheckerType() != turn) {
                gameScene.getTableGame().getListOfAllTriangles().get(index).getTriangleShape().setFill(Color.YELLOW);
                gameScene.getTableGame().getListOfAllTriangles().get(index).setAvailableForChecker(true);
                trianglesAvailable.put(gameScene.getTableGame().getListOfAllTriangles().get(index), valueDice);
                indexesJailTriangle.add(index);
            }
        }
    }

    private static void addAvailableTriangle(int index, int indexTriangle, int valueDice) {
        boolean pos1;
        if (sumAllMoves - valueDice >= 0 && valueDice > -1) {
            pos1 = (accessNewTriangle(index, indexTriangle, valueDice));
            if (pos1) {
                gameScene.getTableGame().getListOfAllTriangles().get(indexTriangle).getTriangleShape().setFill(Color.YELLOW);
                gameScene.getTableGame().getListOfAllTriangles().get(indexTriangle).setAvailableForChecker(true);
                trianglesAvailable.put(gameScene.getTableGame().getListOfAllTriangles().get(indexTriangle), valueDice);
            }
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
        if (trianglesAvailable.keySet().contains(triangle)) {
            if (indexesJailTriangle.contains(triangle.getIndex())) {
                VBox jail = (VBox) gameScene.getRoot().getChildren().get(2);
                Checker unlucky = triangle.getListOfCheckers().get(0);
                jail.getChildren().add(unlucky.getShapeChecker());
                if (unlucky.getColorValue() == 0) {
                    whiteInJail++;
                } else {
                    blackInJail++;
                }
                Triangle oldTriangle = unlucky.getCurrentTriangle();
                oldTriangle.getListOfCheckers().remove(unlucky);
                oldTriangle.getGroupCheckers().getChildren().remove(unlucky.getShapeChecker());
                oldTriangle.setNumberCheckers(0);

                unlucky.setCurrentTriangle(null);
                unlucky.setInJail(true);
                //indexesJailTriangle.remove((Integer) triangle.getIndex());
                playerBlocked = (short) (1 - turn);
            }
            indexesJailTriangle.clear();
            if (checkerToMove.isInJail()) {
                if (checkerToMove.getColorValue() == 0) {
                    whiteInJail--;
                    if (whiteInJail == 0 && playerBlocked == 0)
                        playerBlocked = -1;
                }
                if (checkerToMove.getColorValue() == 1) {
                    blackInJail--;
                    if (blackInJail == 0 && playerBlocked == 1)
                        playerBlocked = -1;
                }
            }
            TableVisualController.addCheckerToTriangle(checkerToMove, triangle);
            triangle.setColorCheckerType(checkerToMove.getColorValue());
            TableVisualController.resetCheckerColor(checkerToMove);
            for (int i = 1; i < 24; i++) {
                gameScene.getTableGame().getListOfAllTriangles().get(i).resetColor();
                gameScene.getTableGame().getListOfAllTriangles().get(i).setAvailableForChecker(false);
                //it could be better
            }
            sumAllMoves -= trianglesAvailable.get(triangle);
            countAvailableMoves--;
            if (valueDice1 == trianglesAvailable.get(triangle)) valueDice1 = -1;
            else if (valueDice2 == trianglesAvailable.get(triangle)) valueDice2 = -1;
            System.out.println("triunghi: " + triangle.getIndex() + "      valoare zar pentru triunghi ales: " + trianglesAvailable.get(triangle));
            triangle.resetColor();
            trianglesAvailable.remove(triangle);

            System.out.println("Valori noi:");
            System.out.println("Coordonate checker:" + checkerToMove.getShapeChecker().getCenterX() + "   " + checkerToMove.getShapeChecker().getCenterY());
            System.out.println("Layout checker: " + checkerToMove.getShapeChecker().getLayoutX() + "   " + checkerToMove.getShapeChecker().getLayoutY() + "\n");
            //checkerToMove = null;
            if (countAvailableMoves > 0) {
                if (sumAllMoves == 0) {
                    if (doubleDices) {
                        doubleDices = false;
                        sumAllMoves = 2 * backUpDiceDouble;
                        valueDice1 = valueDice2 = backUpDiceDouble;
                        backUpDiceDouble = -1;
                    } else {
                        trianglesAvailable.clear();
                        changeTurn();
                        dicesThrown = false;

                    }
                }
            } else {
                trianglesAvailable.clear();
                changeTurn();
                dicesThrown = false;
            }
        }
    }

    public static boolean getDicesThrown() {
        return dicesThrown;
    }

    public static void setDicesThrown(boolean dicesThrown) {
        GameController.dicesThrown = dicesThrown;
    }
}
