package com.spice.response;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class FlexBalance {
	private String value;
	private String expiryDate;
/*	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}
	@Override
	public String toString() {
		return "FlexBalance value=" + value + ", expiryDate=" + expiryDate
				+ "";
	}*/
}
