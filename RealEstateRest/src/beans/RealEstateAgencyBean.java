package beans;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.apache.cxf.endpoint.Endpoint;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.ws.security.wss4j.WSS4JInInterceptor;
import org.apache.cxf.ws.security.wss4j.WSS4JOutInterceptor;
import org.apache.wss4j.dom.handler.WSHandlerConstants;

import handlers.TransactionHandlerResolver;
import realEstateService.CommentType;
import realEstateService.RealEstateAgency;
import realEstateService.RealEstateAgency_Service;
import realEstateService.RealEstateType;
import realEstateService.UserType;

/**
 * Session Bean implementation class RealEstateAgencyBean
 */
@Stateless
@LocalBean
public class RealEstateAgencyBean implements RealEstateAgencyBeanRemote {
	
	private RealEstateAgency getServicePort() {
		RealEstateAgency_Service service = new RealEstateAgency_Service();
		RealEstateAgency realEstatePort = service.getRealEstateAgencyImplPort();
		org.apache.cxf.endpoint.Client client = ClientProxy.getClient(realEstatePort);
		Endpoint endpoint = client.getEndpoint();
		
		Map<String, Object> outProps = new HashMap<String, Object>();
		outProps.put(WSHandlerConstants.ACTION, "Encrypt Signature Timestamp");
		outProps.put("user", "realestateclient");
		outProps.put(WSHandlerConstants.PW_CALLBACK_CLASS, "handlers.ServerKeystorePasswordCallbackHandler");
		outProps.put(WSHandlerConstants.ENC_PROP_FILE, "resources/realEstateClientKeystore.properties");
		outProps.put(WSHandlerConstants.ENCRYPTION_USER, "realestateserver");
		outProps.put(WSHandlerConstants.ENC_KEY_ID, "DirectReference");
		outProps.put(WSHandlerConstants.SIG_KEY_ID, "DirectReference");
		outProps.put(WSHandlerConstants.SIG_PROP_FILE, "resources/realEstateClientKeystore.properties");
		
//		outProps.put(WSHandlerConstants.SIGNATURE_PARTS, "{Element}");
		
		WSS4JOutInterceptor wssOut = new WSS4JOutInterceptor(outProps);
		endpoint.getOutInterceptors().add(wssOut);
		
		Map<String, Object> inProps = new HashMap<String, Object>();
		inProps.put(WSHandlerConstants.ACTION, "Encrypt");
		inProps.put(WSHandlerConstants.PW_CALLBACK_CLASS, "handlers.ServerKeystorePasswordCallbackHandler");
		inProps.put(WSHandlerConstants.DEC_PROP_FILE, "resources/realEstateClientKeystore.properties");
		
		WSS4JInInterceptor wssIn = new WSS4JInInterceptor(inProps);
		endpoint.getInInterceptors().add(wssIn);
		return realEstatePort;
	}
	
	private RealEstateAgency getServicePortTX() {
		RealEstateAgency_Service service = new RealEstateAgency_Service();
		service.setHandlerResolver(new TransactionHandlerResolver());
		RealEstateAgency realEstatePort = service.getRealEstateAgencyImplPort();
		org.apache.cxf.endpoint.Client client = ClientProxy.getClient(realEstatePort);
		Endpoint endpoint = client.getEndpoint();
		
		Map<String, Object> outProps = new HashMap<String, Object>();
		outProps.put(WSHandlerConstants.ACTION, "Encrypt Signature Timestamp");
		outProps.put("user", "realestateclient");
		outProps.put(WSHandlerConstants.PW_CALLBACK_CLASS, "handlers.ServerKeystorePasswordCallbackHandler");
		outProps.put(WSHandlerConstants.ENC_PROP_FILE, "resources/realEstateClientKeystore.properties");
		outProps.put(WSHandlerConstants.ENCRYPTION_USER, "realestateserver");
		outProps.put(WSHandlerConstants.ENC_KEY_ID, "DirectReference");
		outProps.put(WSHandlerConstants.SIG_KEY_ID, "DirectReference");
		outProps.put(WSHandlerConstants.SIG_PROP_FILE, "resources/realEstateClientKeystore.properties");
		
//		outProps.put(WSHandlerConstants.SIGNATURE_PARTS, "{Element}");
		
		WSS4JOutInterceptor wssOut = new WSS4JOutInterceptor(outProps);
		endpoint.getOutInterceptors().add(wssOut);
		
		Map<String, Object> inProps = new HashMap<String, Object>();
		inProps.put(WSHandlerConstants.ACTION, "Encrypt");
		inProps.put(WSHandlerConstants.PW_CALLBACK_CLASS, "handlers.ServerKeystorePasswordCallbackHandler");
		inProps.put(WSHandlerConstants.DEC_PROP_FILE, "resources/realEstateClientKeystore.properties");
		
		WSS4JInInterceptor wssIn = new WSS4JInInterceptor(inProps);
		endpoint.getInInterceptors().add(wssIn);
		return realEstatePort;
	}

	public UserType getUserByID(int userID) {
		return getServicePort().getUserByID(userID);
	}
	
	public List<RealEstateType> getAllRealEstates() {
		return getServicePort().getAllRealEstates(null);
	}
	
	public boolean buyRealEstate(int buyerID, int realEstateID) {
		return getServicePortTX().buyRealEstate(buyerID, realEstateID);
	}
	
	public boolean deleteComment(int commentID) {
		return getServicePort().deleteComment(commentID);
	}
	
	public boolean addUser(UserType in) {
		return getServicePort().addUser(in);
	}
	
	public UserType getUser(String username) {
		return getServicePort().getUser(username);
	}
	
	public List<RealEstateType> getRealEstatesSold(int userID) {
		return getServicePort().getRealEstatesSold(userID);
	}
	
	public List<RealEstateType> searchRealEstateByCity(String in) {
		return getServicePort().searchRealEstateByCity(in);
	}
	
	public List<CommentType> getCommentsOn(int userID) {
		return getServicePort().getCommentsOn(userID);
	}
	
	public UserType validateUser(String username, String password) {
		return getServicePort().validateUser(username, password);
	}
	
	public List<CommentType> getCommentsFrom(int userID) {
		return getServicePort().getCommentsFrom(userID);
	}
	
	public List<RealEstateType> searchRealEstateByName(String in) {
		return getServicePort().searchRealEstateByName(in);
	}
	
	public List<RealEstateType> getRealEstatesBought(int userID) {
		return getServicePort().getRealEstatesBought(userID);
	}
	
	public boolean deleteRealEstate(int realEstateID) {
		return getServicePort().deleteRealEstate(realEstateID);
	}
	
	public boolean addComment(CommentType in) {
		return getServicePort().addComment(in);
	}
	
	public boolean addRealEstate(RealEstateType in) {
		return getServicePort().addRealEstate(in);
	}
	
	public RealEstateType getRealEstate(int realEstateID) {
		return getServicePort().getAllRealEstates(null).stream().filter(x -> {return x.getRealEstateID() == realEstateID;}).findFirst().get();
	}
	
    /**
     * Default constructor. 
     */
    public RealEstateAgencyBean() {
        // TODO Auto-generated constructor stub
    }

}
