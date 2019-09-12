package com.gabrielbergamim.cursomc.config;

import com.gabrielbergamim.cursomc.services.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;

@Configuration
@Profile("test")
public class TestConfig  {

    @Autowired
    private DbService dbService;

    @Bean
    public boolean instantiateDataBase() throws ParseException {

        dbService.instantiateDataBase();

        return true;
    }
}
