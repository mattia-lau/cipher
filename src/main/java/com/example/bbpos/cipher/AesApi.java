package com.example.bbpos.cipher;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bbpos.cipher.dto.EncryptParamDto;
import com.example.bbpos.cipher.model.CipherTextResponseObject;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("aes")
@RequiredArgsConstructor
public class AesApi {
    @PostMapping("encrypt")
    public ResponseEntity<CipherTextResponseObject> encrypt(
            @Valid @RequestBody EncryptParamDto param) throws Exception {
        String cipherText = Crypto.encrypt(param.getPlainText().getBytes(), param.getAesKey());
        CipherTextResponseObject dto = new CipherTextResponseObject(cipherText);
        return new ResponseEntity<CipherTextResponseObject>(dto, HttpStatus.OK);
    }
}