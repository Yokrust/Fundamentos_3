package Grafos;
class GrafoArrays {
    private String[] vertices;
    private int numVertices;
    private int[][] matrizAdyacencia;

    public GrafoArrays(String origen) {
        creaTablaVertices(origen);
        matrizAdyacencia = new int[numVertices][numVertices];
        populateMatrizAdyacencia(origen);
    }

    private void creaTablaVertices(String origen) {
        String[] lineas = origen.split("\\R");
        vertices = new String[lineas.length * 2];
        numVertices = 0;
        for (String linea : lineas) {
            String arista = linea.trim();
            if (arista.startsWith("{")) {
                arista = arista.substring(1).trim();
            }
            int llave = arista.indexOf('[');
            if (llave >= 0) {
                arista = arista.substring(llave + 1).trim();
            }
            String[] extremos = arista.split("--", 2);
            if (extremos.length == 2) {
                agregarVertice(extremos[0].trim());
                agregarVertice(extremos[1].trim());
            }
        }
        // Crear método para agregar aristas después de crear la tabla de vértices populateMatrizAdyacencia(origen);
             // Dividir el archivo de texto en lineas y procesar cada línea para agregar aristas 
             // Cada linea se separa en dos vértices y un peso, luego se llama al método agregarArista para agregarlas a la matriz de adyacencia
    }

    private void populateMatrizAdyacencia(String origen) {
        String[] lineas = origen.split("\\R");
        for (String linea : lineas) {
            String arista = linea.trim();
            if (arista.startsWith("{")) {
                arista = arista.substring(1).trim();
            }
            int etiqueta = arista.indexOf('[');
            int peso = 1; // Peso por defecto
            if (etiqueta >= 0) {
                int cierre = arista.indexOf(']', etiqueta);
                if (cierre > etiqueta) {
                    String pesoStr = arista.substring(etiqueta + 1, cierre).replaceAll("[^0-9-]", "");
                    if (!pesoStr.isEmpty()) {
                        try {
                            peso = Integer.parseInt(pesoStr);
                        } catch (NumberFormatException e) {
                            System.out.println("Peso inválido en la línea: " + linea);
                        }
                    }
                }
                arista = arista.substring(0, etiqueta).trim();
            }
            String[] extremos = arista.split("--", 2);
            if (extremos.length == 2 && !extremos[0].trim().isEmpty() && !extremos[1].trim().isEmpty()) {
                agregarArista(extremos[0].trim(), extremos[1].trim(), peso);
            }
        }
    }
/*
if (etiqueta >= 0) {
    int cierre = arista.indexOf(']', etiqueta);
    if (cierre > etiqueta) {
        String pesoStr = arista.substring(etiqueta + 1, cierre).replaceAll("[^0-9-]", "");
        if (!pesoStr.isEmpty()) {
            try {
                peso = Integer.parseInt(pesoStr);
            } catch (NumberFormatException e) {
                System.out.println("Peso inválido en la línea: " + linea);
            }
        }
    }
    arista = arista.substring(0, etiqueta).trim();
}
 */
    private void displayMatriz() {
        System.out.println("Matriz de adyacencia:");
        System.out.print("\t");
        for (int i = 0; i < numVertices; i++) {
            System.out.print(vertices[i] + "\t");
        }
        System.out.println();
        for (int i = 0; i < numVertices; i++) {
            System.out.print(vertices[i] + "\t");
            for (int j = 0; j < numVertices; j++) {
                System.out.print(matrizAdyacencia[i][j] + "\t");
            }
            System.out.println();
        }
    }


    private void agregarVertice(String vertice) {
        for (int i = 0; i < numVertices; i++) {
            if (vertices[i].equals(vertice)) {
                return;
            }
        }
        vertices[numVertices] = vertice;
        numVertices++;
    }

    public void display() {
        System.out.println("Vértices del grafo:");
        displayVertices();
        System.out.println();
        displayMatriz();   
    }

    private void displayVertices() {
        for (int i = 0; i < numVertices; i++) {
            System.out.println(vertices[i]);
        }
    }
    private int obtenerIndiceVertice(String vertice) {
        for (int i = 0; i < numVertices; i++) {
            if (vertices[i].equals(vertice)) {
                return i;
            }
        }
        return -1;
    }
    private void agregarArista(String vertice1, String vertice2, int peso) {
        int indice1 = obtenerIndiceVertice(vertice1);
        int indice2 = obtenerIndiceVertice(vertice2);
        if (indice1 >= 0 && indice2 >= 0) {
            matrizAdyacencia[indice1][indice2] = peso;
            matrizAdyacencia[indice2][indice1] = peso; // Para grafos no dirigidos
            System.out.println("Arista agregada: " + vertice1 + " -- " + vertice2 + " (peso: " + peso + ")");
        }
    }
}

public class grafos {
    public static void main(String[] args) {
        String texto = cargaArchivo("/Users/yael/Documents/Fundamentos3/Fundamentos_3/Grafos/grafos.txt");
        System.out.println("Contenido del archivo:\n" + texto);
        GrafoArrays grafo = new GrafoArrays(texto);
        grafo.display();
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
