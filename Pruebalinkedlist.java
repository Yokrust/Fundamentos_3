/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pruebalinkedlist;

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

class LnkdList {
    
    Student head;
    
    public LnkdList(){
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
    
    public void print(){
        Student current = this.head;
        while(current !=null) {
            System.out.println(current.name + " - " + current.score);
            current = current.next;
        } 
    }
}


public class Pruebalinkedlist {
    public static void main(String[] args) {
        LnkdList lista = new LnkdList();
        lista.add("Juanpi",99);
        lista.add("Uriel", 88);
        lista.add("Andreschan", 81);
        System.out.println("agregados.");
        
        lista.print();
    }
    
}