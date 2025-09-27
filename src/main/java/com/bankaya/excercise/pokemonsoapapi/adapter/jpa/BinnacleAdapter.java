package com.bankaya.excercise.pokemonsoapapi.adapter.jpa;

import com.bankaya.excercise.pokemonsoapapi.domain.dto.BinnacleMessage;
import com.bankaya.excercise.pokemonsoapapi.ports.spi.BinnaclePort;
import com.bankaya.excercise.pokemonsoapapi.repository.BinnacleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class BinnacleAdapter implements BinnaclePort {

    private final BinnacleRepository binnacleRepository;

    @Override
    public void saveBinnacleLog(BinnacleMessage binnacleMessage) {
        binnacleRepository.saveBinnacleLog(
                binnacleMessage.getOriginIp(),
                binnacleMessage.getRequestDate(),
                binnacleMessage.getExecutedMethod(),
                binnacleMessage.getDurationTime(),
                binnacleMessage.getRequest(),
                binnacleMessage.getResponse()
        );
    }
}
