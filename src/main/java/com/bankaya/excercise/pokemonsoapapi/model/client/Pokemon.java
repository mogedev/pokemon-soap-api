package com.bankaya.excercise.pokemonsoapapi.model.client;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record Pokemon(
    int id,
    String name,
    @JsonProperty(value="base_experience")
    Integer baseExperience,
    @JsonProperty(value="location_area_encounters")
    String locationAreaEncounters,
    List<Abilities> abilities,
    @JsonProperty(value = "held_items")
    List<HeldItem> heldItems
) {
}
