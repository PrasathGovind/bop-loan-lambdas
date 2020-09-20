package com.bop.loan.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.bop.loan.DAO.LoanDAO;
import com.bop.loan.beans.ApplyLoanResponse;
import com.bop.loan.beans.Loan;
import com.bop.loan.beans.LoanRequest;
import com.bop.loan.beans.LoanRequestDTO;
import com.bop.loan.beans.User;
import com.bop.loan.exceptions.APIException;
import com.bop.loan.exceptions.ErrorConstants;
import com.bop.utils.EmailUtils;

public class LoanApplicationService {
	
	LoanDAO loanDAO = new LoanDAO();
	
	EmailUtils emailUtils = new EmailUtils();

	public ApplyLoanResponse applyForALoan(LoanRequest loanRequest, Context context) {
		
		LambdaLogger logger = context.getLogger();

		logger.log("Inside LoanApplicationService.applyForALoan ....");
		
		if(loanRequest==null) {
			throw new APIException(ErrorConstants.LOAN_APPLICATION_REJECTED,"Details are missing in Loan Application!");
		}
		
		if(loanRequest!=null && (loanRequest.getMobileNumber()==null || loanRequest.getMobileNumber().isEmpty()
				|| loanRequest.getEmailId()==null || loanRequest.getEmailId().isEmpty())) {
			throw new APIException(ErrorConstants.LOAN_APPLICATION_REJECTED,"Mandatory details are missing in Loan Application!");
		}
		
		if(loanRequest!=null && (loanRequest.getLoanType()==null || loanRequest.getLoanType().isEmpty())) {
			throw new APIException(ErrorConstants.LOAN_APPLICATION_REJECTED,"Loan Type must be mentioned!");
		}
		
		ApplyLoanResponse applyLoanResponse = new ApplyLoanResponse();
		applyLoanResponse.setMessage("Loan Applied Successfully! You will receive an email with further details!");
		
		//Item userItem = userDAO.getUser(loanRequest.getMobileNumber());
		//System.out.println("User fetched : "+userItem.toJSONPretty());
		
		LoanRequestDTO loanDTO = new LoanRequestDTO();
		loanDTO.setMobileNumber(loanRequest.getMobileNumber());
		loanDTO.setLoanAmount(loanRequest.getLoanAmount());
		loanDTO.setLoanType(loanRequest.getLoanType());
		loanDTO.setTermInYears(loanRequest.getTermInYears());
		
		loanDAO.saveLoanRequest(loanDTO);
		
		//emailUtils.sendLoanAppliedEmail(loanRequest.getEmailId());
		
		applyLoanResponse.setUser(new User());
		applyLoanResponse.getUser().setMobileNumber(loanRequest.getMobileNumber());
		
		try {
			emailUtils.sendEmail(loanRequest.getEmailId());
			emailUtils.postEmailtoSQS(loanRequest.getEmailId());
		} catch (Exception ex) {
			logger.log("Exception thrown while sending Loan Procurement Email! ex="+ex.getMessage());
		}
		
		logger.log("Exit LoanApplicationService.applyForALoan.");

		return applyLoanResponse;
	}
	
	
	public List<Loan> getLoanOptionsAndDetails(){
		
		Loan homeLoan = new Loan();
		
		homeLoan.setLoanType("HOME LOAN");
		homeLoan.setRateOfInterest("9.25 %");
		String[] homeLoanTerms = {"5 years", "10 years", "15 years", "20 years", "25 years", "30 years"};
		homeLoan.setTerms(Arrays.asList(homeLoanTerms));
		homeLoan.setMinimumApplicableLoanAmount("5,00,000 INR");
		
		Loan vehicleLoan = new Loan();
		
		vehicleLoan.setLoanType("CAR LOAN");
		vehicleLoan.setRateOfInterest("12.5 %");
		String[] vehicleLoanTerms = {"5 years", "10 years", "15 years"};
		vehicleLoan.setTerms(Arrays.asList(vehicleLoanTerms));
		vehicleLoan.setMinimumApplicableLoanAmount("1,25,000 INR");
		
		List<Loan> loanOptionsAndDetails = new ArrayList<Loan>(0);
		loanOptionsAndDetails.add(homeLoan);
		loanOptionsAndDetails.add(vehicleLoan);
		
		return loanOptionsAndDetails;
	}

}
