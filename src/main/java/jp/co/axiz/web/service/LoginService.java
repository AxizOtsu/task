package jp.co.axiz.web.service;

import java.util.List;

import jp.co.axiz.web.entity.Admin;



public interface LoginService {
	public List<Admin> findByIdAndPass(String id, String pass);
}
