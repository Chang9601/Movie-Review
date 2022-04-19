package com.chang.recmv.dto;

import java.util.List;

import com.chang.recmv.model.Movie;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class MovieDto {

	private String lastBuildDate;
	private Integer total;
	private Integer start;
	private Integer display;
	private List<Item> items = null;

	@ToString
	@Getter
	@Setter
	public class Item {

		private String title;
		private String link;
		private String image;
		private String subtitle;
		private String pubDate;
		private String director;
		private String actor;
		private String userRating;
		private String plot;
		
		public Movie toEntity() {
			Movie movie = Movie.builder()
					.title(title)
					.link(link)
					.image(image)
					.actor(actor)
					.plot(plot)
					.build();
			
			return movie;
		}
	}
}