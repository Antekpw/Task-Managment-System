package com.example.taskmanagmentsystem.utils;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtUtils {

    @Value("${jwt.private-key}")
    private Resource privateKeyResource;  // Plik z kluczem prywatnym

    @Value("${jwt.public-key}")
    private Resource publicKeyResource;   // Plik z kluczem publicznym

    @Value("${jwt.expiration}")
    private long expiration;

    // Wczytaj klucz prywatny z pliku PEM
    private PrivateKey getPrivateKey() throws Exception {
        byte[] keyBytes = privateKeyResource.getInputStream().readAllBytes();
        String pem = new String(keyBytes)
                .replace("-----BEGIN PRIVATE KEY-----", "")
                .replace("-----END PRIVATE KEY-----", "")
                .replaceAll("\\s", "");
        byte[] decoded = Base64.getDecoder().decode(pem);
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(decoded);
        return KeyFactory.getInstance("RSA").generatePrivate(spec);
    }

    // Wczytaj klucz publiczny z pliku PEM
    private PublicKey getPublicKey() throws Exception {
        byte[] keyBytes = publicKeyResource.getInputStream().readAllBytes();
        String pem = new String(keyBytes)
                .replace("-----BEGIN PUBLIC KEY-----", "")
                .replace("-----END PUBLIC KEY-----", "")
                .replaceAll("\\s", "");
        byte[] decoded = Base64.getDecoder().decode(pem);
        X509EncodedKeySpec spec = new X509EncodedKeySpec(decoded);
        return KeyFactory.getInstance("RSA").generatePublic(spec);
    }

    public String generateToken(String login,String role) throws Exception {
        return Jwts.builder()
                .subject(login)
                .claim("role", role) // Dodanie roli do tokena
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getPrivateKey(), Jwts.SIG.RS256) // Nowy sposób podpisu
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(getPublicKey())  // Weryfikacja kluczem publicznym
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getLoginFromToken(String token) throws Exception {
        return Jwts.parser()
                .verifyWith(getPublicKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject(); // getSubject() z payloadu
    }
    public String getRoleFromToken(String token) throws Exception {
        return Jwts.parser()
                .verifyWith(getPublicKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get("role", String.class);
    }


}