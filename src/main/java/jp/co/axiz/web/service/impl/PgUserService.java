package jp.co.axiz.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.axiz.web.dao.UserDao;
import jp.co.axiz.web.entity.User;
import jp.co.axiz.web.service.UserService;

@Service
public class PgUserService implements UserService{
	@Autowired
	private UserDao userDao;
	@Override
	public List<User> find(User user) {

		if (user.isConditionsEmpty()) {
			return userDao.findAll();
		} else {
			return userDao.findByConditions(user);
		}
	}

	@Override
	public User findById(Integer id) {
		return userDao.findById(id);
	}

	@Override
	public int insert(User user) {
		return userDao.register(user);
	}

	@Override
	public void update(User user) {
		userDao.update(user);
	}

	@Override
	public void delete(Integer id) {
		userDao.delete(id);
	}


}
