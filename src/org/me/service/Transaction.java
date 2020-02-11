/**
 * 
 */
package org.me.service;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author Mohanarani.S
 *
 */
public class Transaction implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * @return the transactionId
	 */
	public String getTransactionId() {
		return transactionId;
	}
	/**
	 * @param transactionId the transactionId to set
	 */
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	/**
	 * @return the fromAccountId
	 */
	public String getFromAccountId() {
		return fromAccountId;
	}
	/**
	 * @param fromAccountId the fromAccountId to set
	 */
	public void setFromAccountId(String fromAccountId) {
		this.fromAccountId = fromAccountId;
	}
	/**
	 * @return the toAccountId
	 */
	public String getToAccountId() {
		return toAccountId;
	}
	/**
	 * @param toAccountId the toAccountId to set
	 */
	public void setToAccountId(String toAccountId) {
		this.toAccountId = toAccountId;
	}
	/**
	 * @return the createdAt
	 */
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	/**
	 * @param createdAt the createdAt to set
	 */
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	/**
	 * @return the amount
	 */
	public Float getAmount() {
		return amount;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(Float amount) {
		this.amount = amount;
	}
	/**
	 * @return the transactionType
	 */
	public String getTransactionType() {
		return TransactionType;
	}
	/**
	 * @param transactionType the transactionType to set
	 */
	public void setTransactionType(String transactionType) {
		TransactionType = transactionType;
	}
	/**
	 * @return the relatedTransactions
	 */
	public String getRelatedTransactions() {
		return relatedTransactions;
	}
	/**
	 * @param relatedTransactions the relatedTransactions to set
	 */
	public void setRelatedTransactions(String relatedTransactions) {
		this.relatedTransactions = relatedTransactions;
	}
	private String transactionId;
	private String fromAccountId;
	private String toAccountId;
	private LocalDateTime createdAt;
	private Float amount;
	private String TransactionType;
	private String relatedTransactions;
	
	
}
