package handlers;

import java.util.ArrayList;
import java.util.List;

import javax.xml.ws.handler.Handler;
import javax.xml.ws.handler.HandlerResolver;
import javax.xml.ws.handler.PortInfo;

import org.jboss.jbossts.txbridge.outbound.JaxWSTxOutboundBridgeHandler;

import com.arjuna.mw.wst11.client.JaxWSHeaderContextProcessor;


public class TransactionHandlerResolver implements HandlerResolver {

    public List<Handler> getHandlerChain(PortInfo arg0) {

      List<Handler> handlerChain = new ArrayList<Handler>();
      handlerChain.add(new JaxWSTxOutboundBridgeHandler());
      handlerChain.add(new JaxWSHeaderContextProcessor());  
      return handlerChain;

    }
    
  } 
