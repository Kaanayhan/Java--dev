import java.util.Scanner;
import java.util.Random;

public class JavaOdev {

    public static void main(String[] args) {
        Scanner oku = new Scanner(System.in);
        Random rastgele = new Random();

        String[] takimlar = new String[4];
        boolean takimlarGirildi = false;

        int[][] haftalikSkorlar = new int[6][4];

        int[][] fikstur = {
                { 0, 3, 1, 2 }, { 0, 2, 3, 1 }, { 0, 1, 2, 3 }, // 1. Devre
                { 3, 0, 2, 1 }, { 2, 0, 1, 3 }, { 1, 0, 3, 2 } // 2. Devre
        };

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

            if (secim == '5')
                break;

            if (secim >= '2' && secim <= '4' && !takimlarGirildi) {
                System.out.println("Önce takım isimlerini belirlemelisiniz.");
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
                        if (i == 0)
                            System.out.println("[1. DEVRE]");
                        if (i == 3)
                            System.out.println("[2. DEVRE]");

                        System.out.printf("%d. HAFTA: %s - %s | %s - %s%n",
                                (i + 1), 
                                "Ev sahibi = " + takimlar[fikstur[i][0]],
                                "Deplasman = " + takimlar[fikstur[i][1]],
                                "Ev sahibi  = " + takimlar[fikstur[i][2]],
                                "Deplasman = " + takimlar[fikstur[i][3]]);
                    }
                    break;

                case '3':
                    System.out.println("--- SKORLAR ATANIYOR ---");
                    for (int i = 0; i < 6; i++) {
                        if (haftalikSkorlar[i][0] == -1) {
                            for (int j = 0; j < 4; j++)
                                haftalikSkorlar[i][j] = rastgele.nextInt(5);
                        }
                        System.out.printf("%d. Hafta: %s %d-%d %s | %s %d-%d %s%n",
                                (i + 1), takimlar[fikstur[i][0]], haftalikSkorlar[i][0], haftalikSkorlar[i][1],
                                takimlar[fikstur[i][1]],
                                takimlar[fikstur[i][2]], haftalikSkorlar[i][2], haftalikSkorlar[i][3],
                                takimlar[fikstur[i][3]]);
                    }
                    break;

                case '4':
                    int[] galibiyet = new int[4];
                    int[] beraberlik = new int[4];
                    int[] yenilgi = new int[4];
                    int[] puan = new int[4];
                    int[] atilanGol = new int[4];
                    int[] yenilenGol = new int[4];
                    int[] oynananMac = new int[4];
                    int[] averaj = new int[4];
                    int[] siraliIndisler = { 0, 1, 2, 3 };

                    for (int h = 0; h < 6; h++) {
                        if (haftalikSkorlar[h][0] == -1)
                            continue;

                        for (int mac = 0; mac < 2; mac++) {
                            int evIndis = (mac == 0) ? 0 : 2;
                            int depIndis = (mac == 0) ? 1 : 3;

                            int ev = fikstur[h][evIndis];
                            int dep = fikstur[h][depIndis];
                            int evSkor = haftalikSkorlar[h][evIndis];
                            int depSkor = haftalikSkorlar[h][depIndis];

                            oynananMac[ev]++;
                            oynananMac[dep]++;
                            atilanGol[ev] += evSkor;
                            yenilenGol[ev] += depSkor;
                            atilanGol[dep] += depSkor;
                            yenilenGol[dep] += evSkor;

                            if (evSkor > depSkor) {
                                puan[ev] += 3;
                                galibiyet[ev]++;
                                yenilgi[dep]++;
                            } else if (depSkor > evSkor) {
                                puan[dep] += 3;
                                galibiyet[dep]++;
                                yenilgi[ev]++;
                            } else {
                                puan[ev]++;
                                puan[dep]++;
                                beraberlik[ev]++;
                                beraberlik[dep]++;
                            }
                        }
                    }

                    for (int i = 0; i < 4; i++)
                        averaj[i] = atilanGol[i] - yenilenGol[i];

                    for (int i = 0; i < 4 - 1; i++) {
                        for (int j = 0; j < 4 - i - 1; j++) {
                            int t1 = siraliIndisler[j];
                            int t2 = siraliIndisler[j + 1];

                            if (puan[t1] < puan[t2] || (puan[t1] == puan[t2] && averaj[t1] < averaj[t2])) {
                                int gecici = siraliIndisler[j];
                                siraliIndisler[j] = siraliIndisler[j + 1];
                                siraliIndisler[j + 1] = gecici;
                            }
                        }
                    }

                    System.out.println("--- PUAN DURUMU (SIRALI) ---");
                    System.out.printf("%-4s %-15s %-3s %-10s %-10s %-10s %-7s %-7s %-7s %-5s%n",
                            "Sıra", "Takım", "OynananMaç", "Galibiyet", "Beraberlik", "Yenilgi", "Atılan", "Yenilen",
                            "Averaj",
                            "Puan");
                    System.out.println(
                            "-----------------------------------------------------------------------------------------");

                    for (int i = 0; i < 4; i++) {
                        int idx = siraliIndisler[i];
                        System.out.printf("%-4d %-15s %-3d %-10d %-10d %-10d %-7d %-7d %-7d %-5d%n",
                                (i + 1), takimlar[idx], oynananMac[idx], galibiyet[idx], beraberlik[idx], yenilgi[idx],
                                atilanGol[idx], yenilenGol[idx], averaj[idx], puan[idx]);
                    }
                    break;

                default:
                    System.out.println("Hatalı seçim!");
            }
        }
    }
}