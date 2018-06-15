package jp.co.axiz.web.dao;

import java.util.List;

import jp.co.axiz.web.entity.User;


public interface UserDao {
	public List<User> findAll();

	public List<User> findByConditions(User user);

	public int register(User user);

	public User findById(Integer id);

	public void update( User user);

	public void delete(Integer id);


}
