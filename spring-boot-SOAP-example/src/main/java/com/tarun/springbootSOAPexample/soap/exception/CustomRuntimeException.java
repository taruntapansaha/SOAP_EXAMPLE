package com.tarun.springbootSOAPexample.soap.exception;

import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;

//@SoapFault(faultCode=FaultCode.CLIENT)
@SoapFault(faultCode=FaultCode.CUSTOM,
		customFaultCode="{in28minutes.com/courses}001_Course_Not_Found")
public class CustomRuntimeException extends RuntimeException {

	private static final long serialVersionUID = 334265223955114940L;

	public CustomRuntimeException(String message) {
		super(message);
	}

}
