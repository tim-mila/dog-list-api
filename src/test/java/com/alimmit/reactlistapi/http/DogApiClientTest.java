package com.alimmit.reactlistapi.http;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DogApiClientTest {

    private DogApiClient client;

    @Before
    public void setUp() throws Exception {
        client = new DogApiClient();
    }

    @Test
    public void get() {
        BreedListMessage message = client.get("/api/breeds/list/all", BreedListMessage.class);
        assertNotNull(message);
        assertEquals("success", message.getStatus());
        assertTrue(message.getMessage().keySet().iterator().hasNext());
    }
}