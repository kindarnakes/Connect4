package org.example.Casilla;

/**
 * @author Ángel Serrano García
 */
public class Casilla {

    private boolean _red;

    private Casilla() {

    }

    protected Casilla(boolean isred) {
        _red = isred;
    }

    public boolean isRed() {
        return _red;
    }

}
