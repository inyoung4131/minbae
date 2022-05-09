package com.minbae.sso.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenProvider {
    private final HttpServletRequest request;

    public JwtTokenProvider(
        HttpServletRequest request
    ) {
        this.request = request;
    }

    private final String secretKey = "yangsonghuh";
    private final String tokenIss = "minbae.com";
    private final String claimIss = "iss";
    private final String claimUid = "memberId";
    private final String claimMemberIdx = "memberIdx";
    private final String jwtHeaderName = "user-token";
    private final long accessExpireTime = 172800000; //(60 * 60 * 1000L) * 48
    private final long refreshExpireTime = 172800000; // (60 * 60 * 1000L) * 48

    public String createToken(String memberId, long memberIdx) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + accessExpireTime);
        return Jwts.builder()
                .setSubject(""+memberId)
                .setClaims(createClaims(memberId, tokenIss, memberIdx))
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public String createRefreshToken(String memberId,  long memberIdx) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + refreshExpireTime);

        return Jwts.builder()
                .setSubject(""+memberId)
                .setClaims(createClaims(memberId, tokenIss, memberIdx))
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public boolean validateExpToken(String jwtToken) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    public boolean validateExpTokenForRole(String jwtToken,String role) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
            if(claims.getBody().get("Role").equals(role))return true;
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private Map<String, Object> createClaims(String memberId, String tokenIss, long memberIdx) {
        Map<String, Object> claims = new HashMap<>();

        claims.put(claimUid, memberId);
        claims.put(claimIss, tokenIss);
        claims.put(claimMemberIdx, memberIdx);
        
        return claims;
    }

    public Claims getClaims(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }

    public String getToken() {
        String token = request.getHeader(jwtHeaderName);
        return token;
    }
}
