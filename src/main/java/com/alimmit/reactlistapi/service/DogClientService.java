package com.alimmit.reactlistapi.service;

import com.alimmit.reactlistapi.http.BreedImageMessage;
import com.alimmit.reactlistapi.http.BreedListMessage;

public interface DogClientService {

    BreedListMessage all();

    BreedImageMessage imageForBreed(String name);
}
