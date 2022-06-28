package org.saikumo.vams.controller;

import org.saikumo.vams.dto.*;
import org.saikumo.vams.service.ActivityOrganizerService;
import org.saikumo.vams.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/activityorganizer")
public class ActivityOrganizerController {
	@Autowired
	ActivityOrganizerService activityOrganizerService;
	@Autowired
	ManagerService managerService;


	@PostMapping("/submitactivity")
	public ApiResult submitActivity(Authentication authentication,
									@Valid @RequestBody SubmitActivityRequest submitActivityRequest){
		return activityOrganizerService.submitActvitity(authentication,submitActivityRequest.getName(),
				submitActivityRequest.getDescription(),submitActivityRequest.getLocation(),
				submitActivityRequest.getTimestamp());
	}

	@PostMapping("/resubmitactivity")
	public ApiResult resubmitActivity(Authentication authentication,
									  @Valid @RequestBody ResubmitActivityRequest resubmitActivityRequest){
		return activityOrganizerService.resubmitActivity(authentication,resubmitActivityRequest.getActivityId());
	}

	@PostMapping("/checkjoinrecord")
	public ApiResult checkJoinRecord(@Valid @RequestBody CheckJoinRecordRequest checkJoinRecordRequest){
		return activityOrganizerService.checkJoinRecord(checkJoinRecordRequest.getActionType(),
				checkJoinRecordRequest.getJoinRecordId());
	}

	@GetMapping("/organizeactivitylist")
	public ApiResult organizeActivityList(Authentication authentication){
		return activityOrganizerService.organizeActivityList(authentication);
	}

	@GetMapping("/joinrecordlist")
	public ApiResult joinRecordList(Authentication authentication){
		return activityOrganizerService.joinRecordList(authentication);
	}

	@PostMapping("/deleteactivity")
	public ApiResult deleteActivity(@Valid @RequestBody ManagerDeleteActivityRequest managerDeleteActivityRequest){
		return managerService.deleteActivity(managerDeleteActivityRequest.getActivityId());
	}


}
