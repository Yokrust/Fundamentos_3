package pruebaarraylist;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

public class nombresArrayList {
    public static void main(String[] args) {
        Path filePath = Paths.get("/Users/yael/Documents/Fundamentos3/Fundamentos_3"
                + "/pruebaarraylist/listado.txt");
        ArrayList<String> namesList = readNamesFromFile(filePath.toString());
        printNames(namesList);


        ContaryRepetidos(namesList);
    }
    public static void ContaryRepetidos(ArrayList<String> namesList){
        ArrayList<String> uniqueNames = new ArrayList<String>();
        ArrayList<Integer> Contadores = new ArrayList<Integer>();
        for(String linea : namesList){
            String primerNombre = linea.trim().split(" ")[0];
            int indice = uniqueNames.indexOf(primerNombre);
            if(indice != -1){
                int cantidadActual = Contadores.get(indice);
                Contadores.set(indice, cantidadActual + 1);
            } else {
                uniqueNames.add(primerNombre);
                Contadores.add(1);
            }
        }
        System.out.println("Nombres únicos y sus repeticiones:");
        for(int i = 0; i < uniqueNames.size(); i++){
            System.out.println(uniqueNames.get(i) + ": " + Contadores.get(i) + " Veces");
        }
    }

    public static void printNames(ArrayList<String> namesList){
        for(String name : namesList){
            System.out.println(name);
        }

    }

    public static ArrayList<String> readNamesFromFile(String filePath){
        ArrayList<String> names = new ArrayList<String>();
        try{
            Stream<String> lines = Files.lines(Paths.get(filePath));
            for(String line : (Iterable<String>) lines::iterator){
                names.add(line);
            }
            lines.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return names;
    }
}
