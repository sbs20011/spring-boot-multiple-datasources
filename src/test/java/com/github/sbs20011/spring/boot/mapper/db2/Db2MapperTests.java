package com.github.sbs20011.spring.boot.mapper.db2;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sbs20011.spring.boot.Application;
import com.sbs20011.spring.boot.mapper.db2.Db2Mapper;
import com.sbs20011.spring.boot.model.User;

@SpringBootTest(classes = Application.class)
public class Db2MapperTests {

	@Autowired
	private Db2Mapper db2Mapper;
	
	@Test
	public void findOneByName() {
		User user = db2Mapper.findOneByName("DATASOURCE2_USER");
		assertThat(user).isNotNull();
		assertThat(user.getName()).isNotNull();
		assertThat(user.getName()).isEqualTo("DATASOURCE2_USER");
	}

}
