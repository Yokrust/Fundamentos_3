import java.util.*;

public class GrafosArray {
    private String[] vertices;
    private int numVertices;
    private int[][] matrizAdyacencia;
    private Queue<String> arcosDFS = new ArrayDeque<>();
    private Queue<String> arcosBFS = new ArrayDeque<>();

    public GrafosArray(String origen) {
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

    private void agregarVertice(String vertice) {
        for (int i = 0; i < numVertices; i++) {
            if (vertices[i].equals(vertice)) {
                return;
            }
        }
        vertices[numVertices] = vertice;
        numVertices++;
    }

    private void populateMatrizAdyacencia(String origen){
        String[] lineas = origen.split("\\R");
        String labelPeso = "";
        for (String vertice : vertices){

            int indxV = obtenerIndiceVertice(vertice);
            for (String linea : lineas){
                String arista = linea.trim();
                if (arista.startsWith("{")) {
                    arista = arista.substring(1).trim();
                }
                int etiqueta = arista.indexOf('[');
                if (etiqueta >= 0) {
                    labelPeso = arista.substring(etiqueta);
                    arista = arista.substring(0, etiqueta);
                }
                String[] extremos = arista.split("--", 2);
                if (extremos[0].equals(vertice)) {
                    int peso = Integer.parseInt((labelPeso.charAt(labelPeso.indexOf("\"") + 1)) + "");
                    matrizAdyacencia[indxV][obtenerIndiceVertice(extremos[1])] = peso;
                    matrizAdyacencia[obtenerIndiceVertice(extremos[1])][indxV] = peso;
                }
            }
        }
    }

    public void display() {
        System.out.println("Vértices del grafo:");
        displayVertices();
    }

    private void displayVertices() {
        for (int i = 0; i < numVertices; i++) {
            System.out.println(vertices[i]);
        }
    }

    public void displayMatrizAdyacencia(){
        System.out.println("Matriz de adyacencia del Grafo:");
        System.out.print("    ");
        for (int i =0; i<numVertices;i++){
            System.out.print(vertices[i].substring(0,3)+ "  ");
        }
        System.out.println();
        displayVerticePonderado();
    }

    private void displayVerticePonderado(){
        for (int i = 0; i < numVertices; i++) {
            System.out.print(vertices[i].substring(0,3)+ "  ");
            for(int j = 0; j < numVertices; j++){
                System.out.print(matrizAdyacencia[i][j] + "    ");
            }
            System.out.println();
        }
        System.out.print("   ");

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
            matrizAdyacencia[indice2][indice1] = peso; // Para Grafos no dirigidos
        }
    }

    public void BFS(String verticeInicial, String verticeFinal){
        boolean[] visitado = new boolean[numVertices];
        this.arcosBFS.clear();
        int indxInicial = obtenerIndiceVertice(verticeInicial);
        int indxFinal = obtenerIndiceVertice(verticeFinal);
        if(indxInicial == -1 || indxFinal == -1){
            System.out.println("Uno o ambos vertices no exixten en el grafo " + " con BFS:");
            return;
        }
        System.out.println("Recorrido desde " + verticeInicial + " a " + verticeFinal + " con BFS:");
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(indxInicial);

        while(!queue.isEmpty()){

            int indxActual = queue.poll();

            if(!visitado[indxActual]){

                visitado[indxActual] = true;
                this.arcosBFS.add(vertices[indxActual]);
                System.out.println(vertices[indxActual] + " ");

                if (indxActual == indxFinal){

                    System.out.println("\n Alcanzamos el vertice final.");
                    return;

                }
            }
            //Agregar Vecinos del indice actual
            for(int i = 0; i < numVertices; i++){
                if(matrizAdyacencia[indxActual][i] != 0 && !visitado[i]){
                    queue.add(i);
                }
            }
        }

    }

