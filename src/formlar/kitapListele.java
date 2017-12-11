package formlar;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import globalFonksiyonlar.Fonksiyonlar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import globalDegiskenler.DiziDegiskenler;
import globalDegiskenler.DiziOlmayanDegiskenler;
import globalDegiskenler.Iconlar;

public class kitapListele {

	private static JTextField txtAra = null;
	private JFrame frame;
	private static JTable table;

	public kitapListele() {

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

	// Güncelledikten sonra gelmesi için bunu public yaptýk..

	public static void Listele() {
		dbClasslar.kitap.kitapDBList();
		table = Fonksiyonlar.JTableVeriTemizleme(table);
		Object[] obj = DiziDegiskenler.KitapListMap.values().toArray();
		for (int i = 0; i < obj.length; i++) {
			dbClasslar.kitap kitap = (dbClasslar.kitap) obj[i];
			if (kitap.getSilindimi().equals("h")) {
				table = Fonksiyonlar.JTableVeriEkleme(table, kitap.kitapGetModel());
			}
		}
	}

	private static void AramaYap(String aranan_veri) {
		aranan_veri = aranan_veri.toLowerCase();
		table = Fonksiyonlar.JTableVeriTemizleme(table);
		Object[] obj = DiziDegiskenler.KitapListMap.values().toArray();
		for (int i = 0; i < obj.length; i++) {
			dbClasslar.kitap kitap = (dbClasslar.kitap) obj[i];
			dbClasslar.yazar yazar = DiziDegiskenler.YazarListMap.get(kitap.getY_id());
			dbClasslar.kategori kategori = DiziDegiskenler.KategoriListMap.get(kitap.getKat_id());
			if (kitap.getSilindimi().equals("h")) {
				if (kitap.getK_ad().toLowerCase().contains(aranan_veri)
						|| yazar.getY_ad().toLowerCase().contains(aranan_veri)
						|| kategori.getKat_ad().toLowerCase().contains(aranan_veri)) {
					table = Fonksiyonlar.JTableVeriEkleme(table, kitap.kitapGetModel());
				}
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
			new kitap("Ekleme", -1).formAl().setVisible(true);
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

		JButton btnYazarEkle = varsayilan.Componentler.jbutton("Yazar Ekle", new ImageIcon(Iconlar.Ekletusux16), 750, y,
				115, height);
		btnYazarEkle.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new formlar.kategoriyazar("Ekle", -1, "Kitap").formAl().setVisible(true);
			}
		});

		JButton btnYenile = varsayilan.Butonlar.Yenile(650, y, 90, height);
		btnYenile.addActionListener(ListeYenile);

		JButton btnDzenle = varsayilan.Butonlar.Duzenle(970, y, 100, height);
		btnDzenle.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int i = table.getSelectedRow();
				if (i >= 0) {
					int id = Fonksiyonlar.IdGetir(table, i);
					new kitap("Güncelleme", id).formAl().setVisible(true);
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
					dbClasslar.kitap kitap = (dbClasslar.kitap) DiziDegiskenler.KitapListMap.get(id);
					kitap.kitapDBSil();
					DiziDegiskenler.KitapListMap.remove(id);
				} else {
					new JOptionPane().showMessageDialog(null, "Silinecek veriyi seçmediniz.");
				}
			}
		});

		Fonksiyonlar.frameElementEkle(frame, btnYazarEkle, btnDzenle, btnSil, btnGeri, btnEkle, txtAra, btnAra,
				btnYenile);
		return frame;
	}

	private void Yükle() {
		Fonksiyonlar.JTattooYukle();

		dbClasslar.kitap.kitapDBList();
		dbClasslar.yazar.yazarDBList();
		dbClasslar.kategori.kategoriDBList();

		frame = Fonksiyonlar.AnaFormOlustur(100, 100, 1300, 500, "Kitap Listeleme");

		table = Fonksiyonlar.JTableOlustur(DiziDegiskenler.KitapListeleme);

		JScrollPane pane = new JScrollPane(table);
		pane.setBounds(0, 0, 1290, 400);

		frame.addWindowFocusListener(test);

		Fonksiyonlar.frameElementEkle(componentUretme(frame, 900, 420, 87, 25), pane);

	}
}
