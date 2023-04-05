package ibf2022.batch1.csf.assessment.server.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ibf2022.batch1.csf.assessment.server.models.Comment;
import ibf2022.batch1.csf.assessment.server.models.Review;
import ibf2022.batch1.csf.assessment.server.services.MovieService;
import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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
	@PostMapping(
		path="/comment",
		consumes={MediaType.APPLICATION_FORM_URLENCODED_VALUE},
		produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> postComment(@ModelAttribute Comment c) {

		System.out.println("Received comment: " + c);

		// call svc, add c to Mongo -> Document -> insert to Mongo
		Document insertedDoc = movieSvc.postComment(c);

		return ResponseEntity
				.status(HttpStatus.CREATED)
				.contentType(MediaType.APPLICATION_JSON)
				.body(insertedDoc.toJson().toString());
	}

}
