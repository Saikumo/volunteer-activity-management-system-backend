package org.saikumo.vams.service;

import org.saikumo.vams.dto.ApiResult;
import org.saikumo.vams.entity.User;
import org.saikumo.vams.repository.UserActivityRepository;
import org.saikumo.vams.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class VolunteerService {
	@Autowired
	UserRepository userRepository;
	@Autowired
	UserActivityRepository userActivityRepository;

	public ApiResult joinActivity(Authentication authentication,Long activityId){
		User user = userRepository.findUserByUsername(authentication.getName());
		if(user == null){
			return ApiResult.fail("用户不存在");
		}
		userActivityRepository.createUserActivitity(activityId,user.getId());
		return ApiResult.ok();
	}
}
