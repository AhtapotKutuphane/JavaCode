package formlar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import DatabaseFonksiyonlar.IdAl;
import dbClasslar.kitap_odunc;
import globalDegiskenler.DiziDegiskenler;
import globalDegiskenler.DiziOlmayanDegiskenler;
import globalFonksiyonlar.Fonksiyonlar;
import varsayilan.Butonlar;

public class kitapOduncVerme {

	private JFrame frame;
	private JTextField uye_ad_soyad;
	private JTextField kitapArama;
	private static JTable kitapTable;
	private static JTable kitapGecmisTable;
	private static JTable sepetTable;
	private JScrollPane pane1, pane2, pane3;
	int ko_id;
	int KitapLimit = 0;
	JLabel KitapLimitLabel = null;
	dbClasslar.uyeler uye = DiziOlmayanDegiskenler.UyeOduncIade;
	dbClasslar.uyetipi uyetipi = null;

	/**
	 * 
	 * @param str
	 */
	public kitapOduncVerme() {
		Yükle();
	}

	public JFrame formAl() {
		return frame;
	};

	private void Yenile() {
		kitapTable = Fonksiyonlar.JTableVeriTemizleme(kitapTable);
		kitapArama.setText("");
		Ara();
	}

	private boolean OduncAlKitapGuncelle(dbClasslar.kitap kitap, dbClasslar.kitap_odunc kitapodunc, int i, int miktar) {
		boolean bool = true;
		if (miktar >= 0) {
			if (kitap.kitapDBGüncelle()) {
				if (kitapodunc.kitap_oduncDBSil()) {
					Fonksiyonlar.JTableVeriSilme(kitapGecmisTable, i);
					DiziDegiskenler.KitapOduncMapUyeOzel.replace(kitapodunc.getKo_id(),
							kitapodunc.kitap_oduncNewVerisiGetir());
					DiziDegiskenler.KitapListMap.replace(kitap.getK_id(), kitap.kitapArrayListNewVerisiGetir());
					bool = bool && true;
					Yenile();
				} else {
					bool = bool && false;
				}
			}
		} else {
			bool = bool && false;
		}
		return bool;
	}

