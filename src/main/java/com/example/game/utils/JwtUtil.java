package com.example.game.utils;

import com.alibaba.fastjson.JSONObject;
import com.example.game.common.SystemConstant;
import com.example.game.error.BusinessErrorEnum;
import com.example.game.error.BusnessException;
import com.example.game.response.CommonResponse;
import io.jsonwebtoken.*;
import org.bouncycastle.util.encoders.Base64;


import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;

/**
 * @ Author   ：yangyunlong.
 * @ Date     ：Created in 11:17 2019/12/05
 * @Version ： 1.0
 */
public class JwtUtil {

    //创建jwt token
    public static String createJwt(String id, String subject,long ttlMillis){
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        SecretKey secretKey = generalKey();
        JwtBuilder builder = Jwts.builder()
                .setId(id)
                .setSubject(subject)   // 主题
                .setIssuer("user")     // 签发者
                .setIssuedAt(now)      // 签发时间
                .signWith(signatureAlgorithm, secretKey); // 签名算法以及密匙
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date expDate = new Date(expMillis);
            builder.setExpiration(expDate); // 过期时间
        }
        return builder.compact();
    }

    /**
     * 验证JWT
     * @param jwtStr
     * @return
     */
    public static CommonResponse validateJWT(String jwtStr) throws BusnessException {
        Claims claims = null;
        try {
            claims = parseJWT(jwtStr);
            return CommonResponse.create(claims);
        } catch (ExpiredJwtException e) {
            throw new BusnessException(BusinessErrorEnum.TOKEN_VALIDATE_EXPIRED_ERROR,BusinessErrorEnum.TOKEN_VALIDATE_EXPIRED_ERROR.getErrorMsg());
        } catch (SignatureException e) {
            throw new BusnessException(BusinessErrorEnum.TOKEN_VALIDATE_FAIL_ERROR,BusinessErrorEnum.TOKEN_VALIDATE_FAIL_ERROR.getErrorMsg());
        } catch (Exception e) {
            throw new BusnessException(BusinessErrorEnum.TOKEN_VALIDATE_ERROR,BusinessErrorEnum.TOKEN_VALIDATE_ERROR.getErrorMsg());
        }
    }

    /**
     *
     * 解析JWT字符串
     * @param jwt
     * @return
     * @throws Exception
     */
    public static Claims parseJWT(String jwt) throws Exception {
        SecretKey secretKey = generalKey();
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwt)
                .getBody();
    }

    public static SecretKey generalKey() {
        byte[] encodedKey = Base64.decode(SystemConstant.JWT_SECERT);
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }

    public static void main(String[] args) throws Exception {
        JSONObject json = new JSONObject();
        json.put("name","妹妹v");
        String token = JwtUtil.createJwt(json.toString(),"登录token",60 * 1000);
        Claims claims = JwtUtil.parseJWT(token);
        CommonResponse commonResponse = JwtUtil.validateJWT(token);
        System.out.println(commonResponse);
        System.out.println(claims);
        System.out.println(token);
    }
}
