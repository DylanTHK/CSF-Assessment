package ibf2022.batch1.csf.assessment.server.repositories;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import ibf2022.batch1.csf.assessment.server.models.Comment;

@Repository
public class MovieRepository {

	@Autowired
	private MongoTemplate mongoTemplate;

	private final String COLLECTION_NAME = "comments";

	// Task 5
	// You may modify the parameter but not the return type
	// Write the native mongo database query in the comment below
	// db.comments.find({ title: "<insert title>" });
	public int countComments(String title) {
		Query query = Query.query(Criteria.where("title").is(title));
		return (int) mongoTemplate.count(query, COLLECTION_NAME);
	}

	// Task 8
	// Write a method to insert movie comments comments collection
	// Write the native mongo database query in the comment below
	// db.comments.insert({title: "<title>", name: "<name>", rating: <rating>, comment: "<comment>"});
	public Document postComment(Comment c) {
		Document d = Document.parse(c.toJson().toString());
		return mongoTemplate.insert(d, COLLECTION_NAME);
	}
}
