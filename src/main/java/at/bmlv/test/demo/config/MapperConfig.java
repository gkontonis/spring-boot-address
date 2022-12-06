package at.bmlv.test.demo.config;

import at.bmlv.test.demo.domain.Place;
import at.bmlv.test.demo.mapper.AddressMapper;
import at.bmlv.test.demo.mapper.CountryMapper;
import at.bmlv.test.demo.mapper.PersonMapper;
import at.bmlv.test.demo.mapper.Person_AddressMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    public AddressMapper createAddressMapper() {
        return new AddressMapper();
    }

    @Bean
    public CountryMapper createCountryMapper() {
        return new CountryMapper();
    }

    @Bean
    public PersonMapper createPersonMapper(Person_AddressMapper mapper) {
        return new PersonMapper(mapper);
    }

    @Bean
    public Person_AddressMapper createPerson_AddressMapper() {
        return new Person_AddressMapper();
    }

    @Bean
    public Place createPlace() {
        return new Place();
    }

}
