import java.util.Scanner;
import java.util.Random;
public class JavaÖdev {
    public static void main(String[] args) {
        Scanner islem = new Scanner(System.in);
        Random rastgele = new Random();
        int karakter;
        String[] takimlar = new String[4];
        boolean takimlarGirildi = false;
        while (true) {
            System.out.println();
            System.out.println("1. Takım isimleri");
            System.out.println("2. Fikstür oluştur");
            System.out.println("3. Skorları rastgele ata");
            System.out.println("4. Puan durumuna göz at");
            System.out.println("5. Çıkış");
            System.out.println();
            System.out.print("Seçim Yapınız = ");
            karakter = islem.nextInt();
            if (karakter == 5) {
                System.out.println();
                System.out.println("Programdan çıkılıyor");
                System.out.println();
                break;
            }
            if ((karakter >= 2 && karakter <= 4) && !takimlarGirildi) {
                System.out.println();
                System.out.println("Önce takım isimlerini belirlemelisiniz.");
                continue;
            }
            switch (karakter) {
                case 1:
                    System.out.println();
                    System.out.println("Takım İsimlerini Giriniz");
                    for (int i = 0; i < 4; i++) {
                        System.out.print((i + 1) + ". Takım: ");
                        takimlar[i] = islem.next();
                    }
                    takimlarGirildi = true;
                    System.out.println();
                    System.out.println("Takımlar başarıyla kaydedildi.");
                    break;
                case 2:
                    System.out.println();
                    System.out.println("Fikstür Karıştırılıyor ve Oluşturuluyor");
                    for (int i = 0; i < takimlar.length; i++) {
                        int rastgeleIndeks = rastgele.nextInt(takimlar.length);
                        String gecici = takimlar[rastgeleIndeks];
                        takimlar[rastgeleIndeks] = takimlar[i];
                        takimlar[i] = gecici;
                    }
                    System.out.println("1. DEVRE");
                    System.out.print("1. HAFTA: Ev Sahibi Takım = " + takimlar[0] + " vs " + takimlar[3]);
                    System.out.println(" | Ev Sahibi Takım = " + takimlar[1] + " vs " + takimlar[2]);
                    System.out.print("2. HAFTA: Ev Sahibi Takım = " + takimlar[0] + " vs " + takimlar[2]);
                    System.out.println(" | Ev Sahibi Takım = " + takimlar[3] + " vs " + takimlar[1]);
                    System.out.print("3. HAFTA: Ev Sahibi Takım = " + takimlar[0] + " vs " + takimlar[1]);
                    System.out.println(" | Ev Sahibi Takım = " + takimlar[2] + " vs " + takimlar[3]);
                    System.out.println("2. DEVRE (ev sahibi değişikliği)");
                    System.out.print("4. HAFTA: Ev Sahibi Takım = " + takimlar[3] + " vs " + takimlar[0]);
                    System.out.println(" | Ev Sahibi Takım = " + takimlar[2] + " vs " + takimlar[1]);
                    System.out.print("5. HAFTA: Ev Sahibi Takım = " + takimlar[2] + " vs " + takimlar[0]);
                    System.out.println(" | Ev Sahibi Takım = " + takimlar[1] + " vs " + takimlar[3]);
                    System.out.print("6. HAFTA: Ev Sahibi Takım = " + takimlar[1] + " vs " + takimlar[0]);
                    System.out.println(" | Ev Sahibi Takım = " + takimlar[3] + " vs " + takimlar[2]);
                case 3:
                    System.out.println("Skorlar rastgele atanıyor");
                    break;
                case 4:
                    System.out.println("Puan durumu tablosu:");
                    break;
                default:
                    System.out.println("Geçersiz bir seçim yaptınız!");
            }
        }
    }
}