package com.cts.bscp.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.cts.bscp.exception.LoanException;
import com.cts.bscp.model.Loan;

public class LoanDAOCollectionImpl implements ILoanDAO {
	
	private Map<String, Loan> loans;
	
	public LoanDAOCollectionImpl() {
		loans = new TreeMap<>();
	}
	
	@Override
	public String add(Loan loan) throws LoanException {
		String transactionId = null;
		if (loan != null) {
			transactionId = loan.getTransactionId();
			loans.put(transactionId, loan);	
		}
		return transactionId;
	}
	
	@Override
	public boolean delete(String transactionId) throws LoanException {
		boolean isDone = false;
		if (transactionId != null) {
			loans.remove(transactionId);
			isDone = true;
		}
		return isDone;
	}
	
	@Override
	public Loan get(String transactionId) throws LoanException {
		return loans.get(transactionId);
	}
	

	@Override
	public List<Loan> getAll() throws LoanException {
		return new ArrayList<>(loans.values());
	}
	
	@Override
	public boolean update(Loan loan) throws LoanException {
		boolean isDone = false;
		if (loan != null) {
			String transactionId = loan.getTransactionId();
			loans.replace(transactionId, loan);
			
		}
		return isDone;
	}

	@Override
	public void persist() throws LoanException {
		
	}
	
	
}
