package com.bankaya.excercise.pokemonsoapapi.model.client;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Abilities(
        Ability ability,
        @JsonProperty(value = "is_hidden")
        Boolean isHidden,
        Integer slot
) {
}
