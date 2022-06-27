package org.saikumo.vams.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CheckActivityRequest {
	@NotBlank(message = "活动Id不能为空")
	private Long activityId;
	@NotBlank(message = "类型不能为空")
	// 0审批过 1审批不过打回
	private int actionType;
}
