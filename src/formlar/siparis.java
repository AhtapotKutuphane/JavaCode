package formlar;

import javax.swing.JFrame;
import globalFonksiyonlar.Fonksiyonlar;
import javax.swing.JTextField;

import DatabaseFonksiyonlar.IdAl;
import globalDegiskenler.DiziDegiskenler;
import globalDegiskenler.DiziOlmayanDegiskenler;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JButton;

/**
 * 
 * @author Alican
 *
 */
public class siparis {

	private int id = -1;
	private JFrame frame;
	private JTextField jtfMiktarAd;
	JComboBox comboboxTedarikci;
	JComboBox comboboxKitapAd;
	JComboBox comboboxDurum;
	String kim = "";
	dbClasslar.siparis siparis = new dbClasslar.siparis();

	public siparis(String str, int id, String kim) {
		this.id = id;
		this.kim = kim;
		Yükle(str);
	}

	public JFrame formAl() {
		return frame;
	}

	private boolean Kontroller() {
		if (Fonksiyonlar.SayiMý(jtfMiktarAd.getText())) {
			if (Fonksiyonlar.ComboboxSecinizDýþýndakiVeriMi(comboboxTedarikci.getSelectedIndex(),
					comboboxDurum.getSelectedIndex(), comboboxKitapAd.getSelectedIndex())) {
				return true;
			} else {
				new JOptionPane().showMessageDialog(null, "Verileri Seçiniz.");
				return false;
			}
		} else {
			new JOptionPane().showMessageDialog(null, "Miktar deðeri boþ veya sayý deðil.");
			return false;
		}
	}

	private ActionListener Ekle = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (Kontroller()) {
				dbClasslar.kitap kitap = (dbClasslar.kitap) comboboxKitapAd.getSelectedItem();
				dbClasslar.tedarikci tedarikci = (dbClasslar.tedarikci) comboboxTedarikci.getSelectedItem();
				dbClasslar.durum durum = (dbClasslar.durum) comboboxDurum.getSelectedItem();
				siparis = new dbClasslar.siparis(IdAl.SiparisIdAl() + 1, tedarikci.getT_id(), kitap.getK_id(),
						Integer.parseInt(jtfMiktarAd.getText()), durum.getD_id(),
						new java.sql.Date(Calendar.getInstance().getTime().getTime()));

				if (siparis.siparisDBEkle()) {
					DiziDegiskenler.TempArray.add(siparis.siparisGetModel());
					DiziDegiskenler.SiparisListMap.put(siparis.getS_id(), siparis.siparisNewVerisiGetir());
					formlar.siparisListele.EklemeYap();
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
				dbClasslar.tedarikci tedarikci = (dbClasslar.tedarikci) comboboxTedarikci.getSelectedItem();
				dbClasslar.durum durum = (dbClasslar.durum) comboboxDurum.getSelectedItem();
				siparis = new dbClasslar.siparis(siparis.getS_id(), tedarikci.getT_id(), kitap.getK_id(),
						Integer.parseInt(jtfMiktarAd.getText()), durum.getD_id(),
						new java.sql.Date(Calendar.getInstance().getTime().getTime()));
				if (siparis.siparisDBGüncelle()) {
					DiziDegiskenler.SiparisListMap.replace(siparis.getS_id(), siparis.siparisNewVerisiGetir());
					if (kim.equals("Siparis")) {
						siparisListele.Listele();
					}
					new JOptionPane().showMessageDialog(null, "Güncellendi.");
				} else {
					new JOptionPane().showMessageDialog(null, "Güncellenemedi.");
				}
			}

		}
	};

	private void Yükle(String str) {
		Fonksiyonlar.JTattooYukle();
		globalFonksiyonlar.comboboxModelYenile.TedarikciModelYenile();
		globalFonksiyonlar.comboboxModelYenile.KitapModelYenile();
		globalFonksiyonlar.comboboxModelYenile.DurumModelYenile();

		String miktar = "";
		frame = Fonksiyonlar.AnaFormOlustur(100, 100, 450, 305, "Sipariþ " + str);

		JButton btnGeri = varsayilan.Butonlar.Geri(291, 212, 97, 25);
		btnGeri.addActionListener(globalFonksiyonlar.Eventler.FormKapatGeriDonActionListener);

		JLabel lblTedarikci = varsayilan.Componentler.label("Tedarikçi Adý :", 27, 33, 120, 16);

		comboboxTedarikci = varsayilan.Componentler.combobox(globalDegiskenler.comboboxModeller.TedarikciModel, 159, 31,
				229, 22);

		JLabel lblKitapAd = varsayilan.Componentler.label("Kitap Adý :", 27, 73, 120, 16);

		comboboxKitapAd = varsayilan.Componentler.combobox(globalDegiskenler.comboboxModeller.KitapModel, 159, 71, 229,
				22);

		JLabel lblDurum = varsayilan.Componentler.label("Durum :", 27, 143, 120, 16);

		comboboxDurum = varsayilan.Componentler.combobox(globalDegiskenler.comboboxModeller.DurumModel, 159, 141, 229,
				22);

		JButton btnKaydet = null;
		if (id == -1) {
			btnKaydet = varsayilan.Butonlar.Ekle(159, 212, 97, 25);
			btnKaydet.addActionListener(Ekle);

		} else if (id != -1 && kim.equals("Talep")) {
			btnKaydet = varsayilan.Butonlar.Kaydet(159, 212, 97, 25);
			btnKaydet.addActionListener(Ekle);
			dbClasslar.talep talep = DiziDegiskenler.TalepListMap.get(id);
			miktar = String.valueOf(talep.getKt_miktar());
			comboboxKitapAd.setSelectedItem(DiziDegiskenler.KitapListMap.get(talep.getK_id()));
			comboboxDurum.setSelectedItem(DiziDegiskenler.DurumListMap.get(talep.getD_id()));
		} else {
			btnKaydet = varsayilan.Butonlar.Kaydet(159, 212, 97, 25);
			btnKaydet.addActionListener(Kaydet);
			siparis = DiziDegiskenler.SiparisListMap.get(id);
			miktar = String.valueOf(siparis.getS_miktar());
			comboboxKitapAd.setSelectedItem(DiziDegiskenler.KitapListMap.get(siparis.getK_id()));
			comboboxTedarikci.setSelectedItem(DiziDegiskenler.TedarikciListMap.get(siparis.getT_id()));
			comboboxDurum.setSelectedItem(DiziDegiskenler.DurumListMap.get(siparis.getD_id()));

		}
		JLabel lblMiktarAd = varsayilan.Componentler.label("Miktar :", 27, 109, 120, 16);

		jtfMiktarAd = varsayilan.Componentler.jtextfield(miktar, 159, 106, 229, 22);

		Fonksiyonlar.frameElementEkle(frame, lblDurum, lblKitapAd, lblMiktarAd, lblTedarikci, btnGeri, btnKaydet,
				comboboxDurum, comboboxKitapAd, comboboxTedarikci, jtfMiktarAd);
	}
}
