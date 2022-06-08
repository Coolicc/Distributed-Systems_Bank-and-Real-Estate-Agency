package handlers;

import java.util.Iterator;
import java.util.Set;

import javax.inject.Inject;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFault;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPHeaderElement;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import javax.xml.ws.soap.SOAPFaultException;

import beans.UserBean;
import model.UserB;


public class UserHandler implements SOAPHandler<SOAPMessageContext> {
	
	@Inject
	UserBean ub;

	@Override
	public boolean handleMessage(SOAPMessageContext context) {
		if (((Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY)).booleanValue())
			return true;

		SOAPMessage msg = context.getMessage();
		SOAPHeader hdr;

		try {
			hdr = msg.getSOAPHeader();
			QName ADDRESS_HEADER = new QName("mynamespace", "address");
			Iterator<SOAPHeaderElement> i = hdr.examineAllHeaderElements();
			SOAPHeaderElement hdrblock = null;
			boolean ok = false;
			while (i.hasNext() && !ok) {
				hdrblock = i.next();
				if (hdrblock.getElementQName().equals(ADDRESS_HEADER))
					ok = true;
			}

			if (!ok)
				System.out.println("Random random");
			String ip = hdrblock.getElementsByTagName("username_password").item(0).getTextContent();
			String[] tok = ip.split("_");
			UserB user = ub.getUserByUsername(tok[0]);
			if (user == null && !user.getPassword().equals(tok[1])) {
				System.out.println("Nepostojeci klijent.");
				return false;
			}

		} catch (Exception e) {
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

}
