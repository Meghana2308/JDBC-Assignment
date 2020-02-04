package com.cts.bscp.model;

import java.io.Serializable;
import java.time.LocalDate;


@SuppressWarnings("serial")
public class Loan implements Serializable {

	private String transactionId;
	private String branch;
	private LocalDate dateIssued;
	private double amountOfLoan;

	public Loan() {
		
	}

	public Loan(String transactionId, String branch, LocalDate dateIssued, double amountOfLoan) {
		super();
		this.transactionId = transactionId;
		this.branch = branch;
		this.dateIssued = dateIssued;
		this.amountOfLoan = amountOfLoan;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public LocalDate getDateIssued() {
		return dateIssued;
	}

	public void setDateIssued(LocalDate dateIssued) {
		this.dateIssued = dateIssued;
	}

	public double getAmountOfLoan() {
		return amountOfLoan;
	}

	public void setAmountOfLoan(double amountOfLoan) {
		this.amountOfLoan = amountOfLoan;
	}

	@Override
	public String toString() {
		StringBuilder output = new StringBuilder("transaction id : ");
		output.append(transactionId);
		output.append("\tbranch : ");
		output.append(branch);
		output.append("\tdate issued :");
		output.append(dateIssued);
		output.append("\tamountOfLoan : ");
		output.append(amountOfLoan);
		return output.toString();
	}
}
