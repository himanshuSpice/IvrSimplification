package com.spice.response;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CoreBalance {
	/*@Override
	public String toString() {
		return "CoreBalance value=" + value + ", expiryDate=" + expiryDate
				+ "";
	}*/
	private String value;
	private String expiryDate;


}
