import java.util.Scanner;
import java.util.Random;

public class JavaOdev { // Türkçe karakter hatasını önlemek için 'O' kullanıldı

    public static void main(String[] args) {
        Scanner oku = new Scanner(System.in);
        Random rastgele = new Random();

        String[] takimlar = new String[4];
        boolean takimlarGirildi = false;
        
        // Skorlar: [Hafta][4 Skor (H1, D1, H2, D2)]
        int[][] haftalikSkorlar = new int[6][4];
        
        // Fikstür: Her hafta oynayacak takımların indisleri
        int[][] fikstur = {
            {0, 3, 1, 2}, {0, 2, 3, 1}, {0, 1, 2, 3}, // 1. Devre
            {3, 0, 2, 1}, {2, 0, 1, 3}, {1, 0, 3, 2}  // 2. Devre
        };

        // Skorları -1 (oynanmadı) ile başlat
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 4; j++) {
                haftalikSkorlar[i][j] = -1;
            }
        }

        while (true) {
            System.out.println("------    ANA MENÜ    ------");
            System.out.println("1. Takım İsimlerini Gir");
            System.out.println("2. Fikstürü Görüntüle");
            System.out.println("3. Haftalık Skorları Rastgele Ata");
            System.out.println("4. Puan Durumunu Göster");
            System.out.println("5. Çıkış");
            System.out.print("Seçiminiz: ");
            
            char secim = oku.next().charAt(0);

            if (secim == '5') break;

            if (secim >= '2' && secim <= '4' && !takimlarGirildi) {
                System.out.println("Önce takımları girmelisiniz.");
                continue;
            }

            switch (secim) {
                case '1':
                    for (int i = 0; i < 4; i++) {
                        System.out.print((i + 1) + ". Takım: ");
                        takimlar[i] = oku.next();
                    }
                    takimlarGirildi = true;
                    break;

                case '2':
                    System.out.println("--- SEZON FİKSTÜRÜ ---");
                    for (int i = 0; i < 6; i++) {
                        if (i == 0) System.out.println("[1. DEVRE]");
                        if (i == 3) System.out.println("[2. DEVRE]");
                        
                        System.out.printf("%d. HAFTA: %s - %s | %s - %s", 
                            (i + 1), takimlar[fikstur[i][0]], takimlar[fikstur[i][1]], 
                            takimlar[fikstur[i][2]], takimlar[fikstur[i][3]]);
                    }
                    break;

                case '3':
                    System.out.println("--- SKORLAR ATANIYOR ---");
                    for (int i = 0; i < 6; i++) {
                        if (haftalikSkorlar[i][0] == -1) {
                            for (int j = 0; j < 4; j++) haftalikSkorlar[i][j] = rastgele.nextInt(5);
                        }
                        // Oynanan maçları yazdır
                        System.out.printf("%d. Hafta: %s %d-%d %s | %s %d-%d %s",
                            (i+1), takimlar[fikstur[i][0]], haftalikSkorlar[i][0], haftalikSkorlar[i][1], takimlar[fikstur[i][1]],
                            takimlar[fikstur[i][2]], haftalikSkorlar[i][2], haftalikSkorlar[i][3], takimlar[fikstur[i][3]]);
                    }
                    break;

                case '4':
                    int[] G = new int[4], B = new int[4], M = new int[4], P = new int[4];
                    int[] AG = new int[4], YG = new int[4], OM = new int[4];

                    for (int h = 0; h < 6; h++) {
                        if (haftalikSkorlar[h][0] == -1) continue;
                        
                        // Her haftadaki 2 maçı döngüyle işle (Kod tasarrufu sağlar)
                        for (int mac = 0; mac < 2; mac++) {
                            int evIndis = (mac == 0) ? 0 : 2;
                            int depIndis = (mac == 0) ? 1 : 3;
                            
                            int ev = fikstur[h][evIndis];
                            int dep = fikstur[h][depIndis];
                            int evSkor = haftalikSkorlar[h][evIndis];
                            int depSkor = haftalikSkorlar[h][depIndis];

                            OM[ev]++; OM[dep]++;
                            AG[ev] += evSkor; YG[ev] += depSkor;
                            AG[dep] += depSkor; YG[dep] += evSkor;

                            if (evSkor > depSkor) { P[ev] += 3; G[ev]++; M[dep]++; }
                            else if (depSkor > evSkor) { P[dep] += 3; G[dep]++; M[ev]++; }
                            else { P[ev]++; P[dep]++; B[ev]++; B[dep]++; }
                        }
                    }

                    System.out.printf("%-15s %-3s %-3s %-3s %-3s %-3s %-3s %-3s %-3s", 
                                      "Takım", "OM", "G", "B", "M", "AG", "YG", "AV", "P");
                    System.out.println("---------------------------------------------------------");
                    for (int i = 0; i < 4; i++) {
                        System.out.printf("%-15s %-3d %-3d %-3d %-3d %-3d %-3d %-3d %-3d",
                            takimlar[i], OM[i], G[i], B[i], M[i], AG[i], YG[i], (AG[i]-YG[i]), P[i]);
                    }
                    break;

                default:
                    System.out.println("Hatalı seçim!");
            }
        }
    }
}