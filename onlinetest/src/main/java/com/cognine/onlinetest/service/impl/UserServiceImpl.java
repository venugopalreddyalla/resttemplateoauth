package com.cognine.onlinetest.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cognine.onlinetest.dao.UserDAO;
import com.cognine.onlinetest.model.Role;
import com.cognine.onlinetest.model.User;
import com.cognine.onlinetest.service.UserService;
import com.cognine.onlinetest.utils.exception.FailedToPersistException;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public int createUser(User user) throws FailedToPersistException {
		encodePassword(user);
		return userDAO.createUser(user);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public int mapUserRoles(Map paramMap) throws FailedToPersistException {
		List<Role> roles = paramMap.containsKey("roles") ? (List<Role>) paramMap.get("roles") : new ArrayList<Role>();
		int userId = paramMap.containsKey("userId") ? (int) paramMap.get("userId") : -1;
		userDAO.deleteUserRoles(userId);
		return userDAO.mapUserRoles(userId, roles);
	}

	@Override
	public int updateUser(User user) throws FailedToPersistException {
		encodePassword(user);
		return userDAO.updateUser(user);
	}

	private void encodePassword(User user) {
		String password = user.getPassword();
		if (password != null) {
			user.setPassword(passwordEncoder.encode(password));
		}
	}

}
