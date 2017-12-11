package dbClasslar;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import DatabaseFonksiyonlar.DBConnect;
import globalDegiskenler.DiziDegiskenler;

/**
 * 
 * @author Alican
 *
 */
public class uyeler {

	private int u_id;
	private java.sql.Date u_dogum_yili;
	private int ut_id;
	private String u_ad = "";
	private String u_tc = "";
	private String u_cep_telefon = "";
	private String u_soyad = "";
	private String u_meslek = "";
	private String u_email = "";
	private String u_adres = "";
	private String silindimi = "h";

	public String getSilindimi() {
		return silindimi;
	}

	public void setSilindimi(String silindimi) {
		this.silindimi = silindimi;
	}

	public int getU_id() {
		return u_id;
	}

	public void setU_id(int u_id) {
		this.u_id = u_id;
	}

	public String getU_tc() {
		return u_tc;
	}

	public void setU_tc(String u_tc) {
		this.u_tc = u_tc;
	}

	public java.sql.Date getU_dogum_yili() {
		return u_dogum_yili;
	}

	public void setU_dogum_yili(java.sql.Date u_dogum_yili) {
		this.u_dogum_yili = u_dogum_yili;
	}

	public String getU_ad() {
		return u_ad;
	}

	public void setU_ad(String u_ad) {
		this.u_ad = u_ad;
	}

	public String getU_cep_telefon() {
		return u_cep_telefon;
	}

	public void setU_cep_telefon(String u_cep_telefon) {
		this.u_cep_telefon = u_cep_telefon;
	}

	public String getU_soyad() {
		return u_soyad;
	}

	public void setU_soyad(String u_soyad) {
		this.u_soyad = u_soyad;
	}

	public String getU_meslek() {
		return u_meslek;
	}

	public void setU_meslek(String u_meslek) {
		this.u_meslek = u_meslek;
	}

	public String getU_email() {
		return u_email;
	}

	public void setU_email(String u_email) {
		this.u_email = u_email;
	}

	public String getU_adres() {
		return u_adres;
	}

	public void setU_adres(String u_adres) {
		this.u_adres = u_adres;
	}

	public int getUt_id() {
		return ut_id;
	}

	public void setUt_id(int ut_id) {
		this.ut_id = ut_id;
	}

	@Override
	public String toString() {
		return this.u_ad + " " + this.u_soyad;
		// return super.toString();
	}

	public uyeler() {

	}

	/**
	 * Get Setler ile uðraþmadan direk create yaparken verileri eklemek için id ile
	 * birlikte
	 * 
	 * @param üye_id
	 * @param TC
	 * @param üye_ad
	 * @param üye_soyad
	 * @param u_dogum_yili
	 * @param üye_meslek
	 * @param u_cep_tel
	 * @param üye_email
	 * @param üye_adres
	 */
	public uyeler(int u_id, String u_tc, String u_ad, String u_soyad, java.sql.Date u_dogum_yili, String u_meslek,
			String u_cep_telefon, String u_email, String u_adres, String silindimi, int ut_id) {

		this.u_id = u_id;
		this.u_tc = u_tc;
		this.u_ad = u_ad;
		this.u_soyad = u_soyad;
		this.u_dogum_yili = u_dogum_yili;
		this.u_meslek = u_meslek;
		this.u_cep_telefon = u_cep_telefon;
		this.u_email = u_email;
		this.u_adres = u_adres;
		this.ut_id = ut_id;
	}

	public Object[] uyeGetModel() {

		dbClasslar.uyetipi uye_tipi = (dbClasslar.uyetipi) DiziDegiskenler.UyeTipiListMap.get(this.ut_id);

		Object[] Object = { this.u_id, this.u_tc, this.u_ad, this.u_soyad, this.u_dogum_yili, this.u_meslek,
				this.u_cep_telefon, this.u_email, this.u_adres, uye_tipi.getUt_ad() };

		return Object;
	}

	public uyeler uyeArrayListNewVerisiGetir() {

		return new uyeler(this.u_id, this.u_tc, this.u_ad, this.u_soyad, this.u_dogum_yili, this.u_meslek,
				this.u_cep_telefon, this.u_email, this.u_adres, this.silindimi, this.ut_id);
	}

	public boolean üyeDBGüncelle(int TC, String üye_ad, String üye_soyad, int u_dogum_yili, String üye_meslek,
			int u_cep_tel, String üye_email, String üye_adres) {

		DBConnect.Olustur();

		try {

			String sql = "UPDATE `uyeler` SET `u_tc` = ?, `u_ad` = ?, `u_soyad` = ?, `u_dogum_yili` = ?, `u_meslek` = ?, `u_cep_telefon` = ?, `u_email` = ?, `u_adres` = ?,`ut_id` = ? WHERE `u_id` = ?";
			PreparedStatement statement = DBConnect._Connection.prepareStatement(sql);
			statement.setString(1, this.u_tc);
			statement.setString(2, this.u_ad);
			statement.setString(3, this.u_soyad);
			statement.setDate(4, this.u_dogum_yili);
			statement.setString(5, this.u_meslek);
			statement.setString(6, this.u_cep_telefon);
			statement.setString(7, this.u_email);
			statement.setString(8, this.u_adres);
			statement.setInt(9, this.ut_id);
			statement.setInt(10, this.u_id);
			statement.executeUpdate();
			return true;

		} catch (Exception e) {
			System.out.println(e);
			return false;
		}

	}

