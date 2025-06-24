package org.scoula.ex03.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller // 싱글톤 + 클래스 안에서 설정한 주소->함수 매핑을 핸들러매퍼에게 등록시켜줌.
@RequestMapping("/sample") // 해당 도메인을 요청할 때는 앞에다 sample이라고 붙이자!
@Log4j2
public class SampleController {

    // sample 도메인을 위한 여러 요청에 대한 함수를 정의
    @RequestMapping("") // sample
    public String sample() {
        log.info("SampleController =================================");
        return "sample";
        // /WEB-INF/views/sample.jsp ==> 없는 경우 404가 발생함.
    }

    @GetMapping("/basicOnlyGet")
    public void basicOnlyGet() {
        // void인 경우에 호출한 주소를 사용해서 /WEB-INF/views/호출주소.jsp
        // 요청주소와 views 밑에 파일 이름이 같다면 String으로 return 안해도 됨.
        // /sample/basicOnlyGet.jsp를 찾음.
        log.info("/basicOnlyGet ==============");
    }

    @RequestMapping(value = "/basic", method = {RequestMethod.GET, RequestMethod.POST})
    // /sample/basic
    public void basic() {
        log.info("/basic ==============");
        // 스프링은 컨트롤러로 오면 그 다음은 무조건 jsp 파일을 호출하는 흐름임.
        // forward라고 함. ==> 결과를 넣는 jsp 페이지가 필요하다!
        // redirect 해서 view로 안갈수도 있음.
    }
}
