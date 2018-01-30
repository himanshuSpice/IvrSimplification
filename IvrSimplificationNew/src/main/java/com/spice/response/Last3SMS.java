package com.spice.response;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Last3SMS {
	private String number;
	private String amount;
	private String dateTime;
}
