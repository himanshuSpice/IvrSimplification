package com.spice.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.spice.exception.CustomSocketException;
import com.spice.response.FlexBalance;
import com.spice.response.Last3Call;
import com.spice.response.Last3Deduction;
import com.spice.response.Last3Recharges;
import com.spice.response.Last3SMS;
import com.spice.response.Offer;
import com.spice.response.Pack;
import com.spice.request.RequestIvr;
import com.spice.response.ChhotaCredit;
import com.spice.response.CoreBalance;
import com.spice.response.DataPack;
import com.spice.response.IvrResponse;
import com.spice.response.Response;
import com.spice.utility.*;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class IvrSimplificationImpl implements IvrSimplificationInt {

	@Autowired
	ChannelParamConfig param;
	@Autowired
	SocketUrl socketUrl;

	public IvrResponse ivrService(long uniqueId, RequestIvr objRequestIvr) throws CustomSocketException {

		IvrResponse objIvrResponse = new IvrResponse();
		Response objResponse = new Response();

		String response = socketUrl.urlHandle(uniqueId, objRequestIvr.getMsisdn(), objRequestIvr.getRequestId(),
				objRequestIvr.getCircle(), objRequestIvr.getChannel(),objRequestIvr.getIs_chhota_credit());

		String successVlue = param.getProperty("Success");
		
		if (response.trim().length() != 0 && response != null) {
			if (response.equalsIgnoreCase("2001")) {

				throw new CustomSocketException(uniqueId, objRequestIvr, "2001");
			}
			if (response.equalsIgnoreCase("2002")) {
				throw new CustomSocketException(uniqueId, objRequestIvr, "2002");
			}

			System.out.println("********response===="+response);
			objResponse = parsing(response);

			if (objResponse != null) {

				objIvrResponse.setStatus(successVlue.split("\\$")[0]);
				objIvrResponse.setDescription(successVlue.split("\\$")[1]);
				objIvrResponse.setResponseId(objRequestIvr.getRequestId());// ("-1");
				objIvrResponse.setResponse(objResponse);
			}

		} else {

			objIvrResponse.setStatus(param.getProperty("failure").split("\\$")[0]);
			objIvrResponse.setDescription(param.getProperty("failure").split("\\$")[1]);
			objIvrResponse.setResponseId(objRequestIvr.getRequestId());
			objIvrResponse.setResponse(objResponse);
			// throw new CustomException(Constant.NO_RESPONSE);
		}
		return objIvrResponse;
	}

	private Response parsing(String response) {

		Response objResponse = new Response();
		List<Last3Call> objListLast3Call = new ArrayList<Last3Call>();
		List<Last3Deduction> objListLast3Deduction = new ArrayList<Last3Deduction>();
		List<Last3Recharges> objListLast3Recharges = new ArrayList<Last3Recharges>();
		List<Last3SMS> objListLast3SMS = new ArrayList<Last3SMS>();
		List<Offer> objListOffer = new ArrayList<Offer>();
		List<DataPack> objListData = new ArrayList<DataPack>();
		List<Pack> objListPack = new ArrayList<Pack>();

		try {
			if (response.trim().length() != 0 && response != null) {

				String split[] = response.split("\\|");

				for (int i = 0; i < split.length; i++) {
					// System.out.println("in split " + split[i]);
					String header = split[i];
					if (split[i].contains("$")) {
						header = split[i].substring(0, split[i].indexOf("$"));
						// System.out.println("in header $ "+header);

					}

					if (split[i].contains("*")) {

						header = split[i].substring(0, split[i].lastIndexOf("*"));
						// System.out.println("in header * "+header);
					}

					System.out.println("header= " + header);

					switch (header) {

					case "CoreBal":
						String coreBalance[] = split[i].split("\\$");
						CoreBalance objCoreBalance = new CoreBalance();

						objCoreBalance.setValue(coreBalance[1]);
						objCoreBalance.setExpiryDate(coreBalance[2]);

						objResponse.setCorebalance(objCoreBalance);
						break;

					case "FlexBal":
						String flexBalance[] = split[i].split("\\$");
						FlexBalance objFlexBalance = new FlexBalance();

						objFlexBalance.setValue(flexBalance[1]);
						objFlexBalance.setExpiryDate(flexBalance[2]);

						objResponse.setFlexBalance(objFlexBalance);
						break;
					case "ChhotaCredit":
						String chhotaCreditBalance[] = split[i].split("\\$");
						ChhotaCredit chhotacredit = new ChhotaCredit();
						if(chhotaCreditBalance[1].equals("0"))
							chhotacredit.setDueAmount(chhotaCreditBalance[2]);							
						else 
							chhotacredit.setDueAmount(chhotaCreditBalance[1]);

						objResponse.setChhotaCredit(chhotacredit);

						break;

					default:
						try {

							String valueCheck[] = split[i].split("\\$");
							String lengthCheck[] = valueCheck[0].split("\\*");
							// System.out.println("length of lengthCheck " + lengthCheck.length
							// + ", lengthCheck[1]) valueCheck " + lengthCheck[1] + "," +
							// valueCheck.toString());
							if (Integer.parseInt(lengthCheck[1]) > 0) {
								for (int k = 0; k < Integer.parseInt(lengthCheck[1]); k++) {
									// System.out.println("length of lengthCheck " + lengthCheck.length + ",k " + k
									// + ", lengthCheck[1]) valueCheck " + lengthCheck[1] + ","
									// + valueCheck[k]);

									// + "Call*3$1-7874504298-0.30-42-23/04/2017
									// 17:16$2-9724659900-0.30-24-23/04/2017 12:40$3-7874504298-1.20-223-23/04/2017
									// 09:46|";

									// System.out.println("lengthCheck * "+lengthCheck[k] +","+valueCheck[k+1]);

									String callBalance[] = valueCheck[k + 1].split("~");

									// for(int j=0;j<callBalance.length;j++){
									System.out.println("Default header= " + header);
									switch (header) {

									case "Call":

										// 111~0.30~60~17/04/2017 21:02
										Last3Call objLast3Call = new Last3Call();
										objLast3Call.setNumber(callBalance[0]);
										objLast3Call.setAmount(callBalance[1]);
										objLast3Call.setUsage(callBalance[2]);
										objLast3Call.setDateTime(callBalance[3]);
										if (objLast3Call != null) {
											objListLast3Call.add(objLast3Call);
										}
										objResponse.setLast3call(objListLast3Call);

										break;
									//
									case "SMS":
										// 19/04/201710:28~9985338630~SMS~0.02
										Last3SMS objLast3SMS = new Last3SMS();
										objLast3SMS.setDateTime(callBalance[0]);
										objLast3SMS.setNumber(callBalance[1]);
										objLast3SMS.setAmount(callBalance[3]);
										if (objLast3SMS != null) {
											objListLast3SMS.add(objLast3SMS);
											objResponse.setLast3Sms(objListLast3SMS);
										}
										break;

									case "Recharges":
										// 1.17/04/2017 19:36~Rs.10~Rs.7.70
										Last3Recharges objLast3Recharges = new Last3Recharges();
										objLast3Recharges.setDateTime(callBalance[0]);
										objLast3Recharges.setRechargeValue(callBalance[1]);
										objLast3Recharges.setRechargeType(callBalance[2]);
										objLast3Recharges.setFaceValue(callBalance[3]);
										if (objLast3Recharges != null) {
											objListLast3Recharges.add(objLast3Recharges);
											objResponse.setLast3Recharges(objListLast3Recharges);
										}

										break;

									case "Deduction":
										// Rs 1.00 ~ DNLD-WWAP_HLT0001_001_CELFITTIPSM_P-SM
										Last3Deduction objLast3Deduction = new Last3Deduction();
										objLast3Deduction.setAmount(callBalance[0]);
										objLast3Deduction.setPackDesc(callBalance[1]);
										objLast3Deduction.setDateTime(callBalance[2]);
										if (objLast3Deduction != null) {
											objListLast3Deduction.add(objLast3Deduction);
											objResponse.setLast3Deduction(objListLast3Deduction);
										}

										break;

									case "Promo":
										// Promo*2$packname~Local Mins~85020.0~03/05/2017 01:21

										Pack objPack = new Pack();
										objPack.setPackId(callBalance[0]);
										objPack.setPackShortDesc(callBalance[1]);
										objPack.setPackBalance(callBalance[2]);
										objPack.setExpiryDate(callBalance[3]);
										objPack.setPackType(callBalance[4]);
										if (objPack != null) {
											objListPack.add(objPack);
										}

										objResponse.setPack(objListPack);

										break;

									case "Alco":
										// Alco*2$offername~LocalandSTD@30ps_EX_LL~04/06/2017 23:59
										Offer objOffer = new Offer();
										objOffer.setOfferId(callBalance[0]);
										objOffer.setOfferShortDesc(callBalance[1]);
										objOffer.setExpiryDate(callBalance[2]);
										objOffer.setOfferType(callBalance[3]);
										if (objOffer != null) {
											objListOffer.add(objOffer);
											objResponse.setOffer(objListOffer);
										}
										break;

									/*
									 * Data*3$II3GV30R152D500 ~Mobile Internet 500MB (3G/4G) . ~1000.0MB ~1000.0M ~
									 * May 31, 2017 11:59:59 P ~1522$II2GV28R0D200 ~Mobile Internet 200MB (2G) .
									 * ~200.0MB ~0.0M ~ May 26, 2017 11:59:59 P ~03$SI3GV30R20D500 ~Mobile Internet
									 * 500MB (3G/4G) . ~500.0MB ~0.0M ~ May 31, 2017 11:59:59 P ~20
									 */
									case "Data":
										// Alco*2$offername~LocalandSTD@30ps_EX_LL~04/06/2017 23:59
										/*
										 * 1-data 2-pack name 3-pack value 4-data balance 5-expiry
										 */
										DataPack dataPack = new DataPack();
										dataPack.setPackDescription(callBalance[1]);
										dataPack.setDataBalance(callBalance[3]);
										dataPack.setExpiryDate(callBalance[4]);
										dataPack.setMrp(callBalance[5]);
										objListData.add(dataPack);
										objResponse.setDataPack(objListData);
										break;

									default:
										System.out.println("Default called for header=" + header);
										break;
									}// END OF SWITCH
								} // END OF INNER FOR LOOP
							} // END OF OUTER FOR LOOP
								// }//END OF IF
						} catch (Exception e) {
							log.error(e.getMessage());
						}

					}// END OF SWITCH
				} // END OF FOR
			} // END OF IF

		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return objResponse;

	}

}
