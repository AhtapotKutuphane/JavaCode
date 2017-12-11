package formlar;

import globalDegiskenler.LabelyadaButtonIcon;
import globalFonksiyonlar.Fonksiyonlar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class admin_anasayfa {

	private JFrame frame = null;

	public admin_anasayfa() {
		Yükle();
	}

	public JFrame formAl() {
		return frame;
	}

	private void Yükle() {

		Fonksiyonlar.JTattooYukle();

		frame = Fonksiyonlar.AnaFormOlustur(100, 100, 929, 464);

		JLabel lblKitapIslemleri = varsayilan.Componentler.label(LabelyadaButtonIcon.KitapIslemleri, 126, 42, 128, 128);

		JButton btnKitapIslemleri = varsayilan.Componentler.jbutton("KÝTAP ÝÞLEMLERÝ", 111, 175, 145, 25);
		btnKitapIslemleri.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new formlar.kitapListele().formAl().setVisible(true);

				Fonksiyonlar.GetirParentForm(e).dispose();
			}
		});

		JLabel lblPersonelIslemleri = varsayilan.Componentler.label(LabelyadaButtonIcon.PersonelIslemleri, 488, 42, 128,
				128);

		JButton btnPersonelIslemleri = varsayilan.Componentler.jbutton("PERSONEL ÝÞLEMLERÝ", 459, 175, 193, 25);
		btnPersonelIslemleri.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new formlar.personelListele().formAl().setVisible(true);

				Fonksiyonlar.GetirParentForm(e).dispose();
			}
		});

		JLabel lblTedarikci = varsayilan.Componentler.label(LabelyadaButtonIcon.Tedarikci, 287, 42, 128, 128);

		JButton btnTedarikci = varsayilan.Componentler.jbutton("TEDARÝKÇÝ", 287, 175, 128, 25);
		btnTedarikci.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new tedarikciListele().formAl().setVisible(true);
				
				Fonksiyonlar.GetirParentForm(e).dispose();
			}
		});

		JLabel lblTalepIslemleri = varsayilan.Componentler.label(LabelyadaButtonIcon.TalepIslemleri, 488, 226, 128,
				128);

		JButton btnTalepIslemleri = varsayilan.Componentler.jbutton("TALEP ÝÞLEMLERÝ", 459, 357, 193, 25);
		btnTalepIslemleri.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new formlar.talepListele().formAl().setVisible(true);

				Fonksiyonlar.GetirParentForm(e).dispose();
				
			}
		});

		JLabel lblUyeler = varsayilan.Componentler.label(LabelyadaButtonIcon.Uyeler, 111, 224, 128, 128);

		JButton btnUyeler = varsayilan.Componentler.jbutton("ÜYE TÝPÝ ÝÞLEM", 111, 357, 145, 25);
		btnUyeler.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new formlar.uyetipiListele().formAl().setVisible(true);

				Fonksiyonlar.GetirParentForm(e).dispose();

			}
		});

		// Alican
		/*
		 * btnUyeler.addActionListener(new ActionListener() {
		 * 
		 * @Override public void actionPerformed(ActionEvent e) {
		 * formlar.globalFormlar.UyeListele.setVisible(true);
		 * 
		 * Fonksiyonlar.GetirParentForm(e).dispose();
		 * 
		 * } });
		 */

		JLabel lblKurallar = varsayilan.Componentler.label(LabelyadaButtonIcon.Kurallar, 287, 226, 128, 128);
		
		JButton btnKurallar = varsayilan.Componentler.jbutton("KURALLAR", 287, 359, 128, 25);
		btnKurallar.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new formlar.kuralListele().formAl().setVisible(true);

				Fonksiyonlar.GetirParentForm(e).dispose();
			}
		});

		JLabel lblKatagori = varsayilan.Componentler.label(LabelyadaButtonIcon.Katagori, 713, 42, 128, 128);

		JButton btnKategori = varsayilan.Componentler.jbutton("KATEGORÝ", 688, 175, 110, 25);
		btnKategori.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
//ozanýn				new formlar.kategori().formAl().setVisible(true);
				new formlar.kategoriListele().formAl().setVisible(true);

				Fonksiyonlar.GetirParentForm(e).dispose();
			}
		});
		JButton btnYazar = varsayilan.Componentler.jbutton("YAZAR", 798, 175, 75, 25);
		btnYazar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new formlar.yazarListele().formAl().setVisible(true);

				Fonksiyonlar.GetirParentForm(e).dispose();
			}
		});
		

		JLabel lblSipariþ = varsayilan.Componentler.label(LabelyadaButtonIcon.Siparis, 713, 224, 128, 128);

		JButton btnSipariþ = varsayilan.Componentler.jbutton("SÝPARÝÞ", 688, 357, 175, 25);
		btnSipariþ.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new formlar.siparisListele().formAl().setVisible(true);

				Fonksiyonlar.GetirParentForm(e).dispose();
				
			}
		});

		frame = Fonksiyonlar.frameElementEkle(frame, lblKitapIslemleri, lblKatagori, lblPersonelIslemleri, lblKurallar,
				lblSipariþ, lblUyeler, lblTedarikci, lblTalepIslemleri, btnKategori, btnKitapIslemleri, btnKurallar,
				btnPersonelIslemleri, btnSipariþ, btnTalepIslemleri, btnTedarikci, btnUyeler,btnYazar);
	}
}
