package org.saikumo.vams.dto;

import lombok.Data;

@Data
public class ManagerUserListResponse {

	private Long userId;

	private String username;

	private String userRole;
}
