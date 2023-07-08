/*
    Realizar el juego de Tatetí para dos jugadores. 
    EL jugador 1 utilizará la letra X 
    y el jugador 2 la letra O para marcar sus elecciones. 
    El tablero armarlo con una matriz de 3x3 
    que debe ser visualizada en pantalla en cada jugada marcando 
    la ubicación elegida por cada jugador. 
    Luego de cada jugada se debe llamar a una función 
    para verificar si el jugador ganó o no la partida. 
    Si se llega a la última jugada y nadie gana, se debe informar del empate.
 */

import java.util.Random;
import java.util.Scanner;

public class tateti {

    static Scanner SC = new Scanner(System.in);
    static char[][] tablero = new char[3][3];
    static int completo = 0;
    static int ganador = 0;
    static String jugadorUno;
    static String jugadorDos;

    public static void main(String[] args) {
        String respuesta = " ";
        do {
            inicio();
            while (completo != 9 && ganador == 0) {
                jugar(jugadorUno, 'X');
                if (completo != 9 && ganador == 0) {
                    jugar(jugadorDos, 'O');
                }
            }
            respuesta = finDelJueo();
        } while (respuesta.compareToIgnoreCase("si") == 0);
    }

    public static void inicio() {
        String respuesta = " ";
        completo = 0;
        ganador = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tablero[i][j] = ' ';
            }
        }
        System.out.println("¡Bienvenido al juego del Ta-Te-Ti!");
        System.out.println("");
        System.out.println("        |     |     ");
        System.out.println("        |     |     ");
        System.out.println("   _____|_____|_____");
        System.out.println("        |     |     ");
        System.out.println("        |     |     ");
        System.out.println("   _____|_____|_____");
        System.out.println("        |     |     ");
        System.out.println("        |     |     ");
        System.out.println("        |     |     ");
        System.out.println("");
        System.out.println("El jugador 1 juega con las X y el jugador 2 juega con las O.");
        System.out.println("Para jugar ingrese el número de fila y columna en donde desea colocar su ficha.");
        System.out.println("");
        System.out.print("¿Desea jugar contra la computadora? Si/No: ");
        respuesta = SC.next();
        System.out.println("");
        System.out.print("Ingresa el nombre del Jugador 1: ");
        jugadorUno = SC.next();
        System.out.println("");
        if (respuesta.compareToIgnoreCase("Si") == 0) {
            jugadorDos = "LA COMPUTADORA";
        } else {
            System.out.print("Ingresa el nombre del Jugador 2: ");
            jugadorDos = SC.next();
            System.out.println("");
        }
    }

    public static void jugar(String jugador, char ficha) {
        int fila = 0, col = 0;
        Random numAleatorio = new Random();
        System.out.println("TURNO DE " + jugador);
        System.out.println("");
        if (jugador != "LA COMPUTADORA") {
            System.out.print("Ingrese una fila del 1 al 3: ");
            fila = SC.nextInt() - 1;
            System.out.print("Ingrese una columna del 1 al 3: ");
            col = SC.nextInt() - 1;
        } else {
            fila = numAleatorio.nextInt(3);
            col = numAleatorio.nextInt(3);
        }
        while (fila > 2 || fila <= -1 || col > 2 || col <= -1 || tablero[fila][col] != ' ') {
            if (jugador == "LA COMPUTADORA") {
                fila = numAleatorio.nextInt(3);
                col = numAleatorio.nextInt(3);
            } else {
                System.out.println("");
                System.out.println("ERROR. Posicion invalida.");
                System.out.println("Ingrese una fila y columna del 1 al 3 que no este ocupada.");
                System.out.println("");
                System.out.print("Ingrese una fila del 1 al 3: ");
                fila = SC.nextInt() - 1;
                System.out.print("Ingrese una columna del 1 al 3: ");
                col = SC.nextInt() - 1;
            }

        }
        tablero[fila][col] = ficha;
        completo++;
        mostrarTablero();
        if (verificarGanador(ficha)) {
            ganador++;
            System.out.println("¡Fin del juego! ¡" + jugador + " es el ganador!");
            System.out.println("");
        }
    }

    public static void mostrarTablero() {
        System.out.println("");
        System.out.println("        |     |     ");
        for (int i = 0; i < 3; i++) {
            System.out.println("     " + tablero[i][0] + "  |  " + tablero[i][1] + "  |  "
                    + tablero[i][2] + "  ");
            if (i < 2) {
                System.out.println("   _____|_____|_____");
                System.out.println("        |     |     ");
            }
        }
        System.out.println("        |     |     ");
        System.out.println("");
    }

    public static boolean verificarGanador(char ficha) {
        for (int i = 0; i < 3; i++) {
            if (tablero[i][0] == ficha && tablero[i][1] == ficha && tablero[i][2] == ficha) {
                return true;
            }
        }
        for (int i = 0; i < 3; i++) {
            if (tablero[0][i] == ficha && tablero[1][i] == ficha && tablero[2][i] == ficha) {
                return true;
            }
        }
        if (tablero[0][0] == ficha && tablero[1][1] == ficha && tablero[2][2] == ficha) {
            return true;
        }
        if (tablero[0][2] == ficha && tablero[1][1] == ficha && tablero[2][0] == ficha) {
            return true;
        }

        return false;
    }

    public static String finDelJueo() {
        String respuesta = " ";
        if (ganador == 0) {
            System.out.println("");
            System.out.println("¡Fin del juego! ¡Empate, nadie gana!");
            System.out.println("");
        }
        System.out.print("¿Desean volver a jugar? Si/No: ");
        respuesta = SC.next();
        System.out.println("");
        return respuesta;
    }
}