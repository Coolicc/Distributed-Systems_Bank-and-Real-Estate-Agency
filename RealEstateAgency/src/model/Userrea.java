package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the userrea database table.
 * 
 */
@Entity
@NamedQuery(name="Userrea.findAll", query="SELECT u FROM Userrea u")
public class Userrea implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int userID;

	private String email;

	private String name;

	private String password;

	private String username;

	//bi-directional many-to-one association to Commentrea
	@OneToMany(mappedBy="poster")
	private List<Commentrea> commentsPosted;

	//bi-directional many-to-one association to Commentrea
	@OneToMany(mappedBy="user")
	private List<Commentrea> comments;

	//bi-directional many-to-one association to Realestaterea
	@OneToMany(mappedBy="buyer")
	private List<Realestaterea> realEstatesBought;

	//bi-directional many-to-one association to Realestaterea
	@OneToMany(mappedBy="seller")
	private List<Realestaterea> realEsatesSelling;

	public Userrea() {
	}

	public int getUserID() {
		return this.userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Commentrea> getCommentsPosted() {
		return this.commentsPosted;
	}

	public void setCommentsPosted(List<Commentrea> commentreas1) {
		this.commentsPosted = commentreas1;
	}

	public Commentrea addcommentsPosted(Commentrea commentreas1) {
		getCommentsPosted().add(commentreas1);
		commentreas1.setPoster(this);

		return commentreas1;
	}

	public Commentrea removeCommentsPosted(Commentrea commentreas1) {
		getCommentsPosted().remove(commentreas1);
		commentreas1.setPoster(null);

		return commentreas1;
	}

	public List<Commentrea> getComments() {
		return this.comments;
	}

	public void setComments(List<Commentrea> commentreas2) {
		this.comments = commentreas2;
	}

	public Commentrea addComment(Commentrea commentreas2) {
		getComments().add(commentreas2);
		commentreas2.setUser(this);

		return commentreas2;
	}

	public Commentrea removeComment(Commentrea commentreas2) {
		getComments().remove(commentreas2);
		commentreas2.setUser(null);

		return commentreas2;
	}

	public List<Realestaterea> getRealEstatesBought() {
		return this.realEstatesBought;
	}

	public void setRealEstatesBought(List<Realestaterea> realestatereas1) {
		this.realEstatesBought = realestatereas1;
	}

	public Realestaterea addEealEstatesBought(Realestaterea realestatereas1) {
		getRealEstatesBought().add(realestatereas1);
		realestatereas1.setBuyer(this);

		return realestatereas1;
	}

	public Realestaterea removeRealEstatesBought(Realestaterea realestatereas1) {
		getRealEstatesBought().remove(realestatereas1);
		realestatereas1.setBuyer(null);

		return realestatereas1;
	}

	public List<Realestaterea> getRealEsatesSelling() {
		return this.realEsatesSelling;
	}

	public void setRealEsatesSelling(List<Realestaterea> realestatereas2) {
		this.realEsatesSelling = realestatereas2;
	}

	public Realestaterea addRealEsatesSelling(Realestaterea realestatereas2) {
		getRealEsatesSelling().add(realestatereas2);
		realestatereas2.setSeller(this);

		return realestatereas2;
	}

	public Realestaterea removeRealEsatesSelling(Realestaterea realestatereas2) {
		getRealEsatesSelling().remove(realestatereas2);
		realestatereas2.setSeller(null);

		return realestatereas2;
	}

}