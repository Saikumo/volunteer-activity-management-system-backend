package org.saikumo.vams.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ChangePasswordRequest {
	@NotBlank(message = "旧密码不能为空")
	private String oldPassword;

	@NotBlank(message = "新密码不能为空")
	private String newPassword;
}