    public void DFS(String verticeInicial, String verticeFinal){
        boolean[] visitado = new boolean[numVertices];
        this.arcosDFS.clear();
        int indxInicial = obtenerIndiceVertice(verticeInicial);
        int indxFinal = obtenerIndiceVertice(verticeFinal);
        if(indxInicial == -1 || indxFinal == -1){
            System.out.println("Uno o ambos vertices no exixten en el grafo.");
            return;
        }
        System.out.println("Recorrido desde " + verticeInicial + " a " + verticeFinal + " con DFS:");
        Stack<Integer> stack = new Stack<>();
        stack.push(indxInicial);

        while(!stack.isEmpty()){

            int indxActual = stack.pop();

            if(!visitado[indxActual]){

                visitado[indxActual] = true;
                this.arcosDFS.add(vertices[indxActual]);
                System.out.println(vertices[indxActual] + " ");

                if (indxActual == indxFinal){

                    System.out.println("\n Alcanzamos el vertice final.");
                    return;

                }
            }
            //Agregar Vecinos del indice actual
            for(int i = 0; i < numVertices; i++){
                if(matrizAdyacencia[indxActual][i] != 0 && !visitado[i]){
                    stack.push(i);
                }
            }
        }
    }

    public void despliegaArcos(){
        ArrayList<Queue<String>> pasos = new ArrayList<>();
        int[] pasosPonderados = new int[2];
        pasos.add(arcosBFS);
        pasos.add(arcosDFS);

        int arBfs = arcosBFS.size() - 1;
        int verticesBfs = arcosBFS.size();

        int arDfs = arcosDFS.size() - 1;
        int verticesDfs = arcosDFS.size();

        for(Queue<String> p : pasos){
            System.out.println("Arcos para " + ((p == arcosBFS) ? "BFS":"DFS") + ":");
            if (p.isEmpty()){
                System.out.println("La lista de arcos esta vacia :(");
                continue;
            }

            String vertcieIzq = p.poll();
            while(!p.isEmpty()){

                int pesoArco = matrizAdyacencia[obtenerIndiceVertice(vertcieIzq)][obtenerIndiceVertice(p.peek())];

                pasosPonderados[pasos.indexOf(p)] += pesoArco;
                System.out.printf("%s - %d - %s \n", vertcieIzq, pesoArco, p.peek());

                vertcieIzq = p.poll();
            }
        }

        System.out.println("Numero de arcos en DFS: " + arDfs);
        System.out.println("Numero de arcos en BFS: " + arBfs);

        System.out.printf("Busqueda mas optima: %s \n", ((arDfs < arBfs) ? "DFS":"BFS"));

        System.out.println("Peso de arcos en DFS: " + pasosPonderados[0]);
        System.out.println("Peso de arcos en BFS: " + pasosPonderados[1]);

        System.out.printf("Busqueda mas optima: %s \n", ((pasosPonderados[0] < pasosPonderados[1]) ? "DFS":"BFS"));

    }

    public String caminoMasCorto(String verticeInicial, String verticeFinal){
        int inicio = obtenerIndiceVertice(verticeInicial);
        int destino = obtenerIndiceVertice(verticeFinal);

        if (inicio == -1 || destino == -1){
            return "uno o ambos vertices no existen en el grafo";
        }

        int[] distancia = new int[numVertices];
        int[] anterior = new int[numVertices];
        boolean[] visitado = new boolean[numVertices];

        Arrays.fill(distancia, Integer.MAX_VALUE);
        Arrays.fill(anterior, -1);
        distancia[inicio] = 0;

        for(int paso = 0; paso <= numVertices; paso++){
            int actual = -1;
            for(int i = 0; i < numVertices; i++){
                if(!visitado[i] && distancia[i] != Integer.MAX_VALUE && (actual == -1 || distancia[i] < distancia[actual])){
                    actual = i;
                }
                if(actual == -1){
                    break;
                }
                if( actual == destino){
                    break;
                }
                visitado[actual] = true;
                for(int vecino = 0; vecino < numVertices; vecino++){
                    int peso = matrizAdyacencia[actual][vecino];
                    if( peso > 0 && !visitado[vecino] && distancia[actual] <= Integer.MAX_VALUE - peso){
                        int nuevaDistancia = distancia[actual] + peso;
                        if(nuevaDistancia < distancia[vecino]){
                            distancia[vecino] = nuevaDistancia;
                            anterior[vecino] = actual;
                        }
                    }
                }
            }
        }

        if(distancia[destino] == Integer.MAX_VALUE){
            return "No existe un camino entre " + verticeInicial + " y " + verticeFinal + ".";
        }
        ArrayList<String> camino = new ArrayList<>();
        int actual = destino;
        while(actual != -1){
            camino.addFirst(vertices[actual]);
            actual = anterior[actual];
        }
        return  "El camino mas corto de " + verticeInicial + " a " + verticeFinal + " es: " + String.join("->", camino);
    }
}
