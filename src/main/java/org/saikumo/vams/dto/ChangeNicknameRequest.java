package org.saikumo.vams.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ChangeNicknameRequest {

	@NotBlank(message = "昵称不能为空")
	private String newNickname;

}
