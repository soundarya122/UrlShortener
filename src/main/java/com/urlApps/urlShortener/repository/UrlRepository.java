package com.urlApps.urlShortener.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.urlApps.urlShortener.model.Url;

@Repository
public interface UrlRepository extends JpaRepository<Url, Long> {
	Url findByOriginalUrl(String url);
	Url findByShortUrl(String url);
}
