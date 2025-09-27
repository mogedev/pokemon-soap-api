package com.bankaya.excercise.pokemonsoapapi.model.client;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record HeldItem(
    Item item,
    @JsonProperty(value = "version_details")
    List<VersionDetail> versionDetails
) {
}
