package org.saikumo.vams.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ManagerDeleteUserRequest {
	@NotBlank(message = "用户id不能为空")
	private Long userId;
}
