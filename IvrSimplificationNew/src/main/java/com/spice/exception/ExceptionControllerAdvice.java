package com.spice.exception;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.spice.aspect.LoggingAspect;
import com.spice.request.*;
import com.spice.response.*;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice(annotations=RestController.class)
public class ExceptionControllerAdvice {

	private String failed;

	@Autowired
	private MessageSource messageSource;  
		    
	
	@ExceptionHandler(CustomSocketException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public IvrResponse handle(final CustomSocketException ex) {
		Locale aLocale = Locale.forLanguageTag("en");
		IvrResponse ivrresponse=new IvrResponse();
		
		ivrresponse.setStatus(ex.getCode());
		ivrresponse.setResponseId(ex.getApi().getRequestId());
				
		ivrresponse.setDescription(messageSource.getMessage(ex.getCode(),null,aLocale));
		
        return ivrresponse;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException ex,Locale locale,HttpServletRequest request) 
    {  	
    	
    	Gson gson=new Gson();
		Object obj=ex.getBindingResult().getTarget();
		String test=gson.toJson(obj); 	
    	 log.info(

    			 "Req Class:{} , Req Method:{} , Req Data:{} ",
                 request.getClass(),
                 request.getMethod(),
               //  request.getRequestURI(),              
                 Arrays.asList(test)); 
    	
    		  		
    		RequestIvr errorRequest=gson.fromJson(test,RequestIvr.class);   		
    		BindingResult result = ex.getBindingResult();
    		return processFieldErrors(new HashSet<FieldError>(result.getFieldErrors()),locale,errorRequest);  	
    }

   private ErrorResponse processFieldErrors(HashSet<FieldError> fieldErrors,Locale locale,RequestIvr errorRequest) {
	   //System.out.println("fieldError=="+fieldErrors+",local=="+locale+",errorReq=="+errorRequest);
	   ErrorResponse errorResponse=new ErrorResponse("");
    	String defaultMessage="10008";
    	String field="";
    
        for (FieldError fieldError: fieldErrors) 
        {
           String localizedErrorMessage = resolveLocalizedErrorMessage(fieldError);
           //System.out.println("localizedErrorMessage="+localizedErrorMessage+",fieldEROOR==="+fieldError.getField());
           log.debug("Adding error message: {} to field: {}", localizedErrorMessage, fieldError.getField());
           defaultMessage=fieldError.getDefaultMessage();
           field=fieldError.getField();         
        } 
        
        int code =Integer.parseInt(defaultMessage);
        String errorMessage = messageSource.getMessage(defaultMessage,new Object[]{field},locale);
        
        errorResponse.setStatus(defaultMessage.toString());
        errorResponse.setResponseId(errorRequest.getRequestId());     
        errorResponse.setErrorDescription(errorMessage);
             
        //log.info(" Resp::"+errorResponse);
        return errorResponse;
    }
   
   private String resolveLocalizedErrorMessage(FieldError fieldError) 
   {
       Locale currentLocale = LocaleContextHolder.getLocale();
       String localizedErrorMessage = messageSource.getMessage(fieldError, currentLocale);
       //If a message was not found, return the most accurate field error code instead.
       //You can remove this check if you prefer to get the default error message.
       if (localizedErrorMessage.equals(fieldError.getDefaultMessage())) {
           String[] fieldErrorCodes = fieldError.getCodes();
           localizedErrorMessage = fieldErrorCodes[0];
       }
       return localizedErrorMessage;
   } 
     
 }