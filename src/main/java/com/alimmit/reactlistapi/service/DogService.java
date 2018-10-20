package com.alimmit.reactlistapi.service;

import com.alimmit.reactlistapi.controller.Breed;

public interface DogService {

    Iterable<Breed> breeds();

    Iterable<Breed> breeds(String query);
}
