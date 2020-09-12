package com.bop;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.bop.loan.beans.ApplyLoanResponse;
import com.bop.loan.beans.LoanRequest;
import com.bop.loan.services.LoanApplicationService;

//  com.bop.LoanApplicationLambda::handleRequest

public class LoanApplicationLambda implements RequestHandler<LoanRequest, Context> {
	
	private LoanApplicationService loanApplicationService = new LoanApplicationService();

	public Context handleRequest(LoanRequest loanRequest, Context context) {
		
		LambdaLogger logger = context.getLogger();
		
		logger.log("Inside LoanApplicationLambda, loanRequest="+loanRequest);
		
		ApplyLoanResponse response = loanApplicationService.applyForALoan(loanRequest, context);
		
		logger.log("ApplyLoanResponse response="+response);
		
		return context;
	}

}
