package org.saikumo.vams.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SubmitActivityRequest {

	@NotBlank(message = "活动名不能为空")
	private String name;
	@NotBlank(message = "活动描述不能为空")
	private String description;
	@NotBlank(message = "活动地点不能为空")
	private String location;
	@NotBlank(message = "活动时间不能为空")
	private String timestamp;
}
