package com.spice.controller;

import com.spice.exception.CustomException;
import com.spice.exception.CustomSocketException;
import com.spice.request.RequestIvr;
import com.spice.response.IvrResponse;
import com.spice.service.IvrSimplificationInt;

import javax.validation.Valid;

/*import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;*/
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping(value = "/IvrAllBalance")
public class Ivr {

	@Autowired
	IvrSimplificationInt objIvrSimplificationInt;
	
	@RequestMapping(value = "/", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody IvrResponse employees(@Valid @RequestBody RequestIvr objRequestIvr)
			throws CustomSocketException, CustomException {
		long uniqueId = System.currentTimeMillis();
		IvrResponse objIvrResponse = null;

		objIvrResponse = objIvrSimplificationInt.ivrService(uniqueId, objRequestIvr);
		return objIvrResponse;
	}
}
