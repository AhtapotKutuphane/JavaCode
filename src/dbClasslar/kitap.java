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
import globalFonksiyonlar.Fonksiyonlar;

/**
 * @author ozan
 *
 */
public class kitap {
	private int k_id;
	private int y_id;
	private int k_depo;
	private int k_raf;
	private int k_odunc;
	private int kat_id;
	private String k_ad = "";
	private String silindimi = "h";

	public String getSilindimi() {
		return silindimi;
	}

	public void setSilindimi(String silindimi) {
		this.silindimi = silindimi;
	}

	public int getK_id() {
		return k_id;
	}

	public void setK_id(int k_id) {
		this.k_id = k_id;
	}

	public int getY_id() {
		return y_id;
	}

	public void setY_id(int y_id) {
		this.y_id = y_id;
	}

	public int getK_depo() {
		return k_depo;
	}

	public void setK_depo(int k_depo) {
		this.k_depo = k_depo;
	}

	public int getK_raf() {
		return k_raf;
	}

	public void setK_raf(int k_raf) {
		this.k_raf = k_raf;
	}

	public int getK_odunc() {
		return k_odunc;
	}

	public void setK_odunc(int k_odunc) {
		this.k_odunc = k_odunc;
	}

	public int getKat_id() {
		return kat_id;
	}

	public void setKat_id(int kat_id) {
		this.kat_id = kat_id;
	}

	public String getK_ad() {
		return k_ad;
	}

	public void setK_ad(String k_ad) {
		this.k_ad = k_ad;
	}

	public kitap() {

	}
	
	@Override
	public String toString() {
		return this.k_ad;
	//	return super.toString();
	}

	/**
	 * Get Setler ile uðraþmadan direk create yaparken verileri eklemek için id ile
	 * birlikte
	 * 
	 * @param k_id
	 * @param y_id
	 * @param k_depo
	 * @param k_raf
	 * @param k_odunc
	 * @param kat_id
	 * @param k_ad
	 */
	public kitap(int k_id, int y_id, int k_depo, int k_raf, int k_odunc, int kat_id, String k_ad) {

		this.k_id = k_id;
		this.y_id = y_id;
		this.k_depo = k_depo;
		this.k_raf = k_raf;
		this.k_odunc = k_odunc;
		this.kat_id = kat_id;
		this.k_ad = k_ad;

	}

	/**
	 * Get Setler ile uðraþmadan direk create yaparken verileri eklemek için
	 * 
	 * @param y_id
	 * @param k_depo
	 * @param k_raf
	 * @param k_odunc
	 * @param kat_id
	 * @param k_ad
	 */
	public kitap(int y_id, int k_depo, int k_raf, int k_odunc, int kat_id, String k_ad) {

		this.y_id = y_id;
		this.k_depo = k_depo;
		this.k_raf = k_raf;
		this.k_odunc = k_odunc;
		this.kat_id = kat_id;
		this.k_ad = k_ad;

	}

	public Object[] kitapGetModel(){

		dbClasslar.yazar yazar = (dbClasslar.yazar) DiziDegiskenler.YazarListMap.get(this.y_id);
		dbClasslar.kategori kategori = (dbClasslar.kategori) DiziDegiskenler.KategoriListMap.get(this.kat_id);

		Object[] Object = { this.k_id, this.k_ad, yazar.getY_ad(), this.k_depo, this.k_raf, this.k_odunc,
				kategori.getKat_ad(), Fonksiyonlar.Toplam(this.k_odunc, this.k_raf, this.k_depo) };

		return Object;
	}

	public kitap kitapArrayListNewVerisiGetir() {

		return new kitap(this.k_id, this.y_id, this.k_depo, this.k_raf, this.k_odunc, this.kat_id, this.k_ad);
	}

	public boolean kitapDBGüncelle(String k_ad, int y_id, int k_depo, int k_raf, int k_odunc, int kat_id, int k_id) {

		DBConnect.Olustur();

		try {

			String sql = "UPDATE `kitap` SET `k_ad` = ?, `y_id` = ?, `k_depo` = ?, `k_raf` = ?, `k_odunc` = ?, `kat_id` = ? WHERE `k_id` = ?";
			PreparedStatement statement = DBConnect._Connection.prepareStatement(sql);
			statement.setString(1, k_ad);
			statement.setInt(2, y_id);
			statement.setInt(3, k_depo);
			statement.setInt(4, k_raf);
			statement.setInt(5, k_odunc);
			statement.setInt(6, kat_id);
			statement.setInt(7, k_id);
			statement.executeUpdate();
			return true;

		} catch (Exception e) {
			System.out.println(e);
			return false;
		}

	}

