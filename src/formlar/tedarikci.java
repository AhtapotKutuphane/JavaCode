package formlar;

import javax.swing.JFrame;
import globalFonksiyonlar.Fonksiyonlar;
import javax.swing.JTextField;
import DatabaseFonksiyonlar.IdAl;
import globalDegiskenler.DiziDegiskenler;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 * 
 * @author Alican
 *
 */
public class tedarikci {

	private JFrame frame;
	private JTextField jtfAd;
	private JTextField jtfTelefon;
	private JTextField jtfEMail;
	private TextField textfieldAdres;
	private int id = -1;
	private String tedarikciAdý = "";
	dbClasslar.tedarikci tedarikci = new dbClasslar.tedarikci();

	public tedarikci(String str, int id) {
		this.id = id;
		Yükle(str);
	}

	public JFrame formAl() {
		return frame;
	}

	private boolean Kontroller() {
		tedarikciAdý = jtfAd.getText().trim();
		if (Fonksiyonlar.BosMu(tedarikciAdý, jtfTelefon.getText(), jtfEMail.getText(), textfieldAdres.getText())) {

			int rakam = 11;
			if (Fonksiyonlar.RakamKadarHaneMi(rakam, jtfTelefon.getText())) {
					return true;
				} else if (id != -1) {
					// güncelleme ekranýndaki yazar kitap adý iliþki olayýný çözmediðimiz sürece
					// true kalmasý gerekiyor.
					// new JOptionPane().showMessageDialog(null, "Ayný yazarýn böyle bir kitabý var.
					// ");
					return true;
				} else {
					new JOptionPane().showMessageDialog(null, "Girdiðiniz rakam " + rakam + " hane deðil. (Telefon)");
					return false;
				}
		} else {
			new JOptionPane().showMessageDialog(null, "Boþ býrakýlmýþ veri var.");
			return false;
		}

	}

	public ActionListener Kaydet = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (Kontroller()) {
				tedarikci = new dbClasslar.tedarikci(tedarikci.getT_id(), tedarikciAdý, jtfTelefon.getText(), jtfEMail.getText(),textfieldAdres.getText(),tedarikci.getSilindimi());

				if (tedarikci.tedarikciDBGüncelle()) {
					DiziDegiskenler.TedarikciListMap.replace(tedarikci.getT_id(), tedarikci.tedarikciNewVerisiGetir());
					tedarikciListele.Listele();
					new JOptionPane().showMessageDialog(null, "Güncellendi.");
				}
				else {
					new JOptionPane().showMessageDialog(null, "Güncellenmedi.");
				}
			}
		}
	};

	public ActionListener Ekle = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (Kontroller()) {
				tedarikci = new dbClasslar.tedarikci(IdAl.TedarikciIdAl()+1, tedarikciAdý, jtfTelefon.getText(), jtfEMail.getText(),textfieldAdres.getText(),"h");

				if (tedarikci.tedarikciDBEkle()) {
					DiziDegiskenler.TedarikciListMap.replace(tedarikci.getT_id(), tedarikci.tedarikciNewVerisiGetir());
					tedarikciListele.Listele();
					new JOptionPane().showMessageDialog(null, "Eklendi.");
				}
				else {
					new JOptionPane().showMessageDialog(null, "Eklenemedi.");
				}
			}
		}
	};
	
	private void Yükle(String str) {
		Fonksiyonlar.JTattooYukle();

		String[] JTextFieldTexts = { "", "", "", ""};
		if (id != -1) {
			tedarikci = (dbClasslar.tedarikci) DiziDegiskenler.TedarikciListMap.get(id);
			JTextFieldTexts[0] = tedarikci.getT_ad();
			JTextFieldTexts[1] = tedarikci.getT_telefon();
			JTextFieldTexts[2] = tedarikci.getT_email();
			JTextFieldTexts[3] = tedarikci.getT_adres();
		}

		frame = Fonksiyonlar.AnaFormOlustur(100, 100, 450, 348, "Tedarikçi "+str);

		JLabel lblAd = varsayilan.Componentler.label("Ad :", 23, 16, 56, 16);

		jtfAd = varsayilan.Componentler.jtextfield(JTextFieldTexts[0], 166, 13, 205, 22);

		JLabel lblCepTelefonu = varsayilan.Componentler.label("Cep Telefonu :", 23, 51, 131, 16);

		jtfTelefon = varsayilan.Componentler.jtextfield(JTextFieldTexts[1], 166, 48, 205, 22);

		JLabel lblEmail = varsayilan.Componentler.label("E-mail :", 23, 86, 99, 16);

		jtfEMail = varsayilan.Componentler.jtextfield(JTextFieldTexts[2], 166, 83, 205, 22);

		JLabel lblAdres = varsayilan.Componentler.label("Adres :", 23, 120, 85, 16);
		
		textfieldAdres = varsayilan.Componentler.textfield(JTextFieldTexts[3], 166, 123, 205, 101);

		JButton btnKaydet = null;
		if (id == -1) {
			btnKaydet = varsayilan.Butonlar.Ekle(166, 237, 97, 25);
			btnKaydet.addActionListener(Ekle);
		} else {
			btnKaydet = varsayilan.Butonlar.Kaydet(166, 237, 97, 25);
			btnKaydet.addActionListener(Kaydet);
		}

		JButton btnGeri = varsayilan.Butonlar.Geri(274, 237, 97, 25);
		btnGeri.addActionListener(globalFonksiyonlar.Eventler.FormKapatGeriDonActionListener);

		Fonksiyonlar.frameElementEkle(frame, jtfAd, lblAd, jtfTelefon, jtfEMail, lblCepTelefonu, lblEmail,
				textfieldAdres, lblAdres, btnGeri, btnKaydet);

	}
}
