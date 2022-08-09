package com.urlApps.urlShortener.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@ToString
public class Url {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Lob
	private String originalUrl;
	private String shortUrl;
}
