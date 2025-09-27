//
// Este archivo ha sido generado por Eclipse Implementation of JAXB v3.0.0 
// Visite https://eclipse-ee4j.github.io/jaxb-ri 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2025.09.26 a las 11:39:54 PM CST 
//


package com.bankaya.excercise.pokemonsoapapi.domain.data;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para heldItemType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="heldItemType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="item" type="{http://www.bankaya.com/pokemon-soap-api}itemType"/&gt;
 *         &lt;element name="version_details" type="{http://www.bankaya.com/pokemon-soap-api}versionDetailsListType"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "heldItemType", propOrder = {
    "item",
    "versionDetails"
})
public class HeldItemType {

    @XmlElement(required = true)
    protected ItemType item;
    @XmlElement(name = "version_details", required = true)
    protected VersionDetailsListType versionDetails;

    /**
     * Obtiene el valor de la propiedad item.
     * 
     * @return
     *     possible object is
     *     {@link ItemType }
     *     
     */
    public ItemType getItem() {
        return item;
    }

    /**
     * Define el valor de la propiedad item.
     * 
     * @param value
     *     allowed object is
     *     {@link ItemType }
     *     
     */
    public void setItem(ItemType value) {
        this.item = value;
    }

    /**
     * Obtiene el valor de la propiedad versionDetails.
     * 
     * @return
     *     possible object is
     *     {@link VersionDetailsListType }
     *     
     */
    public VersionDetailsListType getVersionDetails() {
        return versionDetails;
    }

    /**
     * Define el valor de la propiedad versionDetails.
     * 
     * @param value
     *     allowed object is
     *     {@link VersionDetailsListType }
     *     
     */
    public void setVersionDetails(VersionDetailsListType value) {
        this.versionDetails = value;
    }

}
