package globalDegiskenler;

import java.util.*;

public class DiziDegiskenler {

	public static final String[] KitapListeleme = { "ID", "Kitap Ad", "Yazar Ad", "Depodaki Kitap Miktarý",
			"Raftaki Kitap Miktarý", "Ödünç Alýnmýþ Kitap Miktarý", "Kategori Ad", "Toplam Kitap Adeti" };
	public static final String[] KategoriListeleme = { "ID", "Kategori Ad", "Kitap Miktarý" };
	public static final String[] YazarListeleme = { "ID", "Yazar Ad", "Kitap Miktarý" };
	public static final String[] KitapOduncListe = { "ID", "Kitap Ad", "Yazar Ad" ,"Miktar"};
	public static final String[] KitapOduncSepetListe = { "ID", "Kitap Ad", "Yazar Ad"};
	public static final String[] KitapOduncGecmisListe = { "ID", "Kitap Ad", "Yazar Ad"};
	public static final String[] UyeListeleme = { "ID", "TC", "Ad", "Soyad", "Doðum Yýlý", "Meslek", "Cep Telefon",
			"E-Mail", "Adres", "Uye Tipi" };
	public static final String[] PersonelListeleme = { "ID", "TC", "Ad", "Soyad", "Doðum Yýlý", "Yetki", "Cep Telefon",
			"E-Mail", "Adres", "Sifre" };
	public static final String[] UyeTipiListeleme = { "ID", "Uye tipi Ad", "Kitap Limit", "Gün Limit" };
	public static final String[] TedarikciListeleme = { "ID", "Þirket ADI", "Telefon", "E-Mail", "Adres" };
	public static final String[] KurallarListeleme = { "ID", "AD", "Icerik" };
	public static final String[] TalepListeleme = { "ID", "Kitap AD", "Uye Ad Soyad", " Tarih", "Miktar", "Durumu" };
	public static final String[] SiparisListeleme = { "ID", "Tedarikçi Adý", "Kitap Adý", " Miktarý", "Durum",
			"Verildiði Tarih" };

	public static Map<Integer, dbClasslar.durum> DurumListMap = new HashMap<Integer, dbClasslar.durum>();
	public static Map<Integer, dbClasslar.personel> PersonelListMap = new HashMap<Integer, dbClasslar.personel>();
	public static Map<Integer, dbClasslar.uyetipi> UyeTipiListMap = new HashMap<Integer, dbClasslar.uyetipi>();
	public static Map<Integer, dbClasslar.kategori> KategoriListMap = new HashMap<Integer, dbClasslar.kategori>();
	public static Map<Integer, dbClasslar.yazar> YazarListMap = new HashMap<Integer, dbClasslar.yazar>();
	public static Map<Integer, dbClasslar.kitap> KitapListMap = new HashMap<Integer, dbClasslar.kitap>();
	public static Map<Integer, dbClasslar.uyeler> UyeListMap = new HashMap<Integer, dbClasslar.uyeler>();
	public static Map<Integer, dbClasslar.tedarikci> TedarikciListMap = new HashMap<Integer, dbClasslar.tedarikci>();
	public static Map<Integer, dbClasslar.kurallar> KurallarListMap = new HashMap<Integer, dbClasslar.kurallar>();
	public static Map<Integer, dbClasslar.talep> TalepListMap = new HashMap<Integer, dbClasslar.talep>();
	public static Map<Integer, dbClasslar.siparis> SiparisListMap = new HashMap<Integer, dbClasslar.siparis>();
	public static Map<Integer, dbClasslar.kitap_odunc> KitapOduncMapUyeOzel = new HashMap<Integer, dbClasslar.kitap_odunc>();

	public static ArrayList<Object[]> TempArray = new ArrayList<>();
}
