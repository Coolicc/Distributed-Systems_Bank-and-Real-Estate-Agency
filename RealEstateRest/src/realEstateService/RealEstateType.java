
package realEstateService;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for realEstateType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="realEstateType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="realEstateID" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="price" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="sold" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="bankAccount" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="picture" type="{http://www.w3.org/2001/XMLSchema}byte" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="buyer" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="seller" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="address" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="city" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "realEstateType", propOrder = {
    "realEstateID",
    "description",
    "name",
    "price",
    "sold",
    "bankAccount",
    "picture",
    "buyer",
    "seller",
    "address",
    "city"
})
public class RealEstateType {

    protected int realEstateID;
    @XmlElement(required = true)
    protected String description;
    @XmlElement(required = true)
    protected String name;
    protected double price;
    protected boolean sold;
    protected int bankAccount;
    @XmlElement(type = Byte.class)
    protected List<Byte> picture;
    protected int buyer;
    protected int seller;
    @XmlElement(required = true)
    protected String address;
    @XmlElement(required = true)
    protected String city;

    /**
     * Gets the value of the realEstateID property.
     * 
     */
    public int getRealEstateID() {
        return realEstateID;
    }

    /**
     * Sets the value of the realEstateID property.
     * 
     */
    public void setRealEstateID(int value) {
        this.realEstateID = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the price property.
     * 
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the value of the price property.
     * 
     */
    public void setPrice(double value) {
        this.price = value;
    }

    /**
     * Gets the value of the sold property.
     * 
     */
    public boolean isSold() {
        return sold;
    }

    /**
     * Sets the value of the sold property.
     * 
     */
    public void setSold(boolean value) {
        this.sold = value;
    }

    /**
     * Gets the value of the bankAccount property.
     * 
     */
    public int getBankAccount() {
        return bankAccount;
    }

    /**
     * Sets the value of the bankAccount property.
     * 
     */
    public void setBankAccount(int value) {
        this.bankAccount = value;
    }

    /**
     * Gets the value of the picture property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the picture property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPicture().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Byte }
     * 
     * 
     */
    public List<Byte> getPicture() {
        if (picture == null) {
            picture = new ArrayList<Byte>();
        }
        return this.picture;
    }

    /**
     * Gets the value of the buyer property.
     * 
     */
    public int getBuyer() {
        return buyer;
    }

    /**
     * Sets the value of the buyer property.
     * 
     */
    public void setBuyer(int value) {
        this.buyer = value;
    }

    /**
     * Gets the value of the seller property.
     * 
     */
    public int getSeller() {
        return seller;
    }

    /**
     * Sets the value of the seller property.
     * 
     */
    public void setSeller(int value) {
        this.seller = value;
    }

    /**
     * Gets the value of the address property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the value of the address property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddress(String value) {
        this.address = value;
    }

    /**
     * Gets the value of the city property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the value of the city property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCity(String value) {
        this.city = value;
    }

}
