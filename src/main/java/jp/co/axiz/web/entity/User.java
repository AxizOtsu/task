package jp.co.axiz.web.entity;

import java.io.Serializable;

public class User implements Serializable{
private Integer user_id;
private String user_name;
private String telephone;
private String password;
public Integer getUser_id() {
	return user_id;
}
public void setUser_id(Integer user_id) {
	this.user_id = user_id;
}
public String getUser_name() {
	return user_name;
}
public void setUser_name(String user_name) {
	this.user_name = user_name;
}
public String getTelephone() {
	return telephone;
}
public void setTelephone(String telephone) {
	this.telephone = telephone;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}

public boolean isConditionsEmpty() {
	return user_id == null
			&& (user_name == null || user_name.isEmpty())
			&& (telephone == null || telephone.isEmpty());
}

@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((user_id == null) ? 0 : user_id.hashCode());
	result = prime * result + ((user_name == null) ? 0 : user_name.hashCode());
	result = prime * result + ((password == null) ? 0 : password.hashCode());
	result = prime * result + ((telephone == null) ? 0 : telephone.hashCode());
	return result;
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	User other = (User) obj;
	if (user_id == null) {
		if (other.user_id != null)
			return false;
	} else if (!user_id.equals(other.user_id))
		return false;
	if (user_name == null) {
		if (other.user_name != null)
			return false;
	} else if (!user_name.equals(other.user_name))
		return false;
	if (password == null) {
		if (other.password != null)
			return false;
	} else if (!password.equals(other.password))
		return false;
	if (telephone == null) {
		if (other.telephone != null)
			return false;
	} else if (!telephone.equals(other.telephone))
		return false;
	return true;
}
}



