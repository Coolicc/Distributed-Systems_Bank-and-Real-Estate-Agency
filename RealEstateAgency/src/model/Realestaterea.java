package model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the realestaterea database table.
 * 
 */
@Entity
@NamedQuery(name="Realestaterea.findAll", query="SELECT r FROM Realestaterea r")
public class Realestaterea implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int realEstateID;

	@Lob
	private String description;

	private String name;

	@Lob
	private byte[] picture;

	private BigDecimal price;

	private byte sold;
	
	private int bankAccount;
	
	private String address;
	
	private String city;

	//bi-directional many-to-one association to Userrea
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="buyer")
	private Userrea buyer;

	//bi-directional many-to-one association to Userrea
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="seller")
	private Userrea seller;

	public Realestaterea() {
	}

	public int getRealEstateID() {
		return this.realEstateID;
	}

	public void setRealEstateID(int realEstateID) {
		this.realEstateID = realEstateID;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte[] getPicture() {
		return this.picture;
	}

	public void setPicture(byte[] picture) {
		this.picture = picture;
	}

	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public byte getSold() {
		return this.sold;
	}

	public void setSold(byte sold) {
		this.sold = sold;
	}

	public Userrea getBuyer() {
		return this.buyer;
	}

	public void setBuyer(Userrea userrea1) {
		this.buyer = userrea1;
	}

	public Userrea getSeller() {
		return this.seller;
	}

	public void setSeller(Userrea userrea2) {
		this.seller = userrea2;
	}

	public int getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(int bankAccount) {
		this.bankAccount = bankAccount;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

}