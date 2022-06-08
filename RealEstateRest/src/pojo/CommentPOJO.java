package pojo;

import javax.xml.bind.annotation.XmlElement;

public class CommentPOJO {

	private int posterID;
	private String posterName;
	private int userID;
	private String userName;
	private int rating;
	private String text;
	private String commentID;

	public CommentPOJO() {
		super();
	}

	public int getPosterID() {
		return posterID;
	}

	public void setPosterID(int posterID) {
		this.posterID = posterID;
	}

	public String getPosterName() {
		return posterName;
	}

	public void setPosterName(String posterName) {
		this.posterName = posterName;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getCommentID() {
		return commentID;
	}

	public void setCommentID(String commentID) {
		this.commentID = commentID;
	}

}
