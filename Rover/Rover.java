package Rover;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Rover {
    public static void main(String[] args) {
        String cadena = args.length > 0 ? args[0] : "/Users/yael/Documents/Fundamentos3/Fundamentos_3/Rover/path1.txt";
        ArrayList<String> lineas = leerArchivos(cadena);
        Queue<String> queue = addToQueue(lineas);
        travelQueue(queue);
    }
    public static Queue<String> addToQueue(ArrayList<String> lineas) {
        Queue<String> queue = new LinkedList<>();
        for (String linea : lineas) {
            queue.add(linea);
        }
        return queue;
    }

    public static void travelQueue(Queue<String> queue) {
        while (!queue.isEmpty()) {
            String linea = queue.poll();
            if (linea != null) {
                if(linea.toUpperCase().equals("A")){
                    System.out.print("^");
                } else if(linea.toUpperCase().equals("D")) {
                    System.out.print("v");
                } else if(linea.toUpperCase().equals("S")){
                    System.out.print("-");
                }
            }
        }
    }

    public static ArrayList<String> leerArchivos(String nombreArchivo) {
        ArrayList<String> lineas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                lineas.add(linea);
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
        return lineas;
    }
    
}
