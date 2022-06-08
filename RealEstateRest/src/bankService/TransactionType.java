
package bankService;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for transactionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="transactionType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="amount" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="comment" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="fromAccount" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="toAccount" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "transactionType", propOrder = {
    "amount",
    "comment",
    "fromAccount",
    "toAccount"
})
public class TransactionType {

    protected double amount;
    @XmlElement(required = true)
    protected String comment;
    protected int fromAccount;
    protected int toAccount;

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
     * Gets the value of the comment property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComment() {
        return comment;
    }

    /**
     * Sets the value of the comment property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComment(String value) {
        this.comment = value;
    }

    /**
     * Gets the value of the fromAccount property.
     * 
     */
    public int getFromAccount() {
        return fromAccount;
    }

    /**
     * Sets the value of the fromAccount property.
     * 
     */
    public void setFromAccount(int value) {
        this.fromAccount = value;
    }

    /**
     * Gets the value of the toAccount property.
     * 
     */
    public int getToAccount() {
        return toAccount;
    }

    /**
     * Sets the value of the toAccount property.
     * 
     */
    public void setToAccount(int value) {
        this.toAccount = value;
    }

}
