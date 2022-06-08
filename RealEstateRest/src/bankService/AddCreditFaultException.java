
package bankService;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 3.2.7
 * 2019-06-14T12:57:17.770+02:00
 * Generated source version: 3.2.7
 */

@WebFault(name = "addCreditFault", targetNamespace = "http://www.example.org/Bank/")
public class AddCreditFaultException extends Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private bankService.AddCreditFault addCreditFault;

    public AddCreditFaultException() {
        super();
    }

    public AddCreditFaultException(String message) {
        super(message);
    }

    public AddCreditFaultException(String message, java.lang.Throwable cause) {
        super(message, cause);
    }

    public AddCreditFaultException(String message, bankService.AddCreditFault addCreditFault) {
        super(message);
        this.addCreditFault = addCreditFault;
    }

    public AddCreditFaultException(String message, bankService.AddCreditFault addCreditFault, java.lang.Throwable cause) {
        super(message, cause);
        this.addCreditFault = addCreditFault;
    }

    public bankService.AddCreditFault getFaultInfo() {
        return this.addCreditFault;
    }
}