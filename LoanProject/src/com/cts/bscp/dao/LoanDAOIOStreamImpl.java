package com.cts.bscp.dao;

import com.cts.bscp.model.Loan;
import com.cts.bscp.exception.LoanException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class LoanDAOIOStreamImpl implements ILoanDAO {
	public static final String DATA_STORE_FILE_NAME = "loan.dat";

	private Map<String, Loan> loans;

	@SuppressWarnings("unchecked")
	public LoanDAOIOStreamImpl() throws LoanException {
		File file = new File(DATA_STORE_FILE_NAME);

		if (!file.exists()) {
			loans = new TreeMap<>();
		} else {
			try (ObjectInputStream fin = new ObjectInputStream(
					new FileInputStream(DATA_STORE_FILE_NAME))) {

				Object obj = fin.readObject();

				if (obj instanceof Map) {
					loans = (Map<String, Loan>) obj;
				} else {
					throw new LoanException("Not a valid DataStore");
				}
			} catch (IOException | ClassNotFoundException exp) {
				throw new LoanException("Loading Data Failed");
			}
		}
	}

	@Override
	public String add(Loan loan) throws LoanException {
		String transactionId = null;
		if (loan != null) {
			transactionId =loan.getTransactionId();
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
		return new ArrayList<Loan>(loans.values());
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

	
	public void persist() throws LoanException {
		try (ObjectOutputStream fout = new ObjectOutputStream(
				new FileOutputStream(DATA_STORE_FILE_NAME))) {
			fout.writeObject(loans);
		} catch (IOException exp) {
			throw new LoanException("Saving Data Failed");
		}
	}
}
