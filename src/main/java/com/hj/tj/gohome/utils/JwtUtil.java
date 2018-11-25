package com.hj.tj.gohome.utils;

import com.auth0.jwt.JWTSigner;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.JWTVerifyException;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.HashMap;
import java.util.Map;


@Slf4j
public class JwtUtil {
	public static final String USER_ID = "userId";

	private static String jwtKey = "e6170e0539b8bd0e429f4dc5d8fc5eff";

	public static Map<String, Object> decode(String signStr) {
		try {
			return new JWTVerifier(jwtKey).verify(signStr);
		} catch (InvalidKeyException | NoSuchAlgorithmException | IllegalStateException | SignatureException
				| IOException | JWTVerifyException e) {
			log.error("[action = `decode`, decode error.signStr:{}]", signStr);
			return  null;
		}
	}

	public static Integer getUserId(String sid) throws Exception {
		if(StringUtil.isBlank(sid)){
			return 0;
		}
		Map<String, Object> sidMap = JwtUtil.decode(sid);
		return (Integer) sidMap.get(USER_ID);
	}
	
	public static String encode(int userId) {
		Map<String, Object> sidMap = new HashMap<String, Object>();
		sidMap.put(USER_ID, userId);
		JWTSigner signer = new JWTSigner(jwtKey);
		String sid = signer.sign(sidMap);
		return sid;
	}
}
