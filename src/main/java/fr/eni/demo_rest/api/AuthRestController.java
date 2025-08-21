package fr.eni.demo_rest.api;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@RestController
public class AuthRestController {

    @GetMapping("api/create-token")
    public String createToken(){

        // (milliseconde * nb seconde * nb minute) * nb heure
        Date tokenLifetime = new Date(System.currentTimeMillis() + ((1000 * 60 * 60) * 1));
        //Date tokenLifetime = new Date(System.currentTimeMillis() + 1000);

        // convertir un string en base 64
        byte[] keyBytes = Decoders.BASE64.decode("69636e783529213d5722613b2b336c793371666524684a3445226e5573");
        // convertir une base 64 en Key
        Key key = Keys.hmacShaKeyFor(keyBytes);

        // Le code pour générer un token
        String token = Jwts.builder()
                .subject("test@gmail.com")
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(tokenLifetime)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        return token;
    }

    @GetMapping("api/check-token/{token}")
    public String checkToken(@PathVariable String token){

        // Error: 1 - Si Empty
        if (token.isEmpty()){
            return "Erreur: Token vide";
        }

        // convertir un string en base 64
        byte[] keyBytes = Decoders.BASE64.decode("69636e783529213d5722613b2b336c793371666524684a3445226e5573");
        // convertir une base 64 en Key
        Key key = Keys.hmacShaKeyFor(keyBytes);

        try {
            // Outil pour récupérer le token (déchiffrer)
            JwtParser jwtParser = Jwts.parser().setSigningKey(key).build();

            // -- récupérer les claims de mon token
            //  claims => toutes les info
            Claims claims = jwtParser.parseSignedClaims(token).getBody();

            // Récupérer la date
            // 1: Version abstraite (couplage faible)
            // Function<Claims, Date> expirationFunction = Claims::getExpiration;
            // Date expirationDate = expirationFunction.apply(claims);
            // 2: Version explicite (couplage fort)
            Date expirationDate = claims.getExpiration();

            System.out.println(expirationDate);

        } catch (Exception e) {
            // Si la date d'expiration est depassé alors
            // Si exception jwt de type expiration
            if (e instanceof ExpiredJwtException){
                return "Token expiré";
            }

            // Si token malformé
            if (e instanceof MalformedJwtException){
                return "Token malformé";
            }

            return "Erreur inconnue";
        }

        return "Token valide";
    }
}
