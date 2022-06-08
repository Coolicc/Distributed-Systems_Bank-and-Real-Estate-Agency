package realEstateService.impl;


import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

import org.apache.cxf.interceptor.InInterceptors;
import org.apache.cxf.interceptor.OutInterceptors;

import beans.CommentBEan;
import beans.RealEstateBean;
import beans.UserBean;
import model.Commentrea;
import model.Realestaterea;
import model.Userrea;
import realEstateService.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.jws.HandlerChain;
import javax.jws.WebService;
import javax.transaction.Transactional;

@InInterceptors(interceptors = {"handlers.WSInInterceptor"})
@OutInterceptors(interceptors = {"handlers.WSOutInterceptor"})
@WebService(serviceName = "RealEstateAgency", endpointInterface = "realEstateService.RealEstateAgency", targetNamespace = "http://www.example.org/RealEstateAgency/")
//@HandlerChain(file="handler-chain.xml")
//@Transactional(value = Transactional.TxType.SUPPORTS)
public class RealEstateAgencyImpl implements RealEstateAgency {
	
	@Inject
	UserBean ub;
	
	@Inject
	RealEstateBean reb;
	
	@Inject
	CommentBEan cb;
	
	public realEstateService.UserType getUserByID(int userID) {
		Userrea u = ub.getUser(userID);
		if (u == null) {
			return null;
		}
		UserType ut = new UserType();
		ut.setEmail(u.getEmail());
		ut.setName(u.getName());
		ut.setPassword(u.getPassword());
		ut.setUserID(u.getUserID());
		ut.setUsername(u.getUsername());
		ut.setRating(cb.getRating(u));
		return ut;
	}

	public java.util.List<realEstateService.RealEstateType> getAllRealEstates(java.lang.String in) {
		List<RealEstateType> reType = new ArrayList<>();
		List<Realestaterea> res = reb.getAllRealEstates();
		for (Realestaterea r: res) {
			RealEstateType rt = new RealEstateType();
			rt.setAddress(r.getAddress());
			rt.setBankAccount(r.getBankAccount());
			if (r.getSold() != 0) {
				rt.setBuyer(r.getSold() == 0 ? null : r.getBuyer().getUserID());
			}
			rt.setCity(r.getCity());
			rt.setDescription(r.getDescription());
			rt.setName(r.getName());
			rt.setPrice(r.getPrice().doubleValue());
			rt.setRealEstateID(r.getRealEstateID());
			rt.setSeller(r.getSeller().getUserID());
			rt.setSold(r.getSold() == 0 ? false : true);
//			List<Byte> pic = new ArrayList<>();
//			for (byte b: r.getPicture()) {
//				pic.add(b);
//			}
//			rt.setPicture(pic);
			reType.add(rt);
		}
		return reType;
	}

//	@Transactional(value = Transactional.TxType.MANDATORY)
	public boolean buyRealEstate(int buyerID, int realEstateID) {
		System.out.println("ASDADSDADSDADSADSDSADSDSADSADSADSADASDSADSADSADSADSA");
		Userrea u = ub.getUser(buyerID);
		if (u == null) {
			return false;
		}
		return reb.buyRealEstate(u, realEstateID);
	}

	public boolean deleteComment(int commentID) {
		return cb.deleteComment(cb.getComment(commentID));
	}

	public boolean addUser(realEstateService.UserType in) {
		Userrea u = new Userrea();
		u.setEmail(in.getEmail());
		u.setName(in.getName());
		u.setPassword(in.getPassword());
		u.setUsername(in.getUsername());
		return ub.addUser(u);
	}

	public realEstateService.UserType getUser(java.lang.String username) {
		Userrea u = ub.getUserByUsername(username);
		if (u == null) {
			return null;
		}
		UserType ut = new UserType();
		ut.setEmail(u.getEmail());
		ut.setName(u.getName());
		ut.setPassword(u.getPassword());
		ut.setUserID(u.getUserID());
		ut.setUsername(u.getUsername());
		ut.setRating(cb.getRating(u));
		return ut;
	}

