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
public class kural {

	private JFrame frame;
	private int id = -1;
	TextField jtfKuralIcerik;
	JTextField jtfKuralAd;
	private String kurallarAd� = "";
	dbClasslar.kurallar kurallar = new dbClasslar.kurallar();

	public kural(String str, int id) {
		this.id = id;
		Y�kle(str);
	}

	public JFrame formAl() {
		return frame;
	}

	private boolean Kontroller() {
		kurallarAd� = jtfKuralAd.getText().trim();
		if (Fonksiyonlar.BosMu(kurallarAd�, jtfKuralIcerik.getText())) {
			return true;
		} else {
			new JOptionPane().showMessageDialog(null, "Bo� b�rak�lm�� veri var.");
			return false;
		}

	}

	public ActionListener Kaydet = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (Kontroller()) {
				kurallar = new dbClasslar.kurallar(kurallar.getKur_id(), kurallarAd�, jtfKuralIcerik.getText());

				if (kurallar.kurallarDBG�ncelle()) {
					DiziDegiskenler.KurallarListMap.replace(kurallar.getKur_id(), kurallar.kurallarNewVerisiGetir());
					kuralListele.Listele();
					new JOptionPane().showMessageDialog(null, "G�ncellendi.");
				} else {
					new JOptionPane().showMessageDialog(null, "G�ncellenmedi.");
				}
			}
		}
	};

	public ActionListener Ekle = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (Kontroller()) {
				kurallar = new dbClasslar.kurallar(kurallar.getKur_id(), kurallarAd�, jtfKuralIcerik.getText());

				if (kurallar.kurallarDBEkle()) {
					DiziDegiskenler.KurallarListMap.replace(IdAl.KitapIdAl()+1, kurallar.kurallarNewVerisiGetir());
					kuralListele.Listele();
					new JOptionPane().showMessageDialog(null, "Eklendi.");
				} else {
					new JOptionPane().showMessageDialog(null, "Eklenemedi.");
				}
			}
		}
	};

	private void Y�kle(String str) {

		Fonksiyonlar.JTattooYukle();

		String[] JTextFieldTexts = { "", "" };
		if (id != -1) {
			kurallar = DiziDegiskenler.KurallarListMap.get(id);
			JTextFieldTexts[0] = kurallar.getKur_ad();
			JTextFieldTexts[1] = kurallar.getKur_icerik();
		}

		frame = Fonksiyonlar.AnaFormOlustur(100, 100, 450, 305, "Kural " + str);

		JButton btnKaydet = null;
		if (id == -1) {
			btnKaydet = varsayilan.Butonlar.Ekle(159, 212, 97, 25);
			btnKaydet.addActionListener(Ekle);
		} else {
			btnKaydet = varsayilan.Butonlar.Kaydet(159, 212, 97, 25);
			btnKaydet.addActionListener(Kaydet);
		}

		JButton btnGeri = varsayilan.Butonlar.Geri(257, 212, 97, 25);
		btnGeri.addActionListener(globalFonksiyonlar.Eventler.FormKapatGeriDonActionListener);

		JLabel lblKuralAd = varsayilan.Componentler.label("Kural Ad� :", 27, 33, 120, 16);

		jtfKuralAd = varsayilan.Componentler.jtextfield(JTextFieldTexts[0], 159, 31, 195, 22);

		JLabel lblIcerik = varsayilan.Componentler.label("��erik :", 27, 75, 120, 16);

		jtfKuralIcerik = varsayilan.Componentler.textfield(JTextFieldTexts[1], 159, 66, 195, 133);

		Fonksiyonlar.frameElementEkle(frame, btnKaydet, btnGeri, jtfKuralAd, jtfKuralIcerik, lblIcerik, lblKuralAd);

	}
}
