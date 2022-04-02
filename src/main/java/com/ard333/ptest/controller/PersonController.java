package com.ard333.ptest.controller;

import java.util.List;

import com.ard333.ptest.dto.PeopleByPostalCodeResponse;
import com.ard333.ptest.dto.PersonRequest;
import com.ard333.ptest.service.PersonService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/person")
@RequiredArgsConstructor
public class PersonController {
    
    private final PersonService personService;

    @GetMapping("/postal-code-range")
    public PeopleByPostalCodeResponse findByRangePostalCode(@RequestParam String from, @RequestParam String to) {
        return personService.findByPostalCodeBetween(from, to);
    }

    @PostMapping
    public void save(@RequestBody List<PersonRequest> people) {
        personService.save(people);
    }
}
