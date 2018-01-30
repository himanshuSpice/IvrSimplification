package com.spice.response;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Last3Recharges {
		private String rechargeValue;
		private String faceValue;
		private String dateTime;
		private String rechargeType;
}
