package Grafos;
class GrafoArrays {
    private String[] vertices;
    private int numVertices;
    private int[][] matrizAdyacencia;
    private java.util.ArrayList<String> arcosDFS;
    private java.util.ArrayList<String> arcosBFS;

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
                arista = arista.substring(0, llave);
            }
            String[] extremos = arista.split("--", 2);
            if (extremos.length == 2) {
                agregarVertice(extremos[0].trim());
                agregarVertice(extremos[1].trim());
            }
        }
    }

    public void populateMatrizAdyacencia(String origen) {
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
            String vertice = vertices[i];
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
            matrizAdyacencia[indice2][indice1] = peso;
            System.out.println("Arista agregada: " + vertice1 + " -- " + vertice2 + " (peso: " + peso + ")");
        }
    }
    public void DFS(String verticeInicial, String VerticeFinal){
        boolean[] visitado = new boolean[numVertices];
        int indiceInicial = obtenerIndiceVertice(verticeInicial);
        int indiceFinal = obtenerIndiceVertice(VerticeFinal);
        if (indiceInicial == -1 || indiceFinal == -1) {
            System.out.println("Uno o ambos vértices no existen en el grafo.");
            return;
        }


        arcosDFS = new java.util.ArrayList<>();
        System.out.println("Recorrido DFS desde " + verticeInicial + " hasta " + VerticeFinal + ":");
        java.util.Stack<int[]> stack = new java.util.Stack<>();
        stack.push(new int[]{-1, indiceInicial});
        while (!stack.isEmpty()) {
            int[] par = stack.pop();
            int indicePadre = par[0];
            int indiceActual = par[1];
            if (!visitado[indiceActual]) {
                visitado[indiceActual] = true;
                System.out.print(vertices[indiceActual] + " ");
                if (indicePadre != -1) {
                    arcosDFS.add(vertices[indicePadre] + " -- " + vertices[indiceActual]);
                }
                if (indiceActual == indiceFinal) {
                    System.out.println("\nSe ha alcanzado el vértice final: " + VerticeFinal);
                    return;
                }
            }
            //agregar vecinos no visitados al stack
            for (int i = numVertices - 1; i >= 0; i--) {
                if (matrizAdyacencia[indiceActual][i] != 0 && !visitado[i]) {
                    stack.push(new int[]{indiceActual, i});
                }
            }
        }

    }
    public void BFS(String verticeInicial, String VerticeFinal){
        boolean[] visitado = new boolean[numVertices];
        int indiceInicial = obtenerIndiceVertice(verticeInicial);
        int indiceFinal = obtenerIndiceVertice(VerticeFinal);
        if (indiceInicial == -1 || indiceFinal == -1) {
            System.out.println("Uno o ambos vértices no existen en el grafo.");
            return;
        }
        arcosBFS = new java.util.ArrayList<>();
        System.out.println("Recorrido BFS desde " + verticeInicial + " hasta " + VerticeFinal + ":");
        java.util.Queue<int[]> queue = new java.util.LinkedList<>();
        queue.add(new int[]{-1, indiceInicial});
        while (!queue.isEmpty()) {
            int[] par = queue.poll();
            int indicePadre = par[0];
            int indiceActual = par[1];
            if (!visitado[indiceActual]) {
                visitado[indiceActual] = true;
                System.out.print(vertices[indiceActual] + " ");
                if (indicePadre != -1) {
                    arcosBFS.add(vertices[indicePadre] + " -- " + vertices[indiceActual]);
                }
                if (indiceActual == indiceFinal) {
                    System.out.println("\nSe ha alcanzado el vértice final: " + VerticeFinal);
                    return;
                }
            }
            //agregar vecinos no visitados a la cola
            for (int i = 0; i < numVertices; i++) {
                if (matrizAdyacencia[indiceActual][i] != 0 && !visitado[i]) {
                    queue.add(new int[]{indiceActual, i});
                }
            }
        }
    }
    //contar los saltos de BFS Y DFS
    public static int contarSaltos(GrafoArrays grafo, String verticeInicial, String verticeFinal) {
        boolean[] visitado = new boolean[grafo.numVertices];
        int indiceInicial = grafo.obtenerIndiceVertice(verticeInicial);
        int indiceFinal = grafo.obtenerIndiceVertice(verticeFinal);
        if (indiceInicial == -1 || indiceFinal == -1) {
            System.out.println("Uno o ambos vértices no existen en el grafo.");
            return -1;
        }
        java.util.Queue<Integer> queue = new java.util.LinkedList<>();
        queue.add(indiceInicial);
        int saltos = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int indiceActual = queue.poll();
                if (!visitado[indiceActual]) {
                    visitado[indiceActual] = true;
                    if (indiceActual == indiceFinal) {
                        return saltos;
                    }
                    for (int j = 0; j < grafo.numVertices; j++) {
                        if (grafo.matrizAdyacencia[indiceActual][j] != 0 && !visitado[j]) {
                            queue.add(j);
                        }
                    }
                }
            }
            saltos++;
        }
        return -1;
    }

    public String caminoMasCortoBFS(String verticeInicial, String verticeFinal){
        int inicio = obtenerIndiceVertice(verticeInicial);
        int destino = obtenerIndiceVertice(verticeFinal);
        if(inicio == -1 || destino == -1){
            System.out.println("Uno o ambos vértices no existen en el grafo.");
            return null;
        }
        int[] distancia = new int[numVertices];
        int[] anterior = new int[numVertices];
        boolean[] visitado = new boolean[numVertices];
        java.util.Arrays.fill(distancia, Integer.MAX_VALUE);
        java.util.Arrays.fill(anterior, -1);
        distancia[inicio] = 0;
        for(int paso = 0; paso < numVertices; paso ++){
            int actual = -1;
            for(int i = 0; i < numVertices; i++){
                if(!visitado[i] && distancia[i] != Integer.MAX_VALUE && (actual == -1 || distancia[i] < distancia[actual])){
                    actual = i;
                }
            }
            if(actual == -1){
                break;
            }
            if(actual == destino){
                break;
            }
            visitado[actual] = true;
            for(int vecino = 0; vecino < numVertices; vecino++){
                int peso = matrizAdyacencia[actual][vecino];
                if(peso != 0 && !visitado[vecino] && distancia[actual] <= Integer.MAX_VALUE - peso){
                    int nuevaDistancia = distancia[actual] + peso;
                    if(nuevaDistancia < distancia[vecino]){
                        distancia[vecino] = nuevaDistancia;
                        anterior[vecino] = actual;
                    }
                }
            }
        }
        if(distancia[destino] == Integer.MAX_VALUE){
            System.out.println("No hay camino desde " + verticeInicial + " hasta " + verticeFinal);
            return null;
        }
        java.util.List<String> camino = new java.util.ArrayList<>();
        int actual = destino;
        while(actual != -1){
            camino.add(vertices[actual]);
            actual = anterior[actual];
        }
        return "el camino mas corto de " + verticeInicial + " hasta " + verticeFinal + " es: " + camino.toString();
    }

    public void despliegaArcos() {
        System.out.println("Arcos recorridos en DFS:");
        if (arcosDFS != null) {
            for (String arco : arcosDFS) {
                System.out.println(arco);
            }
        }
        System.out.println("Arcos recorridos en BFS:");
        if (arcosBFS != null) {
            for (String arco : arcosBFS) {
                System.out.println(arco);
            }
        }

        int totalArcosPosibles = numVertices * (numVertices - 1) / 2;
        int totalArcosOcupados = 0;
        for (int i = 0; i < numVertices; i++) {
            for (int j = i + 1; j < numVertices; j++) {
                if (matrizAdyacencia[i][j] != 0) {
                    totalArcosOcupados++;
                }
            }
        }


        System.out.println("Número total de vértices: " + numVertices);
        System.out.println("Número total de arcos posibles: " + totalArcosPosibles);
        System.out.println("Número total de arcos ocupados: " + totalArcosOcupados);

        if (arcosDFS != null && arcosBFS != null) {
            if (arcosDFS.size() < arcosBFS.size()) {
                System.out.println("arcosDFS tiene menos arcos que arcosBFS (" + arcosDFS.size() + " vs " + arcosBFS.size() + ")");
            } else if (arcosBFS.size() < arcosDFS.size()) {
                System.out.println("arcosBFS tiene menos arcos que arcosDFS (" + arcosBFS.size() + " vs " + arcosDFS.size() + ")");
            } else {
                System.out.println("arcosDFS y arcosBFS tienen el mismo número de arcos (" + arcosDFS.size() + ")");
            }
        }
    }
}

public class grafos {
    public static void main(String[] args) {
        String texto = cargaArchivo("/Users/yael/Documents/Fundamentos3/Fundamentos_3/Grafos/grafo_salon2.txt");
        System.out.println("Contenido del archivo:\n" + texto);
        GrafoArrays grafo = new GrafoArrays(texto);
        grafo.populateMatrizAdyacencia(texto);
        grafo.display();
        grafo.DFS("Zahid", "Jose");
        grafo.DFS("Jose", "Zahid");
        grafo.BFS("Zahid", "Jose");
        System.out.println("Saltos de BFS: " + GrafoArrays.contarSaltos(grafo, "Zahid", "Jose"));
        System.out.println("Saltos de DFS: " + GrafoArrays.contarSaltos(grafo, "Jose", "Zahid"));
        grafo.despliegaArcos();
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
