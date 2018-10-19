package com.alimmit.reactlistapi.service;

import com.alimmit.reactlistapi.http.BreedListMessage;
import com.alimmit.reactlistapi.http.DogApiClient;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class DogServiceImpl implements DogService {

    private static final Log LOG = LogFactory.getLog(DogServiceImpl.class);
    private static final String BREED_LIST = "/api/breeds/list/all";

    private final DogApiClient dogApiClient;

    public DogServiceImpl(final DogApiClient dogApiClient) {
        this.dogApiClient = dogApiClient;
    }

    @Override
    @Cacheable("breeds")
    public Iterable<String> breeds() {
        LOG.debug("get breed data from remote service");
        return dogApiClient.get(BREED_LIST, BreedListMessage.class).getMessage().keySet();
    }

    @Override
    @Cacheable("filtered_breeds")
    public Iterable<String> breeds(final String query) {
        LOG.debug("get filtered breed data from remote service");
        return StreamSupport.stream(breeds().spliterator(), false).filter((s) -> StringUtils.startsWithIgnoreCase(s, query)).collect(Collectors.toList());
    }
}
