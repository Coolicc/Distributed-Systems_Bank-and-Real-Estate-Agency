
package realEstateService;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for commentType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="commentType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="posterID" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="userID" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="rating" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="text" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="commentID" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "commentType", propOrder = {
    "posterID",
    "userID",
    "rating",
    "text",
    "commentID"
})
public class CommentType {

    protected int posterID;
    protected int userID;
    protected int rating;
    @XmlElement(required = true)
    protected String text;
    @XmlElement(required = true)
    protected String commentID;

    /**
     * Gets the value of the posterID property.
     * 
     */
    public int getPosterID() {
        return posterID;
    }

    /**
     * Sets the value of the posterID property.
     * 
     */
    public void setPosterID(int value) {
        this.posterID = value;
    }

    /**
     * Gets the value of the userID property.
     * 
     */
    public int getUserID() {
        return userID;
    }

    /**
     * Sets the value of the userID property.
     * 
     */
    public void setUserID(int value) {
        this.userID = value;
    }

    /**
     * Gets the value of the rating property.
     * 
     */
    public int getRating() {
        return rating;
    }

    /**
     * Sets the value of the rating property.
     * 
     */
    public void setRating(int value) {
        this.rating = value;
    }

    /**
     * Gets the value of the text property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getText() {
        return text;
    }

    /**
     * Sets the value of the text property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setText(String value) {
        this.text = value;
    }

    /**
     * Gets the value of the commentID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCommentID() {
        return commentID;
    }

    /**
     * Sets the value of the commentID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCommentID(String value) {
        this.commentID = value;
    }

}
