package com.caiyun.guzhang;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;

import com.caiyun.app.guzhang.R;
import com.caiyun.guzhang.AboutStockActivity;
import com.zhaojin.activity.BaseActivity;
import com.zhaojin.utils.LogUtils;

import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.keys.HmacKey;
import org.jose4j.lang.JoseException;

import java.util.HashMap;
import java.util.Map;


public class Activitys extends BaseActivity {
    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activitys);
        // 获取屏幕高/宽
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        MainActivity.screenWidth = dm.widthPixels;
        MainActivity.screenHeigth = dm.heightPixels;
        MainActivity.screenScale = dm.scaledDensity;
    }

    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.button1:
                intent = new Intent(this, LoginActivity.class);
                break;
            case R.id.button2:
                intent = new Intent(this, MainActivity.class);
                break;
            case R.id.button3:
                intent = new Intent(this, TransactionActivity.class);
                break;
            case R.id.button4:
                intent = new Intent(this, StockInfoActivity.class);
                break;
            case R.id.button5:
                intent = new Intent(this, RankActivity.class);
                break;
            case R.id.button6:
                intent = new Intent(this, TiCaiInfoActivity.class);
                break;
            case R.id.button7:
                intent = new Intent(this, TrendsActivity.class);
                break;
            case R.id.button8:
                intent = new Intent(this, WarningListActivity.class);
                break;
            case R.id.button9:
                intent = new Intent(this, WarningInfoActivity.class);
                break;
            case R.id.button10:
                intent = new Intent(this, MyStockActivity.class);
                break;
            case R.id.button11:
                // Generate an RSA key pair, which will be used for signing and verification of the JWT, wrapped in a JWK
                HmacKey key = new HmacKey("qt734TTNYRsal72x4Wbs5uab4CAlXNfx".getBytes());
                // Give the JWK a Key ID (kid), which is just the polite thing to do
                JwtClaims claims = new JwtClaims();
//                claims.set
//                claims.setIssuer("Issuer");  // who creates the token and signs it
//                claims.setAudience("Audience"); // to whom the token is intended to be sent
//                claims.setExpirationTimeMinutesInTheFuture(10); // time when the token will expire (10 minutes from now)
//                claims.setGeneratedJwtId(); // a unique identifier for the token
//                claims.setIssuedAtToNow();  // when the token was issued/created (now)
//                claims.setNotBeforeMinutesInThePast(2); // time before which the token is not yet valid (2 minutes ago)
//                claims.setSubject("subject"); // the subject/principal is whom the token is about
//                claims.setClaim("parames","pwd, 123456"); // additional claims/attributes about the subject can be added
//                claims.setClaim("mb", "13797891312");
                HashMap map=new HashMap();
                map.put("md","13797891312");
                map.put("pwd","123456");
                claims.setClaim("params",map);
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
                } catch (JoseException e) {
                    LogUtils.e("******", "异常" + e);
                }


                // Now you can do something with the JWT. Like send it to some other party
                // over the clouds and through the interwebs.
                LogUtils.e("+++++", "加密: " + jwt);


                // Use JwtConsumerBuilder to construct an appropriate JwtConsumer, which will
                // be used to validate and process the JWT.
                // The specific validation requirements for a JWT are context dependent, however,
                // it typically advisable to require a expiration time, a trusted issuer, and
                // and audience that identifies your system as the intended recipient.
                // If the JWT is encrypted too, you need only provide a decryption key or
                // decryption key resolver to the builder.
                JwtConsumer jwtConsumer = new JwtConsumerBuilder()
//                        .setRequireExpirationTime() // the JWT must have an expiration time
                        .setAllowedClockSkewInSeconds(30) // allow some leeway in validating time based claims to account for clock skew
                        .setVerificationKey(key)// verify the signature with the public key
                        .build(); // create the JwtConsumer instance

                try {
                    //  Validate the JWT and process it to the Claims
                    JwtClaims jwtClaims = jwtConsumer.processToClaims(jwt);
                    LogUtils.e("++++", "解密" + jwtClaims);
                } catch (Exception e) {
                    // InvalidJwtException will be thrown, if the JWT failed processing or validation in anyway.
                    // Hopefully with meaningful explanations(s) about what went wrong.
                    LogUtils.e("******", "异常" + e);
                }
                return;
            case R.id.button12:
                intent = new Intent(this, UsedRecordActivity.class);
                break;
            case R.id.button13:
                intent = new Intent(this, MyFriendsActivity.class);
                break;
            case R.id.button14:
                intent = new Intent(this, AboutStockActivity.class);
                break;
        }
        this.startActivity(intent);
    }
}