	public java.util.List<realEstateService.RealEstateType> getRealEstatesSold(int userID) {
		List<RealEstateType> reType = new ArrayList<>();
		Userrea u = ub.getUser(userID);
		if (u == null) {
			return null;
		}
		List<Realestaterea> res = reb.getRealEstatesSold(u);
		for (Realestaterea r: res) {
			RealEstateType rt = new RealEstateType();
			rt.setAddress(r.getAddress());
			rt.setBankAccount(r.getBankAccount());
			if (r.getSold() != 0) {
				rt.setBuyer(r.getSold() == 0 ? null : r.getBuyer().getUserID());
			}
			rt.setCity(r.getCity());
			rt.setDescription(r.getDescription());
			rt.setName(r.getName());
			rt.setPrice(r.getPrice().doubleValue());
			rt.setRealEstateID(r.getRealEstateID());
			rt.setSeller(r.getSeller().getUserID());
			rt.setSold(r.getSold() == 0 ? false : true);
//			List<Byte> pic = new ArrayList<>();
//			for (byte b: r.getPicture()) {
//				pic.add(b);
//			}
//			rt.setPicture(pic);
			reType.add(rt);
		}
		return reType;
	}

	public java.util.List<realEstateService.RealEstateType> searchRealEstateByCity(java.lang.String in) {
		List<RealEstateType> reType = new ArrayList<>();
		List<Realestaterea> res = reb.searchRealEstatesByCity(in);
		for (Realestaterea r: res) {
			RealEstateType rt = new RealEstateType();
			rt.setAddress(r.getAddress());
			rt.setBankAccount(r.getBankAccount());
			if (r.getSold() != 0) {
				rt.setBuyer(r.getSold() == 0 ? null : r.getBuyer().getUserID());
			}
			rt.setCity(r.getCity());
			rt.setDescription(r.getDescription());
			rt.setName(r.getName());
			rt.setPrice(r.getPrice().doubleValue());
			rt.setRealEstateID(r.getRealEstateID());
			rt.setSeller(r.getSeller().getUserID());
			rt.setSold(r.getSold() == 0 ? false : true);
//			List<Byte> pic = new ArrayList<>();
//			for (byte b: r.getPicture()) {
//				pic.add(b);
//			}
//			rt.setPicture(pic);
			reType.add(rt);
		}
		return reType;
	}

	public java.util.List<realEstateService.CommentType> getCommentsOn(int userID) {
		Userrea u = ub.getUser(userID);
		if (u == null) {
			return null;
		}
		List<Commentrea> comments = cb.getCommentsOn(u);
		List<CommentType> cType = new ArrayList<>();
		for (Commentrea c: comments) {
			CommentType ct = new CommentType();
			ct.setCommentID(c.getCommentID()+"");
			ct.setPosterID(c.getPoster().getUserID());
			ct.setText(c.getText());
			ct.setRating(c.getRating());
			ct.setUserID(c.getUser().getUserID());
			cType.add(ct);
		}
		return cType;
	}

	public realEstateService.UserType validateUser(java.lang.String username, java.lang.String password) {
		Userrea u = ub.validateUser(username, password);
		if (u == null) {
			return null;
		}
		UserType ut = new UserType();
		ut.setEmail(u.getEmail());
		ut.setName(u.getName());
		ut.setPassword(u.getPassword());
		ut.setUserID(u.getUserID());
		ut.setUsername(u.getUsername());
		ut.setRating(cb.getRating(u));
		return ut;
	}

	public java.util.List<realEstateService.CommentType> getCommentsFrom(int userID) {
		Userrea u = ub.getUser(userID);
		if (u == null) {
			return null;
		}
		List<Commentrea> comments = cb.getCommentsFrom(u);
		List<CommentType> cType = new ArrayList<>();
		for (Commentrea c: comments) {
			CommentType ct = new CommentType();
			ct.setCommentID(c.getCommentID()+"");
			ct.setPosterID(c.getPoster().getUserID());
			ct.setText(c.getText());
			ct.setRating(c.getRating());
			ct.setUserID(c.getUser().getUserID());
			cType.add(ct);
		}
		return cType;
	}

