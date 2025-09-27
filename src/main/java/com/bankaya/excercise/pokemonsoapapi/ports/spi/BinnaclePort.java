package com.bankaya.excercise.pokemonsoapapi.ports.spi;

import com.bankaya.excercise.pokemonsoapapi.domain.dto.BinnacleMessage;

public interface BinnaclePort {
    void saveBinnacleLog(BinnacleMessage binnacleMessage);
}
