package jp.co.axiz.web.entity;

import javax.validation.constraints.NotNull;

public class UpdateForm {
	@NotNull
	private Integer id;

	private String newName;

	private String newTel;

	private String newPassword;

	private String confirmNewPassword;

	private String prevName;

	private String prevTel;

	private String prevPassword;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNewName() {
		return newName;
	}

	public void setNewName(String newName) {
		this.newName = newName;
	}

	public String getNewTel() {
		return newTel;
	}

	public void setNewTel(String newTel) {
		this.newTel = newTel;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getConfirmNewPassword() {
		return confirmNewPassword;
	}

	public void setConfirmNewPassword(String confirmNewPassword) {
		this.confirmNewPassword = confirmNewPassword;
	}

	public String getPrevName() {
		return prevName;
	}

	public void setPrevName(String prevName) {
		this.prevName = prevName;
	}

	public String getPrevTel() {
		return prevTel;
	}

	public void setPrevTel(String prevTel) {
		this.prevTel = prevTel;
	}

	public String getPrevPassword() {
		return prevPassword;
	}

	public void setPrevPassword(String prevPassword) {
		this.prevPassword = prevPassword;
	}
	public boolean hasRequiredError() {
		return (newName == null || newName.isEmpty())
				|| (newTel == null || newTel.isEmpty())
				|| (newPassword == null || newPassword.isEmpty());
	}
}
