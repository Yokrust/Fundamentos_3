/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pruebalnkdlist;

/**
 *
 * @author yael
 */
class Student{
    String name;
    int score;
    Student next;
    public Student(String name, int score){
        this.name = name;
        this.score = score;
        this.next = null;
    }
}
class LnkdLst{
    Student head;
    public LnkdLst(){
        this.head = null;
    }
    public void add(String name, int score){
        Student newStudent = new Student(name,score);
        if (head == null){
            head = newStudent;
        } else {
            Student current = head;
            while (current.next != null){
                current = current.next;
            }
            current.next = newStudent;
        }
    
    }
}


public class PruebaLnkdList {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        LnkdLst lista = new LnkdLst();
        lista.add("Airam", 99);
        lista.add("Juanpi", 88);
        lista.add("Alfredo", 81);
        System.out.println("agregado");
    }
    
}
