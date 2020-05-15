package com.keywer.aot;

public class User {
    private String name;
    private int age;
    public String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void sayHello() {
        System.out.println("Hello");
    }

    public void sayHelloForUser(String name) {
        System.out.println("Hello " + name);
    }
}
