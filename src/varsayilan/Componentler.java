package varsayilan;

import java.awt.TextField;
import java.util.Vector;

import javax.swing.*;

import globalDegiskenler.Fontlar;
import globalDegiskenler.Renkler;

/**
 * 
 * @author Alican
 *
 */
public class Componentler {

	/**
	 * label yaratýrken varsayilan sistem kullanýlmasý gerekiyorsa.
	 * 
	 * @param str
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @return
	 */
	public static JLabel label(String str, int x, int y, int width, int height) {
		JLabel label = new JLabel(str);
		label.setFont(Fontlar.LabelText);
		label.setBounds(x, y, width, height);
		return label;
	}

	/**
	 * Anasayfadaki labellar için
	 * 
	 * @param ico
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @return
	 */
	public static JLabel label(ImageIcon ico, int x, int y, int width, int height) {
		JLabel label = new JLabel();
		label.setIcon(ico);
		label.setBounds(x, y, width, height);
		return label;
	}

	/**
	 * Varsayilan jtextfield için.
	 */
	public static JTextField jtextfield(String str, int x, int y, int width, int height) {
		JTextField textfield = new JTextField(str);
		textfield.setBounds(x, y, width, height);

		return textfield;
	}

	/**
	 * Varsayilan textfield için.
	 */
	public static TextField textfield(String str, int x, int y, int width, int height) {
		TextField textfield = new TextField(str);
		textfield.setBounds(x, y, width, height);

		return textfield;
	}

	/**
	 * Anasayfadaki Butonlar için.
	 * 
	 * @param str
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @return
	 */
	public static JButton jbutton(String str, int x, int y, int width, int height) {

		JButton btn = new JButton(str);
		btn.setForeground(Renkler.AnasayfaButton);
		btn.setFont(Fontlar.GirisButtonText);
		btn.setBounds(x, y, width, height);
		return btn;
	}

	/**
	 * Giriþ sayfasýnda butonlar için. ama iconlu butonlar için kullanýlmasý olasý
	 * ??
	 * 
	 * @param str
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @return
	 */
	public static JButton jbutton(String str, ImageIcon ico, int x, int y, int width, int height) {

		JButton btn = new JButton(str);
		btn.setForeground(Renkler.GirisButton);
		btn.setFont(Fontlar.GirisButtonText);
		btn.setIcon(ico);
		if (str.length() < 6) {
			btn.setIconTextGap(50);
		}
		btn.setHorizontalAlignment(SwingConstants.LEFT);
		btn.setBounds(x, y, width, height);
		return btn;
	}

	/**
	 * combobox yaratýrken varsayilan vector kullanýlan sistem kullanýlmasý
	 * gerekiyorsa.
	 * 
	 * @param model
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @return
	 */
	public static JComboBox combobox(Vector model, int x, int y, int width, int height) {
		JComboBox combobox = new JComboBox(model);
		combobox.setBounds(x, y, width, height);
		return combobox;
	}

	/**
	 * combobox yaratýrken varsayilan sistem kullanýlmasý gerekiyorsa.
	 * 
	 * @param model
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @return
	 */
	public static JComboBox combobox(int x, int y, int width, int height) {
		JComboBox combobox = new JComboBox();
		combobox.setBounds(x, y, width, height);
		return combobox;
	}

	public static JList list(int x, int y, int width, int height) {
		JList list = new JList();
		list.setBounds(x, y, width, height);
		return list;

	}
	public static JList list(int x, int y, int width, int height, DefaultListModel listModel) {
		JList list = new JList(listModel);
		list.setBounds(x, y, width, height);
		return list;

	}
}
