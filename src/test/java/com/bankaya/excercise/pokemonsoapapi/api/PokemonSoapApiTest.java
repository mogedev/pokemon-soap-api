package com.bankaya.excercise.pokemonsoapapi.api;

import com.bankaya.excercise.pokemonsoapapi.domain.data.GetPokemonRequest;
import com.bankaya.excercise.pokemonsoapapi.domain.data.GetPokemonResponse;
import com.bankaya.excercise.pokemonsoapapi.domain.data.Pokemon;
import com.bankaya.excercise.pokemonsoapapi.ports.service.PokemonPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PokemonSoapApiTest {

    @Mock
    private PokemonPort pokemonPort;
    private PokemonSoapApi pokemonSoapApi;

    @BeforeEach
    void setUp() {
        pokemonSoapApi = new PokemonSoapApi(pokemonPort);
    }

    @Test
    void getPokemonByName_ShouldReturnExpectedResponse() {
        // Arrange
        GetPokemonRequest request = new GetPokemonRequest();
        request.setPokemonName("pikachu");
        Pokemon expectedPokemon = new Pokemon();
        expectedPokemon.setId(25);
        expectedPokemon.setName("pikachu");
        expectedPokemon.setBaseExperience(112);
        expectedPokemon.setLocationAreaEncounters("");
        GetPokemonResponse expectedResponse = new GetPokemonResponse();
        expectedResponse.setPokemon(expectedPokemon);

        when(pokemonPort.getPokemonByName(request)).thenReturn(expectedResponse);

        // Act
        GetPokemonResponse actualResponse = pokemonSoapApi.getPokemonByName(request);

        // Assert
        assertNotNull(actualResponse);
        assertNotNull(actualResponse.getPokemon());
        assertEquals("pikachu", actualResponse.getPokemon().getName());
        assertEquals(112, actualResponse.getPokemon().getBaseExperience());
        verify(pokemonPort, times(1)).getPokemonByName(request);
    }

    @Test
    void getPokemonByName_WhenPortReturnsNull_ShouldReturnNull() {
        // Arrange
        GetPokemonRequest request = new GetPokemonRequest();
        request.setPokemonName("unknown");
        when(pokemonPort.getPokemonByName(request)).thenReturn(null);

        // Act
        GetPokemonResponse actualResponse = pokemonSoapApi.getPokemonByName(request);

        // Assert
        assertNull(actualResponse);
        verify(pokemonPort, times(1)).getPokemonByName(request);
    }
}