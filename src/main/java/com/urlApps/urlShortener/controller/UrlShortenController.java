package com.urlApps.urlShortener.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.urlApps.urlShortener.model.Url;
import com.urlApps.urlShortener.model.UrlDto;
import com.urlApps.urlShortener.model.UrlErrorResponseDto;
import com.urlApps.urlShortener.service.UrlService;

@RestController
@RequestMapping("/url")
public class UrlShortenController {

	@Autowired
	private UrlService urlService;
	
	@PostMapping("/generate")
	public ResponseEntity<?> generateShortenedUrl(@RequestBody UrlDto dto){
		Url url = urlService.generateShortenedUrl(dto);
		System.out.println("url: "+ url);
		return new ResponseEntity<>(url, HttpStatus.OK);
	}
	
	@GetMapping("/{shortUrl}")
	public ResponseEntity<?> decodeUrl(@PathVariable String shortUrl, HttpServletResponse response) throws IOException{
		Url url = urlService.getOriginalUrl(shortUrl);
		if(url == null) {
			UrlErrorResponseDto dto = new UrlErrorResponseDto();
			dto.setError("Invalid Short URL");
			dto.setStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<UrlErrorResponseDto>(dto, HttpStatus.OK);
		}else {
			response.sendRedirect(url.getOriginalUrl());
			return null;
		}
	}
	
	@GetMapping("/id/{id}")
	public ResponseEntity<?> decodeId(@PathVariable Long id, HttpServletResponse response) throws IOException{
		Optional<Url> originalUrl = urlService.getOriginalUrl(id);
		if(originalUrl.isPresent()) {
			response.sendRedirect(originalUrl.get().getOriginalUrl());
			return new ResponseEntity<>(originalUrl.get(), HttpStatus.OK);
		}
		UrlErrorResponseDto dto = new UrlErrorResponseDto();
		dto.setError("Invalid ID");
		dto.setStatus(HttpStatus.NOT_FOUND);
		return new ResponseEntity<UrlErrorResponseDto>(dto, HttpStatus.OK);
	}
	
	@GetMapping("/allUrls")
	public ResponseEntity<?> getAllUrls(){
		List<Url> allUrls = urlService.getAllUrls();
		return new ResponseEntity<>(allUrls, HttpStatus.OK);
	}
}
