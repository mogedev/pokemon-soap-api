//
// Este archivo ha sido generado por Eclipse Implementation of JAXB v3.0.0 
// Visite https://eclipse-ee4j.github.io/jaxb-ri 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2025.09.26 a las 11:39:54 PM CST 
//


package com.bankaya.excercise.pokemonsoapapi.domain.data;

import jakarta.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.bankaya.excercise.pokemonsoapapi.domain.data package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.bankaya.excercise.pokemonsoapapi.domain.data
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetPokemonRequest }
     * 
     */
    public GetPokemonRequest createGetPokemonRequest() {
        return new GetPokemonRequest();
    }

    /**
     * Create an instance of {@link GetPokemonResponse }
     * 
     */
    public GetPokemonResponse createGetPokemonResponse() {
        return new GetPokemonResponse();
    }

    /**
     * Create an instance of {@link Pokemon }
     * 
     */
    public Pokemon createPokemon() {
        return new Pokemon();
    }

    /**
     * Create an instance of {@link Abilities }
     * 
     */
    public Abilities createAbilities() {
        return new Abilities();
    }

    /**
     * Create an instance of {@link AbilityType }
     * 
     */
    public AbilityType createAbilityType() {
        return new AbilityType();
    }

    /**
     * Create an instance of {@link AbilityDetailsType }
     * 
     */
    public AbilityDetailsType createAbilityDetailsType() {
        return new AbilityDetailsType();
    }

    /**
     * Create an instance of {@link HeldItems }
     * 
     */
    public HeldItems createHeldItems() {
        return new HeldItems();
    }

    /**
     * Create an instance of {@link HeldItemType }
     * 
     */
    public HeldItemType createHeldItemType() {
        return new HeldItemType();
    }

    /**
     * Create an instance of {@link ItemType }
     * 
     */
    public ItemType createItemType() {
        return new ItemType();
    }

    /**
     * Create an instance of {@link VersionDetailsListType }
     * 
     */
    public VersionDetailsListType createVersionDetailsListType() {
        return new VersionDetailsListType();
    }

    /**
     * Create an instance of {@link VersionDetailType }
     * 
     */
    public VersionDetailType createVersionDetailType() {
        return new VersionDetailType();
    }

    /**
     * Create an instance of {@link VersionType }
     * 
     */
    public VersionType createVersionType() {
        return new VersionType();
    }

}
