package TESTLER;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.util.Date;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import globalFonksiyonlar.Fonksiyonlar;

class KurallarTEST {
	
	final int ID = -1;
	
	static dbClasslar.kurallar kurallar = null;
	@BeforeEach
	void setUp() throws Exception {
		kurallar = new dbClasslar.kurallar(ID, "Test Verisi", "Icerik Yok");
		globalFonksiyonlar.comboboxModelFonksiyonlar.YetkiVektorModelGetir();
		kurallar.kurallarDBEkle();
	}

	@AfterEach
	void tearDown() throws Exception {
		kurallar.kurallarDBSil();
	}

	@Test
	void KurallarListeleTEST() {
		assertTrue(dbClasslar.kurallar.kurallarDBList());
	}

	@Test
	void KurallarListeleVerilenIDTEST() {
		assertEquals(kurallar.getKur_ad(), dbClasslar.kurallar.kurallarDBList(ID).getKur_ad());
	}

	@Test
	void KurallarGuncelleTEST(){
		kurallar = new dbClasslar.kurallar(ID, "Güncelleme Test", "Icerik Yok");
		kurallar.kurallarDBGüncelle();
		assertEquals("Güncelleme Test", dbClasslar.kurallar.kurallarDBList(ID).getKur_ad());
	}
	@Test
	void KurallarGetModelTEST() {
		
		assertNotNull(kurallar.kurallarGetModel());
	}

}
