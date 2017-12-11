package DatabaseFonksiyonlar;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class IdAl {

	/**
	 * Kitap En son id Hangisi ise onu alýr.
	 * 
	 * @return -1 olursa hata oldu , hata olmaz ise dönmesi gereken id döner.
	 */
	public static int KitapIdAl() {

		try {
			DBConnect.Olustur();
			String sql = "SELECT k_id FROM kitap ORDER BY k_id DESC LIMIT 1";
			PreparedStatement statement = DBConnect._Connection.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			int id = 0;
			while (rs.next()) {
				id = Integer.parseInt(rs.getString("k_id"));
			}
			return id;

		} catch (Exception e) {
			System.out.println(e);
			return -1;
		}
	}

	/**
	 * Üye En son id Hangisi ise onu alýr.
	 * 
	 * @return -1 olursa hata oldu , hata olmaz ise dönmesi gereken id döner.
	 */
	public static int UyeIdAl() {

		try {
			DBConnect.Olustur();
			String sql = "SELECT u_id FROM uyeler ORDER BY u_id DESC LIMIT 1";
			PreparedStatement statement = DBConnect._Connection.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			int id = 0;
			while (rs.next()) {
				id = Integer.parseInt(rs.getString("u_id"));
			}
			return id;

		} catch (Exception e) {
			System.out.println(e);
			return -1;
		}
	}

	/**
	 * Personel En son id Hangisi ise onu alýr.
	 * 
	 * @return -1 olursa hata oldu , hata olmaz ise dönmesi gereken id döner.
	 */
	public static int PersonelIdAl() {

		try {
			DBConnect.Olustur();
			String sql = "SELECT p_id FROM personel ORDER BY p_id DESC LIMIT 1";
			PreparedStatement statement = DBConnect._Connection.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			int id = 0;
			while (rs.next()) {
				id = Integer.parseInt(rs.getString("p_id"));
			}
			return id;

		} catch (Exception e) {
			System.out.println(e);
			return -1;
		}
	}

	/**
	 * üye Tipi En son id Hangisi ise onu alýr.
	 * 
	 * @return -1 olursa hata oldu , hata olmaz ise dönmesi gereken id döner.
	 */
	public static int UyeTipiIdAl() {

		try {
			DBConnect.Olustur();
			String sql = "SELECT ut_id FROM uye_tipi ORDER BY ut_id DESC LIMIT 1";
			PreparedStatement statement = DBConnect._Connection.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			int id = 0;
			while (rs.next()) {
				id = Integer.parseInt(rs.getString("ut_id"));
			}
			return id;

		} catch (Exception e) {
			System.out.println(e);
			return -1;
		}
	}

	/**
	 * Tedarikci En son id Hangisi ise onu alýr.
	 * 
	 * @return -1 olursa hata oldu , hata olmaz ise dönmesi gereken id döner.
	 */
	public static int TedarikciIdAl() {

		try {
			DBConnect.Olustur();
			String sql = "SELECT t_id FROM tedarikci ORDER BY t_id DESC LIMIT 1";
			PreparedStatement statement = DBConnect._Connection.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			int id = 0;
			while (rs.next()) {
				id = Integer.parseInt(rs.getString("t_id"));
			}
			return id;

		} catch (Exception e) {
			System.out.println(e);
			return -1;
		}
	}

	/**
	 * Tedarikci En son id Hangisi ise onu alýr.
	 * 
	 * @return -1 olursa hata oldu , hata olmaz ise dönmesi gereken id döner.
	 */
	public static int KurallarIdAl() {

		try {
			DBConnect.Olustur();
			String sql = "SELECT kur_id FROM kurallar ORDER BY kur_id DESC LIMIT 1";
			PreparedStatement statement = DBConnect._Connection.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			int id = 0;
			while (rs.next()) {
				id = Integer.parseInt(rs.getString("kur_id"));
			}
			return id;

		} catch (Exception e) {
			System.out.println(e);
			return -1;
		}
	}

	public static int TalepIdAl() {

		try {
			DBConnect.Olustur();
			String sql = "SELECT kt_id FROM kitap_talep ORDER BY kt_id DESC LIMIT 1";
			PreparedStatement statement = DBConnect._Connection.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			int id = 0;
			while (rs.next()) {
				id = Integer.parseInt(rs.getString("kt_id"));
			}
			return id;

		} catch (Exception e) {
			System.out.println(e);
			return -1;
		}
	}

	public static int SiparisIdAl() {

		try {
			DBConnect.Olustur();
			String sql = "SELECT s_id FROM siparis ORDER BY s_id DESC LIMIT 1";
			PreparedStatement statement = DBConnect._Connection.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			int id = 0;
			while (rs.next()) {
				id = Integer.parseInt(rs.getString("s_id"));
			}
			return id;

		} catch (Exception e) {
			System.out.println(e);
			return -1;
		}
	}

	public static int YazarIdAl() {

		try {
			DBConnect.Olustur();
			String sql = "SELECT y_id FROM yazar ORDER BY y_id DESC LIMIT 1";
			PreparedStatement statement = DBConnect._Connection.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			int id = 0;
			while (rs.next()) {
				id = Integer.parseInt(rs.getString("y_id"));
			}
			return id;

		} catch (Exception e) {
			System.out.println(e);
			return -1;
		}
	}

	public static int KategoriIdAl() {

		try {
			DBConnect.Olustur();
			String sql = "SELECT kat_id FROM kategori ORDER BY kat_id DESC LIMIT 1";
			PreparedStatement statement = DBConnect._Connection.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			int id = 0;
			while (rs.next()) {
				id = Integer.parseInt(rs.getString("kat_id"));
			}
			return id;

		} catch (Exception e) {
			System.out.println(e);
			return -1;
		}
	}
	
	public static int KitapOduncIdAl() {

		try {
			DBConnect.Olustur();
			String sql = "SELECT ko_id FROM kitap_odunc ORDER BY ko_id DESC LIMIT 1";
			PreparedStatement statement = DBConnect._Connection.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			int id = 0;
			while (rs.next()) {
				id = Integer.parseInt(rs.getString("ko_id"));
			}
			return id;

		} catch (Exception e) {
			System.out.println(e);
			return -1;
		}
	}
}
