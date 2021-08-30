package com.spring.msprofile;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.spring.msprofile.entity.SubType;

@SpringBootTest
class MsProfileApplicationTests {

	@Autowired
	private WebTestClient client;
	
	@Test
	public void contextLoads() {
		assertTrue(true);
	}
	
	@Test
	public void listSubType() {
		client.get()
		.uri("/subtype/list")
		.accept(MediaType.APPLICATION_JSON)
		.exchange()
		.expectStatus().isOk()
		.expectHeader().contentType(MediaType.APPLICATION_JSON)
		.expectBodyList(SubType.class);
	}

}
