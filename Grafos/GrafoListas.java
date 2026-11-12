import java.util.ArrayList;
import java.util.LinkedList;

public class GrafoListas {

    ArrayList<Nodos> nodos;

    public GrafoListas(String texto){
        crearListaVertices(Texto);
    }
    
    private void creaListaVertices(String origen) {
    String[] lineas = origen.split("\\R");
    nodos = new ArrayList<>();
   for (String linea : lineas) {
        String arista = linea.trim();
        if (arista.startsWith("{")) {
            arista = arista.substring(1).trim();
        }
        int etiqueta = arista.indexOf('[');
        if (etiqueta >= 0) {
            arista = arista.substring(0, etiqueta);
        }
        String[] extremos = arista.split("--", 2);
        if (extremos.length == 2) {
            agregarVertice(extremos[0].trim());
            agregarVertice(extremos[1].trim());
        }
     }
 }

 private void agregarVertice(String nombre) {
    for (Nodos nodo : nodos) {
        if (!nodo.getNombre().equals(nombre)) {
            nodos.add(new Nodos(nombre));{

                }
            }
        }

}
