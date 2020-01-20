package com.sbs20011.spring.boot.mapper.db2;

import org.apache.ibatis.annotations.Param;

import com.sbs20011.spring.boot.model.User;

public interface Db2Mapper {
	public int insertDbUser(User user);
	public User findOneByName(@Param("name") String name);
}
