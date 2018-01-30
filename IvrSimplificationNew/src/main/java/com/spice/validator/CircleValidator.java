package com.spice.validator;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.spice.validator.*;

public class CircleValidator implements ConstraintValidator<ValidateCircles, String>{
	private List<String> valueList;

    @Override
    public void initialize(ValidateCircles constraintAnnotation) {
        valueList = new ArrayList<String>();
        for(String val : constraintAnnotation.acceptedValues()) {
            valueList.add(val.toUpperCase());
        }
    }
    
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context)
    {
    	
    	if(value==null||value.isEmpty())
    	{
    		return false;
    	}
        if(!valueList.contains(value.toUpperCase())) 
        {
            return false;
        }
        return true;
    }
}
