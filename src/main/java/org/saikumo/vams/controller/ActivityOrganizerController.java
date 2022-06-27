package org.saikumo.vams.controller;

import org.saikumo.vams.dto.ApiResult;
import org.saikumo.vams.dto.ResubmitActivityRequest;
import org.saikumo.vams.dto.SubmitActivityRequest;
import org.saikumo.vams.service.ActivityOrganizerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/activityorganizer")
public class ActivityOrganizerController {
	@Autowired
	ActivityOrganizerService activityOrganizerService;


	@PostMapping("/submitactivity")
	public ApiResult submitActivity(Authentication authentication,
									@Valid @RequestBody SubmitActivityRequest submitActivityRequest){
		return activityOrganizerService.submitActvitity(authentication,submitActivityRequest.getName(),
				submitActivityRequest.getDescription(),submitActivityRequest.getLocation(),
				submitActivityRequest.getTimestamp());
	}

	@PostMapping("/resubmitactivity")
	public ApiResult ResubmitActivity(Authentication authentication,
									  @Valid @RequestBody ResubmitActivityRequest resubmitActivityRequest){
		return activityOrganizerService.resubmitActivity(authentication,resubmitActivityRequest.getActivityId());
	}
}
