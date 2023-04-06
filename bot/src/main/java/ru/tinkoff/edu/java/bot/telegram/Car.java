package ru.tinkoff.edu.java.bot.telegram;

public class Car {
    public Car(String name, String mark, String qal){
        System.out.println("name, mark, qal");
    }

    public Car(String name){
        System.out.println("name");
    }

    public Car(String name, String mark){
        System.out.println("name, mark");
    }

    public int set(int b){
        return b+2;
    }
}
