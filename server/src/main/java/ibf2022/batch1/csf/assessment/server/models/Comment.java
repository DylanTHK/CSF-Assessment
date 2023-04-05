package ibf2022.batch1.csf.assessment.server.models;

public class Comment {
    private String title;
    private String name;
    private Integer rating;
    private String comment;

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getRating() {
        return rating;
    }
    public void setRating(Integer rating) {
        this.rating = rating;
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Comment [title=" + title + ", name=" + name + ", rating=" + rating + ", comment=" + comment + "]";
    }


}
