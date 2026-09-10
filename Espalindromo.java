
import java.util.ArrayDeque;
import java.util.Deque;

public class Espalindromo {
    public static void main(String[] args) {
        String cadena = args.length > 0 ? args[0] : "ama";
        boolean Espalindromo = Espalindromo(cadena);
        System.out.println("Cadena: " + cadena);
        System.out.println("¿Es palíndromo? " + Espalindromo);
    }
    public static boolean Espalindromo(String cadena) {
        Deque<Character> pila = new ArrayDeque<>();
        for (char c : cadena.toCharArray()) {
            pila.push(c);
        }
        char[] cadenaInvertida = new char[cadena.length()];
        while (!pila.isEmpty()) {
            cadenaInvertida[cadena.length() - pila.size()] = pila.pop();
        }
        String cadenaInvertidaStr = new String(cadenaInvertida);
        System.out.println("Cadena invertida: " + cadenaInvertidaStr);
        return cadena.equals(cadenaInvertidaStr);
    }
}
