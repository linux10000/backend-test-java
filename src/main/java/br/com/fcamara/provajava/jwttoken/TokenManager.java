package br.com.fcamara.provajava.jwttoken;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

public class TokenManager {

	public DecodedJWT getToken(String authorization) {
		Algorithm algorithmHS = Algorithm.HMAC256("secret");
		JWTVerifier verifier = JWT.require(algorithmHS).build();
		return verifier.verify(authorization.replace("Baerer ", ""));
	}
}
