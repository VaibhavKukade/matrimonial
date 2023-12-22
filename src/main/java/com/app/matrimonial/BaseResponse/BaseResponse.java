package com.app.matrimonial.BaseResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse<   T> {
	private  T data;
	private Response  response;
	private Errors errors;
	private  String logID;
	private  String serviceName;
}