	public java.util.List<realEstateService.RealEstateType> searchRealEstateByName(java.lang.String in) {
		List<RealEstateType> reType = new ArrayList<>();
		List<Realestaterea> res = reb.searchRealEstatesByName(in);
		for (Realestaterea r: res) {
			RealEstateType rt = new RealEstateType();
			rt.setAddress(r.getAddress());
			rt.setBankAccount(r.getBankAccount());
			System.out.println("BUYER: " + (r.getSold() == 0 ? null : r.getBuyer().getUserID()));
			System.out.println("SOLD: "+r.getSold());
			if (r.getSold() != 0) {
				rt.setBuyer(r.getSold() == 0 ? null : r.getBuyer().getUserID());
			}
			rt.setCity(r.getCity());
			rt.setDescription(r.getDescription());
			rt.setName(r.getName());
			rt.setPrice(r.getPrice().doubleValue());
			rt.setRealEstateID(r.getRealEstateID());
			rt.setSeller(r.getSeller().getUserID());
			rt.setSold(r.getSold() == 0 ? false : true);
//			List<Byte> pic = new ArrayList<>();
//			for (byte b: r.getPicture()) {
//				pic.add(b);
//			}
//			rt.setPicture(pic);
			reType.add(rt);
		}
		return reType;
	}

	public java.util.List<realEstateService.RealEstateType> getRealEstatesBought(int userID) {
		List<RealEstateType> reType = new ArrayList<>();
		Userrea u = ub.getUser(userID);
		if (u == null) {
			return null;
		}
		List<Realestaterea> res = reb.getRealEstatesBought(u);
		for (Realestaterea r: res) {
			RealEstateType rt = new RealEstateType();
			rt.setAddress(r.getAddress());
			rt.setBankAccount(r.getBankAccount());
			if (r.getSold() != 0) {
				rt.setBuyer(r.getSold() == 0 ? null : r.getBuyer().getUserID());
			}
			rt.setCity(r.getCity());
			rt.setDescription(r.getDescription());
			rt.setName(r.getName());
			rt.setPrice(r.getPrice().doubleValue());
			rt.setRealEstateID(r.getRealEstateID());
			rt.setSeller(r.getSeller().getUserID());
			rt.setSold(r.getSold() == 0 ? false : true);
//			List<Byte> pic = new ArrayList<>();
//			for (byte b: r.getPicture()) {
//				pic.add(b);
//			}
//			rt.setPicture(pic);
			reType.add(rt);
		}
		return reType;
	}

	public boolean deleteRealEstate(int realEstateID) {
		return reb.deleteRealEstate(reb.getRealEstate(realEstateID));
	}

	public boolean addComment(realEstateService.CommentType in) {
		Commentrea c = new Commentrea();
		Userrea p = ub.getUser(in.getPosterID());
		Userrea u = ub.getUser(in.getUserID());
		if (u == null || p == null) {
			return false;
		}
		c.setPoster(p);
		c.setRating(in.getRating());
		c.setText(in.getText());
		c.setUser(u);
		return cb.addComment(c);
	}

	@Override
	public boolean addRealEstate(RealEstateType in) {
		Realestaterea re = new Realestaterea();
		re.setAddress(in.getAddress());
		re.setBankAccount(in.getBankAccount());
		re.setCity(in.getCity());
		re.setDescription(in.getDescription());
		re.setName(in.getName());
		re.setPrice(new BigDecimal(in.getPrice()));
		re.setSeller(ub.getUser(in.getSeller()));
		re.setSold((byte)0);
//		byte[] pic = new byte[in.getPicture().size()];
//		for (int i = 0; i < pic.length; i++) {
//			pic[i] = in.getPicture().get(i);
//		}
//		re.setPicture(pic);
		return reb.addRealEstate(re);
	}
}