package com.sbs20011.spring.boot.mapper.db1;

import org.apache.ibatis.annotations.Param;

import com.sbs20011.spring.boot.model.User;

public interface Db1Mapper {
	public int insertDbUser(User user);
	public User findOneByName(@Param("name") String name);
}
