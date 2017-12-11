package formlar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import globalDegiskenler.DiziDegiskenler;
import globalDegiskenler.DiziOlmayanDegiskenler;
import globalDegiskenler.Iconlar;
import globalFonksiyonlar.Fonksiyonlar;

public class talepListele {

	private static JTextField txtAra = null;
	private JFrame frame;
	private static JTable table;

	public talepListele() {

		Yükle();
	}

	public JFrame formAl() {
		return frame;
	}

	/**
	 * Test Ederken ürettim olasý deðiþme ihtimali var.
	 */
	private WindowFocusListener test = new WindowFocusListener() {

		@Override
		public void windowLostFocus(WindowEvent e) {
			//

		}

		@Override
		public void windowGainedFocus(WindowEvent e) {

			if (!DiziOlmayanDegiskenler.AramaMý) {
				Listele();
			}

		}
	};

	private ActionListener ListeYenile = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {

			txtAra.setText("");
			DiziOlmayanDegiskenler.AramaMý = false;
			Listele();
		}
	};

	private ActionListener SiparisVer = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			int i = table.getSelectedRow();
			if (i >= 0) {
				int id = Fonksiyonlar.IdGetir(table, i);
				dbClasslar.talep talep = (dbClasslar.talep) DiziDegiskenler.TalepListMap.get(id);
				if (talep.getD_id() == 2) {
					new siparis("Ekle", id, "Talep").formAl().setVisible(true);
				} else {
					new JOptionPane().showMessageDialog(null, "Onaylanmýþ talebi seçmediniz.");
				}
			} else {
				new JOptionPane().showMessageDialog(null, "Sipariþ verilecek veriyi seçmediniz.");
			}
		}
	};
	private static ActionListener Ara = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (txtAra != null && !txtAra.getText().equals("")) {
				DiziOlmayanDegiskenler.AramaMý = true;
				AramaYap(txtAra.getText());
			} else if (txtAra.getText().equals("")) {
				DiziOlmayanDegiskenler.AramaMý = false;
				Listele();
			}

		}
	};

	public static void Listele() {
		dbClasslar.talep.talepDBList();
		table = Fonksiyonlar.JTableVeriTemizleme(table);
		Object[] obj = DiziDegiskenler.TalepListMap.values().toArray();
		for (int i = 0; i < obj.length; i++) {
			dbClasslar.talep talep = (dbClasslar.talep) obj[i];
			table = Fonksiyonlar.JTableVeriEkleme(table, talep.talepGetModel());
		}
	}

	private static void AramaYap(String aranan_veri) {
		aranan_veri = aranan_veri.toLowerCase();
		table = Fonksiyonlar.JTableVeriTemizleme(table);
		Object[] obj = DiziDegiskenler.TalepListMap.values().toArray();
		for (int i = 0; i < obj.length; i++) {
			dbClasslar.talep talep = (dbClasslar.talep) obj[i];
			dbClasslar.kitap kitap = DiziDegiskenler.KitapListMap.get(talep.getK_id());
			dbClasslar.durum durum = DiziDegiskenler.DurumListMap.get(talep.getD_id());
			dbClasslar.uyeler uyeler = DiziDegiskenler.UyeListMap.get(talep.getU_id());
			if (uyeler.toString().toLowerCase().contains(aranan_veri)
					|| kitap.getK_ad().toLowerCase().contains(aranan_veri)
					|| durum.getD_ad().toLowerCase().contains(aranan_veri)) {
				table = Fonksiyonlar.JTableVeriEkleme(table, talep.talepGetModel());
			}
		}
	}

	public static void EklemeYap() {
		if (DiziDegiskenler.TempArray != null) {
			for (int i = 0; i < DiziDegiskenler.TempArray.size(); i++) {
				table = Fonksiyonlar.JTableVeriEkleme(table, DiziDegiskenler.TempArray.get(i));
			}
			DiziDegiskenler.TempArray = new ArrayList<>();
		}
	}

	private ActionListener Ekle = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			new talep("Ekleme", -1).formAl().setVisible(true);
		}
	};

	/**
	 * Bu Böyle kalmayacak deðiþecek büyük ihtimalle ama biþiler kurcalarken böyle
	 * yaptým deðiþtirmedim...
	 * 
	 * @param frame
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @return
	 */
	private JFrame componentUretme(JFrame frame, int x, int y, int width, int height) {
		txtAra = varsayilan.Componentler.jtextfield("", 10, y, 200, height);
		txtAra.addActionListener(Ara);

		JButton btnAra = varsayilan.Butonlar.Ara(215, y, 85, height);
		btnAra.addActionListener(Ara);

		JButton btnGeri = varsayilan.Butonlar.Geri(1180, y, 95, height);
		btnGeri.addActionListener(globalFonksiyonlar.Eventler.AnasayfaGeriDonActionListener);

		JButton btnEkle = varsayilan.Butonlar.Ekle(870, y, 90, height);
		btnEkle.addActionListener(Ekle);

		JButton btnYenile = varsayilan.Butonlar.Yenile(770, y, 90, height);
		btnYenile.addActionListener(ListeYenile);

		JButton btnSiparisVer = null;
		if (DiziOlmayanDegiskenler.Yetki.equals("Yönetici")) {
			btnSiparisVer = varsayilan.Componentler.jbutton("Sipariþ Ver", new ImageIcon(Iconlar.SiparisEkletusux16),
					640, y, 120, height);
			btnSiparisVer.addActionListener(SiparisVer);
		}

		JButton btnDzenle = varsayilan.Butonlar.Duzenle(970, y, 100, height);
		btnDzenle.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int i = table.getSelectedRow();
				if (i >= 0) {
					int id = Fonksiyonlar.IdGetir(table, i);
					new talep("Güncelleme", id).formAl().setVisible(true);
				} else {
					new JOptionPane().showMessageDialog(null, "Düzenlenecek veriyi seçmediniz.");
				}
			}
		});

		JButton btnSil = varsayilan.Butonlar.Sil(1080, y, 95, height);
		btnSil.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int i = table.getSelectedRow();
				if (i >= 0) {
					int id = Fonksiyonlar.IdGetir(table, i);
					table = globalFonksiyonlar.Fonksiyonlar.JTableVeriSilme(table, i);
					dbClasslar.talep talep = (dbClasslar.talep) DiziDegiskenler.TalepListMap.get(id);
					talep.talepDBSil();
					DiziDegiskenler.TalepListMap.remove(id);
				} else {
					new JOptionPane().showMessageDialog(null, "Silinecek veriyi seçmediniz.");
				}
			}
		});

		if (btnSiparisVer != null) {
			Fonksiyonlar.frameElementEkle(frame, btnSiparisVer, btnDzenle, btnSil, btnGeri, btnEkle, txtAra, btnAra,
					btnYenile);
		}
		else if (btnSiparisVer == null) {
			Fonksiyonlar.frameElementEkle(frame, btnDzenle, btnSil, btnGeri, btnEkle, txtAra, btnAra,
					btnYenile);
		}
		return frame;
	}

	private void Yükle() {
		Fonksiyonlar.JTattooYukle();

		dbClasslar.kitap.kitapDBList();
		dbClasslar.durum.durumDBList();
		dbClasslar.uyeler.uyeDBList();

		frame = Fonksiyonlar.AnaFormOlustur(100, 100, 1300, 500, "Talep Listeleme");

		table = Fonksiyonlar.JTableOlustur(DiziDegiskenler.TalepListeleme);

		JScrollPane pane = new JScrollPane(table);
		pane.setBounds(0, 0, 1290, 400);

		frame.addWindowFocusListener(test);

		Fonksiyonlar.frameElementEkle(componentUretme(frame, 900, 420, 87, 25), pane);

	}
}
