package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Checker {
    private Circle forma;
    private Color culoare;
    private double raza;
    static final double piValoare = Math.PI;

    public Checker(double raza, int indexJucator) {
        this.raza = raza;
        forma = new Circle(this.raza);
        if (indexJucator == 0) {
            forma.setFill(Color.GREY);
        } else
            forma.setFill(Color.BLUE);
    }
}
