package com.alimmit.reactlistapi.service;

public interface DogService {

    Iterable<String> breeds();

    Iterable<String> breeds(String query);
}
