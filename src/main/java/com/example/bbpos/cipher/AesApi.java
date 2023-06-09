package com.example.bbpos.cipher;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import com.example.bbpos.cipher.dto.DecryptRequestBodyDto;
import com.example.bbpos.cipher.dto.EncryptRequestBodyDto;
import com.example.bbpos.cipher.model.EncryptResponseObject;
import com.example.bbpos.cipher.model.DecryptResponseObject;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("aes")
@RequiredArgsConstructor
public class AesApi {
    @PostMapping("encrypt")
    public ResponseEntity<EncryptResponseObject> encrypt(
            @Valid @RequestBody EncryptRequestBodyDto param) throws Exception {
        String cipherText = Crypto.encrypt(param.getPlainText().getBytes(), param.getAesKey());
        EncryptResponseObject dto = new EncryptResponseObject(cipherText);
        return new ResponseEntity<EncryptResponseObject>(dto, HttpStatus.OK);
    }

    @PostMapping("decrypt")
    public ResponseEntity<DecryptResponseObject> encrypt(
            @Valid @RequestBody DecryptRequestBodyDto body) throws Exception {
        String plainText = Crypto.decrypt(body.getCipherText(), body.getAesKey());
        String base64 = Base64Coder.encodeString(plainText);

        return new ResponseEntity<DecryptResponseObject>(new DecryptResponseObject(plainText, base64), HttpStatus.OK);
    }
}