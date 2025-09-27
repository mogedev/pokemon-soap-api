package com.bankaya.excercise.pokemonsoapapi.repository;

import com.bankaya.excercise.pokemonsoapapi.entity.Binnacle;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

import static com.bankaya.excercise.pokemonsoapapi.constants.SQLConstants.*;

@Repository
public interface BinnacleRepository extends JpaRepository<Binnacle, Integer> {

    @Modifying
    @Transactional
    @Query(value = INSERT_BINNACLE_LOG, nativeQuery = true)
    void saveBinnacleLog(@Param(ORIGIN_IP_PARAM) String originIp,
                         @Param(REQUEST_DATE_PARAM) LocalDateTime requestDate,
                         @Param(EXECUTED_METHOD_PARAM) String executedMethod,
                         @Param(DURATION_TIME_PARAM) Long durationTime,
                         @Param(REQUEST_PARAM) String request,
                         @Param(RESPONSE_PARAM) String response);

}
