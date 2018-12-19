package com.spice.response;

import com.spice.request.RequestIvr;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class IvrResponse {
	private String status;
	private String responseId;
	private String description;
	private Response response;

}
