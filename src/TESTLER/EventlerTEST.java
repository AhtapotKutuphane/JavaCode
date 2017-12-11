package TESTLER;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import globalFonksiyonlar.Eventler;

class EventlerTEST {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void AnasayfaGeriDonActionListenerTEST() {
		assertNotNull(Eventler.AnasayfaGeriDonActionListener);
	}
	@Test
	void FormKapatGeriDonActionListenerTEST() {
		assertNotNull(Eventler.FormKapatGeriDonActionListener);
	}

}
