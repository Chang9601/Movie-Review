package com.chang.recmv.controller.api;

/*import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;
*/
///import org.apache.commons.io.FileUtils;
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
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;

import com.chang.recmv.controller.RevController;
import com.chang.recmv.model.Rev;
import com.chang.recmv.service.RevService;
//import com.google.gson.JsonObject;

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
		// 영화제목에 해당하는 리뷰의 개수
		Integer num = service.getNumRevsByTitle(movie);
		// 영화제목으로 이전 평균 평점 
		Double rating = service.getAvgRating(movie);
		// 리뷰개수를 곱해서 평점의 총합
		Double totalRating = rating * num;
		// 평점 총합 업데이트
		totalRating += rev.getRating();
		// 새로운 평점
		rating = totalRating / (num + 1);	
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
		// 삭제할 리뷰의 평점
		Rev rev = service.readRev(num);
		Double ratingDel = rev.getRating();
		// 삭제할 리뷰의 영화제목
		String movie = rev.getMovie();
		// 영화제목으로 이전 평균값 
		Double rating = service.getAvgRating(movie);
		// 영화제목에 해당하는 리뷰의 개수
		Integer numRevs = service.getNumRevsByTitle(movie);
		// 리뷰개수를 곱해서 총값
		Double totalRating = rating * numRevs;
		// 평균 평점 업데이트
		totalRating -= ratingDel;
		// 새로운 평균값(리뷰가 1, 0개이면 예외처리)
		if((numRevs - 1) > 1) rating = totalRating / (numRevs - 1);
		else if((numRevs - 1) == 1) rating = Math.floor(totalRating);
		else rating = 0.0;
		logger.info("새로운 평균값: " + rating);
		service.updateAvgRating(movie, rating);		
		service.deleteRev(num);
		logger.info("Rev: deleteRevDELETE(@PathVariable Integer num) 끝");
		
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}
	// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 리뷰삭제 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@	
	
	/*
	@PostMapping(value = "/uploadSummernoteImageFile", produces = "application/json; charset=UTF-8")
	public JsonObject uploadSummernoteImageFile(@RequestParam("file") MultipartFile multipartFile) {
		logger.info("Rev: uploadSummernoteImageFile(@RequestParam(\"file\") MultipartFile multipartFile) 시작");
		
		JsonObject jsonObject = new JsonObject();
		
		String fileRoot = "D:\\summernote_image\\";
		
		String originalFileName = multipartFile.getOriginalFilename();
		String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
		
		String savedFileName = UUID.randomUUID() + extension;
		
		File targetFile = new File(fileRoot + savedFileName);
		
		try {
			InputStream fileStream = multipartFile.getInputStream();
			FileUtils.copyInputStreamToFile(fileStream, targetFile);
			jsonObject.addProperty("url", "/recmv/summernoteImage/" + savedFileName);
			jsonObject.addProperty("responseCode", "success");
		} catch(IOException e) {
			FileUtils.deleteQuietly(targetFile);
			jsonObject.addProperty("responseCode", "error");
			e.printStackTrace();
		}
		logger.info("Rev: uploadSummernoteImageFile(@RequestParam(\"file\") MultipartFile multipartFile) 끝");

		return jsonObject;
	}
	*/
}