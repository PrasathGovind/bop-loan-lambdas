package com.bop.services;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.bop.loan.beans.Loan;
import com.bop.loan.services.LoanApplicationService;

public class LoanApplicationServicesTest {
	
	LoanApplicationService loanApplicationService = new LoanApplicationService();
	
	@Test
	public void getLoanOptionsAndDetails() {

		List<Loan> loans = loanApplicationService.getLoanOptionsAndDetails();
		Assert.assertTrue(loans!=null && loans.size()>=1);
		
		Loan loan = loans.get(0);
		Assert.assertTrue(loan!=null && loan.getLoanType()!=null && loan.getMinimumApplicableLoanAmount()!=null && loan.getRateOfInterest()!=null);
		
		List<String> terms = loan.getTerms();
		Assert.assertTrue(terms!=null && terms.size()>=1);
		String loanStr = loan.toString();
		Assert.assertTrue(loanStr!=null);
	}

}
