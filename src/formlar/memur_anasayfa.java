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
public class memur_anasayfa {

	private JFrame frame = null;

	public memur_anasayfa() {
		Yükle();
	}

	public JFrame formAl() {
		return frame;
	}

	private void Yükle() {

		Fonksiyonlar.JTattooYukle();

		frame = Fonksiyonlar.AnaFormOlustur(100, 100, 550, 464);

		JLabel lblKitapIslemleri = varsayilan.Componentler.label(LabelyadaButtonIcon.KitapIslemleri, 126, 42, 128, 128);

		JButton btnKitapIslemleri = varsayilan.Componentler.jbutton("KÝTAP ÝÞLEMLERÝ", 111, 175, 143, 25);
		btnKitapIslemleri.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new formlar.kitapListele().formAl().setVisible(true);

				Fonksiyonlar.GetirParentForm(e).dispose();
			}
		});

		JLabel lblOduncIadeIslemleri = varsayilan.Componentler.label(LabelyadaButtonIcon.OduncIadeIslemleri, 287, 226, 128, 128);

		JButton btnOduncIadeIslemleri = varsayilan.Componentler.jbutton("ODUNC/IADE", 287, 359, 128, 25);
		btnOduncIadeIslemleri.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new formlar.oduncGiris().formAl().setVisible(true);
				
			}
		});

		JLabel lblTalepIslemleri = varsayilan.Componentler.label(LabelyadaButtonIcon.TalepIslemleri, 287, 42, 128, 128);

		JButton btnTalepIslemleri = varsayilan.Componentler.jbutton("TALEP ISLEMLERI", 287, 175, 128, 25);
		btnTalepIslemleri.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new formlar.talepListele().formAl().setVisible(true);

				Fonksiyonlar.GetirParentForm(e).dispose();
				
			}
		});

		JLabel lblUyeler = varsayilan.Componentler.label(LabelyadaButtonIcon.Uyeler, 111, 224, 128, 128);

		JButton btnUyeler = varsayilan.Componentler.jbutton("ÜYELER", 111, 357, 128, 25);
		btnUyeler.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new formlar.uyeListele().formAl().setVisible(true);

				Fonksiyonlar.GetirParentForm(e).dispose();

			}
		});


		
		frame = Fonksiyonlar.frameElementEkle(frame, lblKitapIslemleri, lblUyeler, lblTalepIslemleri, btnKitapIslemleri,
			    btnTalepIslemleri, btnUyeler, lblOduncIadeIslemleri, btnOduncIadeIslemleri);
	}
}
