package com.cts.bscp.dao;


import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cts.bscp.exception.LoanException;
import com.cts.bscp.model.Loan;
import com.cts.bscp.util.ConnectionProvider;

//import org.apache.log4j.Logger;

public class LoanDAOJDBCImpl implements ILoanDAO {

	ConnectionProvider conProvider;
	//Logger log;

	public LoanDAOJDBCImpl() throws LoanException {
		// log = Logger.getLogger("dao");

		try {
			conProvider = ConnectionProvider.getInstance();
		} catch (ClassNotFoundException | IOException exp) {
			//log.error(exp);
			throw new LoanException("Database is not reachable");
		}
	}

	@Override
	public String add(Loan loan) throws LoanException {
		String transactionId = null;
		if (loan != null) {
			try (Connection con = conProvider.getConnection();
					PreparedStatement pInsert = con
							.prepareStatement(IQueryMapper.ADD_LOAN_QRY)) {

				pInsert.setString(1, loan.getTransactionId());
				pInsert.setString(2, loan.getBranch());
				pInsert.setDate(4, Date.valueOf(loan.getDateIssued()));
				pInsert.setDouble(3, loan.getAmountOfLoan());
				

				int rowCount = pInsert.executeUpdate();

				if (rowCount == 1) {
					transactionId = loan.getTransactionId();
				}
			} catch (SQLException exp) {
				//log.error(exp);
				throw new LoanException("Loan is not added"+exp);
			}
		}
		return transactionId;
	}

	@Override
	public boolean delete(String transactionId) throws LoanException {
		boolean isDone = false;

		try (Connection con = conProvider.getConnection();
				PreparedStatement pDelete = con
						.prepareStatement(IQueryMapper.DEL_LOAN_QRY)) {

			pDelete.setString(1, transactionId);

			int rowCount = pDelete.executeUpdate();

			if (rowCount == 1) {
				isDone = true;
			}
		} catch (SQLException exp) {
			//log.error(exp);
			throw new LoanException("Loan is not added");
		}

		return isDone;
	}

	@Override
	public Loan get(String transactionId) throws LoanException {
		Loan loan=null;
		try (Connection con = conProvider.getConnection();
				PreparedStatement pSelect = con
						.prepareStatement(IQueryMapper.GET_LOAN_QRY)) {

			pSelect.setString(1, transactionId);

			ResultSet rs = pSelect.executeQuery();
			
			if(rs.next()){
				loan = new Loan();
				loan.setTransactionId(rs.getString("transactionId"));
				loan.setBranch(rs.getString("Branch"));
				loan.setAmountOfLoan(rs.getDouble("AmountOfLoan"));
				loan.setDateIssued(rs.getDate("DateIssued").toLocalDate());
			}
			
		} catch (SQLException exp) {
			//log.error(exp);
			throw new LoanException("Loan is not available");
		}		
		return loan;
	}

	@Override
	public List<Loan> getAll() throws LoanException {
		List<Loan> loans=null;
		try (Connection con = conProvider.getConnection();
				PreparedStatement pSelect = con
						.prepareStatement(IQueryMapper.GET_ALL_LOAN_QRY)) {

			ResultSet rs = pSelect.executeQuery();
			
			loans = new ArrayList<Loan>();
			
			while(rs.next()){
				Loan loan = new Loan();
				loan.setTransactionId(rs.getString("transactionId"));
				loan.setBranch(rs.getString("Branch"));
				loan.setAmountOfLoan(rs.getDouble("amount of loan"));
				loan.setDateIssued(rs.getDate("date issued").toLocalDate());
				loans.add(loan);
			}
			
		} catch (SQLException exp) {
			//log.error(exp);
			throw new LoanException("No Books are available.");
		}		
		return loans;	
	}

	@Override
	public boolean update(Loan loan) throws LoanException {
		boolean isDone = false;
		if (loan != null) {
			try (Connection con = conProvider.getConnection();
					PreparedStatement pUpdate = con
							.prepareStatement(IQueryMapper.MODIFY_LOAN_QRY)) {

				
				pUpdate.setString(2, loan.getBranch());
				pUpdate.setDouble(3, loan.getAmountOfLoan());
				pUpdate.setDate(4, Date.valueOf(loan.getDateIssued()));
				pUpdate.setString(1, loan.getTransactionId());
				

				int rowCount = pUpdate.executeUpdate();

				if (rowCount == 1) {
					isDone = true;
				}
			} catch (SQLException exp) {
				//log.error(exp);
				throw new LoanException("loan is not updated.");
			}
		}
		return isDone;
	}

	@Override
	public void persist() throws LoanException {
		/* no implementation required */
		
	}
}
