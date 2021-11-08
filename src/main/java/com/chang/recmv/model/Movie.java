package com.chang.recmv.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Movie {
	private String title;
	private String link;
	private String image;
	private String actor;
}
