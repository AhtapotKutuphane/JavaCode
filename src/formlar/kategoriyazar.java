package formlar;

import javax.swing.JFrame;
import globalFonksiyonlar.Fonksiyonlar;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import DatabaseFonksiyonlar.IdAl;
import globalDegiskenler.DiziDegiskenler;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

/**
 * 
 * @author Alican
 *
 */
public class kategoriyazar {

	private JFrame frame;
	private int id = -1;
	private String kim = "";
	private boolean boolKitaptan = false;
	JTextField jtfAd;
	private String Adý = "";
	dbClasslar.yazar yazar = new dbClasslar.yazar();
	dbClasslar.kategori kategori = new dbClasslar.kategori();

	public kategoriyazar(String str, int id, String kim) {
		this.id = id;
		this.kim = kim;
		if (kim.equals("Kitap")) {
			boolKitaptan = true;
			this.kim = "Yazar";
		}
		Yükle(str);
	}

	public JFrame formAl() {
		return frame;
	}

	private boolean Kontroller() {
		Adý = jtfAd.getText().trim();
		if (Fonksiyonlar.BosMu(Adý)) {
			return true;
		} else {
			new JOptionPane().showMessageDialog(null, "Boþ býrakýlmýþ veri var.");
			return false;
		}
	}

	public ActionListener Kaydet = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (Kontroller()) {
				if (kim.equals("Yazar")) {
					yazar = new dbClasslar.yazar(yazar.getY_id(), Adý);
					if (yazar.yazarDBGüncelle()) {
						DiziDegiskenler.YazarListMap.put(yazar.getY_id(), yazar.yazarNewVerisiGetir());
						yazarListele.Listele();
						new JOptionPane().showMessageDialog(null, "Güncellendi.");
					} else {
						new JOptionPane().showMessageDialog(null, "Güncellenemedi.");
					}
				} else if (kim.equals("Kategori")) {
					kategori = new dbClasslar.kategori(kategori.getKat_id(), Adý);
					if (kategori.kategoriDBGüncelle()) {
						DiziDegiskenler.KategoriListMap.put(kategori.getKat_id(), kategori.kategoriNewVerisiGetir());
						kategoriListele.Listele();
						new JOptionPane().showMessageDialog(null, "Güncellendi.");
					} else {
						new JOptionPane().showMessageDialog(null, "Güncellenemedi.");
					}
				}
			}
		}
	};

	public ActionListener Ekle = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (Kontroller()) {
				if (kim.equals("Yazar")) {
					yazar = new dbClasslar.yazar(IdAl.YazarIdAl() + 1, Adý);
					if (yazar.yazarDBEkle()) {
						DiziDegiskenler.YazarListMap.put(yazar.getY_id(), yazar.yazarNewVerisiGetir());
						if (!boolKitaptan) {
							yazarListele.YazarEkle();
						}
						new JOptionPane().showMessageDialog(null, "Eklendi.");
					} else {
						new JOptionPane().showMessageDialog(null, "Eklenemedi.");
					}
				} else if (kim.equals("Kategori")) {
					kategori = new dbClasslar.kategori(IdAl.KategoriIdAl()+1, Adý);
					if (kategori.kategoriDBEkle()) {
						DiziDegiskenler.KategoriListMap.put(kategori.getKat_id(), kategori.kategoriNewVerisiGetir());
						kategoriListele.KategoriEkle();
						new JOptionPane().showMessageDialog(null, "Eklendi.");
					} else {
						new JOptionPane().showMessageDialog(null, "Eklenemedi.");
					}
				}
			}
		}
	};

	private void Yükle(String str) {

		Fonksiyonlar.JTattooYukle();

		String JTextFieldTexts = "";
		if (id != -1) {
			if (kim.equals("Kategori")) {
				kategori = DiziDegiskenler.KategoriListMap.get(id);
				JTextFieldTexts = kategori.getKat_ad();
			} else if (kim.equals("Yazar")) {
				yazar = DiziDegiskenler.YazarListMap.get(id);
				JTextFieldTexts = yazar.getY_ad();
			}
		}
		frame = Fonksiyonlar.AnaFormOlustur(100, 100, 390, 140, kim + " " + str);

		JButton btnKaydet = null;
		if (id == -1) {
			btnKaydet = varsayilan.Butonlar.Ekle(160, 70, 97, 25);
			btnKaydet.addActionListener(Ekle);
		} else {
			btnKaydet = varsayilan.Butonlar.Kaydet(160, 70, 97, 25);
			btnKaydet.addActionListener(Kaydet);
		}

		JButton btnGeri = varsayilan.Butonlar.Geri(257, 70, 97, 25);
		btnGeri.addActionListener(globalFonksiyonlar.Eventler.FormKapatGeriDonActionListener);

		JLabel lblAd = varsayilan.Componentler.label(kim + " Adý :", 30, 35, 120, 25);

		jtfAd = varsayilan.Componentler.jtextfield(JTextFieldTexts, 160, 35, 195, 25);

		Fonksiyonlar.frameElementEkle(frame,jtfAd, btnKaydet, btnGeri, lblAd);

	}
}
