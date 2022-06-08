package beans;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import model.AccountB;
import model.TransactionB;
import model.UserB;

/**
 * Session Bean implementation class TransactionBean
 */
@Stateless
@LocalBean
public class TransactionBean implements TransactionBeanRemote {
	
	@PersistenceContext
	EntityManager em;

	public boolean addTransaction(TransactionB t) {
		try {
//			AccountB from = t.getAccountFrom();
//			AccountB to = t.getAccountTo();
			em.persist(t);
//			from.addTransactionFrom(t);
//			to.addTransactionTo(t);
//			em.merge(from);
//			em.merge(to);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public List<TransactionB> getTransactionsFrom(AccountB a) {
		Query q = em.createQuery("SELECT t FROM TransactionB t WHERE t.accountFrom = :account");
		q.setParameter("account", a);
		return q.getResultList();
	}
	
	public List<TransactionB> getTransactionsTo(AccountB a) {
		Query q = em.createQuery("SELECT t FROM TransactionB t WHERE t.accountTo = :account");
		q.setParameter("account", a);
		return q.getResultList();
	}
	
    /**
     * Default constructor. 
     */
    public TransactionBean() {
        // TODO Auto-generated constructor stub
    }

}
