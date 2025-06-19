package org.scoula.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 객체 생성할 때 설정파일로 2가지 중 하나를 사용함.
// 1. xml 파일, 2. java 파일(***)

@Configuration // 설정파일로 스프링 프레임워크에 알려주는 역할
public class ProjectConfig {

    @Bean // 스프링에 싱글톤으로 만들어야 한다고 알려주는 어노테이션
}
