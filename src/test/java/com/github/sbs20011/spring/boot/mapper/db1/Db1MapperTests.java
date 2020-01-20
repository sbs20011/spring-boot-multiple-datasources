package com.github.sbs20011.spring.boot.mapper.db1;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sbs20011.spring.boot.Application;
import com.sbs20011.spring.boot.mapper.db1.Db1Mapper;
import com.sbs20011.spring.boot.model.User;

@SpringBootTest(classes = Application.class)
public class Db1MapperTests {

	@Autowired
	private Db1Mapper db1Mapper;
	
	@Test
	public void findOneByName() {
		User user = db1Mapper.findOneByName("DATASOURCE1_USER");
		assertThat(user).isNotNull();
		assertThat(user.getName()).isNotNull();
		assertThat(user.getName()).isEqualTo("DATASOURCE1_USER");
	}

}
