package jp.co.axiz.web.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
//import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import jp.co.axiz.web.dao.LoginDao;
import jp.co.axiz.web.entity.Admin;

@Repository
public class PgLoginDao implements LoginDao {
	@Autowired
	private NamedParameterJdbcTemplate npJdbcTemplate;


	public List<Admin> findByIdAndPass(String id, String pass){
		return npJdbcTemplate.query("SELECT admin_id,password,admin_name FROM admin WHERE admin_id  = :admin_id AND password = :password",
				new MapSqlParameterSource()
				.addValue("admin_id", id)
				.addValue("password", pass),
				new BeanPropertyRowMapper <Admin>(Admin.class));

	}
}
