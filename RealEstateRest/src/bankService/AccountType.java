
package bankService;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AccountType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AccountType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="accountID" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="balance" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="owner" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AccountType", propOrder = {
    "accountID",
    "balance",
    "owner"
})
public class AccountType {

    protected int accountID;
    protected double balance;
    protected int owner;

    /**
     * Gets the value of the accountID property.
     * 
     */
    public int getAccountID() {
        return accountID;
    }

    /**
     * Sets the value of the accountID property.
     * 
     */
    public void setAccountID(int value) {
        this.accountID = value;
    }

    /**
     * Gets the value of the balance property.
     * 
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Sets the value of the balance property.
     * 
     */
    public void setBalance(double value) {
        this.balance = value;
    }

    /**
     * Gets the value of the owner property.
     * 
     */
    public int getOwner() {
        return owner;
    }

    /**
     * Sets the value of the owner property.
     * 
     */
    public void setOwner(int value) {
        this.owner = value;
    }

}