	public boolean uyeDBGüncelle() {

		DBConnect.Olustur();

		try {

			String sql = "UPDATE `uyeler` SET `u_tc` = ?, `u_ad` = ?, `u_soyad` = ?, `u_dogum_yili` = ?, `u_meslek` = ?, `u_cep_telefon` = ?, `u_email` = ?, `u_adres` = ?,`ut_id` = ? WHERE `u_id` = ?";
			PreparedStatement statement = DBConnect._Connection.prepareStatement(sql);
			statement.setString(1, this.u_tc);
			statement.setString(2, this.u_ad);
			statement.setString(3, this.u_soyad);
			statement.setDate(4, this.u_dogum_yili);
			statement.setString(5, this.u_meslek);
			statement.setString(6, this.u_cep_telefon);
			statement.setString(7, this.u_email);
			statement.setString(8, this.u_adres);
			statement.setInt(9, this.ut_id);
			statement.setInt(10, this.u_id);
			statement.executeUpdate();
			return true;

		} catch (Exception e) {
			System.out.println(e);
			return false;
		}

	}

	public boolean uyeDBEkle() {

		DBConnect.Olustur();

		try {
			String sql = "INSERT INTO `uyeler` (`u_tc`, `u_ad`, `u_soyad`, `u_dogum_yili`, `u_meslek`, `u_cep_telefon`, `u_email`, `u_adres`, `ut_id`,`u_id`) VALUES (?,?, ?, ?, ?, ?, ?,?,?,?)";
			PreparedStatement statement = DBConnect._Connection.prepareStatement(sql);
			statement.setString(1, this.u_tc);
			statement.setString(2, this.u_ad);
			statement.setString(3, this.u_soyad);
			statement.setDate(4, this.u_dogum_yili);
			statement.setString(5, this.u_meslek);
			statement.setString(6, this.u_cep_telefon);
			statement.setString(7, this.u_email);
			statement.setString(8, this.u_adres);
			statement.setInt(9, this.ut_id);
			statement.setInt(10, this.u_id);
			statement.executeUpdate();
			return true;

		} catch (Exception e) {
			System.out.println(e);
			return false;
		}

	}

	/**
	 * o yaratýlmýþ nesnedeki üyeyi siler.
	 */
	public boolean uyeDBSil() {
		try {
			DBConnect.Olustur();

			String sql = "DELETE FROM `uyeler` WHERE `u_id` = ?";
			PreparedStatement statement = DBConnect._Connection.prepareStatement(sql);
			statement.setInt(1, this.u_id);
			statement.executeUpdate();
			return true;

		} catch (Exception e) {
			System.out.println(e);
			return false;
		}

	}

	/**
	 * Listeliyor.
	 * 
	 * @return
	 */
	public static boolean uyeDBList() {
		try {
			DBConnect.Olustur();

			DiziDegiskenler.UyeListMap = new HashMap<Integer, uyeler>();

			String sql = "SELECT * FROM `uyeler`  ORDER BY  u_id ASC";
			PreparedStatement statement = DBConnect._Connection.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				DiziDegiskenler.UyeListMap.put(rs.getInt("u_id"),
						new dbClasslar.uyeler(rs.getInt("u_id"), rs.getString("u_tc"), rs.getString("u_ad"),
								rs.getString("u_soyad"), rs.getDate("u_dogum_yili"), rs.getString("u_meslek"),
								rs.getString("u_cep_telefon"), rs.getString("u_email"), rs.getString("u_adres"),
								rs.getString("silindimi"), rs.getInt("ut_id")));
			}
			return true;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}

	public static uyeler uyeDBList(int id) {
		try {
			DBConnect.Olustur();

			DiziDegiskenler.UyeListMap = new HashMap<Integer, uyeler>();

			String sql = "SELECT * FROM `uyeler` WHERE u_id = ? ORDER BY  u_id ASC";
			PreparedStatement statement = DBConnect._Connection.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				return new dbClasslar.uyeler(rs.getInt("u_id"), rs.getString("u_tc"), rs.getString("u_ad"),
						rs.getString("u_soyad"), rs.getDate("u_dogum_yili"), rs.getString("u_meslek"),
						rs.getString("u_cep_telefon"), rs.getString("u_email"), rs.getString("u_adres"),
						rs.getString("silindimi"), rs.getInt("ut_id"));
			}
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}

		return null;
	}

}
