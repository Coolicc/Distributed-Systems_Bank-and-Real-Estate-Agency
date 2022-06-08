package handlers;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPHeaderElement;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

public class UserHandler implements SOAPHandler<SOAPMessageContext>{
	
	private String username;
	private String password;

	public UserHandler(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	@Override
	public boolean handleMessage(SOAPMessageContext context) {
		if(!((Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY)).booleanValue())
			return true;
		
		String ip = getAddress();
		
		SOAPMessage msg = context.getMessage();
		SOAPHeader hdr;
		
		try {
			hdr = msg.getSOAPHeader();
			if(hdr == null)
				hdr = msg.getSOAPPart().getEnvelope().addHeader();
			
			QName ADDRESS_HEADER = new QName("mynamespace", "address");
			SOAPHeaderElement hdrElement = hdr.addHeaderElement(ADDRESS_HEADER);
			//u bazi klijent nema jmbg, pa se umesto toga proverava preko imena i prezimena
			SOAPElement ipaddr = hdrElement.addChildElement("username_password");
			ipaddr.setTextContent(username + "_" +password);
		} catch (SOAPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

	@Override
	public boolean handleFault(SOAPMessageContext context) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void close(MessageContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Set<QName> getHeaders() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getAddress(){
		InetAddress ip;
		try {
			ip = InetAddress.getLocalHost();
			return ip.getHostAddress();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}

