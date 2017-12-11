package formlar;

import javax.swing.JFrame;
import globalFonksiyonlar.Fonksiyonlar;
import javax.swing.JTextField;
import DatabaseFonksiyonlar.IdAl;
import globalDegiskenler.DiziDegiskenler;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 * 
 * @author Alican
 *
 */
public class uyetipi {

	private JFrame frame;
	private JTextField uyetipi_Adý;
	private JTextField kitap_limit;
	private JTextField gun_limit;
	int id;
	dbClasslar.uyetipi uyetipi = new dbClasslar.uyetipi();
	private String uyetipiAdý = "";

	/**
	 * 
	 * @param str
	 *            Ekleme yada güncelleme olduðunu buraya yazarak belirt.
	 */
	public uyetipi(String str, int id) {
		this.id = id;
		Yükle(str);
	}

	public JFrame formAl() {
		return frame;
	};

	private ActionListener Ekle = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {

			uyetipiAdý = uyetipi_Adý.getText().trim();
			if (Fonksiyonlar.BosMu(uyetipiAdý)) {

				if (Fonksiyonlar.SayiMý(kitap_limit.getText(), gun_limit.getText())) {

					uyetipi = new dbClasslar.uyetipi(IdAl.UyeTipiIdAl() + 1, uyetipiAdý,
							Integer.parseInt(kitap_limit.getText()), Integer.parseInt(gun_limit.getText()),"h");
					if (uyetipi.uyetipiDBEkle()) {
						DiziDegiskenler.TempArray.add(uyetipi.uyetipiGetModel());
						DiziDegiskenler.UyeTipiListMap.put(uyetipi.getUt_id(),
								uyetipi.uyetipiArrayListNewVerisiGetir());
						formlar.uyetipiListele.EklemeYap();

						uyetipi = new dbClasslar.uyetipi();
						new JOptionPane().showMessageDialog(null, "Eklendi.");
					} else {
						new JOptionPane().showMessageDialog(null, "Eklenemedi.");
					}
				} else {
					new JOptionPane().showMessageDialog(null, "Sayi Girilmesi gereken yerler boþ veya yazý var.");
				}
			} else {
				new JOptionPane().showMessageDialog(null, "Boþ býrakýlmýþ veri var.");
			}
		}
	};

	private ActionListener Kaydet = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			String uyetipiAdý = uyetipi_Adý.getText().trim();
			if (Fonksiyonlar.BosMu(uyetipiAdý)) {

				if (Fonksiyonlar.SayiMý(kitap_limit.getText(), gun_limit.getText())) {

					uyetipi = new dbClasslar.uyetipi(uyetipi.getUt_id(), uyetipiAdý,
							Integer.parseInt(kitap_limit.getText()), Integer.parseInt(gun_limit.getText()),"h");

					if (uyetipi.uyetipiDBGüncelle()) {
						DiziDegiskenler.UyeTipiListMap.replace(uyetipi.getUt_id(),
								uyetipi.uyetipiArrayListNewVerisiGetir());
						// Üye güncellendikten sonra listele ekranýda güncellenmesi için

						uyetipiListele.Listele();
						new JOptionPane().showMessageDialog(null, "Güncellendi.");
					} else {
						new JOptionPane().showMessageDialog(null, "Güncellenemedi.");
					}

				} else {
					new JOptionPane().showMessageDialog(null, "Sayi Girilmesi gereken yerler boþ veya yazý var.");
				}
			} else {
				new JOptionPane().showMessageDialog(null, "Boþ býrakýlmýþ veri var.");
			}

		}
	};

	private void Yükle(String str) {

		String[] JTextFieldTexts = { "", "0", "0" };
		if (id != -1) {
			uyetipi = (dbClasslar.uyetipi) DiziDegiskenler.UyeTipiListMap.get(id);
			JTextFieldTexts[0] = uyetipi.getUt_ad();
			JTextFieldTexts[1] = String.valueOf(uyetipi.getUt_kitap_limit());
			JTextFieldTexts[2] = String.valueOf(uyetipi.getUt_gun_limit());

		}

		Fonksiyonlar.JTattooYukle();

		frame = Fonksiyonlar.AnaFormOlustur(100, 100, 430, 235, "Üye Tipi " + str);

		JLabel lblUyeTipiAdý = varsayilan.Componentler.label("Üye Tipi Adý :", 15, 30, 120, 20);

		uyetipi_Adý = varsayilan.Componentler.jtextfield(JTextFieldTexts[0], 170, 30, 205, 20);

		JLabel lblKitapLimitSayi = varsayilan.Componentler.label("Kitap Limit Sayýsý :", 15, 70, 170, 20);

		kitap_limit = varsayilan.Componentler.jtextfield(JTextFieldTexts[1], 170, 70, 205, 20);

		JLabel lblgunLimitSayi = varsayilan.Componentler.label("Gün Limiti :", 15, 110, 150, 20);

		gun_limit = varsayilan.Componentler.jtextfield(JTextFieldTexts[2], 170, 110, 205, 20);

		JButton btnKaydet = null;
		if (id == -1) {
			btnKaydet = varsayilan.Butonlar.Ekle(166, 155, 97, 25);
			btnKaydet.addActionListener(Ekle);
		} else {
			btnKaydet = varsayilan.Butonlar.Kaydet(166, 155, 97, 25);
			btnKaydet.addActionListener(Kaydet);

		}
		JButton btnGeri = varsayilan.Butonlar.Geri(274, 155, 97, 25);
		btnGeri.addActionListener(globalFonksiyonlar.Eventler.FormKapatGeriDonActionListener);

		Fonksiyonlar.frameElementEkle(frame, uyetipi_Adý, kitap_limit, gun_limit, lblUyeTipiAdý, lblKitapLimitSayi,
				btnKaydet, btnGeri, lblgunLimitSayi);

	}
}
