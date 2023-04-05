package ibf2022.batch1.csf.assessment.server.services;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import ibf2022.batch1.csf.assessment.server.models.Comment;
import ibf2022.batch1.csf.assessment.server.models.Review;
import ibf2022.batch1.csf.assessment.server.repositories.MovieRepository;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonReader;;

@Service
public class MovieService {

	@Value("${movie.api.url}")
	private String MOVIE_URL;

	@Value("${movie.api.key}")
	private String API_KEY;

	@Autowired
	private MovieRepository movieRepo;

	// Task 4
	// DO NOT CHANGE THE METHOD'S SIGNATURE
	public List<Review> searchReviews(String query) {
		// construct url
		String url = UriComponentsBuilder.fromUriString(MOVIE_URL)
			.queryParam("query", query)
			.queryParam("api-key", API_KEY)
			.build().toString();

		// Formatting Request headers
        RequestEntity<Void> req = RequestEntity.get(url)
            .accept(MediaType.APPLICATION_JSON)
            .build();
			
		// Make API Call
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> resp = null;
		try {
            resp = restTemplate.exchange(req, String.class);
        } catch(RestClientException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }

		JsonReader reader = Json.createReader(new StringReader(resp.getBody()));
		JsonArray jsonArray = reader.readObject()
            .getJsonArray("results");

		List<Review> reviewList = new ArrayList<>();
		jsonArray.stream()
			.map(m -> m.asJsonObject())
			.forEach(m -> {
				reviewList.add(Review.toReview(m));
			});
				
		return reviewList;
	}

	// Task 5
	public int getCount(String title) {
		return movieRepo.countComments(title);
	}

	// Task 8
	public Document postComment(Comment c) {
		return movieRepo.postComment(c);
	}
}