	private ActionListener IadeEt = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			int i = kitapGecmisTable.getSelectedRow();
			if (i >= 0) {
				int id = Fonksiyonlar.IdGetir(kitapGecmisTable, i);
				dbClasslar.kitap_odunc kitapodunc = DiziDegiskenler.KitapOduncMapUyeOzel.get(id);
				dbClasslar.kitap kitap = DiziDegiskenler.KitapListMap.get(kitapodunc.getK_id());
				kitap.setK_depo(kitap.getK_depo() + 1);
				kitap.setK_odunc(kitap.getK_odunc() - 1);
				if (OduncAlKitapGuncelle(kitap, kitapodunc, i, kitap.getK_odunc())) {
					KitapLimitLabel.setText("Kitap Limit : " + ++KitapLimit);
					oduncListele();
					new JOptionPane().showMessageDialog(null, "Iade edildi.");
				}
			} else {
				new JOptionPane().showMessageDialog(null, "Iade edilemedi.");
			}

		}
	};

	private ActionListener SepettenÇýkar = new ActionListener() {
		@Override
		// Þurda çok absürt bi fantezi var ona sonra dönecem ... (sayi güncellemek için
		// fonksiyon gerekecek.)
		public void actionPerformed(ActionEvent arg0) {
			int i = sepetTable.getSelectedRow();
			if (i >= 0) {
				Fonksiyonlar.JTableVeriSilme(sepetTable, i);
				KitapLimitLabel.setText("Kitap Limit : " + ++KitapLimit);

			} else {
				new JOptionPane().showMessageDialog(null, "Kitap seçmediniz.");
			}
		}
	};

	private void oduncListele() {
		kitapGecmisTable = Fonksiyonlar.JTableVeriTemizleme(kitapGecmisTable);
		KitapLimit = uyetipi.getUt_kitap_limit();
		dbClasslar.kitap_odunc.kitap_oduncListeUyeOzel(DiziOlmayanDegiskenler.UyeOduncIade.getU_id());
		Object[] obj = DiziDegiskenler.KitapOduncMapUyeOzel.values().toArray();
		for (int i = 0; i < obj.length; i++) {
			dbClasslar.kitap_odunc kitapodunc = (dbClasslar.kitap_odunc) obj[i];
			if (kitapodunc.getSilindimi().equals("h")) {
				kitapGecmisTable = Fonksiyonlar.JTableVeriEkleme(kitapGecmisTable, kitapodunc.kitap_oduncGetModel());
				KitapLimitLabel.setText("Kitap Limit : " + --KitapLimit);
			}
		}
		
		Ara();
	}

	private boolean OduncVerKitapGuncelle(dbClasslar.kitap kitap, dbClasslar.kitap_odunc kitapodunc, int i,
			int miktar) {
		boolean bool = true;
		if (miktar >= 0) {
			if (kitap.kitapDBGüncelle()) {
				if (kitapodunc.kitap_oduncDBEkle()) {
					Fonksiyonlar.JTableVeriSilme(sepetTable, i);
					dbClasslar.yazar yazar = DiziDegiskenler.YazarListMap.get(kitap.getY_id());
					Fonksiyonlar.JTableVeriEkleme(kitapGecmisTable, kitapodunc.getKo_id(), kitap.getK_ad(),
							yazar.getY_ad(), 0);
					DiziDegiskenler.KitapOduncMapUyeOzel.put(kitapodunc.getKo_id(),kitapodunc.kitap_oduncNewVerisiGetir());
					DiziDegiskenler.KitapListMap.replace(kitap.getK_id(), kitap.kitapArrayListNewVerisiGetir());
					bool = bool && true;
					Yenile();
				} else {
					bool = bool && false;
				}
			}
		} else {
			bool = bool && false;
		}
		return bool;
	}

	private ActionListener OduncVer = new ActionListener() {
		@Override
		// Þurda çok absürt bi fantezi var ona sonra dönecem ... (sayi güncellemek için
		// fonksiyon gerekecek.)
		public void actionPerformed(ActionEvent arg0) {
			if (sepetTable.getRowCount() > 0) {
				int id = 0;
				boolean bool = true;
				boolean kitapYok = false;
				for (int i = sepetTable.getRowCount() - 1; i >= 0; i--) {
					id = Fonksiyonlar.IdGetir(sepetTable, i);
					dbClasslar.kitap kitap = DiziDegiskenler.KitapListMap.get(id);
					Calendar calendar = Calendar.getInstance();
					java.sql.Date date = new java.sql.Date(calendar.getTime().getTime());
					calendar.add(Calendar.DATE, uyetipi.getUt_gun_limit());
					java.sql.Date dateEklendi = new java.sql.Date(calendar.getTime().getTime());
					dbClasslar.kitap_odunc kitapodunc = new kitap_odunc(IdAl.KitapOduncIdAl() + 1, kitap.getK_id(),
							uye.getU_id(), date, dateEklendi, "h");
					kitap.setK_raf(kitap.getK_raf() - 1);
					if (kitap.getK_raf() >= 0) {
						kitap.setK_odunc(kitap.getK_odunc() + 1);
						bool = OduncVerKitapGuncelle(kitap, kitapodunc, i, kitap.getK_raf());
					} else if (kitap.getK_depo() >= 0) {
						kitap.setK_raf(0);
						kitap.setK_depo(kitap.getK_depo() - 1);
						kitap.setK_odunc(kitap.getK_odunc() + 1);
						if (kitap.getK_depo() >= 0) {
							bool = OduncVerKitapGuncelle(kitap, kitapodunc, i, kitap.getK_depo());
						} else {
							kitap.setK_depo(0);
							kitapYok = true;
							bool = false;
						}
					} else {
						kitapYok = true;
						bool = false;
					}
				}
				if (bool) {
					new JOptionPane().showMessageDialog(null, "Eklendi.");
				} else {
					if (kitapYok) {
						new JOptionPane().showMessageDialog(null, "Kitap yok. Eklenemedi");
					} else {
						new JOptionPane().showMessageDialog(null, " Eklenemedi.");
					}
				}
			} else {
				new JOptionPane().showMessageDialog(null, "Sepette kitap yok.");
			}

		}
	};

	private ActionListener SepetEkle = new ActionListener() {
		@Override
		// Þurda çok absürt bi fantezi var ona sonra dönecem ... (sayi güncellemek için
		// fonksiyon gerekecek.)
		public void actionPerformed(ActionEvent arg0) {
			int i = kitapTable.getSelectedRow();
			if (i >= 0 && KitapLimitLabel != null) {
				if (KitapLimit-- > 0) {
					KitapLimitLabel.setText("Kitap Limit : " + KitapLimit);
					int id = Fonksiyonlar.IdGetir(kitapTable, i);
					dbClasslar.kitap kitap = DiziDegiskenler.KitapListMap.get(id);
					dbClasslar.yazar yazar = DiziDegiskenler.YazarListMap.get(kitap.getY_id());
					Fonksiyonlar.JTableVeriEkleme(sepetTable, kitap.getK_id(), kitap.getK_ad(), yazar.getY_ad());
				} else {
					KitapLimit = 0;
					new JOptionPane().showMessageDialog(null, "Kitap Limit Geçildi.Daha Fazla Kitap Veremezsin.");
				}
			} else if (KitapLimitLabel == null) {
				new JOptionPane().showMessageDialog(null, "Kitap Limit Görüntülenemedi.");
			} else {
				new JOptionPane().showMessageDialog(null, "Kitap seçmediniz.");
			}
		}
	};

	private void Ara() {
		Fonksiyonlar.JTableVeriTemizleme(kitapTable);
		dbClasslar.kitap.kitapDBList();
		dbClasslar.yazar.yazarDBList();
		Object[] obj = DiziDegiskenler.KitapListMap.values().toArray();
		for (int i = 0; i < obj.length; i++) {
			String aranan = kitapArama.getText().toLowerCase();
			dbClasslar.kitap kitap = (dbClasslar.kitap) obj[i];
			if (kitap.getK_ad().toLowerCase().contains(aranan)) {
				dbClasslar.yazar yazar = DiziDegiskenler.YazarListMap.get(kitap.getY_id());
				Fonksiyonlar.JTableVeriEkleme(kitapTable, kitap.getK_id(), kitap.getK_ad(), yazar.getY_ad(),
						kitap.getK_raf() + kitap.getK_depo());
			}
		}
	}

	private ActionListener Arama = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			Ara();
		}
	};

	private ActionListener Yenile = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			Yenile();
		}
	};
	
	private void Yükle() {

		Fonksiyonlar.JTattooYukle();

		frame = Fonksiyonlar.AnaFormOlustur(100, 100, 550, 800, "Kitap Ödünç Verme");

		kitapTable = Fonksiyonlar.JTableOlustur(DiziDegiskenler.KitapOduncListe);
		sepetTable = Fonksiyonlar.JTableOlustur(DiziDegiskenler.KitapOduncSepetListe);
		kitapGecmisTable = Fonksiyonlar.JTableOlustur(DiziDegiskenler.KitapOduncGecmisListe);

		dbClasslar.kitap.kitapDBList();
		dbClasslar.yazar.yazarDBList();
		dbClasslar.uyetipi.uyetipiDBList();
		uyetipi = DiziDegiskenler.UyeTipiListMap.get(DiziOlmayanDegiskenler.UyeOduncIade.getUt_id());
		KitapLimit = uyetipi.getUt_kitap_limit();

		JLabel adSoyadLabel = varsayilan.Componentler.label("Adý Soyadý :", 20, 10, 120, 25);

		JLabel uyeAdSoyadLabel = varsayilan.Componentler.label(DiziOlmayanDegiskenler.UyeOduncIade.toString(), 130, 10,
				290, 25);

		KitapLimitLabel = varsayilan.Componentler.label("Kitap Limit : " + KitapLimit, 385, 10, 165, 25);

		JLabel lblkitapArama = varsayilan.Componentler.label("Kitap Arama :", 20, 50, 290, 16);

		kitapArama = varsayilan.Componentler.jtextfield("", 130, 50, 190, 22);
		kitapArama.addActionListener(Arama);

		
		JButton btnAra = Butonlar.Ara(330, 50, 70, 25);
		btnAra.addActionListener(Arama);
		
		JButton btnYenile = Butonlar.Yenile(410, 50, 100, 25);
		btnYenile.addActionListener(Yenile);

		JLabel kitaplar = varsayilan.Componentler.label("Kitaplar", 20, 90, 120, 16);

		JButton btnSepetEkle = Butonlar.sepetEkle(205, 350, 120, 25);
		btnSepetEkle.addActionListener(SepetEkle);

		pane1 = new JScrollPane(kitapTable);
		pane1.setBounds(20, 110, 500, 220);

		JLabel kitapGecmis = varsayilan.Componentler.label("Kitap Geçmiþi", 20, 390, 120, 20);

		pane3 = new JScrollPane(kitapGecmisTable);
		pane3.setBounds(20, 420, 235, 265);

		JLabel sepet = varsayilan.Componentler.label("Sepet", 265, 390, 120, 20);

		pane2 = new JScrollPane(sepetTable);
		pane2.setBounds(265, 420, 235, 265);

		JButton btnSepetKaldir = Butonlar.sepetKaldir(335, 715, 120, 25);
		btnSepetKaldir.addActionListener(SepettenÇýkar);

		JButton btnIade = Butonlar.iade(85, 715, 97, 25);
		btnIade.addActionListener(IadeEt);

		JButton btnOdunc = Butonlar.odunc(215, 715, 97, 25);
		btnOdunc.addActionListener(OduncVer);

		Fonksiyonlar.frameElementEkle(frame, btnAra, adSoyadLabel, uyeAdSoyadLabel, lblkitapArama, kitapArama, kitaplar,
				btnSepetEkle, btnYenile,btnSepetKaldir, kitapGecmis, sepet, btnIade, btnOdunc, KitapLimitLabel, pane1, pane2,
				pane3);

		oduncListele();
	}
}
