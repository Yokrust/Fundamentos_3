public class grafos {
    public static void main(String[] args) {
        String texto = cargaArchivo("/Users/yael/Documents/Fundamentos3/Fundamentos_3/Grafos/grafos.txt");
        System.out.println("Contenido del archivo:\n" + texto);
        GrafosArray grafo = new GrafosArray(texto);
        grafo.display();
        grafo.displayMatrizAdyacencia();

        grafo.DFS("Luna", "Sergio");
        grafo.BFS("Luna", "Sergio");

        System.out.println("Metodo despliegaArcos");
        grafo.despliegaArcos();

        String caminoDijjstra = grafo.caminoMasCorto("Luna", "Sergio");
        System.out.println(caminoDijjstra);
    }

    public static String cargaArchivo(String ruta) {
        String contenido = "";
        try {
            java.io.File archivo = new java.io.File(ruta);
            java.util.Scanner scanner = new java.util.Scanner(archivo);
            while (scanner.hasNextLine()) {
                contenido += scanner.nextLine() + "\n";
            }
            scanner.close();
        } catch (java.io.FileNotFoundException e) {
            System.out.println("Archivo no encontrado: " + ruta);
        }
        return contenido;
    }

}
