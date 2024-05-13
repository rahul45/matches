package com.footballmatches.matches.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoder;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtHelper {
    final static String SECRET_KEY="I8P2XwBetBK89Uwsucsbh5PZqXvunIJMPASSWORDKOLENGTH256SEJYADA";
    final static int VALIDATE_TOKEN_TIME=1000*60*30;
    public String generateToken(UserDetails userDetails){
        //Everything is claims in jwt tokens
        Map<String,Object> claims=new HashMap<>();
        return createToken(claims,userDetails.getUsername());

    }

    private String createToken(Map<String, Object> claims,String userName) {
            return Jwts.builder()
                    .setClaims(claims)
                    .setSubject(userName)
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(new Date(System.currentTimeMillis()+VALIDATE_TOKEN_TIME))
                    .signWith(SignatureAlgorithm.HS256,SECRET_KEY).compact();
    }

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public Boolean validateToken(String token,String userName){
        String user = getUserNameFromToken(token);
        return user.equals(userName) && !isTokenExpired(token);
    }

    public boolean isTokenExpired(String token) {
        final Date expirationDate = getExpirationDateFromToken(token);
            return expirationDate.before(new Date());
    }

    public Date getExpirationDateFromToken(String token){
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public  <T> T getClaimFromToken(String token, Function<Claims,T> claimsResolver) {
            Claims claims=getAllClaimsFromToken(token);
            return claimsResolver.apply(claims);
    }
    public Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    public String getUserNameFromToken(String token) {
            return getClaimFromToken(token,Claims::getSubject);
    }
}

