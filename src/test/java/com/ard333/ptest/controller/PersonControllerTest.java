package com.ard333.ptest.controller;

import java.util.List;

import com.ard333.ptest.dto.PeopleByPostalCodeResponse;
import com.ard333.ptest.dto.PersonRequest;

import org.junit.jupiter.api.Test;
import org.springframework.util.LinkedMultiValueMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PersonControllerTest extends BaseTest {
    
    @Test
    void shouldReturnCorrectResponseWhenRequestingPeopleByPostalCodeRange() throws Exception {
        List<PersonRequest> people = List.of(
                PersonRequest.builder().name("Ard").postalCode("6000").build(),
                PersonRequest.builder().name("Oliver").postalCode("6001").build(),
                PersonRequest.builder().name("Mary").postalCode("6002").build(),
                PersonRequest.builder().name("John").postalCode("6003").build(),
                PersonRequest.builder().name("Peter").postalCode("6004").build()
        );
        performPost("/person", people).andExpect(status().isOk());

        LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("from", "6001");
        requestParams.add("to", "6003");

        PeopleByPostalCodeResponse peopleResponse = mvcResultToObject(
                performGet("/person/postal-code-range", requestParams).andExpect(status().isOk())
                        .andExpect(status().isOk())
                        .andReturn(),
                PeopleByPostalCodeResponse.class
        );
        assertThat(peopleResponse.getPeople()).hasSizeGreaterThanOrEqualTo(3);
    }

}
