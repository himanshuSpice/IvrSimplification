package com.spice.response;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Offer {
	private String offerId;
	private String offerShortDesc;
	private String expiryDate;
	private String offerType;

	/*@Override
	public String toString() {
		return "Offer offerId=" + offerId + ", offerShortDesc=" + offerShortDesc + ", expiryDate=" + expiryDate
				+ ", offerType=" + offerType;
	}*/

	/*
	 * public String getOfferType() { return offerType; } public void
	 * setOfferType(String offerType) { this.offerType = offerType; }
	 * 
	 * public String getOfferId() { return offerId; } public void setOfferId(String
	 * offerId) { this.offerId = offerId; } public String getOfferShortDesc() {
	 * return offerShortDesc; } public void setOfferShortDesc(String offerShortDesc)
	 * { this.offerShortDesc = offerShortDesc; } public String getExpiryDate() {
	 * return expiryDate; } public void setExpiryDate(String expiryDate) {
	 * this.expiryDate = expiryDate; }
	 */

}
