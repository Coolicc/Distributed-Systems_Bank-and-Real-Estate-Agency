package model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;


/**
 * The persistent class for the "TransactionB" database table.
 * 
 */
@Entity
@Table(name="\"TransactionB\"")
@NamedQuery(name="TransactionB.findAll", query="SELECT t FROM TransactionB t")
public class TransactionB implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="\"transactionID\"")
	private Integer transactionID;

	private BigDecimal amount;

	private String comment;

	//bi-directional many-to-one association to AccountB
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="\"fromAccount\"")
	private AccountB accountFrom;

	//bi-directional many-to-one association to AccountB
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="\"toAccount\"")
	private AccountB accountTo;

	public TransactionB() {
	}

	public Integer getTransactionID() {
		return this.transactionID;
	}

	public void setTransactionID(Integer transactionID) {
		this.transactionID = transactionID;
	}

	public BigDecimal getAmount() {
		return this.amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public AccountB getAccountFrom() {
		return this.accountFrom;
	}

	public void setAccountFrom(AccountB accountFrom) {
		this.accountFrom = accountFrom;
	}

	public AccountB getAccountTo() {
		return this.accountTo;
	}

	public void setAccountTo(AccountB accountTo) {
		this.accountTo = accountTo;
	}

}