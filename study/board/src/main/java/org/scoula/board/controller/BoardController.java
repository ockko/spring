package org.scoula.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.scoula.board.dto.BoardDTO;
import org.scoula.board.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Log4j2
@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService service;

    @GetMapping("/list") // board/list
    public void list(Model model) {
        // db에서 가지고 온 것 있어야 함.
        // Controller --> Service --> dao
        log.info("================> BoardController /list");
        model.addAttribute("list", service.getList());
        // 요청한 주소와 views의 호출할 파일명이 같으면 return 안해도 됨.
    }

    @GetMapping("/create") // board/create(입력화면 보여줘)
    public void create() {
        log.info("create");
    }

    @PostMapping("/create") // board/create(입력한거 db 처리해줘)
    public String create(BoardDTO board) {
        log.info("create: " + board);
        service.create(board);
        return "redirect:/board/list";
    }

    @GetMapping({"/get", "/update"}) // board/get // board/update(수정하기 전에 검색 먼저 해서 한번 보여줘)
    public void get(@RequestParam("no") Long no, Model model) {
        log.info("/get or update");
        model.addAttribute("board", service.get(no));
    }

    @PostMapping("/update") // board/update(수정한거 db 처리해줘)
    public String update(BoardDTO board) {
        log.info("update: " + board);
        service.update(board);
        return "redirect:/board/list";
    }

    @PostMapping("/delete") // board/delete(삭제할거 db 처리해줘)
    public String delete(@RequestParam("no") Long no) {
        log.info("delete..." + no);
        service.delete(no);
        return "redirect:/board/list";
    }
}
