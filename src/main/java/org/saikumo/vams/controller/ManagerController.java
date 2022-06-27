package org.saikumo.vams.controller;

import org.saikumo.vams.dto.*;
import org.saikumo.vams.service.AuthService;
import org.saikumo.vams.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/manager")
public class ManagerController {
	@Autowired
	AuthService authService;
	@Autowired
	ManagerService managerService;

	@PostMapping("/register")
	public ApiResult register(@Valid @RequestBody ManagerRegisterRequest managerRegisterRequest) {
		return authService.register(managerRegisterRequest.getLoginAccount(), managerRegisterRequest.getPassword()
				, managerRegisterRequest.getRoleName());
	}

	@GetMapping("/userlist")
	public ApiResult userList() {
		return managerService.userList();
	}

	@PostMapping("/deleteuser")
	public ApiResult deleteUser(@Valid @RequestBody ManagerDeleteUserRequest managerDeleteUserRequest){
		return managerService.deleteUser(managerDeleteUserRequest.getUserId());
	}

	@PostMapping("/checkactivity")
	public ApiResult checkActivity(@Valid @RequestBody CheckActivityRequest checkActivityRequest){
		return managerService.checkActivity(checkActivityRequest.getActivityId(),
				checkActivityRequest.getActionType());
	}

	@GetMapping("/activitylist")
	public ApiResult activityList(){
		return managerService.activityList();
	}

	@PostMapping("/deleteactivity")
	public ApiResult deleteActivity(@Valid @RequestBody ManagerDeleteActivityRequest managerDeleteActivityRequest){
		return managerService.deleteActivity(managerDeleteActivityRequest.getActivityId());
	}

	@GetMapping("/checkactivitylist")
	public ApiResult checkActivityList(){
		return managerService.checkActivityList();
	}

}
