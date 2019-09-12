package com.gabrielbergamim.cursomc.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.gabrielbergamim.cursomc.services.DbService;
import com.gabrielbergamim.cursomc.services.EmailService;
import com.gabrielbergamim.cursomc.services.MockEmailService;
import com.gabrielbergamim.cursomc.services.SmtpEmailService;

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
    
    /*@Bean
    public EmailService emailService() {
    	return new MockEmailService();
    }*/
    
    @Bean
    public EmailService emailService() {
    	return new SmtpEmailService();
    }
}
