package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the "UserB" database table.
 * 
 */
@Entity
@Table(name="\"UserB\"")
@NamedQuery(name="UserB.findAll", query="SELECT u FROM UserB u")
public class UserB implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="\"userID\"")
	private Integer userID;

	private String address;

	@Temporal(TemporalType.DATE)
	@Column(name="\"dateOfBIrth\"")
	private Date dateOfBIrth;

	private String email;

	private String name;

	private String password;

	private String username;

	//bi-directional many-to-one association to AccountB
	@OneToMany(mappedBy="owner")
	private List<AccountB> accountBs;

	public UserB() {
	}

	public Integer getUserID() {
		return this.userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getDateOfBIrth() {
		return this.dateOfBIrth;
	}

	public void setDateOfBIrth(Date dateOfBIrth) {
		this.dateOfBIrth = dateOfBIrth;
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

	public List<AccountB> getAccountBs() {
		return this.accountBs;
	}

	public void setAccountBs(List<AccountB> accountBs) {
		this.accountBs = accountBs;
	}

	public AccountB addAccountB(AccountB accountB) {
		getAccountBs().add(accountB);
		accountB.setOwner(this);

		return accountB;
	}

	public AccountB removeAccountB(AccountB accountB) {
		getAccountBs().remove(accountB);
		accountB.setOwner(null);

		return accountB;
	}

}