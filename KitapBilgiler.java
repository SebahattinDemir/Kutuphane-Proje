package kitapprojem;

public class KitapBilgiler {

    int kitapNo ;
    String kitapAdi ;
    String kitabinYazarininAdi ;
    double kitapFiyati;

    public KitapBilgiler(int kitapNo,String kitapAdi, String kitabinYazarininAdi, double kitapFiyati) {
        this.kitapAdi = kitapAdi;
        this.kitabinYazarininAdi = kitabinYazarininAdi;
        this.kitapFiyati = kitapFiyati;
        this.kitapNo = kitapNo;
    }

    public int getKitapNo() {
        return kitapNo;
    }

    public void setKitapNo(int kitapNo) {
        this.kitapNo = kitapNo;
    }

    public String getKitapAdi() {
        return kitapAdi;
    }

    public void setKitapAdi(String kitapAdi) {
        this.kitapAdi = kitapAdi;
    }

    public String getKitabinYazarininAdi() {
        return kitabinYazarininAdi;
    }

    public void setKitabinYazarininAdi(String kitabinYazarininAdi) {
        this.kitabinYazarininAdi = kitabinYazarininAdi;
    }

    public double getKitapFiyati() {
        return kitapFiyati;
    }

    public void setKitapFiyati(double kitapFiyati) {
        this.kitapFiyati = kitapFiyati;
    }

    @Override
    public String toString() {
        return  kitapNo +"\t\t\t\t "+ kitapAdi +"\t\t\t  "+ kitabinYazarininAdi + "\t\t\t   "+kitapFiyati;
    }
}
