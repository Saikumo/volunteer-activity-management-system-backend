package org.saikumo.vams.service;

import org.saikumo.vams.constant.ActivityStatus;
import org.saikumo.vams.dto.ApiResult;
import org.saikumo.vams.entity.Activity;
import org.saikumo.vams.entity.User;
import org.saikumo.vams.repository.ActivityRepository;
import org.saikumo.vams.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class ActivityOrganizerService {
	@Autowired
	ActivityRepository activityRepository;
	@Autowired
	UserRepository userRepository;

	public ApiResult submitActvitity(Authentication authentication,String name,String description
			,String location,Long timestamp){
		User user = userRepository.findUserByUsername(authentication.getName());
		Activity activity = new Activity();

		activity.setOrganizerId(user.getId());
		activity.setOrganizerName(user.getUsername());
		activity.setName(name);
		activity.setDescription(description);
		activity.setLocation(location);
		activity.setTimestamp(timestamp);
		activity.setStatus(ActivityStatus.WAITING_CHECK.getStatus());

		activityRepository.save(activity);
		return ApiResult.ok();
	}

	public ApiResult resubmitActivity(Authentication authentication,Long activityId){
		Activity activity = activityRepository.findById(activityId);
		if(activity == null){
			return ApiResult.fail("活动不存在");
		}
		if(!activity.getOrganizerName().equals(authentication.getName())){
			return ApiResult.fail("用户权限不足");
		}
		activity.setStatus(ActivityStatus.WAITING_CHECK.getStatus());
		activityRepository.save(activity);
		return ApiResult.ok();
	}
}
