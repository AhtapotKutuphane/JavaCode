package dbClasslar;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import DatabaseFonksiyonlar.DBConnect;
import globalDegiskenler.DiziDegiskenler;
import globalFonksiyonlar.Fonksiyonlar;

public class uyetipi {

	private int ut_id, ut_kitap_limit, ut_gun_limit;
	private String ut_ad = "";
	private String silindimi = "h";

	
	
	public String getSilindimi() {
		return silindimi;
	}

	public void setSilindimi(String silindimi) {
		this.silindimi = silindimi;
	}

	public int getUt_id() {
		return ut_id;
	}

	public void setUt_id(int ut_id) {
		this.ut_id = ut_id;
	}

	public int getUt_kitap_limit() {
		return ut_kitap_limit;
	}

	public void setUt_kitap_limit(int ut_kitap_limit) {
		this.ut_kitap_limit = ut_kitap_limit;
	}

	public int getUt_gun_limit() {
		return ut_gun_limit;
	}

	public void setUt_gun_limit(int ut_gun_limit) {
		this.ut_gun_limit = ut_gun_limit;
	}

	public String getUt_ad() {
		return ut_ad;
	}

	public void setUt_ad(String ut_ad) {
		this.ut_ad = ut_ad;
	}

	@Override
	public String toString() {
		return this.ut_ad;
		// return super.toString();
	}

	public uyetipi() {
	}

	// Üyetipi çekmek için düzeltin
	public uyetipi(int ut_id, String ut_ad) {
		this.ut_id = ut_id;
		this.ut_ad = ut_ad;
	}

	public uyetipi(int ut_id, String ut_ad, int ut_kitap_limit, int ut_gun_limit, String silindimi) {

		this.ut_id = ut_id;
		this.ut_ad = ut_ad;
		this.ut_kitap_limit = ut_kitap_limit;
		this.ut_gun_limit = ut_gun_limit;
		this.silindimi = silindimi;

	}

	public Object[] uyetipiGetModel() {

		Object[] Object = { this.ut_id, this.ut_ad, this.ut_kitap_limit, this.ut_gun_limit };

		return Object;
	}

	public uyetipi uyetipiArrayListNewVerisiGetir() {

		return new uyetipi(this.ut_id, this.ut_ad, this.ut_kitap_limit, this.ut_gun_limit,this.silindimi);
	}

	public boolean uyetipiDBGüncelle(String ut_ad, int ut_kitap_limit, int ut_gun_limit, int ut_id) {

		DBConnect.Olustur();

		try {

			String sql = "UPDATE `uye_tipi` SET `ut_ad` = ?, `ut_kitap_limit` = ?, `ut_gun_limit` = ? WHERE `ut_id` = ?";
			PreparedStatement statement = DBConnect._Connection.prepareStatement(sql);
			statement.setString(1, ut_ad);
			statement.setInt(2, ut_kitap_limit);
			statement.setInt(3, ut_gun_limit);
			statement.setInt(4, ut_id);
			statement.executeUpdate();
			return true;

		} catch (Exception e) {
			System.out.println(e);
			return false;
		}

	}

	public boolean uyetipiDBGüncelle() {

		DBConnect.Olustur();

		try {

			String sql = "UPDATE `uye_tipi` SET `ut_ad` = ?, `ut_kitap_limit` = ?, `ut_gun_limit` = ? WHERE `ut_id` = ?";
			PreparedStatement statement = DBConnect._Connection.prepareStatement(sql);
			statement.setString(1, ut_ad);
			statement.setInt(2, ut_kitap_limit);
			statement.setInt(3, ut_gun_limit);
			statement.setInt(4, ut_id);
			statement.executeUpdate();
			return true;

		} catch (Exception e) {
			System.out.println(e);
			return false;
		}

	}

	public boolean uyetipiDBEkle() {

		DBConnect.Olustur();

		try {
			String sql = "INSERT INTO `uye_tipi` (`ut_ad`, `ut_kitap_limit`, `ut_gun_limit` , ut_id) VALUES (?,?, ?,?)";
			PreparedStatement statement = DBConnect._Connection.prepareStatement(sql);
			statement.setString(1, ut_ad);
			statement.setInt(2, ut_kitap_limit);
			statement.setInt(3, ut_gun_limit);
			statement.setInt(4, ut_id);
			statement.executeUpdate();
			return true;

		} catch (Exception e) {
			System.out.println(e);
			return false;
		}

	}

	public boolean uyetipiDBSil() {
		try {
			DBConnect.Olustur();

			String sql = "DELETE FROM `uye_tipi` WHERE `ut_id` = ?";
			PreparedStatement statement = DBConnect._Connection.prepareStatement(sql);
			statement.setInt(1, this.ut_id);
			statement.executeUpdate();
			return true;

		} catch (Exception e) {
			System.out.println(e);
			return false;
		}

	}

	public static boolean uyetipiDBList() {
		try {
			DBConnect.Olustur();
			
			DiziDegiskenler.UyeTipiListMap = new HashMap<Integer, uyetipi>();

			String sql = "SELECT * FROM `uye_tipi`  ORDER BY  ut_id ASC";
			PreparedStatement statement = DBConnect._Connection.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				DiziDegiskenler.UyeTipiListMap.put(Integer.parseInt(rs.getString("ut_id")),
						new dbClasslar.uyetipi(Integer.parseInt(rs.getString("ut_id")), rs.getString("ut_ad"),
								Integer.parseInt(rs.getString("ut_kitap_limit")),
								Integer.parseInt(rs.getString("ut_gun_limit")),rs.getString("silindimi")));
			}
			return true;

		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}

	public static uyetipi uyetipiDBList(int ut_id) {

		DBConnect.Olustur();
		
		DiziDegiskenler.UyeTipiListMap = new HashMap<Integer, uyetipi>();
		
		try {
			String sql = "SELECT * FROM `uye_tipi` where ut_id = ? ORDER BY  ut_id ASC";
			PreparedStatement statement = DBConnect._Connection.prepareStatement(sql);
			statement.setInt(1, ut_id);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				return new dbClasslar.uyetipi(Integer.parseInt(rs.getString("ut_id")), rs.getString("ut_ad"),
								Integer.parseInt(rs.getString("ut_kitap_limit")),
								Integer.parseInt(rs.getString("ut_gun_limit")),rs.getString("silindimi"));
			}
			return null;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

}
