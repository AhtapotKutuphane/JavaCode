package TESTLER;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.ParseException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dbClasslar.kitap;
import globalFonksiyonlar.Fonksiyonlar;

public class UyelerTEST {
		static dbClasslar.uyeler uye = null;

	@BeforeEach
	void setUp() throws Exception {
		uye = new dbClasslar.uyeler(15, "23332365342", "Alican", "Ertop", Fonksiyonlar.StringToDate("2017-12-07"), "Öðrenci", "23234212342", "@gmail.com", "EV","h", 1);
		globalFonksiyonlar.comboboxModelFonksiyonlar.UyeTipiVektorModelGetir();
		uye.uyeDBEkle();
	}

	@AfterEach
	void tearDown() throws Exception {
		uye.uyeDBSil();
	}

	@Test
	void UyeListeleHerseyiTEST() {
		assertTrue(dbClasslar.uyeler.uyeDBList());
	}

	@Test
	void UyeListeleVerilenIDTEST() {
		assertEquals(uye.getU_ad(), dbClasslar.uyeler.uyeDBList(15).getU_ad());
	}

	@Test
	void UyeGuncelleTEST() throws ParseException {
		uye = new dbClasslar.uyeler(15, "23332332342", "Serhat", "Kaya", Fonksiyonlar.StringToDate("2017-12-07"), "Öðrenci", "23234212342", "@gmail.com", "EV","h", 1);
		uye.uyeDBGüncelle();
		assertEquals("Serhat", dbClasslar.uyeler.uyeDBList(15).getU_ad());
	}
	@Test
	void UyeGetModelTEST() {
		
		assertNotNull(uye.uyeGetModel());
	}
}
