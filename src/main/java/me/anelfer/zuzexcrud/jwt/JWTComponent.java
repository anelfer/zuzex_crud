package me.anelfer.zuzexcrud.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTComponent {

    private static final String SUBJECT = "anelfer.me";
    private static final String ISSUER = "anelfer";

    @Value("${app.jwt.secret}")
    private String jwtSecret;

    public String generateToken(String id) {
        return JWT.create()
                .withSubject(SUBJECT)
                .withClaim("id", id)
                .withIssuedAt(new Date())
                .withIssuer(ISSUER)
                .sign(Algorithm.HMAC256(jwtSecret));
    }

    public String validateTokenAndRetrieveSubject(String token) {
        return JWT.require(Algorithm.HMAC256(jwtSecret))
                .withSubject(SUBJECT)
                .withIssuer(ISSUER)
                .build()
                .verify(token)
                .getClaim("id")
                .asString();
    }
}
