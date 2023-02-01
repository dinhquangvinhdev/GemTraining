package com.example.myapplication.model;

public class Customer {
    private int age;
    private String name;

    public Customer() {
    }

    public Customer(int age, String name) {
        this.name = name;
        this.age = age;
    }

    public Customer(int age) {
        this.age = age;
        this.name = null;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
