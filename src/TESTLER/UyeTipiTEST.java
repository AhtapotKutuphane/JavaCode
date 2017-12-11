package TESTLER;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dbClasslar.uyetipi;
import globalFonksiyonlar.Fonksiyonlar;

class UyeTipiTEST {

	
	dbClasslar.uyetipi UyeTipiTESTobj = new dbClasslar.uyetipi(100,"Doktor", 3, 3,"h");
	
	@BeforeEach
	void setUp() throws Exception {
		globalFonksiyonlar.comboboxModelFonksiyonlar.UyeTipiVektorModelGetir();
		UyeTipiTESTobj.uyetipiDBEkle();
	}

	@AfterEach
	void tearDown() throws Exception {
		UyeTipiTESTobj.uyetipiDBSil();
	}
	
	@Test
	void UyeTipiListeleHerseyiTEST() {
		assertTrue(uyetipi.uyetipiDBList());
	}
	
	@Test
	void UyeTipiListeleVerilenIDTEST() {
		assertEquals(UyeTipiTESTobj.getUt_ad(), uyetipi.uyetipiDBList(100).getUt_ad());
	}
	
	@Test
	void UyeTipiGüncelleTEST() {
		dbClasslar.uyetipi test = uyetipi.uyetipiDBList(100);
		UyeTipiTESTobj.uyetipiDBGüncelle("Mimar", 3, 3,100);
		assertEquals("Mimar", uyetipi.uyetipiDBList(100).getUt_ad());
		
	}
	@Test
	void UyeTipiGüncelleVerilenVerilerinTEST() {
		assertTrue(new uyetipi(100,"Mühendis", 3, 3,"h").uyetipiDBGüncelle());
	}
	
	
	
}
