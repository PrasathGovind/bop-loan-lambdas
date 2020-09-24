package com.bop.beans;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import com.bop.loan.beans.ApplyLoanResponse;
import com.bop.loan.beans.Loan;
import com.bop.loan.beans.User;

public class BeansTest {
	
	@Test
	public void applyLoanResponse() {
		
		ApplyLoanResponse applyLoanResponse = new ApplyLoanResponse();
		applyLoanResponse.setMessage("loan asdsalj");
		
		User user = new User();
		user.setEmailId("sadsd@maliniatro.com");
		user.setMobileNumber("2232321312");
		user.setUserId("asdsa-asdsd-vxcv-oiij");
		user.setUserName("Prazy");
		applyLoanResponse.setUser(user);
		
		Loan loan = new Loan();
		loan.setLoanType("CAR LAON");
		loan.setMinimumApplicableLoanAmount("1,00,000 INR");
		loan.setRateOfInterest("7.8%");
		loan.setTerms(Arrays.asList("3 years","2 years"));
		applyLoanResponse.setLoan(loan);
		
		Assert.assertTrue(applyLoanResponse!=null && applyLoanResponse.toString()!=null && applyLoanResponse.getLoan()!=null && applyLoanResponse.getMessage()!=null && applyLoanResponse.getUser()!=null);
		
		Assert.assertTrue(user!=null && user.toString()!=null && user.getEmailId()!=null && user.getMobileNumber()!=null && user.getUserId()!=null && user.getUserName()!=null);
		
		Assert.assertTrue(loan!=null && loan.toString()!=null && loan.getLoanType()!=null && loan.getMinimumApplicableLoanAmount()!=null && loan.getRateOfInterest()!=null && loan.getTerms()!=null);
		
	}

}
