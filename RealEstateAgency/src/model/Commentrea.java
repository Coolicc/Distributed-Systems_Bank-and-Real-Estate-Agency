package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the commentrea database table.
 * 
 */
@Entity
@NamedQuery(name="Commentrea.findAll", query="SELECT c FROM Commentrea c")
public class Commentrea implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int commentID;

	private int rating;

	@Lob
	private String text;

	//bi-directional many-to-one association to Userrea
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="poster")
	private Userrea poster;

	//bi-directional many-to-one association to Userrea
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="user")
	private Userrea user;

	public Commentrea() {
	}

	public int getCommentID() {
		return this.commentID;
	}

	public void setCommentID(int commentID) {
		this.commentID = commentID;
	}

	public int getRating() {
		return this.rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Userrea getPoster() {
		return this.poster;
	}

	public void setPoster(Userrea userrea1) {
		this.poster = userrea1;
	}

	public Userrea getUser() {
		return this.user;
	}

	public void setUser(Userrea userrea2) {
		this.user = userrea2;
	}

}