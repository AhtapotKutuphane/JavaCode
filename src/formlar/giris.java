package formlar;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import globalDegiskenler.DiziOlmayanDegiskenler;
import globalDegiskenler.Iconlar;
import globalFonksiyonlar.Fonksiyonlar;
import globalFonksiyonlar.Login_Islem;

import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Toolkit;


public class giris {

	private JFrame frame;
	private JTextField textUsername;
	private JPasswordField passwordField;
	String[] veriler=new String[3];

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					giris window = new giris();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public giris() {
		Fonksiyonlar.JTattooYukle();
		initialize();
		
	}

	private ActionListener Giris = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
            String mesaj = new Login_Islem().GirisUzunlukKontrol(textUsername.getText(), passwordField.getText());									
			new Login_Islem().GirisKontrol(textUsername.getText(), passwordField.getText());
			
			if(DiziOlmayanDegiskenler.Yetki.equals("Yönetici")){
				
				new formlar.admin_anasayfa().formAl().setVisible(true);
				frame.dispose();
			}

			else if (DiziOlmayanDegiskenler.Yetki.equals("Memur")) {
							
				new formlar.memur_anasayfa().formAl().setVisible(true);
				frame.dispose();
			}
		
			else if (DiziOlmayanDegiskenler.Yetki.equals("Personel")) {
													
				new formlar.destek_anasayfa().formAl().setVisible(true);
				frame.dispose();
			}
			else if (DiziOlmayanDegiskenler.Yetki.equals("")) {
				JOptionPane.showMessageDialog(null,"Giriþ yapýlamadý.");
			}
			
			else {
				JOptionPane.showMessageDialog(null,"Girilen Deger Veritabaninda bulunamamaktadir." + "\n" + mesaj, "Hatali Giris!", JOptionPane.ERROR_MESSAGE);
				passwordField.setText(null);
				textUsername.setText(null);
			}
			
		}
	}; 
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(Iconlar.ToolBarx48));
		frame.setBounds(200, 200, 500, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblUsername = new JLabel("Kullanici Adi");
		lblUsername.setBounds(46, 88, 103, 14);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Sifre");
		lblPassword.setBounds(46, 133, 61, 14);
		frame.getContentPane().add(lblPassword);
		
		textUsername = new JTextField();
		textUsername.setBounds(170, 85, 257, 20);
		frame.getContentPane().add(textUsername);
		textUsername.setColumns(10);
		
		
		passwordField = new JPasswordField();
		passwordField.setBounds(170, 130, 257, 20);
		passwordField.addActionListener(Giris);
		
		frame.getContentPane().add(passwordField);
		
		JButton btnLogin = new JButton("Giris");
		btnLogin.addActionListener(Giris);
		btnLogin.setBounds(88, 206, 89, 23);
		btnLogin.setIcon(new ImageIcon(Iconlar.Girisx16));
		frame.getContentPane().add(btnLogin);
		
		JButton btnExit = new JButton("Cikis");
		btnExit.setIcon(new ImageIcon(Iconlar.SilmeTusux16));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFrame frmLoginSystem = new JFrame("Exit");
				if (JOptionPane.showConfirmDialog(frmLoginSystem, "Programi Kapat ?","Login Systems",
						JOptionPane.YES_NO_OPTION)== JOptionPane.YES_NO_OPTION){
					System.exit(0);
				}
				
				
			}
		});
		
		btnExit.setBounds(323, 206, 89, 23);
		frame.getContentPane().add(btnExit);
		
		JButton btnReset = new JButton("Temizle");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textUsername.setText(null);
				passwordField.setText(null);
			}
		});
		btnReset.setBounds(206, 206, 89, 23);
		frame.getContentPane().add(btnReset);
		btnReset.setIcon(new ImageIcon(Iconlar.Temizletusux16));
		
		JSeparator separator = new JSeparator();
		separator.setBounds(27, 173, 431, 2);
		frame.getContentPane().add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(27, 61, 431, 2);
		frame.getContentPane().add(separator_1);
		
		JLabel lblLogin = new JLabel("Giris Ekrani");
		lblLogin.setFont(new Font("Century Gothic", Font.PLAIN, 30));
		lblLogin.setBounds(27, 14, 302, 49);
		frame.getContentPane().add(lblLogin);
	}

	public JFrame formAl() {
		return frame;
	}
}
