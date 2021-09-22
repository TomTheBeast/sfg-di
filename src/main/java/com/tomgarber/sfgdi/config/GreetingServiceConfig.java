package com.tomgarber.sfgdi.config;

import com.tomgarber.pets.PetService;
import com.tomgarber.pets.PetServiceFactory;
import com.tomgarber.sfgdi.datasource.FakeDataSource;
import com.tomgarber.sfgdi.repositories.EnglishGreetingRepository;
import com.tomgarber.sfgdi.repositories.EnglishGreetingRepositoryImpl;
import com.tomgarber.sfgdi.services.*;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.*;

@EnableConfigurationProperties(Sf5ConstructorConfig.class)
@ImportResource("classpath:sfgdi-config.xml")
@Configuration
public class GreetingServiceConfig {

    /*@Bean
    FakeDataSource fakeDataSource(@Value("${com.username}") String username, @Value("${com.password}") String password, @Value("${com.jdbcurl}") String jdbcurl) {
        FakeDataSource fakeDataSource = new FakeDataSource();
        fakeDataSource.setUsername(username);
        fakeDataSource.setPassword(password);
        fakeDataSource.setJdbcurl(jdbcurl);
        return fakeDataSource;
    }*/

    @Bean
    FakeDataSource fakeDataSource(Sf5ConstructorConfig sf5ConstructorConfig) {
        FakeDataSource fakeDataSource = new FakeDataSource();
        fakeDataSource.setUsername(sf5ConstructorConfig.getUsername());
        fakeDataSource.setPassword(sf5ConstructorConfig.getPassword());
        fakeDataSource.setJdbcurl(sf5ConstructorConfig.getJdbcurl());
        return fakeDataSource;
    }


    @Bean
    PetServiceFactory petServiceFactory() {
        return new PetServiceFactory();
    }

    @Profile({"dog", "default"})
    @Bean
    PetService dogPetService() {
        return petServiceFactory().getPetService("dog");
    }

    @Profile("cat")
    @Bean
    PetService catPetService() {
        return petServiceFactory().getPetService("cat");
    }

    @Profile("ES")
    @Bean("i18nService")
    I18NSpanishService i18NSpanishService() {
        return new I18NSpanishService();
    }

    @Bean
    EnglishGreetingRepository englishGreetingRepository(){
        return new EnglishGreetingRepositoryImpl();
    }

    @Profile({"EN", "Default"})
    @Bean("i18nService")
    I18nEnglishGreetingService i18nEnglishGreetingService(EnglishGreetingRepository englishGreetingRepository) {
        return new I18nEnglishGreetingService(englishGreetingRepository);
    }

    @Primary
    @Bean
    PrimaryGreetingService primaryGreetingService() {
        return new PrimaryGreetingService();
    }

    @Bean
    PropertyInjectedGreetingService propertyInjectedGreetingService() {
        return new PropertyInjectedGreetingService();
    }

    @Bean
    SetterInjectedGreetingService setterInjectedGreetingService() {
        return new SetterInjectedGreetingService();
    }

}
