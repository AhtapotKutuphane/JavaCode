package formlar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import globalDegiskenler.DiziDegiskenler;
import globalDegiskenler.DiziOlmayanDegiskenler;
import globalFonksiyonlar.Fonksiyonlar;

public class siparisListele {

	private static JTextField txtAra = null;
	private JFrame frame;
	private static JTable table;

	public siparisListele() {

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

	public static void Listele() {
		dbClasslar.siparis.siparisDBList();
		table = Fonksiyonlar.JTableVeriTemizleme(table);
		Object[] obj = DiziDegiskenler.SiparisListMap.values().toArray();
		for (int i = 0; i < obj.length; i++) {
			dbClasslar.siparis siparis = (dbClasslar.siparis) obj[i];
			table = Fonksiyonlar.JTableVeriEkleme(table, siparis.siparisGetModel());
		}
	}

	private static void AramaYap(String aranan_veri) {
		aranan_veri = aranan_veri.toLowerCase();
		table = Fonksiyonlar.JTableVeriTemizleme(table);
		Object[] obj = DiziDegiskenler.SiparisListMap.values().toArray();
		for (int i = 0; i < obj.length; i++) {
			dbClasslar.siparis siparis = (dbClasslar.siparis) obj[i];
			dbClasslar.kitap kitap = DiziDegiskenler.KitapListMap.get(siparis.getK_id());
			dbClasslar.durum durum = DiziDegiskenler.DurumListMap.get(siparis.getD_id());
			dbClasslar.tedarikci tedarikci = DiziDegiskenler.TedarikciListMap.get(siparis.getT_id());
			if (tedarikci.getT_ad().toLowerCase().contains(aranan_veri) || kitap.getK_ad().toLowerCase().contains(aranan_veri) || durum.getD_ad().toLowerCase().contains(aranan_veri)) {
				table = Fonksiyonlar.JTableVeriEkleme(table, siparis.siparisGetModel());
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
			new siparis("Ekleme",-1,"Siparis").formAl().setVisible(true);
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

		JButton btnDzenle = varsayilan.Butonlar.Duzenle(970, y, 100, height);
		btnDzenle.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int i = table.getSelectedRow();
				if (i >= 0) {
					int id = Fonksiyonlar.IdGetir(table, i);
					new siparis("Güncelleme", id,"Siparis").formAl().setVisible(true);
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
					dbClasslar.siparis siparis= (dbClasslar.siparis) DiziDegiskenler.SiparisListMap.get(id);
					siparis.siparisDBSil();
					DiziDegiskenler.SiparisListMap.remove(id);
				} else {
					new JOptionPane().showMessageDialog(null, "Silinecek veriyi seçmediniz.");
				}
			}
		});

		Fonksiyonlar.frameElementEkle(frame, btnDzenle, btnSil, btnGeri, btnEkle, txtAra, btnAra,btnYenile);
		return frame;
	}

	private void Yükle() {
		Fonksiyonlar.JTattooYukle();


		dbClasslar.kitap.kitapDBList();
		dbClasslar.durum.durumDBList();
		dbClasslar.tedarikci.tedarikciDBList();

		frame = Fonksiyonlar.AnaFormOlustur(100, 100, 1300, 500, "Sipariþ Listeleme");

		table = Fonksiyonlar.JTableOlustur(DiziDegiskenler.SiparisListeleme);

		JScrollPane pane = new JScrollPane(table);
		pane.setBounds(0, 0, 1290, 400);

		frame.addWindowFocusListener(test);

		Fonksiyonlar.frameElementEkle(componentUretme(frame, 900, 420, 87, 25), pane);

	}
}
