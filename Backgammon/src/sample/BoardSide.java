package sample;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;


public class BoardSide {
    private Tabla tabla;
    private Rectangle suprafata;
    private Group nodParteTabla;

    public BoardSide(char side, Tabla tabla) {
        suprafata = new Rectangle(450, 550);
        nodParteTabla = new Group();
        this.tabla = tabla;
        if (side == 's')//daca e in stanga
        {
            suprafata.setX(tabla.suprafata.getX() + 12);
            suprafata.setY(tabla.suprafata.getY() + 25);
        } else {//daca ii in dreapta
            suprafata.setX(tabla.suprafata.getX() + tabla.suprafata.getWidth() / 2 + 37);
            suprafata.setY(tabla.suprafata.getY() + 25);
        }
    }

    public void deseneazaParte() {
        suprafata.setFill(Color.WHITE);
        nodParteTabla.getChildren().add(suprafata);
        double pozitieStartDesenare = suprafata.getX();
        double offset = 75;
        for (int i = 1; i <= 6; i++) {
            Polygon triunghiJos = new Polygon(pozitieStartDesenare, suprafata.getY() + 550, pozitieStartDesenare + offset, suprafata.getY() + 550,
                    pozitieStartDesenare + offset / 2, suprafata.getY() + 400);
            Polygon triunghiSus = new Polygon(pozitieStartDesenare, suprafata.getY(),pozitieStartDesenare + offset, suprafata.getY(),
                    pozitieStartDesenare + offset / 2, suprafata.getY() + 200);
            pozitieStartDesenare += 75;
            if (i % 2 == 0) {
                triunghiJos.setFill(Color.TURQUOISE);
                triunghiSus.setFill(Color.LIMEGREEN);
            } else {
                triunghiJos.setFill(Color.LIMEGREEN);
                triunghiSus.setFill(Color.TURQUOISE);
            }
            nodParteTabla.getChildren().addAll(triunghiJos,triunghiSus);

        }
    }

    public Group getParteTabla() {
        this.deseneazaParte();
        return nodParteTabla;
    }
}
