package br.com.fcamara.provajava.jwttoken;

public class UserToken {

	private String name;
	private String login;
	private String userId;
	
	public UserToken() {
		
	}

	public UserToken(String name, String login, String userId) {
		super();
		this.name = name;
		this.login = login;
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
