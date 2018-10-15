package com.cognine.onlinetest.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import com.cognine.onlinetest.dao.UserDAO;
import com.cognine.onlinetest.dao.mapper.UserMapper;
import com.cognine.onlinetest.model.Role;
import com.cognine.onlinetest.model.User;
import com.cognine.onlinetest.utils.exception.FailedToPersistException;

@Repository
public class UserDAOImpl implements UserDAO {

	@Autowired
	private UserMapper mapper;

	@Override
	public int createUser(User user) throws FailedToPersistException {
		int returnVal = -1;
		try {
			mapper.createUser(user);
			returnVal = user.getUserId();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new FailedToPersistException(User.class, "username", user.getUserName());
		}
		return returnVal;
	}

	@Override
	public User findUserWithRoles(String userEmail) throws UsernameNotFoundException {
		User user = mapper.findUserWithRoles(userEmail);
		if (user == null) {
			throw new UsernameNotFoundException(userEmail);
		} else {
			List<Role> roles = mapper.getUserRoles(user.getUserId());
			user.setUserRoles(roles);
		}
		return user;
	}

	@Override
	public int mapUserRoles(int userId, List<Role> roles) throws FailedToPersistException {
		int returnVal = -1;
		try {
			returnVal = mapper.mapUserRoles(userId, roles);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new FailedToPersistException(User.class, "userId", userId + "");
		}
		return returnVal;
	}

	@Override
	public int deleteUserRoles(int userId) throws FailedToPersistException {
		int returnVal = -1;
		try {
			returnVal = mapper.deleteUserRoles(userId);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new FailedToPersistException(User.class, "userId", userId + "");
		}
		return returnVal;
	}

	@Override
	public int updateUser(User user) throws FailedToPersistException {
		int returnVal = -1;
		try {
			returnVal = mapper.updateUser(user);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new FailedToPersistException(User.class, "userId", user.getUserId() + "", "userEmail",
					user.getUserEmail());
		}
		return returnVal;
	}

}
