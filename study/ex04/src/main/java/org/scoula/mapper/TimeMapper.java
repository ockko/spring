package org.scoula.mapper;

import org.apache.ibatis.annotations.Select;

public interface TimeMapper {

    // 필요한 db처리 기능을 함수로 불완전하게 써놓자.
    // 추상메서드라고 함.

    @Select("SELECT sysdate()")
    public String getTime();
}
