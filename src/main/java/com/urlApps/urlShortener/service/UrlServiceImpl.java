package com.urlApps.urlShortener.service;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.google.common.hash.Hashing;
import com.urlApps.urlShortener.model.Url;
import com.urlApps.urlShortener.model.UrlDto;
import com.urlApps.urlShortener.repository.UrlRepository;

@Component
public class UrlServiceImpl implements UrlService {

	@Autowired
	private UrlRepository urlRepository;
	
	
	/* Convert the big string URL to the short hashed value */
	@Override
	public Url generateShortenedUrl(UrlDto dto) {
		String shortUrl = encodeUrl(dto.getUrl());
		Url url = new Url();
		url.setOriginalUrl(dto.getUrl());
		url.setShortUrl(shortUrl);
		
		Url existingUrl = urlRepository.findByOriginalUrl(dto.getUrl());
		if(existingUrl != null) {
			System.out.println("Url: "+ url);
			return existingUrl;
		}
		return urlRepository.save(url);
	}

	
	/* Get the URL by providing the shortened hash value to the api */
	@Override
	public Url getOriginalUrl(String shortUrl) {
		Url existingUrl = urlRepository.findByShortUrl(shortUrl);
		if(existingUrl != null) {
			System.out.println("Url: "+ existingUrl);
			return existingUrl;
		}
		return null;
	}

	
	/*
	 * Use this to convert large string into smaller chunk, this is a hashing
	 * library
	 */
	private String encodeUrl(String url) {
		return Hashing.murmur3_32()
        .hashString(url, StandardCharsets.UTF_8)
        .toString();
	}

	
	/* List out all the URLs available in the database */
	@Override
	public List<Url> getAllUrls() {
		return urlRepository.findAll();
	}

	
	/* Get the URL by providing the incremental value to the api */
	@Override
	public Optional<Url> getOriginalUrl(Long id) {
		return urlRepository.findById(id);
	}
}
