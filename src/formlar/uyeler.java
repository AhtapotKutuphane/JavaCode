package formlar;

import javax.swing.JFrame;
import globalFonksiyonlar.Fonksiyonlar;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;
import DatabaseFonksiyonlar.IdAl;
import globalDegiskenler.DiziDegiskenler;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.JButton;

public class uyeler {

	private JFrame frame;
	private JTextField jtfAd;
	private JTextField jtfSoyad;
	private JTextField jtfTC;
	private JTextField jtfMeslek;
	private JTextField jtfEMail;
	private JTextField jtfTelefon;
	private TextField textfieldAdres;
	JDateChooser dateChooser = new JDateChooser();
	private JComboBox comboboxUyeTipi;
	private int id;
	String kim = "";
	dbClasslar.uyeler uye = new dbClasslar.uyeler();
	private String uye_adý = "";

	/**
	 * 
	 * @param str
	 *            Ekleme yada güncelleme olduðunu buraya yazarak belirt.
	 */
	public uyeler(String str, int id , String kim) {
		this.id = id;
		this.kim = kim;
		Yükle(str);
	}

	public JFrame formAl() {
		return frame;
	};

	
	public boolean Kontroller() {
		uye_adý = jtfAd.getText().trim();
		if (Fonksiyonlar.BosMu(uye_adý, jtfAd.getText(), jtfEMail.getText(), jtfMeslek.getText(),
				jtfSoyad.getText(), jtfTC.getText(), jtfTelefon.getText(), textfieldAdres.getText())) {

			int rakam = 11;
			if (Fonksiyonlar.RakamKadarHaneMi(rakam, jtfTelefon.getText(), jtfTC.getText())) {

				if (Fonksiyonlar.ComboboxSecinizDýþýndakiVeriMi(comboboxUyeTipi.getSelectedIndex())) {
						if (dateChooser.getDate() != null) {
							return true;
						}
						else {
							new JOptionPane().showMessageDialog(null, "Tarih seçiniz.");
							return false;
						}
						
				} else {
					new JOptionPane().showMessageDialog(null, "Üye tipi seçiniz.");
					return false;
				}
			} else {
				new JOptionPane().showMessageDialog(null, "Girdiðiniz rakam " + rakam + " hane deðil. (TC yada Telefon)");
				return false;
			}
		} else {
			new JOptionPane().showMessageDialog(null, "Boþ veri var.");
			return false;
		}
		
	}
	
