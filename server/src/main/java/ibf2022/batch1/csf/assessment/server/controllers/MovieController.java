package ibf2022.batch1.csf.assessment.server.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ibf2022.batch1.csf.assessment.server.models.Review;
import ibf2022.batch1.csf.assessment.server.services.MovieService;
import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping(path="/api")
public class MovieController {

	@Autowired
	private MovieService movieSvc;

	// Task 3
	@GetMapping(path="/search")
	public ResponseEntity<String> searchReviews (
		@RequestParam String query) {

		JsonArrayBuilder jsonArray = Json.createArrayBuilder();

		// Task 4 MovieSvc 
		List<Review> list = movieSvc.searchReviews(query);

		// convert from List<Review> to Json
		list.stream()
			.map(r -> {
				// for each query number of comments and add to Review
				int count = movieSvc.getCount(r.getTitle());
				// FIXME: REMOVE sysout
				// System.out.println("Number of comments for " + r.getTitle() + " is: " + count);
				r.setCommentCount(count);
				return r;
			})
			.forEach(r -> {
				jsonArray.add(r.toJson());
			});

		return ResponseEntity
				.status(HttpStatus.OK)
				.contentType(MediaType.APPLICATION_JSON)
				.body(jsonArray.build().toString());
	}
	 
	
	
	// Task 8

}
