package TESTLER;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import globalFonksiyonlar.comboboxModelFonksiyonlar;

class comboboxModelFonksiyonlaTEST {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	
	@Test
	void KategoriVektorModelGetirTEST() {
		assertNotNull(comboboxModelFonksiyonlar.KategoriVektorModelGetir());
	}

	@Test
	void UyeTipiVektorModelGetirTEST() {
		assertNotNull(comboboxModelFonksiyonlar.UyeTipiVektorModelGetir());
	}
	@Test
	void YazarVektorModelGetirTEST() {
		assertNotNull(comboboxModelFonksiyonlar.YazarVektorModelGetir());
	}
}
