package TESTLER;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.util.Date;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import globalFonksiyonlar.Fonksiyonlar;

class PersonelTEST {

	
	static dbClasslar.personel personel = null;
	@BeforeEach
	void setUp() throws Exception {
		personel = new dbClasslar.personel(15, "11111131111", "Alican", "Ertop",Fonksiyonlar.StringToDate("2017-12-07") ,"Mudur", "23234212342", "@gmail.com", "EV", "1");
		globalFonksiyonlar.comboboxModelFonksiyonlar.YetkiVektorModelGetir();
		personel.personelDBEkle();
	}

	@AfterEach
	void tearDown() throws Exception {
		personel.personelDBSil();
	}

	@Test
	void PersonelListeleTEST() {
		assertTrue(dbClasslar.uyeler.uyeDBList());
	}

	@Test
	void PersonelListeleVerilenIDTEST() {
		assertEquals(personel.getP_ad(), dbClasslar.personel.personelDBList(15).getP_ad());
	}

	@Test
	void UyeGuncelleTEST() throws ParseException {
		personel = new dbClasslar.personel(15, "11111131111", "Serhat", "Kaya",  Fonksiyonlar.StringToDate("2017-12-07"), "Personel", "23234212342", "@gmail.com", "ÝÞ", "1");
		personel.personelDBGüncelle();
		assertEquals("Serhat", dbClasslar.personel.personelDBList(15).getP_ad());
	}
	@Test
	void UyeGetModelTEST() {
		
		assertNotNull(personel.personelGetModel());
	}

}
