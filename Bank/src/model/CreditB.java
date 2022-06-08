package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the "CreditB" database table.
 * 
 */
@Entity
@Table(name="\"CreditB\"")
@NamedQuery(name="CreditB.findAll", query="SELECT c FROM CreditB c")
public class CreditB implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="\"creditID\"")
	private Integer creditID;

	@ManyToOne
	@JoinColumn(name="account")
	private AccountB accountB;

	private double amount;

	@Column(name="\"amountPaidOff\"")
	private double amountPaidOff;

	@Column(name="\"paidOff\"")
	private Boolean paidOff;

	public CreditB() {
	}

	public Integer getCreditID() {
		return this.creditID;
	}

	public void setCreditID(Integer creditID) {
		this.creditID = creditID;
	}

	public AccountB getAccount() {
		return this.accountB;
	}

	public void setAccount(AccountB account) {
		this.accountB = account;
	}

	public double getAmount() {
		return this.amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getAmountPaidOff() {
		return this.amountPaidOff;
	}

	public void setAmountPaidOff(double amountPaidOff) {
		this.amountPaidOff = amountPaidOff;
	}

	public Boolean getPaidOff() {
		return this.paidOff;
	}

	public void setPaidOff(Boolean paidOff) {
		this.paidOff = paidOff;
	}

}