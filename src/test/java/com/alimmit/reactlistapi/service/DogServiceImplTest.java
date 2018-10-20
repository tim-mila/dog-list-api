package com.alimmit.reactlistapi.service;

import com.alimmit.reactlistapi.ReactListApiApplicationTests;
import com.alimmit.reactlistapi.controller.Breed;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class DogServiceImplTest extends ReactListApiApplicationTests {

    @Autowired
    private DogService dogService;

    @Test
    public void breeds() {
        final Iterable<Breed> breeds = dogService.breeds();
        assertNotNull(breeds);
        assertTrue(breeds.iterator().hasNext());
    }

    @Test
    public void breedsFiltered() {
        final Iterable<Breed> breeds = dogService.breeds("viz");
        assertNotNull(breeds);
        assertTrue(breeds.iterator().hasNext());
    }
}