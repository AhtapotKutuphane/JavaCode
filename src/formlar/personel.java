package formlar;

import javax.swing.JFrame;
import globalFonksiyonlar.Fonksiyonlar;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;
import DatabaseFonksiyonlar.IdAl;
import globalDegiskenler.DiziDegiskenler;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.JComboBox;
import javax.swing.JButton;

/**
 * 
 * @author Alican
 *
 */
public class personel {

	private JFrame frame;
	private JTextField jtfAd;
	private JTextField jtfSoyad;
	private JTextField jtfTCNo;
	private JTextField jtfCepTelefon;
	private JTextField jtfEMail;
	private JTextField jtfSifre;
	private TextField textfieldAdres;
	JDateChooser dateChooser = new JDateChooser();
	private JComboBox comboboxYetki;
	int id;
	dbClasslar.personel personel = new dbClasslar.personel();

	String personelAd = "";
	String personelSoyad = "";
	String personelTC = "";
	String personelCepTelefon = "";
	String personelEmail = "";
	String personelSifre = "";
	String personelAdres = "";
	java.sql.Date personelDogumYili = null;
	String personelYetki = null;

	public personel(String str, int id) {
		this.id = id;
		Yükle();
	}

	public JFrame formAl() {
		return frame;
	}

	private boolean Kontroller() {

		if (dateChooser.getDate() != null) {
			personelAd = jtfAd.getText().trim();
			personelSoyad = jtfSoyad.getText().trim();
			personelTC = jtfTCNo.getText().trim();
			personelCepTelefon = jtfCepTelefon.getText().trim();
			personelEmail = jtfEMail.getText().trim();
			personelSifre = jtfSifre.getText().trim();
			personelAdres = textfieldAdres.getText().trim();
			personelDogumYili = new java.sql.Date(dateChooser.getDate().getTime());
			personelYetki = String.valueOf(comboboxYetki.getSelectedItem());

			if (Fonksiyonlar.BosMu(personelAd, personelSoyad, personelTC, personelCepTelefon, personelEmail,
					personelAdres, personelSifre, personelDogumYili.toString())) {
				if (Fonksiyonlar.RakamKadarHaneMi(11, personelTC, personelCepTelefon)) {
					if (Fonksiyonlar.ComboboxSecinizDýþýndakiVeriMi(comboboxYetki.getSelectedIndex())) {
						return true;
					} else {
						new JOptionPane().showMessageDialog(null, "Yetkiyi seçiniz.");
						return false;
					}

				} else {
					new JOptionPane().showMessageDialog(null, "Telefon ve TC 11 hane olmalýdýr.");
					return false;
				}

			} else {
				new JOptionPane().showMessageDialog(null, "Boþ býrakýlmýþ veri var.");
				return false;
			}
		} else {
			new JOptionPane().showMessageDialog(null, "Tarih seçilmedi.");
			return false;
		}

	}

