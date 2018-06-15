package jp.co.axiz.web.dao;

import java.util.List;

import jp.co.axiz.web.entity.Admin;

public interface LoginDao {
	public List<Admin> findByIdAndPass(String id, String pass);

}