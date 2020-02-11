/**
 * 
 */
package org.me.service;

/**
 * @author Mohanarani.S
 *
 */
public class BankOutput {
	
	private Float Balance;

	private Integer countOfTransaction;
	
	public Float getBalance() {
		return Balance;
	}
	public void setBalance(Float balance) {
		Balance = balance;
	}
	public Integer getCountOfTransaction() {
		return countOfTransaction;
	}
	public void setCountOfTransaction(Integer countOfTransaction) {
		this.countOfTransaction = countOfTransaction;
	}

}
