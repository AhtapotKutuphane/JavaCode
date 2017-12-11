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
	private JTextField uyetipi_Ad�;
	private JTextField kitap_limit;
	private JTextField gun_limit;
	int id;
	dbClasslar.uyetipi uyetipi = new dbClasslar.uyetipi();
	private String uyetipiAd� = "";

	/**
	 * 
	 * @param str
	 *            Ekleme yada g�ncelleme oldu�unu buraya yazarak belirt.
	 */
	public uyetipi(String str, int id) {
		this.id = id;
		Y�kle(str);
	}

	public JFrame formAl() {
		return frame;
	};

	private ActionListener Ekle = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {

			uyetipiAd� = uyetipi_Ad�.getText().trim();
			if (Fonksiyonlar.BosMu(uyetipiAd�)) {

				if (Fonksiyonlar.SayiM�(kitap_limit.getText(), gun_limit.getText())) {

					uyetipi = new dbClasslar.uyetipi(IdAl.UyeTipiIdAl() + 1, uyetipiAd�,
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
					new JOptionPane().showMessageDialog(null, "Sayi Girilmesi gereken yerler bo� veya yaz� var.");
				}
			} else {
				new JOptionPane().showMessageDialog(null, "Bo� b�rak�lm�� veri var.");
			}
		}
	};

	private ActionListener Kaydet = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			String uyetipiAd� = uyetipi_Ad�.getText().trim();
			if (Fonksiyonlar.BosMu(uyetipiAd�)) {

				if (Fonksiyonlar.SayiM�(kitap_limit.getText(), gun_limit.getText())) {

					uyetipi = new dbClasslar.uyetipi(uyetipi.getUt_id(), uyetipiAd�,
							Integer.parseInt(kitap_limit.getText()), Integer.parseInt(gun_limit.getText()),"h");

					if (uyetipi.uyetipiDBG�ncelle()) {
						DiziDegiskenler.UyeTipiListMap.replace(uyetipi.getUt_id(),
								uyetipi.uyetipiArrayListNewVerisiGetir());
						// �ye g�ncellendikten sonra listele ekran�da g�ncellenmesi i�in

						uyetipiListele.Listele();
						new JOptionPane().showMessageDialog(null, "G�ncellendi.");
					} else {
						new JOptionPane().showMessageDialog(null, "G�ncellenemedi.");
					}

				} else {
					new JOptionPane().showMessageDialog(null, "Sayi Girilmesi gereken yerler bo� veya yaz� var.");
				}
			} else {
				new JOptionPane().showMessageDialog(null, "Bo� b�rak�lm�� veri var.");
			}

		}
	};

	private void Y�kle(String str) {

		String[] JTextFieldTexts = { "", "0", "0" };
		if (id != -1) {
			uyetipi = (dbClasslar.uyetipi) DiziDegiskenler.UyeTipiListMap.get(id);
			JTextFieldTexts[0] = uyetipi.getUt_ad();
			JTextFieldTexts[1] = String.valueOf(uyetipi.getUt_kitap_limit());
			JTextFieldTexts[2] = String.valueOf(uyetipi.getUt_gun_limit());

		}

		Fonksiyonlar.JTattooYukle();

		frame = Fonksiyonlar.AnaFormOlustur(100, 100, 430, 235, "�ye Tipi " + str);

		JLabel lblUyeTipiAd� = varsayilan.Componentler.label("�ye Tipi Ad� :", 15, 30, 120, 20);

		uyetipi_Ad� = varsayilan.Componentler.jtextfield(JTextFieldTexts[0], 170, 30, 205, 20);

		JLabel lblKitapLimitSayi = varsayilan.Componentler.label("Kitap Limit Say�s� :", 15, 70, 170, 20);

		kitap_limit = varsayilan.Componentler.jtextfield(JTextFieldTexts[1], 170, 70, 205, 20);

		JLabel lblgunLimitSayi = varsayilan.Componentler.label("G�n Limiti :", 15, 110, 150, 20);

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

		Fonksiyonlar.frameElementEkle(frame, uyetipi_Ad�, kitap_limit, gun_limit, lblUyeTipiAd�, lblKitapLimitSayi,
				btnKaydet, btnGeri, lblgunLimitSayi);

	}
}
