package com.cognine.onlinetest.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognine.onlinetest.model.User;
import com.cognine.onlinetest.service.UserService;
import com.cognine.onlinetest.utils.exception.FailedToPersistException;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {

	@Autowired
	private UserService userService;

	/*@Autowired
	TokenStore tokenStore;*/

	@PostMapping("/createUser")
	public int createUser(@RequestBody User user) throws FailedToPersistException {
		return userService.createUser(user);
	}

	@PostMapping("/mapRoles")
	public int mapUserRoles(@RequestBody Map paramMap) throws FailedToPersistException {

		/*OAuth2AuthenticationDetails auth2AuthenticationDetails = (OAuth2AuthenticationDetails) SecurityContextHolder
				.getContext().getAuthentication().getDetails();
		Map<String, Object> details = tokenStore.readAccessToken(auth2AuthenticationDetails.getTokenValue())
				.getAdditionalInformation();
		String name = (String) details.get("uname");*/
		//System.out.println(SecurityContextHolder.getContext().getAuthentication().getName());

		return userService.mapUserRoles(paramMap);
	}

	@PostMapping("/updateUser")
	public int updateUser(@RequestBody User user) throws FailedToPersistException {
		return userService.updateUser(user);
	}

}
