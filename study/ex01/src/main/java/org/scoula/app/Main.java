package org.scoula.app;

import org.scoula.domain.Parrot;

public class Main {

    public static void main(String[] args) {

        // 스프링을 쓰지 않으면 필요할 때 new 키워드 써서 개발자가 객체를 생성했음.
        Parrot p = new Parrot();
        p.setName("Test");
        System.out.println(p.getName());
    }
}
