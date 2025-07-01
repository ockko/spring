package org.scoula.board.mapper;

import org.apache.ibatis.annotations.Select;
import org.scoula.board.domain.BoardAttachmentVO;
import org.scoula.board.domain.BoardVO;

import java.util.List;

// @Repository // 저장(db)
// @Component + db 예외처리
public interface BoardMapper {

    // @Select("select * from tbl_board order by no desc")
    List<BoardVO> getList();

    // 상세검색
    public BoardVO get(Long no);

    // 게시판 글쓰기 ==> vo가 거의 대부분임.
    public void create(BoardVO board);

    public int update(BoardVO board);

    public int delete(Long no);

    public void createAttachment(BoardAttachmentVO attach);

    public List<BoardAttachmentVO> getAttachmentList(Long bno);

    public BoardAttachmentVO getAttachment(Long no);

    public int deleteAttachment(Long no);
}
