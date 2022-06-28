package org.saikumo.vams.dto;


import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CheckJoinRecordRequest {
	@NotBlank(message = "类型不能为空")
	//0审批通过 1审批不通过
	private Integer actionType;

	@NotBlank(message = "申请记录Id不能为空")
	private Long joinRecordId;
}
