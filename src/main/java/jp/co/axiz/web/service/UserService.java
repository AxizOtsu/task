package jp.co.axiz.web.service;

import java.util.List;

import jp.co.axiz.web.entity.User;

public interface UserService {
	public List<User> find(User user);
	public User findById(Integer id);
	public int insert(User user);
	public void delete(Integer id);
	public void update(User user);
}
