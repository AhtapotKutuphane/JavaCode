package formlar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import globalDegiskenler.DiziDegiskenler;
import globalDegiskenler.DiziOlmayanDegiskenler;
import globalFonksiyonlar.Fonksiyonlar;

public class uyeListele{

	private static JTextField txtAra = null;
	private JFrame frame;
	private static JTable table;
	protected Object u_id;

	public uyeListele() {

		Y�kle();
	}

	public JFrame formAl() {
		return frame;
	}	
	
	private ActionListener ListeYenile = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
		
			txtAra.setText("");
			DiziOlmayanDegiskenler.AramaM� = false;
			Listele();
		}
	};
	
	private WindowFocusListener test = new WindowFocusListener() {

		@Override
		public void windowLostFocus(WindowEvent e) {
			//

		}

		@Override
		public void windowGainedFocus(WindowEvent e) {

			if (!DiziOlmayanDegiskenler.AramaM�) {
				Listele();
			}

		}
	};
	
	private static ActionListener Ara = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if (txtAra != null && !txtAra.getText().equals("")) {
				DiziOlmayanDegiskenler.AramaM� = true;
				AramaYap(txtAra.getText());
			}
			else if (txtAra.getText().equals("")) {
				DiziOlmayanDegiskenler.AramaM�  = false;
				Listele();
			}
		}
	}; 

	public static void Listele() {
		dbClasslar.uyeler.uyeDBList();
		table = Fonksiyonlar.JTableVeriTemizleme(table);
		Object[] obj = DiziDegiskenler.UyeListMap.values().toArray();
		for (int i = 0; i < obj.length ; i++) {
			dbClasslar.uyeler uye= (dbClasslar.uyeler) obj[i];
			System.out.println(uye.getSilindimi());
			if (uye.getSilindimi().equals("h")) {
				table = Fonksiyonlar.JTableVeriEkleme(table, uye.uyeGetModel());
			}
		}
	}
	
	private static void AramaYap(String aranan_veri) {
		aranan_veri = aranan_veri.toLowerCase();
		table = Fonksiyonlar.JTableVeriTemizleme(table);
		Object[] obj = DiziDegiskenler.UyeListMap.values().toArray();
		for (int i = 0; i < obj.length; i++) {
			dbClasslar.uyeler uye= (dbClasslar.uyeler) obj[i];
			if (uye.getSilindimi().equals("h")) {
				if (uye.getU_ad().toLowerCase().contains(aranan_veri)) {
					table = Fonksiyonlar.JTableVeriEkleme(table, uye.uyeGetModel());
				}
			}
		}
	}
	
	public static void EklemeYap() {
		if (DiziDegiskenler.TempArray != null) {
			for (int i = 0; i < DiziDegiskenler.TempArray.size(); i++) {
				table = Fonksiyonlar.JTableVeriEkleme(table, DiziDegiskenler.TempArray.get(i));
			}
			DiziDegiskenler.TempArray = new ArrayList<>();
		}
	}
	private ActionListener Ekle = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			new uyeler("Ekleme",-1,"UyeListele").formAl().setVisible(true);
		}
	};

	

	/**
	 * Bu B�yle kalmayacak de�i�ecek b�y�k ihtimalle ama bi�iler kurcalarken b�yle
	 * yapt�m de�i�tirmedim...
	 * 
	 * @param frame
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @return
	 */
	private JFrame componentUretme(JFrame frame, int x, int y, int width, int height) {
		txtAra = varsayilan.Componentler.jtextfield("", 10, y, 200, height);
		txtAra.addActionListener(Ara);

		JButton btnAra = varsayilan.Butonlar.Ara(215, y, 85, height);
		btnAra.addActionListener(Ara);

		JButton btnGeri = varsayilan.Butonlar.Geri(1180, y, 95, height);
		btnGeri.addActionListener(globalFonksiyonlar.Eventler.AnasayfaGeriDonActionListener);

		JButton btnEkle = varsayilan.Butonlar.Ekle(870, y, 90, height);
		btnEkle.addActionListener(Ekle);
		
		JButton btnYenile = varsayilan.Butonlar.Yenile(770, y, 90, height);
		btnYenile.addActionListener(ListeYenile);

		JButton btnDzenle = varsayilan.Butonlar.Duzenle(970, y, 100, height);
		btnDzenle.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int i = table.getSelectedRow();
				if (i >= 0) {
					int id = Fonksiyonlar.IdGetir(table, i);
					new uyeler("G�ncelleme", id,"UyeListele").formAl().setVisible(true);
				} else {
					new JOptionPane().showMessageDialog(null, "D�zenlenecek veriyi se�mediniz.");
				}
			}
		});

		JButton btnSil = varsayilan.Butonlar.Sil(1080, y, 95, height);
		btnSil.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int i = table.getSelectedRow();
				if (i >= 0) {
					int id = Fonksiyonlar.IdGetir(table, i);
					table = globalFonksiyonlar.Fonksiyonlar.JTableVeriSilme(table, i);
					dbClasslar.uyeler uye = (dbClasslar.uyeler)DiziDegiskenler.UyeListMap.get(id);
					uye.uyeDBSil();
					DiziDegiskenler.UyeListMap.remove(id);
				} else {
					new JOptionPane().showMessageDialog(null, "Silinecek veriyi se�mediniz.");
				}
			}
		});

		Fonksiyonlar.frameElementEkle(frame, btnDzenle, btnSil, btnGeri, btnEkle, txtAra, btnAra,btnYenile);
		return frame;
	}

	private void Y�kle() {
		Fonksiyonlar.JTattooYukle();
		dbClasslar.uyetipi.uyetipiDBList();
			
		frame = Fonksiyonlar.AnaFormOlustur(100, 100, 1300, 500, "�ye Listeleme");
		
		table = Fonksiyonlar.JTableOlustur(DiziDegiskenler.UyeListeleme);
		
		JScrollPane pane = new JScrollPane(table);
		pane.setBounds(0, 0, 1290, 400);
		
		frame.addWindowFocusListener(test);

		Fonksiyonlar.frameElementEkle(componentUretme(frame, 900, 420, 87, 25), pane);		
		
	}
	

	
}
