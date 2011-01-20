package ebank.bank;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ebank.TransactionRequest;

public class AuthorizationImplTest {
	
	private AuthorizationImpl auth;

	@Before
	public void setUp() throws Exception {
		auth = new AuthorizationImpl();
	}

	@After
	public void tearDown() throws Exception {
		auth = null;
	}

	@Test
	public void testProcess() {
		TransactionRequest transaction = new TransactionRequest(Long.parseLong("497300000000000000"), 810, "", 1500, 10);
		auth.process(transaction);
		fail("Not yet implemented");
	}

	@Test
	public void testGetDebitMaxEnCours() {
		fail("Not yet implemented");
	}

	@Test
	public void testSendDebitRequestToBankProcessCenter() {
		fail("Not yet implemented");
	}

}
