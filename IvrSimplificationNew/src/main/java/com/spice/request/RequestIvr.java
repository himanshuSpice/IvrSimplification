package com.spice.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Required;

import com.spice.validator.ValidateCircles;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RequestIvr {
	
	@NotNull(message="1001")
	@Pattern(regexp="(^$|[0-9]{10})",message="1001")
	private String msisdn;
	
	@ValidateCircles(acceptedValues={"0001", "0002","0003","0004","0004","0005","0006","0007","0008","0009","0010",
			"0011","0012","0013","0014","0015","0016","0017","0018","0019","0020","0021","0022","0023"}, message="1002")
	private String circle;
	
	@NotNull(message="1003")
	@NotEmpty(message = "1003")
	private String channel;
	
	@NotNull(message = "1004")
	@NotEmpty(message = "1004")
	@Size(min=0, max=35,message = "1004")
	@Pattern(regexp = "^[a-z0-9_]*$",message="1004")
	private String requestId;
	
	private String is_chhota_credit,param1,param2;
	
}
