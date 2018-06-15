package jp.co.axiz.web.entity;

import javax.validation.constraints.NotBlank;

public class LoginForm {

	@NotBlank( message = "Idを入力してください")
	private String id;
	@NotBlank( message = "Passを入力してください")
	private String pass;

	public  LoginForm(String id, String pass ) {
		this.id = id;
		this.pass = pass;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}

}