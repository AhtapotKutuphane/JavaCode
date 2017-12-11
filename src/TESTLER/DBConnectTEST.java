package TESTLER;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import DatabaseFonksiyonlar.DBConnect;

class DBConnectTEST {

	@BeforeEach
	void setUp() throws Exception {
		DBConnect.Olustur();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void OlusturTEST() {
		assertTrue(DBConnect.Olustur());
	}
	@Test
	void ConnectionTEST() {
		
		assertNotNull(DBConnect._Connection);
	}

}
