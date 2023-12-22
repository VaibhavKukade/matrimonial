package com.app.matrimonial.BaseResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Response {
	private  String type;
	private  String responseCode;
	private  String responseMessage;
}
