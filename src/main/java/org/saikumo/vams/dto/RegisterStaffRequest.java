package org.saikumo.vams.dto;

import lombok.Data;


@Data
public class RegisterStaffRequest {
	private String loginAccount;

	private String password;

	private String roleName;
}
