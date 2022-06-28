package org.saikumo.vams.service;

import org.saikumo.vams.constant.ActivityStatus;
import org.saikumo.vams.constant.JoinRecordStatus;
import org.saikumo.vams.dto.ApiResult;
import org.saikumo.vams.entity.Activity;
import org.saikumo.vams.entity.JoinRecord;
import org.saikumo.vams.entity.User;
import org.saikumo.vams.repository.ActivityRepository;
import org.saikumo.vams.repository.JoinRecordRepository;
import org.saikumo.vams.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityOrganizerService {
	@Autowired
	ActivityRepository activityRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	JoinRecordRepository joinRecordRepository;

	public ApiResult submitActvitity(Authentication authentication,String name,String description
			,String location,String timestamp){
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

	public ApiResult checkJoinRecord(Integer actionType,Long joinRecordId){
		JoinRecord joinRecord = joinRecordRepository.findById(joinRecordId);
		if(joinRecord == null){
			return ApiResult.fail("申请记录不存在");
		}

		if(actionType == 0){
			Activity activity = activityRepository.findById(joinRecord.getActivityId());
			if(activity == null){
				return ApiResult.fail("活动不存在");
			}
			List<User> userList = activity.getUsers();
			User user = userRepository.findUserByUsername(joinRecord.getUsername());
			if(user == null){
				return ApiResult.fail("用户不存在");
			}

			if(userList.contains(user)){
				return ApiResult.fail("用户已参加活动");
			}

			userList.add(user);
			activity.setUsers(userList);

			activityRepository.save(activity);

			joinRecord.setStatus(JoinRecordStatus.CHECKED.getStatus());

		}else if(actionType == 1){
			joinRecord.setStatus(JoinRecordStatus.CHECK_FAILURE.getStatus());
		}else{
			return ApiResult.fail("类型错误");
		}
		joinRecordRepository.save(joinRecord);

		return ApiResult.ok();
	}

	public ApiResult organizeActivityList(Authentication authentication){
		List<Activity> activityList = activityRepository.findAllByOrganizerName(authentication.getName());

		return ApiResult.ok(activityList);
	}

	public ApiResult joinRecordList(Authentication authentication){
		List<JoinRecord> joinRecordList = joinRecordRepository.findAllByOrganizerNameAndStatus(authentication.getName(),
				JoinRecordStatus.WAITING_CHECK.getStatus());

		return ApiResult.ok(joinRecordList);
	}
}
