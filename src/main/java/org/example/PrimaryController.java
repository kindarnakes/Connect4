package org.example;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import org.example.Tablero.Tablero;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PrimaryController {

    private final Tablero t = Tablero.getINSTANCE();
    @FXML
    private Label turno;
    @FXML
    private ArrayList<VBox> colums;
    private boolean turn = true;
    private Map<Circle, Posicion> circulos;
    private boolean start = false;

    @FXML
    public void start() throws IOException {
        if (!start) {
            List<Circle> circles = new ArrayList<>();
            circulos = new HashMap<>();

            for (VBox v : colums) {
                for (Node n : v.getChildren()) {
                    if (n instanceof Circle) {
                        circles.add((Circle) n);
                        Posicion p = new Posicion();
                        p.col = Integer.parseInt(n.getParent().getId().charAt(2) + "");
                        p.fil = Integer.parseInt(n.getId().charAt(1) + "");
                        circulos.put((Circle) n, p);

                    }
                }
            }
            setColor();
            start = true;
        }
    }

    @FXML
    public void setColor() throws IOException {
        circulos.forEach((c, p) -> {
            c.setOnMouseClicked(e -> {
                if (t.idNext(p.col) == p.fil) {
                    boolean done = t.colocar(p.col, turn);
                    if (done) {
                        if (turn) {
                            c.setFill(Color.RED);
                        } else {
                            c.setFill(Color.YELLOW);
                        }
                        turn = !turn;
                    }
                }
            });
        });
        turno.setText("Turno del jugador: " + (turn ? "1" : "2"));
        if (t.victoria(!turn)) {
            App.setRoot("Winner", 73, 33);
        }
    }

    @FXML
    public void exit() {
        System.exit(0);
    }

    private class Posicion {
        public int fil;
        public int col;
    }
}
