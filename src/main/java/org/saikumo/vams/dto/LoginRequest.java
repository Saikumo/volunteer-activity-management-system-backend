package org.saikumo.vams.dto;

import lombok.Data;


@Data
public class LoginRequest {

	private String loginAccount;

	private String password;
}
