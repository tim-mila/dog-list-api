package com.alimmit.reactlistapi.service;

import com.alimmit.reactlistapi.http.BreedImageMessage;
import com.alimmit.reactlistapi.http.BreedListMessage;
import com.alimmit.reactlistapi.http.DogApiClient;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class DogClientServiceImpl implements DogClientService {

    private static final Log LOG = LogFactory.getLog(DogClientServiceImpl.class);

    static final String BREED_LIST = "/api/breeds/list/all";
    static final String BREED_IMAGE_RANDOM = "/api/breed/{breed}/images/random";

    private final DogApiClient dogApiClient;

    public DogClientServiceImpl(final DogApiClient dogApiClient) {
        this.dogApiClient = dogApiClient;
    }

    @Override
    @Cacheable("breeds")
    public BreedListMessage all() {
        LOG.info("Get all breed data from remote service");
        return dogApiClient.get(BREED_LIST, BreedListMessage.class);
    }

    @Override
    @Cacheable("breed_image")
    public BreedImageMessage imageForBreed(final String name) {
        LOG.info("Get breed image url from remote service");
        return dogApiClient.get(BREED_IMAGE_RANDOM, BreedImageMessage.class, name);
    }
}