	private ActionListener Ekle = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (Kontroller()) {
				personel = new dbClasslar.personel(IdAl.PersonelIdAl() + 1, personelTC, personelAd, personelSoyad,
						personelDogumYili, personelYetki, personelCepTelefon, personelEmail, personelAdres,
						personelSifre);
				if (personel.personelDBEkle()) {
					DiziDegiskenler.TempArray.add(personel.personelGetModel());
					DiziDegiskenler.PersonelListMap.put(personel.getP_id(), personel.personelArrayListNewVerisiGetir());
					formlar.personelListele.EklemeYap();

					personel = new dbClasslar.personel();
					new JOptionPane().showMessageDialog(null, "Eklendi.");
				} else {
					new JOptionPane().showMessageDialog(null, "Sistemde bu TC ye sahip bir personel var.");
				}

			}
		}
	};

	private ActionListener Kaydet = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (Kontroller()) {
				personel = new dbClasslar.personel(personel.getP_id(), personelTC, personelAd, personelSoyad,
						personelDogumYili, personelYetki, personelCepTelefon, personelEmail, personelAdres,
						personelSifre);
				if (personel.personelDBGüncelle()) {
					DiziDegiskenler.PersonelListMap.replace(personel.getP_id(),
							personel.personelArrayListNewVerisiGetir());
					// Üye güncellendikten sonra listele ekranýda güncellenmesi için

					personelListele.Listele();
					new JOptionPane().showMessageDialog(null, "Güncellendi.");
				} else {
					new JOptionPane().showMessageDialog(null, "Sistemde bu TC ye sahip bir personel var.");
				}
			}

		}
	};

	private void Yükle() {

		String[] JTextFieldTexts = { "", "", "", "", "", "", "", "", "" };
		Date dogumyili = null;
		if (id != -1) {
			personel = (dbClasslar.personel) DiziDegiskenler.PersonelListMap.get(id);
			JTextFieldTexts[0] = personel.getP_tc();
			JTextFieldTexts[1] = personel.getP_ad();
			JTextFieldTexts[2] = personel.getP_soyad();
			JTextFieldTexts[3] = personel.getP_yetki();
			JTextFieldTexts[4] = personel.getP_cep_telefon();
			JTextFieldTexts[5] = personel.getP_email();
			JTextFieldTexts[6] = personel.getP_adres();
			JTextFieldTexts[7] = personel.getP_sifre();
			dogumyili = personel.getP_dogum_yili();
		}

		Fonksiyonlar.JTattooYukle();
		globalFonksiyonlar.comboboxModelYenile.YetkiModelYenile();

		frame = Fonksiyonlar.AnaFormOlustur(100, 100, 450, 502, "Personel Ekleme");

		JLabel lblAd = varsayilan.Componentler.label("Ad :", 23, 16, 56, 16);

		jtfAd = varsayilan.Componentler.jtextfield(JTextFieldTexts[1], 166, 13, 205, 22);

		JLabel lblSoyad = varsayilan.Componentler.label("Soyad :", 23, 51, 56, 16);

		jtfSoyad = varsayilan.Componentler.jtextfield(JTextFieldTexts[2], 166, 48, 205, 22);

		JLabel lblCepTelefonu = varsayilan.Componentler.label("Cep Telefonu :", 23, 199, 131, 16);

		jtfCepTelefon = varsayilan.Componentler.jtextfield(JTextFieldTexts[4], 166, 196, 205, 22);

		JLabel lblDogumTarihi = varsayilan.Componentler.label("Doðum Tarihi :", 23, 121, 110, 16);

		dateChooser.setBounds(166, 118, 250, 22);
		dateChooser.setDate(dogumyili);

		JLabel lblTcNo = varsayilan.Componentler.label("TC No :", 23, 86, 99, 16);

		jtfTCNo = varsayilan.Componentler.jtextfield(JTextFieldTexts[0], 166, 83, 205, 22);

		JLabel lblYetki = varsayilan.Componentler.label("Yetki :", 23, 164, 110, 16);

		comboboxYetki = varsayilan.Componentler.combobox(globalDegiskenler.comboboxModeller.YetkiModel, 166, 161, 205,
				22);

		JLabel lblEmail = varsayilan.Componentler.label("E-mail :", 23, 234, 99, 16);

		jtfEMail = varsayilan.Componentler.jtextfield(JTextFieldTexts[5], 166, 231, 205, 22);

		JLabel lblSifre = varsayilan.Componentler.label("Þifre :", 23, 279, 85, 19);

		jtfSifre = varsayilan.Componentler.jtextfield(JTextFieldTexts[7], 166, 278, 205, 22);

		JLabel lblAdres = varsayilan.Componentler.label("Adres :", 23, 311, 85, 16);

		textfieldAdres = varsayilan.Componentler.textfield(JTextFieldTexts[6], 166, 312, 205, 101);

		JButton btnKaydet = varsayilan.Butonlar.Kaydet(166, 426, 97, 25);
		if (id == -1) {
			btnKaydet = varsayilan.Butonlar.Ekle(166, 426, 97, 25);
			btnKaydet.addActionListener(Ekle);
		} else {
			btnKaydet = varsayilan.Butonlar.Kaydet(166, 426, 97, 25);
			btnKaydet.addActionListener(Kaydet);
			comboboxYetki.setSelectedItem(personel.getP_yetki());

		}

		JButton btnGeri = varsayilan.Butonlar.Geri(274, 426, 97, 25);
		btnGeri.addActionListener(globalFonksiyonlar.Eventler.FormKapatGeriDonActionListener);

		Fonksiyonlar.frameElementEkle(frame, jtfAd, textfieldAdres, jtfCepTelefon, jtfEMail, jtfSifre, jtfSoyad,
				jtfTCNo, btnGeri, btnKaydet, dateChooser, comboboxYetki, lblAd, lblAdres, lblCepTelefonu,
				lblDogumTarihi, lblEmail, lblSoyad, lblTcNo, lblSifre, lblYetki);

	}
}
