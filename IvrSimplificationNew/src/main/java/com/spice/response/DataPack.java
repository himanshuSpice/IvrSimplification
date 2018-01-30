package com.spice.response;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class DataPack {
	private String packDescription;
	private String dataBalance;
	private String expiryDate,mrp;
}
