package beans;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import model.AccountB;
import model.UserB;

/**
 * Session Bean implementation class AccountBean
 */
@Stateless
@LocalBean
public class AccountBean implements AccountBeanRemote {
	
	@PersistenceContext
	EntityManager em;
	
	public List<AccountB> getAccounts(UserB user) {
		Query q = em.createQuery("SELECT a FROM AccountB a WHERE a.owner = :user");
		q.setParameter("user", user);
		return q.getResultList();
	}
	
	public AccountB getAccount(int accountID) {
		return em.find(AccountB.class, accountID);
	}
	
	public boolean addAccount(UserB user) {
		try {
			AccountB account = new AccountB();
			account.setBalance(new BigDecimal(0));
			account.setOwner(user);
			em.persist(account);
			user.addAccountB(account);
			em.merge(user);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public boolean increseFunds(int accountID, double amount) {
		AccountB account = getAccount(accountID);
		account.setBalance(account.getBalance().add(new BigDecimal(amount)));
		em.merge(account);
		return true;
	}

    /**
     * Default constructor. 
     */
    public AccountBean() {
        // TODO Auto-generated constructor stub
    }

}
