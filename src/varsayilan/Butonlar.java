package varsayilan;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import globalDegiskenler.Fontlar;
import globalDegiskenler.Iconlar;

/**
 * Ýçindeki butonlar sayfanýn çoðu yerinde tekrar ediyor bu yüzden dýþtan çekilmesi için yaratýldý.
 * @author Alican
 */
public class Butonlar {

	public static JButton Kaydet(int x , int y ,int width , int height) {
		JButton button = new JButton("Kaydet");
		button.setFont(Fontlar.ButtonText);
		button.setIcon(new ImageIcon(Iconlar.Kaydettusux16));
		button.setBounds(x, y, width, height);
		
		return button;
	} 
	public static JButton Geri(int x , int y ,int width , int height) {
		JButton button = new JButton("Geri");
		button.setFont(Fontlar.ButtonText);
		button.setIcon(new ImageIcon(Iconlar.Geritusux16));
		button.setBounds(x, y, width, height);
		
		return button;
	} 
	public static JButton Yenile(int x , int y ,int width , int height) {
		JButton button = new JButton("Yenile");
		button.setIcon(new ImageIcon(Iconlar.Yeniletusux16));
		button.setFont(Fontlar.ButtonText);
		button.setBounds(x, y, width, height);
		
		return button;
	}
	public static JButton iade(int x , int y ,int width , int height) {
		JButton button = new JButton("Iade");
		button.setFont(Fontlar.ButtonText);
		button.setBounds(x, y, width, height);
		
		return button;
	} 
	public static JButton odunc(int x , int y ,int width , int height) {
		JButton button = new JButton("Ödünç");
		button.setFont(Fontlar.ButtonText);
		button.setBounds(x, y, width, height);
		
		return button;
	} 
	public static JButton sepetEkle(int x , int y ,int width , int height) {
		JButton button = new JButton("Sepete Ekle");
		button.setFont(Fontlar.ButtonText);
		button.setBounds(x, y, width, height);
		
		return button;
	}
	public static JButton sepetKaldir(int x , int y ,int width , int height) {
		JButton button = new JButton("Sepeten Çýkar");
		button.setFont(Fontlar.ButtonText);
		button.setBounds(x, y, width, height);
		
		return button;
	}
	public static JButton Ekle(int x , int y ,int width , int height) {
		JButton button = new JButton("Ekle");
		button.setIcon(new ImageIcon(Iconlar.Ekletusux16));
		button.setFont(Fontlar.ButtonText);
		button.setBounds(x, y, width, height);
		
		return button;
	}
	public static JButton Sil(int x , int y ,int width , int height) {
		JButton button = new JButton("Sil");
		button.setIcon(new ImageIcon(Iconlar.SilmeTusux16));
		button.setFont(Fontlar.ButtonText);
		button.setBounds(x, y, width, height);
		
		return button;
	}
	public static JButton Ara(int x , int y ,int width , int height) {
		JButton button = new JButton("Ara");
		button.setIcon(new ImageIcon(Iconlar.Aratusux16));
		button.setFont(Fontlar.ButtonText);
		button.setBounds(x, y, width, height);
		
		return button;
	}
	public static JButton Duzenle(int x , int y ,int width , int height) {
		JButton button = new JButton("Düzenle");
		button.setIcon(new ImageIcon(Iconlar.DuzenleTusux16));
		button.setFont(Fontlar.ButtonText);
		button.setBounds(x, y, width, height);
		
		return button;
	}
	public static JButton Giris(int x , int y ,int width , int height) {
		JButton button = new JButton("Giriþ");
		button.setFont(Fontlar.ButtonText);
		button.setIcon(new ImageIcon(Iconlar.Girisx16));
		button.setBounds(x, y, width, height);
		
		return button;
	}
	public static JButton UyeKayit(int x , int y ,int width , int height) {
		JButton button = new JButton("Üye Kayýt");
		button.setFont(Fontlar.ButtonText);
		button.setBounds(x, y, width, height);
		
		return button;
	}
	public static JButton SifremiUnuttum(int x , int y ,int width , int height) {
		JButton button = new JButton("ÞÝFREMÝ UNUTTUM");
		button.setFont(Fontlar.ButtonText);
		button.setBounds(x, y, width, height);
		
		return button;
	}
}
