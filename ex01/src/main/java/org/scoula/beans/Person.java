package org.scoula.beans;

public class Person {
    private String name;
    private Parrot parrot;

    public Parrot getParrot() {
        return parrot;
    }

    public String getName() {
        return name;
    }

    // Set을 사용한 의존성 주입
    public void setParrot(Parrot parrot) {
        this.parrot = parrot;
    }

    public void setName(String name) {
        this.name = name;
    }
}
