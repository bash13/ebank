package ebank;

/**
 * Classe repr�sentant une transaction 
 *
 * @author JacORB IDL compiler V 2.3.1, 27-May-2009
 * @version generated at 9 janv. 2011 18:23:44
 */
public final class TransactionRequest implements org.omg.CORBA.portable.IDLEntity
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TransactionRequest(){}
	
	/**
	 * Num�ro de la carte
	 */
	public long card_number;
	
	/**
	 * CCV de la carte
	 */
	public int ccv;
	
	/**
	 * Date de p�remption de la carte
	 */
	public java.lang.String date = "";
	
	/**
	 * Montant de la transaction
	 */
	public float amount;
	
	/**
	 * Num�ro de compte du marchand
	 */
	public int dealer_account_number;
	
	public long getCard_number() {
		return card_number;
	}

	public void setCard_number(long card_number) {
		this.card_number = card_number;
	}

	public int getCcv() {
		return ccv;
	}

	public void setCcv(int ccv) {
		this.ccv = ccv;
	}

	public java.lang.String getDate() {
		return date;
	}

	public void setDate(java.lang.String date) {
		this.date = date;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public int getDealer_account_number() {
		return dealer_account_number;
	}

	public void setDealer_account_number(int dealer_account_number) {
		this.dealer_account_number = dealer_account_number;
	}

	public TransactionRequest(long card_number, int ccv, java.lang.String date, float amount, int dealer_account_number)
	{
		this.card_number = card_number;
		this.ccv = ccv;
		this.date = date;
		this.amount = amount;
		this.dealer_account_number = dealer_account_number;
	}
	
	/**
	 * Renvoie le BIN du num�ro de la carte
	 * @return
	 */
	public Integer getBin()
	{
		String cbString = ""+this.card_number;
		return Integer.parseInt(cbString.substring(1,4));
	}
}
