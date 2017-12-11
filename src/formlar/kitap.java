package formlar;

import javax.swing.JFrame;
import globalFonksiyonlar.Fonksiyonlar;
import javax.swing.JTextField;
import DatabaseFonksiyonlar.DBKontroller;
import DatabaseFonksiyonlar.IdAl;
import dbClasslar.kategori;
import dbClasslar.yazar;
import globalDegiskenler.DiziDegiskenler;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JButton;

/**
 * 
 * @author Alican
 *
 */
public class kitap {

	private JFrame frame;
	private JTextField kitap_Ad�;
	private JTextField stok_sayi;
	private JTextField raf_sayi;
	private JTextField odunc_sayi;
	private JComboBox comboboxKategori;
	private JComboBox comboboxYazar;
	private String kitapAd� = "";
	int id = -1;
	dbClasslar.kitap kitap = new dbClasslar.kitap();

	/**
	 * 
	 * @param str
	 *            Ekleme yada g�ncelleme oldu�unu buraya yazarak belirt.
	 */
	public kitap(String str, int id) {
		this.id = id;
		Y�kle(str);
	}

	public JFrame formAl() {
		return frame;
	};

	private boolean Kontroller() {
		kitapAd� = kitap_Ad�.getText().trim();
		if (Fonksiyonlar.BosMu(kitapAd�,odunc_sayi.getText(),raf_sayi.getText(),stok_sayi.getText())) {

			if (Fonksiyonlar.SayiM�(odunc_sayi.getText(), raf_sayi.getText(), stok_sayi.getText())) {

				if (Fonksiyonlar.ComboboxSecinizD���ndakiVeriMi(comboboxKategori.getSelectedIndex(),comboboxYazar.getSelectedIndex())) {
					yazar Yazar = (dbClasslar.yazar) comboboxYazar.getSelectedItem();
					
					if (DBKontroller.KitapAd�veYazarAd�Ayn�VarM�(kitapAd�, Yazar.getY_id(),kitap.getK_id())) {
						return true;
					}
					else if (id != -1) {
						// g�ncelleme ekran�ndaki yazar kitap ad� ili�ki olay�n� ��zmedi�imiz s�rece true kalmas� gerekiyor.
						//new JOptionPane().showMessageDialog(null, "Ayn� yazar�n b�yle bir kitab� var. ");
						return true;
					}
					else {
						new JOptionPane().showMessageDialog(null, "Ayn� yazar�n b�yle bir kitab� var.");
						return false;						
					}
				}
				else {
					new JOptionPane().showMessageDialog(null, "Kategori veya yazar se�iniz.");
					return false;
				}
			} else {
				new JOptionPane().showMessageDialog(null, "Sayi Girilmesi gereken yerler bo� veya yaz� var.");
				return false;
			}
		} else {
			new JOptionPane().showMessageDialog(null, "Bo� b�rak�lm�� veri var.");
			return false;
		}
		
	}
	
