package Recursividad;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

class Nodo {
    int valor;
    Nodo izquierda;
    Nodo derecha;
    public Nodo(int valor) {
        this.valor = valor;
        this.izquierda = null;
        this.derecha = null;
    }
}

class ArbolBinario {
    Nodo raiz;
    public ArbolBinario(){
        this.raiz = null;    
    }
    public void insertar(int valor){
        raiz = insertarRecursivo(raiz, valor);
    }
    private Nodo insertarRecursivo(Nodo nodo, int valor){
        if (nodo == null){
            return new Nodo(valor);
        }
        if (valor < nodo.valor) {
            nodo.izquierda = insertarRecursivo(nodo.izquierda, valor);
        } else if (valor > nodo.valor) {
            nodo.derecha = insertarRecursivo(nodo.derecha, valor);
        }
        return nodo;
    }
    public void preOrden(){
        preOrdenRecursivo(raiz);
    }
    private void preOrdenRecursivo(Nodo nodo){
        if (nodo != null) {
            System.out.println(nodo.valor + "");
            preOrdenRecursivo(nodo.izquierda);
            preOrdenRecursivo(nodo.derecha);
            
        }
    }
}

public class Recursividad {

    static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] arr = {22,21, 22, 23, 24, 25, 26, 27, 28, 29, 30};
        int indice = buscar(arr, 30, 0, arr.length - 1);
        System.out.println("El elemento 30 se encuentra en el índice: " + indice);
        /* int[] arr = {7, 15, 30, 45, 50};
        int x = 30;
        int indice = buscar(arr, x, 0, arr.length - 1);
        
        long startTime = System.nanoTime();

        for(int i = 0; i < arr.length; i++){
            long sTime = System.nanoTime();
            System.out.println("Fibonacci de " + arr[i] + ": " + fibonacciIlogico(arr[i]));
            long eTime = System.nanoTime();
            System.out.println("Tiempo de ejecucion del ciclo: " + (eTime - sTime)/1000000.0 +"  ms \n");
        }
        long endTime = System.nanoTime();
        System.out.println("Tiempo de ejecucion: " + (endTime - startTime)/1000000.0 +"  ms");
 */
    }

    private static int  calcularfactorial(int numero){
        if(numero == 0){
            return 1;
        }
        else{
            return numero * calcularfactorial(numero-1);
        }
    }

    public static int fibonacciIlogico(int n){
        if (n == 0){
            return 0;
        }
        if (n == 1){
            return 1;
        }
        else {
            return fibonacciIlogico(n -1) + fibonacciIlogico(n - 2);
        }
    }

    static Map<Integer, Long> memo = new HashMap<>();

    public static long fib(int n){
        if (n <= 1) return n;
        Long guardado = memo.get(n);
        if (guardado != null) return guardado;
        long resultado = fib(n - 1) + fib(n - 2);
        memo.put(n, resultado);
        return resultado;
    }
    static int buscar(int[] a, int x, int ini, int fin) {
        if (ini > fin) return -1;
        int mid = (ini + fin) >>> 1;
        if (a[mid] == x) return mid;
        if (x < a[mid]) return buscar(a, x, ini, mid - 1);
        return buscar(a, x, mid + 1, fin);
    
    }
    /*public class towerofhanoi {
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
        */
}