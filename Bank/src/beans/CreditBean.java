package beans;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import model.AccountB;
import model.CreditB;

/**
 * Session Bean implementation class CreditBean
 */
@Stateless
@LocalBean
public class CreditBean implements CreditBeanRemote {
	
	@PersistenceContext
	EntityManager em;
	
	public List<CreditB> getCredits(AccountB account) {
		Query q = em.createQuery("SELECT c FROM CreditB c WHERE c.accountB = :account");
		q.setParameter("account", account);
		return q.getResultList();
	}
	
	public boolean addCredit(CreditB credit) {
		AccountB a = credit.getAccount();
		em.persist(credit);
		a.addCreditB(credit);
		em.merge(a);
		return true;
	}
	
    /**
     * Default constructor. 
     */
    public CreditBean() {
        // TODO Auto-generated constructor stub
    }

}
