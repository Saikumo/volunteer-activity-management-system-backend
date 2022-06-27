package org.saikumo.vams.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ActivityStatus {

	WAITING_MODIFY("等待修改中"),
	WAITING_CHECK("等待审批中"),
	PUBLISHED("已发布");

	private final String status;

}
