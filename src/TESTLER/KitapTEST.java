package TESTLER;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dbClasslar.kitap;
import globalFonksiyonlar.Fonksiyonlar;

class KitapTEST {

	dbClasslar.kitap KitapTESTobj = new dbClasslar.kitap(100,1, 3, 3, 3, 1, "masa");
	
	@BeforeEach
	void setUp() throws Exception {
		KitapTESTobj.kitapDBEkle();
	}

	@AfterEach
	void tearDown() throws Exception {
		KitapTESTobj.kitapDBSil();
	}
	
	@Test
	void KitapListeleHerseyiTEST() {
		assertTrue(kitap.kitapDBList());
	}
	
	@Test
	void KitapListeleVerilenIDTEST() {
		assertEquals(KitapTESTobj.getK_ad(), kitap.kitapDBList(100).getK_ad());
	}
	
	@Test
	void KitapGüncelleTEST() {
		KitapTESTobj.kitapDBGüncelle("sandalye", 2, 3, 3, 3, 1, 100);
		assertEquals("sandalye", kitap.kitapDBList(100).getK_ad());
	}
	@Test
	void KitapGüncelleVerilenVerilerinTEST() {
		assertTrue(new kitap(100, 2, 10, 10, 10, 2, "laptop").kitapDBGüncelle());
	}
	
	@Test
	void kitapGetModelTEST(){
		globalFonksiyonlar.comboboxModelFonksiyonlar.YazarVektorModelGetir();
		globalFonksiyonlar.comboboxModelFonksiyonlar.KategoriVektorModelGetir();
		assertNotNull(KitapTESTobj.kitapGetModel());
	}
	
}
