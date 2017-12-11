package globalFonksiyonlar;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import globalDegiskenler.DiziOlmayanDegiskenler;

public class Eventler {

	public static ActionListener AnasayfaGeriDonActionListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (DiziOlmayanDegiskenler.Yetki.equals("Yönetici")) {

				new formlar.admin_anasayfa().formAl().setVisible(true);

			} else if (DiziOlmayanDegiskenler.Yetki.equals("Memur")) {
				new formlar.memur_anasayfa().formAl().setVisible(true);
			}

			else if (DiziOlmayanDegiskenler.Yetki.equals("Personel")) {
				new formlar.destek_anasayfa().formAl().setVisible(true);
			}

			Fonksiyonlar.GetirParentForm(e).dispose();
		}
	};

	public static ActionListener FormKapatGeriDonActionListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {

			Fonksiyonlar.GetirParentForm(e).dispose();
		}
	};

	/**
	 * 
	 */
	public static WindowListener KapatButonu = new WindowListener() {
		
		@Override
		public void windowOpened(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void windowIconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void windowDeiconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void windowDeactivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void windowClosing(WindowEvent e) {
			JFrame test = (JFrame) e.getWindow();
			if (test.getTitle() == DiziOlmayanDegiskenler.FormBaþlýkMetin) {
				DiziOlmayanDegiskenler.Yetki = "";
				new formlar.giris().formAl().setVisible(true);
				e.getWindow().dispose();
			}
			else {
				e.getWindow().dispose();	
			}
		}
		
		@Override
		public void windowClosed(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void windowActivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
	};
}
