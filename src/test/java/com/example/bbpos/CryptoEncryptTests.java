package com.example.bbpos;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import com.example.bbpos.cipher.Crypto;
import com.example.bbpos.cipher.exceptions.InvalidKeyLengthException;

public class CryptoEncryptTests {
    @Test
    public void should_return_encrypted_apple() throws Exception {
        String cipherText = Crypto.encrypt("Apple".getBytes(), "404D635166546A576E5A723475377721");
        assertThat(cipherText, is("C9E461E80EC3047944ACAE96A9896BC3"));
    }

    @Test
    public void should_return_encrypted_hello_bbpos() throws Exception {
        String cipherText = Crypto.encrypt("Hello, Bbpos".getBytes(), "404D635166546A576E5A723475377721");
        assertThat(cipherText, is("2A539D6A94B0F7DB7997CCC68F27BF7B"));
    }

    @Test
    public void should_return_empty_string() throws Exception {
        String cipherText = Crypto.encrypt("".getBytes(), "404D635166546A576E5A723475377721");
        assertThat(cipherText, is(""));
    }

    @Test
    public void non_acsii_characters() throws Exception {
        String cipherText = Crypto.encrypt("こんにちは、世界".getBytes(), "404D635166546A576E5A723475377721");
        assertThat(cipherText, is("D2AF6EF775CB4BE7A4CADF92C50A31BC25630980E65E6E164DAB44D25176CECB"));
    }

    @Test
    public void invalidKeyLengthExceptionThrown() throws Exception {
        InvalidKeyLengthException thrown = Assertions.assertThrows(InvalidKeyLengthException.class,
                () -> Crypto.encrypt("Exception".getBytes(), "INVALID_KEY"));

        assertThat(thrown.getMessage(), is("Invalid key length"));
    }

    @Test
    public void should_return_cipher_text_for_large_string() throws Exception {
        String cipherText = Crypto.encrypt("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed euismod interdum turpis. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; Nulla facilisi. Sed vel porttitor massa. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. In enim justo, rhoncus ut, imperdiet a, venenatis vitae, justo. Nullam dictum felis eu pede mollis pretium. Integer tincidunt. Cras dapibus. Vivamus elementum semper nisi. Aenean vulputate eleifend tellus. Aenean leo ligula, porttitor eu, consequat vitae, eleifend ac, enim. Aliquam lorem ante, dapibus in, viverra quis, feugiat a, tellus. Phasellus viverra nulla ut metus varius laoreet. Quisque rutrum. Aenean imperdiet. Etiam ultricies nisi vel augue. Curabitur ullamcorper ultricies nisi.".getBytes(), "404D635166546A576E5A723475377721");
        assertThat(cipherText, is("936579D87345E53C27BA7070B457E8D340BE8408FFB6083D1B0878CC97C59EF800993B3691EE5F8F0581636B532DDD5DCBA05B53EB07F99575CC4F23660C5E44C4D299236A3CB3106FE7CC827CF23B83F8278254B3A6932F3E125D05D729E57E45E42E5A295BB891A0EEAFA26DCF1442E646DE777E873710FC03C0DDB6830429054DC4CC5236519F2D180EBA5F46A36238BCD5600FABB9E9607DC6469E42B2D7C7EEF58887670B87CE853A581D8444D303E67FCD1C69EBB5E585D4D707B0E51CE8EC0CC4DE1DEC90E6FDC4D1F7CB51304625C90FA83BA06B6B8B5749BD2EFB277A33EB4C5215ADC3B05669EB1961287BAD623CD29480B3ADE476B5887AC066B7E955CF6BCE92D1E00059857A91F53839AA26957A022BB15598A489A3164C8E18525F67A464B27CB5D5EC90417FF7A4548F5E75323F17011D31F4093B408B90F1EFD8F8360187A2FA74EE6939C500CA3BA6385EEA68D5435C263867CFB0BC32051ED678AAAC3D7C2F304A81097274E1A373397ACF7CA3EF173B32C41BACB67217D070403918C02E39B853FCA9FC2C3E7A20E3E1BFDF4BDA0958DB8E3AF2ED621658807E9AB31EA0593660D7E7B853B6CA983EB3DE251C4CCD427E6C8C70D95BDE9ABBCD336ADF6ED29758255DB890BC775D1B7A39023EC89924E7D7E14185FFD54BC15B0C0D9FC75A2985A9633095A67ABBD24A2CF93CA87F0B952292E6C01143EB53E94E9D9ACAC34909C3477CFEAB356B9469C46FDB5588B456C74DB48ADB9D0DD6C64F032EFC1F618627C02CD4B3701287D712344FF8EF169C861350FE739957AC1E9ACEC98FEFA5F3AABEF1B2814ABF47115C71698701B829F57EEF33C4F154C4A024B781897C5CEE1B1D7E31D9EC271683F870F548D21D0A6BC751B3C68B8EEDB5A6B31F154A4C13D26695C2D7255EE42AB60C9DDA7B075D6ABB3427E7F766FE0E309E8273F7AA6D3030E71FF37F99CC0CE4439D573F73A666C7479D24CF362807144EC64942689BCB363AA1146B49CA1482E09CCFC9D9A0BFA7496F730C869A1C2BCDB60AECB9A2AE52EA6461BECD6A79304878F1D9958FC387C051FFE5EBC93F2669FA274392FDC1FB5CFD5598BA2A830AAB7ADF0956B890D9BF97BFCD66C87A35F37EF3BAC039B910B4790DBCAD87178E4763DD12EAEF1263142AAD58A953496BDAD31F0E2ED775676DDCF82C43AB41D9ACA9E538162C86F2E79607C57E5B575D35894226E47974AFBA16B393"));
    }
}