	public ActionListener Kaydet = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (Kontroller()) {
				dbClasslar.uyetipi uye_tipi = (dbClasslar.uyetipi) comboboxUyeTipi.getSelectedItem();
				uye = new dbClasslar.uyeler(uye.getU_id(), jtfTC.getText(), uye_adý, jtfSoyad.getText(), new java.sql.Date(dateChooser.getDate().getTime()),
						jtfMeslek.getText(), jtfTelefon.getText(), jtfEMail.getText(), textfieldAdres.getText(),"h",
						uye_tipi.getUt_id());

				if (uye.uyeDBGüncelle()) {
					DiziDegiskenler.UyeListMap.replace(uye.getU_id(), uye.uyeArrayListNewVerisiGetir());
					uyeListele.Listele();
					new JOptionPane().showMessageDialog(null, "Güncellendi.");
				}
				else {
					new JOptionPane().showMessageDialog(null, "Güncellenmedi ayný tc var.");
				}
				
			}

		}
	};

	public ActionListener Ekle = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (Kontroller()) {
				dbClasslar.uyetipi uye_tipi = (dbClasslar.uyetipi) comboboxUyeTipi.getSelectedItem();
				uye = new dbClasslar.uyeler(IdAl.UyeIdAl() + 1, jtfTC.getText(), uye_adý, jtfSoyad.getText(),
						new java.sql.Date(dateChooser.getDate().getTime()), jtfMeslek.getText(), jtfTelefon.getText(), jtfEMail.getText(),
						textfieldAdres.getText(),"h", uye_tipi.getUt_id());
				if (uye.uyeDBEkle()) {
					DiziDegiskenler.TempArray.add(uye.uyeGetModel());
					// uye.getU_id olarak deðiþtirildi. en sondaki öðe güncellenebilmesi için.
					DiziDegiskenler.UyeListMap.put(uye.getU_id(), uye.uyeArrayListNewVerisiGetir());
					if (!kim.equals("Iade_Odunc")) {
						formlar.uyeListele.EklemeYap();
					}	
					new JOptionPane().showMessageDialog(null, "Eklendi.");
				}
				else {
					new JOptionPane().showMessageDialog(null, "Eklenemedi ayný tc var.");
				}
				uye = new dbClasslar.uyeler();
				
			}
		}
	};

	public void Yükle(String str) {
		Fonksiyonlar.JTattooYukle();
		globalFonksiyonlar.comboboxModelYenile.UyeTipiModelYenile();

		String[] JTextFieldTexts = { "", "", "", "", "", "", "" };
		Date dogumyili = null;
		if (id != -1) {
			uye = (dbClasslar.uyeler) DiziDegiskenler.UyeListMap.get(id);
			JTextFieldTexts[0] = uye.getU_ad();
			JTextFieldTexts[1] = uye.getU_soyad();
			JTextFieldTexts[2] = uye.getU_tc();
			JTextFieldTexts[3] = uye.getU_meslek();
			JTextFieldTexts[4] = uye.getU_email();
			JTextFieldTexts[5] = uye.getU_cep_telefon();
			JTextFieldTexts[6] = uye.getU_adres();
			dogumyili = uye.getU_dogum_yili();

		}
		frame = Fonksiyonlar.AnaFormOlustur(100, 100, 450, 502, "Üye " + str);

		JLabel lblAd = varsayilan.Componentler.label("Ad :", 23, 16, 56, 16);

		jtfAd = varsayilan.Componentler.jtextfield(JTextFieldTexts[0], 166, 13, 205, 22);

		JLabel lblSoyad = varsayilan.Componentler.label("Soyad :", 23, 51, 56, 16);

		jtfSoyad = varsayilan.Componentler.jtextfield(JTextFieldTexts[1], 166, 48, 205, 22);

		JLabel lblTcNo = varsayilan.Componentler.label("TC No :", 23, 86, 99, 16);

		jtfTC = varsayilan.Componentler.jtextfield(JTextFieldTexts[2], 166, 83, 205, 22);

		JLabel lblDogumTarihi = varsayilan.Componentler.label("Doðum Tarihi :", 23, 121, 110, 16);

		dateChooser.setBounds(166, 118, 250, 22);
		dateChooser.setDate(dogumyili);

		JLabel lblMeslek = varsayilan.Componentler.label("Meslek :", 23, 164, 110, 16);

		jtfMeslek = varsayilan.Componentler.jtextfield(JTextFieldTexts[3], 166, 161, 205, 22);

		JLabel lblEmail = varsayilan.Componentler.label("E-mail :", 23, 234, 99, 16);

		jtfEMail = varsayilan.Componentler.jtextfield(JTextFieldTexts[4], 166, 231, 205, 22);

		JLabel lblCepTelefonu = varsayilan.Componentler.label("Cep Telefonu :", 23, 199, 131, 16);

		jtfTelefon = varsayilan.Componentler.jtextfield(JTextFieldTexts[5], 166, 196, 205, 22);

		JLabel lblUyeTipi = varsayilan.Componentler.label("Üye Tipi :", 23, 279, 85, 19);

		comboboxUyeTipi = varsayilan.Componentler.combobox(globalDegiskenler.comboboxModeller.UyeTipiModel, 166, 277,
				205, 22);

		JLabel lblAdres = varsayilan.Componentler.label("Adres :", 23, 311, 85, 16);

		textfieldAdres = varsayilan.Componentler.textfield(JTextFieldTexts[6], 166, 312, 205, 101);

		JButton btnKaydet = null;
		if (id == -1) {
			btnKaydet = varsayilan.Butonlar.Ekle(166, 425, 97, 25);
			btnKaydet.addActionListener(Ekle);
		} else {
			btnKaydet = varsayilan.Butonlar.Kaydet(166, 425, 97, 25);
			btnKaydet.addActionListener(Kaydet);
			comboboxUyeTipi.setSelectedItem(DiziDegiskenler.UyeTipiListMap.get(uye.getUt_id()));
		}

		JButton btnGeri = varsayilan.Butonlar.Geri(274, 425, 97, 25);
		btnGeri.addActionListener(globalFonksiyonlar.Eventler.FormKapatGeriDonActionListener);

		Fonksiyonlar.frameElementEkle(frame,dateChooser, jtfAd, jtfEMail, jtfMeslek, jtfSoyad, jtfTC, jtfTelefon, lblAd, lblSoyad,
				lblTcNo, comboboxUyeTipi, lblDogumTarihi, lblMeslek,
				lblCepTelefonu, lblEmail, lblUyeTipi, textfieldAdres, lblAdres, btnKaydet, btnGeri);

	}
}
