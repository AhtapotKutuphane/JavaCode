package globalFonksiyonlar;

import java.util.Calendar;
import java.util.Vector;

import dbClasslar.kategori;
import dbClasslar.uyetipi;
import dbClasslar.yazar;
import globalDegiskenler.DiziDegiskenler;

/**
 * 
 * @author Alican
 *
 */

/**
 * 
 * @author Alican
 *
 */
public class comboboxModelFonksiyonlar {

	public static Vector KategoriVektorModelGetir() {
		Vector vector = new Vector();
		vector.addElement(new kategori(0, "Seçiniz", "h"));
		dbClasslar.kategori.kategoriDBList();
		Object[] obj = DiziDegiskenler.KategoriListMap.values().toArray();
		for (int i = 0; i < obj.length; i++) {
			dbClasslar.kategori kategori = (dbClasslar.kategori) obj[i];
			if (kategori.getSilindimi().equals("h")) {
				vector.addElement(kategori);
			}
		}

		return vector;
	}

	public static Vector YazarVektorModelGetir() {
		Vector vector = new Vector();
		vector.addElement(new yazar(0, "Seçiniz", "h"));
		dbClasslar.yazar.yazarDBList();
		Object[] obj = DiziDegiskenler.YazarListMap.values().toArray();
		for (int i = 0; i < obj.length; i++) {
			dbClasslar.yazar yazar = (dbClasslar.yazar) obj[i];
			if (yazar.getSilindimi().equals("h")) {
				vector.addElement(yazar);
			}
		}
		return vector;
	}

	public static Vector UyeTipiVektorModelGetir() {
		Vector vector = new Vector();
		vector.addElement(new uyetipi(0, "Seçiniz"));
		dbClasslar.uyetipi.uyetipiDBList();
		Object[] obj = DiziDegiskenler.UyeTipiListMap.values().toArray();
		for (int i = 0; i < obj.length; i++) {
			dbClasslar.uyetipi uye_tipi = (dbClasslar.uyetipi) obj[i];
			if (uye_tipi.getSilindimi().equals("h")) {
				vector.addElement(uye_tipi);
			}
		}
		return vector;
	}

	public static Vector KitapVektorModelGetir() {
		Vector vector = new Vector();
		vector.addElement(new uyetipi(0, "Seçiniz"));
		dbClasslar.kitap.kitapDBList();
		Object[] obj = DiziDegiskenler.KitapListMap.values().toArray();
		for (int i = 0; i < obj.length; i++) {
			dbClasslar.kitap object = (dbClasslar.kitap) obj[i];
			if (object.getSilindimi().equals("h")) {
				vector.addElement(object);
			}
		}
		return vector;
	}

	public static Vector UyelerVektorModelGetir() {
		Vector vector = new Vector();
		vector.addElement(new uyetipi(0, "Seçiniz"));
		dbClasslar.uyeler.uyeDBList();
		Object[] obj = DiziDegiskenler.UyeListMap.values().toArray();
		for (int i = 0; i < obj.length; i++) {
			dbClasslar.uyeler object = (dbClasslar.uyeler) obj[i];
			if (object.getSilindimi().equals("h")) {
				vector.addElement(object);
			}
		}
		return vector;
	}

	public static Vector DurumVektorModelGetir() {
		Vector vector = new Vector();
		vector.addElement(new uyetipi(0, "Seçiniz"));
		dbClasslar.durum.durumDBList();
		Object[] obj = DiziDegiskenler.DurumListMap.values().toArray();
		for (int i = 0; i < obj.length; i++) {
			dbClasslar.durum object = (dbClasslar.durum) obj[i];
			vector.addElement(object);
		}
		return vector;
	}

	public static Vector TedarikciVektorModelGetir() {
		Vector vector = new Vector();
		vector.addElement(new uyetipi(0, "Seçiniz"));
		dbClasslar.tedarikci.tedarikciDBList();
		Object[] obj = DiziDegiskenler.TedarikciListMap.values().toArray();
		for (int i = 0; i < obj.length; i++) {
			dbClasslar.tedarikci object = (dbClasslar.tedarikci) obj[i];
			if (object.getSilindimi().equals("h")) {
				vector.addElement(object);
			}
		}
		return vector;
	}

	public static Vector YetkiVektorModelGetir() {
		Vector vector = new Vector();
		vector.addElement("Seçiniz");
		vector.addElement("Yönetici");
		vector.addElement("Memur");
		vector.addElement("Personel");
		return vector;
	}

}
