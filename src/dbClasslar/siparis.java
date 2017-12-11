package dbClasslar;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

import DatabaseFonksiyonlar.DBConnect;
import globalDegiskenler.DiziDegiskenler;

public class siparis {

	private int s_id, t_id, k_id, s_miktar, d_id;
	private java.sql.Date s_tarih;

	public int getS_id() {
		return s_id;
	}

	public void setS_id(int s_id) {
		this.s_id = s_id;
	}

	public int getT_id() {
		return t_id;
	}

	public void setT_id(int t_id) {
		this.t_id = t_id;
	}

	public int getK_id() {
		return k_id;
	}

	public void setK_id(int k_id) {
		this.k_id = k_id;
	}

	public int getS_miktar() {
		return s_miktar;
	}

	public void setS_miktar(int s_miktar) {
		this.s_miktar = s_miktar;
	}

	public int getD_id() {
		return d_id;
	}

	public void setD_id(int d_id) {
		this.d_id = d_id;
	}

	public java.sql.Date getS_tarih() {
		return s_tarih;
	}

	public void setS_tarih(java.sql.Date s_tarih) {
		this.s_tarih = s_tarih;
	}

	public siparis() {
		// TODO Auto-generated constructor stub
	}

	public siparis(int s_id, int t_id, int k_id, int s_miktar, int d_id, java.sql.Date s_tarih) {
		this.s_tarih = s_tarih;
		this.s_id = s_id;
		this.t_id = t_id;
		this.k_id = k_id;
		this.d_id = d_id;
		this.s_miktar = s_miktar;
	}

	public Object[] siparisGetModel() {

		dbClasslar.kitap kitap = DiziDegiskenler.KitapListMap.get(this.k_id);
		dbClasslar.durum durum = DiziDegiskenler.DurumListMap.get(this.d_id);
		dbClasslar.tedarikci tedarikci = DiziDegiskenler.TedarikciListMap.get(this.t_id);

		Object[] Object = { this.s_id, tedarikci.getT_ad(), kitap.getK_ad(), this.s_miktar, durum.getD_ad(), this.s_tarih };
		return Object;
	}

	public siparis siparisNewVerisiGetir() {

		return new siparis(this.s_id, this.t_id, this.k_id, this.s_miktar, this.d_id, this.s_tarih);
	}

	public boolean siparisDBGüncelle(int s_id, int t_id, int k_id, int s_miktar, int d_id, java.sql.Date s_tarih) {

		DBConnect.Olustur();

		try {

			String sql = "UPDATE `siparis` SET `t_id` = ?, `k_id` = ?, `s_tarih` = ?, `s_miktar` = ? , `d_id` = ? WHERE `s_id` = ?";
			PreparedStatement statement = DBConnect._Connection.prepareStatement(sql);
			statement.setInt(1, t_id);
			statement.setInt(2, k_id);
			statement.setDate(3, s_tarih);
			statement.setInt(4, s_miktar);
			statement.setInt(5, d_id);
			statement.setInt(6, s_id);
			statement.executeUpdate();
			return true;

		} catch (Exception e) {
			System.out.println(e);
			return false;
		}

	}

	public boolean siparisDBGüncelle() {

		DBConnect.Olustur();

		try {

			String sql = "UPDATE `siparis` SET `t_id` = ?, `k_id` = ?, `s_tarih` = ?, `s_miktar` = ? , `d_id` = ? WHERE `s_id` = ?";
			PreparedStatement statement = DBConnect._Connection.prepareStatement(sql);
			statement.setInt(1, t_id);
			statement.setInt(2, k_id);
			statement.setDate(3, s_tarih);
			statement.setInt(4, s_miktar);
			statement.setInt(5, d_id);
			statement.setInt(6, s_id);
			statement.executeUpdate();
			return true;

		} catch (Exception e) {
			System.out.println(e);
			return false;
		}

	}

	public boolean siparisDBEkle() {

		DBConnect.Olustur();

		try {
			String sql = "INSERT INTO `siparis` (`t_id`, `k_id`, `s_tarih`, `s_miktar`,`d_id`) VALUES (?,?,?,?,?)";
			PreparedStatement statement = DBConnect._Connection.prepareStatement(sql);
			statement.setInt(1, t_id);
			statement.setInt(2, k_id);
			statement.setDate(3, s_tarih);
			statement.setInt(4, s_miktar);
			statement.setInt(5, d_id);
			statement.executeUpdate();
			return true;

		} catch (Exception e) {
			System.out.println(e);
			return false;
		}

	}

	public boolean siparisDBSil() {
		try {
			DBConnect.Olustur();

			String sql = "DELETE FROM `siparis` WHERE `s_id` = ?";
			PreparedStatement statement = DBConnect._Connection.prepareStatement(sql);
			statement.setInt(1, this.s_id);
			statement.executeUpdate();
			return true;

		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}

	public boolean siparisDBSil(int s_id) {
		try {
			DBConnect.Olustur();

			String sql = "DELETE FROM `siparis` WHERE `s_id` = ?";
			PreparedStatement statement = DBConnect._Connection.prepareStatement(sql);
			statement.setInt(1, s_id);
			statement.executeUpdate();
			return true;

		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}

	public static boolean siparisDBList() {
		try {
			DBConnect.Olustur();

			DiziDegiskenler.SiparisListMap = new HashMap<Integer, siparis>();

			String sql = "SELECT * FROM `siparis`  ORDER BY  s_id ASC";
			PreparedStatement statement = DBConnect._Connection.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				DiziDegiskenler.SiparisListMap.put(rs.getInt("s_id"),
						new dbClasslar.siparis(rs.getInt("s_id"), rs.getInt("t_id"), rs.getInt("k_id"),
								rs.getInt("s_miktar"), rs.getInt("d_id"), rs.getDate("s_tarih")));
			}
			return true;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}

	public static siparis siparisDBList(int t_id) {
		try {
			DBConnect.Olustur();

			DiziDegiskenler.SiparisListMap = new HashMap<Integer, siparis>();

			String sql = "SELECT * FROM `siparis` where s_id = ?  ORDER BY  s_id ASC";
			PreparedStatement statement = DBConnect._Connection.prepareStatement(sql);
			statement.setInt(1, t_id);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				return new dbClasslar.siparis(rs.getInt("s_id"), rs.getInt("t_id"), rs.getInt("k_id"),
						rs.getInt("s_miktar"), rs.getInt("d_id"), rs.getDate("s_tarih"));

			}
			return null;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

}
