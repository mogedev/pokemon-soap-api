package com.bankaya.excercise.pokemonsoapapi.ports.service;

import com.bankaya.excercise.pokemonsoapapi.domain.data.GetPokemonRequest;
import com.bankaya.excercise.pokemonsoapapi.domain.data.GetPokemonResponse;

public interface PokemonPort {
    GetPokemonResponse getPokemonByName(GetPokemonRequest pokemonRequest);
}
