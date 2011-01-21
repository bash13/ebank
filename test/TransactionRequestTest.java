package ebank.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ebank.TransactionRequest;

public class TransactionRequestTest {
	
	private TransactionRequest transaction;

	@Before
	public void setUp() throws Exception {
		transaction = new TransactionRequest(Long.parseLong("497400000000000000"), 8010, "", 1500, 1, 5);
	}

	@After
	public void tearDown() throws Exception {
		transaction = null;
	}

	@Test
	public void testGetBin() {
		String bin = transaction.getBin();
		assertEquals("974", bin);
	}

}
