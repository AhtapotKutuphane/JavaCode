package TESTLER;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import globalFonksiyonlar.Login_Islem;

class Login_IslemTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testAdminGirisKontrol() {
		try{
		String yetki=new Login_Islem().GirisKontrol("1", "1");
		assertEquals(yetki, "Yönetici");		
		}
		
		catch(Exception e){		
			fail("Not yet implemented");
		}
	}
	
	@Test
	void testMemurGirisKontrol() {
		try{
			
		String yetki=new Login_Islem().GirisKontrol("2", "2");
		assertEquals(yetki, "Memur");		
		}
		
		catch(Exception e){		
			fail("Not yet implemented");
		}
	}
	
	@Test
	void testSorumluGirisKontrol() {
		try{
		String yetki=new Login_Islem().GirisKontrol("1", "1");
		assertEquals(yetki, "Yönetici");		
		}
		
		catch(Exception e){		
			fail("Not yet implemented");
		}
	}
	
	@Test
	void testBosGirisKontrol() {
		try
		{
		String mesaj = new Login_Islem().GirisUzunlukKontrol("","");	
		assertEquals(mesaj, "Kullanici Adi veya Sifre eksik");		
		}
		
		catch(Exception e)
		{		
			fail("Not yet implemented");
		}
	}
	
	@Test
	void test32UzeriGirisKontrol() {
		try
		{
		String mesaj = new Login_Islem().GirisUzunlukKontrol("qwertyudsadiopasdfghjklzxcvbnmqwert","qwertyuiopaasdasdfghjklzxcvbnmqwert");	
		assertEquals(mesaj, "Kullanici Adi veya Sifre 32 Karaketerden fazla, Yeniden Girin");		
		}
		
		catch(Exception e)
		{		
			fail("Not yet implemented");
		}
	}
	
	@Test
	void testGirisKontrol() {
		try
		{
		String mesaj = new Login_Islem().GirisUzunlukKontrol("1","Yönetici");	
		assertEquals(mesaj, "");		
		}
		
		catch(Exception e)
		{		
			fail("Not yet implemented");
		}
	}
	
	

}
