package globalFonksiyonlar;

import java.sql.ResultSet;
import java.sql.Statement;

import globalDegiskenler.DiziOlmayanDegiskenler;

public class Login_Islem {

	public String GirisUzunlukKontrol(String kullaniciAdi2, String sifre2) {

		String mesaj = "";

		if (kullaniciAdi2.length() == 0 || sifre2.length() == 0)
			mesaj = "Kullanici Adi veya Sifre eksik";

		if (kullaniciAdi2.length() > 32 || sifre2.length() > 32)
			mesaj = "Kullanici Adi veya Sifre 32 Karaketerden fazla, Yeniden Girin";

		return mesaj;
	}

	public String GirisKontrol(String kullaniciAdi, String sifre) {
		String yetki = "";
		String sql = "Select p_id,p_ad,p_yetki From personel WHERE p_tc='" + kullaniciAdi + "' AND p_sifre='" + sifre
				+ "'";

		try {
			DatabaseFonksiyonlar.DBConnect.Olustur();
			Statement s = DatabaseFonksiyonlar.DBConnect._Connection.prepareStatement(sql);
			ResultSet rs = s.executeQuery(sql);

			while (rs.next()) {
				DiziOlmayanDegiskenler.Yetki = rs.getString(3);
				yetki = rs.getString(3);
			}
			return yetki;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

}
