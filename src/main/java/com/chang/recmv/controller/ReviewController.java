package com.chang.recmv.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.chang.recmv.config.auth.PrincipalDetails;
import com.chang.recmv.model.Review;
import com.chang.recmv.service.MovieService;
import com.chang.recmv.service.ReviewService;

@Controller
public class ReviewController {
	
	private final MovieService movieService;
	
	private final ReviewService reviewService;
	
	// 다른 사용자 정보 접근 시 이동	
	private boolean isUserPrincipalDetailsSame(String user, String principalDetail) {
		return user.equals(principalDetail);
	}
	
	public ReviewController(MovieService movieService, ReviewService reviewService) {
		this.movieService = movieService;
		this.reviewService = reviewService;
	}
	
	@GetMapping("/reviews")
	public String list(Model model, @PageableDefault Pageable pageable) throws Exception {
		model.addAttribute("reviews", reviewService.findAll(pageable));

		return "review/list";
	}	
	
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')") // 미리 접근 권한 요구
	@GetMapping("/movies/{id}/reviews")
	public String create(@PathVariable int id, Model model) {
		model.addAttribute("movie", movieService.findById(id));
		
		return "review/create";
	}
	
	@GetMapping("/reviews/{id}")
	public String read(@PathVariable int id, Model model) {
		reviewService.updateView(id); 
		model.addAttribute("review", reviewService.findById(id));
		
		return "review/read";
	}

	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')") // 미리 접근 권한 요구
	@GetMapping("/reviews/{id}/update")
	public String update(@AuthenticationPrincipal PrincipalDetails principalDetails, @PathVariable int id, Model model) {
		Review review = reviewService.findById(id);
		
		if(!isUserPrincipalDetailsSame(review.getUser().getUsername(), principalDetails.getUsername())) {
			return "redirect:/";
		}
		
		model.addAttribute("review", review);
		
		return "review/update";
	}
	
	@GetMapping("/reviews/search")
	public String search(@RequestParam(name = "query") String query, @RequestParam(name = "choice") String choice, @PageableDefault Pageable pageable, Model model) {
		Page<Review> list = null;
		
		if(choice.equals("title")) list = reviewService.findByTitleContaining(query, pageable);
		else list = reviewService.findByContentContaining(query, pageable);
		
		model.addAttribute("reviews", list);
		model.addAttribute("query", query);
		model.addAttribute("choice", choice);
		
		return "review/search";
	}
	
	@GetMapping("/reviews/movies/id/{id}")
	public String title(@PathVariable int id, Model model, @PageableDefault Pageable pageable) throws Exception {
		model.addAttribute("reviews", reviewService.findByMovieId(id, pageable));
		model.addAttribute("id", id);

		return "review/title";
	}		
}