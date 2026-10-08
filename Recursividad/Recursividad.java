package Recursividad;

public class Recursividad {
    public static void main(String[] args) {
        long startTime = System.nanoTime();
        System.out.println("Fibonacci de 50: " + fibonacci(50));
        //System.out.println("Factorial de 3: " + factorial(31));
        long endTime = System.nanoTime();
        System.out.println("Tiempo de ejecución: " + (endTime - startTime) / 1000000.0 + " milisegundos");
    }
    public static int factorial(int n) {
        if (n == 0) {
            return 1;
        } else {
            return n * factorial(n - 1);
        }
    }
    public static int fibonacci(int n) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else {
            return fibonacci(n - 1) + fibonacci(n - 2);
        }
    }
}
