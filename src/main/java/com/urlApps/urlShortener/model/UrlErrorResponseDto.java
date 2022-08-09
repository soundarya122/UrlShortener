package com.urlApps.urlShortener.model;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class UrlErrorResponseDto {

	private HttpStatus status;
	private String error;
}
