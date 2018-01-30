package com.spice.response;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Last3Call {
	private String number;
	private String amount;
	private String usage;
	private String dateTime;
}
