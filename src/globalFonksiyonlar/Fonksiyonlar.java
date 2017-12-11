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
	 * Tc telefon gibi 11 rakam gerektiren deðiþkenlerde mesela 11 rakamýný gönderip
	 * kullaným için
	 * 
	 * @param str
	 *            kaç tane veri girip uzunluðunu test edeceksen gir.
	 * @param rakam
	 *            kaç hane istiyosan yani 11 rakam istiyosan 11 buraya gelecek.
	 * @return false dönüyorsa uzunluðu rakam kadar hane deðildir.
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
	 * Index numarasý üzerinden test ediliyor.
	 * 
	 * @param Intarray
	 * @return Eðer index numarasý 0 ise o deðer false döndürür. Index numarasý 0
	 *         dan farklý ise true döner.
	 */
	public static boolean ComboboxSecinizDýþýndakiVeriMi(int... Intarray) {
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
	 * JTattoo diye bir kütüphane var tasarýmsal deðiþimler yapýyor o kütüphaneyi
	 * yüklemek için.
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
	 * Ýstenilen deðerler görüldüðü anlaþýldýðý gibi ona göre anaformun boyutu
	 * oluþuyor.
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @return
	 */
	public static JFrame AnaFormOlustur(int x, int y, int width, int height) {

		JFrame frame = new JFrame();
		frame.setTitle(DiziOlmayanDegiskenler.FormBaþlýkMetin);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(Iconlar.ToolBarx48));
		frame.setBounds(x, y, width, height);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLayout(null);
		frame.addWindowListener(Eventler.KapatButonu);

		return frame;

	}

	/**
	 * Ýstenilen deðerler görüldüðü anlaþýldýðý gibi ona göre anaformun boyutu
	 * oluþuyor.
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param Baslýk
	 *            Formun baþlýðý yani üstteki yazý ...
	 * @return
	 */
	public static JFrame AnaFormOlustur(int x, int y, int width, int height, String Baslýk) {

		JFrame frame = new JFrame();
		frame.setTitle(Baslýk);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(Iconlar.ToolBarx48));
		frame.setBounds(x, y, width, height);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.addWindowListener(Eventler.KapatButonu);

		return frame;

	}

	/**
	 * Bu fonksiyon içine eklenmesi gereken jrame yi alýp içine componentleri
	 * ekliyor bu componentler kaç tane olduðu önemli deðil sadece sýrasý ile
	 * eklenmiþ olmasý yeterki.
	 * 
	 * @param frame
	 *            hangi frameye eklenecekse o frame
	 * @param comp
	 *            componentler içine eklenmesi gereken
	 * @return Jframeyi çevirir componentlerin eklendiði
	 */
	public static JFrame frameElementEkle(JFrame frame, Component... comp) {

		for (Component component : comp) {
			frame.getContentPane().add(component);
		}
		return frame;
	}

	/**
	 * Eðer fonksiyona verilen integer deðerlerin içinde 0 deðeri varsa false
	 * döndürür.
	 * 
	 * @param IntegerValue
	 *            Integer deðeleri sýralý bir þekilde içine atayýp kullanýlýr.
	 * @return eðer 0 deðeri var ise false yok ise true döner.
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
	 *            buraya String dizisi yada tablodaki kolonlarý direk string olarak
	 *            sýralý bir þekildede yazabilirsin..
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
	 *            Veri dizi þekilde tabi direk sýralý string vb þekilde de
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
	 * JTable içindeki verileri temizliyor.
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
	 * JTable içinde solda bulunan id verisi alýr.
	 * 
	 * @param table
	 *            table iþte .d
	 * @param row_i
	 *            hangi rowda olduðunu buraya yolla
	 * @return int deðer döndürür
	 */
	public static int IdGetir(JTable table, int row_i) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		return (int) model.getValueAt(row_i, 0);
	}

	/**
	 * String olarak gelmiþ deðerlerin sayi olup olmadýðýný test eder.
	 * 
	 * @param str
	 * @return false dönerse sayi deðil true dönerse sayýdýrlar.
	 */
	public static boolean SayiMý(String... str) {
		boolean SayiMý = true;
		for (int i = 0; i < str.length; i++) {

			try {
				Integer.parseInt(str[i]);
				SayiMý = SayiMý && true;
			} catch (Exception e) {
				// System.out.println(e);
				SayiMý = false;
			}
		}
		return SayiMý;
	}

	/**
	 * Girilen string deðerler boþmu deðil mi kontrol ediyor.
	 * 
	 * @param str
	 * @return bos ise true dönecek , dolu ise false dönecek
	 */

	// ! koy boþ gelmesi için
	public static boolean BosMu(String... str) {
		boolean BosMu = true;
		for (int i = 0; i < str.length; i++) {
			BosMu = BosMu && !str[i].equals("");
		}
		return BosMu;
	}

	/**
	 * Raf , stok , odunc sayýlarýný toplayýp döndürüyor.
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
	 * @return sql.date þekilinde çevilmiþ hali
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
