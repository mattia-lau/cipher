package com.example.bbpos.cipher.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class EncryptParamDto {
  @NotNull(message = "plain text must not be null")
  @JsonProperty("plain_text")
  private String plainText;

  @NotNull(message = "key must not be null")
  @Size(max = 32, min = 32, message = "key must be 32 characters")
  @JsonProperty("aes_key")
  private String aesKey;
}