package com.ard333.ptest.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import com.ard333.ptest.dto.PeopleByPostalCodeResponse;
import com.ard333.ptest.dto.PersonRequest;
import com.ard333.ptest.entity.Person;
import com.ard333.ptest.repository.PersonRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {
    
    @Mock
    private PersonRepository personRepository;

    private PersonService personService;

    @BeforeEach
    public void setup() {
        personService = new PersonService(personRepository);
    }

    @Test
    void shouldSaveAllPerson() {
        List<PersonRequest> people = List.of(
                PersonRequest.builder().name("Ard").postalCode("6000").build(),
                PersonRequest.builder().name("Oliver").postalCode("6001").build(),
                PersonRequest.builder().name("Mary").postalCode("6002").build()
        );
        personService.save(people);
        verify(personRepository, times(people.size())).save(any(Person.class));
    }

    @Test
    void shouldReturnCorrectResponseWhenRequestingPeopleByPostalCodeRange() {
        List<Person> people = List.of(
                Person.builder().name("Oliver").postalCode("6000").build(),
                Person.builder().name("John").postalCode("6001").build(),
                Person.builder().name("Peter").postalCode("6002").build()
        );
        String from = "6000";
        String to = "6002";
        when(personRepository.findByPostalCodeBetweenOrderByNameAsc(from, to)).thenReturn(people);
        
        PeopleByPostalCodeResponse response = personService.findByPostalCodeBetween(from, to);

        assertThat(response.getTotalNamesChars()).isEqualTo(15);
        assertThat(response.getPeople()).isEqualTo(people);
    }

}
