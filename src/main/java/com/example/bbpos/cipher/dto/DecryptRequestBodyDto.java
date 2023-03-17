package com.example.bbpos.cipher.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DecryptRequestBodyDto {
    @NotNull(message = "cipher text must not be null")
    @JsonProperty("cipher_text")
    private String cipherText;

    @NotNull(message = "key must not be null")
    @Size(max = 32, min = 32, message = "key must be 32 characters")
    @JsonProperty("aes_key")
    private String aesKey;
}
