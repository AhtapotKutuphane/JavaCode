package TESTLER;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.util.Date;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import globalFonksiyonlar.Fonksiyonlar;

class TedarikciTEST {

final int ID = -1;
	
	static dbClasslar.tedarikci tedarikci = null;
	@BeforeEach
	void setUp() throws Exception {
		tedarikci = new dbClasslar.tedarikci(ID, "Test Verisi", "Icerik Yok","Telefon Numarasý","Adres","h");
		globalFonksiyonlar.comboboxModelFonksiyonlar.YetkiVektorModelGetir();
		tedarikci.tedarikciDBEkle();
	}

	@AfterEach
	void tearDown() throws Exception {
		tedarikci.tedarikciDBSil(ID);
	}

	@Test
	void TedarikciListeleTEST() {
		assertTrue(dbClasslar.kurallar.kurallarDBList());
	}

	@Test
	void TedarikciListeleVerilenIDTEST() {
		assertEquals(tedarikci.getT_ad(), dbClasslar.tedarikci.tedarikciDBList(ID).getT_ad());
	}

	@Test
	void TedarikciGuncelleTEST(){
		tedarikci = new dbClasslar.tedarikci(ID, "Güncelleme Test", "Icerik Yok","Telefon Numarasý","Adres","h");
		tedarikci.tedarikciDBGüncelle();
		assertEquals("Güncelleme Test", dbClasslar.tedarikci.tedarikciDBList(ID).getT_ad());
	}
	@Test
	void TedarikciGetModelTEST() {
		
		assertNotNull(tedarikci.tedarikciGetModel());
	}
}
