package globalFonksiyonlar;

import java.awt.Component;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import globalDegiskenler.DiziOlmayanDegiskenler;
import globalDegiskenler.Fontlar;
import globalDegiskenler.Iconlar;
import globalDegiskenler.Renkler;

/**
 * 
 * @author Alican
 *
 */
public class Fonksiyonlar {

	/**
	 * Tc telefon gibi 11 rakam gerektiren de�i�kenlerde mesela 11 rakam�n� g�nderip
	 * kullan�m i�in
	 * 
	 * @param str
	 *            ka� tane veri girip uzunlu�unu test edeceksen gir.
	 * @param rakam
	 *            ka� hane istiyosan yani 11 rakam istiyosan 11 buraya gelecek.
	 * @return false d�n�yorsa uzunlu�u rakam kadar hane de�ildir.
	 */
	public static boolean RakamKadarHaneMi(int rakam, String... str) {
		boolean bool = true;
		for (int i = 0; i < str.length; i++) {
			if (str[i].length() == rakam) {
				try {
					Double.parseDouble(str[i]);
					bool = bool && true;
				} catch (Exception e) {
					bool = false;
				}
			} else {
				bool = false;
			}
		}
		return bool;
	}

	/**
	 * Index numaras� �zerinden test ediliyor.
	 * 
	 * @param Intarray
	 * @return E�er index numaras� 0 ise o de�er false d�nd�r�r. Index numaras� 0
	 *         dan farkl� ise true d�ner.
	 */
	public static boolean ComboboxSecinizD���ndakiVeriMi(int... Intarray) {
		boolean bool = true;
		for (int i = 0; i < Intarray.length; i++) {
			if (Intarray[i] == 0) {
				bool = bool && false;
			} else {
				bool = bool && true;
			}
		}
		return bool;
	}

