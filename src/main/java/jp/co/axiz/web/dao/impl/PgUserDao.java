package jp.co.axiz.web.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import jp.co.axiz.web.dao.UserDao;
import jp.co.axiz.web.entity.User;

@Repository
public class PgUserDao implements UserDao{
	@Autowired
	private NamedParameterJdbcTemplate npJdbcTemplate;



	public List<User> findAll() {
		String sql = "SELECT * FROM user_info ORDER BY user_id";
		List<User> resultList = npJdbcTemplate.query(sql, new BeanPropertyRowMapper<User>(User.class));
		return resultList;
	}

	@Override
	public List<User> findByConditions(User user) {

		List<String> condition = new ArrayList<String>();
		MapSqlParameterSource param = new MapSqlParameterSource();

		Integer user_id = user.getUser_id();
		String user_name = user.getUser_name();
		String telephone = user.getTelephone();

		if(user_id != null) {
			condition.add("user_id = :userId");
			param.addValue("userId", user_id);
		}

		if(user_name != null && !user_name.isEmpty()) {
			condition.add("user_name = :userName");
			param.addValue("userName", user_name);

		}

		if(telephone != null && !telephone.isEmpty()) {
			condition.add("telephone = :telephone");
			param.addValue("telephone", telephone);

		}

		String whereString = String.join(" AND ", condition.toArray(new String[]{}));

		String sql = "SELECT * FROM user_info WHERE " + whereString + " ORDER BY user_id";

		List<User> resultList = npJdbcTemplate.query(sql, param, new BeanPropertyRowMapper<User>(User.class));

		return resultList;
	}
	@Override
	public User findById(Integer id) {

		String sql = "SELECT * FROM user_info WHERE user_id = :userId";
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("userId", id);

		List<User> resultList = npJdbcTemplate.query(sql, param, new BeanPropertyRowMapper<User>(User.class));

		return resultList.isEmpty() ? null : resultList.get(0);
	}



	public int register(User user) {
		String sql ="INSERT INTO user_info (user_name, telephone, password) VALUES(:user_name, :telephone, :password)";
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("user_name", user.getUser_name());
		param.addValue("telephone", user.getTelephone());
		param.addValue("password", user.getPassword());
		param.addValue("user_id", user.getUser_id());

		KeyHolder keyHolder = new GeneratedKeyHolder();

		npJdbcTemplate.update(sql, param, keyHolder, new String[] {"user_id"});

		return keyHolder.getKey().intValue();

	}

	public void update(User user) {
		String sql ="UPDATE user_info SET user_name = :user_name,telephone = :telephone,password = :password WHERE user_id =:user_id";
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("user_name", user.getUser_name());
		param.addValue("telephone", user.getTelephone());
		param.addValue("password", user.getPassword());
		param.addValue("user_id", user.getUser_id());
		npJdbcTemplate.update(sql, param);
	}

	public void delete(Integer id) {
		String sql ="DELETE FROM user_info WHERE user_id = :user_id";
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("user_id", id);
		npJdbcTemplate.update(sql, param);
	}
}
