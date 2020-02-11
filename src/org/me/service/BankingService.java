package org.me.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 
 */

/**
 * @author Mohanarani.S
 *
 */
public class BankingService {

	/**
	 * @param args
	 * @throws IOException
	 */

	public static DateTimeFormatter sdf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

	public static void main(String[] args) throws IOException, ParseException {
		// TODO Auto-generated method stub
		String fileName = "Resources/Transaction.csv";
		Path pathToFile = Paths.get(fileName);
		System.out.println(pathToFile.toAbsolutePath());
		List<String> list = new ArrayList<>();

		List<Transaction> transactionAryList = new ArrayList<>();

		try (Stream<String> stream = Files.lines(Paths.get(fileName)).skip(1)) {

			// 1. filter line 3
			// 2. convert all content to upper case
			// 3. convert it into a List

			list = stream.filter(x -> x != null && !x.isEmpty()).collect(Collectors.toList());

			for (String input : list) {
				String[] transacionStrArr = input.split(",", -1);
				Transaction transactionObj = new Transaction();
				transactionObj.setTransactionId(transacionStrArr[0]);
				transactionObj.setFromAccountId(transacionStrArr[1]);
				transactionObj.setToAccountId(transacionStrArr[2]);
				LocalDateTime dateTime = LocalDateTime.parse(transacionStrArr[3].toString(), sdf);
				transactionObj.setCreatedAt(dateTime);
				transactionObj.setAmount(Float.parseFloat(transacionStrArr[4].toString()));
				transactionObj.setTransactionType(transacionStrArr[5]);
				transactionAryList.add(transactionObj);
			}

			List<BankOutput> bankOutputList = calculateOuput(transactionAryList, "ACC334455", "20/10/2018 12:00:00",
					"20/10/2018 19:00:00");

			System.out.println("The output is:");
			System.out.println("Relative balance for the period is:" + bankOutputList.get(0).getBalance());
			System.out.println("Number of transactions included is:" + bankOutputList.get(0).getCountOfTransaction());

		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	private static List<BankOutput> calculateOuput(List<Transaction> transactionList, String inputAcctId,
			String inputFromDateStr, String inputToDateStr) {

		// Get the list of transactions where the inputAcctId is same as fromAcctID and
		// append to List

		List<BankOutput> BankOutputList = new ArrayList<>();

		for (Transaction transactionObj : transactionList) {

			String fromAcctStr = transactionObj.getFromAccountId();
			LocalDateTime formDateTime = transactionObj.getCreatedAt();

			if (fromAcctStr.trim().equalsIgnoreCase(inputAcctId.trim())) {
				LocalDateTime dateTimefrom = LocalDateTime.parse(inputFromDateStr, sdf);

				LocalDateTime dateTimeTo = LocalDateTime.parse(inputToDateStr, sdf);

				// csv form date is greater than or equal to inputfromdate

				if (formDateTime.compareTo(dateTimefrom) > 0 || formDateTime.compareTo(dateTimefrom) == 0) {

					// csv to date is less than or equal to inputtodate
					if (formDateTime.compareTo(dateTimeTo) < 0 || formDateTime.compareTo(dateTimeTo) == 0) {

						BankOutputList = amountCalculation(transactionObj.getTransactionType(),
								transactionObj.getAmount());
					}
				}
			}
		}
		return BankOutputList;
	}

	private static List<BankOutput> amountCalculation(String transactionType, Float amount) {
		Float debitAmt = 0.0f;
		int count = 0;
		List<BankOutput> bankOutputList = new ArrayList<>();
		BankOutput bankOutputObj = new BankOutput();
		if (transactionType.equalsIgnoreCase("PAYMENT")) {
			// amount is Debited from account
			debitAmt = -(debitAmt + amount);
			count = count + 1;

		} else if (transactionType.equalsIgnoreCase("REVERSAL")) {
			// amount is Credited
			debitAmt = (debitAmt + amount);
			count = count - 1;
		}
		bankOutputList.clear();
		bankOutputObj.setBalance(debitAmt);
		bankOutputObj.setCountOfTransaction(count);
		bankOutputList.add(bankOutputObj);
		return bankOutputList;
	}
}