	private ActionListener Ekle = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (Kontroller()) {
				kategori Kategori = (dbClasslar.kategori) comboboxKategori.getSelectedItem();
				yazar Yazar = (dbClasslar.yazar) comboboxYazar.getSelectedItem();
				kitap = new dbClasslar.kitap(IdAl.KitapIdAl()+1,Yazar.getY_id(), Integer.parseInt(stok_sayi.getText()),
						Integer.parseInt(raf_sayi.getText()), Integer.parseInt(odunc_sayi.getText()),
						Kategori.getKat_id(), kitapAd�);
				kitap.kitapDBEkle();

				DiziDegiskenler.TempArray.add(kitap.kitapGetModel());
//kitap.getk_id olarak de�i�tirildi.  en sondaki ��e g�ncellenebilmesi i�in.
				DiziDegiskenler.KitapListMap.put(kitap.getK_id(),kitap.kitapArrayListNewVerisiGetir());
				formlar.kitapListele.EklemeYap();

				kitap = new dbClasslar.kitap();
				new JOptionPane().showMessageDialog(null, "Eklendi.");
			}
		}
	};

	private ActionListener Kaydet = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (Kontroller()) {
				kategori Kategori = (dbClasslar.kategori) comboboxKategori.getSelectedItem();
				yazar Yazar = (dbClasslar.yazar) comboboxYazar.getSelectedItem();

				kitap = new dbClasslar.kitap(kitap.getK_id(), Yazar.getY_id(),
						Integer.parseInt(stok_sayi.getText()), Integer.parseInt(raf_sayi.getText()),
						Integer.parseInt(odunc_sayi.getText()), Kategori.getKat_id(), kitapAd�);
				kitap.kitapDBG�ncelle();

				DiziDegiskenler.KitapListMap.replace(kitap.getK_id(), kitap.kitapArrayListNewVerisiGetir());
				// �ye g�ncellendikten sonra listele ekran�da g�ncellenmesi i�in
				
				kitapListele.Listele();
				new JOptionPane().showMessageDialog(null, "G�ncellendi.");
			}

		}
	};

	private void Y�kle(String str) {

		String[] JTextFieldTexts = { "", "0", "0", "0" };
		
		if (id != -1) {
			kitap = (dbClasslar.kitap) DiziDegiskenler.KitapListMap.get(id);	
			JTextFieldTexts[0] = kitap.getK_ad();
			JTextFieldTexts[1] = String.valueOf(kitap.getK_depo());
			JTextFieldTexts[2] = String.valueOf(kitap.getK_raf());
			JTextFieldTexts[3] = String.valueOf(kitap.getK_odunc());
		}

		Fonksiyonlar.JTattooYukle();
		
		globalFonksiyonlar.comboboxModelYenile.YazarModelYenile();
		globalFonksiyonlar.comboboxModelYenile.KategoriModelYenile();

		frame = Fonksiyonlar.AnaFormOlustur(100, 100, 450, 340, "Kitap " + str);

		JLabel lblKitapAd� = varsayilan.Componentler.label("Kitap Ad� :", 23, 16, 97, 16);

		kitap_Ad� = varsayilan.Componentler.jtextfield(JTextFieldTexts[0], 166, 13, 205, 22);

		JLabel lblYazar = varsayilan.Componentler.label("Yazar :", 23, 51, 56, 16);

		comboboxYazar = varsayilan.Componentler.combobox(globalDegiskenler.comboboxModeller.YazarModel, 166, 48, 205,
				22);

		JLabel lblStokSayi = varsayilan.Componentler.label("Stoktaki Say�s� :", 23, 86, 131, 16);

		stok_sayi = varsayilan.Componentler.jtextfield(JTextFieldTexts[1], 166, 83, 205, 22);

		JLabel lblRafSayi = varsayilan.Componentler.label("Raftaki Say�s� :", 23, 130, 120, 16);

		raf_sayi = varsayilan.Componentler.jtextfield(JTextFieldTexts[2], 166, 127, 205, 22);

		JLabel lblOduncSayi = varsayilan.Componentler.label("�d�n� Verilen :", 23, 165, 131, 16);

		odunc_sayi = varsayilan.Componentler.jtextfield(JTextFieldTexts[3], 166, 162, 205, 22);
		odunc_sayi.setEnabled(false);

		JLabel lblKategori = varsayilan.Componentler.label("Kategori :", 23, 205, 88, 16);

		comboboxKategori = varsayilan.Componentler.combobox(globalDegiskenler.comboboxModeller.KategoriModel, 166, 203,
				205, 22);

		JButton btnKaydet = null;
		if (id == -1) {
			btnKaydet = varsayilan.Butonlar.Ekle(166, 238, 97, 25);
			btnKaydet.addActionListener(Ekle);
		} else {
			btnKaydet = varsayilan.Butonlar.Kaydet(166, 238, 97, 25);
			btnKaydet.addActionListener(Kaydet);
			comboboxKategori.setSelectedItem(DiziDegiskenler.KategoriListMap.get(kitap.getKat_id()));
			comboboxYazar.setSelectedItem(DiziDegiskenler.YazarListMap.get(kitap.getY_id()));
		}

		JButton btnGeri = varsayilan.Butonlar.Geri(274, 238, 97, 25);
		btnGeri.addActionListener(globalFonksiyonlar.Eventler.FormKapatGeriDonActionListener);

		Fonksiyonlar.frameElementEkle(frame, kitap_Ad�, comboboxYazar, stok_sayi, raf_sayi, odunc_sayi,
				comboboxKategori, lblStokSayi, lblOduncSayi, btnKaydet, btnGeri, lblKitapAd�, lblOduncSayi, lblYazar,
				lblRafSayi, lblKategori);

	}
}
