package kitapprojem;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class KitapEkleme {

    public static final String kirmizi = "\u001B[31m";
    public static final String yesil = "\u001B[32m";
    public static final String sari = "\u001B[33m";
    public static final String mavi = "\u001B[34m";
    public static final String mor = "\u001B[35m";
    public static final String beyaz = "\u001B[37m";
    static Scanner input = new Scanner(System.in);
    static Map<Integer, KitapBilgiler> kitapListesi = new HashMap<>();
    static Set<Integer> kitapNoSet = kitapListesi.keySet();
    static Collection<KitapBilgiler> values = kitapListesi.values();
    static int counter = 1000;

    public static void baslangic() throws InterruptedException {
        playMusic(filePath);
        kitapMenu();
    }

    public static void kitapMenu() throws InterruptedException {
        System.out.println(mavi + "---------------------------------# KITAP ISLEM SAYFASI #---------------------------------");
        System.out.println(kirmizi + "1. Kitap Ekleme\n" +
                "2. Numara ile Kitap Goruntuleme\n" +
                "3. Bilgi ile Kitap Goruntuleme\n" +
                "4. Numara ile Kitap Silme\n" +
                "5. Tum Kitaplari Listele\n" +
                "6. Cikis\n");
        System.out.println(yesil + "Yapmak istediginiz islemin numarasini seciniz.");
        String secim = input.next();
        boolean whileDongusu = true;
        do {
            switch (secim) {
                case "1":
                    sayfaGecis(filePath);
                    ekleme();
                    whileDongusu = false;
                    break;
                case "2":
                    sayfaGecis(filePath);
                    numaraIleArama();
                    whileDongusu = false;
                    break;
                case "3":
                    sayfaGecis(filePath);
                    bilgiIleArama();
                    whileDongusu = false;
                    break;
                case "4":
                    sayfaGecis(filePath);
                    silme();
                    whileDongusu = false;
                    break;
                case "5":
                    sayfaGecis(filePath);
                    listeleme();
                    whileDongusu = false;
                    break;
                case "6":
                    cikis();
                    whileDongusu = false;
                    break;
                default:
                    hataliGiris(filePath);
                    System.out.println(mavi + "Yanlis bir secim yaptiniz. Tekrar giris yapiniz");
                    secim = input.next();
            }
        } while (whileDongusu);
    }

    public static void ekleme() throws InterruptedException {

        System.out.println(kirmizi + "-".repeat(24) + "# KITAP EKLEME SAYFASI #" + "-".repeat(24));
        System.out.println(mavi + "Eklemek istediginiz kitabin adini giriniz.");
        input.nextLine();
        String kitabinAdi = input.nextLine().toLowerCase();
        System.out.println(mavi + "Eklemek istediginiz kitabin yazarini giriniz.");
        String yazarAdi = input.nextLine().toLowerCase();
        System.out.println(mor + "Eklemek istediginiz kitabin fiyatini giriniz.");
        double kitapFiyati = input.nextDouble();
        KitapBilgiler kitapBilgiler = new KitapBilgiler(counter, kitabinAdi, yazarAdi, kitapFiyati);
        kitapListesi.put(counter, kitapBilgiler);
        System.out.println(sari + "Kitap basariyla eklenmistir ✅✅✅\n" + "Eklediğiniz kitabın kayıt numarası: " + counter + "\n" + "Kitap eklemeye devam etmek icin 1'e basiniz.\n" +
                "Ana menüye donmek icin 2'ye basiniz.");
        counter++;
        devamMiEkleme();
    }

    public static void numaraIleArama() throws InterruptedException {

        System.out.println(kirmizi + "------------------------# KAYIT NUMARASI ILE KITAP ARAMA SAYFASI #------------------------");
        System.out.println(yesil + "Aramak istediginiz kitabin kayit numarasini giriniz: ");
        input.nextLine();
        int kayitNo = input.nextInt();
        System.out.println(sari + "Kitap No             Kitap Adi            Yazar Adi            Fiyati               \n" +
                "------------------------------------------------------------------------------------------");
        if (kitapNoSet.contains(kayitNo)) {
            KitapBilgiler kitapBilgiler = kitapListesi.get(kayitNo);
            System.out.println(kitapBilgiler);
            devamMiNumaraIle();
        } else {
            hataliGiris(filePath);
            System.out.println(mavi + "Yanlis kayit numarasi girdiniz ❗❗❗ ");
            devamMiNumaraIle();
        }
    }

    public static void bilgiIleArama() throws InterruptedException {

        System.out.println(kirmizi + "------------------------# KITAP BILGILERI ILE KITAP ARAMA SAYFASI #------------------------");
        System.out.println(kirmizi + "1. Kitap Adi ile Arama\n" +
                "2. Yazar Adi ile Arama\n" + "3. Ana Menüye Dön ↩↩↩");
        input.nextLine();
        System.out.println(sari + "Yapmak istediginiz islemin numarasini seciniz.");
        String secim = input.next();
        boolean whileDongusu = true;
        do {
            switch (secim) {
                case "1":
                    kitapAdiIleArama();
                    whileDongusu = false;
                    break;
                case "2":
                    yazarAdiIleArama();
                    whileDongusu = false;
                    break;
                case "3":
                    System.out.println("Kitap Islem Sayfasina Donuluyor... \n");
                    kitapMenu();
                    whileDongusu = false;
                    break;
                default:
                    hataliGiris(filePath);
                    System.out.println(mavi + "Yanlis bir secim yaptiniz. Tekrar giris yapiniz");
                    secim = input.next();
            }
        } while (whileDongusu);
    }

    public static void kitapAdiIleArama() throws InterruptedException {

        System.out.println(mor + "--------------------------# KITAP ADI ILE KITAP ARAMA SAYFASI #--------------------------");
        System.out.println(yesil + "Aramak istediginiz kitabin adini giriniz: ");
        input.nextLine();
        String kitabinAdi = input.nextLine();
        System.out.println(mavi + "Kitap No             Kitap Adi            Yazar Adi            Fiyati               \n" +
                "------------------------------------------------------------------------------------------");
        for (KitapBilgiler w : values) {
            String a = w.getKitapAdi();
            if (a.equalsIgnoreCase(kitabinAdi)) {
                KitapBilgiler kitapBilgiler = new KitapBilgiler(w.getKitapNo(), w.getKitapAdi(), w.getKitabinYazarininAdi(), w.getKitapFiyati());
                System.out.println(kitapBilgiler);
            }
        }
        boolean sonuc = values.stream().anyMatch(w -> w.getKitapAdi().equalsIgnoreCase(kitabinAdi));
        if (sonuc) {
            devamMiKitapAdi();
        } else {
            hataliGiris(filePath);
            System.out.println(sari + "Listede olmayan yazar adi girdiniz ❗❗❗ ");
            devamMiKitapAdi();
        }
    }

    public static void yazarAdiIleArama() throws InterruptedException {

        System.out.println(mor + "--------------------------# YAZAR ADI ILE KITAP ARAMA SAYFASI #--------------------------");
        System.out.println(yesil + "Aramak istediginiz kitabin yazarinin adini giriniz: ");
        input.nextLine();
        String yazarinAdi = input.nextLine();
        System.out.println(mavi + "Kitap No             Kitap Adi            Yazar Adi            Fiyati               \n" +
                "------------------------------------------------------------------------------------------");
        for (KitapBilgiler w : values) {
            String a = w.getKitabinYazarininAdi();
            if (a.equalsIgnoreCase(yazarinAdi)) {
                KitapBilgiler kitapBilgiler = new KitapBilgiler(w.getKitapNo(), w.getKitapAdi(), w.getKitabinYazarininAdi(), w.getKitapFiyati());
                System.out.println(kitapBilgiler);
            }
        }
        boolean sonuc = values.stream().anyMatch(w -> w.getKitabinYazarininAdi().equalsIgnoreCase(yazarinAdi));
        if (sonuc) {
            devamMiYazarAdi();
        } else {
            hataliGiris(filePath);
            System.out.println(sari + "Listede olmayan yazar adi girdiniz ❗❗❗ ");
            devamMiYazarAdi();
        }
    }

    public static void silme() throws InterruptedException {

        System.out.println(mor + "---------------------------------# KITAP SILME SAYFASI #---------------------------------");
        System.out.println(sari + "Silmek istediginiz kitabin kayit numarasini giriniz");
        int kitapSilmeSecim2 = input.nextInt();

        if (kitapNoSet.contains(kitapSilmeSecim2)) {
            System.out.println(kirmizi + "Silinecek kitap bilgileri: ");
            System.out.println(sari + "Kitap No             Kitap Adi            Yazar Adi            Fiyati               \n" +
                    "------------------------------------------------------------------------------------------");
            System.out.println(kitapListesi.get(kitapSilmeSecim2));
            kitapListesi.remove(kitapSilmeSecim2);
            System.out.println(kirmizi + "Kitap basariyla silindi ☑☑☑\n");
            devamMiSilme();
        } else {
            hataliGiris(filePath);
            System.out.println(mavi + "Yanlis kayit numarasi girdiniz ❗❗❗");
            devamMiSilme();
        }
    }

    public static void listeleme() throws InterruptedException {

        System.out.println(kirmizi + "------------------------# KITAP LISTESI #------------------------");
        System.out.println(sari + "Kitap No              Kitap Adi             Yazar Adi             Fiyati               \n" +
                "------------------------------------------------------------------------------------------");

        for (Map.Entry<Integer, KitapBilgiler> w : kitapListesi.entrySet()) {
            Integer key = w.getKey();
            System.out.printf("%-21s %-21s %-21s %-21s\n", key, kitapListesi.get(key).getKitapAdi(),
                    kitapListesi.get(key).getKitabinYazarininAdi(), kitapListesi.get(key).getKitapFiyati());
        }
        devamMiListeleme();
    }

    public static void cikis() throws InterruptedException {
        filePath = "src/main/java/kitapprojem/pckapanissesi.wav";
        KitapEkleme audioPlayer = null;
        try {
            audioPlayer = new KitapEkleme();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            throw new RuntimeException(e);
        }
        audioPlayer.play();
        System.out.println(sari + "\t\t\t...SİSTEM KAPANIYOR LÜTFEN BEKLEYİNİZ...");
        Thread.sleep(500);
        System.out.print("\t\t\t\t\t\t" + (char) 9203 + "\t");
        Thread.sleep(500);
        System.out.print((char) 9203 + "\t");
        Thread.sleep(500);
        System.out.println((char) 9203);
        Thread.sleep(500);
        audioPlayer.stop();
        System.out.println(sari + "\t\t\t✨✨✨✨...İYİ GÜNLER DİLERİZ...✨✨✨✨");
        yurumeSesi(filePath);
        kapiSesi(filePath);
    }

    public static void devamMiYazarAdi() throws InterruptedException {
        System.out.println(sari + "Yazar adı ile kitap aramaya devam etmek icin 1'e basiniz.\n" + "Kitap bilgileri ile kitap arama menusune donmek icin 2'ye basiniz.\n" +
                "Ana menüye donmek icin 3'e basiniz.");
        String devamMi = input.next();
        switch (devamMi) {
            case "1":
                yazarAdiIleArama();
                break;
            case "2":
                bilgiIleArama();
                break;
            case "3":
                kitapMenu();
                break;
            default:
                hataliGiris(filePath);
                System.out.println(sari + "Hatalı değer girdiniz ❗❗❗");
                devamMiYazarAdi();
                break;
        }
    }

    public static void devamMiKitapAdi() throws InterruptedException {
        System.out.println(sari + "Kitap adı ile kitap aramaya devam etmek icin 1'e basiniz.\n" + "Kitap bilgileri ile kitap arama menusune donmek icin 2'ye basiniz.\n" +
                "Ana menüye donmek icin 3'e basiniz.");
        String devamMi = input.next();
        switch (devamMi) {
            case "1":
                kitapAdiIleArama();
                break;
            case "2":
                bilgiIleArama();
                break;
            case "3":
                kitapMenu();
                break;
            default:
                hataliGiris(filePath);
                System.out.println(sari + "Hatalı değer girdiniz ❗❗❗");
                devamMiKitapAdi();
                break;
        }
    }

    public static void devamMiNumaraIle() throws InterruptedException {
        System.out.println(sari + "Kitap aramaya devam etmek icin 1'e basiniz.\n" +
                "Ana menüye donmek icin 2'ye basiniz.");
        String devamMi = input.next();
        if (devamMi.equals("1")) {
            numaraIleArama();
        } else if (devamMi.equals("2")) {
            kitapMenu();
        } else {
            hataliGiris(filePath);
            System.out.println(sari + "Hatalı değer girdiniz ❗❗❗");
            devamMiNumaraIle();
        }
    }

    public static void devamMiSilme() throws InterruptedException {
        System.out.println(mor + "Kitap silmeye devam etmek icin 1'e basiniz.\n" +
                "Ana menüye donmek icin 2'ye basiniz\n");

        String devamMi = input.next();

        if (devamMi.equals("1")) {
            silme();
        } else if (devamMi.equals("2")) {
            kitapMenu();
        } else {
            hataliGiris(filePath);
            System.out.println(sari + "Hatalı değer girdiniz ❗❗❗");
            devamMiSilme();
        }
    }

    public static void devamMiEkleme() throws InterruptedException {
        String devamMi = input.next();
        if (devamMi.equals("1")) {
            ekleme();
        } else if (devamMi.equals("2")) {
            kitapMenu();
        } else {
            hataliGiris(filePath);
            System.out.println(sari + "Hatalı değer girdiniz ❗❗❗");
            System.out.println(sari + "Kitap eklemeye devam etmek icin 1'e basiniz.\n" +
                    "Ana menüye donmek icin 2'ye basiniz.");
            devamMiEkleme();
        }
    }

    public static void devamMiListeleme() throws InterruptedException {
        System.out.println(mor + "Ana menüye donmek icin 1'e basiniz");
        String devamMi = input.next();

        if (devamMi.equals("1")) {
            kitapMenu();
        } else {
            hataliGiris(filePath);
            System.out.println(sari + "Hatalı değer girdiniz ❗❗❗");
            devamMiListeleme();
        }
    }

    static Clip clip;

    String status;

    AudioInputStream audioInputStream;
    static String filePath;

    public KitapEkleme()
            throws UnsupportedAudioFileException,
            IOException, LineUnavailableException {
        audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
        clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public static void yurumeSesi(String filePath1) {
        try {
            filePath = "src/main/java/kitapprojem/yurumesesi.wav";
            KitapEkleme audioPlayer = new KitapEkleme();
            audioPlayer.play();
            Thread.sleep(4000);
            audioPlayer.stop();
        } catch (Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }

    public static void kapiSesi(String filePath1) {
        try {
            filePath = "src/main/java/kitapprojem/kapises.wav";
            KitapEkleme audioPlayer = new KitapEkleme();
            audioPlayer.play();
            Thread.sleep(6000);
            audioPlayer.stop();
        } catch (Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }

    public static void playMusic(String filePath1) {
        try {
            filePath = "src/main/java/kitapprojem/kapises.wav";
            KitapEkleme audioPlayer = new KitapEkleme();
            System.out.println(sari + "\t\t\t\t✨✨✨...HOŞGELDİNİZ...✨✨✨\n" + "\t" + (char) 9819 + (char) 9819 + (char) 9819 + "...ULUC(SES YÖNETMENİ) HOCAMIN KATKILARIYLA..." + (char) 9819 + (char) 9819 + (char) 9819);
            audioPlayer.play();
            Thread.sleep(6000);
            audioPlayer.stop();
            playMusic2(filePath);
        } catch (Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }

    public static void playMusic2(String filePath1) {
        try {
            filePath = "src/main/java/kitapprojem/yurumesesi.wav";
            KitapEkleme audioPlayer = new KitapEkleme();
            audioPlayer.play();
            Thread.sleep(4000);
            audioPlayer.stop();
            playMusic3(filePath);
        } catch (Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();

        }

    }

    public static void playMusic3(String filePath1) {
        try {
            filePath = "src/main/java/kitapprojem/bilgisayaracilissesi.wav";
            KitapEkleme audioPlayer = new KitapEkleme();
            System.out.println(sari + "\t\t\t...SİSTEM AÇILIYOR LÜTFEN BEKLEYİNİZ...");
            audioPlayer.play();
            Thread.sleep(3500);
            audioPlayer.stop();
        } catch (Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();

        }

    }

    public static void sayfaGecis(String filePath1) {
        try {
            filePath = "src/main/java/kitapprojem/kitapsayfasesi.wav";
            KitapEkleme audioPlayer = new KitapEkleme();

            audioPlayer.play();
            Thread.sleep(1000);
            audioPlayer.stop();
        } catch (Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();

        }

    }

    public static void hataliGiris(String filePath1) {
        try {
            filePath = "src/main/java/kitapprojem/hatasesi.wav";
            KitapEkleme audioPlayer = new KitapEkleme();

            audioPlayer.play();
            Thread.sleep(1000);
            audioPlayer.stop();
        } catch (Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();

        }

    }

    public void play() {
        //start the clip
        clip.start();
        status = "play";
    }

    public void stop() {
        //start the clip
        clip.stop();
        status = "stop";
    }


}
