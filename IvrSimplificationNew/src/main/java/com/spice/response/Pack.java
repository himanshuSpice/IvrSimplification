package com.spice.response;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Pack {
	private String packId;
	private String packShortDesc;
	private String PackBalance;
	private String expiryDate;
	private String packType;
	/*
	 * @Override public String toString() { return "Pack packId=" + packId +
	 * ", packShortDesc=" + packShortDesc + ", PackBalance=" + PackBalance +
	 * ", expiryDate=" + expiryDate + " ,packType="+packType; }
	 * 
	 * public String getPackType() { return packType; } public void
	 * setPackType(String packType) { this.packType = packType; }
	 * 
	 * public String getPackId() { return packId; } public void setPackId(String
	 * packId) { this.packId = packId; } public String getPackShortDesc() { return
	 * packShortDesc; } public void setPackShortDesc(String packShortDesc) {
	 * this.packShortDesc = packShortDesc; } public String getPackBalance() { return
	 * PackBalance; } public void setPackBalance(String packBalance) { PackBalance =
	 * packBalance; } public String getExpiryDate() { return expiryDate; } public
	 * void setExpiryDate(String expiryDate) { this.expiryDate = expiryDate; }
	 */
}
