package com.alimmit.reactlistapi.http;

import com.alimmit.reactlistapi.service.DogClientService;
import com.alimmit.reactlistapi.service.DogServiceImpl;
import org.apache.commons.lang3.StringUtils;
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

    @Test
    public void getImage() {
        BreedImageMessage message = client.get("/api/breed/{breed}/images/random", BreedImageMessage.class, "vizsla");
        assertNotNull(message);
        assertEquals("success", message.getStatus());
        assertTrue(StringUtils.isNotBlank(message.getMessage()));
    }
}