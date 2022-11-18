package org.example;

public class Main {

    public static void main(String[] args) {
        //System.out.println( "Hello world!" );
        Student s = new Student( 2,"kk" );
        tes( 2,s );
        System.out.println(s.name);
    }
    public static void tes(int a, Student s){
        a=6;
        s= new Student( 1,"raj" );
    }

    public static class Student{
        int age;
        String name;

        public Student(int age, String name) {
            this.age = age;
            this.name = name;
        }
    }
}