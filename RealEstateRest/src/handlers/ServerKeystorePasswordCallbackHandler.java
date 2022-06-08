package handlers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;

import org.apache.wss4j.common.ext.WSPasswordCallback;

public class ServerKeystorePasswordCallbackHandler implements CallbackHandler {

	private Map<String, String> passwords = new HashMap<>();
	
	@Override
	public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
		for (int i = 0; i < callbacks.length; i++) {
			WSPasswordCallback pc = (WSPasswordCallback) callbacks[i];
			String password = passwords.get(pc.getIdentifier());
			if (password != null) {
				pc.setPassword(password);
				return;
			}
		}
	}
	
	public Map<String, String> getPasswords() {
		return passwords;
	}

	public void setPasswords(Map<String, String> passwords) {
		this.passwords = passwords;
	}

	public ServerKeystorePasswordCallbackHandler() {
		super();
		passwords.put("realestateclient", "realEstateClient");
		passwords.put("realestateserver", "realEstateServer");
		passwords.put("bankserver", "bankServer");
	}

}
