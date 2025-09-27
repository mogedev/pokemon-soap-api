package com.bankaya.excercise.pokemonsoapapi.adapter.client;

import com.bankaya.excercise.pokemonsoapapi.clients.PokeApiClient;
import com.bankaya.excercise.pokemonsoapapi.model.client.Abilities;
import com.bankaya.excercise.pokemonsoapapi.model.client.HeldItem;
import com.bankaya.excercise.pokemonsoapapi.model.client.Pokemon;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PokemonApiAdapterTest {

    @Mock
    private PokeApiClient pokeApiClient;

    @InjectMocks
    private PokemonApiAdapter pokemonApiAdapter;

    @Test
    void getPokemonByName_WhenValidName_ShouldReturnPokemon() {
        // Arrange
        String pokemonName = "pikachu";
        var mockAbility = Mockito.mock(Abilities.class);
        var mockHeldItem = Mockito.mock(HeldItem.class);
        var mockResponse = new Pokemon(
                1,
                "pikachu",
                112,
                "location-url",
                List.of(mockAbility),
                List.of(mockHeldItem));

        when(pokeApiClient.getPokemonByName(pokemonName)).thenReturn(mockResponse);

        // Act
        com.bankaya.excercise.pokemonsoapapi.domain.data.Pokemon result = pokemonApiAdapter.getPokemonByName(pokemonName);

        // Assert
        Assertions.assertNotNull(result);
        Assertions.assertEquals("pikachu", result.getName());
        Assertions.assertEquals(112, result.getBaseExperience());
    }

}
