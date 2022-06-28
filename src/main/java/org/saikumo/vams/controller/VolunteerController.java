package org.saikumo.vams.controller;

import org.saikumo.vams.dto.ApiResult;
import org.saikumo.vams.dto.CommentRequest;
import org.saikumo.vams.dto.JoinActivityRequest;
import org.saikumo.vams.service.ManagerService;
import org.saikumo.vams.service.VolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/volunteer")
public class VolunteerController {
	@Autowired
	VolunteerService volunteerService;

	@PostMapping("/joinactivity")
	public ApiResult joinActivity(Authentication authentication,
								  @Valid @RequestBody JoinActivityRequest joinActivityRequest){
		return volunteerService.joinActivity(authentication,joinActivityRequest.getActivityId());
	}

	@GetMapping("/joinrecordlist")
	public ApiResult joinRecordList(Authentication authentication){
		return volunteerService.joinRecordList(authentication);
	}



	@GetMapping("/activitylist")
	public ApiResult activityList(){
		return volunteerService.activityList();
	}
}
