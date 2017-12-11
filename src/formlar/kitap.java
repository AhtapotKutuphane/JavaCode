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
	private JTextField kitap_Adý;
	private JTextField stok_sayi;
	private JTextField raf_sayi;
	private JTextField odunc_sayi;
	private JComboBox comboboxKategori;
	private JComboBox comboboxYazar;
	private String kitapAdý = "";
	int id = -1;
	dbClasslar.kitap kitap = new dbClasslar.kitap();

	/**
	 * 
	 * @param str
	 *            Ekleme yada güncelleme olduðunu buraya yazarak belirt.
	 */
	public kitap(String str, int id) {
		this.id = id;
		Yükle(str);
	}

	public JFrame formAl() {
		return frame;
	};

	private boolean Kontroller() {
		kitapAdý = kitap_Adý.getText().trim();
		if (Fonksiyonlar.BosMu(kitapAdý,odunc_sayi.getText(),raf_sayi.getText(),stok_sayi.getText())) {

			if (Fonksiyonlar.SayiMý(odunc_sayi.getText(), raf_sayi.getText(), stok_sayi.getText())) {

				if (Fonksiyonlar.ComboboxSecinizDýþýndakiVeriMi(comboboxKategori.getSelectedIndex(),comboboxYazar.getSelectedIndex())) {
					yazar Yazar = (dbClasslar.yazar) comboboxYazar.getSelectedItem();
					
					if (DBKontroller.KitapAdýveYazarAdýAynýVarMý(kitapAdý, Yazar.getY_id(),kitap.getK_id())) {
						return true;
					}
					else if (id != -1) {
						// güncelleme ekranýndaki yazar kitap adý iliþki olayýný çözmediðimiz sürece true kalmasý gerekiyor.
						//new JOptionPane().showMessageDialog(null, "Ayný yazarýn böyle bir kitabý var. ");
						return true;
					}
					else {
						new JOptionPane().showMessageDialog(null, "Ayný yazarýn böyle bir kitabý var.");
						return false;						
					}
				}
				else {
					new JOptionPane().showMessageDialog(null, "Kategori veya yazar seçiniz.");
					return false;
				}
			} else {
				new JOptionPane().showMessageDialog(null, "Sayi Girilmesi gereken yerler boþ veya yazý var.");
				return false;
			}
		} else {
			new JOptionPane().showMessageDialog(null, "Boþ býrakýlmýþ veri var.");
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
						Kategori.getKat_id(), kitapAdý);
				kitap.kitapDBEkle();

				DiziDegiskenler.TempArray.add(kitap.kitapGetModel());
//kitap.getk_id olarak deðiþtirildi.  en sondaki öðe güncellenebilmesi için.
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
						Integer.parseInt(odunc_sayi.getText()), Kategori.getKat_id(), kitapAdý);
				kitap.kitapDBGüncelle();

				DiziDegiskenler.KitapListMap.replace(kitap.getK_id(), kitap.kitapArrayListNewVerisiGetir());
				// Üye güncellendikten sonra listele ekranýda güncellenmesi için
				
				kitapListele.Listele();
				new JOptionPane().showMessageDialog(null, "Güncellendi.");
			}

		}
	};

	private void Yükle(String str) {

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

		JLabel lblKitapAdý = varsayilan.Componentler.label("Kitap Adý :", 23, 16, 97, 16);

		kitap_Adý = varsayilan.Componentler.jtextfield(JTextFieldTexts[0], 166, 13, 205, 22);

		JLabel lblYazar = varsayilan.Componentler.label("Yazar :", 23, 51, 56, 16);

		comboboxYazar = varsayilan.Componentler.combobox(globalDegiskenler.comboboxModeller.YazarModel, 166, 48, 205,
				22);

		JLabel lblStokSayi = varsayilan.Componentler.label("Stoktaki Sayýsý :", 23, 86, 131, 16);

		stok_sayi = varsayilan.Componentler.jtextfield(JTextFieldTexts[1], 166, 83, 205, 22);

		JLabel lblRafSayi = varsayilan.Componentler.label("Raftaki Sayýsý :", 23, 130, 120, 16);

		raf_sayi = varsayilan.Componentler.jtextfield(JTextFieldTexts[2], 166, 127, 205, 22);

		JLabel lblOduncSayi = varsayilan.Componentler.label("Ödünç Verilen :", 23, 165, 131, 16);

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

		Fonksiyonlar.frameElementEkle(frame, kitap_Adý, comboboxYazar, stok_sayi, raf_sayi, odunc_sayi,
				comboboxKategori, lblStokSayi, lblOduncSayi, btnKaydet, btnGeri, lblKitapAdý, lblOduncSayi, lblYazar,
				lblRafSayi, lblKategori);

	}
}
