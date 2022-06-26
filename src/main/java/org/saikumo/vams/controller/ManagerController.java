package org.saikumo.vams.controller;

import org.saikumo.vams.dto.ApiResult;
import org.saikumo.vams.dto.RegisterStaffRequest;
import org.saikumo.vams.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/manager")
public class ManagerController {
	@Autowired
	AuthService authService;

	@PostMapping("/register_staff")
	public ApiResult registerStaff(@RequestBody RegisterStaffRequest registerStaffRequest) {
		return authService.register(registerStaffRequest.getLoginAccount(), registerStaffRequest.getPassword()
				, registerStaffRequest.getRoleName());
	}
}
