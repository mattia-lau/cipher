package com.example.bbpos.cipher.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class DecryptResponseObject {
    @JsonProperty("plain_text")
    private String plainText;

    private String base64;
}
