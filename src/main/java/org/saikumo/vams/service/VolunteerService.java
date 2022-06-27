package org.saikumo.vams.service;

import org.saikumo.vams.constant.JoinRecordStatus;
import org.saikumo.vams.dto.ApiResult;
import org.saikumo.vams.dto.CommentRequest;
import org.saikumo.vams.entity.Activity;
import org.saikumo.vams.entity.Comment;
import org.saikumo.vams.entity.JoinRecord;
import org.saikumo.vams.entity.User;
import org.saikumo.vams.repository.ActivityRepository;
import org.saikumo.vams.repository.CommentRepository;
import org.saikumo.vams.repository.JoinRecordRepository;
import org.saikumo.vams.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VolunteerService {
	@Autowired
	UserRepository userRepository;
	@Autowired
	ActivityRepository activityRepository;
	@Autowired
	JoinRecordRepository joinRecordRepository;
	@Autowired
	CommentRepository commentRepository;

	public ApiResult joinActivity(Authentication authentication,Long activityId){
		User user = userRepository.findUserByUsername(authentication.getName());
		if(user == null){
			return ApiResult.fail("用户不存在");
		}
		Activity activity = activityRepository.findById(activityId);
		if(activity == null){
			return ApiResult.fail("活动不存在");
		}

		JoinRecord joinRecord = new JoinRecord();
		joinRecord.setUserId(user.getId());
		joinRecord.setUsername(user.getUsername());
		joinRecord.setActivityId(activity.getId());
		joinRecord.setOrganizerName(activity.getOrganizerName());
		joinRecord.setStatus(JoinRecordStatus.WAITING_CHECK.getStatus());

		joinRecordRepository.save(joinRecord);

		return ApiResult.ok();
	}

	public ApiResult joinRecordList(Authentication authentication){
		User user = userRepository.findUserByUsername(authentication.getName());
		if(user == null){
			return ApiResult.fail("用户不存在");
		}

		List<JoinRecord> joinRecordList = joinRecordRepository.findAllByUserId(user.getId());

		return ApiResult.ok(joinRecordList);
	}

	public ApiResult comment(Authentication authentication,String description,Long activityId){
		User user = userRepository.findUserByUsername(authentication.getName());
		if(user == null){
			return ApiResult.fail("用户不存在");
		}

		Comment comment = new Comment();
		comment.setUserId(user.getId());
		comment.setUsername(user.getUsername());
		comment.setDescription(description);
		comment.setCreateTime(LocalDateTime.now());

		Activity activity = activityRepository.findById(activityId);
		if(activity == null){
			return ApiResult.fail("活动不存在");
		}

		List<Comment> commentList = activity.getComments();
		commentList.add(comment);
		activity.setComments(commentList);

		activityRepository.save(activity);

		return ApiResult.ok();
	}

}
