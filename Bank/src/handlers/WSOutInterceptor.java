package handlers;

import org.apache.cxf.ws.security.wss4j.WSS4JOutInterceptor;
import org.apache.wss4j.dom.handler.WSHandlerConstants;

public class WSOutInterceptor extends WSS4JOutInterceptor {

	public WSOutInterceptor() {
		getProperties().put(WSHandlerConstants.ACTION, "Encrypt");
		getProperties().put(WSHandlerConstants.ENC_PROP_FILE, "resources/bankServerKeystore.properties");
		getProperties().put(WSHandlerConstants.PW_CALLBACK_CLASS, "handlers.ServerKeystorePasswordCallbackHandler");
		getProperties().put(WSHandlerConstants.ENCRYPTION_USER, "realestateclient");
	}
	
}
