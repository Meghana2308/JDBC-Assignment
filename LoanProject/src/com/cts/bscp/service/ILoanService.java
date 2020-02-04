package com.cts.bscp.service;

import com.cts.bscp.model.Loan;
import com.cts.bscp.exception.LoanException;

import java.util.List;

public interface ILoanService {	
	String add(Loan loan) throws LoanException;
	boolean delete(String transactionId) throws LoanException;
	Loan get(String transactionId) throws LoanException;
	List<Loan> getAll() throws LoanException;;
	boolean update(Loan loan) throws LoanException;
	void persist()throws LoanException;
}
