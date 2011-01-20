package ebank.test;


import org.junit.After;
import org.junit.Before;

import ebank.bank.AuthorizationImpl;

public class AuthorizationServerTest {
	
	private AuthorizationImpl auth;

	@Before
	public void setUp() throws Exception {
		auth = new AuthorizationImpl();
	}

	@After
	public void tearDown() throws Exception {
		auth=null;
	}
	
	

}
