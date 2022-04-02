package com.ard333.ptest.service;

import java.util.List;

import com.ard333.ptest.dto.PeopleByPostalCodeResponse;
import com.ard333.ptest.dto.PersonRequest;
import com.ard333.ptest.entity.Person;
import com.ard333.ptest.repository.PersonRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PersonService {
    
    private final PersonRepository personRepository;

    public void save(List<PersonRequest> people) {
        for (PersonRequest person : people) {
            Person p = Person.builder()
                    .name(person.getName())
                    .postalCode(person.getPostalCode())
                    .build();
            personRepository.save(p);
        }
    }

    public PeopleByPostalCodeResponse findByPostalCodeBetween(String from, String to) {
        List<Person> people = personRepository.findByPostalCodeBetweenOrderByNameAsc(from, to);

        //Another alternative is to get total chars of name with query
        //SELECT SUM(LENGTH(name)) FROM Person p WHERE p.postalCode BETWEEN :from AND :to
        Long totalNamesChars = people.stream()
                .map(Person::getName)
                .mapToLong(String::length)
                .sum();

        return PeopleByPostalCodeResponse.builder()
                .people(people)
                .totalNamesChars(totalNamesChars)
                .build();
    }

}
