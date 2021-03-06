//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.09.12 at 10:28:58 PM IST 
//


package com.casa.jaxb.transaction;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


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
 *         &lt;element ref="{}Segment" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *       &lt;attribute name="Arrive" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       &lt;attribute name="Depart" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       &lt;attribute name="Duration" use="required" type="{http://www.w3.org/2001/XMLSchema}NMTOKEN" />
 *       &lt;attribute name="From" use="required" type="{http://www.w3.org/2001/XMLSchema}NCName" />
 *       &lt;attribute name="FromCountry" use="required" type="{http://www.w3.org/2001/XMLSchema}NCName" />
 *       &lt;attribute name="Stops" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="To" use="required" type="{http://www.w3.org/2001/XMLSchema}NCName" />
 *       &lt;attribute name="ToCountry" use="required" type="{http://www.w3.org/2001/XMLSchema}NCName" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "segment"
})
@XmlRootElement(name = "Flight")
public class Flight {

    @XmlElement(name = "Segment", required = true)
    protected List<Segment> segment;
    @XmlAttribute(name = "Arrive", required = true)
    @XmlSchemaType(name = "anySimpleType")
    protected String arrive;
    @XmlAttribute(name = "Depart", required = true)
    @XmlSchemaType(name = "anySimpleType")
    protected String depart;
    @XmlAttribute(name = "Duration", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NMTOKEN")
    protected String duration;
    @XmlAttribute(name = "From", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NCName")
    protected String from;
    @XmlAttribute(name = "FromCountry", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NCName")
    protected String fromCountry;
    @XmlAttribute(name = "Stops", required = true)
    protected BigInteger stops;
    @XmlAttribute(name = "To", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NCName")
    protected String to;
    @XmlAttribute(name = "ToCountry", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NCName")
    protected String toCountry;

    /**
     * Gets the value of the segment property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the segment property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSegment().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Segment }
     * 
     * 
     */
    public List<Segment> getSegment() {
        if (segment == null) {
            segment = new ArrayList<Segment>();
        }
        return this.segment;
    }

    /**
     * Gets the value of the arrive property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArrive() {
        return arrive;
    }

    /**
     * Sets the value of the arrive property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArrive(String value) {
        this.arrive = value;
    }

    /**
     * Gets the value of the depart property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDepart() {
        return depart;
    }

    /**
     * Sets the value of the depart property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDepart(String value) {
        this.depart = value;
    }

    /**
     * Gets the value of the duration property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDuration() {
        return duration;
    }

    /**
     * Sets the value of the duration property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDuration(String value) {
        this.duration = value;
    }

    /**
     * Gets the value of the from property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFrom() {
        return from;
    }

    /**
     * Sets the value of the from property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFrom(String value) {
        this.from = value;
    }

    /**
     * Gets the value of the fromCountry property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFromCountry() {
        return fromCountry;
    }

    /**
     * Sets the value of the fromCountry property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFromCountry(String value) {
        this.fromCountry = value;
    }

    /**
     * Gets the value of the stops property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getStops() {
        return stops;
    }

    /**
     * Sets the value of the stops property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setStops(BigInteger value) {
        this.stops = value;
    }

    /**
     * Gets the value of the to property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTo() {
        return to;
    }

    /**
     * Sets the value of the to property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTo(String value) {
        this.to = value;
    }

    /**
     * Gets the value of the toCountry property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getToCountry() {
        return toCountry;
    }

    /**
     * Sets the value of the toCountry property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setToCountry(String value) {
        this.toCountry = value;
    }

}
