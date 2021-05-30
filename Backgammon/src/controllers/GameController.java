package controllers;

import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import scenes.GameScene;
import scenes.ScenesFactory;
import tablecomponents.Checker;
import tablecomponents.Triangle;

import java.util.*;

public class GameController {
    private static int turn = 0;//white first
    static int valueDice1;
    static int valueDice2;
    private static GameScene gameScene;
    private static Checker checkerToMove;
    private static boolean doubleDices = false;
    private static int backUpDiceDouble = -1;
    private static final Map<Triangle, Integer> trianglesAvailable = new HashMap<>();
    private static boolean dicesThrown = false;
    private static int sumAllMoves = -1;
    private static int availableMoves = -1;
    private static Text textInfo;
    private static int currentNumberCheckersWhite = 15;//used fot taking the checkers from the house
    private static int currentNumberCheckersBlack = 15;
    private static Set<Integer> indexesJailTriangle = new HashSet<>();//the triangles that are vulnerable for an enemy checker
    private static VBox jail;
    private static List<Checker> checkersInJail = new ArrayList<>();
    private static boolean enableBearOff = false;
    private static List<Checker> outWhiteCheckers = new ArrayList<>();
    private static List<Checker> outBlackCheckers = new ArrayList<>();


    public static int countAvailableMoves() {
        /*
        function used to see how many possibilities a player has
         */
        int counterPossibleSimpleMoves = 0;
        int index1 = 0, index2 = 0;
        //if (playerBlocked != turn && playerBlocked != 2) {
        //counting all the simple moves the player can make
        if (!checkAllCheckersInTheHouse()) {
            if (countCheckersInJain(turn) == 0) {
                for (Triangle triangle : gameScene.getTableGame().getListOfAllTriangles()) {
                    if (triangle != null && triangle.getColorCheckerType() == turn) {
                        if (turn == 1) {
                            index1 = triangle.getIndex() + valueDice1;
                            index2 = triangle.getIndex() + valueDice2;
                        } else {
                            index1 = triangle.getIndex() - valueDice1;
                            index2 = triangle.getIndex() - valueDice2;
                        }
                        if (accessNewTriangle(triangle.getIndex(), index1, valueDice1) && valueDice1 > -1) {
                            counterPossibleSimpleMoves++;
                        }
                        if (accessNewTriangle(triangle.getIndex(), index2, valueDice2) && valueDice2 > -1) {
                            counterPossibleSimpleMoves++;
                        }
                    }
                }
            } else {
                for (Checker checker : checkersInJail) {
                    if (checker.getColorValue() == turn) {
                        if (turn == 0) {
                            //white searches in the house of the black
                            index1 = 24 - valueDice1 + 1;
                            index2 = 24 - valueDice2 + 1;
                        } else if (turn == 1) {
                            index1 = valueDice1;
                            index2 = valueDice2;
                            //black searches in the house of the white
                        }
                        if (addTrianglesForEscapeJail(index1, valueDice1))
                            counterPossibleSimpleMoves++;
                        if (addTrianglesForEscapeJail(index2, valueDice2))
                            counterPossibleSimpleMoves++;
                    }
                }
            }
        } else {
            if (turn == 0) {
                for (int i = 6; i >= 1; i--) {
                    if (gameScene.getTableGame().getListOfAllTriangles().get(i).getColorCheckerType() == turn &&
                            gameScene.getTableGame().getListOfAllTriangles().get(i).getNumberCheckers() > 0) {
                        index1 = i - valueDice1;
                        index2 = i - valueDice2;
                        if (index1 > 0) {
                            if (accessNewTriangle(i, index1, valueDice1)) counterPossibleSimpleMoves++;
                        } else counterPossibleSimpleMoves++;
                        if (index2 > 0) {
                            if (accessNewTriangle(i, index2, valueDice2)) counterPossibleSimpleMoves++;
                        } else counterPossibleSimpleMoves++;
                    }
                }
            } else {
                for (int i = 19; i <= 24; i++) {
                    if (gameScene.getTableGame().getListOfAllTriangles().get(i).getColorCheckerType() == turn &&
                            gameScene.getTableGame().getListOfAllTriangles().get(i).getNumberCheckers() > 0) {
                        index1 = i + valueDice1;
                        index2 = i + valueDice2;
                        if (index1 < 25) {
                            if (accessNewTriangle(i, index1, valueDice1)) counterPossibleSimpleMoves++;
                        } else counterPossibleSimpleMoves++;
                        if (index2 < 25) {
                            if (accessNewTriangle(i, index2, valueDice2)) counterPossibleSimpleMoves++;
                        } else counterPossibleSimpleMoves++;
                    }

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

    public static void setSumAllMoves(int newSum) {
        sumAllMoves = newSum;
    }

    public static void setCountAvailableMoves(int count) {
        availableMoves = count;
    }

    public static void startGame(GameScene gameSceneCurrent) {
        System.out.println("sa inceapa jocul");
        gameScene = gameSceneCurrent;
        jail = (VBox) gameScene.getRoot().getChildren().get(2);
        textInfo = (Text) gameScene.getRoot().getChildren().get(3);
    }

    public static void changeTurn(String message) {
        turn = (short) (1 - turn);
        dicesThrown = false;
        //"Dati cu zarul. Jucatorul cu tura " + (1 - turn) + " a terminat"
        System.out.println(message);
        TableVisualController.setTextInfo(message, textInfo);
        /**
         * TODO: de setat un text ca jucatorul respectiv a terminat de mutat
         */
    }

    public static boolean accessNewTriangle(int currentIndex, int newIndex, int costMove) {
        //this function tells that if a triangle at a new position is accesible from a current position
        if (newIndex < 25 && newIndex > 0) {
            if (turn == gameScene.getTableGame().getListOfAllTriangles().get(currentIndex).getColorCheckerType()) {
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
        }
        //return "Nici un triunghi valabil pentru zarul cu valoare " + (newIndex - currentIndex);
        return false;
    }

    public static boolean checkAllCheckersInTheHouse() {
        //function used fot taking of the checkers from the HOUSE
        int countCheckers = 0;
        int i;
        if (turn == 1) {
            //check the house of the black player
            for (i = 24; i >= 19; i--) {
                if (gameScene.getTableGame().getListOfAllTriangles().get(i).getColorCheckerType() == 1) {
                    countCheckers += gameScene.getTableGame().getListOfAllTriangles().get(i).getNumberCheckers();
                }
            }
            if (countCheckers == currentNumberCheckersBlack) return true;
        } else {
            //check the house of the white player
            for (i = 6; i >= 1; i--) {
                if (gameScene.getTableGame().getListOfAllTriangles().get(i).getColorCheckerType() == 0) {
                    countCheckers += gameScene.getTableGame().getListOfAllTriangles().get(i).getNumberCheckers();
                }
            }
            if (countCheckers == currentNumberCheckersWhite) return true;
        }
        return false;
    }

    private static int countCheckersInJain(int turn) {
        int count = 0;
        for (Checker checker : checkersInJail) {
            if (checker.getColorValue() == turn) {
                count++;
            }
        }

        return count;
    }

    public static void prepareForBearingOff() {
        if (checkerToMove.getCurrentTriangle() != null)
            if (checkerToMove.getCurrentTriangle().getIndex() >= 19 || checkerToMove.getCurrentTriangle().getIndex() <= 6) {
                int index1, index2;
                int currentPosition = checkerToMove.getCurrentTriangle().getIndex();
                boolean farthest = true;
                if (turn == 0) {
                    for (int i = currentPosition + 1; i <= 6; i++) {
                        if (gameScene.getTableGame().getListOfAllTriangles().get(i).getColorCheckerType() == turn) {
                            farthest = false;
                            break;
                        }
                    }
                    index1 = checkerToMove.getCurrentTriangle().getIndex() - valueDice1;
                    index2 = checkerToMove.getCurrentTriangle().getIndex() - valueDice2;
                    //first dice
                    if (index1 > 0 && valueDice1 > -1) {
                        addAvailableTriangle(currentPosition, index1, valueDice1);
                    } else if ((farthest || index1 == 0) && valueDice1 > -1) {
                        enableBearOff = true;
                    }
                    //second dice
                    if (index2 > 0 && valueDice2 > -1) {
                        addAvailableTriangle(currentPosition, index2, valueDice2);
                    } else if ((farthest || index2 == 0) && valueDice2 > -1) {
                        enableBearOff = true;
                    }

                } else {
                    for (int i = currentPosition - 1; i >= 19; i--) {
                        if (gameScene.getTableGame().getListOfAllTriangles().get(i).getColorCheckerType() == turn) {
                            farthest = false;
                            break;
                        }
                    }
                    index1 = checkerToMove.getCurrentTriangle().getIndex() + valueDice1;
                    index2 = checkerToMove.getCurrentTriangle().getIndex() + valueDice2;
                    //first dice
                    if (index1 < 25 && valueDice1 > -1) {
                        addAvailableTriangle(currentPosition, index1, valueDice1);
                    } else if ((farthest || index1 == 25) && valueDice1 > -1) {
                        enableBearOff = true;
                    }
                    //second dice
                    if (index2 < 25 && valueDice2 > -1) {
                        addAvailableTriangle(currentPosition, index2, valueDice2);
                    } else if ((farthest || index2 == 25) && valueDice2 > -1) {
                        enableBearOff = true;
                    }
                }
            }
    }

    private static boolean isFarthest() {
        boolean farthest = true;
        if (turn == 0) {
            for (int i = checkerToMove.getCurrentTriangle().getIndex() + 1; i <= 6; i++) {
                if (gameScene.getTableGame().getListOfAllTriangles().get(i).getColorCheckerType() == turn) {
                    farthest = false;
                    break;
                }
            }
        } else {
            for (int i = checkerToMove.getCurrentTriangle().getIndex() - 1; i >= 19; i--) {
                if (gameScene.getTableGame().getListOfAllTriangles().get(i).getColorCheckerType() == turn) {
                    farthest = false;
                    break;
                }
            }
        }
        return farthest;
    }

    public static void bearOff(int typeButton) {
        if (enableBearOff) {
            Triangle oldTriangle = checkerToMove.getCurrentTriangle();
            if (checkerToMove.getColorValue() == turn && typeButton == turn) {
                if (turn == 0) {
                    int foundDice = -1;
                    if (checkerToMove.getCurrentTriangle().getIndex() - valueDice1 == 0) {
                        sumAllMoves -= valueDice1;
                        availableMoves--;
                        foundDice = valueDice1;
                        valueDice1 = -1;
                    } else if (checkerToMove.getCurrentTriangle().getIndex() - valueDice1 < 0 && isFarthest()) {
                        sumAllMoves -= valueDice1;
                        availableMoves--;
                        foundDice = valueDice1;
                        valueDice1 = -1;
                    }

                    if (foundDice == -1) {
                        if (checkerToMove.getCurrentTriangle().getIndex() - valueDice2 == 0) {
                            sumAllMoves -= valueDice2;
                            availableMoves--;
                            foundDice = valueDice2;
                            valueDice2 = -1;
                        } else if (checkerToMove.getCurrentTriangle().getIndex() - valueDice2 < 0 && isFarthest()) {
                            sumAllMoves -= valueDice2;
                            availableMoves--;
                            foundDice = valueDice2;
                            valueDice2 = -1;
                        }
                    }
                    TableVisualController.resetCheckerColor(checkerToMove);
                    oldTriangle.setNumberCheckers(oldTriangle.getNumberCheckers() - 1);
                    oldTriangle.getGroupCheckers().getChildren().remove(checkerToMove.getShapeChecker());
                    oldTriangle.getListOfCheckers().remove(checkerToMove);
                    TableVisualController.reconfigureCheckersPositioning(1, oldTriangle);

                    gameScene.getTableGame().getContainerWhite().getChildren().add(checkerToMove.getShapeChecker());
                    outWhiteCheckers.add(checkerToMove);
                    currentNumberCheckersWhite--;
                    if (currentNumberCheckersWhite == 0) {
                        ScenesFactory.getWinnerScene().setTextMessage("Castigatorul este jucatorul alb");
                        ScenesController.setNewScene(ScenesFactory.getWinnerScene().getScene());
                    }

                } else {
                    int foundDice = -1;
                    if (checkerToMove.getCurrentTriangle().getIndex() + valueDice1 == 25) {
                        sumAllMoves -= valueDice1;
                        availableMoves--;
                        foundDice = valueDice1;
                        valueDice1 = -1;
                    } else if (checkerToMove.getCurrentTriangle().getIndex() + valueDice1 > 25 && isFarthest()) {
                        sumAllMoves -= valueDice1;
                        availableMoves--;
                        foundDice = valueDice1;
                        valueDice1 = -1;
                    }

                    if (foundDice == -1) {
                        if (checkerToMove.getCurrentTriangle().getIndex() + valueDice2 == 25) {
                            sumAllMoves -= valueDice2;
                            availableMoves--;
                            foundDice = valueDice2;
                            valueDice2 = -1;
                        } else if (checkerToMove.getCurrentTriangle().getIndex() + valueDice2 > 25 && isFarthest()) {
                            sumAllMoves -= valueDice2;
                            availableMoves--;
                            foundDice = valueDice2;
                            valueDice2 = -1;
                        }
                    }
                    TableVisualController.resetCheckerColor(checkerToMove);
                    oldTriangle.setNumberCheckers(oldTriangle.getNumberCheckers() - 1);
                    oldTriangle.getGroupCheckers().getChildren().remove(checkerToMove.getShapeChecker());
                    oldTriangle.getListOfCheckers().remove(checkerToMove);
                    TableVisualController.reconfigureCheckersPositioning(3, oldTriangle);

                    gameScene.getTableGame().getContainerBlack().getChildren().add(checkerToMove.getShapeChecker());
                    outBlackCheckers.add(checkerToMove);
                    currentNumberCheckersBlack--;

                    if (currentNumberCheckersBlack == 0) {
                        ScenesFactory.getWinnerScene().setTextMessage("Castigatorul este jucatorul negru");
                        ScenesController.setNewScene(ScenesFactory.getWinnerScene().getScene());
                    }

                }
                enableBearOff = false;
                checkerToMove.setIsOut(true);
                checkerToMove.setCurrentTriangle(null);
                if (checkerToMove != null) {
                    TableVisualController.resetCheckerColor(checkerToMove);
                }
                for (int i = 1; i <= 24; i++) {
                    gameScene.getTableGame().getListOfAllTriangles().get(i).resetColor();
                }
                trianglesAvailable.clear();
            }
            if (availableMoves > 0) {
                if (sumAllMoves == 0) {
                    if (doubleDices) {
                        doubleDices = false;
                        sumAllMoves = 2 * backUpDiceDouble;
                        valueDice1 = valueDice2 = backUpDiceDouble;
                        backUpDiceDouble = -1;
                        trianglesAvailable.clear();
                        enableBearOff = false;
                    } else {
                        trianglesAvailable.clear();
                        enableBearOff = false;
                        changeTurn("Dati cu zarul. Jucatorul cu tura " + (turn) + " a terminat");
                        dicesThrown = false;

                    }
                }
            } else {
                //if the are not any available moves
                trianglesAvailable.clear();
                changeTurn("Dati cu zarul. Jucatorul cu tura " + (turn) + " a terminat");
                dicesThrown = false;
                enableBearOff = false;
            }
        }

    }

    @Important
    public static void makeMoveChecker(Checker checker, Triangle triangle) {
        //this function is called when the player clicks on a checker
        if (dicesThrown) {
            System.out.println("Valori zar 1 :: " + valueDice1 + "  si zar 2 :: " + valueDice2);
            //if the dices were thrown
            enableBearOff = false;
            if (checkerToMove != null) {//reset the color of a previous selected checker
                TableVisualController.resetCheckerColor(checkerToMove);
            }
            for (int i = 1; i <= 24; i++) {
                gameScene.getTableGame().getListOfAllTriangles().get(i).resetColor();
            }
            trianglesAvailable.clear();
            checkerToMove = checker;

            if (checker.getCurrentTriangle() != null) {//info about the selected checker
                System.out.println("Index checker triunghi: " + checker.getCurrentTriangle().getIndex());
                System.out.println("Index triunghi dat: " + triangle.getIndex());
                System.out.println("Tringhiuri accesibile pentru mutare:");
            }

            TableVisualController.setColorSelectedChecker(checkerToMove);
            if (turn == checkerToMove.getColorValue()) {
                //if the player requesting to move a checker has its turn
                TableVisualController.setTextInfo("Jucatorul cu tura " + turn + " are voie sa mute", textInfo);
                if (countCheckersInJain(turn) == 0) {
                    //if the player has not any checker in the jail

                    if (checkAllCheckersInTheHouse()) {
                        //if the player can take off its checkers
                        prepareForBearingOff();
                        return;
                    }
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
                } else if (checkerToMove.isInJail()) {
                    if (checkAvailableMoves() == false)
                        return;
                    int index1 = 0, index2 = 0;
                    if (turn == 0) {
                        //white searches in the house of the black
                        index1 = 24 - valueDice1 + 1;
                        index2 = 24 - valueDice2 + 1;
                    } else if (turn == 1) {
                        index1 = valueDice1;
                        index2 = valueDice2;
                        //black searches in the house of the white
                    }
                    addTrianglesForEscapeJail(index1, valueDice1);
                    addTrianglesForEscapeJail(index2, valueDice2);
                } else {
                    System.out.println("jucatorule  " +
                            turn + "  esti blocat");
                    TableVisualController.setTextInfo("jucatorule  " +
                            turn + "  esti blocat", textInfo);
                }
            } else {
                System.out.println("Nu este piesa ta");
                TableVisualController.setTextInfo("Nu este piesa ta jucator " + turn, textInfo);
            }
        }
    }

    private static boolean addTrianglesForEscapeJail(int index, int valueDice) {
        //here i look for triangle that can be used to escape a checker from the jail
        //it is adding auto the triangle to the list
        if (index > 0 && index < 25 && valueDice > -1) {
            if (gameScene.getTableGame().getListOfAllTriangles().get(index).getColorCheckerType() == -1) {
                gameScene.getTableGame().getListOfAllTriangles().get(index).getTriangleShape().setFill(Color.YELLOW);
                gameScene.getTableGame().getListOfAllTriangles().get(index).setAvailableForChecker(true);
                trianglesAvailable.put(gameScene.getTableGame().getListOfAllTriangles().get(index), valueDice);
                return true;
            } else if (gameScene.getTableGame().getListOfAllTriangles().get(index).getColorCheckerType() == turn) {
                gameScene.getTableGame().getListOfAllTriangles().get(index).getTriangleShape().setFill(Color.YELLOW);
                gameScene.getTableGame().getListOfAllTriangles().get(index).setAvailableForChecker(true);
                trianglesAvailable.put(gameScene.getTableGame().getListOfAllTriangles().get(index), valueDice);
                return true;
            } else if (gameScene.getTableGame().getListOfAllTriangles().get(index).getNumberCheckers() == 1 && gameScene.getTableGame().getListOfAllTriangles().get(index).getColorCheckerType() != turn) {
                gameScene.getTableGame().getListOfAllTriangles().get(index).getTriangleShape().setFill(Color.YELLOW);
                gameScene.getTableGame().getListOfAllTriangles().get(index).setAvailableForChecker(true);
                trianglesAvailable.put(gameScene.getTableGame().getListOfAllTriangles().get(index), valueDice);
                indexesJailTriangle.add(index);
                return true;
            }
        }
        return false;
    }

    private static void addAvailableTriangle(int index, int indexTriangle, int valueDice) {
        //this function is used for checkers that are normal (not in jail or in the process of bearing off)
        boolean pos1 = false;
        if (sumAllMoves - valueDice >= 0 && valueDice > -1 && turn == gameScene.getTableGame().getListOfAllTriangles().get(index).getColorCheckerType()) {
            pos1 = accessNewTriangle(index, indexTriangle, valueDice);
            if (pos1) {
                gameScene.getTableGame().getListOfAllTriangles().get(indexTriangle).getTriangleShape().setFill(Color.YELLOW);
                gameScene.getTableGame().getListOfAllTriangles().get(indexTriangle).setAvailableForChecker(true);
                trianglesAvailable.put(gameScene.getTableGame().getListOfAllTriangles().get(indexTriangle), valueDice);
            }
        }
    }

    public static void setValueDice1(int valueDice1) {
        GameController.valueDice1 = valueDice1;
    }

    public static void setValueDice2(int valueDice2) {
        GameController.valueDice2 = valueDice2;
    }

    public static void putChecker(Triangle triangle) {
        enableBearOff = false;
        if (trianglesAvailable.keySet().contains(triangle) && checkerToMove != null) {
            if (checkerToMove.isInJail()) {//if the checker that is gonna be moved was in hail
                checkersInJail.remove(checkerToMove);
                jail.getChildren().remove(checkerToMove.getShapeChecker());
                checkerToMove.setCurrentTriangle(triangle);
            }
            if (indexesJailTriangle.contains(triangle.getIndex())) {
                /*
                this works as I see
                the triangle that will receive a checker has one checker of the other player
                we will put that old checker in the jail
                */
                Checker unlucky = triangle.getListOfCheckers().get(0);
                jail.getChildren().add(unlucky.getShapeChecker());
                Triangle oldTriangle = unlucky.getCurrentTriangle();
                oldTriangle.getListOfCheckers().remove(unlucky);
                oldTriangle.getGroupCheckers().getChildren().remove(unlucky.getShapeChecker());
                oldTriangle.setNumberCheckers(0);

                unlucky.setCurrentTriangle(null);
                unlucky.setInJail(true);
                checkersInJail.add(unlucky);
            }
            TableVisualController.addCheckerToTriangle(checkerToMove, triangle);
            triangle.setColorCheckerType(checkerToMove.getColorValue());//in case the triangle hadn't before another checker
            TableVisualController.resetCheckerColor(checkerToMove);

            for (Triangle triangleAvailable : trianglesAvailable.keySet()
            ) {
                triangleAvailable.resetColor();//reseting the color
                triangle.setAvailableForChecker(false);//reseting the availability for a checker
            }
            indexesJailTriangle.clear();


            sumAllMoves -= trianglesAvailable.get(triangle);
            availableMoves--;
            if (valueDice1 == trianglesAvailable.get(triangle)) valueDice1 = -1;
            else if (valueDice2 == trianglesAvailable.get(triangle)) valueDice2 = -1;

            System.out.println("triunghi: " + triangle.getIndex() + "      valoare zar pentru triunghi ales: " + trianglesAvailable.get(triangle));
            System.out.println("Valori noi:");
            System.out.println("Coordonate checker:" + checkerToMove.getShapeChecker().getCenterX() + "   " + checkerToMove.getShapeChecker().getCenterY());
            System.out.println("Layout checker: " + checkerToMove.getShapeChecker().getLayoutX() + "   " + checkerToMove.getShapeChecker().getLayoutY() + "\n");

            checkerToMove = null;
            if (availableMoves > 0) {
                if (sumAllMoves == 0) {
                    if (doubleDices) {
                        doubleDices = false;
                        sumAllMoves = 2 * backUpDiceDouble;
                        valueDice1 = valueDice2 = backUpDiceDouble;
                        backUpDiceDouble = -1;
                        trianglesAvailable.clear();
                    } else {
                        trianglesAvailable.clear();
                        changeTurn("Dati cu zarul. Jucatorul cu tura " + (turn) + " a terminat");
                        dicesThrown = false;
                        return;
                    }
                }
            } else {
                //if the are not any available moves
                trianglesAvailable.clear();
                changeTurn("Dati cu zarul. Jucatorul cu tura " + (turn) + " a terminat");
                dicesThrown = false;
                return;
            }
            if (checkAvailableMoves() == false)
                return;
        }
    }

    public static boolean getDicesThrown() {
        return dicesThrown;
    }

    public static boolean checkAvailableMoves() {
        /*
        this function checks if the current player still has moves to make
        if not it is changing the turn and display a message
        */
        if (countAvailableMoves() == 0) {
            System.out.println("jucatorul cu tura " + turn + " nu are mutari");
            TableVisualController.setTextInfo("Jucatorul cu tura " + turn + " pierde tura. 0 mutari posibile. Dati cu zarul", textInfo);
            changeTurn("Jucatorul cu tura " + turn + " is va pierde tura deoarece nu are mutari posibile");
            dicesThrown = false;
            return false;
        }
        return true;
    }

    public static void setDicesThrown(boolean dicesThrown) {
        GameController.dicesThrown = dicesThrown;
        trianglesAvailable.clear();
        indexesJailTriangle.clear();
        checkerToMove = null;
        TableVisualController.setTextInfo("Tura jucator "+turn, textInfo);
        checkAvailableMoves();

    }

    public static int getTurn() {
        return turn;
    }

}
