package org.scoula.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Log4j2
public class HomeController {

    @GetMapping("/")
    public String home() {
        log.info("================> HomController /");
        // return "index";		// View의 이름
        return "redirect:/board/list";
        // list.jsp에는 db에서 전체 검색을 한 결과를 넣어야 함.
        // 컨트롤러를 다시 호출하게 해야 함.
        // 컨트롤러 --> 서비스 --> dao에 걸쳐서 db 결과를 가지고 오게 됨!
    }

}
