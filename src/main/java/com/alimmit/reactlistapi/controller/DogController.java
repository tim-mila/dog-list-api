package com.alimmit.reactlistapi.controller;

import com.alimmit.reactlistapi.service.DogService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DogController {

    static final String PATH = "/dogs";

    private final DogService dogService;

    public DogController(final DogService dogService) {
        this.dogService = dogService;
    }

    @GetMapping(PATH)
    public Iterable<String> breeds() {
        return dogService.breeds();
    }

    @GetMapping(value = PATH, params = { "q" })
    public Iterable<String> breeds(@RequestParam("q") final String query) {
        return dogService.breeds(query);
    }
}
