//Author: Yael Lomas
package ExpedientePersonas;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

class Personas{
    private String nombre;
    private String expediente;
    private int edad;

    public Personas(String nombre, String expediente, int edad) {
        this.nombre = nombre;
        this.expediente = expediente;
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public String getExpediente() {
        return expediente;
    }

    public int getEdad() {
        return edad;
    }

    public void mostrarDatos() {
        System.out.println("Nombre: " + nombre);
        System.out.println("Expediente: " + expediente);
        System.out.println("Edad: " + edad);
    }
}

public class ExpedientePersonas {

    static void main(String[] args) {
        String cadena = args.length > 0 ? args[0] : "C:\\Users\\uriel\\Documents\\Fundamentos_3\\fundamentos_3\\ExpedientesPersonas\\listado_personas_expediente.csv";
        ArrayList<Personas> personas = cargarPersonas(cadena);
        if (personas.size() > 10) {
            Personas personaEncontrada = personas.get(10);
            personaEncontrada.mostrarDatos();
        } else {
            System.out.println("No hay suficientes registros en el archivo.");
        }
        String[] nombresABuscar = {"Alan Aldama Andre", "Isabel Domínguez Ochoa", "Ernesto Ozuna Ramirez", "Ada Pino López", "Bruno Díaz Hernández","Luis Caro Durazo"};
        buscarVariasPersonasEnLista(personas, nombresABuscar);
        System.out.println("--------------------------------------------------");
        HashMap<String,Personas> mapaPersonas = new HashMap<>();
        for (Personas persona : personas) {
            mapaPersonas.put(persona.getNombre(), persona);
        }
        Personas personaEncontradaEnHashMap = buscarPersonaEnHashMap(mapaPersonas,"Alan Aldama Andre" );
        buscarVariasPersonasEnHashMap(mapaPersonas, nombresABuscar);

    }

    public static Personas buscarPersonaEnHashMap(HashMap<String, Personas> mapaPersonas, String nombre) {
        return mapaPersonas.get(nombre);
    }

    public static void buscarVariasPersonasEnHashMap(java.util.HashMap<String,Personas> mapaPersonas, String[] nombres) {
        long startTime = System.nanoTime();
        for(String nombre: nombres){
            Personas personaEncontrada = buscarPersonaEnHashMap(mapaPersonas, nombre);
            if (personaEncontrada != null){
                personaEncontrada.mostrarDatos();
            } else {
                System.out.println("No se encontro a la persona con nombre: " + nombre);
            }
        }
        long endTime = System.nanoTime();
        System.out.println("Tiempo de busqueda en HashMap: " + (endTime-startTime)/1000000.0 + " ms");

    }

    public static Personas buscarPersonaEnLista(ArrayList<Personas> personas, String nombre) {
        for (Personas persona : personas) {
            if (persona.getNombre().equalsIgnoreCase(nombre)) {
                return persona;
            }
        }
        return null; 
    }

    public static void buscarVariasPersonasEnLista(ArrayList<Personas> personas, String[] nombres) {
        long startTime = System.nanoTime();
        for (String nombre : nombres) {
            Personas personaEncontrada = buscarPersonaEnLista(personas, nombre);
            if (personaEncontrada != null) {
                personaEncontrada.mostrarDatos();
            } else {
                System.out.println("No se encontró a la persona con nombre: " + nombre);
            }
        }
        long endTime = System.nanoTime();
        System.out.println("Tiempo de búsqueda: " + (endTime - startTime) / 1000000.0 + " ms");
    }

    public static ArrayList<Personas> cargarPersonas(String nombreArchivo) {
        ArrayList<Personas> personas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            boolean encabezado = true;
            while ((linea = br.readLine()) != null) {
                if  (encabezado) {
                    encabezado = false;
                    continue;
                }
                String[] partes =  linea.split(",");
                if (partes.length == 3){
                    String nombre = partes[0].trim();
                    String expediente = partes[1].trim();
                    int edad = Integer.parseInt(partes[2].trim());

                    personas.add(new Personas(nombre, expediente, edad));
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
        return personas;
    }
}