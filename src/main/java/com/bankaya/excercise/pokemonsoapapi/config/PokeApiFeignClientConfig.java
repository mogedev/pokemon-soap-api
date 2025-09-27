package com.bankaya.excercise.pokemonsoapapi.config;

import com.bankaya.excercise.pokemonsoapapi.component.PokeApiErrorDecoder;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PokeApiFeignClientConfig {

    @Bean
    public ErrorDecoder errorDecoder() {
        return new PokeApiErrorDecoder();
    }

}
