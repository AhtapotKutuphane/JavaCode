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
public class yazarListele {

	private JFrame frame;
	private static JTable tableYazar;
	private JTextField ara;

	public yazarListele() {
		Yükle();
	}

	public JFrame formAl() {
		return frame;
	};

	public static void Listele() {
		dbClasslar.yazar.yazarDBList();
		tableYazar = Fonksiyonlar.JTableVeriTemizleme(tableYazar);
		Object[] obj = DiziDegiskenler.YazarListMap.values().toArray();
		for (int i = 0; i < obj.length; i++) {
			dbClasslar.yazar yazar = (dbClasslar.yazar) obj[i];
			if (yazar.getSilindimi().equals("h")) {
				yazar.setToplam(DBKontroller.KacKitapVarBuYazarda(yazar.getY_id()));
				tableYazar = Fonksiyonlar.JTableVeriEkleme(tableYazar, yazar.yazarGetModel());
			}
		}
	}

	public static void YazarEkle() {
		Listele();
		for (int i = 0; i < DiziDegiskenler.TempArray.size(); i++) {
			Fonksiyonlar.JTableVeriEkleme(tableYazar, DiziDegiskenler.TempArray.get(i));
		}
		DiziDegiskenler.TempArray = new ArrayList<>();
	}

	private ActionListener Ekle = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			new formlar.kategoriyazar("Ekle", -1, "Yazar").formAl().setVisible(true);
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
			if (tableYazar.getSelectedRow() >= 0) {
				int id = Fonksiyonlar.IdGetir(tableYazar, tableYazar.getSelectedRow());
				new formlar.kategoriyazar("Güncelleme", id, "Yazar").formAl().setVisible(true);
			}
		}
	};

	private ActionListener Sil = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (tableYazar.getSelectedRow() >= 0) {
				int id = Fonksiyonlar.IdGetir(tableYazar, tableYazar.getSelectedRow());
				tableYazar = globalFonksiyonlar.Fonksiyonlar.JTableVeriSilme(tableYazar, tableYazar.getSelectedRow());
				dbClasslar.yazar yazar = (dbClasslar.yazar) DiziDegiskenler.YazarListMap.get(id);
				yazar.yazarDBSil();
				DiziDegiskenler.YazarListMap.remove(id);
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
		tableYazar = Fonksiyonlar.JTableVeriTemizleme(tableYazar);
		Object[] obj = DiziDegiskenler.YazarListMap.values().toArray();
		for (int i = 0; i < obj.length; i++) {
			dbClasslar.yazar yazar = (dbClasslar.yazar) obj[i];
			if (yazar.getY_ad().toLowerCase().contains(aranan_veri)) {
				tableYazar = Fonksiyonlar.JTableVeriEkleme(tableYazar, yazar.yazarGetModel());
			}
		}
	}

	private void Yükle() {
		Fonksiyonlar.JTattooYukle();

		frame = Fonksiyonlar.AnaFormOlustur(100, 100, 950, 450, "Yazar Listeleme");
		tableYazar = Fonksiyonlar.JTableOlustur(DiziDegiskenler.YazarListeleme);

		JScrollPane paneYazar = new JScrollPane(tableYazar);
		paneYazar.setBounds(30, 15, 930, 300);

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

		Fonksiyonlar.frameElementEkle(frame, ara, btnYenile, btnAra, btnSil, btnEkle, btnDuzenle, paneYazar, btnGeri);

	}
}
