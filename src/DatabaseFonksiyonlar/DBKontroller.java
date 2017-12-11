package DatabaseFonksiyonlar;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import globalFonksiyonlar.Fonksiyonlar;

public class DBKontroller {

	
	// GERÝ DÖNÜCEM BURAYA ......
	// Kullanýlmýyor..
	/**
	 * 
	 * @param k_ad
	 * @param y_id
	 * @return false döner ise aynýsýndan vardýr , true ise yoktur.
	 */
	public static boolean KitapAdýveYazarAdýAynýVarMý(String k_ad, int y_id,int k_id) {
		boolean bool = false;
		try {
			DBConnect.Olustur();
			String sql = "SELECT k_id , Count(*) as sayi FROM `kitap` WHERE k_ad = ? AND y_id = ?";
			PreparedStatement statement = DBConnect._Connection.prepareStatement(sql);
			statement.setString(1, k_ad);
			statement.setInt(2, y_id);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				if (rs.getInt("sayi") > 0) {
					
				}
				else {
					bool = !bool;
				}
			}

		} catch (Exception e) {
			System.out.println(e);
			bool = !bool;
		}
		return bool;
	}
	
	/**
	 * 
	 * @param id
	 * @return -1 Dönerse hatalý.. Diðer deðerler doðru deðerdir.
	 */
	public static int KacKitapVarBuKategoride(int id) {
		int toplam = 0;
		try {
			DBConnect.Olustur();
			String sql = "SELECT DISTINCT kitap.k_id , kitap.k_depo , kitap.k_raf , kitap.k_odunc  FROM `kategori` INNER JOIN kitap ON kitap.kat_id = ? WHERE kategori.silindimi = 'h' AND kitap.silindimi = 'h'";
			PreparedStatement statement = DBConnect._Connection.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				toplam += Fonksiyonlar.Toplam(rs.getInt("kitap.k_depo"), rs.getInt("kitap.k_raf"), rs.getInt("kitap.k_odunc"));
			}
		} catch (Exception e) {
			System.out.println(e);
			return -1;
		}
		return toplam;
	}
	
	/**
	 * 
	 * @param id
	 * @return -1 Dönerse hatalý.. Diðer deðerler doðru deðerdir.
	 */
	public static int KacKitapVarBuYazarda(int id) {
		int toplam = 0;
		try {
			DBConnect.Olustur();
			String sql = "SELECT kitap.k_depo , kitap.k_raf , kitap.k_odunc FROM `yazar` INNER JOIN kitap ON kitap.y_id = yazar.y_id WHERE yazar.silindimi = 'h' AND kitap.silindimi = 'h' AND yazar.y_id = ?";
			PreparedStatement statement = DBConnect._Connection.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				toplam += Fonksiyonlar.Toplam(rs.getInt("kitap.k_depo"), rs.getInt("kitap.k_raf"), rs.getInt("kitap.k_odunc"));
			}
		} catch (Exception e) {
			System.out.println(e);
			return -1;
		}
		return toplam;
	}
	
	
}
