package br.com.fcamara.provajava.service.impl;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import br.com.fcamara.provajava.dao.UsuarioDao;
import br.com.fcamara.provajava.exception.InvalidCredentialsException;
import br.com.fcamara.provajava.helper.SessaoManager;
import br.com.fcamara.provajava.pojo.Usuario;
import br.com.fcamara.provajava.service.TokenService;

@Service
@Scope( BeanDefinition.SCOPE_PROTOTYPE )
public class TokenServiceImpl implements TokenService {

	@Lazy(true)
	@Autowired
	private UsuarioDao usuarioDao;

	public Map<String, String> getToken(String username, String password) throws InvalidCredentialsException, NoSuchAlgorithmException {
		Usuario uso = usuarioDao.findByUsocloginAndUsocsenha(username, password).stream().findFirst().orElse(null);
		if ( uso == null )
			throw new InvalidCredentialsException();

		LocalDateTime expireDate = LocalDateTime.now();
		expireDate = expireDate.plusMinutes(1440); //1 dia
		
		Algorithm algorithmHS = Algorithm.HMAC256("secret");		
		String token = JWT.create()
				.withExpiresAt(Date.from(expireDate.atZone(ZoneId.systemDefault()).toInstant()))
				.withClaim("login", username)
				.withClaim("userName", uso.getUsocnome())
				.withClaim("userId", uso.getUsonid().toString())
				.sign(algorithmHS);
		
		Map<String, String> r = new HashMap<>();
		r.put("token", token);
		r.put("login", username);
		r.put("name", uso.getUsocnome());
		
		SessaoManager.getInstancia().getSessoes().put(username, token);
		
		return r;
	}
}
