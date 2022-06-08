package beans;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import model.Realestaterea;
import model.Userrea;

/**
 * Session Bean implementation class RealEstateBean
 */
@Stateless
@LocalBean
public class RealEstateBean implements RealEstateBeanRemote {
	
	@PersistenceContext
	EntityManager em;
	
	public List<Realestaterea> getAllRealEstates() {
		return em.createNamedQuery("Realestaterea.findAll").getResultList();
	}
	
	public boolean buyRealEstate(Userrea user, int realEstateID) {
		Realestaterea re = em.find(Realestaterea.class, realEstateID);
		re.setBuyer(user);
		re.setSold((byte) 1);
//		user.addEealEstatesBought(re);
		em.merge(re);
//		em.merge(user);
		return true;
	}
	
	public List<Realestaterea> getRealEstatesSold(Userrea user) {
		Query q = em.createQuery("SELECT r FROM Realestaterea r WHERE r.seller = :user");
		q.setParameter("user", user);
		return q.getResultList();
	}
	
	public List<Realestaterea> getRealEstatesBought(Userrea user) {
		Query q = em.createQuery("SELECT r FROM Realestaterea r WHERE r.buyer = :user");
		q.setParameter("user", user);
		return q.getResultList();
	}
	
	public List<Realestaterea> searchRealEstatesByCity(String city) {
		Query q = em.createQuery("SELECT r FROM Realestaterea r WHERE r.city LIKE :user");
		q.setParameter("user", "%"+city+"%");
		return q.getResultList();
	}
	
	public List<Realestaterea> searchRealEstatesByName(String name) {
		Query q = em.createQuery("SELECT r FROM Realestaterea r WHERE r.name LIKE :user");
		q.setParameter("user", "%"+name+"%");
		return q.getResultList();
	}
	
	public boolean deleteRealEstate(Realestaterea re) {
		Userrea buyer = re.getBuyer();
		Userrea seller = re.getSeller();
		buyer.removeRealEstatesBought(re);
		seller.removeRealEsatesSelling(re);
		em.remove(re);
		em.merge(buyer);
		em.merge(seller);
		return true;
	}
	
	public Realestaterea getRealEstate(int realEstateID) {
		return em.find(Realestaterea.class, realEstateID);
	}
	
	public boolean addRealEstate(Realestaterea re) {
//		Userrea seller = re.getSeller();
		em.persist(re);
//		seller.addRealEsatesSelling(re);
//		em.merge(seller);
		return true;
	}

    /**
     * Default constructor. 
     */
    public RealEstateBean() {
        // TODO Auto-generated constructor stub
    }

}
