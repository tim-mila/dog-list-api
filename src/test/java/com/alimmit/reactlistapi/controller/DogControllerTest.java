package com.alimmit.reactlistapi.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.Assert.assertTrue;

public class DogControllerTest extends AbstractMockMvcTest {

    @Test
    public void breeds() throws Exception {
        final List<Breed> breeds = doGetList(new TypeReference<List<Breed>>() {}, status().isOk(), DogController.PATH);
        assertTrue(CollectionUtils.isNotEmpty(breeds));
        assertTrue(StringUtils.isNotBlank(breeds.iterator().next().getName()));
    }

    @Test
    public void breeds1() throws Exception {
        final List<Breed> breeds = doGetList(new TypeReference<List<Breed>>() {}, status().isOk(), DogController.PATH + "?q={q}", "viz");
        assertTrue(CollectionUtils.isNotEmpty(breeds));
        final Breed found = breeds.stream().filter(breed -> StringUtils.equalsIgnoreCase(breed.getName(), "vizsla")).findFirst().orElseThrow(() -> new RuntimeException("No breed found matching filter"));
        assertEquals("vizsla", found.getName());
    }
}