package dbClasslar;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import DatabaseFonksiyonlar.DBConnect;
import globalDegiskenler.DiziDegiskenler;

public class kitap_odunc {
	private int ko_id;
	private int k_id;
	private int u_id;
	private java.sql.Date ko_tarih;
	private java.sql.Date ki_tarih;
	private String silindimi;

	public int getU_id() {
		return u_id;
	}

	public void setU_id(int u_id) {
		this.u_id = u_id;
	}

	public java.sql.Date getKo_tarih() {
		return ko_tarih;
	}

	public void setKo_tarih(java.sql.Date ko_tarih) {
		this.ko_tarih = ko_tarih;
	}

	public java.sql.Date getKi_tarih() {
		return ki_tarih;
	}

	public void setKi_tarih(java.sql.Date ki_tarih) {
		this.ki_tarih = ki_tarih;
	}

	public String getSilindimi() {
		return silindimi;
	}

	public void setSilindimi(String silindimi) {
		this.silindimi = silindimi;
	}

	public int getKo_id() {
		return ko_id;
	}

	public void setKo_id(int ko_id) {
		this.ko_id = ko_id;
	}

	public int getK_id() {
		return k_id;
	}

	public void setK_id(int k_id) {
		this.k_id = k_id;
	}

	public kitap_odunc() {

	}

	/**
	 * @param ko_id
	 * @param k_id
	 * @param u_id
	 * @param d_id
	 * @param ko_tarih
	 * @param ki_tarih
	 * @param silindimi
	 */
	public kitap_odunc(int ko_id, int k_id, int u_id, java.sql.Date ko_tarih, java.sql.Date ki_tarih,
			String silindimi) {
		this.ko_id = ko_id;
		this.k_id = k_id;
		this.u_id = u_id;
		this.ko_tarih = ko_tarih;
		this.ki_tarih = ki_tarih;
		this.silindimi = silindimi;
	}

	public Object[] kitap_oduncGetModel() {

		dbClasslar.kitap kitap = DiziDegiskenler.KitapListMap.get(this.k_id);
		dbClasslar.yazar yazar = DiziDegiskenler.YazarListMap.get(kitap.getY_id());

		Object[] Object = { this.ko_id, kitap.getK_ad(), yazar.getY_ad() };
		return Object;
	}
	
	public kitap_odunc kitap_oduncNewVerisiGetir() {

		return new kitap_odunc(this.ko_id, this.k_id, this.u_id, this.ko_tarih, this.ki_tarih,this.silindimi);
	}

	public boolean kitap_oduncDBEkle() {

		DBConnect.Olustur();

		try {
			String sql = "INSERT INTO `kitap_odunc` (`k_id`, `u_id`, `ko_tarih`, `ki_tarih`, `silindimi`) VALUES (?,?,?,?,'h')";
			PreparedStatement statement = DBConnect._Connection.prepareStatement(sql);
			statement.setInt(1, k_id);
			statement.setInt(2, u_id);
			statement.setDate(3, ko_tarih);
			statement.setDate(4, ki_tarih);
			statement.executeUpdate();
			return true;

		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}

	public static boolean kitap_oduncListeUyeOzel(int id) {
		try {
			DiziDegiskenler.KitapOduncMapUyeOzel = new HashMap<Integer, dbClasslar.kitap_odunc>();

			DBConnect.Olustur();
			String sql = "SELECT kitap_odunc.*  FROM kitap_odunc INNER JOIN uyeler ON uyeler.u_id = kitap_odunc.u_id WHERE kitap_odunc.silindimi = 'h' AND uyeler.u_id = ?";
			PreparedStatement statement = DBConnect._Connection.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				DiziDegiskenler.KitapOduncMapUyeOzel.put(rs.getInt("ko_id"),
						new kitap_odunc(rs.getInt("ko_id"), rs.getInt("k_id"), rs.getInt("u_id"),
								rs.getDate("ko_tarih"), rs.getDate("ki_tarih"), rs.getString("silindimi")));
			}
			return true;
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

	public boolean kitap_oduncDBSil() {

		DBConnect.Olustur();
		try {
			String sql = "UPDATE `kitap_odunc` SET `silindimi` = 'e' WHERE `ko_id` = ?";
			PreparedStatement statement = DBConnect._Connection.prepareStatement(sql);
			statement.setInt(1, this.ko_id);
			statement.executeUpdate();
			return true;

		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}
}
