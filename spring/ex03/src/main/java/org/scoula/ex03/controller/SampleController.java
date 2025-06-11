package org.scoula.ex03.controller;

import lombok.extern.log4j.Log4j2;
import org.scoula.ex03.dto.SampleDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/sample")
@Log4j2
public class SampleController {

    @RequestMapping("")
    public void basic() {
        log.info("basic............");
    }

    @RequestMapping(value="/basic", method= {RequestMethod.GET, RequestMethod.POST})
    public void basicGet(){
        log.info("basic get............");
    }

    @GetMapping("/basicOnlyGet")
    public void basicGet2(){
        log.info("basic get only get............");
    }

    @GetMapping("/ex04")
    public String ex04(SampleDTO dto, @ModelAttribute("page") int page, Model model) {
        log.info("dto: " + dto);
        log.info("page: " + page);
        model.addAttribute("dto", dto);
        model.addAttribute("page", page);
        return "sample/ex04";
    }

    @GetMapping("/ex05")
    public void ex05() {
        log.info("/ex05........");
    }

    @GetMapping("/ex06")
    public String ex06(RedirectAttributes ra) {
        log.info("/ex06........");
        ra.addAttribute("name", "AAA");
        ra.addAttribute("age", 10);
        return "redirect:ex06-2";
    }

    @GetMapping("/ex06-2")
    public String ex06_2(Model model) {
        log.info("/ex06-2........");
        model.addAttribute("name", model.getAttribute("name"));
        model.addAttribute("age", model.getAttribute("age"));
        return "ex06-2";
    }
}
