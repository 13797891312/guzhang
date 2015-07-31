package com.caiyun.guzhang.util;

import com.zhaojin.utils.LogUtils;

import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.keys.HmacKey;
import org.jose4j.lang.JoseException;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class UrlUtils {
    public static String httpAdress = "http://api.guzhang.com/v1?service=";
    public static DecimalFormat dFormat = new DecimalFormat("#.##");
    public static HmacKey key = new HmacKey("qt734TTNYRsal72x4Wbs5uab4CAlXNfx".getBytes());

    /**
     * 获取http URL **
     */
    public static String getHttpUrl(String function,String key[],String values[]) {
        String url=httpAdress + function+"&"+"sign="+encode(key,values);
        LogUtils.e("url",url);
        return url;
    }

    public static String encode(String[] keys, Object[] values) {
        // Generate an RSA key pair, which will be used for signing and verification of the JWT, wrapped in a JWK
        // Give the JWK a Key ID (kid), which is just the polite thing to do
        JwtClaims claims = new JwtClaims();
        for (int i = 0; i < keys.length; i++) {
            LogUtils.e("参数",keys[i]+"----"+values[i]);
            claims.setClaim(keys[i], values[i]);
        }
//                claims.set
//                claims.setIssuer("Issuer");  // who creates the token and signs it
//                claims.setAudience("Audience"); // to whom the token is intended to be sent
//                claims.setExpirationTimeMinutesInTheFuture(10); // time when the token will expire (10 minutes from now)
//                claims.setGeneratedJwtId(); // a unique identifier for the token
//                claims.setIssuedAtToNow();  // when the token was issued/created (now)
//                claims.setNotBeforeMinutesInThePast(2); // time before which the token is not yet valid (2 minutes ago)
//                claims.setSubject("subject"); // the subject/principal is whom the token is about
        // additional claims/attributes about the subject can be added
//                List<String> groups = Arrays.asList("group-one", "other-group", "group-three");
//                claims.setStringListClaim("groups", groups); // multi-valued claims work too and will end up as a JSON array

        // A JWT is a JWS and/or a JWE with JSON claims as the payload.
        // In this example it is a JWS so we create a JsonWebSignature object.
        JsonWebSignature jws = new JsonWebSignature();

        // The payload of the JWS is JSON content of the JWT Claims
        jws.setPayload(claims.toJson());

        // The JWT is signed using the private key
        jws.setKey(key);

        // Set the Key ID (kid) header because it's just the polite thing to do.
        // We only have one key in this example but a using a Key ID helps
        // facilitate a smooth key rollover process

        // Set the signature algorithm on the JWT/JWS that will integrity protect the claims
        jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.HMAC_SHA256);

        // Sign the JWS and produce the compact serialization or the complete JWT/JWS
        // representation, which is a string consisting of three dot ('.') separated
        // base64url-encoded parts in the form Header.Payload.Signature
        // If you wanted to encrypt it, you can simply set this jwt as the payload
        // of a JsonWebEncryption object and set the cty (Content Type) header to "jwt".
        jws.setHeader("typ", "JWT");
        String jwt = null;
        try {
            jwt = jws.getCompactSerialization();
            LogUtils.e("加密",jwt);
        } catch (JoseException e) {
            LogUtils.e("******", "异常" + e);
        }

        // Use JwtConsumerBuilder to construct an appropriate JwtConsumer, which will
        // be used to validate and process the JWT.
        // The specific validation requirements for a JWT are context dependent, however,
        // it typically advisable to require a expiration time, a trusted issuer, and
        // and audience that identifies your system as the intended recipient.
        // If the JWT is encrypted too, you need only provide a decryption key or
        // decryption key resolver to the builder.
        return jwt;
    }

    public String decode() {
        JwtConsumer jwtConsumer = new JwtConsumerBuilder()
//                        .setRequireExpirationTime() // the JWT must have an expiration time
//				.setAllowedClockSkewInSeconds(30) // allow some leeway in validating time based claims to account for clock skew
                .setVerificationKey(key)// verify the signature with the public key
                .build(); // create the JwtConsumer instance

        try {
            //  Validate the JWT and process it to the Claims
//                    JwtClaims jwtClaims = jwtConsumer.processToClaims(jwt);
            JwtClaims jwtClaims = jwtConsumer.processToClaims("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1aWQiOjEsInVuYW1lIjoiXHU2OGE2XHU2OGE2XHU5Zjk5XHU5Zjk5IiwidXJsIjoiaHR0cDpcL1wvYXBpLmd1emhhbmcuY29tXC9pbmZvXC8xMjM0XC9hc2RcL2FzZCJ9.Il2IDwtvR7UJcVa5LMk9fnqvx2u07Jd-zO8Qhd4jslw");
            LogUtils.e("++++", "解密" + jwtClaims);
        } catch (Exception e) {
            // InvalidJwtException will be thrown, if the JWT failed processing or validation in anyway.
            // Hopefully with meaningful explanations(s) about what went wrong.
            LogUtils.e("******", "异常" + e);
        }
        return "";
    }
}
