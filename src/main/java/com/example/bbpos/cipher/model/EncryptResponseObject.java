package com.example.bbpos.cipher.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
@AllArgsConstructor
public class EncryptResponseObject {
    @JsonProperty("cipher_text")
    private String cipherText;
}
