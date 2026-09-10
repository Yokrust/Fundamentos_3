package pruebaarraylist;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;


public class nombresArrayList {
    public static class nameCount {
    String name;
    int count;

    public nameCount(String name){
        this.count = 1;
        this.name = name;
    }
    public void incrementCount(){
        this.count++;
    }
    public String getName(){
        return this.name + " (" + this.count + ")";
    }
}
    public static void main(String[] args) {
        Path filePath = Paths.get("/Users/yael/Documents/Fundamentos3/Fundamentos_3"
                + "/pruebaarraylist/listado.txt");
    
        ArrayList<String> namesList = readNamesFromFile(filePath.toString());
        int FIRSTNAME = 0;
        int FLASTNAME = 1;
        int SLASTNAME = 2;

        printNames(namesList);

        System.out.println("---------------------");

        ArrayList<String> uniqueNames = countNames(namesList, FIRSTNAME);
        printNames(uniqueNames);

        System.out.println("---------------------");

        ArrayList<String> uniqueFLNames = countNames(namesList, FLASTNAME);
        printNames(uniqueFLNames);

        ArrayList<String> uniqueSLNames = countNames(namesList, SLASTNAME);
        printNames(uniqueSLNames);
        
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
    private static ArrayList<String> countNames(ArrayList<String> namesList, int pos) {
        ArrayList<nameCount> countedNames = new ArrayList<>();
        for(String name : namesList){
            boolean found = false;
            String[] splitName = name.split(" ");
            for(nameCount nc : countedNames){
                if(nc.name.equals(splitName[pos])){
                    nc.incrementCount();
                    found = true;
                    break;
                }
            }
            if(!found){
                countedNames.add(new nameCount(splitName[pos]));
            }
        }
        ArrayList<String> result = new ArrayList<>();
        for(nameCount nc : countedNames){
            result.add(nc.getName());
        }
        return result;
     }
}
