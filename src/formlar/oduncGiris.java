package formlar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import globalDegiskenler.DiziDegiskenler;
import globalDegiskenler.DiziOlmayanDegiskenler;
import globalFonksiyonlar.Fonksiyonlar;
import varsayilan.Butonlar;

public class oduncGiris {

	private JFrame frame;
	private JTextField tc;

	/**
	 * 
	 * @param str
	 *            Ekleme yada güncelleme olduðunu buraya yazarak belirt.
	 */
	public oduncGiris() {
		Yükle();
	}

	public JFrame formAl() {
		return frame;
	};

	private ActionListener kontrol = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (Fonksiyonlar.RakamKadarHaneMi(11, tc.getText())) {
				boolean Silinmis = true;
				dbClasslar.uyeler.uyeDBList();
				Object[] obj = DiziDegiskenler.UyeListMap.values().toArray();
				for (int i = 0; i < obj.length; i++) {
					dbClasslar.uyeler uyeler = (dbClasslar.uyeler) obj[i];
					if (uyeler.getSilindimi().equals("h")) {
						if (uyeler.getU_tc().equals(tc.getText())) {
							DiziOlmayanDegiskenler.UyeOduncIade = uyeler;
							new formlar.kitapOduncVerme().formAl().setVisible(true);
							Silinmis = Silinmis && false;
							Fonksiyonlar.GetirParentForm(e).dispose();
						} else {
							Silinmis = Silinmis && true;
						}
					}
				}
				if (Silinmis) {
					new JOptionPane().showMessageDialog(null, "Uye silinmiþ.");
				}
			} else {
				new JOptionPane().showMessageDialog(null, "11 Hane ve rakam giriniz.");
			}
		}
	};
	private ActionListener uyeGiris = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			new formlar.uyeler("Ekleme", -1, "Iade_Odunc").formAl().setVisible(true);
		}
	};

	private void Yükle() {

		Fonksiyonlar.JTattooYukle();

		frame = Fonksiyonlar.AnaFormOlustur(100, 100, 300, 150, "Ödünç Giriþ");

		JLabel tcLabel = varsayilan.Componentler.label("TC:", 40, 20, 170, 16);
		tc = varsayilan.Componentler.jtextfield("", 75, 18, 170, 22);
		JButton btngiris = Butonlar.Giris(50, 60, 75, 25);
		btngiris.addActionListener(kontrol);
		JButton btnuyekayit = Butonlar.UyeKayit(135, 60, 100, 25);
		btnuyekayit.addActionListener(uyeGiris);
		Fonksiyonlar.frameElementEkle(frame, tcLabel, tc, btngiris, btnuyekayit);
	}
}
