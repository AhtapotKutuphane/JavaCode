-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Anamakine: 127.0.0.1
-- Üretim Zamanı: 11 Ara 2017, 15:49:01
-- Sunucu sürümü: 10.1.16-MariaDB
-- PHP Sürümü: 5.6.24

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Veritabanı: `kutuphane`
--

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `durum`
--

CREATE TABLE `durum` (
  `d_id` int(11) NOT NULL,
  `d_ad` varchar(25) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Tablo döküm verisi `durum`
--

INSERT INTO `durum` (`d_id`, `d_ad`) VALUES
(1, 'Verildi'),
(2, 'Onaylandı'),
(3, 'Onaylanmadı'),
(4, 'Alındı');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `kategori`
--

CREATE TABLE `kategori` (
  `kat_id` int(11) NOT NULL,
  `kat_ad` varchar(25) COLLATE utf8_bin NOT NULL,
  `silindimi` varchar(10) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Tablo döküm verisi `kategori`
--

INSERT INTO `kategori` (`kat_id`, `kat_ad`, `silindimi`) VALUES
(1, 'Bilim-Kurgu', 'h'),
(2, 'Fantastik', 'h'),
(3, 'Edebiyat', 'h'),
(4, 'qwerty', 'e'),
(5, 'Macera', 'h'),
(6, 'asd', 'e'),
(7, 'TEst', 'e'),
(8, 'Tarih', 'h'),
(9, 'fg', 'e');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `kitap`
--

CREATE TABLE `kitap` (
  `k_id` int(11) NOT NULL,
  `k_ad` varchar(25) COLLATE utf8_bin NOT NULL,
  `y_id` int(11) NOT NULL,
  `k_depo` int(11) NOT NULL,
  `k_raf` int(11) NOT NULL,
  `k_odunc` int(11) NOT NULL,
  `kat_id` int(11) NOT NULL,
  `silindimi` varchar(10) COLLATE utf8_bin NOT NULL DEFAULT 'h'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Tablo döküm verisi `kitap`
--

INSERT INTO `kitap` (`k_id`, `k_ad`, `y_id`, `k_depo`, `k_raf`, `k_odunc`, `kat_id`, `silindimi`) VALUES
(1, 'Kan Konusmaz', 1, 20, 0, 0, 3, 'h'),
(2, 'Kavim', 2, 20, 0, 0, 1, 'h'),
(3, 'Olasiliksiz', 7, 20, 0, 0, 1, 'h'),
(4, 'Sefiller', 5, 20, 0, 0, 3, 'h'),
(5, 'Tutunamayanlar', 28, 20, 0, 0, 3, 'h');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `kitap_odunc`
--

CREATE TABLE `kitap_odunc` (
  `ko_id` int(11) NOT NULL,
  `k_id` int(11) NOT NULL,
  `u_id` int(11) NOT NULL,
  `ko_tarih` date DEFAULT NULL,
  `ki_tarih` date DEFAULT NULL,
  `silindimi` varchar(10) COLLATE utf8_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `kitap_talep`
--

CREATE TABLE `kitap_talep` (
  `kt_id` int(11) NOT NULL,
  `k_id` int(11) NOT NULL,
  `u_id` int(11) NOT NULL,
  `kt_tarih` date NOT NULL,
  `d_id` int(11) NOT NULL,
  `kt_miktar` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `kurallar`
--

CREATE TABLE `kurallar` (
  `kur_id` int(11) NOT NULL,
  `kur_ad` varchar(25) COLLATE utf8_bin NOT NULL,
  `kur_icerik` varchar(500) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Tablo döküm verisi `kurallar`
--

INSERT INTO `kurallar` (`kur_id`, `kur_ad`, `kur_icerik`) VALUES
(3, 'ceza', '5tl'),
(4, 'Hasar', '12tl');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `personel`
--

CREATE TABLE `personel` (
  `p_id` int(11) NOT NULL,
  `p_tc` varchar(11) COLLATE utf8_bin NOT NULL,
  `p_ad` varchar(25) COLLATE utf8_bin NOT NULL,
  `p_soyad` varchar(25) COLLATE utf8_bin NOT NULL,
  `p_dogum_yili` date NOT NULL,
  `p_yetki` varchar(10) COLLATE utf8_bin NOT NULL,
  `p_cep_telefon` varchar(11) COLLATE utf8_bin NOT NULL,
  `p_email` varchar(50) COLLATE utf8_bin NOT NULL,
  `p_adres` varchar(500) COLLATE utf8_bin NOT NULL,
  `p_sifre` varchar(500) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Tablo döküm verisi `personel`
--

INSERT INTO `personel` (`p_id`, `p_tc`, `p_ad`, `p_soyad`, `p_dogum_yili`, `p_yetki`, `p_cep_telefon`, `p_email`, `p_adres`, `p_sifre`) VALUES
(1, '1', 'ben', 'deneme', '0008-07-03', 'Yönetici', '15915915915', 'ad@gmail.com', 'sd', '1'),
(2, '2', 'Alican', 'Ertop', '2017-12-08', 'Memur', '22342342342', '232@gmail.com', '1', '2'),
(3, '3', 'deneme', 'ben', '0013-11-30', 'Personel', '33333333333', 'kiskis@hotmail.com.tr', 'asd', '3');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `siparis`
--

CREATE TABLE `siparis` (
  `s_id` int(11) NOT NULL,
  `t_id` int(11) NOT NULL,
  `k_id` int(11) NOT NULL,
  `s_miktar` int(11) NOT NULL,
  `d_id` int(11) NOT NULL,
  `s_tarih` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Tablo döküm verisi `siparis`
--

INSERT INTO `siparis` (`s_id`, `t_id`, `k_id`, `s_miktar`, `d_id`, `s_tarih`) VALUES
(1, 1, 1, 23, 1, '2017-12-09'),
(4, 2, 2, 25, 2, '2017-12-10'),
(6, 1, 2, 10, 2, '2017-12-10');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `tedarikci`
--

CREATE TABLE `tedarikci` (
  `t_id` int(11) NOT NULL,
  `t_ad` varchar(25) COLLATE utf8_bin NOT NULL,
  `t_telefon` varchar(11) COLLATE utf8_bin NOT NULL,
  `t_adres` varchar(500) COLLATE utf8_bin NOT NULL,
  `t_email` varchar(50) COLLATE utf8_bin NOT NULL,
  `silindimi` varchar(1) COLLATE utf8_bin DEFAULT 'h'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Tablo döküm verisi `tedarikci`
--

INSERT INTO `tedarikci` (`t_id`, `t_ad`, `t_telefon`, `t_adres`, `t_email`, `silindimi`) VALUES
(1, 'Ahtapot', '53698745631', 'Tekirdag', 'kitap@windowslive.com', 'h'),
(2, 'Buro Teknik', '05469632145', 'Resadiye Mah. mandiraci 2. aralik sok. kara apt. d:3', 'deneme@test.com', 'h');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `uyeler`
--

CREATE TABLE `uyeler` (
  `u_id` int(11) NOT NULL,
  `u_tc` varchar(11) COLLATE utf8_bin NOT NULL,
  `u_ad` varchar(25) COLLATE utf8_bin NOT NULL,
  `u_soyad` varchar(25) COLLATE utf8_bin NOT NULL,
  `u_dogum_yili` date NOT NULL,
  `u_meslek` varchar(25) COLLATE utf8_bin NOT NULL,
  `u_cep_telefon` varchar(11) COLLATE utf8_bin NOT NULL,
  `u_email` varchar(50) COLLATE utf8_bin NOT NULL,
  `u_adres` varchar(500) COLLATE utf8_bin NOT NULL,
  `ut_id` int(11) NOT NULL,
  `silindimi` varchar(10) COLLATE utf8_bin DEFAULT 'h'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Tablo döküm verisi `uyeler`
--

INSERT INTO `uyeler` (`u_id`, `u_tc`, `u_ad`, `u_soyad`, `u_dogum_yili`, `u_meslek`, `u_cep_telefon`, `u_email`, `u_adres`, `ut_id`, `silindimi`) VALUES
(13, '12345678900', 'dene', 'deneme', '2017-12-01', 'stajyer', '12345678900', 'qwerty@gmail.com', 'Çorlu', 1, 'h'),
(14, '36246248082', 'Serhat', 'Kaya', '2017-12-14', 'Ö?renci', '19231283712', 'qkwjadsl@gmail.com', 'Çorlu ', 1, 'h'),
(16, '12345678912', 'qweq', 'qwe', '2017-12-22', 'asd', '12345678910', '1111', '1111', 2, 'h'),
(17, '12345678911', 'selam', 'hac?', '2017-12-06', 'Ev hanimi', '12345678910', 'halk@outlook.com', 'orda', 4, 'h'),
(18, '11111111111', 'ben', 'tmam', '2017-12-04', 'asd', '11111111111', 'tmm@gmail.com', 'burasi', 2, 'h');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `uye_tipi`
--

CREATE TABLE `uye_tipi` (
  `ut_id` int(11) NOT NULL,
  `ut_ad` varchar(25) COLLATE utf8_bin NOT NULL,
  `ut_kitap_limit` int(11) NOT NULL,
  `ut_gun_limit` int(11) NOT NULL,
  `silindimi` varchar(10) COLLATE utf8_bin NOT NULL DEFAULT 'h'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Tablo döküm verisi `uye_tipi`
--

INSERT INTO `uye_tipi` (`ut_id`, `ut_ad`, `ut_kitap_limit`, `ut_gun_limit`, `silindimi`) VALUES
(1, 'Ögrenci', 3, 7, 'h'),
(2, 'Ögretmen', 5, 10, 'h'),
(3, 'Arastirmaci', 7, 14, 'h'),
(4, 'Halk', 3, 3, 'h'),
(5, 'Personel', 1, 5, 'h');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `yazar`
--

CREATE TABLE `yazar` (
  `y_id` int(11) NOT NULL,
  `y_ad` varchar(50) COLLATE utf8_bin NOT NULL,
  `silindimi` varchar(10) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Tablo döküm verisi `yazar`
--

INSERT INTO `yazar` (`y_id`, `y_ad`, `silindimi`) VALUES
(1, 'Nazım Hikmet', 'h'),
(2, 'Ahmet Ümit', 'h'),
(3, 'test', 'e'),
(4, 'Hey', 'e'),
(5, 'Victor Hugo', 'h'),
(6, 'Tim Berners Lee', 'h'),
(7, 'Dostoyevski', 'h'),
(26, 'asd', 'e'),
(27, 'Namik Kemal', 'h'),
(28, 'Oguz Atay', 'h'),
(29, 'hooop', 'e');

--
-- Dökümü yapılmış tablolar için indeksler
--

--
-- Tablo için indeksler `durum`
--
ALTER TABLE `durum`
  ADD PRIMARY KEY (`d_id`);

--
-- Tablo için indeksler `kategori`
--
ALTER TABLE `kategori`
  ADD PRIMARY KEY (`kat_id`);

--
-- Tablo için indeksler `kitap`
--
ALTER TABLE `kitap`
  ADD PRIMARY KEY (`k_id`),
  ADD KEY `FKmraftw141a915ofp1lnd3nrnk` (`kat_id`),
  ADD KEY `FKmrqhkbcos9ci550f5gdda2vww` (`y_id`);

--
-- Tablo için indeksler `kitap_odunc`
--
ALTER TABLE `kitap_odunc`
  ADD PRIMARY KEY (`ko_id`),
  ADD KEY `FK7x9ysrwml43u8864nsipvtk4p` (`k_id`),
  ADD KEY `FKgeyx6py24v4d9s71clje1lkg6` (`u_id`);

--
-- Tablo için indeksler `kitap_talep`
--
ALTER TABLE `kitap_talep`
  ADD PRIMARY KEY (`kt_id`);

--
-- Tablo için indeksler `kurallar`
--
ALTER TABLE `kurallar`
  ADD PRIMARY KEY (`kur_id`);

--
-- Tablo için indeksler `personel`
--
ALTER TABLE `personel`
  ADD PRIMARY KEY (`p_id`),
  ADD UNIQUE KEY `p_tc` (`p_tc`);

--
-- Tablo için indeksler `siparis`
--
ALTER TABLE `siparis`
  ADD PRIMARY KEY (`s_id`);

--
-- Tablo için indeksler `tedarikci`
--
ALTER TABLE `tedarikci`
  ADD PRIMARY KEY (`t_id`);

--
-- Tablo için indeksler `uyeler`
--
ALTER TABLE `uyeler`
  ADD PRIMARY KEY (`u_id`),
  ADD UNIQUE KEY `u_tc` (`u_tc`);

--
-- Tablo için indeksler `uye_tipi`
--
ALTER TABLE `uye_tipi`
  ADD PRIMARY KEY (`ut_id`);

--
-- Tablo için indeksler `yazar`
--
ALTER TABLE `yazar`
  ADD PRIMARY KEY (`y_id`);

--
-- Dökümü yapılmış tablolar için AUTO_INCREMENT değeri
--

--
-- Tablo için AUTO_INCREMENT değeri `durum`
--
ALTER TABLE `durum`
  MODIFY `d_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- Tablo için AUTO_INCREMENT değeri `kategori`
--
ALTER TABLE `kategori`
  MODIFY `kat_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
--
-- Tablo için AUTO_INCREMENT değeri `kitap`
--
ALTER TABLE `kitap`
  MODIFY `k_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- Tablo için AUTO_INCREMENT değeri `kitap_odunc`
--
ALTER TABLE `kitap_odunc`
  MODIFY `ko_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- Tablo için AUTO_INCREMENT değeri `kitap_talep`
--
ALTER TABLE `kitap_talep`
  MODIFY `kt_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- Tablo için AUTO_INCREMENT değeri `kurallar`
--
ALTER TABLE `kurallar`
  MODIFY `kur_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- Tablo için AUTO_INCREMENT değeri `personel`
--
ALTER TABLE `personel`
  MODIFY `p_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- Tablo için AUTO_INCREMENT değeri `siparis`
--
ALTER TABLE `siparis`
  MODIFY `s_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- Tablo için AUTO_INCREMENT değeri `tedarikci`
--
ALTER TABLE `tedarikci`
  MODIFY `t_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- Tablo için AUTO_INCREMENT değeri `uyeler`
--
ALTER TABLE `uyeler`
  MODIFY `u_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;
--
-- Tablo için AUTO_INCREMENT değeri `uye_tipi`
--
ALTER TABLE `uye_tipi`
  MODIFY `ut_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- Tablo için AUTO_INCREMENT değeri `yazar`
--
ALTER TABLE `yazar`
  MODIFY `y_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;
--
-- Dökümü yapılmış tablolar için kısıtlamalar
--

--
-- Tablo kısıtlamaları `kitap`
--
ALTER TABLE `kitap`
  ADD CONSTRAINT `FKmraftw141a915ofp1lnd3nrnk` FOREIGN KEY (`kat_id`) REFERENCES `kategori` (`kat_id`),
  ADD CONSTRAINT `FKmrqhkbcos9ci550f5gdda2vww` FOREIGN KEY (`y_id`) REFERENCES `yazar` (`y_id`);

--
-- Tablo kısıtlamaları `kitap_odunc`
--
ALTER TABLE `kitap_odunc`
  ADD CONSTRAINT `FK7x9ysrwml43u8864nsipvtk4p` FOREIGN KEY (`k_id`) REFERENCES `kitap` (`k_id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
