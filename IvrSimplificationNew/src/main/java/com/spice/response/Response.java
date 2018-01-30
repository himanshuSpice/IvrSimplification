package com.spice.response;

import com.spice.utility.SocketUrl;

import lombok.Data;
import lombok.ToString;

import java.util.List;

import org.apache.log4j.Logger;

@Data
@ToString
public class Response {

	private CoreBalance corebalance;
	private FlexBalance flexBalance;
	private ChhotaCredit chhotaCredit;
	
	private List<Offer> offer;
	private List<Pack> pack;
	private List<Last3SMS> last3Sms;

	private List<Last3Call> last3call;
	private List<Last3Recharges> last3Recharges;
	private List<Last3Deduction> last3Deduction;

	private List<DataPack> dataPack;

	/*public ChhotaCredit getChhotaCredit() {
		return chhotaCredit;
	}

	public void setChhotaCredit(ChhotaCredit chhotaCredit) {
		this.chhotaCredit = chhotaCredit;
	}

	

	public List<DataPack> getDataPack() {
		return dataPack;
	}

	public void setDataPack(List<DataPack> dataPack) {
		this.dataPack = dataPack;
	}

	public List<Last3SMS> getLast3Sms() {
		return last3Sms;
	}

	public void setLast3Sms(List<Last3SMS> last3Sms) {
		this.last3Sms = last3Sms;
	}

	public CoreBalance getCorebalance() {
		return corebalance;
	}

	public void setCorebalance(CoreBalance corebalance) {
		this.corebalance = corebalance;
	}

	@Override
	public String toString() {
		return "Response corebalance=" + corebalance + ", flexBalance=" + flexBalance + ", offer=" + offer + ", pack="
				+ pack + ", last3Sms=" + last3Sms + ", last3call=" + last3call + ", last3Recharges=" + last3Recharges
				+ ", last3Deduction=" + last3Deduction + ", dataPack=" + dataPack + ", getLast3Sms()=" + getLast3Sms()
				+ ", getCorebalance()=" + getCorebalance() + ", getFlexBalance()=" + getFlexBalance() + ", getOffer()="
				+ getOffer() + ", getPack()=" + getPack() + ", getLast3call()=" + getLast3call()
				+ ", getLast3Recharges()=" + getLast3Recharges() + ", getLast3Deduction()=" + getLast3Deduction()
				+ ", getDataPack()=" + getDataPack() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "";
	}

	public FlexBalance getFlexBalance() {
		return flexBalance;
	}

	public void setFlexBalance(FlexBalance flexBalance) {
		this.flexBalance = flexBalance;
	}

	public List<Offer> getOffer() {
		return offer;
	}

	public void setOffer(List<Offer> offer) {
		this.offer = offer;
	}

	public List<Pack> getPack() {
		return pack;
	}

	public void setPack(List<Pack> pack) {
		this.pack = pack;
	}

	public List<Last3Call> getLast3call() {
		return last3call;
	}

	public void setLast3call(List<Last3Call> last3call) {
		this.last3call = last3call;
	}

	public List<Last3Recharges> getLast3Recharges() {
		return last3Recharges;
	}

	public void setLast3Recharges(List<Last3Recharges> last3Recharges) {
		this.last3Recharges = last3Recharges;
	}

	public List<Last3Deduction> getLast3Deduction() {
		return last3Deduction;
	}

	public void setLast3Deduction(List<Last3Deduction> last3Deduction) {
		this.last3Deduction = last3Deduction;
	}*/

}
