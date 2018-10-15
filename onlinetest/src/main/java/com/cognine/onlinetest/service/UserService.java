package com.cognine.onlinetest.service;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.cognine.onlinetest.model.User;
import com.cognine.onlinetest.utils.exception.FailedToPersistException;

public interface UserService {

	public static final Logger logger = LogManager.getLogger(UserService.class);

	public int createUser(User user) throws FailedToPersistException;

	public int mapUserRoles(Map paramMap) throws FailedToPersistException;

	public int updateUser(User user) throws FailedToPersistException;
}
