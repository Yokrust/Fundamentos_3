import java.util.ArrayList;

public class Nodos {
    String nombre;
    int peso;
    ArrayList<Nodos> adyacentes;

    public Nodos(String nombre, int peso){
        this.nombre = nombre;
        this.peso = peso;
        this.adyacentes = new ArrayList<>();
    }

    public void agregarAdyacente(Nodos nodo){
        adyacentes.add(nodo);
    }

    public void display(){
        System.out.println(nombre);
        System.out.println("|Adyacentes: ");
        for(Nodos n : adyacentes){
            System.out.println(n.nombre + " : " + peso + " ");
        }
        System.out.println();
    }
    public String getNombre() {
        return nombre;
    }

    public  ArrayList<Nodos> getAdyacentes() {
        return adyacentes;
    }

    public int getPeso() {
        return peso;
    }
}
