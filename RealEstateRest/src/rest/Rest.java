package rest;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.arjuna.mw.wst11.UserTransaction;
import com.arjuna.mw.wst11.UserTransactionFactory;
import com.arjuna.wst.SystemException;
import com.arjuna.wst.UnknownTransactionException;
import com.arjuna.wst.WrongStateException;

import bankService.TransactionType;
import beans.BankBean;
import beans.RealEstateAgencyBean;
import pojo.CommentPOJO;
import realEstateService.CommentType;
import realEstateService.RealEstateType;
import realEstateService.UserType;

@Path("/services")
public class Rest {
	
	@EJB
	BankBean bb;
	
	@EJB
	RealEstateAgencyBean reb;
	
	@GET
	@Path("/getAllRealEstates")
	@PermitAll
	@Produces(MediaType.APPLICATION_JSON)
	public List<RealEstateType> getAllRealEstates() {
		return reb.getAllRealEstates();
	}
	
	@POST
	@Path("/register")
	@PermitAll
	@Produces(MediaType.APPLICATION_JSON)
	public UserType register(@FormParam("username") String username, @FormParam("password")
	String password, @FormParam("email") String email, @FormParam("name") String name) {
		UserType user = new UserType();
		user.setEmail(email);
		user.setName(name);
		user.setPassword(password);
		user.setUsername(username);
		if (!reb.addUser(user)) {
			return null;
		}
		return reb.getUser(username);
	}
	
	@POST
	@Path("/login")
	@PermitAll
	@Produces(MediaType.APPLICATION_JSON)
	public UserType login(@FormParam("username") String user, @FormParam("password") String pass) {
		return reb.validateUser(user, pass);
	}
	
	@POST
	@Path("/addRealEstate")
	@RolesAllowed("user")
	@Produces(MediaType.APPLICATION_JSON)
	public RealEstateType addRealEstate(@FormParam("name") String name, @FormParam("description")
	String description, @FormParam("price") double price, @FormParam("bankAccount") 
	int bankAccount, @FormParam("seller") int seller, @FormParam("address") String address,
	@FormParam("city") String city) {
		RealEstateType re = new RealEstateType();
		re.setAddress(address);
		re.setBankAccount(bankAccount);
		re.setSeller(seller);
		re.setCity(city);
		re.setDescription(description);
		re.setName(name);
		re.setPrice(price);
		if (!reb.addRealEstate(re)) {
			return null;
		}
		return reb.searchRealEstateByName(re.getName()).get(0);
	}
	
	@GET
	@Path("/searchRealEstateByCity")
	@PermitAll
	@Produces(MediaType.APPLICATION_JSON)
	public List<RealEstateType> searchRealEstatesByCity(@QueryParam("query") String query) {
		return reb.searchRealEstateByCity(query);
	}
	
	@GET
	@Path("/searchRealEstateByName")
	@PermitAll
	@Produces(MediaType.APPLICATION_JSON)
	public List<RealEstateType> searchRealEstatesByName(@QueryParam("query") String query) {
		return reb.searchRealEstateByName(query);
	}
	
	@GET
	@Path("/getUser")
	@PermitAll
	@Produces(MediaType.APPLICATION_JSON)
	public UserType getUser(@QueryParam("userID") int userID) {
		return reb.getUserByID(userID);
	}
	
	@GET
	@Path("/getCommentsOn")
	@PermitAll
	@Produces(MediaType.APPLICATION_JSON)
	public List<CommentPOJO> getCommentsOn(@QueryParam("userID") int userID) {
		List<CommentType> comments = reb.getCommentsOn(userID);
		List<CommentPOJO> cPOJOs = new ArrayList<>();
		for (CommentType ct: comments ) {
			CommentPOJO cp = new CommentPOJO();
			cp.setCommentID(ct.getCommentID());
			cp.setRating(ct.getRating());
			cp.setText(ct.getText());
			cp.setUserID(ct.getUserID());
			cp.setPosterID(ct.getPosterID());
			cp.setPosterName(reb.getUserByID(ct.getPosterID()).getUsername());
			cp.setUserName(reb.getUserByID(ct.getUserID()).getUsername());
			cPOJOs.add(cp);
		}
		return cPOJOs; 
	}
	
