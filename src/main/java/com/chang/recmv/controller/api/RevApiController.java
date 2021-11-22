package com.chang.recmv.controller.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chang.recmv.controller.RevController;
import com.chang.recmv.model.Rev;
import com.chang.recmv.service.RevService;

@RestController
@RequestMapping("/api/rev/*")
public class RevApiController {
	private static final Logger logger = LoggerFactory.getLogger(RevController.class); 

	@Autowired
	private RevService service;

	// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 리뷰쓰기 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	@PostMapping("/write")
	public ResponseEntity<String> writePOST(@RequestBody Rev rev) throws Exception {
		logger.info("Rev: writePOST(@RequestBody Rev rev) 시작");
		logger.info("리뷰쓰기: " + rev);	
		String movie = rev.getMovie();
		Integer num = service.getNumRevsByTitle(movie);
		logger.info("리뷰개수: " + num);
		// 영화제목으로 이전 평균값 
		Double rating = service.getAvgRating(movie);
		logger.info("이전 평균값: " + rating); 
		// 리뷰개수를 곱해서 총값
		Double totalRating = rating * num;
		logger.info("총값: " + totalRating);
		// 평균 평가값 업데이트
		totalRating += rev.getRating();
		// 새로운 평균값
		rating = totalRating / (num + 1);	
		logger.info("새로운 평균값: " + rating);
		service.updateAvgRating(movie, rating);
		service.writeRev(rev);
		logger.info("Rev: writePOST(@RequestBody Rev rev) 끝");
		
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}
	// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 리뷰쓰기 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	
	// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 리뷰수정 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	@PutMapping("/update/{num}")
	public ResponseEntity<String> updateRevPUT(@RequestBody Rev rev, @PathVariable Integer num) throws Exception {
		logger.info("Rev: updateRevPUT(@RequestBody Rev rev, @PathVariable Integer num) 시작");
		logger.info("리뷰수정 후: " + rev);	
		service.updateRev(rev, num);
		logger.info("Rev: updateRevPUT(@RequestBody Rev rev, @PathVariable Integer num) 끝");
		
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}
	// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 리뷰수정 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	
	// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 리뷰삭제 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	@DeleteMapping("/delete/{num}")
	public ResponseEntity<String> deleteRevDELETE(@PathVariable Integer num) throws Exception {
		logger.info("Rev: deleteRevDELETE(@PathVariable Integer num) 시작");
		logger.info("리뷰삭제: " + num);	
		service.deleteRev(num);
		logger.info("Rev: deleteRevDELETE(@PathVariable Integer num) 끝");
		
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}
	// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 리뷰삭제 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@	
}