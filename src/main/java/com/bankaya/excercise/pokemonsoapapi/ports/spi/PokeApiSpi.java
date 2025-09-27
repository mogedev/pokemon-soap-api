package com.bankaya.excercise.pokemonsoapapi.ports.spi;

import com.bankaya.excercise.pokemonsoapapi.domain.data.Pokemon;

public interface PokeApiSpi {
    Pokemon getPokemonByName(String pokemonName);
}
