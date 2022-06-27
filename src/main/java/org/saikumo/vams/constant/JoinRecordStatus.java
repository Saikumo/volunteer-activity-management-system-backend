package org.saikumo.vams.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum JoinRecordStatus {

	WAITING_CHECK("等待审批中"),
	CHECK_FAILURE("审批不通过"),
	CHECKED("审批通过");

	private final String status;
}
