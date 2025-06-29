package org.scoula.board.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.scoula.board.domain.BoardVO;
import org.scoula.board.dto.BoardDTO;
import org.scoula.board.mapper.BoardMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Log4j2
@Service // @Component + 서비스 역할의 클래스라는 것이 스프링에 등록
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    // 전처리해서 dao의 메서드를 불러서 db 처리해달라고 해야 함.
    private final BoardMapper boardMapper;

    @Override
    public List<BoardDTO> getList() {
        return boardMapper.getList().stream() // BoardVO의 스트림
                .map(BoardDTO::of) // BoardDTO의 스트림
                .toList(); // List<BoardDTO> 변환
    }

    @Override
    public BoardDTO get(Long no) {
        BoardVO vo = boardMapper.get(no);
        // 다른 곳으로 넘길 때는 dto로 만들어서 넘기자.
        BoardDTO dto = BoardDTO.of(vo);
        return Optional.ofNullable(dto)
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public void create(BoardDTO board) {
        BoardVO vo = board.toVo();
        boardMapper.create(vo);
        board.setNo(vo.getNo());
    }

    @Override
    public boolean update(BoardDTO board) {
        return boardMapper.update(board.toVo()) == 1;
    }

    @Override
    public boolean delete(Long no) {
        return boardMapper.delete(no) == 1;
    }
}