	public boolean kitapDBGüncelle() {

		DBConnect.Olustur();

		try {

			String sql = "UPDATE `kitap` SET `k_ad` = ?, `y_id` = ?, `k_depo` = ?, `k_raf` = ?, `k_odunc` = ?, `kat_id` = ? WHERE `k_id` = ?";
			PreparedStatement statement = DBConnect._Connection.prepareStatement(sql);
			statement.setString(1, this.k_ad);
			statement.setInt(2, this.y_id);
			statement.setInt(3, this.k_depo);
			statement.setInt(4, this.k_raf);
			statement.setInt(5, this.k_odunc);
			statement.setInt(6, this.kat_id);
			statement.setInt(7, this.k_id);
			statement.executeUpdate();
			return true;

		} catch (Exception e) {
			System.out.println(e);
			return false;
		}

	}

	public boolean kitapDBEkle() {

		DBConnect.Olustur();

		try {
			String sql = "INSERT INTO `kitap` (`k_ad`, `y_id`, `k_depo`, `k_raf`, `k_odunc`, `kat_id`,`k_id`) VALUES (?,?, ?, ?, ?, ?, ?)";
			PreparedStatement statement = DBConnect._Connection.prepareStatement(sql);
			statement.setString(1, k_ad);
			statement.setInt(2, y_id);
			statement.setInt(3, k_depo);
			statement.setInt(4, k_raf);
			statement.setInt(5, k_odunc);
			statement.setInt(6, kat_id);
			statement.setInt(7, k_id);
			statement.executeUpdate();
			return true;

		} catch (Exception e) {
			System.out.println(e);
			return false;
		}

	}

	/**
	 * o yaratýlmýþ nesnedeki kitabý siler.
	 */
	public boolean kitapDBSil() {
		try {
			DBConnect.Olustur();

			String sql = "DELETE FROM `kitap` WHERE `kitap`.`k_id` = ?";
			PreparedStatement statement = DBConnect._Connection.prepareStatement(sql);
			statement.setInt(1, this.k_id);
			statement.executeUpdate();
			return true;

		} catch (Exception e) {
			System.out.println(e);
			return false;
		}

	}

	/**
	 * o yaratýlmýþ nesnedeki kitabý siler.
	 * 
	 * @return
	 */
	public static boolean kitapDBList() {
		try {
			DBConnect.Olustur();
			
			DiziDegiskenler.KitapListMap  = new HashMap<Integer, kitap>();
			
			String sql = "SELECT * FROM `kitap`  ORDER BY  k_id ASC";
			PreparedStatement statement = DBConnect._Connection.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				DiziDegiskenler.KitapListMap.put(Integer.parseInt(rs.getString("k_id")),
						new dbClasslar.kitap(Integer.parseInt(rs.getString("k_id")),
								Integer.parseInt(rs.getString("y_id")), Integer.parseInt(rs.getString("k_depo")),
								Integer.parseInt(rs.getString("k_raf")), Integer.parseInt(rs.getString("k_odunc")),
								Integer.parseInt(rs.getString("kat_id")), rs.getString("k_ad")));
			}
			return true;

		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}

	/**
	 * istenilen id deki itemi getirir.
	 * 
	 * @param k_id
	 *            id verisi
	 * @return null deðer döndürürse hatalýdýr. Kitap verisi dönmesi beklenir.
	 */
	public static kitap kitapDBList(int k_id) {
		try {
			DBConnect.Olustur();
			
			DiziDegiskenler.KitapListMap = new HashMap<Integer, kitap>();
			
			String sql = "SELECT * FROM `kitap`  WHERE k_id = ? ORDER BY  k_id ASC";
			PreparedStatement statement = DBConnect._Connection.prepareStatement(sql);
			statement.setInt(1, k_id);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				return new kitap(Integer.parseInt(rs.getString("k_id")), Integer.parseInt(rs.getString("y_id")),
						Integer.parseInt(rs.getString("k_depo")), Integer.parseInt(rs.getString("k_raf")),
						Integer.parseInt(rs.getString("k_odunc")), Integer.parseInt(rs.getString("kat_id")),
						rs.getString("k_ad"));
			}
			System.out.println("While dýþý");
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
		return null;
	}

}
