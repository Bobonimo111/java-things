package com.willaim.springSecurity.services;

import java.time.Instant;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

    private final JwtEncoder encoder;

    public JwtService(JwtEncoder encoder) {
        this.encoder = encoder;
    }

    public String generatedToken(Authentication auth){
        Instant now = Instant.now();
        long expiry = 3000L;

        String scopes = auth.getAuthorities()
        .stream()
        .map(GrantedAuthority::getAuthority)
        .collect(Collectors.joining(" "));

        var claims = JwtClaimsSet.builder()
        .issuer("projeto-verdinho-motherfucker")
        .issuedAt(now)
        .expiresAt(now.plusSeconds(expiry))
        .subject(auth.getName())
        .claim("scopes", scopes)    
        .build();

        return encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }
}