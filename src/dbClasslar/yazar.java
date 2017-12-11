package dbClasslar;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import java.sql.PreparedStatement;

import DatabaseFonksiyonlar.DBConnect;
import globalDegiskenler.DiziDegiskenler;

/**
 * 
 * @author Alican
 *
 */
public class yazar {
	private int y_id;
	private String y_ad;
	private String silindimi = "h";
	private int toplam;
	
	

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
	public int getY_id() {
		return y_id;
	}

	public void setY_id(int y_id) {
		this.y_id = y_id;
	}

	public String getY_ad() {
		return y_ad;
	}

	public void setY_ad(String y_ad) {
		this.y_ad = y_ad;
	}

	@Override
	public String toString() {
		return this.y_ad;
		// return super.toString();
	}
	
	/**
	 * @param y_id
	 * @param y_ad
	 */
	public yazar(int y_id, String y_ad,String silindimi) {
		this.y_id = y_id;
		this.y_ad = y_ad;
		this.silindimi = silindimi;
	}
	
	public yazar(int y_id, String y_ad) {
		this.y_id = y_id;
		this.y_ad = y_ad;
	}

	public yazar() {
		// TODO Auto-generated constructor stub
	}
	public static boolean yazarDBList() {

		DBConnect.Olustur();
		DiziDegiskenler.YazarListMap = new HashMap<Integer, yazar>();
		try {
			String sql = "SELECT * FROM `yazar`  ORDER BY  y_id ASC";
			PreparedStatement statement = DBConnect._Connection.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				DiziDegiskenler.YazarListMap.put(rs.getInt("y_id"), new yazar(rs.getInt("y_id"), rs.getString("y_ad"),rs.getString("silindimi")));
			}
			return true;

		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}
	
	public Object[] yazarGetModel() {

		Object[] Object = { this.y_id, this.y_ad,this.toplam};

		return Object;
	}

	public yazar yazarNewVerisiGetir() {

		return new yazar(this.y_id, this.y_ad, this.silindimi);
	}

	public boolean yazarDBGüncelle(String y_ad, String silindimi, int y_id) {

		DBConnect.Olustur();

		try {

			String sql = "UPDATE `yazar` SET `y_ad` = ?, `silindimi` = ? WHERE `y_id` = ?";
			PreparedStatement statement = DBConnect._Connection.prepareStatement(sql);
			statement.setString(1, y_ad);
			statement.setString(2, silindimi);
			statement.setInt(3, y_id);
			statement.executeUpdate();
			return true;

		} catch (Exception e) {
			System.out.println(e);
			return false;
		}

	}

	public boolean yazarDBGüncelle() {

		DBConnect.Olustur();

		try {

			String sql = "UPDATE `yazar` SET `y_ad` = ?, `silindimi` = ? WHERE `y_id` = ?";
			PreparedStatement statement = DBConnect._Connection.prepareStatement(sql);
			statement.setString(1, y_ad);
			statement.setString(2, silindimi);
			statement.setInt(3, y_id);
			statement.executeUpdate();
			return true;

		} catch (Exception e) {
			System.out.println(e);
			return false;
		}

	}

	public boolean yazarDBEkle() {

		DBConnect.Olustur();

		try {
			String sql = "INSERT INTO `yazar` (`y_ad`, `silindimi`) VALUES (?,?)";
			PreparedStatement statement = DBConnect._Connection.prepareStatement(sql);
			statement.setString(1, y_ad);
			statement.setString(2, silindimi);
			statement.executeUpdate();
			return true;

		} catch (Exception e) {
			System.out.println(e);
			return false;
		}

	}

	public boolean yazarDBSil() {
		
		try {
			String sql = "UPDATE `yazar` SET `silindimi` = ? WHERE `y_id` = ?";
			PreparedStatement statement = DBConnect._Connection.prepareStatement(sql);
			statement.setString(1, "e");
			statement.setInt(2, y_id);
			statement.executeUpdate();
			return true;

		} catch (Exception e) {
			System.out.println(e);
			return false;
		}

	}

}
