package handlers;

import java.util.ArrayList;
import java.util.List;

import javax.xml.ws.handler.Handler;
import javax.xml.ws.handler.HandlerResolver;
import javax.xml.ws.handler.PortInfo;

import org.jboss.jbossts.txbridge.outbound.JaxWSTxOutboundBridgeHandler;

import com.arjuna.mw.wst11.client.JaxWSHeaderContextProcessor;

public class UserHandlerResolver implements HandlerResolver {
	
	private String username;
	private String password;

	public UserHandlerResolver(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	@Override
	public List<Handler> getHandlerChain(PortInfo arg0) {
		List<Handler> handlerChain = new ArrayList<>();
		handlerChain.add(new UserHandler(username, password));
		handlerChain.add(new JaxWSTxOutboundBridgeHandler());
		handlerChain.add(new JaxWSHeaderContextProcessor());
		return handlerChain;
	}
	
}
