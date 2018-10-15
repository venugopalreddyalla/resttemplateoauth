package com.cognine.onlinetest.dao;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.cognine.onlinetest.model.Role;
import com.cognine.onlinetest.model.User;
import com.cognine.onlinetest.utils.exception.FailedToPersistException;

public interface UserDAO {

	public static final Logger logger = LogManager.getLogger(UserDAO.class);

	public int createUser(User user) throws FailedToPersistException;

	public User findUserWithRoles(String userEmail) throws UsernameNotFoundException;

	public int mapUserRoles(int userId, List<Role> roles) throws FailedToPersistException;

	public int deleteUserRoles(int userId) throws FailedToPersistException;

	public int updateUser(User user) throws FailedToPersistException;

}
