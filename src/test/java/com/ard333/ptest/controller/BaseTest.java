package com.ard333.ptest.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;

import lombok.Getter;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
public class BaseTest {
    
    @Autowired
    @Getter
    private MockMvc mockMvc;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public ResultActions performPost(String url, Object body) throws Exception {
        return mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(body))
        );
    }

    public ResultActions performGet(String url, LinkedMultiValueMap<String, String> requestParams) throws Exception {
        return mockMvc.perform(get(url)
                .params(requestParams)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
        );
    }

    public <T> String objectToJson(T object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> T mvcResultToObject(MvcResult result, Class<T> resultType) {
        try {
            return jsonToObject(result.getResponse().getContentAsString(), resultType);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T jsonToObject(String json, Class<T> resultType) {
        try {
            return objectMapper.readValue(json, resultType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
