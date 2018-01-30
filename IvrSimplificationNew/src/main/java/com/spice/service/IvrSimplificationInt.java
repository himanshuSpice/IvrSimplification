package com.spice.service;

import com.spice.exception.CustomException;
import com.spice.exception.CustomSocketException;
import com.spice.request.RequestIvr;
import com.spice.response.IvrResponse;

public interface IvrSimplificationInt {
	
	IvrResponse ivrService(long uniqueId,RequestIvr objRequestIvr)throws CustomException, CustomSocketException;
	//IvrResponse ivrService(RequestIvr objRequestIvr)throws CustomException, CustomSocketException;

}
