package com.bankaya.excercise.pokemonsoapapi.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

import static com.bankaya.excercise.pokemonsoapapi.constants.SQLConstants.*;

@Entity(name = BINNACLE_TABLE_NAME)
public class Binnacle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = BINNACLE_ID_COLUMN, nullable = false)
    private Integer id;
    @Column(name = ORIGIN_IP_COLUMN, nullable = false)
    private String originIp;
    @Column(name = REQUEST_DATE_COLUMN, nullable = false)
    private LocalDateTime requestDate;
    @Column(name = EXECUTED_METHOD_COLUMN, nullable = false)
    private String executedMethod;
    @Column(name = DURATION_TIME_COLUMN, nullable = false)
    private Long durationTime;
    @Column(name = REQUEST_COLUMN, columnDefinition = "TEXT", nullable = false)
    private String request;
    @Column(name = RESPONSE_COLUMN, columnDefinition = "TEXT", nullable = false)
    private String response;
}
