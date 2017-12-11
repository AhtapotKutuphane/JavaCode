package dbClasslar;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

import DatabaseFonksiyonlar.DBConnect;
import globalDegiskenler.DiziDegiskenler;
import globalFonksiyonlar.Fonksiyonlar;

public class tedarikci {

	private int t_id;
	private String t_ad, t_telefon, t_adres, t_email = "";
	private String silindimi = "h";

	
	
	public String getSilindimi() {
		return silindimi;
	}

	public void setSilindimi(String silindimi) {
		this.silindimi = silindimi;
	}

	public int getT_id() {
		return t_id;
	}

	public void setT_id(int t_id) {
		this.t_id = t_id;
	}

	public String getT_ad() {
		return t_ad;
	}

	public void setT_ad(String t_ad) {
		this.t_ad = t_ad;
	}

	public String getT_telefon() {
		return t_telefon;
	}

	public void setT_telefon(String t_telefon) {
		this.t_telefon = t_telefon;
	}

	public String getT_adres() {
		return t_adres;
	}

	public void setT_adres(String t_adres) {
		this.t_adres = t_adres;
	}

	public String getT_email() {
		return t_email;
	}

	public void setT_email(String t_email) {
		this.t_email = t_email;
	}

	@Override
	public String toString() {
		return this.t_ad;
	//	return super.toString();
	}
	
	public tedarikci() {
		// TODO Auto-generated constructor stub
	}

	public tedarikci(int t_id, String t_ad, String t_telefon, String t_email, String t_adres,String silindimi) {
		this.t_id = t_id;
		this.t_ad = t_ad;
		this.t_telefon = t_telefon;
		this.t_adres = t_adres;
		this.t_email = t_email;
		this.silindimi = silindimi;
	}

	public Object[] tedarikciGetModel() {

		Object[] Object = { this.t_id, this.t_ad, this.t_telefon, this.t_email, this.t_adres , this.silindimi };
		return Object;
	}

	public tedarikci tedarikciNewVerisiGetir() {

		return new tedarikci(this.t_id, this.t_ad, this.t_telefon, this.t_email, this.t_adres,this.silindimi);
	}

	public boolean tedarikciDBGüncelle(int t_id, String t_ad, String t_telefon, String t_email, String t_adres) {

		DBConnect.Olustur();

		try {

			String sql = "UPDATE `tedarikci` SET `t_ad` = ?, `t_telefon` = ?, `t_email` = ?, `t_adres` = ? , silindimi = 'h' WHERE `t_id` = ?";
			PreparedStatement statement = DBConnect._Connection.prepareStatement(sql);
			statement.setString(1, t_ad);
			statement.setString(2, t_telefon);
			statement.setString(3, t_email);
			statement.setString(4, t_adres);
			statement.setInt(5, t_id);
			statement.executeUpdate();
			return true;

		} catch (Exception e) {
			System.out.println(e);
			return false;
		}

	}

	public boolean tedarikciDBGüncelle() {

		DBConnect.Olustur();

		try {

			String sql = "UPDATE `tedarikci` SET `t_ad` = ?, `t_telefon` = ?, `t_email` = ?, `t_adres` = ? , silindimi = 'h' WHERE `t_id` = ?";
			PreparedStatement statement = DBConnect._Connection.prepareStatement(sql);
			statement.setString(1, this.t_ad);
			statement.setString(2, this.t_telefon);
			statement.setString(3, this.t_email);
			statement.setString(4, this.t_adres);
			statement.setInt(5, this.t_id);
			statement.executeUpdate();
			return true;

		} catch (Exception e) {
			System.out.println(e);
			return false;
		}

	}

	public boolean tedarikciDBEkle() {

		DBConnect.Olustur();

		try {
			String sql = "INSERT INTO `tedarikci` (`t_ad`, `t_telefon`, `t_email`, `t_adres`,`t_id`) VALUES (?,?,?,?,?)";
			PreparedStatement statement = DBConnect._Connection.prepareStatement(sql);
			statement.setString(1, this.t_ad);
			statement.setString(2, this.t_telefon);
			statement.setString(3, this.t_email);
			statement.setString(4, this.t_adres);
			statement.setInt(5, this.t_id);
			statement.executeUpdate();
			return true;

		} catch (Exception e) {
			System.out.println(e);
			return false;
		}

	}

	public boolean tedarikciDBSil() {
		try {
			DBConnect.Olustur();

			String sql = "UPDATE `tedarikci` SET silindimi = 'e' WHERE `t_id` = ?";
			PreparedStatement statement = DBConnect._Connection.prepareStatement(sql);
			statement.setInt(1, this.t_id);
			statement.executeUpdate();
			return true;

		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}

	public boolean tedarikciDBSil(int t_id) {
		try {
			DBConnect.Olustur();

			String sql = "DELETE FROM `tedarikci` WHERE `t_id` = ?";
			PreparedStatement statement = DBConnect._Connection.prepareStatement(sql);
			statement.setInt(1, t_id);
			statement.executeUpdate();
			return true;

		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}

	public static boolean tedarikciDBList() {
		try {
			DBConnect.Olustur();

			DiziDegiskenler.TedarikciListMap = new HashMap<Integer, tedarikci>();
			
			String sql = "SELECT * FROM `tedarikci`  ORDER BY  t_id ASC";
			PreparedStatement statement = DBConnect._Connection.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				DiziDegiskenler.TedarikciListMap.put(rs.getInt("t_id"),
						new dbClasslar.tedarikci(rs.getInt("t_id"), rs.getString("t_ad"), rs.getString("t_telefon"),
								rs.getString("t_email"), rs.getString("t_adres"),rs.getString("silindimi")));
			}
			return true;

		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}

	public static tedarikci tedarikciDBList(int t_id) {
		try {
			DBConnect.Olustur();

			DiziDegiskenler.TedarikciListMap = new HashMap<Integer, tedarikci>();
			
			String sql = "SELECT * FROM `tedarikci` where t_id = ?  ORDER BY  t_id ASC";
			PreparedStatement statement = DBConnect._Connection.prepareStatement(sql);
			statement.setInt(1, t_id);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				return new dbClasslar.tedarikci(rs.getInt("t_id"), rs.getString("t_ad"), rs.getString("t_telefon"),
						rs.getString("t_email"), rs.getString("t_adres"),rs.getString("silindimi"));
			}
			return null;

		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
}
