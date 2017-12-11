package TESTLER;

import static org.junit.jupiter.api.Assertions.*;

import javax.swing.JFrame;
import javax.swing.JTable;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dbClasslar.kitap;
import globalDegiskenler.DiziDegiskenler;
import globalFonksiyonlar.Fonksiyonlar;

class FonksiyonlarTEST {

	JFrame frame = null;
	JTable table = null;
	kitap ki = null;
	@BeforeEach
	void setUp() throws Exception {
		ki= new dbClasslar.kitap(100,1, 3, 3, 3, 1, "masa");
		globalFonksiyonlar.comboboxModelFonksiyonlar.YazarVektorModelGetir();
		globalFonksiyonlar.comboboxModelFonksiyonlar.KategoriVektorModelGetir();
		frame = Fonksiyonlar.AnaFormOlustur(0, 0, 0, 0);
		table = Fonksiyonlar.JTableOlustur(DiziDegiskenler.KitapListeleme);
		table = Fonksiyonlar.JTableVeriEkleme(table, ki.kitapGetModel());
	}

	@AfterEach
	void tearDown() throws Exception {
		
	}
	
	
	@Test
	void ToplamTEST() {
		assertTrue(Fonksiyonlar.Toplam(1, 2, 3) == 6);
	}
	@Test
	void SayiMýTEST() {
		assertTrue(Fonksiyonlar.SayiMý("123","123555","123"));
	}
	@Test
	void BosMuTEST() {
		assertTrue(Fonksiyonlar.BosMu("ÞuanBoþDeðil"));
	}
	@Test
	void RakamKadarHaneMiTEST() {
		
		assertTrue(Fonksiyonlar.RakamKadarHaneMi(11, "12345678910"));
	}
	@Test
	void ComboboxSecinizDýþýndakiVeriMiTEST() {
		assertTrue(Fonksiyonlar.ComboboxSecinizDýþýndakiVeriMi(1,2,3,4,5,6,6,6,6,2,3));
	}
	
	@Test
	void JTableVeriTemizlemeTEST() {
		assertNotNull(Fonksiyonlar.JTableVeriTemizleme(table));
	}
	
	@Test
	void JTableVeriSilmeTEST() {
		// Tek veri eklendiði için sadece 0 indexini silebilir.
		assertNotNull(Fonksiyonlar.JTableVeriSilme(table, 0));
	}
	
	@Test
	void JTableVeriEklemeTEST() {
		assertNotNull(Fonksiyonlar.JTableVeriEkleme(table, ki.kitapGetModel()));
	}
	
	@Test
	void JTableOluþturTEST() {
		assertNotNull(Fonksiyonlar.JTableOlustur(DiziDegiskenler.KitapListeleme));
	}
	
	@Test
	void frameElementEkleTEST() {
		assertNotNull(Fonksiyonlar.frameElementEkle(frame,table));
	}
	
	@Test
	void FrameOlusturTEST() {
		assertNotNull(Fonksiyonlar.AnaFormOlustur(0, 0, 0, 0));
	}
	@Test
	void FrameOlusturBaslýklaTEST() {
		assertNotNull(Fonksiyonlar.AnaFormOlustur(0, 0, 0, 0,"ORNEKTEST"));
	}
	@Test
	void JTattooYukleTEST() {
		assertTrue(Fonksiyonlar.JTattooYukle());
	}
		
}
