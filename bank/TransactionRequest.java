package ebank.bank;

/**
 * Classe représentant une demande de transaction
 * @author alex
 *
 */
public class TransactionRequest {

	/**
	 * Numéro de la carte
	 */
	private CardNumber card_number;
	
	/**
	 * Montant de la transaction
	 */
	private Float amount;
	
	/**
	 * Numéro de compte du commerçant
	 */
	private Integer dealer_account_number;	
	
	
	public CardNumber getCard_number() {
		return card_number;
	}

	public void setCard_number(CardNumber cardNumber) {
		card_number = cardNumber;
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
		this.card_number=new CardNumber(card_number);
		this.dealer_account_number=dealer_account_number;
		this.amount=amount;
	}
	
}
