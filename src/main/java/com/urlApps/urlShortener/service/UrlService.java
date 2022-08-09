package com.urlApps.urlShortener.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.urlApps.urlShortener.model.Url;
import com.urlApps.urlShortener.model.UrlDto;

@Service
public interface UrlService {

	public Url generateShortenedUrl(UrlDto dto);
	public Url getOriginalUrl(String shortUrl);
	public Optional<Url> getOriginalUrl(Long id);
	public List<Url> getAllUrls();
}
