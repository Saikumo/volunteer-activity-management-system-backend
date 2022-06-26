package org.saikumo.vams.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class RegisterStaffRequest {
	@NotBlank(message = "登陆用户名为空")
	private String loginAccount;

	@NotBlank(message = "登陆密码为空")
	private String password;

	@NotBlank(message = "用户角色为空")
	private String roleName;
}
