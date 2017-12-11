package formlar;

import javax.swing.JFrame;
import globalFonksiyonlar.Fonksiyonlar;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import DatabaseFonksiyonlar.DBKontroller;
import DatabaseFonksiyonlar.IdAl;
import dbClasslar.kategori;
import dbClasslar.yazar;
import globalDegiskenler.DiziDegiskenler;

import javax.swing.JComboBox;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JButton;

public class talep {

	private int id = -1;
	private JComboBox comboboxKitapAd;
	private JComboBox comboboxUyeAd;
	private JComboBox comboboxDurum;
	private JTextField jtfMiktar;
	private JFrame frame;
	private dbClasslar.talep talep = new dbClasslar.talep();

	public talep(String str, int id) {
		this.id = id;
		Yükle(str);
	}

	private boolean Kontroller() {
		if (Fonksiyonlar.ComboboxSecinizDýþýndakiVeriMi(comboboxKitapAd.getSelectedIndex(),
				comboboxUyeAd.getSelectedIndex(), comboboxDurum.getSelectedIndex())) {
			if (Fonksiyonlar.SayiMý(jtfMiktar.getText())) {
				return true;
			} else {
				new JOptionPane().showMessageDialog(null, "Sayi giriniz. (Miktar)");
				return false;
			}
		} else {
			new JOptionPane().showMessageDialog(null, "Verileri Seçiniz.");
			return false;
		}
	}

	private ActionListener Ekle = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (Kontroller()) {
				dbClasslar.kitap kitap = (dbClasslar.kitap) comboboxKitapAd.getSelectedItem();
				dbClasslar.uyeler uyeler = (dbClasslar.uyeler) comboboxUyeAd.getSelectedItem();
				dbClasslar.durum durum = (dbClasslar.durum) comboboxDurum.getSelectedItem();
				talep = new dbClasslar.talep(IdAl.TalepIdAl() + 1, kitap.getK_id(), uyeler.getU_id(),
						new java.sql.Date(Calendar.getInstance().getTime().getTime()), durum.getD_id(),
						Integer.parseInt(jtfMiktar.getText()));

				if (talep.talepDBEkle()) {
					DiziDegiskenler.TempArray.add(talep.talepGetModel());
					DiziDegiskenler.TalepListMap.put(talep.getKt_id(), talep.talepNewVerisiGetir());
					formlar.talepListele.EklemeYap();
					new JOptionPane().showMessageDialog(null, "Eklendi.");
				} else {
					new JOptionPane().showMessageDialog(null, "Eklenemedi.");
				}
			}
		}
	};

	private ActionListener Kaydet = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (Kontroller()) {
				dbClasslar.kitap kitap = (dbClasslar.kitap) comboboxKitapAd.getSelectedItem();
				dbClasslar.uyeler uyeler = (dbClasslar.uyeler) comboboxUyeAd.getSelectedItem();
				dbClasslar.durum durum = (dbClasslar.durum) comboboxDurum.getSelectedItem();
				talep = new dbClasslar.talep(talep.getKt_id(), kitap.getK_id(), uyeler.getU_id(),
						new java.sql.Date(Calendar.getInstance().getTime().getTime()), durum.getD_id(),Integer.parseInt(jtfMiktar.getText()));
				if (talep.talepDBGüncelle()) {
					DiziDegiskenler.TalepListMap.replace(talep.getKt_id(), talep.talepNewVerisiGetir());
					talepListele.Listele();
					new JOptionPane().showMessageDialog(null, "Güncellendi.");
				} else {
					new JOptionPane().showMessageDialog(null, "Güncellenemedi.");
				}
			}

		}
	};

	public JFrame formAl() {
		return frame;
	}

	private void Yükle(String str) {
		Fonksiyonlar.JTattooYukle();
		globalFonksiyonlar.comboboxModelYenile.TedarikciModelYenile();
		globalFonksiyonlar.comboboxModelYenile.UyelerModelYenile();
		globalFonksiyonlar.comboboxModelYenile.KitapModelYenile();
		globalFonksiyonlar.comboboxModelYenile.DurumModelYenile();

		frame = Fonksiyonlar.AnaFormOlustur(100, 100, 450, 317, "Talep " + str);

		JButton btnGeri = varsayilan.Butonlar.Geri(292, 227, 97, 25);
		btnGeri.addActionListener(globalFonksiyonlar.Eventler.FormKapatGeriDonActionListener);

		JLabel lblKitapAd = varsayilan.Componentler.label("Kitap Adý :", 27, 68, 120, 16);

		comboboxKitapAd = varsayilan.Componentler.combobox(globalDegiskenler.comboboxModeller.KitapModel, 159, 66, 230,
				22);

		JLabel lblUyeAd = varsayilan.Componentler.label("Üye Adý :", 27, 105, 120, 16);

		comboboxUyeAd = varsayilan.Componentler.combobox(globalDegiskenler.comboboxModeller.UyelerModel, 159, 103, 230,
				22);

		JLabel lblDurum = varsayilan.Componentler.label("Durum :", 27, 149, 120, 16);

		comboboxDurum = varsayilan.Componentler.combobox(globalDegiskenler.comboboxModeller.DurumModel, 159, 149, 230,
				22);

		
		String miktar = "";
		JButton btnKaydet = null;
		if (id == -1) {

			btnKaydet = varsayilan.Butonlar.Ekle(159, 227, 97, 25);
			btnKaydet.addActionListener(Ekle);
		} else {
			btnKaydet = varsayilan.Butonlar.Kaydet(159, 227, 97, 25);
			btnKaydet.addActionListener(Kaydet);
			talep = DiziDegiskenler.TalepListMap.get(id);
			miktar = String.valueOf(talep.getKt_miktar());
			comboboxKitapAd.setSelectedItem(DiziDegiskenler.KitapListMap.get(talep.getK_id()));
			comboboxUyeAd.setSelectedItem(DiziDegiskenler.UyeListMap.get(talep.getU_id()));
			comboboxDurum.setSelectedItem(DiziDegiskenler.DurumListMap.get(talep.getD_id()));

		}
		JLabel lblMiktar = varsayilan.Componentler.label("Miktar :", 27, 195, 230, 22);
		jtfMiktar = varsayilan.Componentler.jtextfield(miktar, 159, 195, 230, 22);
		Fonksiyonlar.frameElementEkle(frame, lblKitapAd, lblDurum, lblUyeAd,lblMiktar,jtfMiktar, comboboxKitapAd, comboboxUyeAd,
				comboboxDurum, btnGeri, btnKaydet);

	}
}
