package dbClasslar;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.sql.PreparedStatement;

import DatabaseFonksiyonlar.DBConnect;
import globalDegiskenler.DiziDegiskenler;

public class kategori {
	private int kat_id;
	private String kat_ad = "";
	private String silindimi = "h";
	private int toplam = 0;

	public int getToplam() {
		return toplam;
	}

	public void setToplam(int toplam) {
		this.toplam = toplam;
	}

	public String getSilindimi() {
		return silindimi;
	}

	public void setSilindimi(String silindimi) {
		this.silindimi = silindimi;
	}

	public int getKat_id() {
		return kat_id;
	}

	public void setKat_id(int kat_id) {
		this.kat_id = kat_id;
	}

	public String getKat_ad() {
		return kat_ad;
	}

	public void setKat_ad(String kat_ad) {
		this.kat_ad = kat_ad;
	}

	@Override
	public String toString() {

		return this.kat_ad;
	}

	public kategori() {
	}

	public kategori(int kat_id, String kat_ad, String silindimi) {
		this.kat_id = kat_id;
		this.kat_ad = kat_ad;
		this.silindimi = silindimi;
	}
	
	public kategori(int kat_id, String kat_ad) {
		this.kat_id = kat_id;
		this.kat_ad = kat_ad;
	}

	public static boolean kategoriDBList() {

		DBConnect.Olustur();

		DiziDegiskenler.KategoriListMap = new HashMap<Integer, kategori>();

		try {
			String sql = "SELECT * FROM `kategori`  ORDER BY  kat_id ASC";
			PreparedStatement statement = DBConnect._Connection.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				DiziDegiskenler.KategoriListMap.put(rs.getInt("kat_id"),
						new kategori(rs.getInt("kat_id"), rs.getString("kat_ad"), rs.getString("silindimi")));
			}
			return true;

		} catch (Exception e) {
			System.out.println(e);
			return false;
		}

	}
	
	public Object[] kategoriGetModel() {

		Object[] Object = { this.kat_id, this.kat_ad,this.toplam};

		return Object;
	}

	public kategori kategoriNewVerisiGetir() {

		return new kategori(this.kat_id, this.kat_ad, this.silindimi);
	}

	public boolean kategoriDBGüncelle(String y_ad, String silindimi, int kat_id) {

		DBConnect.Olustur();

		try {

			String sql = "UPDATE `kategori` SET `kat_ad` = ?, `silindimi` = ? WHERE `kat_id` = ?";
			PreparedStatement statement = DBConnect._Connection.prepareStatement(sql);
			statement.setString(1, y_ad);
			statement.setString(2, silindimi);
			statement.setInt(3, kat_id);
			statement.executeUpdate();
			return true;

		} catch (Exception e) {
			System.out.println(e);
			return false;
		}

	}

	public boolean kategoriDBGüncelle() {

		DBConnect.Olustur();

		try {

			String sql = "UPDATE `kategori` SET `kat_ad` = ?, `silindimi` = ? WHERE `kat_id` = ?";
			PreparedStatement statement = DBConnect._Connection.prepareStatement(sql);
			statement.setString(1, kat_ad);
			statement.setString(2, silindimi);
			statement.setInt(3, kat_id);
			statement.executeUpdate();
			return true;

		} catch (Exception e) {
			System.out.println(e);
			return false;
		}

	}

	public boolean kategoriDBEkle() {

		DBConnect.Olustur();

		try {
			String sql = "INSERT INTO `kategori` (`kat_ad`, `silindimi`) VALUES (?,?)";
			PreparedStatement statement = DBConnect._Connection.prepareStatement(sql);
			statement.setString(1, kat_ad);
			statement.setString(2, silindimi);
			statement.executeUpdate();
			return true;

		} catch (Exception e) {
			System.out.println(e);
			return false;
		}

	}

	public boolean kategoriDBSil() {
		
		try {
			String sql = "UPDATE `kategori` SET `silindimi` = ? WHERE `kat_id` = ?";
			PreparedStatement statement = DBConnect._Connection.prepareStatement(sql);
			statement.setString(1, "e");
			statement.setInt(2, kat_id);
			statement.executeUpdate();
			return true;

		} catch (Exception e) {
			System.out.println(e);
			return false;
		}

	}


}
