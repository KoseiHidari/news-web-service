package com.test.newswebservice;

import com.test.newswebservice.model.Comments;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Date;

@RunWith (SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
public class NewsWebServiceApplicationTests {
	@Autowired
	private MockMvc mockMvc;


	@Test
	@Sql(value = {"/beforeAll.sql", "/data.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	public void getAllNews() throws Exception {
		this.mockMvc.perform(get("/news"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.content[*]", hasSize(2)))
				.andExpect(jsonPath("$.content[*].id", containsInAnyOrder(0, 1)));
		this.mockMvc.perform(get("/news?page=9"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.content[*]", hasSize(2)))
				.andExpect(jsonPath("$.content[*].id", containsInAnyOrder(18, 19)));
		this.mockMvc.perform(get("/news?size=20"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.content[*]", hasSize(20)))
				.andExpect(jsonPath("$.content[-1:].id", containsInAnyOrder(19)));
	}

//	@Test
//	@Sql(value = {"/beforeAll.sql", "/data.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
//	public void contextCommentsPages() throws Exception {
//		for (int i = 0; i < 20; i++) {
//			for (int j = 0; j < 10; j++) {
//				new Comments(j, i + 20 * j, new Date(), "LOL KEK " + j, "Profile" + j);
//			}
//		}
//
//		this.mockMvc.perform(get("/news"))
//				.andDo(print())
//				.andExpect(status().isOk())
//				.andExpect(jsonPath("$.content[*]", hasSize(2)))
//				.andExpect(jsonPath("$.content[*].id", containsInAnyOrder(0, 1)));
//		this.mockMvc.perform(get("/news?page=9"))
//				.andDo(print())
//				.andExpect(status().isOk())
//				.andExpect(jsonPath("$.content[*]", hasSize(2)))
//				.andExpect(jsonPath("$.content[*].id", containsInAnyOrder(18, 19)));
//		this.mockMvc.perform(get("/news?size=20"))
//				.andDo(print())
//				.andExpect(status().isOk())
//				.andExpect(jsonPath("$.content[*]", hasSize(20)))
//				.andExpect(jsonPath("$.content[-1:].id", containsInAnyOrder(19)));
//	}
}
