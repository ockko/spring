package org.scoula.board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.scoula.board.domain.BoardVO;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder // build가 쉽게 해당 객체로 만들수있게 어노테이션!
public class BoardDTO {

    private Long no;
    private String title;
    private String content;
    private String writer;
    private Date regDate;
    private Date updateDate;

    // vo --> dto (of())
    public static BoardDTO of(BoardVO vo) {
        // 복잡하지도 않고, 순서도 상관없이 값을 넣어서 객체를 만들고자 하는 방법
        // Builder객체 이용
        return vo == null ? null : BoardDTO.builder()
                .no(vo.getNo())
                .title(vo.getTitle())
                .content(vo.getContent())
                .writer(vo.getWriter()).regDate(vo.getRegDate()).updateDate(vo.getUpdateDate())
                .build();
    }

    // dto --> vo(toVo())
    public BoardVO toVo() {
        return BoardVO.builder()
                .no(no)
                .title(title)
                .content(content)
                .writer(writer).regDate(regDate)
                .updateDate(updateDate)
                .build();
    }
}
