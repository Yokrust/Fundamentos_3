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
