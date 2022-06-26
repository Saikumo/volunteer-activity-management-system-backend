package org.saikumo.vams.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RoleName {

	VOLUNTEER(1L,"志愿者"),
	ACTIVITY_ORGANIZER(2L,"活动组织者"),
	MANAGER(3L,"管理员");

	private final Long id;
	private final String roleName;
}
