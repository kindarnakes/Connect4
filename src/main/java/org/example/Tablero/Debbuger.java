package org.example.Tablero;

public class Debbuger {

    public static void mostrarTablero(Tablero t) {

        if (t != null) {
            for (int i = 0; i < t.fil; i++) {
                for (int j = 0; j < t.col; j++) {
                    if (t._tablero[i][j] != null) {
                        System.out.print("|" + (t._tablero[i][j].isRed() ? "x" : "o"));
                    } else {
                        System.out.print("|" + " ");
                    }
                }
                System.out.println("|");
            }
            System.out.print(" ");
            for (int i = 0; i < 7; i++) {
                System.out.print((t.hayHueco(i) ? "" + "^ " : "  "));
            }

            System.out.println();
            System.out.print(" ");
            for (int i = 0; i < 7; i++) {
                System.out.print((t.hayHueco(i) ? "" + (i + 1) : " ") + " ");
            }
            System.out.println();

        }
    }

}