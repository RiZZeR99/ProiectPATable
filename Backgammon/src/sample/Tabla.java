package sample;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tabla extends Node {
    public final int inaltime;
    public final int lungime;
    public Rectangle suprafata;
    public Rectangle delimitator;
    private Group root;
    private Scene scena;
    private Group nodTabla;
    private BoardSide stanga;
    private BoardSide dreapta;

    public Tabla(int inaltime, int lungime, Scene scena) {
        this.inaltime = inaltime;
        this.lungime = lungime;
        this.scena = scena;
        nodTabla = new Group();
        delimitator = new Rectangle(50, inaltime);
        delimitator.setFill(Color.GREY);
    }

    public void deseneazaTabla() {
        suprafata = new Rectangle(this.lungime, this.inaltime);
        suprafata.setFill(Color.BLACK);
        suprafata.setX(scena.getWidth() / 2 - this.lungime / 2);
        suprafata.setY(scena.getHeight() / 2 - this.inaltime / 2);
        delimitator.setX(suprafata.getX()+this.lungime/2-25);
        delimitator.setY(scena.getHeight() / 2 - this.inaltime / 2);
        stanga=new BoardSide('s',this);
        dreapta=new BoardSide('r',this);
        nodTabla.getChildren().addAll(suprafata,delimitator,stanga.getParteTabla(),dreapta.getParteTabla());

    }

    public Group getNod() {
        return nodTabla;
    }

}
