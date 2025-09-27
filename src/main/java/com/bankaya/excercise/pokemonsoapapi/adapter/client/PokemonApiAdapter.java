package com.bankaya.excercise.pokemonsoapapi.adapter.client;

import com.bankaya.excercise.pokemonsoapapi.adapter.mapper.PokemonMapper;
import com.bankaya.excercise.pokemonsoapapi.clients.PokeApiClient;
import com.bankaya.excercise.pokemonsoapapi.domain.data.Pokemon;
import com.bankaya.excercise.pokemonsoapapi.ports.spi.PokeApiSpi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PokemonApiAdapter implements PokeApiSpi {

    private final PokeApiClient pokeApiClient;

    @Override
    public Pokemon getPokemonByName(String pokemonName) {
        var pokemonResponse = pokeApiClient.getPokemonByName(pokemonName);
        return PokemonMapper.INSTANCE.pokemonToPokemonDTO(pokemonResponse);
    }
}
