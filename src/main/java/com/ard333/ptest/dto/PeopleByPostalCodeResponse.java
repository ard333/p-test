package com.ard333.ptest.dto;

import java.util.List;

import com.ard333.ptest.entity.Person;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PeopleByPostalCodeResponse {
    
    private List<Person> people;

    private Long totalNamesChars;
}
