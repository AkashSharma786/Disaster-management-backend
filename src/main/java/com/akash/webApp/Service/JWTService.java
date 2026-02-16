package com.akash.webApp.Service;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.springframework.boot.jackson.autoconfigure.JacksonProperties.Json;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTService {

    private SecretKey secretKey;

    public JWTService(){

        try{
            KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA256");
              secretKey =   keyGenerator.generateKey();

             
            
            
        }
        catch(NoSuchAlgorithmException e)
        {
            throw new RuntimeException(e);

        }
        
 

    }


    public String generateToken(String username){

        Map<String, Object> map = new HashMap<>();

       return Jwts.builder()
            .claims()
            .add(map)
            .subject(username)
            .issuedAt( new Date(System.currentTimeMillis()))
            .expiration( new Date(System.currentTimeMillis()+ 1000*60*10))
            .and()
            .signWith(getKey())
            .compact();


    }

    public Key getKey(){
 
        byte[] bytes =  secretKey.getEncoded();
        return Keys.hmacShaKeyFor(bytes);
         

    }

    private Claims getAllClaimsFromToken(String token) {
        System.out.println(token);
    return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload();
}

    public String extractUsername(String token){


        Claims claims = getAllClaimsFromToken(token);
        System.out.println(claims);

        return claims.getSubject();
    
    }

    public boolean validateToken(String token, UserDetails userDetails)
    {
        String username = extractUsername(token);
        System.out.println(username);
        System.out.println(token);
        Date expiration = extractExpiry(token);
        //System.out.println(new Date(System.currentTimeMillis()).toGMTString());
        Date current = new Date(System.currentTimeMillis());
        return (username.equals(userDetails.getUsername()) && current.before(expiration) );
      
    }

    public Date extractExpiry(String token){
        Claims claims = getAllClaimsFromToken(token);
       return  claims.getExpiration();
    }
    
}
