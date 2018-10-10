package com.in28minutos.microservices;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.Link;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.in28minutos.microservices.utils.UserMixIn;

@RunWith(SpringRunner.class)
@SpringBootTest
public class In28minutosMicroservicesApplicationTests {

	@Test
	public void contextLoads() {
	}

	/**
	 * 
	 * Testing that MixIn class is filtering some fields of class Link (used as 'hateoas' support to the User entity).
	 * 
	 * @throws JsonProcessingException
	 */
	@Test
	public void testMixInSerialization() throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.addMixIn(Link.class, UserMixIn.class);
		
		Link link = new Link("localhost");
		
		String result = mapper.writeValueAsString(link);
		
		assertThat(result, not(containsString("hreflang")));
		assertThat(result, containsString("rel"));
		assertThat(result, containsString("href"));
		
	}
}
