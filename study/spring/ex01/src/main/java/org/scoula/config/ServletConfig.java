package org.scoula.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// mvc 관련된 설정이나 객체생성 + 주입
@EnableWebMvc // mvc 관련된 설정을 할 수 있는 파일로 등록
@ComponentScan(basePackages = "org.scoula.controller")
public class ServletConfig implements WebMvcConfigurer {
    // 어떤 함수를 써서 설정해야 할지 이미 WebMvcConfigurer에 정의되어 있음.
}