	@POST
	@Path("/postComment")
	@RolesAllowed("user")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean postComment(@FormParam("posterID") int posterID, @FormParam("userID")
	int userID, @FormParam("rating") int rating, @FormParam("text") String text) {
		CommentType ct = new CommentType();
		ct.setPosterID(posterID);
		ct.setRating(rating);
		ct.setText(text);
		ct.setUserID(userID);
		return reb.addComment(ct);
	}
	
	@POST
	@Path("/deleteComment")
	@RolesAllowed("user")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean deleteComment(@FormParam("commentID") int commentID) {
		return reb.deleteComment(commentID);
	}
	
	@POST
	@Path("/deleteRealEstate")
	@RolesAllowed("user")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean deleteRealEstate(@FormParam("realEstateID") int realEstateID) {
		return reb.deleteRealEstate(realEstateID);
	}
	
	@GET
	@Path("/getUsersRealEstates")
	@PermitAll
	@Produces(MediaType.APPLICATION_JSON)
	public List<RealEstateType> getUsersRealEstates(@QueryParam("userID") int userID) {
		return reb.getRealEstatesSold(userID);
	}
	
	@GET
	@Path("/getRealEstate")
	@PermitAll
	@Produces(MediaType.APPLICATION_JSON)
	public RealEstateType getRealEstate(@QueryParam("realEstateID") int realEstateID) {
		//zaboravio dodati operaciju u SOAP servisu...
		return reb.getRealEstate(realEstateID);
	}
	
	//Distribuirana transakcija
	@POST
	@Path("/buyRealEstate")
	@RolesAllowed("user")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean buyRealEstate(@FormParam("fromBankAccount") int fromBankAccount,
			@FormParam("buyerID") int buyerID, @FormParam("realEstateID") int realEstateID,
			@FormParam("username") String username, @FormParam("password") String password) {
		UserTransaction tx = UserTransactionFactory.userTransaction();
		try {
			System.out.println("TX: "+tx);
			tx.begin();
			System.out.println("TX-BEGIN: "+tx);
			System.out.println("OVO JE POSLE BEGIN");
			if (!reb.buyRealEstate(buyerID, realEstateID)) {
				System.out.println("REAL ESTATE");
				tx.rollback();
			}
			RealEstateType ret = reb.getRealEstate(realEstateID);
			TransactionType tt = new TransactionType();
			tt.setAmount(ret.getPrice());
			tt.setComment("Buying real estate.");
			tt.setFromAccount(fromBankAccount);
			tt.setToAccount(ret.getBankAccount());
			if (!bb.transferMoney(tt, username, password)) {
				System.out.println("TRANSFER");
				tx.rollback();
			}
			System.out.println("OVO JE PRE COMMIT");
			tx.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("---------------------------------------------");
			try {
				tx.rollback();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return false;
		}
	}
	
	@POST
	@Path("/buyRealEstateCredit")
	@RolesAllowed("user")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean buyRealEstateCredit(@FormParam("fromBankAccount") int fromBankAccount,
			@FormParam("buyerID") int buyerID, @FormParam("realEstateID") int realEstateID,
			@FormParam("username") String username, @FormParam("password") String password) {
		UserTransaction tx = UserTransactionFactory.userTransaction();
		try {
			tx.begin();
			if (reb.buyRealEstate(buyerID, realEstateID)) {
				tx.rollback();
			}
			RealEstateType ret = reb.getRealEstate(realEstateID);
			if (bb.addCredit(fromBankAccount, ret.getPrice(), username, password)) {
				tx.rollback();
			}
			TransactionType tt = new TransactionType();
			tt.setAmount(ret.getPrice());
			tt.setComment("Buying real estate (with credit)");
			tt.setFromAccount(fromBankAccount);
			tt.setToAccount(ret.getBankAccount());
			if (bb.transferMoney(tt, username, password)) {
				tx.rollback();
			}
			tx.commit();
			return true;
		} catch (Exception e) {
			try {
				tx.rollback();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return false;
		}
	}
	
}
