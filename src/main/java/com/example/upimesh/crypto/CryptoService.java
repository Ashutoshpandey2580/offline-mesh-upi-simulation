package com.example.upimesh.crypto;


import com.example.upimesh.entity.PaymentInstruction;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.Key;
import java.security.MessageDigest;
import java.util.Base64;

@Service
public class CryptoService {

    private final ObjectMapper mapper =
            new ObjectMapper()
                    .findAndRegisterModules();

    private final SecretKey secretKey;


    public CryptoService()throws Exception{

        KeyGenerator generator=
                KeyGenerator.getInstance("AES");
        generator.init(128);

        this.secretKey=generator.generateKey();
    }
    public String encrypt(
            PaymentInstruction instruction
    ) throws Exception{

        String json=
                mapper.writeValueAsString(instruction);

        Cipher cipher=Cipher.getInstance("AES");

        cipher.init(
                Cipher.ENCRYPT_MODE,
                secretKey);
        byte[] encrypted=cipher.doFinal(json.getBytes());

        return Base64.getEncoder().encodeToString(encrypted);
    }
    public PaymentInstruction decrypt(
            String ciphertext)
        throws Exception{

        byte[] decoded=Base64.getDecoder()
                .decode(ciphertext);

        Cipher cipher=
                Cipher.getInstance("AES");

        cipher.init(
                Cipher.DECRYPT_MODE,
                secretKey);

        byte[] decrypted= cipher.doFinal(decoded);

        String json= new String(decrypted);
        return mapper.readValue(
                json,
                PaymentInstruction.class);

    }
    public String sha256(String input)
        throws Exception{


        MessageDigest digest=MessageDigest.getInstance("SHA-256");


        byte[]hash=digest.digest(input.getBytes());

        StringBuilder sb= new StringBuilder();

        for (byte b:hash){
            sb.append(
                    String.format("%02x",b));
        }
        return sb.toString();
    }
}
