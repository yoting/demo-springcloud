package com.gusi.demo.utils;



import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.security.Key;
import java.util.Date;

/**
 * TokenUtil <br>
 *
 * @author Lucky
 * @since 2020/3/2
 */
public class TokenUtil {

	public static String getJWTString(String username, Date expires, Key key) {
		if (username == null) {
			throw new NullPointerException("null username is illegal");
		}

		if (expires == null) {
			throw new NullPointerException("null expires is illegal");
		}
		if (key == null) {
			throw new NullPointerException("null key is illegal");
		}
		// 用签名算法HS256和私钥key生成token
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		String jwtString = Jwts.builder().setIssuer("Jersey-Security-Basic")// 设置发行人
				.setSubject(username)// 设置抽象主题
				.setAudience("user")// 设置角色
				.setExpiration(expires)// 过期时间
				.setIssuedAt(new Date())// 设置现在时间
				.setId("1")// 版本1
				.signWith(signatureAlgorithm, key).compact();
		return jwtString;
	}

	public static String extractJwtTokenFromAuthorizationHeader(String auth) {
		// Replacing "Bearer Token" to "Token" directly
		return auth.replaceFirst("[B|b][E|e][A|a][R|r][E|e][R|r] ", "").replace(" ", "");
	}

	public static boolean isValid(String token, Key key) {
		try {
			Jwts.parser().setSigningKey(key).parseClaimsJws(token.trim());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static String getName(String jwsToken, Key key) {
		if (isValid(jwsToken, key)) {
			Jws<Claims> claimsJws = Jwts.parser().setSigningKey(key).parseClaimsJws(jwsToken);
			return claimsJws.getBody().getSubject();
		}
		return null;
	}

	public static String[] getRoles(String jwsToken, Key key) {
		if (isValid(jwsToken, key)) {
			Jws<Claims> claimsJws = Jwts.parser().setSigningKey(key).parseClaimsJws(jwsToken);
			return claimsJws.getBody().getAudience().split(",");
		}
		return new String[] {};
	}

	public static int getVersion(String jwsToken, Key key) {
		if (isValid(jwsToken, key)) {
			Jws<Claims> claimsJws = Jwts.parser().setSigningKey(key).parseClaimsJws(jwsToken);
			return Integer.parseInt(claimsJws.getBody().getId());
		}
		return -1;
	}

	public static void main(String[] args) {
		String token = getJWTString("13554133412", new Date(System.currentTimeMillis() + 360000L), null);
		System.out.println(token);
	}
}
