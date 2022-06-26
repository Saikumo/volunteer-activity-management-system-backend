package org.saikumo.vams.dto;

import lombok.Data;


@Data
public class RegisterRequest {
	private String loginAccount;

	private String password;
}
