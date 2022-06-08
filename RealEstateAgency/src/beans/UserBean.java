package beans;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import model.Userrea;

/**
 * Session Bean implementation class UserBean
 */
@Stateless
@LocalBean
public class UserBean implements UserBeanRemote {
	
	@PersistenceContext
	EntityManager em;
	
	public Userrea getUser(int userID) {
		return em.find(Userrea.class, userID);
	}
	
	public boolean addUser(Userrea user) {
		em.persist(user);
		return true;
	}
	
	public Userrea getUserByUsername(String username) {
		Query q = em.createQuery("SELECT u FROM Userrea u WHERE u.username LIKE :username");
		q.setParameter("username", username);
		List<Userrea> res = q.getResultList();
		if (res.isEmpty()) {
			return null;
		}
		return res.get(0);
	}
	
	public Userrea validateUser(String username, String password) {
		Query q = em.createQuery("SELECT u FROM Userrea u WHERE u.username LIKE :username"
				+ " AND u.password LIKE :password");
		q.setParameter("username", username);
		q.setParameter("password", password);
		List<Userrea> res = q.getResultList();
		if (res.isEmpty()) {
			return null;
		}
		return res.get(0);
	}

    /**
     * Default constructor. 
     */
    public UserBean() {
        // TODO Auto-generated constructor stub
    }

}