	/**
	 * JTattoo diye bir k�t�phane var tasar�msal de�i�imler yap�yor o k�t�phaneyi
	 * y�klemek i�in.
	 */
	public static boolean JTattooYukle() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
			return true;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			return false;
		}
	}

	public static JFrame GetirParentForm(ActionEvent e) {
		Component component = (Component) e.getSource();
		JFrame frame = (JFrame) SwingUtilities.getRoot(component);
		return frame;
	}

	/**
	 * �stenilen de�erler g�r�ld��� anla��ld��� gibi ona g�re anaformun boyutu
	 * olu�uyor.
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @return
	 */
	public static JFrame AnaFormOlustur(int x, int y, int width, int height) {

		JFrame frame = new JFrame();
		frame.setTitle(DiziOlmayanDegiskenler.FormBa�l�kMetin);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(Iconlar.ToolBarx48));
		frame.setBounds(x, y, width, height);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLayout(null);
		frame.addWindowListener(Eventler.KapatButonu);

		return frame;

	}

	/**
	 * �stenilen de�erler g�r�ld��� anla��ld��� gibi ona g�re anaformun boyutu
	 * olu�uyor.
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param Basl�k
	 *            Formun ba�l��� yani �stteki yaz� ...
	 * @return
	 */
	public static JFrame AnaFormOlustur(int x, int y, int width, int height, String Basl�k) {

		JFrame frame = new JFrame();
		frame.setTitle(Basl�k);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(Iconlar.ToolBarx48));
		frame.setBounds(x, y, width, height);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.addWindowListener(Eventler.KapatButonu);

		return frame;

	}

	/**
	 * Bu fonksiyon i�ine eklenmesi gereken jrame yi al�p i�ine componentleri
	 * ekliyor bu componentler ka� tane oldu�u �nemli de�il sadece s�ras� ile
	 * eklenmi� olmas� yeterki.
	 * 
	 * @param frame
	 *            hangi frameye eklenecekse o frame
	 * @param comp
	 *            componentler i�ine eklenmesi gereken
	 * @return Jframeyi �evirir componentlerin eklendi�i
	 */
	public static JFrame frameElementEkle(JFrame frame, Component... comp) {

		for (Component component : comp) {
			frame.getContentPane().add(component);
		}
		return frame;
	}

	/**
	 * E�er fonksiyona verilen integer de�erlerin i�inde 0 de�eri varsa false
	 * d�nd�r�r.
	 * 
	 * @param IntegerValue
	 *            Integer de�eleri s�ral� bir �ekilde i�ine atay�p kullan�l�r.
	 * @return e�er 0 de�eri var ise false yok ise true d�ner.
	 */
	public static Boolean IsZero(int... IntegerValue) {

		boolean ReturnBool = false;
		for (int i = 0; i < IntegerValue.length; i++) {
			if (IntegerValue[i] != 0) {
				ReturnBool = ReturnBool && true;
			}
			if (IntegerValue[i] == 0) {
				ReturnBool = ReturnBool && false;
			}
		}
		return ReturnBool;
	}

	/**
	 * 
	 * @param kolon
	 *            buraya String dizisi yada tablodaki kolonlar� direk string olarak
	 *            s�ral� bir �ekildede yazabilirsin..
	 * @return
	 */
	public static JTable JTableOlustur(String... kolon) {
		JTable table = new JTable();
		String[] columns = new String[kolon.length];
		for (int i = 0; i < columns.length; i++) {
			columns[i] = kolon[i];
		}

		DefaultTableModel model = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		model.setColumnIdentifiers(columns);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setModel(model);
		table.setRowSelectionAllowed(true);
		table.setBackground(Renkler.JTableBackground);
		table.setForeground(Renkler.JTableForeground);
		table.setFont(Fontlar.TableText);

		return table;
	}

	/**
	 * 
	 * @param table
	 *            Verinin Eklenemesi gereken JTable
	 * @param veri
	 *            Veri dizi �ekilde tabi direk s�ral� string vb �ekilde de
	 *            eklenebilir.
	 * @return
	 */
	public static JTable JTableVeriEkleme(JTable table, Object... veri) {
		DefaultTableModel TableModel = (DefaultTableModel) table.getModel();
		TableModel.addRow(veri);
		table.setModel(TableModel);
		return table;
	}

	/**
	 * Tabledan veri silme.
	 * 
	 * @param table
	 *            Verinin Silinmesi gereken JTable
	 * @param index
	 *            silinmesi gereken verinin indexi
	 * @return
	 */
	public static JTable JTableVeriSilme(JTable table, int index) {
		DefaultTableModel TableModel = (DefaultTableModel) table.getModel();
		TableModel.removeRow(index);
		table.setModel(TableModel);
		return table;
	}

	/**
	 * JTable i�indeki verileri temizliyor.
	 * 
	 * @param table
	 * @return
	 */
	public static JTable JTableVeriTemizleme(JTable table) {
		DefaultTableModel TableModel = (DefaultTableModel) table.getModel();
		while (TableModel.getRowCount() > 0) {
			TableModel.removeRow(0);
		}
		table.setModel(TableModel);
		return table;
	}

	/**
	 * JTable i�inde solda bulunan id verisi al�r.
	 * 
	 * @param table
	 *            table i�te .d
	 * @param row_i
	 *            hangi rowda oldu�unu buraya yolla
	 * @return int de�er d�nd�r�r
	 */
	public static int IdGetir(JTable table, int row_i) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		return (int) model.getValueAt(row_i, 0);
	}

	/**
	 * String olarak gelmi� de�erlerin sayi olup olmad���n� test eder.
	 * 
	 * @param str
	 * @return false d�nerse sayi de�il true d�nerse say�d�rlar.
	 */
	public static boolean SayiM�(String... str) {
		boolean SayiM� = true;
		for (int i = 0; i < str.length; i++) {

			try {
				Integer.parseInt(str[i]);
				SayiM� = SayiM� && true;
			} catch (Exception e) {
				// System.out.println(e);
				SayiM� = false;
			}
		}
		return SayiM�;
	}

	/**
	 * Girilen string de�erler bo�mu de�il mi kontrol ediyor.
	 * 
	 * @param str
	 * @return bos ise true d�necek , dolu ise false d�necek
	 */

	// ! koy bo� gelmesi i�in
	public static boolean BosMu(String... str) {
		boolean BosMu = true;
		for (int i = 0; i < str.length; i++) {
			BosMu = BosMu && !str[i].equals("");
		}
		return BosMu;
	}

	/**
	 * Raf , stok , odunc say�lar�n� toplay�p d�nd�r�yor.
	 * 
	 * @param stok
	 * @param raf
	 * @param odunc
	 * @return
	 */
	public static int Toplam(int stok, int raf, int odunc) {
		return stok + raf + odunc;
	}

	/**
	 * String date tarihinden
	 * 
	 * @param sqlTarih
	 * @return sql.date �ekilinde �evilmi� hali
	 * @throws ParseException
	 */
	public static java.sql.Date StringToDate(String sqlTarih) throws ParseException {
		DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		Date date = format.parse(sqlTarih);

		return new java.sql.Date(date.getTime());
	}

	public static java.sql.Date UtilDateToSqlDate(java.util.Date sqlTarih) {
		return new java.sql.Date(sqlTarih.getTime());
	}
}
