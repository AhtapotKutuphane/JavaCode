package dbClasslar;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import DatabaseFonksiyonlar.DBConnect;
import globalDegiskenler.DiziDegiskenler;
import globalFonksiyonlar.Fonksiyonlar;

public class personel {

	private int p_id;
	private String p_tc, p_ad, p_soyad, p_yetki, p_cep_telefon, p_email, p_adres, p_sifre;
	private java.sql.Date p_dogum_yili;

	public String getP_email() {
		return p_email;
	}

	public void setP_email(String p_email) {
		this.p_email = p_email;
	}

	public int getP_id() {
		return p_id;
	}

	public void setP_id(int p_id) {
		this.p_id = p_id;
	}

	public String getP_tc() {
		return p_tc;
	}

	public void setP_tc(String p_tc) {
		this.p_tc = p_tc;
	}

	public String getP_ad() {
		return p_ad;
	}

	public void setP_ad(String p_ad) {
		this.p_ad = p_ad;
	}

	public String getP_soyad() {
		return p_soyad;
	}

	public void setP_soyad(String p_soyad) {
		this.p_soyad = p_soyad;
	}

	public String getP_yetki() {
		return p_yetki;
	}

	public void setP_yetki(String p_yetki) {
		this.p_yetki = p_yetki;
	}

	public String getP_cep_telefon() {
		return p_cep_telefon;
	}

	public void setP_cep_telefon(String p_cep_telefon) {
		this.p_cep_telefon = p_cep_telefon;
	}

	public String getP_adres() {
		return p_adres;
	}

	public void setP_adres(String p_adres) {
		this.p_adres = p_adres;
	}

	public String getP_sifre() {
		return p_sifre;
	}

	public void setP_sifre(String p_sifre) {
		this.p_sifre = p_sifre;
	}

	public java.sql.Date getP_dogum_yili() {
		return p_dogum_yili;
	}

	public void setP_dogum_yili(java.sql.Date p_dogum_yili) {
		this.p_dogum_yili = p_dogum_yili;
	}

	public personel() {

	}

	/**
	 * 
	 * @param p_id
	 * @param p_tc
	 * @param p_ad
	 * @param p_soyad
	 * @param p_dogum_yili
	 * @param p_yetki
	 * @param p_cep_telefon
	 * @param p_email
	 * @param p_adres
	 * @param p_sifre
	 *
	 */

	public personel(int p_id, String p_tc, String p_ad, String p_soyad, java.sql.Date p_dogum_yili, String p_yetki,
			String p_cep_telefon, String p_email, String p_adres, String p_sifre) {

		this.p_id = p_id;
		this.p_tc = p_tc;
		this.p_ad = p_ad;
		this.p_soyad = p_soyad;
		this.p_dogum_yili = p_dogum_yili;
		this.p_yetki = p_yetki;
		this.p_email = p_email;
		this.p_cep_telefon = p_cep_telefon;
		this.p_adres = p_adres;
		this.p_sifre = p_sifre;

	}

	public Object[] personelGetModel() {

		Object[] Object = { this.p_id, this.p_tc, this.p_ad, this.p_soyad, this.p_dogum_yili, this.p_yetki,
				this.p_cep_telefon, this.p_email, this.p_adres, this.p_sifre };

		return Object;
	}

	public personel personelArrayListNewVerisiGetir() {

		return new personel(this.p_id, this.p_tc, this.p_ad, this.p_soyad, this.p_dogum_yili, this.p_yetki,
				this.p_cep_telefon, this.p_email, this.p_adres, this.p_sifre);
	}

	public boolean personelDBGüncelle(String p_tc, String p_ad, String p_soyad, java.sql.Date p_dogum_yili,
			String p_yetki, String p_cep_telefon, String p_email, String p_adres, String p_sifre, int p_id) {

		DBConnect.Olustur();

		try {

			String sql = "UPDATE `personel` SET `p_tc` = ?, `p_ad` = ?, `p_soyad` = ?, `p_dogum_yili` = ?, `p_yetki` = ?, `p_cep_telefon` = ?,p_email=?,p_adres=?,`p_sifre`=? WHERE `p_id` = ?";
			PreparedStatement statement = DBConnect._Connection.prepareStatement(sql);
			statement.setString(1, p_tc);
			statement.setString(2, p_ad);
			statement.setString(3, p_soyad);
			statement.setDate(4, (java.sql.Date) p_dogum_yili);
			statement.setString(5, p_yetki);
			statement.setString(6, p_cep_telefon);
			statement.setString(7, p_email);
			statement.setString(8, p_adres);
			statement.setString(9, p_sifre);
			statement.setInt(10, p_id);
			statement.executeUpdate();
			return true;

		} catch (Exception e) {
			System.out.println(e);
			return false;
		}

	}

