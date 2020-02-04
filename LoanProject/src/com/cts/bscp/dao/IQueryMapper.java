package com.cts.bscp.dao;

public interface IQueryMapper {

	public static final String ADD_LOAN_QRY = 
			"INSERT INTO loans(transactionId,  branch, amountOfLoan, dateIssued) VALUES(?,?,?,?)";
	public static final String MODIFY_LOAN_QRY = 
			"UPDATE loans SET branch=?,amountOfLoan=?,dateIssued=? WHERE transactionId=?";
	public static final String DEL_LOAN_QRY = 
			"DELETE FROM loans WHERE transactionId=?";
	public static final String GET_ALL_LOAN_QRY = 
			"SELECT * FROM loans";
	public static final String GET_LOAN_QRY = 
			"SELECT * FROM loans WHERE transactionId=?";
}