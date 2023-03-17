package com.example.bbpos;

import static org.hamcrest.CoreMatchers.is;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.bbpos.cipher.dto.EncryptParamDto;

import io.restassured.RestAssured;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AesEncryptApiTests {

    @LocalServerPort
    private int port;

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
    }

    @Test
    public void encrypt_request_plain_text() throws Exception {
        RestAssured.given()
                .when()
                .header("Content-Type", "application/json")
                .body(new EncryptParamDto(null, "404D635166546A576E5A723475377721"))
                .post("/aes/encrypt")
                .then()
                .statusCode(400)
                .body("Errors[0]", is("plain text must not be null"));
    }

    @Test
    public void encrypt_request_missing_key() throws Exception {
        RestAssured.given()
                .when()
                .header("Content-Type", "application/json")
                .body(new EncryptParamDto("", null))
                .post("/aes/encrypt")
                .then()
                .statusCode(400)
                .body("Errors[0]", is("key must not be null"));
    }

    @Test
    public void encrypt_request_invalid_key_length() throws Exception {
        RestAssured.given()
                .when()
                .header("Content-Type", "application/json")
                .body(new EncryptParamDto("", "ABC"))
                .post("/aes/encrypt")
                .then()
                .statusCode(400)
                .body("Errors[0]", is("key must be 32 characters"));
    }

    @Test
    public void should_return_correct_string() throws Exception {
        RestAssured.given()
                .when()
                .header("Content-Type", "application/json")
                .body(new EncryptParamDto("Apple", "404D635166546A576E5A723475377721"))
                .post("/aes/encrypt")
                .then()
                .statusCode(200)
                .body("cipher_text", is("C9E461E80EC3047944ACAE96A9896BC3"));
    }
}