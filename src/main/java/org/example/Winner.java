package org.example;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.example.Tablero.Tablero;

import java.io.IOException;

public class Winner {
    @FXML
    protected Label win;

    public void initialize() {
        Tablero t = Tablero.getINSTANCE();
        win.setText("Felicidades Jugador " + (t.victoria(true) ? "1" : "2"));
    }


    @FXML
    public void aceptar() throws IOException {
        Tablero t = Tablero.getINSTANCE();
        win.setText("Felicidades Jugador: " + (t.victoria(true) ? "1" : "2"));
        t.reset();
        App.setRoot("primary", 640, 480);
    }
}
