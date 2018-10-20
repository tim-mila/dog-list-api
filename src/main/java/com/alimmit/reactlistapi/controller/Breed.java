package com.alimmit.reactlistapi.controller;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Breed {

    private final String name;
    private final String imageUrl;

    @JsonCreator
    public static Breed of(@JsonProperty("name") final String name, @JsonProperty("imageUrl") final String imageUrl) {
        return new Breed(name, imageUrl);
    }

    private Breed(final String name, final String imageUrl) {
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
