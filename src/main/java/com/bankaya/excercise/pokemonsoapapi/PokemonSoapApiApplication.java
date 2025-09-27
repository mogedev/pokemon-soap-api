package com.bankaya.excercise.pokemonsoapapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PokemonSoapApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(PokemonSoapApiApplication.class, args);
    }

}
