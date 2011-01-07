package ebank.bank;

import java.util.Date;

/**
 * Classe représentant une demande de transaction
 * @author alex
 *
 */
public class TransactionRequest {

//	/**
//	 * Numéro de la carte
//	 */
//	private CardNumber card_number;
	
	/**
	 * Numéro de la carte
	 */
	private Long card_number;
	
	/**
	 * Numéro CCV de la carte
	 */
	private Integer ccv;
	
	/**
	 * Date de la carte
	 */
	private Date date;
		
	/**
	 * Montant de la transaction
	 */
	private Float amount;
	
	/**
	 * Numéro de compte du commerçant
	 */
	private Integer dealer_account_number;	
	
	
	public Long getCard_number() {
		return card_number;
	}

	public void setCard_number(Long cardNumber) {
		card_number = cardNumber;
	}

	public Integer getCcv() {
		return ccv;
	}

	public void setCcv(Integer ccv) {
		this.ccv = ccv;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	public Integer getDealer_account_number() {
		return dealer_account_number;
	}

	public void setDealer_account_number(Integer dealerAccountNumber) {
		dealer_account_number = dealerAccountNumber;
	}

	public TransactionRequest(Long card_number, Integer dealer_account_number, Float amount) {
		this.dealer_account_number=dealer_account_number;
		this.amount=amount;
	}
	
}
