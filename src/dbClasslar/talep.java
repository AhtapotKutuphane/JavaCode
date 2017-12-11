package dbClasslar;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

import DatabaseFonksiyonlar.DBConnect;
import globalDegiskenler.DiziDegiskenler;

public class talep {
	private int kt_id, k_id, u_id, d_id,kt_miktar;
	java.sql.Date kt_tarih;

	
	
	public int getKt_miktar() {
		return kt_miktar;
	}

	public void setKt_miktar(int kt_miktar) {
		this.kt_miktar = kt_miktar;
	}

	public int getKt_id() {
		return kt_id;
	}

	public void setKt_id(int kt_id) {
		this.kt_id = kt_id;
	}

	public int getK_id() {
		return k_id;
	}

	public void setK_id(int k_id) {
		this.k_id = k_id;
	}

	public int getU_id() {
		return u_id;
	}

	public void setU_id(int u_id) {
		this.u_id = u_id;
	}

	public int getD_id() {
		return d_id;
	}

	public void setD_id(int d_id) {
		this.d_id = d_id;
	}

	public java.sql.Date getKt_tarih() {
		return kt_tarih;
	}

	public void setKt_tarih(java.sql.Date kt_tarih) {
		this.kt_tarih = kt_tarih;
	}

	public talep(int kt_id, int k_id, int u_id, java.sql.Date kt_tarih, int d_id , int kt_miktar) {
		this.kt_id = kt_id;
		this.k_id = k_id;
		this.u_id = u_id;
		this.d_id = d_id;
		this.kt_tarih = kt_tarih;
		this.kt_miktar = kt_miktar;
	}

	public talep() {
		// TODO Auto-generated constructor stub
	}

	public Object[] talepGetModel() {

		dbClasslar.kitap kitap = DiziDegiskenler.KitapListMap.get(this.k_id);
		dbClasslar.durum durum = DiziDegiskenler.DurumListMap.get(this.d_id);
		dbClasslar.uyeler uyeler = DiziDegiskenler.UyeListMap.get(this.u_id);
		
		
		Object[] Object = { this.kt_id, kitap.getK_ad(), uyeler.toString(), this.kt_tarih,this.kt_miktar, durum.getD_ad() };
		return Object;
	}

	public talep talepNewVerisiGetir() {

		return new talep(this.kt_id, this.k_id, this.u_id, this.kt_tarih, this.d_id,this.kt_miktar);
	}

	public boolean talepDBGüncelle(int kt_id, int k_id, int u_id, java.sql.Date kt_tarih, int d_id , int kt_miktar) {

		DBConnect.Olustur();

		try {
			String sql = "UPDATE `kitap_talep` SET `k_id` = ?, `u_id` = ?, `kt_tarih` = ?, `d_id` = ?  , kt_miktar = ? WHERE `kt_id` = ?";
			PreparedStatement statement = DBConnect._Connection.prepareStatement(sql);
			statement.setInt(1, k_id);
			statement.setInt(2, u_id);
			statement.setDate(3, kt_tarih);
			statement.setInt(4, d_id);
			statement.setInt(5, kt_miktar);
			statement.setInt(6, kt_id);
			statement.executeUpdate();
			return true;

		} catch (Exception e) {
			System.out.println(e);
			return false;
		}

	}

	public boolean talepDBGüncelle() {

		DBConnect.Olustur();

		try {

			String sql = "UPDATE `kitap_talep` SET `k_id` = ?, `u_id` = ?, `kt_tarih` = ?, `d_id` = ? , kt_miktar = ? WHERE `kt_id` = ?";
			PreparedStatement statement = DBConnect._Connection.prepareStatement(sql);
			statement.setInt(1, this.k_id);
			statement.setInt(2, this.u_id);
			statement.setDate(3, this.kt_tarih);
			statement.setInt(4, this.d_id);
			statement.setInt(5, this.kt_miktar);
			statement.setInt(6, this.kt_id);
			statement.executeUpdate();
			return true;

		} catch (Exception e) {
			System.out.println(e);
			return false;
		}

	}

	public boolean talepDBEkle() {

		DBConnect.Olustur();

		try {
			String sql = "INSERT INTO `kitap_talep` (`k_id`, `u_id`, `kt_tarih`, `d_id`,`kt_id` , kt_miktar) VALUES (?,?,?,?,?,?)";
			PreparedStatement statement = DBConnect._Connection.prepareStatement(sql);
			statement.setInt(1, this.k_id);
			statement.setInt(2, this.u_id);
			statement.setDate(3, this.kt_tarih);
			statement.setInt(4, this.d_id);
			statement.setInt(5, this.kt_id);
			statement.setInt(6, this.kt_miktar);
			statement.executeUpdate();
			return true;

		} catch (Exception e) {
			System.out.println(e);
			return false;
		}

	}

	public boolean talepDBSil() {
		try {
			DBConnect.Olustur();

			String sql = "DELETE FROM `kitap_talep` WHERE `kt_id` = ?";
			PreparedStatement statement = DBConnect._Connection.prepareStatement(sql);
			statement.setInt(1, this.kt_id);
			statement.executeUpdate();
			return true;

		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}

	public boolean talepDBSil(int t_id) {
		try {
			DBConnect.Olustur();

			String sql = "DELETE FROM `kitap_talep` WHERE `kt_id` = ?";
			PreparedStatement statement = DBConnect._Connection.prepareStatement(sql);
			statement.setInt(1, t_id);
			statement.executeUpdate();
			return true;

		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}

	public static boolean talepDBList() {
		try {
			DBConnect.Olustur();

			DiziDegiskenler.TalepListMap = new HashMap<Integer, talep>();
			
			String sql = "SELECT * FROM `kitap_talep`  ORDER BY  kt_id ASC";
			PreparedStatement statement = DBConnect._Connection.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				DiziDegiskenler.TalepListMap.put(rs.getInt("kt_id"), new dbClasslar.talep(rs.getInt("kt_id"),
						rs.getInt("k_id"), rs.getInt("u_id"), rs.getDate("kt_tarih"), rs.getInt("d_id"),rs.getInt("kt_miktar")));
			}
			return true;

		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}

	public static talep talepDBList(int t_id) {
		try {
			DBConnect.Olustur();

			DiziDegiskenler.TalepListMap = new HashMap<Integer, talep>();
			
			String sql = "SELECT * FROM `kitap_talep` where kt_id = ?  ORDER BY  kt_id ASC";
			PreparedStatement statement = DBConnect._Connection.prepareStatement(sql);
			statement.setInt(1, t_id);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				return new dbClasslar.talep(rs.getInt("kt_id"), rs.getInt("k_id"), rs.getInt("u_id"),
						rs.getDate("kt_tarih"), rs.getInt("d_id"),rs.getInt("kt_miktar"));
			}
			return null;

		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
}
