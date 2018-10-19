package com.alimmit.reactlistapi.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc(secure = true)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class AbstractMockMvcTest {

    @Autowired
    protected MockMvc mockMvc;

    static final ObjectMapper MAPPER = new ObjectMapper();
    static {
        MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    protected boolean isDebugHttp() {
        return false;
    }

    @Test
    public void contextLoads() throws Exception {
    }

    <T> T doGet(final Class<T> type, final ResultMatcher expected, final String uri, final Object...uriVariables) throws Exception {
        return MAPPER.readerFor(type).readValue(doGet(expected, uri, uriVariables).getResponse().getContentAsString());
    }

    <T> List<T> doGetList(final TypeReference<List<T>> typeReference, final ResultMatcher expected, final String uri, final Object...uriVariables) throws Exception {
        return MAPPER.reader().forType(typeReference).readValue(doGet(expected, uri, uriVariables).getResponse().getContentAsString());
    }

    private MvcResult doGet(final ResultMatcher expected, final String uri, final Object...uriVariables) throws Exception {
        return mockMvc.perform(get(uri, uriVariables).contentType(MediaType.APPLICATION_JSON)).andExpect(expected).andDo(mvcResult -> {if (isDebugHttp()) print();}).andReturn();
    }
}
