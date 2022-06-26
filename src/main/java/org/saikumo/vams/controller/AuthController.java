package org.saikumo.vams.controller;

import jakarta.validation.Valid;
import org.saikumo.vams.constant.RoleName;
import org.saikumo.vams.dto.ApiResult;
import org.saikumo.vams.dto.LoginRequest;
import org.saikumo.vams.dto.RegisterRequest;
import org.saikumo.vams.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	AuthService authService;

	@PostMapping("/login")
	public ApiResult login(@Valid @RequestBody LoginRequest loginRequest) {
		return authService.login(loginRequest.getLoginAccount(), loginRequest.getPassword());
	}

	@PostMapping("/register")
	public ApiResult register(@Valid @RequestBody RegisterRequest registerRequest) {
		return authService.register(registerRequest.getLoginAccount(), registerRequest.getPassword()
				, RoleName.VOLUNTEER.getRoleName());
	}
}
