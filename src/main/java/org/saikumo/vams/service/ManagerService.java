package org.saikumo.vams.service;

import org.saikumo.vams.constant.ActivityStatus;
import org.saikumo.vams.dto.ApiResult;
import org.saikumo.vams.entity.Activity;
import org.saikumo.vams.entity.User;
import org.saikumo.vams.repository.ActivityRepository;
import org.saikumo.vams.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagerService {
	@Autowired
	UserRepository userRepository;
	@Autowired
	ActivityRepository activityRepository;

	public ApiResult userList(){
		List<User> userList = userRepository.findAll();
		return ApiResult.ok(userList);
	}

	public ApiResult deleteUser(Long userId){
		userRepository.deleteById(userId);
		return ApiResult.ok();
	}

	public ApiResult checkActivity(Long activityId,int actionType){
		Activity activity = activityRepository.findById(activityId);
		if(activity == null){
			return ApiResult.fail("活动不存在");
		}

		if(actionType == 0){
			activity.setStatus(ActivityStatus.PUBLISHED.getStatus());
		}else if(actionType == 1){
			activity.setStatus(ActivityStatus.WAITING_MODIFY.getStatus());
		}else{
			return ApiResult.fail("类型错误");
		}

		activityRepository.save(activity);
		return ApiResult.ok();
	}

	public ApiResult activityList(){
		List<Activity> activityList = activityRepository.findAll();
		return ApiResult.ok(activityList);
	}

	public ApiResult deleteActivity(Long activityId){
		activityRepository.deleteById(activityId);
		return ApiResult.ok();
	}

	public ApiResult checkActivityList(){
		List<Activity> activityList = activityRepository.findAllByStatus(ActivityStatus.WAITING_CHECK.getStatus());
		return ApiResult.ok(activityList);
	}
}
