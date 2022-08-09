package com.urlApps.urlShortener;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;

import com.urlApps.urlShortener.controller.UrlShortenController;
import com.urlApps.urlShortener.model.Url;
import com.urlApps.urlShortener.model.UrlDto;
import com.urlApps.urlShortener.repository.UrlRepository;

@SpringBootTest
class UrlShortenerApplicationTests {

	private MockHttpServletResponse response;
	
	@Autowired
	UrlRepository urlRepository;
	
	@Autowired 
	UrlShortenController urlShortenController;
	
	@BeforeEach
	void init() {
		UrlDto dto = new UrlDto();
		dto.setUrl("http://apple.com");
		ResponseEntity<?> shortenedUrl = urlShortenController.generateShortenedUrl(dto);
		
		response = new MockHttpServletResponse();
	}
	
	@Test
	void generateShortenedUrl() throws Exception {
		UrlDto dto = new UrlDto();
		dto.setUrl("http://google.com");
		ResponseEntity<?> shortenedUrl = urlShortenController.generateShortenedUrl(dto);
		
		Url resBody = (Url) shortenedUrl.getBody();
		Url findByShortUrl = urlRepository.findByShortUrl(resBody.getShortUrl());
		assertNotNull(findByShortUrl);
	}
	
	@Test
	public void getAllUrl() {
		ResponseEntity<?> allUrls = urlShortenController.getAllUrls();
		List<Url> urls = (List<Url>) allUrls.getBody();
		assertThat(urls).size().isGreaterThan(0);
	}
	
	@Test
	public void getUrlById() throws IOException {
		
		ResponseEntity<?> allUrls = urlShortenController.getAllUrls();
		List<Url> urls = (List<Url>) allUrls.getBody();
		
		Long urlId = urls.get(0).getId();
		ResponseEntity<?> decodeId = urlShortenController.decodeId(urlId, response);
		Url body = (Url) decodeId.getBody();
		assertNotNull(body.getOriginalUrl());
	}
}
