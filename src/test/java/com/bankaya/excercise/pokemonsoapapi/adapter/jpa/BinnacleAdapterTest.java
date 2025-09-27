package com.bankaya.excercise.pokemonsoapapi.adapter.jpa;

import com.bankaya.excercise.pokemonsoapapi.domain.dto.BinnacleMessage;
import com.bankaya.excercise.pokemonsoapapi.repository.BinnacleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class BinnacleAdapterTest {

    @Mock
    private BinnacleRepository binnacleRepository;
    private BinnacleAdapter binnacleAdapter;

    @BeforeEach
    void setUp() {
        binnacleAdapter = new BinnacleAdapter(binnacleRepository);
    }

    @Test
    void saveBinnacleLog_ShouldCallRepositoryWithCorrectParams() {
        // Arrange
        BinnacleMessage message = new BinnacleMessage();
        message.setOriginIp("127.0.0.1");
        message.setRequestDate(LocalDateTime.of(2025, 9, 25, 12, 0));
        message.setExecutedMethod("getPokemon");
        message.setDurationTime(123L);
        message.setRequest("<request>data</request>");
        message.setResponse("<response>data</response>");

        // Act
        binnacleAdapter.saveBinnacleLog(message);

        // Assert
        verify(binnacleRepository, times(1)).saveBinnacleLog(
                "127.0.0.1",
                LocalDateTime.of(2025, 9, 25, 12, 0),
                "getPokemon",
                123L,
                "<request>data</request>",
                "<response>data</response>"
        );
    }
}