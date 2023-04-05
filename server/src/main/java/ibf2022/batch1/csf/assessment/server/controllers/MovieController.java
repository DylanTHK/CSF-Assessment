package ibf2022.batch1.csf.assessment.server.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ibf2022.batch1.csf.assessment.server.services.MovieService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping(path="/api")
public class MovieController {

	@Autowired
	private MovieService movieSvc;

	// TODO: Task 3, (/api/search)
	@GetMapping(path="/search")
	public ResponseEntity<String> searchReviews (@RequestParam String movie) {
		
		return null;
	}
	 
	
	// Task 4 MovieSvc 
	
	
	// Task 8

}
