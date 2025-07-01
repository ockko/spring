package org.scoula.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.scoula.board.dto.BoardDTO;
import org.scoula.board.service.BoardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/board")
@RequiredArgsConstructor
@Log4j2
// @Controller + @ResponseBody
@RestController // views로 넘어가지 않고 모두 data로 응답하겠다!
public class BoardController {

    private final BoardService service; // 생성자 주입

    @GetMapping("") // ==> /api/board
    // @ResponseBody // 컨트롤러에서 views로 넘어가지 않고 http의 body에 리턴값을 넣어서 응답해라!
    public ResponseEntity<List<BoardDTO>> getList() {
        return ResponseEntity.ok(service.getList());
    }

    @GetMapping("/get") // ==> /api/board/get?no=1
    public BoardDTO get(@RequestParam("no") Long no) {
        return service.get(no);
    }

    @GetMapping("/get/{no}") // ==> /api/board/get/10
    public BoardDTO get2(@PathVariable Long no) {
        return service.get(no);
    }

    @PostMapping("") // ==> /api/board + post
    public ResponseEntity<BoardDTO> create(@RequestBody BoardDTO dto) {
        // @RequestBody --> 브라우저에서 보낼 때도 json으로 보낼 수 있음.
        // 서버에서 json을 받을 때 사용하는 어노테이션!
        return ResponseEntity.ok(service.create(dto));
    }

    @PutMapping("/{no}")
    public ResponseEntity<BoardDTO> update(@PathVariable Long no, @RequestBody BoardDTO dto) {
        return ResponseEntity.ok(service.update(dto));
    }
}
