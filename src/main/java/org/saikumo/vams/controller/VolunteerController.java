package org.saikumo.vams.controller;

import org.saikumo.vams.dto.ApiResult;
import org.saikumo.vams.dto.JoinActivityRequest;
import org.saikumo.vams.service.VolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
