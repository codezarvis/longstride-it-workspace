//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.09.12 at 10:28:58 PM IST 
//


package com.casa.jaxb.transaction;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{}Status"/>
 *         &lt;element ref="{}Confirmation"/>
 *         &lt;element ref="{}Affiliate"/>
 *         &lt;element ref="{}Agent"/>
 *         &lt;element ref="{}User"/>
 *         &lt;element ref="{}Air"/>
 *         &lt;element ref="{}Promotions"/>
 *         &lt;element ref="{}Financials"/>
 *         &lt;element ref="{}PaymentSchedules"/>
 *         &lt;element ref="{}Currency"/>
 *         &lt;element ref="{}Customers"/>
 *         &lt;element ref="{}Details"/>
 *       &lt;/sequence>
 *       &lt;attribute name="CreateUserID" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="ID" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="ModifyUserID" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "status",
    "confirmation",
    "affiliate",
    "agent",
    "user",
    "air",
    "promotions",
    "financials",
    "paymentSchedules",
    "currency",
    "customers",
    "details"
})
@XmlRootElement(name = "Booking")
public class Booking {

    @XmlElement(name = "Status", required = true)
    protected Status status;
    @XmlElement(name = "Confirmation", required = true)
    protected Confirmation confirmation;
    @XmlElement(name = "Affiliate", required = true)
    protected Affiliate affiliate;
    @XmlElement(name = "Agent", required = true)
    protected Agent agent;
    @XmlElement(name = "User", required = true)
    protected User user;
    @XmlElement(name = "Air", required = true)
    protected Air air;
    @XmlElement(name = "Promotions", required = true)
    protected Promotions promotions;
    @XmlElement(name = "Financials", required = true)
    protected Financials financials;
    @XmlElement(name = "PaymentSchedules", required = true)
    protected PaymentSchedules paymentSchedules;
    @XmlElement(name = "Currency", required = true)
    protected Currency currency;
    @XmlElement(name = "Customers", required = true)
    protected Customers customers;
    @XmlElement(name = "Details", required = true)
    protected Details details;
    @XmlAttribute(name = "CreateUserID", required = true)
    protected String createUserID;
    @XmlAttribute(name = "ID", required = true)
    protected String id;
    @XmlAttribute(name = "ModifyUserID", required = true)
    protected String modifyUserID;

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link Status }
     *     
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link Status }
     *     
     */
    public void setStatus(Status value) {
        this.status = value;
    }

    /**
     * Gets the value of the confirmation property.
     * 
     * @return
     *     possible object is
     *     {@link Confirmation }
     *     
     */
    public Confirmation getConfirmation() {
        return confirmation;
    }

    /**
     * Sets the value of the confirmation property.
     * 
     * @param value
     *     allowed object is
     *     {@link Confirmation }
     *     
     */
    public void setConfirmation(Confirmation value) {
        this.confirmation = value;
    }

    /**
     * Gets the value of the affiliate property.
     * 
     * @return
     *     possible object is
     *     {@link Affiliate }
     *     
     */
    public Affiliate getAffiliate() {
        return affiliate;
    }

    /**
     * Sets the value of the affiliate property.
     * 
     * @param value
     *     allowed object is
     *     {@link Affiliate }
     *     
     */
    public void setAffiliate(Affiliate value) {
        this.affiliate = value;
    }

    /**
     * Gets the value of the agent property.
     * 
     * @return
     *     possible object is
     *     {@link Agent }
     *     
     */
    public Agent getAgent() {
        return agent;
    }

    /**
     * Sets the value of the agent property.
     * 
     * @param value
     *     allowed object is
     *     {@link Agent }
     *     
     */
    public void setAgent(Agent value) {
        this.agent = value;
    }

    /**
     * Gets the value of the user property.
     * 
     * @return
     *     possible object is
     *     {@link User }
     *     
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets the value of the user property.
     * 
     * @param value
     *     allowed object is
     *     {@link User }
     *     
     */
    public void setUser(User value) {
        this.user = value;
    }

    /**
     * Gets the value of the air property.
     * 
     * @return
     *     possible object is
     *     {@link Air }
     *     
     */
    public Air getAir() {
        return air;
    }

    /**
     * Sets the value of the air property.
     * 
     * @param value
     *     allowed object is
     *     {@link Air }
     *     
     */
    public void setAir(Air value) {
        this.air = value;
    }

    /**
     * Gets the value of the promotions property.
     * 
     * @return
     *     possible object is
     *     {@link Promotions }
     *     
     */
    public Promotions getPromotions() {
        return promotions;
    }

    /**
     * Sets the value of the promotions property.
     * 
     * @param value
     *     allowed object is
     *     {@link Promotions }
     *     
     */
    public void setPromotions(Promotions value) {
        this.promotions = value;
    }

    /**
     * Gets the value of the financials property.
     * 
     * @return
     *     possible object is
     *     {@link Financials }
     *     
     */
    public Financials getFinancials() {
        return financials;
    }

    /**
     * Sets the value of the financials property.
     * 
     * @param value
     *     allowed object is
     *     {@link Financials }
     *     
     */
    public void setFinancials(Financials value) {
        this.financials = value;
    }

    /**
     * Gets the value of the paymentSchedules property.
     * 
     * @return
     *     possible object is
     *     {@link PaymentSchedules }
     *     
     */
    public PaymentSchedules getPaymentSchedules() {
        return paymentSchedules;
    }

    /**
     * Sets the value of the paymentSchedules property.
     * 
     * @param value
     *     allowed object is
     *     {@link PaymentSchedules }
     *     
     */
    public void setPaymentSchedules(PaymentSchedules value) {
        this.paymentSchedules = value;
    }

    /**
     * Gets the value of the currency property.
     * 
     * @return
     *     possible object is
     *     {@link Currency }
     *     
     */
    public Currency getCurrency() {
        return currency;
    }

    /**
     * Sets the value of the currency property.
     * 
     * @param value
     *     allowed object is
     *     {@link Currency }
     *     
     */
    public void setCurrency(Currency value) {
        this.currency = value;
    }

    /**
     * Gets the value of the customers property.
     * 
     * @return
     *     possible object is
     *     {@link Customers }
     *     
     */
    public Customers getCustomers() {
        return customers;
    }

    /**
     * Sets the value of the customers property.
     * 
     * @param value
     *     allowed object is
     *     {@link Customers }
     *     
     */
    public void setCustomers(Customers value) {
        this.customers = value;
    }

    /**
     * Gets the value of the details property.
     * 
     * @return
     *     possible object is
     *     {@link Details }
     *     
     */
    public Details getDetails() {
        return details;
    }

    /**
     * Sets the value of the details property.
     * 
     * @param value
     *     allowed object is
     *     {@link Details }
     *     
     */
    public void setDetails(Details value) {
        this.details = value;
    }

    /**
     * Gets the value of the createUserID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreateUserID() {
        return createUserID;
    }

    /**
     * Sets the value of the createUserID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreateUserID(String value) {
        this.createUserID = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getID() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setID(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the modifyUserID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getModifyUserID() {
        return modifyUserID;
    }

    /**
     * Sets the value of the modifyUserID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setModifyUserID(String value) {
        this.modifyUserID = value;
    }

}
