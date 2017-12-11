package formlar;

import globalDegiskenler.LabelyadaButtonIcon;
import globalFonksiyonlar.Fonksiyonlar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * 
 * @author Alican
 *
 */
public class destek_anasayfa {

	private JFrame frame = null;

	public destek_anasayfa() {
		Yükle();
	}

	public JFrame formAl() {
		return frame;
	}

	private void Yükle() {

		Fonksiyonlar.JTattooYukle();

		frame = Fonksiyonlar.AnaFormOlustur(100, 100, 390, 464);

		JLabel lblKitapIslemleri = varsayilan.Componentler.label(LabelyadaButtonIcon.KitapIslemleri, 126, 42, 128, 128);

		JButton btnKitapIslemleri = varsayilan.Componentler.jbutton("KÝTAP ÝÞLEMLERÝ", 111, 175, 143, 25);
		btnKitapIslemleri.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new formlar.kitapListele().formAl().setVisible(true);

				Fonksiyonlar.GetirParentForm(e).dispose();
			}
		});


		JLabel lblTalepIslemleri = varsayilan.Componentler.label(LabelyadaButtonIcon.TalepIslemleri, 120, 224, 128, 128);

		JButton btnTalepIslemleri = varsayilan.Componentler.jbutton("TALEP ISLEMLERI", 120, 357, 128, 25);
		btnTalepIslemleri.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new formlar.talepListele().formAl().setVisible(true);

				Fonksiyonlar.GetirParentForm(e).dispose();
				
			}
		});


		
		frame = Fonksiyonlar.frameElementEkle(frame, lblKitapIslemleri, lblTalepIslemleri, btnKitapIslemleri,
			    btnTalepIslemleri);
	}
}
