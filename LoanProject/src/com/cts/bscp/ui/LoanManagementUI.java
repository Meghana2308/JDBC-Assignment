package com.cts.bscp.ui;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import com.cts.bscp.exception.LoanException;
import com.cts.bscp.model.Loan;
import com.cts.bscp.model.LoanAppMenu;
import com.cts.bscp.service.LoanServiceImpl;
import com.cts.bscp.service.ILoanService;

public class LoanManagementUI {

	private static ILoanService loanService;
	private static Scanner scan;
	private static DateTimeFormatter dtFormater;

	public static void main(String[] args) {

		try {
			loanService = new LoanServiceImpl();
		} catch (LoanException e) {
			e.printStackTrace();
		}


		scan = new Scanner(System.in);
		dtFormater = DateTimeFormatter.ofPattern("dd-MM-yyyy");

		LoanAppMenu menu = null;

		while (menu != LoanAppMenu.QUIT) {

			System.out.println("Menu Item");
			System.out.println("--------------");
			for (LoanAppMenu m : LoanAppMenu.values()) {
				System.out.println(m.ordinal() + "\t" + m.name());
			}
			System.out.print("Choice: ");
			int choice = -1;
			if (scan.hasNextInt())
				choice = scan.nextInt();
			else {
				scan.next();
				System.out.println("Pleae choose a choice displayed");
				continue;
			}

			if (choice < 0 || choice >= LoanAppMenu.values().length) {
				System.out.println("Invalid Choice");
			} else {
				menu = LoanAppMenu.values()[choice];
				switch (menu) {
				case ADD:
					doAdd();
					break;
				case LIST:
					doList();
					break;
				case SEARCH:
					doSearch();
					break;
				case REMOVE:
					doRemove();
					break;
				case QUIT:
					System.out.println("ThanQ Come Again!");
					break;
				}
			}

		}

		scan.close();
		try {
			loanService.persist();
		} catch (LoanException e) {
			e.printStackTrace();
		}

	}

	private static void doAdd() {
		try {
			Loan book = new Loan();
			System.out.print("transactionId: ");
			book.setTransactionId(scan.next());
			System.out.print("Branch: ");
			book.setBranch(scan.next());
			System.out.print("Date issued(dd-MM-yyyy): ");
			String pubDtStr = scan.next();

			try {
				book.setDateIssued(LocalDate.parse(pubDtStr, dtFormater));
			} catch (DateTimeException exp) {
				throw new LoanException(
						"Date must be in the format day(dd)-month(MM)-year(yyyy)");
			}
			System.out.print("Amount of loan: ");
			if (scan.hasNextDouble())
				book.setAmountOfLoan(scan.nextDouble());
			else {
				scan.next();
				throw new LoanException("amount of loan is a number");
			}

			String transactionId = loanService.add(book);
			System.out.println("loan is Added with code: " + transactionId);
		} catch (LoanException exp) {
			System.out.println(exp.getMessage());
		}
	}

	private static void doList() {
		List<Loan> loans;
		try {
			loans = loanService.getAll();
			if (loans.size() == 0) {
				System.out.println("No loansTo display");
			} else {
				for (Loan b : loans)
					System.out.println(b);
			}
		} catch (LoanException exp) {
			System.out.println(exp.getMessage());
		}
	}

	private static void doSearch() {
		System.out.print("transaction id: ");
		String transactionId = scan.next();

		try {
			Loan loan =loanService.get(transactionId);
			if (loan != null) {
				System.out.println(loan);
			} else {
				System.out.println("No Such loan");
			}
		} catch (LoanException exp) {
			System.out.println(exp.getMessage());
		}
	}

	private static void doRemove() {
		System.out.print("transaction id: ");
		String transactionId = scan.next();
		try {
			boolean isDone = loanService.delete(transactionId);
			if (isDone) {
				System.out.println("Loan is Canceled");
			} else {
				System.out.println("No Such Loan");
			}
		} catch (LoanException exp) {
			System.out.println(exp.getMessage());
		}
	}
}