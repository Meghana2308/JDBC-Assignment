package com.cts.bscp.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.cts.bscp.dao.LoanDAOCollectionImpl;
import com.cts.bscp.dao.LoanDAOIOStreamImpl;
import com.cts.bscp.dao.LoanDAOJDBCImpl;
import com.cts.bscp.dao.ILoanDAO;
import com.cts.bscp.exception.LoanException;
import com.cts.bscp.exception.LoanException;
import com.cts.bscp.model.Loan;
import com.cts.bscp.model.Loan;

public class LoanServiceImpl implements ILoanService {
	
	private ILoanDAO loanDao;

	public ILoanDAO getDAO(){
		return loanDao;
	}
	
	public LoanServiceImpl() throws LoanException {
		//loanDao = new loanDAOCollectionImpl();
		//loanDao = new LoanDAOIOStreamImpl();
		loanDao= new LoanDAOJDBCImpl();
		
	}
	
	public boolean isValidtransactionId(String transactionId){
		
		Pattern transactionIdPattern = Pattern.compile("[A-Z]\\d{3}");
		Matcher transactionIdMatcher = transactionIdPattern.matcher(transactionId);
		
		return transactionIdMatcher.matches();
	}
	
	public boolean isValidTitle(String branch){
		
		Pattern branchPattern = Pattern.compile("[A-Z]\\w{3,19}");
		Matcher branchMatcher = branchPattern.matcher(branch);
		
		return branchMatcher.matches();
	}
	
	public boolean isValidPrice(double amountOfLoan){
		
		return amountOfLoan>=5000 && amountOfLoan<=500000;
	}
	
	public boolean isValidPublishDate(LocalDate DateIssued){
		
		LocalDate today = LocalDate.now();
		//return publishDate.isBefore(today) || publishDate.equals(today);
		return today.isAfter(DateIssued) || DateIssued.equals(today);
	}
	
	public boolean isValidloan(Loan loan) throws LoanException{
		boolean isValid=false;
		
		List<String> errMsgs = new ArrayList<>();
		
		if(!isValidtransactionId(loan.getTransactionId()))
			errMsgs.add("transactionId should start with a capital alphabet followed by 3 digits");
		
		if(!isValidTitle(loan.getBranch()))
			errMsgs.add("Branch must start with capital and must be in between 4 to 20 chars in length");
		
		if(!isValidPrice(loan.getAmountOfLoan()))
			errMsgs.add("Amount of loan must be between INR.5 and INR.5000");
		
		if(!isValidPublishDate(loan.getDateIssued()))
			errMsgs.add(" Date issued should not be a future date");
		
		if(errMsgs.size()==0)
			isValid=true;
		else
			throw new LoanException(errMsgs.toString());
		
		return isValid;
	}


	@Override
	public String add(Loan loan) throws LoanException {
		String transactionId=null;
		if(loan!=null && isValidloan(loan)){
			transactionId=loanDao.add(loan);
		}
		return transactionId;
	}

	@Override
	public boolean delete(String transactionId) throws LoanException {
		boolean isDone=false;
		if(transactionId!=null && isValidtransactionId(transactionId)){
			loanDao.delete(transactionId);
			isDone = true;
		}else{
			throw new LoanException("transaction Id should be a capital letter followed by 3 digits");
		}
		return isDone;
	}

	@Override
	public Loan get(String transactionId) throws LoanException {
		return loanDao.get(transactionId);
	}

	@Override
	public List<Loan> getAll() throws LoanException {
		return loanDao.getAll();
	}

	@Override
	public boolean update(Loan loan) throws LoanException {
		boolean isDone = false;
		
		if(loan!=null && isValidloan(loan)){
			isDone = loanDao.update(loan);
		}
		
		return isDone;
	}


	@Override
	public void persist() throws LoanException {
		loanDao.persist();
}
}
