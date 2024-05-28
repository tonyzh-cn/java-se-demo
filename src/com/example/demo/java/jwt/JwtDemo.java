package com.example.demo.java.jwt;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JwtDemo {
    private static final long EXPIRE_TIME = 30 * 60 * 1000;
    private static final String TOKEN_SECRET = "pa$$w0rd";
    private final static Logger LOGGER = Logger.getLogger(JwtDemo.class);

    public static String sign(String userName, int userId) {
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        Algorithm algorithm = null;
        try {
            algorithm = Algorithm.HMAC256(TOKEN_SECRET);
        } catch (IllegalArgumentException e) {
            LOGGER.error("illegal argument.", e);
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("unsupported encoding.", e);
        }
        Map<String, Object> header = new HashMap<String, Object>();
        header.put("typ", "JWT");
        header.put("alg", "HS256");
        return JWT.create().withHeader(header).withClaim("userName", userName).withClaim("userId", userId)
                .withExpiresAt(date).sign(algorithm);
    }

    public static boolean verify(String token) {
        Algorithm algorithm = null;
        try {
            algorithm = Algorithm.HMAC256(TOKEN_SECRET);
        } catch (IllegalArgumentException e) {
            LOGGER.error("illegal argument.", e);
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("unsupported encoding.", e);
        }
        JWTVerifier verifier = JWT.require(algorithm).build();
        try {
            verifier.verify(token);
        } catch (TokenExpiredException tee) {
            LOGGER.error("token expired.", tee);
        } catch (Exception ex) {
            LOGGER.error("token verifying failed.", ex);
        }
        return true;
    }

    public static void main(String[] args) {
        String token = sign("admin", 1);
        System.out.println(token);
        DecodedJWT deJwt = JWT.decode(token);
        System.out.println(deJwt.getExpiresAt());
        System.out.println(deJwt.getClaim("userName").asString());
    }
}
