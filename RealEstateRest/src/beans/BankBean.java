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

import bankService.AccountType;
import bankService.AddCreditFaultException;
import bankService.Bank;
import bankService.BankUserType;
import bankService.Bank_Service;
import bankService.CreditType;
import bankService.TransactionType;
import handlers.UserHandlerResolver;

/**
 * Session Bean implementation class BankBean
 */
@Stateless
@LocalBean
public class BankBean implements BankBeanRemote {
	
	private Bank getServicePort() {
		Bank_Service service = new Bank_Service();
		Bank bankPort = service.getBankImplPort();
		org.apache.cxf.endpoint.Client client = ClientProxy.getClient(bankPort);
		Endpoint endpoint = client.getEndpoint();
		
		Map<String, Object> outProps = new HashMap<String, Object>();
		outProps.put(WSHandlerConstants.ACTION, "Encrypt Signature Timestamp");
		outProps.put("user", "realestateclient");
		outProps.put(WSHandlerConstants.PW_CALLBACK_CLASS, "handlers.BankServerKeystorePasswordCallbackHandler");
		outProps.put(WSHandlerConstants.ENC_PROP_FILE, "resources/realEstateClientKeystore.properties");
		outProps.put(WSHandlerConstants.ENCRYPTION_USER, "bankserver");
		outProps.put(WSHandlerConstants.ENC_KEY_ID, "DirectReference");
		outProps.put(WSHandlerConstants.SIG_KEY_ID, "DirectReference");
		outProps.put(WSHandlerConstants.SIG_PROP_FILE, "resources/realEstateClientKeystore.properties");
		
//		outProps.put(WSHandlerConstants.SIGNATURE_PARTS, "{Element}");
		
		WSS4JOutInterceptor wssOut = new WSS4JOutInterceptor(outProps);
		endpoint.getOutInterceptors().add(wssOut);
		
		Map<String, Object> inProps = new HashMap<String, Object>();
		inProps.put(WSHandlerConstants.ACTION, "Encrypt");
		inProps.put(WSHandlerConstants.PW_CALLBACK_CLASS, "handlers.BankServerKeystorePasswordCallbackHandler");
		inProps.put(WSHandlerConstants.DEC_PROP_FILE, "resources/realEstateClientKeystore.properties");
		
		WSS4JInInterceptor wssIn = new WSS4JInInterceptor(inProps);
		endpoint.getInInterceptors().add(wssIn);
		return bankPort;
	}
	
	private Bank getServicePort(String username, String password) {
		Bank_Service service = new Bank_Service();
		service.setHandlerResolver(new UserHandlerResolver(username, password));
		Bank bankPort = service.getBankImplPort();
		org.apache.cxf.endpoint.Client client = ClientProxy.getClient(bankPort);
		Endpoint endpoint = client.getEndpoint();
		
		Map<String, Object> outProps = new HashMap<String, Object>();
		outProps.put(WSHandlerConstants.ACTION, "Encrypt Signature Timestamp");
		outProps.put("user", "realestateclient");
		outProps.put(WSHandlerConstants.PW_CALLBACK_CLASS, "handlers.BankServerKeystorePasswordCallbackHandler");
		outProps.put(WSHandlerConstants.ENC_PROP_FILE, "resources/realEstateClientKeystore.properties");
		outProps.put(WSHandlerConstants.ENCRYPTION_USER, "bankserver");
		outProps.put(WSHandlerConstants.ENC_KEY_ID, "DirectReference");
		outProps.put(WSHandlerConstants.SIG_KEY_ID, "DirectReference");
		outProps.put(WSHandlerConstants.SIG_PROP_FILE, "resources/realEstateClientKeystore.properties");
		
//		outProps.put(WSHandlerConstants.SIGNATURE_PARTS, "{Element}");
		
		WSS4JOutInterceptor wssOut = new WSS4JOutInterceptor(outProps);
		endpoint.getOutInterceptors().add(wssOut);
		
		Map<String, Object> inProps = new HashMap<String, Object>();
		inProps.put(WSHandlerConstants.ACTION, "Encrypt");
		inProps.put(WSHandlerConstants.PW_CALLBACK_CLASS, "handlers.BankServerKeystorePasswordCallbackHandler");
		inProps.put(WSHandlerConstants.DEC_PROP_FILE, "resources/realEstateClientKeystore.properties");
		
		WSS4JInInterceptor wssIn = new WSS4JInInterceptor(inProps);
		endpoint.getInInterceptors().add(wssIn);
		return bankPort;
	}
	
	public List<CreditType> getCredits(int accountID) {
		return getServicePort().getCredits(accountID);
	}
	
	public List<AccountType> getAccounts(int userID) {
		return getServicePort().getAccounts(userID);
	}
	
	public boolean addAccount(int userID) {
		return getServicePort().addAccount(userID);
	}
	
	public boolean transferMoney(TransactionType in, String username, String password) {
		return getServicePort(username, password).transferMoney(in);
	}
	
	public List<TransactionType> getTransactionsFrom(int accountID) {
		return getServicePort().getTransactionsFrom(accountID);
	}
	
	public List<TransactionType> getTransactionsTo(int accountID) {
		return getServicePort().getTransactionsTo(accountID);
	}
	
	public boolean addUser(BankUserType in) {
		return getServicePort().addUser(in);
	}
	
	public BankUserType getUser(String username) {
		return getServicePort().getUser(username);
	}
	
	public boolean evaluateCreditCapability(int accountID) {
		return getServicePort().evaluateCreditCapability(accountID);
	}
	
	public boolean addCredit(int accountID, double amount, String username, String password) throws AddCreditFaultException {
		try {
			return getServicePort(username, password).addCredit(accountID, amount);
		} catch (Exception e) {
			return false;
		}
	}

    /**
     * Default constructor. 
     */
    public BankBean() {
        // TODO Auto-generated constructor stub
    }

}
