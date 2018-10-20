package com.alimmit.reactlistapi.service;

import com.alimmit.reactlistapi.controller.Breed;
import com.alimmit.reactlistapi.http.BreedImageMessage;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class DogServiceImpl implements DogService {

    private static final Log LOG = LogFactory.getLog(DogServiceImpl.class);

    private final DogClientService dogClientService;

    public DogServiceImpl(final DogClientService dogClientService) {
        this.dogClientService = dogClientService;
    }

    @Override
    public Iterable<Breed> breeds() {
        return dogClientService.all()
                .getMessage()
                .keySet()
                .stream()
                .map(name -> {
                    final BreedImageMessage message = dogClientService.imageForBreed(name);
                    return Breed.of(name, message.getMessage());
                }).collect(Collectors.toList());
    }

    @Override
    public Iterable<Breed> breeds(final String query) {
        return StreamSupport.stream(breeds().spliterator(), false).filter((breed) -> StringUtils.startsWithIgnoreCase(breed.getName(), query)).collect(Collectors.toList());
    }
}
