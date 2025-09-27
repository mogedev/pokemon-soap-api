package com.bankaya.excercise.pokemonsoapapi.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BinnacleMessage {
        private String originIp;
        private LocalDateTime requestDate;
        private String executedMethod;
        private Long durationTime;
        private String request;
        private String response;
}
