package beans;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import model.UserB;

/**
 * Session Bean implementation class UserBean
 */
@Stateless
@LocalBean
public class UserBean implements UserBeanRemote {
	
	@PersistenceContext
	EntityManager em;

	public UserB getUser(int userID) {
		return em.find(UserB.class, userID);
	}
	
	public boolean addUser(UserB user) {
		em.persist(user);
		return true;
	}
	
	public UserB getUserByUsername(String username) {
		Query q = em.createQuery("SELECT u FROM UserB u WHERE u.username LIKE :username");
		q.setParameter("username", username);
		List<UserB> u = q.getResultList();
		if (u.isEmpty()) {
			return null;
		}
		return u.get(0);
	}
	
    /**
     * Default constructor. 
     */
    public UserBean() {
        // TODO Auto-generated constructor stub
    }

}
