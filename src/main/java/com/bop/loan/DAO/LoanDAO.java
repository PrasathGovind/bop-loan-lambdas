package com.bop.loan.DAO;


import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.bop.configs.DynamoDBConfig;
import com.bop.loan.beans.LoanRequestDTO;


public class LoanDAO { 
	
	DynamoDBConfig dynamoDBConfig = new DynamoDBConfig();
	
	DynamoDBMapper dynamoDBMapper = dynamoDBConfig.getDynamoDBMapper();
	
	public void saveLoanRequest(LoanRequestDTO loanRequestDTO) {
		
		dynamoDBMapper.save(loanRequestDTO);
	}

}
