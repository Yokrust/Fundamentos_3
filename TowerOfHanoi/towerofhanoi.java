package TowerOfHanoi;

import java.util.Scanner;
import java.util.Stack;

//Autor: Yael Lomas
//Fecha: 2026/09/15
public class towerofhanoi {
    static Scanner sc = new Scanner(System.in);
    static int numDiscos = 3;
    static Stack<Integer>[] torres = new Stack[3];
    public static void main(String[] args) {
        menuPrincipal();
    }
    public static void menuPrincipal(){
        int opcion;
        do{
            System.out.printf("""
                ~ Torres de Hanoi ~
                    1) Elegir Discos (3 - 8)
                    2) Jugar Manualmente [Num. Discos: %d]
                    3) Mostrar Solucion
                    4) Salir
                """,numDiscos);
            opcion = leerEntero();

            switch (opcion) {
                case 1 -> { elegirNumeroDiscos();}
                case 2 -> { jugarManual();}
                case 3 -> {mostrarSolucion();}
                case 4 -> { System.out.println("Hasta luego..."); }
            }
        } while (opcion != 4);
    }
    private static void jugarManual(int numDiscos, char origen, char destino, char auxiliar) {
    }

    static void inicializaTorres() {
        for (int i = 0; i < 3; i++) {
            torres[i] = new Stack<>();
        }
        // inicializa la torre A con los discos
        for (int i = numDiscos; i >= 1; i--) {
            torres[0].push(i);
        }
    }
    private static void despliegaTorres(){
        for (int i = 0; i < torres.length; i++){
            despliegaTorre(i);
        }
    }

    private static void despliegaTorre(int torre){
        System.out.println("Torre " + (char)('A' + torre) + ": ");
        for (int disco : torres[torre]){
            System.out.println(disco );
        }
        System.out.println();
    }
    private static void jugarManual(){
        inicializaTorres();
        while(numDiscos > torres[2].size()){
            despliegaTorres();
            System.out.println("Ingrese la torre del origen (A, B, C): ");
            String origen = eligeTorre("Ingrese la torre de origen (A, B, C): ");
            String destino = eligeTorre("Ingrese la torre de destino (A, B, C): ");
            int torreOrigen = origen.charAt(0) - 'A';
            int torreDestino = destino.charAt(0) - 'A';
            if (torres[torreOrigen].isEmpty()) {
                System.out.println("La torre de origen está vacía. Intenta de nuevo.");
                continue;
            }
            if (torres[torreDestino].isEmpty()) {
                System.out.println("La torre de destino está vacía. Intenta de nuevo.");
                continue;
            }
            if (torreOrigen == torreDestino) {
                System.out.println("La torre de origen y destino son la misma. Intenta de nuevo.");
                continue;
            }
            moverDisco(torreOrigen, torreDestino);
        }
        System.out.println("¡Felicidades! Has completado el juego.");
    }
    private static boolean moverDisco(int origen, int destino) {
        if(!torres[origen].isEmpty() && (torres[destino].isEmpty() || torres[origen].peek() < torres[destino].peek())) {
            int disco = torres[origen].pop();
            torres[destino].push(disco);
            return true;
        } else {
            System.out.println("Movimiento inválido. Intenta de nuevo.");
            return false;
        }
    }
    public static String eligeTorre(String mensaje){
        String torre;
        do {
            System.out.println(mensaje);
            torre = sc.next().toUpperCase();
            if (!torre.equals("A") && !torre.equals("B") && !torre.equals("C")) {
                System.out.println("Torre inválida. Debe ser A, B o C.");
            }
        } while (!torre.equals("A") && !torre.equals("B") && !torre.equals("C"));
        return torre;
    }

    private static void mostrarSolucion(int numDiscos, char origen, char destino, char auxiliar) {
    }
    
    private static void elegirNumeroDiscos() {
        int n = 0;
        do {
        System.out.println("Ingresa el número de discos (3-8): ");
        n = leerEntero();
        if(n<3 || n>8){
            System.out.println("Numero invalido. Debe estar entre 3 y 8");
        } else {
            numDiscos = n;
            }
        } while (n < 3 || n > 8);
    }
    public static int leerEntero() {
        while (!sc.hasNextInt()) {
            System.out.println("Entrada inválida. Por favor, ingresa un número entero.");
            sc.next(); // Descarta la entrada inválida
        }
        return sc.nextInt();
    }
    public static void mostrarSolucion(){
    }
}

