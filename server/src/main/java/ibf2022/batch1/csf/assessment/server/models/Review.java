package ibf2022.batch1.csf.assessment.server.models;

import jakarta.json.Json;
import jakarta.json.JsonObject;

// DO NOT MODIFY THIS CLASS
public class Review {
	// display_title
	private String title;
	// mpaa_rating
	private String rating;
	// byline
	private String byline;
	// headline
	private String headline;
	// summary_short 
	private String summary;
	// link.url
	private String reviewURL;
	// multimedia.src
	private String image = null;

	private int commentCount = 0;

	public void setTitle(String title) { this.title = title; }
	public String getTitle() { return this.title; }

	public void setRating(String rating) { this.rating = rating; }
	public String getRating() { return this.rating; }

	public void setByline(String byline) { this.byline = byline; }
	public String getByline() { return this.byline; }

	public void setHeadline(String headline) { this.headline = headline; }
	public String getHeadline() { return this.headline; }

	public void setSummary(String summary) { this.summary = summary; }
	public String getSummary() { return this.summary; }

	public void setReviewURL(String reviewURL) { this.reviewURL = reviewURL; }
	public String getReviewURL() { return this.reviewURL; }

	public void setImage(String image) { this.image = image; }
	public String getImage() { return this.image; }
	public boolean hasImage() { return null != this.image; }

	public void setCommentCount(int commentCount) { this.commentCount = commentCount; };
	public int getCommentCount() { return this.commentCount; }


	// @Override
	// public String toString() {
	// 	return "Review{title:%s, rating:%s}".formatted(title, rating);
	// }

	@Override
	public String toString() {
		return "Review [title=" + title + ", rating=" + rating + ", byline=" + byline + ", headline=" + headline
				+ ", summary=" + summary + ", reviewURL=" + reviewURL + ", image=" + image + ", commentCount="
				+ commentCount + "]";
	}


	public static Review toReview(JsonObject obj) {
		Review r = new Review();
		JsonObject linkObj = obj.getJsonObject("link");
		String title = getStringValue("display_title", obj);
		r.setTitle(title);
		r.setRating(getStringValue("mpaa_rating", obj));
		r.setByline(getStringValue("byline", obj));
		r.setHeadline(getStringValue("headline", obj));
		r.setSummary(getStringValue("summary_short", obj));
		r.setReviewURL(getStringValue("url", linkObj));		
		// some multimedia are null
		try {
			JsonObject mmObj = obj.getJsonObject("multimedia");
			r.setImage(getStringValue("src", mmObj));
		} catch (Exception e) {
			// e.printStackTrace();
			r.setImage("/assets/placeholder.jpg");
		}
		return r;
	}

	public static String getStringValue(String label, JsonObject o) {
        if(o.containsKey(label) && !o.isNull(label)) {
            return o.getString(label);
        }
        return "No value";
    }

	public JsonObject toJson() {
		return Json.createObjectBuilder()
			.add("title", title)
			.add("rating", rating)
			.add("byline", byline)
			.add("headline", headline)
			.add("summary", summary)
			.add("reviewURL", reviewURL)
			.add("image", image)
			.add("commentCount", commentCount)
			.build();
	}


}
