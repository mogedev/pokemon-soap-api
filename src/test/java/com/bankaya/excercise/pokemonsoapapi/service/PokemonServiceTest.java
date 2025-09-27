package com.bankaya.excercise.pokemonsoapapi.service;

import com.bankaya.excercise.pokemonsoapapi.domain.data.GetPokemonRequest;
import com.bankaya.excercise.pokemonsoapapi.domain.data.GetPokemonResponse;
import com.bankaya.excercise.pokemonsoapapi.domain.data.Pokemon;
import com.bankaya.excercise.pokemonsoapapi.ports.spi.PokeApiSpi;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PokemonServiceTest {

    @Mock
    private PokeApiSpi pokeApiSpi;

    @InjectMocks
    private PokemonService pokemonService;

    @Test
    void getPokemonByName_WhenPokemonExists_ShouldReturnResponse() {
        // Arrange
        String pokemonName = "pikachu";
        GetPokemonRequest request = new GetPokemonRequest();
        request.setPokemonName(pokemonName);

        Pokemon pokemon = new Pokemon();
        pokemon.setName(pokemonName);
        pokemon.setBaseExperience(112);

        when(pokeApiSpi.getPokemonByName(pokemonName)).thenReturn(pokemon);

        // Act
        GetPokemonResponse response = pokemonService.getPokemonByName(request);

        // Assert
        assertNotNull(response);
        assertNotNull(response.getPokemon());
        assertEquals(pokemonName, response.getPokemon().getName());
        assertEquals(112, response.getPokemon().getBaseExperience());
        verify(pokeApiSpi).getPokemonByName(pokemonName);
    }

    @Test
    void getPokemonByName_WhenPokemonNotFound_ShouldReturnNull() {
        // Arrange
        String pokemonName = "nonexistent";
        GetPokemonRequest request = new GetPokemonRequest();
        request.setPokemonName(pokemonName);

        when(pokeApiSpi.getPokemonByName(pokemonName)).thenReturn(null);

        // Act
        GetPokemonResponse response = pokemonService.getPokemonByName(request);

        // Assert
        assertNotNull(response);
        assertNull(response.getPokemon());
        verify(pokeApiSpi).getPokemonByName(pokemonName);
    }

    @Test
    void getPokemonByName_WhenRequestIsNull_ShouldHandleGracefully() {
        // Act & Assert
        assertThrows(NullPointerException.class, () -> pokemonService.getPokemonByName(null));
        verify(pokeApiSpi, never()).getPokemonByName(any());
    }
}