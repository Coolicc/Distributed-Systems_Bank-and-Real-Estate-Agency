package handlers;

import org.apache.cxf.ws.security.wss4j.WSS4JInInterceptor;
import org.apache.wss4j.dom.handler.WSHandlerConstants;

public class WSInInterceptor extends WSS4JInInterceptor {
	
	public WSInInterceptor() {
		getProperties().put(WSHandlerConstants.ACTION, "Encrypt Signature Timestamp");
		getProperties().put(WSHandlerConstants.DEC_PROP_FILE, "resources/realEstateServerKeystore.properties");
		getProperties().put(WSHandlerConstants.PW_CALLBACK_CLASS, "handlers.ServerKeystorePasswordCallbackHandler");
		getProperties().put(WSHandlerConstants.SIGNATURE_USER, "realestateclient");
		getProperties().put(WSHandlerConstants.SIG_PROP_FILE, "resources/realEstateServerKeystore.properties");
	}
	
}
