package com.bankaya.excercise.pokemonsoapapi.clients;

import com.bankaya.excercise.pokemonsoapapi.config.PokeApiFeignClientConfig;
import com.bankaya.excercise.pokemonsoapapi.model.client.Pokemon;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "pokeapi-client", configuration = PokeApiFeignClientConfig.class)
public interface PokeApiClient {

    @GetMapping("/pokemon/{pokemonName}")
    Pokemon getPokemonByName(@PathVariable("pokemonName") String pokemonName);

}
