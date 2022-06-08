package beans;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import model.Commentrea;
import model.Userrea;

/**
 * Session Bean implementation class CommentBEan
 */
@Transactional
@Stateless
@LocalBean
public class CommentBEan implements CommentBEanRemote {
	
	@PersistenceContext
	EntityManager em;
	
	public double getRating(Userrea user) {
		List<Commentrea> comments = getCommentsOn(user);
		if (comments.isEmpty()) {
			return 0.0;
		}
		return comments.stream().mapToDouble((x) -> {return (double)x.getRating();}).average().getAsDouble();
	}
	
	public List<Commentrea> getCommentsOn(Userrea user) {
		Query q = em.createQuery("SELECT c FROM Commentrea c WHERE c.user = :user");
		q.setParameter("user", user);
		return q.getResultList();
	}
	
	public List<Commentrea> getCommentsFrom(Userrea user) {
		Query q = em.createQuery("SELECT c FROM Commentrea c WHERE c.poster = :user");
		q.setParameter("user", user);
		return q.getResultList();
	}
	
	public Commentrea getComment(int commentID) {
		return em.find(Commentrea.class, commentID);
	}
	
	public boolean deleteComment(Commentrea comment) {
		Userrea poster = comment.getPoster();
		Userrea user = comment.getUser();
		em.remove(comment);
		poster.removeComment(comment);
		user.removeComment(comment);
		em.merge(poster);
		em.merge(user);
		return true;
	}
	
	public boolean addComment(Commentrea comment) {
//		Userrea poster = comment.getPoster();
//		Userrea user = comment.getUser();
		em.persist(comment);
//		poster.addComment(comment);
//		user.addComment(comment);
//		em.merge(poster);
//		em.merge(user);
		return true;
	}

    /**
     * Default constructor. 
     */
    public CommentBEan() {
        // TODO Auto-generated constructor stub
    }

}
