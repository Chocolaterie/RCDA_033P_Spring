package fr.eni.demo_rest.service;

import fr.eni.demo_rest.bo.Person;
import fr.eni.demo_rest.dao.IDAOPerson;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class AuthService {

    /**
     * Récupérer la valeur de app.jwt.secret dans application.properties
     */
    @Value("${app.jwt.secret}")
    private String SECRET_KEY;

    private final IDAOPerson daoPerson;

    public AuthService(IDAOPerson daoPerson) {
        this.daoPerson = daoPerson;
    }

    private Key getSecretKey() {
        // convertir un string en base 64
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        // convertir une base 64 en Key
        Key key = Keys.hmacShaKeyFor(keyBytes);

        return key;
    }

    public ServiceResponse<String> createToken(String email, String password){

        // Essayer de trouver une personne avec le couple email/password
        Person loggedPerson = daoPerson.selectPersonByLogin(email, password);

        // Tester que le couple email/mot de passe d'un user est correct
        if (loggedPerson == null){
            return ServiceHelper.buildResponse("716", "Couple email/mot de passe incorrect");
        }

        // (milliseconde * nb seconde * nb minute) * nb heure
        Date tokenLifetime = new Date(System.currentTimeMillis() + ((1000 * 60 * 60) * 1));
        //Date tokenLifetime = new Date(System.currentTimeMillis() + 1000);

        // Le code pour générer un token
        String token = Jwts.builder()
                .subject(loggedPerson.email)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(tokenLifetime)
                .signWith(getSecretKey(), SignatureAlgorithm.HS256)
                .compact();

        return ServiceHelper.buildResponse("204", "Authentifié(e) avec succès", token);
    }

    public String checkToken(String token){
        // Error: 1 - Si Empty
        if (token.isEmpty()){
            return "Erreur: Token vide";
        }

        // ATTENTION SELON LE CAS LE TOKEN EST SUFFIXE D'UN DISCRIMINANT
        // ex Bearer montoken
        // je dois ignorer les 7 premiers caractères (le mot Bearer avec l'espace)
        token = token.substring(7);

        try {
            // Outil pour récupérer le token (déchiffrer)
            JwtParser jwtParser = Jwts.parser().setSigningKey(getSecretKey()).build();

            // -- récupérer les claims de mon token (claims => toutes les info)
            Claims claims = jwtParser.parseSignedClaims(token).getPayload();

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
