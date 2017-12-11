package dbClasslar;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import DatabaseFonksiyonlar.DBConnect;
import globalDegiskenler.DiziDegiskenler;

public class durum {
	private int d_id;
	private String d_ad;

	public int getD_id() {
		return d_id;
	}

	public void setD_id(int d_id) {
		this.d_id = d_id;
	}

	public String getD_ad() {
		return d_ad;
	}

	public void setD_ad(String d_ad) {
		this.d_ad = d_ad;
	}

	@Override
	public String toString() {
		return this.d_ad;
		// return super.toString();
	}

	/**
	 * @param d_id
	 * @param d_ad
	 * @param silindimi
	 */
	public durum(int d_id, String d_ad) {
		super();
		this.d_id = d_id;
		this.d_ad = d_ad;
	}

	public static boolean durumDBList() {
		DBConnect.Olustur();

		DiziDegiskenler.DurumListMap = new HashMap<Integer, durum>();
		
		try {
			String sql = "SELECT * FROM `durum`  ORDER BY  d_id ASC";
			PreparedStatement statement = DBConnect._Connection.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				DiziDegiskenler.DurumListMap.put(rs.getInt("d_id"), new durum(rs.getInt("d_id"), rs.getString("d_ad")));
			}
			return true;

		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}

}
