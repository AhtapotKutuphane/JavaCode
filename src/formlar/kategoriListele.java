package formlar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import DatabaseFonksiyonlar.DBKontroller;
import globalDegiskenler.DiziDegiskenler;
import globalDegiskenler.DiziOlmayanDegiskenler;
import globalFonksiyonlar.Fonksiyonlar;
import varsayilan.Butonlar;

/**
 * 
 * @author Alican
 *
 */
public class kategoriListele {

	private JFrame frame;
	private static JTable tableKategori;
	private JTextField ara;

	public kategoriListele() {
		Yükle();
	}

	public JFrame formAl() {
		return frame;
	};

	public static void Listele() {
		dbClasslar.kategori.kategoriDBList();
		tableKategori = Fonksiyonlar.JTableVeriTemizleme(tableKategori);
		Object[] obj = DiziDegiskenler.KategoriListMap.values().toArray();
		for (int i = 0; i < obj.length; i++) {
			dbClasslar.kategori kategori = (dbClasslar.kategori) obj[i];
			if (kategori.getSilindimi().equals("h")) {
				kategori.setToplam(DBKontroller.KacKitapVarBuKategoride(kategori.getKat_id()));
				tableKategori = Fonksiyonlar.JTableVeriEkleme(tableKategori, kategori.kategoriGetModel());
			}
		}
	}

	public static void KategoriEkle() {
		Listele();
		for (int i = 0; i < DiziDegiskenler.TempArray.size(); i++) {
			Fonksiyonlar.JTableVeriEkleme(tableKategori, DiziDegiskenler.TempArray.get(i));
		}
		DiziDegiskenler.TempArray = new ArrayList<>();
	}

	private ActionListener Ekle = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			new formlar.kategoriyazar("Ekle", -1, "Kategori").formAl().setVisible(true);
		}
	};

	private ActionListener Yenile = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			ara.setText("");
			DiziOlmayanDegiskenler.AramaMý = false;
			Listele();
		}
	};

	private ActionListener Guncelle = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (tableKategori.getSelectedRow() >= 0) {
				int id = Fonksiyonlar.IdGetir(tableKategori, tableKategori.getSelectedRow());
				new formlar.kategoriyazar("Güncelleme", id, "Kategori").formAl().setVisible(true);
			}
		}
	};

	private ActionListener Sil = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (tableKategori.getSelectedRow() >= 0) {
				int id = Fonksiyonlar.IdGetir(tableKategori, tableKategori.getSelectedRow());
				tableKategori = globalFonksiyonlar.Fonksiyonlar.JTableVeriSilme(tableKategori,
						tableKategori.getSelectedRow());
				dbClasslar.kategori kategori = (dbClasslar.kategori) DiziDegiskenler.KategoriListMap.get(id);
				kategori.kategoriDBSil();
				DiziDegiskenler.KategoriListMap.remove(id);
			} else {
				new JOptionPane().showMessageDialog(null, "Silinecek veriyi seçmediniz.");
			}
		}
	};

	private ActionListener Search = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (ara != null && !ara.getText().equals("")) {
				DiziOlmayanDegiskenler.AramaMý = true;
				AramaYap(ara.getText());
			} else if (ara.getText().equals("")) {
				DiziOlmayanDegiskenler.AramaMý = false;
				Listele();
			}
		}
	};

	private static void AramaYap(String aranan_veri) {
		aranan_veri = aranan_veri.toLowerCase();
		tableKategori = Fonksiyonlar.JTableVeriTemizleme(tableKategori);
		Object[] obj = DiziDegiskenler.KategoriListMap.values().toArray();
		for (int i = 0; i < obj.length; i++) {
			dbClasslar.kategori kategori = (dbClasslar.kategori) obj[i];
			if (kategori.getSilindimi().equals("h")) {
				if (kategori.getKat_ad().toLowerCase().contains(aranan_veri)) {
					tableKategori = Fonksiyonlar.JTableVeriEkleme(tableKategori, kategori.kategoriGetModel());
				}
			}
		}
	}

	private void Yükle() {
		Fonksiyonlar.JTattooYukle();

		frame = Fonksiyonlar.AnaFormOlustur(100, 100, 950, 450, "Kategori");
		tableKategori = Fonksiyonlar.JTableOlustur(DiziDegiskenler.KategoriListeleme);

		JScrollPane paneKategori = new JScrollPane(tableKategori);
		paneKategori.setBounds(30, 15, 930, 300);

		Listele();

		ara = varsayilan.Componentler.jtextfield("", 40, 375, 170, 25);
		ara.addActionListener(Search);

		JButton btnAra = Butonlar.Ara(220, 375, 70, 25);
		btnAra.addActionListener(Search);

		JButton btnYenile = Butonlar.Yenile(425, 375, 95, 25);
		btnYenile.addActionListener(Yenile);

		JButton btnEkle = Butonlar.Ekle(525, 375, 95, 25);
		btnEkle.addActionListener(Ekle);

		JButton btnDuzenle = Butonlar.Duzenle(625, 375, 100, 25);
		btnDuzenle.addActionListener(Guncelle);

		JButton btnSil = Butonlar.Sil(730, 375, 90, 25);
		btnSil.addActionListener(Sil);

		JButton btnGeri = Butonlar.Geri(825, 375, 95, 25);
		btnGeri.addActionListener(globalFonksiyonlar.Eventler.AnasayfaGeriDonActionListener);

		Fonksiyonlar.frameElementEkle(frame, ara, btnYenile, btnAra, btnSil, btnEkle, btnDuzenle, paneKategori,
				btnGeri);

	}
}
