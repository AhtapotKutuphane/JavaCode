package dbClasslar;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import DatabaseFonksiyonlar.DBConnect;
import globalDegiskenler.DiziDegiskenler;

public class kurallar {

	private int kur_id;
	private String kur_ad , kur_icerik;
	public int getKur_id() {
		return kur_id;
	}
	public void setKur_id(int kur_id) {
		this.kur_id = kur_id;
	}
	public String getKur_ad() {
		return kur_ad;
	}
	public void setKur_ad(String kur_ad) {
		this.kur_ad = kur_ad;
	}
	public String getKur_icerik() {
		return kur_icerik;
	}
	public void setKur_icerik(String kur_icerik) {
		this.kur_icerik = kur_icerik;
	}
	
	public kurallar() {
		// TODO Auto-generated constructor stub
	}
	
	public kurallar(int kur_id , String kur_ad , String kur_icerik) {
		this.kur_id = kur_id; 
		this.kur_ad = kur_ad;
		this.kur_icerik = kur_icerik;
	}
	
	public Object[] kurallarGetModel() {

		Object[] Object = {this.kur_id, this.kur_ad, this.kur_icerik};
		return Object;
	}
	
	public kurallar kurallarNewVerisiGetir() {

		return new kurallar(this.kur_id, this.kur_ad, this.kur_icerik);
	}
	
	public static boolean kurallarDBList() {
		try {
			DBConnect.Olustur();

			DiziDegiskenler.KurallarListMap = new HashMap<Integer, kurallar>();
			
			String sql = "SELECT * FROM `kurallar`  ORDER BY  kur_id ASC";
			PreparedStatement statement = DBConnect._Connection.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				DiziDegiskenler.KurallarListMap.put(rs.getInt("kur_id"),
						new dbClasslar.kurallar(rs.getInt("kur_id"), rs.getString("kur_ad"), rs.getString("kur_icerik")));
			}
			return true;

		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}

	public static kurallar kurallarDBList(int kur_id) {
		try {
			DBConnect.Olustur();
			
			DiziDegiskenler.KurallarListMap = new HashMap<Integer, kurallar>();

			String sql = "SELECT * FROM `kurallar` where kur_id = ?  ORDER BY  kur_id ASC";
			PreparedStatement statement = DBConnect._Connection.prepareStatement(sql);
			statement.setInt(1, kur_id);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				return new dbClasslar.kurallar(rs.getInt("kur_id"), rs.getString("kur_ad"), rs.getString("kur_icerik"));
			}
			return null;

		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	public boolean kurallarDBGüncelle(int kur_id, String kur_ad, String kur_icerik) {

		DBConnect.Olustur();

		try {

			String sql = "UPDATE `kurallar` SET `kur_ad` = ?, `kur_icerik` = ? WHERE `kur_id` = ?";
			PreparedStatement statement = DBConnect._Connection.prepareStatement(sql);
			statement.setString(1, kur_ad);
			statement.setString(2, kur_icerik);
			statement.setInt(3, kur_id);
			statement.executeUpdate();
			return true;

		} catch (Exception e) {
			System.out.println(e);
			return false;
		}

	}

	public boolean kurallarDBGüncelle() {
		DBConnect.Olustur();
		try {

			String sql = "UPDATE `kurallar` SET `kur_ad` = ?, `kur_icerik` = ? WHERE `kur_id` = ?";
			PreparedStatement statement = DBConnect._Connection.prepareStatement(sql);
			statement.setString(1, this.kur_ad);
			statement.setString(2, this.kur_icerik);
			statement.setInt(3, this.kur_id);
			statement.executeUpdate();
			return true;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}

	public boolean kurallarDBEkle() {

		DBConnect.Olustur();

		try {
			String sql = "INSERT INTO `kurallar` (`kur_ad`, `kur_icerik`, `kur_id`) VALUES (?,?,?)";
			PreparedStatement statement = DBConnect._Connection.prepareStatement(sql);
			statement.setString(1, this.kur_ad);
			statement.setString(2, this.kur_icerik);
			statement.setInt(3, this.kur_id);
			statement.executeUpdate();
			return true;

		} catch (Exception e) {
			System.out.println(e);
			return false;
		}

	}

	public boolean kurallarDBSil() {
		try {
			DBConnect.Olustur();

			String sql = "DELETE FROM `kurallar` WHERE `kur_id` = ?";
			PreparedStatement statement = DBConnect._Connection.prepareStatement(sql);
			statement.setInt(1, this.kur_id);
			statement.executeUpdate();
			return true;

		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}

	public boolean kurallarDBSil(int kur_id) {
		try {
			DBConnect.Olustur();

			String sql = "DELETE FROM `kurallar` WHERE `kur_id` = ?";
			PreparedStatement statement = DBConnect._Connection.prepareStatement(sql);
			statement.setInt(1, kur_id);
			statement.executeUpdate();
			return true;

		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}
	
}
