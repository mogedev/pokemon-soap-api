package com.bankaya.excercise.pokemonsoapapi.api;

import com.bankaya.excercise.pokemonsoapapi.domain.data.GetPokemonRequest;
import com.bankaya.excercise.pokemonsoapapi.domain.data.GetPokemonResponse;
import com.bankaya.excercise.pokemonsoapapi.ports.service.PokemonPort;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class PokemonSoapApi {

    private static final String NAMESPACE_URI = "http://www.bankaya.com/pokemon-soap-api";
    private final PokemonPort pokemonPort;

    public PokemonSoapApi(PokemonPort pokemonPort) {
        this.pokemonPort = pokemonPort;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getPokemonRequest")
    @ResponsePayload
    public GetPokemonResponse getPokemonByName(@RequestPayload GetPokemonRequest pokemonRequest) {
        return pokemonPort.getPokemonByName(pokemonRequest);
    }

}
