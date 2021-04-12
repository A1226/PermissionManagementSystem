package com.itheima.dao;

import com.itheima.domain.Member;
import org.apache.ibatis.annotations.Select;

/**
 * 会员表持久层
 */
public interface MemberDao {

    @Select("select * from member where id = #{memberId}")
    Member findById(String memberId);
}
