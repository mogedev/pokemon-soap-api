package com.bankaya.excercise.pokemonsoapapi.service;

import com.bankaya.excercise.pokemonsoapapi.domain.data.GetPokemonRequest;
import com.bankaya.excercise.pokemonsoapapi.domain.data.GetPokemonResponse;
import com.bankaya.excercise.pokemonsoapapi.ports.service.PokemonPort;
import com.bankaya.excercise.pokemonsoapapi.ports.spi.PokeApiSpi;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PokemonService implements PokemonPort {

    private final PokeApiSpi pokeApiSpi;

    @Override
    public GetPokemonResponse getPokemonByName(GetPokemonRequest pokemonRequest) {
        var pokemonName = pokemonRequest.getPokemonName();
        var pokemonResponse = pokeApiSpi.getPokemonByName(pokemonName);
        var getPokemonResponse = new GetPokemonResponse();
        getPokemonResponse.setPokemon(pokemonResponse);
        return getPokemonResponse;
    }

}
