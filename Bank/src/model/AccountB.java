package model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the "AccountB" database table.
 * 
 */
@Entity
@Table(name="\"AccountB\"")
@NamedQuery(name="AccountB.findAll", query="SELECT a FROM AccountB a")
public class AccountB implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="\"accountID\"")
	private Integer accountID;

	private BigDecimal balance;
	
	@ManyToOne
	@JoinColumn(name="owner")
	private UserB owner;

	//bi-directional many-to-one association to CreditB
	@OneToMany(mappedBy="accountB")
	private List<CreditB> creditBs;
	
	@OneToMany(mappedBy="accountFrom")
	private List<TransactionB> transactionsFrom;
	
	@OneToMany(mappedBy="accountTo")
	private List<TransactionB> transactionsTo;

	public AccountB() {
	}

	public Integer getAccountID() {
		return this.accountID;
	}

	public void setAccountID(Integer accountID) {
		this.accountID = accountID;
	}

	public BigDecimal getBalance() {
		return this.balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public UserB getOwner() {
		return this.owner;
	}

	public void setOwner(UserB owner) {
		this.owner = owner;
	}

	public List<CreditB> getCreditBs() {
		return this.creditBs;
	}

	public void setCreditBs(List<CreditB> creditBs) {
		this.creditBs = creditBs;
	}

	public CreditB addCreditB(CreditB creditB) {
		getCreditBs().add(creditB);
		creditB.setAccount(this);

		return creditB;
	}

	public CreditB removeCreditB(CreditB creditB) {
		getCreditBs().remove(creditB);
		creditB.setAccount(null);

		return creditB;
	}
	
	public List<TransactionB> getTransactionsTo() {
		return this.transactionsTo;
	}

	public void setTransactionsTo(List<TransactionB> transactionsTo) {
		this.transactionsTo = transactionsTo;
	}

	public TransactionB addTransactionTo(TransactionB transactionTo) {
		getTransactionsTo().add(transactionTo);
		transactionTo.setAccountTo(this);

		return transactionTo;
	}

	public TransactionB removeTransactionsTo(TransactionB transactionTo) {
		getTransactionsTo().remove(transactionTo);
		transactionTo.setAccountTo(null);

		return transactionTo;
	}
	
	public List<TransactionB> getTransactionsFrom() {
		return this.transactionsFrom;
	}

	public void setTransactionsFrom(List<TransactionB> transactionsFrom) {
		this.transactionsFrom = transactionsFrom;
	}

	public TransactionB addTransactionFrom(TransactionB transactionFrom) {
		getTransactionsFrom().add(transactionFrom);
		transactionFrom.setAccountFrom(this);

		return transactionFrom;
	}

	public TransactionB removeTransactionsFrom(TransactionB transactionFrom) {
		getTransactionsFrom().remove(transactionFrom);
		transactionFrom.setAccountFrom(null);

		return transactionFrom;
	}

}