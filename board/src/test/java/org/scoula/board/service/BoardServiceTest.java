package org.scoula.board.service;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.scoula.board.dto.BoardDTO;
import org.scoula.board.mapper.BoardMapper;
import org.scoula.config.RootConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {RootConfig.class})
@Log4j2
class BoardServiceTest {

    @Autowired
    private BoardService boardService;
    @Autowired
    private BoardMapper boardMapper;

    @Test
    void getList() {
    }

    @Test
    void get() {
        Long testNo = 1L;
        BoardDTO board = boardService.get(testNo);

        assertNotNull(board);                          // 조회 결과 존재 확인
        assertEquals(testNo, board.getNo());           // 번호 일치 확인

        log.info("조회된 게시글: {}", board);
    }

    @Test
    void create() {
        BoardDTO board = BoardDTO.builder()
                .title("새로운 테스트 제목")
                .content("새로운 테스트 내용")
                .writer("testUser")
                .build();

        boardService.create(board);

        assertNotNull(board.getNo());

        log.info("생성된 게시글 번호 {}", board.getNo());
    }

    @Test
    void update() {
        // 수정할 데이터 조회
        BoardDTO board = boardService.get(1L);

        board.setTitle("수정된 제목");
        board.setContent("수정된 내용");

        boolean result = boardService.update(board);

        assertTrue(result);
    }

    @Test
    void delete() {

        // 테스트 대상
        Long testNo = 2L;

        // 삭제 전 확인
        // 삭제할 데이터를 조회했을 때
        // 데이터가 없어서 발생하는 예외가 발생 안하는 걸 확인
        assertDoesNotThrow(() -> boardService.get(testNo));

        boolean result = boardService.delete(testNo);
        assertTrue(result);

        // 삭제된 데이터를 조회했을 때
        // 데이터가 없어서 발생하는 예외를 확인
        assertThrows(NoSuchElementException.class, () -> {
            boardService.get(testNo);
        });

        log.info("delete RESULT: {}", result);
    }
}