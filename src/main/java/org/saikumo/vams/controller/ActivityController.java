package org.saikumo.vams.controller;

import org.saikumo.vams.dto.ApiResult;
import org.saikumo.vams.dto.CommentRequest;
import org.saikumo.vams.service.ManagerService;
import org.saikumo.vams.service.VolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/activity")
public class ActivityController {
	@Autowired
	ManagerService managerService;
	@Autowired
	VolunteerService volunteerService;

	@GetMapping("/activityinfo")
	public ApiResult activityInfo(@RequestParam("activityId")Long activityId){
		return managerService.activityInfo(activityId);
	}

	@PostMapping("/comment")
	public ApiResult comment(Authentication authentication, @Valid @RequestBody CommentRequest commentRequest){
		return volunteerService.comment(authentication,commentRequest.getDescription(),commentRequest.getActivityId());
	}
}
