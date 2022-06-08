package bankService.impl;


import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

import org.apache.cxf.interceptor.InInterceptors;
import org.apache.cxf.interceptor.OutInterceptors;

import bankService.*;
import beans.AccountBean;
import beans.CreditBean;
import beans.TransactionBean;
import beans.UserBean;
import model.AccountB;
import model.CreditB;
import model.TransactionB;
import model.UserB;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.inject.Inject;
import javax.jws.HandlerChain;
import javax.jws.WebService;
import javax.transaction.Transactional;

@InInterceptors(interceptors = {"handlers.WSInInterceptor"})
@OutInterceptors(interceptors = {"handlers.WSOutInterceptor"})
@HandlerChain(file="handler-chain.xml")
@WebService(serviceName = "Bank", endpointInterface = "bankService.Bank", targetNamespace = "http://www.example.org/Bank/")
//@Transactional(value = Transactional.TxType.SUPPORTS)
public class BankImpl implements Bank {
	
	@Inject
	AccountBean ab;
	
	@Inject
	UserBean ub;
	
	@Inject
	CreditBean cb;
	
	@Inject
	TransactionBean tb;
	
	public java.util.List<bankService.CreditType> getCredits(int accountID) {
		List<CreditType> cTypes = new ArrayList<>();
		List<CreditB> credits = cb.getCredits(ab.getAccount(accountID));
		for (CreditB c: credits) {
			CreditType ct = new CreditType();
			ct.setAccount(accountID);
			ct.setAmount(c.getAmount());
			ct.setAmountPaidOff(c.getAmountPaidOff());
			ct.setCreditID(c.getCreditID());
			ct.setPaidOff(c.getPaidOff());
			cTypes.add(ct);
		}
		return cTypes;
	}

	public java.util.List<bankService.AccountType> getAccounts(int userID) {
		List<AccountType> aTypes = new ArrayList<>();
		List<AccountB> accounts = ab.getAccounts(ub.getUser(userID));
		for (AccountB a: accounts) {
			AccountType at = new AccountType();
			at.setAccountID(a.getAccountID());
			at.setBalance(a.getBalance().doubleValue());
			at.setOwner(userID);
			aTypes.add(at);
		}
		return aTypes;
	}

	public boolean addAccount(int userID) {
		return ab.addAccount(ub.getUser(userID));
	}

//	@Transactional(value = Transactional.TxType.MANDATORY)
	public boolean transferMoney(bankService.TransactionType in) {
		AccountB from = ab.getAccount(in.getFromAccount());
		if (from.getBalance().doubleValue() < in.getAmount()) {
			return false;
		}
		TransactionB t = new TransactionB();
		t.setAmount(new BigDecimal(in.getAmount()));
		t.setComment(in.getComment());
		t.setAccountFrom(from);
		t.setAccountTo(ab.getAccount(in.getToAccount()));
		ab.increseFunds(in.getFromAccount(), -in.getAmount());
		ab.increseFunds(in.getToAccount(), in.getAmount());
		return tb.addTransaction(t);
	}

	public java.util.List<bankService.TransactionType> getTransactionsFrom(int accountID) {
		List<TransactionType> tType = new ArrayList<>();
		List<TransactionB> transactions = tb.getTransactionsFrom(ab.getAccount(accountID));
		for (TransactionB t: transactions) {
			TransactionType tt = new TransactionType();
			tt.setAmount(t.getAmount().doubleValue());
			tt.setComment(t.getComment());
			tt.setFromAccount(t.getAccountFrom().getAccountID());
			tt.setToAccount(t.getAccountTo().getAccountID());
			tType.add(tt);
		}
		return tType;
	}

	public java.util.List<bankService.TransactionType> getTransactionsTo(int accountID) {
		List<TransactionType> tType = new ArrayList<>();
		List<TransactionB> transactions = tb.getTransactionsTo(ab.getAccount(accountID));
		for (TransactionB t: transactions) {
			TransactionType tt = new TransactionType();
			tt.setAmount(t.getAmount().doubleValue());
			tt.setComment(t.getComment());
			tt.setFromAccount(t.getAccountFrom().getAccountID());
			tt.setToAccount(t.getAccountTo().getAccountID());
			tType.add(tt);
		}
		return tType;
	}

	public boolean addUser(bankService.BankUserType in) {
		UserB user = new UserB();
		user.setAddress(in.getAddress());
		user.setDateOfBIrth(in.getDateOfBirth().toGregorianCalendar().getTime());
		user.setEmail(in.getEmail());
		user.setName(in.getName());
		user.setUsername(in.getUsername());
		user.setPassword(in.getPassword());
		return ub.addUser(user);
	}

	public bankService.BankUserType getUser(java.lang.String username) {
		BankUserType but = new BankUserType();
		UserB u = ub.getUserByUsername(username);
		if (u == null) {
			return null;
		}
		but.setAddress(u.getAddress());
		but.setEmail(u.getEmail());
		but.setName(u.getName());
		but.setUserID(u.getUserID());
		but.setUsername(u.getUsername());
		GregorianCalendar gCalendar = new GregorianCalendar();
		gCalendar.setTime(u.getDateOfBIrth());
		XMLGregorianCalendar xmlCalendar = null;
		try {
			xmlCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(gCalendar);
			but.setDateOfBirth(xmlCalendar);
		} catch (DatatypeConfigurationException ex) {
		
		}
		return but;
	}

	public boolean evaluateCreditCapability(int accountID) {
		AccountB account = ab.getAccount(accountID);
		if (account.getBalance().doubleValue() < 0) {
			return false;
		}
		List<CreditB> credits = cb.getCredits(account);
		for (CreditB c: credits) {
			if (!c.getPaidOff()) {
				return false;
			}
		}
		return true;
	}

//	@Transactional(value = Transactional.TxType.MANDATORY)
	public boolean addCredit(int accountID, double amount) throws AddCreditFault_Exception {
		CreditB credit = new CreditB();
		credit.setAccount(ab.getAccount(accountID));
		credit.setAmount(amount);
		credit.setAmountPaidOff(0);
		credit.setPaidOff(false);
		boolean ok = cb.addCredit(credit);
		ab.increseFunds(accountID, amount);
		return ok;
	}
}