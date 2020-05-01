package org.example.Tablero;

import org.example.Casilla.Casilla;
import org.example.Casilla.CasillaAmarilla;
import org.example.Casilla.CasillaRoja;

/**
 * @author Ángel Serrano García
 */
public class Tablero {

    private static final int Defaultcol = 7;
    private static final int Defaultfil = 6;
    private static final Tablero INSTANCE = new Tablero(Defaultfil, Defaultcol);

    protected Casilla[][] _tablero;
    protected int fil;
    protected int col;

    private Tablero(int fil, int col) {
        this.fil = fil;
        this.col = col;
        _tablero = new Casilla[fil][col];
    }

    public static Tablero getINSTANCE() {
        return INSTANCE;
    }

    public void reset() {
        this._tablero = new Casilla[this.fil][this.col];
    }

    public boolean hayHueco(int c) {
        boolean hueco = false;
        if (c < col && c >= 0) {
            if (_tablero[0][c] == null) {
                hueco = true;
            }
        }

        return hueco;
    }

    public int idNext(int col) {
        int id = 0;
        boolean find = false;
        for (int i = 0; i < fil && !find; i++) {
            if (this._tablero[i][col] != null) {
                id = i;
                find = true;
            }
        }
        return id;
    }

    public boolean colocar(int c, boolean isred) {
        boolean colocado = false;

        if (hayHueco(c)) {
            for (int i = this.fil - 1; i >= 0 && !colocado; i--) {
                if (_tablero[i][c] == null) {
                    _tablero[i][c] = isred ? new CasillaRoja() : new CasillaAmarilla();
                    colocado = true;
                }
            }
        }

        return colocado;
    }

    public boolean victoria(boolean isred) {
        boolean victoria = false;

        if (this.victoriaHorizontal(isred) || this.victoriaVertical(isred) || this.victoriaDiagonal(isred)) {
            victoria = true;
        }

        return victoria;
    }

    private boolean victoriaHorizontal(boolean isred) {
        boolean victoria = false;
        int counter = 0;

        for (int i = 0; i < this.fil && !victoria; i++) {
            for (int j = 0; j < this.col && !victoria; j++) {
                if (this._tablero[i][j] != null && this._tablero[i][j].isRed() == isred) {
                    counter++;

                    if (counter >= 4) {
                        victoria = true;
                    }

                } else {
                    counter = 0;
                }
            }
            counter = 0;
        }
        return victoria;
    }

    private boolean victoriaVertical(boolean isred) {
        boolean victoria = false;
        int counter = 0;

        for (int i = 0; i < this.col && !victoria; i++) {
            for (int j = 0; j < this.fil && !victoria; j++) {
                if (this._tablero[j][i] != null && this._tablero[j][i].isRed() == isred) {
                    counter++;

                    if (counter >= 4) {
                        victoria = true;
                    }
                } else {
                    counter = 0;
                }
            }
            counter = 0;
        }
        return victoria;
    }

    private boolean victoriaDiagonal(boolean isred) {
        boolean victoria = false;

        for (int i = 0; i < this.fil / 2 && !victoria; i++) {
            for (int j = 0; j < this.col && !victoria; j++) {
                if (this._tablero[i][j] != null && this._tablero[i][j].isRed() == isred) {
                    victoria = diagonal(isred, i, j);
                }
            }
        }

        return victoria;
    }

    private boolean diagonal(boolean isred, int fil, int col) {
        boolean victoria = false;
        int counter = 0;

        for (int j = col, i = fil; j < this.col && i < this.fil && !victoria; j++, i++) {
            if (this._tablero[i][j] != null && this._tablero[i][j].isRed() == isred) {
                counter++;

                if (counter >= 4) {
                    victoria = true;
                }

            } else {
                counter = 0;
            }

        }
        counter = 0;

        for (int j = col, i = fil; j >= 0 && i < this.fil && !victoria; j--, i++) {
            if (this._tablero[i][j] != null && this._tablero[i][j].isRed() == isred) {
                counter++;

                if (counter >= 4) {
                    victoria = true;
                }

            } else {
                counter = 0;
            }

        }

        return victoria;
    }

}
