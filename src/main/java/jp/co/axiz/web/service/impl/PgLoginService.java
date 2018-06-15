package jp.co.axiz.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.axiz.web.dao.LoginDao;
import jp.co.axiz.web.entity.Admin;
import jp.co.axiz.web.service.LoginService;

@Service
public class PgLoginService implements LoginService{

	@Autowired
	private LoginDao loginDao;
	@Override
	public List<Admin> findByIdAndPass(String id, String pass){
		return loginDao.findByIdAndPass(id, pass);
	}
}
