package org.scoula.board.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.scoula.board.domain.BoardAttachmentVO;
import org.scoula.board.domain.BoardVO;
import org.scoula.board.dto.BoardDTO;
import org.scoula.board.mapper.BoardMapper;
import org.scoula.common.util.UploadFiles;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Log4j2
@Service // @Component + 서비스 역할의 클래스라는 것이 스프링에 등록
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final static String BASE_DIR = "c:/upload/board";
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

    @Transactional
    @Override
    public BoardDTO create(BoardDTO board) {
        BoardVO vo = board.toVo();
        boardMapper.create(vo);

        List<MultipartFile> files = board.getFiles();
        if (files != null && !files.isEmpty()) {
            upload(vo.getNo(), files);
        }

        return get(vo.getNo());
    }

    private void upload(Long bno, List<MultipartFile> files) {
        for (MultipartFile part : files) {
            if (part.isEmpty()) continue;
            try {
                String uploadPath = UploadFiles.upload(BASE_DIR, part);
                BoardAttachmentVO attach = BoardAttachmentVO.of(part, bno, uploadPath);
                boardMapper.createAttachment(attach);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public BoardDTO update(BoardDTO board) {
        boardMapper.update(board.toVo());
        return get(board.getNo());
    }

    @Override
    public BoardDTO delete(Long no) {
        // delete 하기 전에 삭제될 번호로 검색해오자.
        BoardDTO board = get(no);
        boardMapper.delete(no);
        return board;
    }

    @Override
    public BoardAttachmentVO getAttachment(Long no) {
        return boardMapper.getAttachment(no);
    }

    @Override
    public boolean deleteAttachment(Long no) {
        return boardMapper.deleteAttachment(no) == 1;
    }
}
