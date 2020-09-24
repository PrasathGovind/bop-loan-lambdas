package com.bop;

import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.bop.loan.beans.LoanRequest;
import com.bop.loan.services.LoanApplicationService;

public class LoanApplicationLambdaTest {
	
	private LoanApplicationService loanApplicationService = new LoanApplicationService();
	
	@Test
	public void handleRequest() {
		
		Context mockContext = mock(Context.class);
		
		Assert.assertTrue(mockContext!=null);
		
		LambdaLogger mockLambdaLogger = mock(LambdaLogger.class);
		
		Assert.assertTrue(mockLambdaLogger!=null);
		
		when(mockContext.getLogger()).thenReturn(mockLambdaLogger);
		
		LoanRequest loanRequest = getLoanRequest();
		
		LoanApplicationLambda lambda = new LoanApplicationLambda();
		
		Context outputntext = lambda.handleRequest(loanRequest, mockContext);
		
		Assert.assertTrue(outputntext!=null);
		
	}
	
	
	private LoanRequest getLoanRequest() {
		
		LoanRequest loanRequest = new LoanRequest();
		
		loanRequest.setEmailId("itsmeprazy@gmail.com");
		loanRequest.setMobileNumber("+9213239823");
		loanRequest.setLoanAmount("3,20,235 INR");
		loanRequest.setLoanType("CAR LOAN");
		loanRequest.setTermInYears("3 years");
		
		return loanRequest;
	}
}
