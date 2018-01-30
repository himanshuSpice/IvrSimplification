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
	
/*	public String getResponseId() {
		return responseId;
	}

	public void setResponseId(String responseId) {
		this.responseId = responseId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	

	public Response getResponse() {
		return response;
	}

	public void setResponse(Response response) {
		
		this.response = response;
		
	}

	public String toString(){
		if(response==null)
			
			return "Response status=" + status + ", responseId=" + responseId
					+ ", description=" + description  ;
		
		return "Response status=" + status + ", responseId=" + responseId
				+ ", description=" + description + " "+response.toString() ;
		
	}*/

	
}
