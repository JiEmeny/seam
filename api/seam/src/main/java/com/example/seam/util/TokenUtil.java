package com.example.seam.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.seam.pojo.User;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TokenUtil {
    // 设置token到期时长
    private static long EXPIRE_TIME = 10 * 60 * 60 * 1000;
    // 设置密钥
    private static String TOKEN_SECRET = "'fdasfgdsagaxgsregdfdjyghjfhebfdgwe45ygrfbsdfshfdsag'";
    // 发行人
    private static String TOKEN_NAME = "JiEmony";

    /**
     * token生成
     *
     * @param user
     * @return token
     */
    public static String get_token(User user) {
        String token;
        try {
            token = JWT.create()
                    // 发行人
                    .withIssuer(TOKEN_NAME)
                    // 存放数据
                    .withClaim("username", user.getUsername())
                    .withClaim("password", user.getPassword())
                    // 签发时间
                    .withIssuedAt(new Date(System.currentTimeMillis()))
                    // 过期时间
                    .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRE_TIME))
                    .sign(Algorithm.HMAC256(TOKEN_SECRET));
        } catch (Exception e) {
            e.printStackTrace();
            return "token生成错误";
        }
        return token;
    }

    /**
     * token验证
     *
     * @param token
     * @return Boolean
     */
    public static Map<String, Object> verifyToken(String token) {
        Map<String, Object> map = new HashMap<>();
        try {
            // 创建token验证器
            DecodedJWT jiEmony = JWT.require(Algorithm.HMAC256(TOKEN_SECRET))
                    .withIssuer(TOKEN_NAME)
                    .build()
                    .verify(token);
            // 存放token解析数据
            map.put("username", jiEmony.getClaim("username").asString());
            map.put("password", jiEmony.getClaim("password").asString());
            map.put("expiration", jiEmony.getExpiresAt());
        } catch (Exception e) {
            // 抛出异常（验证不通过）
            return map;
        }
        return map;
    }
}
