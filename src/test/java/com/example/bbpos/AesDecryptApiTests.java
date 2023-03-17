package com.example.bbpos;

import static org.hamcrest.CoreMatchers.is;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.bbpos.cipher.dto.DecryptRequestBodyDto;

import io.restassured.RestAssured;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AesDecryptApiTests {

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
                .body(new DecryptRequestBodyDto(null, "404D635166546A576E5A723475377721"))
                .post("/aes/decrypt")
                .then()
                .statusCode(400)
                .body("Errors[0]", is("cipher text must not be null"));
    }

    @Test
    public void encrypt_request_missing_key() throws Exception {
        RestAssured.given()
                .when()
                .header("Content-Type", "application/json")
                .body(new DecryptRequestBodyDto("", null))
                .post("/aes/decrypt")
                .then()
                .statusCode(400)
                .body("Errors[0]", is("key must not be null"));
    }

    @Test
    public void encrypt_request_invalid_key_length() throws Exception {
        RestAssured.given()
                .when()
                .header("Content-Type", "application/json")
                .body(new DecryptRequestBodyDto("", "ABC"))
                .post("/aes/decrypt")
                .then()
                .statusCode(400)
                .body("Errors[0]", is("key must be 32 characters"));
    }

    @Test
    public void should_return_correct_response_object() throws Exception {
        RestAssured.given()
                .when()
                .header("Content-Type", "application/json")
                .body(new DecryptRequestBodyDto("C9E461E80EC3047944ACAE96A9896BC3", "404D635166546A576E5A723475377721"))
                .post("/aes/decrypt")
                .then()
                .statusCode(200)
                .body("plain_text", is("Apple"))
                .body("base64", is("QXBwbGU="));
    }
}