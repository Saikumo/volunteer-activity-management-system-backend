package org.saikumo.vams.controller;

import org.saikumo.vams.dto.ApiResult;
import org.saikumo.vams.dto.ChangeNicknameRequest;
import org.saikumo.vams.dto.ChangePasswordRequest;
import org.saikumo.vams.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {
	@Autowired
	UserService userService;

	@PostMapping("/changepassword")
	public ApiResult changePassword(Authentication authentication, @Valid @RequestBody ChangePasswordRequest changePasswordRequest) {
		return userService.changePassword(authentication, changePasswordRequest.getOldPassword()
				, changePasswordRequest.getNewPassword());
	}

	@PostMapping("/changenickname")
	public ApiResult changeNickname(Authentication authentication, @Valid @RequestBody ChangeNicknameRequest changeNicknameRequest) {
		return userService.changeNickname(authentication, changeNicknameRequest.getNewNickname());
	}
}
