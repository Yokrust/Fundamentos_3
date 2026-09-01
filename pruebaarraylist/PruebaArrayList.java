package pruebaarraylist;
import java.util.ArrayList;

public class PruebaArrayList {

    public static void main(String[] args) {
        ArrayList<String> personalities = new ArrayList<String>();
        personalities.add("Ada Lovelace");
        personalities.add("Alan Turing");
        personalities.add("Grace Hopper");

        String name = personalities.get(0);
        System.out.println(name);
        System.out.println("The size of the array list is: " + personalities.size());

        personalities.set(1, "Margaret Hamilton");
        System.out.println(personalities);
    }
}
