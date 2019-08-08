package br.com.fcamara.provajava.helper;

import java.util.HashMap;
import java.util.Map;

public class SessaoManager {

	private static SessaoManager instancia;
	
	private Map<String, String> sessoes;
	
	public SessaoManager() {
		sessoes = new HashMap<String, String>();		
	}

	public static SessaoManager getInstancia() {
		if ( instancia == null )
			instancia = new SessaoManager();
		
		return instancia;
	}

	public Map<String, String> getSessoes() {
		return sessoes;
	}
}
