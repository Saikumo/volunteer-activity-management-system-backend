package org.saikumo.vams.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class JoinActivityRequest {
	@NotBlank(message = "活动Id不能为空")
	private Long activityId;
}
