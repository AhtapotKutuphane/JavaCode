package DatabaseFonksiyonlar;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnect {
	public static Connection _Connection = null;

	public static boolean Olustur() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			_Connection = DriverManager.getConnection("jdbc:mysql://localhost/kutuphane", "root", "");
			return true;
		} catch (Exception ex) {
			System.err.println(ex);
			return false;
		}
	}

}