	public boolean personelDBGüncelle() {

		DBConnect.Olustur();

		try {

			String sql = "UPDATE `personel` SET `p_tc` = ?, `p_ad` = ?, `p_soyad` = ?, `p_dogum_yili` = ?, `p_yetki` = ?, `p_cep_telefon` = ?, `p_email` = ?,`p_adres`= ? , `p_sifre` = ? WHERE `p_id` = ?";
			PreparedStatement statement = DBConnect._Connection.prepareStatement(sql);
			statement.setString(1, p_tc);
			statement.setString(2, p_ad);
			statement.setString(3, p_soyad);
			statement.setDate(4, p_dogum_yili);
			statement.setString(5, p_yetki);
			statement.setString(6, p_cep_telefon);
			statement.setString(7, p_email);
			statement.setString(8, p_adres);
			statement.setString(9, p_sifre);
			statement.setInt(10, p_id);
			statement.executeUpdate();
			return true;

		} catch (Exception e) {
			System.out.println(e);
			return false;
		}

	}

	public boolean personelDBEkle() {

		DBConnect.Olustur();

		try {
			String sql = "INSERT INTO `personel` (`p_tc`, `p_ad`, `p_soyad`, `p_dogum_yili`, `p_yetki`, `p_cep_telefon`,`p_email`,p_adres,p_sifre,p_id) VALUES (?,?, ?, ?, ?, ?, ?,?,?,?)";
			PreparedStatement statement = DBConnect._Connection.prepareStatement(sql);
			statement.setString(1, p_tc);
			statement.setString(2, p_ad);
			statement.setString(3, p_soyad);
			statement.setDate(4, p_dogum_yili);
			statement.setString(5, p_yetki);
			statement.setString(6, p_cep_telefon);
			statement.setString(7, p_email);
			statement.setString(8, p_adres);
			statement.setString(9, p_sifre);
			statement.setInt(10, p_id);

			statement.executeUpdate();
			return true;

		} catch (Exception e) {
			System.out.println(e);
			return false;
		}

	}

	public boolean personelDBSil() {
		try {
			DBConnect.Olustur();

			String sql = "DELETE FROM `personel` WHERE `personel`.`p_id` = ?";
			PreparedStatement statement = DBConnect._Connection.prepareStatement(sql);
			statement.setInt(1, this.p_id);
			statement.executeUpdate();
			return true;

		} catch (Exception e) {
			System.out.println(e);
			return false;
		}

	}

	/**
	 * Verilen id deki personeli siler
	 * 
	 * @param p_id
	 * @return
	 */
	public boolean personelDBSil(int p_id) {
		try {
			DBConnect.Olustur();

			String sql = "DELETE FROM `personel` WHERE `personel`.`p_id` = ?";
			PreparedStatement statement = DBConnect._Connection.prepareStatement(sql);
			statement.setInt(1, p_id);
			statement.executeUpdate();
			return true;

		} catch (Exception e) {
			System.out.println(e);
			return false;
		}

	}

	public static boolean personelDBList() {
		try {
			DBConnect.Olustur();

			DiziDegiskenler.PersonelListMap =  new HashMap<Integer, personel>();
			
			String sql = "SELECT * FROM `personel`  ORDER BY  p_id ASC";
			PreparedStatement statement = DBConnect._Connection.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				DiziDegiskenler.PersonelListMap.put(Integer.parseInt(rs.getString("p_id")),
						new dbClasslar.personel(Integer.parseInt(rs.getString("p_id")), rs.getString("p_tc"),
								rs.getString("p_ad"), rs.getString("p_soyad"),
								Fonksiyonlar.StringToDate(rs.getString("p_dogum_yili")), rs.getString("p_yetki"),
								rs.getString("p_cep_telefon"), rs.getString("p_email"), rs.getString("p_adres"),
								rs.getString("p_sifre")));
			}
			return true;

		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}
	
	public static personel personelDBList(int p_id) {
		try {
			DBConnect.Olustur();

			DiziDegiskenler.PersonelListMap =  new HashMap<Integer, personel>();
			
			String sql = "SELECT * FROM `personel` where p_id = ? ORDER BY  p_id ASC";
			PreparedStatement statement = DBConnect._Connection.prepareStatement(sql);
			statement.setInt(1, p_id);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				return new dbClasslar.personel(Integer.parseInt(rs.getString("p_id")), rs.getString("p_tc"),
								rs.getString("p_ad"), rs.getString("p_soyad"),
								Fonksiyonlar.StringToDate(rs.getString("p_dogum_yili")), rs.getString("p_yetki"),
								rs.getString("p_cep_telefon"), rs.getString("p_email"), rs.getString("p_adres"),
								rs.getString("p_sifre"));
			}
			return null;

		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

}
