package ebank.bank;

public class CardNumber {
		
	/**
	 * Bank Identify Number (4 premiers chiffres de la carte)
	 */
	private Integer bin;
	
	/**
	 * Numéro identifiant le client (12 derniers chiffres de la carte)
	 */
	private Integer client_code;	
	
	public Integer getBin() {
		return bin;
	}

	public void setBin(Integer bin) {
		this.bin = bin;
	}

	public Integer getClient_code() {
		return client_code;
	}

	public void setClient_code(Integer clientCode) {
		client_code = clientCode;
	}
	
	public Long getCardNumberOrigin()
	{
		return Long.parseLong(String.valueOf(bin)+String.valueOf(client_code));
	}
	
	public CardNumber(Long card_number) {
		String s = card_number.toString();
		bin = Integer.parseInt(s.substring(0,4));
		client_code = Integer.parseInt(s.substring(4, 16));
	}
	
	public CardNumber(Integer bin, Integer client_code) {
		this.bin=bin;
		this.client_code=client_code;
	}
}
