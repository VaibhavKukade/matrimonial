package com.app.matrimonial.BaseResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Errors {
	private  String userMessage;
	private  String internalMessage;
	private  String code;
}
