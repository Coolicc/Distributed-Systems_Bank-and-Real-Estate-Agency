
package bankService;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for creditType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="creditType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="creditID" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="amount" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="amountPaidOff" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="account" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="paidOff" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "creditType", propOrder = {
    "creditID",
    "amount",
    "amountPaidOff",
    "account",
    "paidOff"
})
public class CreditType {

    protected int creditID;
    protected double amount;
    protected double amountPaidOff;
    protected int account;
    protected boolean paidOff;

    /**
     * Gets the value of the creditID property.
     * 
     */
    public int getCreditID() {
        return creditID;
    }

    /**
     * Sets the value of the creditID property.
     * 
     */
    public void setCreditID(int value) {
        this.creditID = value;
    }

    /**
     * Gets the value of the amount property.
     * 
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Sets the value of the amount property.
     * 
     */
    public void setAmount(double value) {
        this.amount = value;
    }

    /**
     * Gets the value of the amountPaidOff property.
     * 
     */
    public double getAmountPaidOff() {
        return amountPaidOff;
    }

    /**
     * Sets the value of the amountPaidOff property.
     * 
     */
    public void setAmountPaidOff(double value) {
        this.amountPaidOff = value;
    }

    /**
     * Gets the value of the account property.
     * 
     */
    public int getAccount() {
        return account;
    }

    /**
     * Sets the value of the account property.
     * 
     */
    public void setAccount(int value) {
        this.account = value;
    }

    /**
     * Gets the value of the paidOff property.
     * 
     */
    public boolean isPaidOff() {
        return paidOff;
    }

    /**
     * Sets the value of the paidOff property.
     * 
     */
    public void setPaidOff(boolean value) {
        this.paidOff = value;
    }

}
